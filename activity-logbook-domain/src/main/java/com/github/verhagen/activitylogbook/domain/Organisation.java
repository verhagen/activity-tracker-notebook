package com.github.verhagen.activitylogbook.domain;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

public class Organisation<T extends ActivityIdentifier> {
	public URI url;
	public String name;
	public List<T> items;


	public List<String> getIdentifiers() {
		return items.stream()
				.map(i -> i.getIdentifier())
				.collect(Collectors.toList());
//		List<String> identifiers = new ArrayList<>();
//		for (T item : items) {
//			identifiers.add(item.getIdentifier());
//		}
//		return identifiers;
	}
}
