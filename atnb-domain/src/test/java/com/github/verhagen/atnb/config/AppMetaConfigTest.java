package com.github.verhagen.atnb.config;

import com.github.verhagen.atnb.config.AppMetaConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppMetaConfigTest {

    @Test
    public void load() {
        AppMetaConfig appMetaConfig = new AppMetaConfig();
        assertEquals("atnb", appMetaConfig.getAppId());
        assertEquals("activity-tracker-notebook", appMetaConfig.getAppName());
        assertEquals("Activity Tracker and Notebook", appMetaConfig.getAppTitle());
        assertEquals("AT&N", appMetaConfig.getAppTitleAbbreviation());
    }
}
