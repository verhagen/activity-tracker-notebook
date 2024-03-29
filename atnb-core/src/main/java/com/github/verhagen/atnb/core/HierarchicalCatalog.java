package com.github.verhagen.atnb.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A HierarchicalCatalog contains a collection of items.
 *
 * @param <T>
 */
public class HierarchicalCatalog<T> {
	private Logger logger = LoggerFactory.getLogger(HierarchicalCatalog.class);
	private List<T> topLevelItems = new ArrayList<>();
	private Map<T, HierarchicalCatalog<T>> collection = new HashMap<>();

	private HierarchicalCatalog() {
		// Nothing to do
	}
	/**
	 * Specify the top level items. Additional items, need to start with one of these
	 * top level items;
	 * 
	 * @param items The items for the catalog
	 */
	public HierarchicalCatalog(List<T> items) {
		if (items.size() == 0) {
			throw new IllegalArgumentException("Argument 'items' should at least contain 1 item.");
		}
		topLevelItems.addAll(items);
		for (T item : topLevelItems) {
			collection.put(item, new HierarchicalCatalog<>());
		}
		logger.info("Adding items: "  + items);
	}

	
//	public void add(T ...items) {
//		add(Arrays.asList(items));
//	}

	/**
	 * @param existingHierarchy
	 * @param newEntries new hierarchy entries
	 */
	public void add(List<T> existingHierarchy, List<T> newEntries) {
		HierarchicalCatalog<T> catalog = get(existingHierarchy.iterator());
		catalog.addNew(newEntries.iterator());
	}

	private HierarchicalCatalog<T> get(Iterator<T> iterator) {
		if (! iterator.hasNext()) {
			return this;
		}
		
		T nextItem = iterator.next();
		if (! topLevelItems.contains(nextItem)) {
			throw new IllegalArgumentException("The item with value '" + nextItem + "' is not a known top level item of this HierarchicalRegistery."
					+ " Expected one of " + topLevelItems + ".");
		}
		
		return collection.get(nextItem).get(iterator);
	}

	private void add(Iterator<T> iterator) {
		if (! iterator.hasNext()) {
			return;
		}
		T item = iterator.next();
		if (! topLevelItems.contains(item)) {
			throw new IllegalArgumentException("Argument '" + item + "' is not a known top level item of this HierarchicalRegistery."
					+ " Expected one of " + topLevelItems + ".");
		}
		if (! collection.containsKey(item)) {
			collection.put(item, new HierarchicalCatalog<>(Collections.singletonList(item)));
			collection.get(item).addNew(iterator);
		}
		else {
			collection.get(item).add(iterator);
		}
	}

	private void addNew(Iterator<T> iterator) {
		if (! iterator.hasNext()) {
			return;
		}
		T item = iterator.next();
		collection.put(item, new HierarchicalCatalog<>(Collections.singletonList(item)));
		collection.get(item).addNew(iterator);
	}

	public List<List<String>> getCollection() {
		// TODO Auto-generated method stub
		return null;
	}



}
