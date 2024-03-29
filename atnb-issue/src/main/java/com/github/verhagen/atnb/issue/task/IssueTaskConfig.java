package com.github.verhagen.atnb.issue.task;

import java.net.URI;
import java.util.Arrays;

import com.github.verhagen.atnb.core.task.AbstractTaskConfig;
import com.github.verhagen.atnb.domain.IdentifierCatalog;
import com.github.verhagen.atnb.core.textfield.StringTextField;
import com.github.verhagen.atnb.core.textfield.TextFieldExtractor;
import com.github.verhagen.atnb.core.textfield.UriInformationExtractor;
import com.github.verhagen.atnb.core.textfield.UriTextField;

public class IssueTaskConfig extends AbstractTaskConfig {

	public IssueTaskConfig(IdentifierCatalog idCatalog) {
		super(idCatalog, createTextFieldExtractor());
	}


	private static TextFieldExtractor createTextFieldExtractor() {
		return new TextFieldExtractor(
				Arrays.asList(
					new StringTextField("title")
					, new UriTextField("uri|url")
				)
				, Arrays.asList(
						new UriInformationExtractor(URI.create("https://jira.organisation.org/browse/"))
				)
		);
	}

}
