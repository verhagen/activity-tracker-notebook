package com.github.verhagen.atnb.issue;

import com.github.verhagen.atnb.issue.domain.Issue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

public class JiraIssueTemplate {
    private static Logger log = LoggerFactory.getLogger(JiraIssueTemplate.class);
    private final JiraIssueTemplateConfig cfg;

    public JiraIssueTemplate(JiraIssueTemplateConfig cfg) {
        this.cfg = cfg;
    }

    public String create(Issue issue) {
        List<String> content = new ArrayList<>();
        content.add("# [" + issue.getIssue() + "](" + issue.getIssueUri() + ")" + (issue.getTitle() != null ? "  " + issue.getTitle() : ""));
        content.add("");
        content.add("local-git-repo: (/c/Sources/omgeving1)");
        content.add("branch: (732b_25918)");
        content.add("tags: []");
        content.add("cluster: (2300 - Cluster Zorg Leuven)");
        content.add("gitlab-merge-request:");
        content.add("");
        content.add("## Contacts");
        content.add("");
        content.add("## Background");
        content.add("");
        content.add("## Resources");
        content.add("");
        content.add("## Database");

        StringBuilder bldr = new StringBuilder();
        content.stream().forEach(l -> append(bldr, l));
        return bldr.toString();
    }

    private void append(StringBuilder bldr, String line) {
        if (bldr.length() > 0) {
            bldr.append("\n");
        }
        bldr.append(line);
    }

    public Path getFilePath(Issue issue) {
        return cfg.getTargetPath().resolve(issue.getIssue() + ".md");
    }
    public void createAsFile(Issue issue) throws IOException {
        if (Files.notExists(cfg.getTargetPath())) {
            Files.createDirectories(cfg.getTargetPath());
        }
        Path filePath = getFilePath(issue);
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, CREATE_NEW)) {
            writer.write(create(issue));
            writer.flush();
            log.info("Created document '" + filePath + "'");
        }
    }
}
