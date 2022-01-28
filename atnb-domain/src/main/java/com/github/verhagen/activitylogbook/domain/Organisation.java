package com.github.verhagen.activitylogbook.domain;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public abstract class Organisation<I extends IdentifierSupplier<String>> implements IdentifierSupplier<String> {
	public URI url;
	public String name;
	public List<String> tags;
	public List<I> items;

	public abstract String getIdentifierType();

	@Override
	public String getIdentifier() {
		String hostName = url.getHost();
		if (hostName.contains(".")) {
			hostName = hostName.substring(0, hostName.lastIndexOf("."));
			if (hostName.contains(".")) { 
				hostName = hostName.substring(hostName.lastIndexOf(".") +1);
			}
		}
		return hostName;
		//return name.toLowerCase().replaceAll(" ", "-");
	}

	public List<String> getIdentifiers() {
		List<String> identifiers = new ArrayList<>();
		String prefix = String.join(".", getIdentifierType(), getIdentifier()); 
		for (I item : items) {
			identifiers.add(String.join(".", prefix, item.getIdentifierType(), item.getIdentifier()));
		}
		return identifiers;
	}

}
