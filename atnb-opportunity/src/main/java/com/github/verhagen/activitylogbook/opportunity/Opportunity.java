package com.github.verhagen.activitylogbook.opportunity;

import java.net.URI;
import java.time.LocalDate;

public class Opportunity {
	private final LocalDate creationDate;
	private final String agent;
	private final String organisation;
	private final String title;
	private final URI mailUri;
	

	public Opportunity(Builder bldr) {
		creationDate = bldr.getCreationDate();
		agent = bldr.getAgent();
		organisation = bldr.getOrganisation();
		title = bldr.getTitle();
		mailUri = bldr.getMailUri();
	}


	public LocalDate getCreationDate() {
		return creationDate;
	}
	public String getAgent() {
		return agent;
	}
	public String getOrganisation() {
		return organisation;
	}
	public String getTitle() {
		return title;
	}
	public URI getMailUri() {
		return mailUri;
	}


	public static class Builder {
		private LocalDate creationDate;
		private String agent;
		private String organisation;
		private String title;
		private URI mailUri;

		public Opportunity create() {
			return new Opportunity(this);
		}

		public LocalDate getCreationDate() {
			return creationDate;
		}
		public Builder addCreationDate(LocalDate creationDate) {
			this.creationDate = creationDate;
			return this;
		}
		public String getAgent() {
			return agent;
		}
		public Builder addAgent(String agent) {
			this.agent = agent;
			return this;
		}
		public String getOrganisation() {
			return organisation;
		}
		public Builder addOrganisation(String organisation) {
			this.organisation = organisation;
			return this;
		}
		public String getTitle() {
			return title;
		}
		public Builder addTitle(String title) {
			this.title = title;
			return this;
		}
		public URI getMailUri() {
			return mailUri;
		}
		public Builder addMailUri(URI mailUri) {
			this.mailUri = mailUri;
			return this;
		}
	}
}
