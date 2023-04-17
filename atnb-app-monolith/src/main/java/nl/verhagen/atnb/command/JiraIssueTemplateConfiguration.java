package nl.verhagen.atnb.command;

import java.nio.file.Path;

public class JiraIssueTemplateConfiguration {
    private final Path targetPath;

    public JiraIssueTemplateConfiguration(Path targetPath) {
        this.targetPath = targetPath;
    }

    public Path getTargetPath() {
        return targetPath;
    }

}
