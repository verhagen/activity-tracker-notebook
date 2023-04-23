package com.github.verhagen.atnb.core;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsTest {
    @Test
    public void resolveVariables_noVariable() throws IOException {
        Path path = new FileUtils().resolveVariables("src/main/java");
        assertTrue(path.toFile().isDirectory(), "Expected '" + path + " to be a directory.");
    }

    @Test
    public void resolveVariables_baseDir() throws IOException {
        Path path = new FileUtils().resolveVariables("{{base-dir}}/src/test");
        assertNotNull(path, "Expected 'path' to be not null.");
        assertTrue(path.toFile().isDirectory(), "Expected '" + path + " to be a directory.");
    }
    @Test
    public void resolveVariables_unknownVariable() {
        try {
            new FileUtils().resolveVariables("{{home-dir}}/dev/project");
            fail();
        }
        catch (IOException ioe) {
            assertEquals("Argument 'pathStr' with value '{{home-dir}}/dev/project' contains variable '{{home-dir}}"
                    + ", which is not a known variable. Known variable names are [temp-dir, base-dir, user-home]."
                    , ioe.getMessage());
        }
    }
    @Test
    public void resolveVariables_withProperties() throws IOException {
        Properties props = new Properties();
        props.setProperty("target-path", "{{base-dir}}/build/test-case/user-home-default/");
        props.setProperty("issue-target-path", "{{target-path}}/issues/");

        Path path = new FileUtils().resolveVariables(props.getProperty("issue-target-path"), props);
        assertNotNull(path, "Expected 'path' to be not null.");
        String expectedPath = "build/test-case/user-home-default/issues";
        assertTrue(path.toString().endsWith(expectedPath), "The path with value '" + path + "' should end with '" + expectedPath + "'");
    }

}
