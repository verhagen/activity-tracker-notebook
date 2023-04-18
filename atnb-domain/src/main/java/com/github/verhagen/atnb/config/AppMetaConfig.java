package com.github.verhagen.atnb.config;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AppMetaConfig {
    public final static String APP_ID = "app.id";
    public final static String APP_ID_DOC = "The Application Identifier";
    public final static String APP_NAME = "app.name";
    public final static String APP_NAME_DOC = "The long identifier, a bit like a full name, but all lower case, no spaces - computer file friendly.";
    public final static String APP_TITLE = "app.title";
    public final static String APP_TITLE_DOC = "The Application Title, human friendly";
    public final static String APP_TITLE_ABBREVIATION = "app.title.abbreviation";
    public final static String APP_TITLE_ABBREVIATION_DOC = "The Application Title abbreviation, human friendly";
    private final static Map<String, String> keyDocumentation = new HashMap<>();
    static {
        keyDocumentation.put(APP_ID, APP_ID_DOC);
        keyDocumentation.put(APP_NAME, APP_NAME_DOC);
        keyDocumentation.put(APP_TITLE, APP_TITLE_DOC);
        keyDocumentation.put(APP_TITLE_ABBREVIATION, APP_TITLE_ABBREVIATION_DOC);
    }

    private final Properties props;

    public AppMetaConfig() {
        String fileName = "app-meta-config.properties";
        URL appMetaCfgUri = AppMetaConfig.class.getClassLoader().getResource(fileName);
        props = new Properties();
        try {
            props.load(AppMetaConfig.class.getClassLoader().getResourceAsStream(fileName));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getAppId() {
        return props.getProperty(APP_ID);
    }
    public String getAppName() {
        return props.getProperty(APP_NAME);
    }
    public String getAppTitle() {
        return props.getProperty(APP_TITLE);
    }
    public String getAppTitleAbbreviation() {
        return props.getProperty(APP_TITLE_ABBREVIATION);
    }

}
