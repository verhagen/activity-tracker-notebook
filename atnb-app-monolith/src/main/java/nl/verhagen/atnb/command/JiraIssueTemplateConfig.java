package nl.verhagen.atnb.command;

import java.nio.file.Path;

public class JiraIssueTemplateConfig {
    private final Path targetPath;

    public JiraIssueTemplateConfig(Path targetPath) {
        this.targetPath = targetPath;
    }

    public Path getTargetPath() {
        return targetPath;
    }

}
