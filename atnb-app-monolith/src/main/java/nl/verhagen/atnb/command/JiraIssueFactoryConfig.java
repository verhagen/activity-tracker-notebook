package nl.verhagen.atnb.command;

import java.net.URI;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class JiraIssueFactoryConfig extends IssueFactoryConfig {
    private String issueMainPrefix;
    private final Set<String> issuePrefix;

    public JiraIssueFactoryConfig(Builder bldr) {
        super(bldr.getSuperBuilder());
        issueMainPrefix = bldr.getIssueMainPrefix();
        issuePrefix = bldr.getIssuePrefix();
    }

    public String getIssueMainPrefix() {
        return issueMainPrefix;
    }

    public Set<String> getIssuePrefix() {
        return issuePrefix;
    }


    static class Builder {
        private IssueFactoryConfig.Builder superBldr = new IssueFactoryConfig.Builder();
        private String issueMainPrefix;
        private Set<String> issuePrefix = new HashSet<>();

        public JiraIssueFactoryConfig create() {
            return new JiraIssueFactoryConfig(this);
        }

        IssueFactoryConfig.Builder getSuperBuilder() {
            return superBldr;
        }

        public String getIssueMainPrefix() {
            return issueMainPrefix;
        }

        public Builder addIssueMainPrefix(String issuePrefix) {
            this.issueMainPrefix = issuePrefix;
            return this;
        }

        public Set<String> getIssuePrefix() {
            return issuePrefix;
        }

        public Builder addIssuePrefix(String issuePrefix) {
            if (issueMainPrefix == null) {
                issueMainPrefix = issuePrefix;
            }
            this.issuePrefix.add(issuePrefix.toUpperCase());
            return this;
        }

        public Builder addOrganisation(String organisation) {
            superBldr.addOrganisation(organisation);
            return this;
        }
        public Builder addProject(String project) {
            superBldr.addProject(project);
            return this;
        }
        public Builder addIssueServer(URI host) {
            superBldr.addIssueServer(host);
            return this;
        }
        public Builder addIssuePath(Path issuePath) {
            superBldr.addIssuePath(issuePath);
            return this;
        }
    }
}
