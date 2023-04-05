package com.github.verhagen.activitylogbook.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @param <P> Path / bread crumb
 * @param <V> Value
 */
public class Node<P, V> {
	private Logger logger = LoggerFactory.getLogger(Node.class);
	private final P breadCrumb;
	private final V value;
	private final Node<P, V> parent;
	private final Map<P, Node<P, V>> childNodes = new HashMap<>();


	public Node(P breadCrumb, V value, Node<P, V> parent) {
		this.breadCrumb = breadCrumb;
		this.value = value;
		this.parent = parent;
	}

	public void add(List<P> breadCrumbs, V  value) {
		addChildNode(breadCrumbs.iterator(), value);
	}

	private void addChildNode(Iterator<P> iter, V value) {
		P breadCrumb = iter.next();
		if (! childNodes.containsKey(breadCrumb)) {
			if (iter.hasNext()) {
				childNodes.put(breadCrumb, new Node<>(breadCrumb, null, this));
			}
		}
		if (iter.hasNext()) {
			childNodes.get(breadCrumb).addChildNode(iter, value);
		}
		else if (! childNodes.containsKey(breadCrumb)) {
			childNodes.put(breadCrumb, new Node<>(breadCrumb, value, this));
		}
		else {
			logger.info("BreadCrumb with value '" + value + "' already registered.");
		}
	}


	public P getBreadCrumb() {
		return breadCrumb;
	}

	public V getValue() {
		return value;
	}


	public void accept(Visitor<Node<P, V>> visitor) {
		visitor.visit(this);
		for (Node<P, V> node : childNodes.values()) {
			node.accept(visitor);
		}
	}

	public List<P> getBreadCrumbs() {
		ArrayList<P> breadCrumbs = new ArrayList<>();
		getBreadCrumbs(breadCrumbs, this);
		return breadCrumbs;
	}

	private List<P> getBreadCrumbs(ArrayList<P> breadCrumbs, Node<P, V> node) {
		if (node.parent != null) {
			getBreadCrumbs(breadCrumbs, node.parent);
		}
		else {
			return breadCrumbs;
		}
		breadCrumbs.add(node.breadCrumb);
		return breadCrumbs;
	}

	@Override
	public String toString() {
		return "breadCrumb: " + breadCrumb + "    " + value;
	}
}
