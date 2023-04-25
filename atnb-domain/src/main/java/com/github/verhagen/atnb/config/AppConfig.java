package com.github.verhagen.atnb.config;

import com.github.verhagen.atnb.AtnbRuntimeException;
import com.github.verhagen.atnb.domain.IdentifierCatalog;
import com.github.verhagen.atnb.domain.IdentifierCatalogImpl;
import org.apache.commons.lang3.StringUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

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
    private boolean isUserHomeConfig = false;
    private Path configDir;
    private Path configFile;

    private IdentifierCatalog idCatalog;

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
            throw new AppConfigException("To be filled in...");
        }
        isUserHomeConfig = bldr.isSimulateUserHomeActive
                ? configDir.getParent().equals(bldr.userHome)
                : configDir.getParent().equals(Paths.get(System.getProperty("user.home")));

        idCatalog = create();
    }

    private IdentifierCatalog create() {
        IdentifierCatalog idCatalog = new IdentifierCatalogImpl();
        // TODO [2023.04.25 TV] Remove default IdentifierCatalog filling
        List<String> ids = Arrays.asList(new String[] {
                "programming.language.java"
                , "programming.language.kotlin"
                , "programming.language.type-script"
                , "programming.language.java.library.apache-commons-stringutils"
                , "programming.language.java.library.junit"
                , "build-tool.maven"
                , "build-tool.gradle"
                , "publisher.pragprog.book.mazes-for-programmers"
                , "publisher.manning.book.software-development-metrics"
                , "project.mazes-for-programmers"
                , "project.mazes-for-programmers-type-script"
                , "organization.aaa.space.kkk.project.mazes-for-programmers-type-script"
        });
        idCatalog.add(ids);
        return idCatalog;
    }
    public Path getConfigDir() {
        return configDir;
    }

    public Path getConfigFile() {
        return configFile;
    }

    public Path getWorkspace() {
        if (isUserHomeConfig) {
            throw new AtnbRuntimeException("This is the user personal configuration, it does not have a workspace.");
        }
        return configDir.getParent();
    }

    public IdentifierCatalog getIdentifierCatalog() {
        return idCatalog;
    }

    public static class Builder {
        private final boolean isSimulateUserHomeActive;
        private final Path userHome;
        private Path path;

        public Builder() {
            this(false, null);
        }
        public Builder(boolean isSimulateUserHomeActive, Path userHome) {
            this.isSimulateUserHomeActive = isSimulateUserHomeActive;
            this.userHome = userHome;
        }

        public AppConfig create() {
            return new AppConfig(this);
        }
        public Builder setPath(Path path) {
//            if (isSimulateUserHomeActive) {
//                if (path == userHome) {
//
//                }
//            }
//            else {
                this.path = path;

//            }
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
