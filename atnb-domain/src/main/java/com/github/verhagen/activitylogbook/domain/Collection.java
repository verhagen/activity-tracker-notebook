package com.github.verhagen.activitylogbook.domain;

import java.util.ArrayList;
import java.util.List;

public class Collection<O extends Identifier<I>, I, T extends Identifier<I>> {
	public O organisation;

	public List<T> items;
	
	public List<I> getIdentifiers() {
		List<I> identifiers = new ArrayList<>();
		for (T item : items) {
			identifiers.add(item.getIdentifier());
		}
		return identifiers;
	}


//	public List<T> getIdentifiers() {
//		return items.stream()
//				.map(i -> i.getIdentifier())
//				.collect(Collectors.toList());
////		List<String> identifiers = new ArrayList<>();
////		for (T item : items) {
////			identifiers.add(item.getIdentifier());
////		}
////		return identifiers;
//	}
}
