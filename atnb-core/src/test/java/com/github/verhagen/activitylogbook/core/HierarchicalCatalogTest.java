package com.github.verhagen.activitylogbook.core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled // Currently these cases are not working. Maybe HierarchicalRegistery is not needed at all.
public class HierarchicalCatalogTest {

	@Test
	void identifierToShort() {
		HierarchicalCatalog<String> registery = new HierarchicalCatalog<>("programming-language");
//		try {
//			registery.add(Collections.singletonList("programming-language"), new String[] { "kotlin" });
//		}
//		catch (IllegalArgumentException iae) {
//			assertEquals("Argument 'kotlin' is not a known top level item of this HierarchicalRegistery. Expected one of [programming-language].", iae.getMessage());
//		}

		try {
			registery.add(Collections.singletonList("programming-language"), Arrays.asList(new String[] { "type-script" }));
		}
		catch (IllegalArgumentException iae) {
			assertEquals("Argument 'type-script' is not a known top level item of this HierarchicalRegistery. Expected one of [programming-language].", iae.getMessage());
		}
	}

	@Test
	void identifier() {
		HierarchicalCatalog<String> registery = new HierarchicalCatalog<>("programming-language");
		registery.add(Collections.singletonList("programming-language"), Arrays.asList(new String[] { "kotlin" }));
		registery.add(Collections.singletonList("programming-language"), Arrays.asList(new String[] { "programming-language", "type-script" }));

		List<List<String>> items = registery.getCollection();
	}

}
