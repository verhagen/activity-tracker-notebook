package com.github.verhagen.atnb.config;

import com.github.verhagen.atnb.config.AppConfig;
import com.github.verhagen.atnb.config.AppConfigException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class AppConfigTest {
    private Path resourcesPath = Paths.get("src/test/data");
    private Path configDir = resourcesPath.resolve(".atnb");
    private Path configFile = configDir.resolve("atnb.properties");

    @BeforeEach
    public void setUp() {
        assertTrue(resourcesPath.toFile().isDirectory());
        assertTrue(configDir.toFile().isDirectory());
        assertTrue(configFile.toFile().isFile());
    }

    @Test
    public void loadByConfigDir() {
        AppConfig appConfig = new AppConfig.Builder().setPath(configDir).create();
        assertTrue(appConfig.getConfigDir().toFile().isDirectory());
        assertTrue(appConfig.getConfigFile().toFile().isFile());
    }
    @Test
    public void loadByConfigFile() {
        AppConfig appConfig = new AppConfig.Builder().setPath(configFile).create();
        assertTrue(appConfig.getConfigDir().toFile().isDirectory());
        assertTrue(appConfig.getConfigFile().toFile().isFile());
    }

    @Test
    public void loadIncorrectFile1() {
        try {
            new AppConfig.Builder().setPath(resourcesPath.resolve("incorrect-config-file/.atnb/app.properties")).create();
            fail();
        }
        catch (AppConfigException  ace) {
            assertEquals("The given file 'src/test/data/incorrect-config-file/.atnb/app.properties' has not the correct name 'atnb.properties'.", ace.getMessage());
        }
    }

    @Test
    public void loadIncorrectFile2() {
        try {
            new AppConfig.Builder().setPath(resourcesPath.resolve("incorrect-config-file/.atnb")).create();
            fail();
        }
        catch (AppConfigException  ace) {
            assertEquals("The given directory 'src/test/data/incorrect-config-file/.atnb' has the correct name '.atnb'. But no file found with the name 'atnb.properties' inside the directory.", ace.getMessage());
        }
    }

    @Test
    public void loadIncorrectDir1() {
        try {
            new AppConfig.Builder().setPath(resourcesPath.resolve("incorrect-config-dir/app/atnb.properties")).create();
            fail();
        }
        catch (AppConfigException  ace) {
            assertEquals("The given file 'src/test/data/incorrect-config-dir/app/atnb.properties' has the correct name 'atnb.properties'. But it is not inside a directory with the name '.atnb'.", ace.getMessage());
        }
    }
    @Test
    public void loadIncorrectDir2() {
        try {
            new AppConfig.Builder().setPath(resourcesPath.resolve("incorrect-config-dir/app")).create();
            fail();
        }
        catch (AppConfigException  ace) {
            assertEquals("The given directory 'src/test/data/incorrect-config-dir/app' has not the correct name '.atnb'.", ace.getMessage());
        }
    }

}
