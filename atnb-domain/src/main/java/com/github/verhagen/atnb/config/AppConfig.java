package com.github.verhagen.atnb.config;

import org.apache.commons.lang3.StringUtils;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * some-dir-name                        <-- workspace-dir
 *              /.atnb                  <-- config-dir
 *                    /atnb.properties  <-- config-file
 *
 */
public class AppConfig {
    private final static String APP_ID = new AppMetaConfig().getAppId();
    private final static String APP_CONFIG_FILE_NAME = new AppMetaConfig().getAppId() + ".properties";
    private final static String APP_CONFIG_DIR_NAME = "." + new AppMetaConfig().getAppId();
    private Path configDir;
    private Path configFile;

    public AppConfig(Builder bldr) {
        Path path = bldr.getPath();
        if (! path.toFile().exists()) {
            throw new AppConfigException("No file or directory found for given path '" + path + "'");
        }
        if (path.toFile().isDirectory()) {
            if (APP_CONFIG_DIR_NAME.equals(path.toFile().getName())) {
                configDir = path;
                configFile = path.resolve(APP_CONFIG_FILE_NAME);
                if (! configFile.toFile().isFile()) {
                    throw new AppConfigException("The given directory '" + path + "' has the correct name '"
                            + APP_CONFIG_DIR_NAME + "'. But no file found with the name '" + APP_CONFIG_FILE_NAME
                            + "' inside the directory.");
                }
            }
            else {
                throw new AppConfigException("The given directory '" + path + "' has not the correct name '"
                        + APP_CONFIG_DIR_NAME + "'.");
            }

        }
        else if (path.toFile().isFile()) {
            if (APP_CONFIG_FILE_NAME.equals(path.toFile().getName())) {
                configDir = path.getParent();
                configFile = path;
                if (! APP_CONFIG_DIR_NAME.equals(configDir.toFile().getName())) {
                    throw new AppConfigException("The given file '" + path + "' has the correct name '"
                            + APP_CONFIG_FILE_NAME + "'. But it is not inside a directory with the name '" + APP_CONFIG_DIR_NAME
                            + "'.");

                }
            }
            else {
                throw new AppConfigException("The given file '" + path + "' has not the correct name '"
                        + APP_CONFIG_FILE_NAME + "'.");
            }
        }
        else {
            throw new AppConfigException("");
        }
    }

    public Path getConfigDir() {
        return configDir;
    }

    public Path getConfigFile() {
        return configFile;
    }

    public static class Builder {
        private Path path;

        public AppConfig create() {
            return new AppConfig(this);
        }
        public Builder setPath(Path path) {
            this.path = path;
            return this;
        }
        public Builder setPath(String pathStr) {
            pathStr = StringUtils.trimToNull(pathStr);
            return pathStr == null ? this : setPath(Paths.get(pathStr));
        }

        public Path getPath() {
            return path;
        }
    }

}
