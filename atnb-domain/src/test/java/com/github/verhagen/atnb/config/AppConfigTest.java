package com.github.verhagen.atnb.config;

import com.github.verhagen.atnb.AtnbRuntimeException;
import com.github.verhagen.atnb.ResourceHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class AppConfigTest {
    private final ResourceHelper resrcHlpr = new ResourceHelper("src/test/data");

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void loadByConfigDir() throws IOException {
        AppConfig appConfig = new AppConfig.Builder().setPath(resrcHlpr.getConfigDirPath("correct-config-dir")).create();
        assertTrue(appConfig.getConfigDir().toFile().isDirectory());
        assertTrue(appConfig.getConfigFile().toFile().isFile());
        assertTrue(appConfig.getWorkspace().toFile().isDirectory());
    }
    @Test
    public void loadByConfigFile() throws IOException {
        AppConfig appConfig = new AppConfig.Builder().setPath(resrcHlpr.getConfigFilePath("correct-config-dir")).create();
        assertTrue(appConfig.getConfigDir().toFile().isDirectory());
        assertTrue(appConfig.getConfigFile().toFile().isFile());
        assertTrue(appConfig.getWorkspace().toFile().isDirectory());
    }

    @Test
    public void loadIncorrectFile1() {
        try {
            new AppConfig.Builder().setPath(resrcHlpr.getResource("incorrect-config-file",".atnb/app.properties" )).create();
            fail();
        }
        catch (AppConfigException  ace) {
            assertEquals("The given file 'src/test/data/incorrect-config-file/.atnb/app.properties' has not the correct name 'atnb.properties'.", ace.getMessage());
        }
    }

    @Test
    public void loadIncorrectFile2() {
        try {
            new AppConfig.Builder().setPath(resrcHlpr.getResource("incorrect-config-file", ".atnb")).create();
            fail();
        }
        catch (AppConfigException  ace) {
            assertEquals("The given directory 'src/test/data/incorrect-config-file/.atnb' has the correct name '.atnb'. But no file found with the name 'atnb.properties' inside the directory.", ace.getMessage());
        }
    }

    @Test
    public void loadIncorrectDir1() {
        try {
            new AppConfig.Builder().setPath(resrcHlpr.getResource("incorrect-config-dir","app/atnb.properties")).create();
            fail();
        }
        catch (AppConfigException  ace) {
            assertEquals("The given file 'src/test/data/incorrect-config-dir/app/atnb.properties' has the correct name 'atnb.properties'. But it is not inside a directory with the name '.atnb'.", ace.getMessage());
        }
    }
    @Test
    public void loadIncorrectDir2() {
        try {
            new AppConfig.Builder().setPath(resrcHlpr.getResource("incorrect-config-dir", "app")).create();
            fail();
        }
        catch (AppConfigException  ace) {
            assertEquals("The given directory 'src/test/data/incorrect-config-dir/app' has not the correct name '.atnb'.", ace.getMessage());
        }
    }

    @Test
    public void userHome() {
        AppConfig appConfig = new AppConfig.Builder(true, resrcHlpr.getResource("user-home"))
                .setPath(resrcHlpr.getResource("user-home", "." + new AppMetaConfig().getAppId())).create();
        try {
            appConfig.getWorkspace();
            fail();
        }
        catch (AtnbRuntimeException ae) {
            assertEquals("This is the user personal configuration, it does not have a workspace.", ae.getMessage());
        }
    }

}
