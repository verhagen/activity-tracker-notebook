package nl.verhagen.activitylogger.command;

import java.net.URI;
import java.nio.file.Path;

public class IssueFactoryConfiguration {
    private final String organisation;
    private final String project;
    private final Path issuePath;
    private final URI host;

    public IssueFactoryConfiguration(Builder bldr) {
        organisation = bldr.getOrganisation();
        project = bldr.getProject();
        issuePath = bldr.getIssuePath();
        host = bldr.getIssueServer();
    }

    public String getOrganisation() {
        return organisation;
    }

    public String getProject() {
        return project;
    }

    public Path getIssuePath() {
        return issuePath;
    }

    public URI getIssueServer() {
        return host;
    }


    static class Builder {
        private String organisation;
        private String project;
        private Path issuePath;
        private URI host;

        public IssueFactoryConfiguration create() {
            return new IssueFactoryConfiguration(this);
        }

        public Path getIssuePath() {
            return issuePath;
        }
        public Builder addIssuePath(Path issuePath) {
            this.issuePath = issuePath;
            return this;
        }

        public URI getIssueServer() {
            return host;
        }
        public Builder addIssueServer(URI host) {
            this.host = host;
            return this;
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
    }
}
