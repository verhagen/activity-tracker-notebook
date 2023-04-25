package com.github.verhagen.atnb.issue.domain;

import org.apache.commons.lang3.StringUtils;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

public class JiraIssue implements Issue {
    private final String organisation;
    private final String project;
    private final String issuePrefix;
    private final long number;
    private final String title;
    private final URI issueServer;

    public JiraIssue(Builder bldr) {
        String organisationCln = StringUtils.trimToNull(bldr.organisation);
        String projectCln = StringUtils.trimToNull(bldr.project);
        String issuePrefixCln = StringUtils.trimToNull(bldr.issuePrefix);
        String titleCln = StringUtils.trimToNull(bldr.title);

        if (organisationCln == null) {
            throw new IllegalArgumentException("Argument 'organisation' should not be null.");
        }
        if (projectCln == null) {
            throw new IllegalArgumentException("Argument 'project' should not be null.");
        }
        if (issuePrefixCln == null) {
            throw new IllegalArgumentException("Argument 'issuePrefix' should not be null.");
        }
//        if (titleCln == null) {
//            throw new IllegalArgumentException("Argument 'title' should not be null.");
//        }
        organisation = organisationCln;
        project = projectCln;
        issuePrefix = issuePrefixCln;
        number = bldr.getNumber();
        title = titleCln;
        issueServer = bldr.getIssueServer();
    }

    @Override
    public String getOrganisation() {
        return organisation;
    }

    @Override
    public String getProject() {
        return project;
    }

    public String getIssuePrefix() {
        return issuePrefix;
    }

    public long getNumber() {
        return number;
    }

    @Override
    public String getIssue() {
        return issuePrefix.toUpperCase() + "-" + number;
    }

    @Override
    public URI getIssueUri() {
        return URI.create(issueServer + "browse/" + getIssue());
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public List<String> getIdentifier() {
        return Arrays.asList("organisation", organisation, "project", project
                , "issue", getIssue());
    }




    public static class Builder {
        private String organisation;
        private String project;
        private String issuePrefix;
        private long number;
        private String title;
        private URI issueServer;

        public JiraIssue create() {
            return new JiraIssue(this);
        }


        public String getOrganisation() {
            return organisation;
        }
        public Builder addOrganisation(String organisation) {
            this.organisation = organisation;
            return this;
        }

        public String getProject() {
            return project;
        }
        public Builder addProject(String project) {
            this.project = project;
            return this;
        }

        public String getIssuePrefix() {
            return issuePrefix;
        }
        public Builder addPrefix(String issuePrefix) {
            this.issuePrefix = issuePrefix;
            return this;
        }

        public long getNumber() {
            return number;
        }
        public Builder addNumber(long number) {
            this.number = number;
            return this;
        }

        public URI getIssueServer() {
            return this.issueServer;
        }
        public Builder addIssueServer(URI issueServer) {
            this.issueServer = issueServer;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public Builder addTitle(String title) {
            this.title = title;
            return this;
        }
    }
}
