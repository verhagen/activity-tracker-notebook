package nl.verhagen.atnb.command;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class JiraIssueTemplateTest {
    private static Path targetPath = Paths.get("target/issue");

    @BeforeAll
    public static void setUp() {
        if (Files.exists(targetPath)) {
            try {
                Files.walkFileTree(targetPath, new FileVisitor<Path>() {
                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Files.deleteIfExists(file);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        Files.deleteIfExists(dir);
                        return FileVisitResult.CONTINUE;
                    }
                });
                //Files.deleteIfExists(targetPath);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void create() {
        JiraIssueTemplate jit = new JiraIssueTemplate(new JiraIssueTemplateConfiguration(targetPath));
        String text = jit.create(
                new JiraIssue.Builder()
                        .addOrganisation("github")
                        .addProject("docs")
                        .addPrefix("github-docs")
                        .addNumber(12)
                        .addIssueServer(URI.create("https://jira.github.com/"))
                        .create()
        );

        String expected =
                "# [GITHUB-DOCS-12](https://jira.github.com/browse/GITHUB-DOCS-12)\n" +
                "\n" +
                "local-git-repo: (/c/Sources/omgeving1)\n" +
                "branch: (732b_25918)\n" +
                "tags: []\n" +
                "cluster: (2300 - Cluster Zorg Leuven)\n" +
                "gitlab-merge-request:\n" +
                "\n" +
                "## Contacts\n" +
                "\n" +
                "## Background\n" +
                "\n" +
                "## Resources\n" +
                "\n" +
                "## Database" +
                "";

        assertEquals(expected, text);
    }

    @Test
    public void createAsFile() throws IOException {

        JiraIssueTemplate jit = new JiraIssueTemplate(new JiraIssueTemplateConfiguration(targetPath));
        jit.createAsFile(
                new JiraIssue.Builder()
                        .addOrganisation("github")
                        .addProject("docs")
                        .addPrefix("github-docs")
                        .addNumber(24)
                        .addIssueServer(URI.create("https://jira.github.com/"))
                        .create()
        );

        Path expectedPath = Paths.get("target/issue/GITHUB-DOCS-24.md");
        assertTrue(Files.exists(expectedPath), "Could not find expected file '" + expectedPath + "' absolute path '" + expectedPath.toAbsolutePath() + "'");
    }
}
