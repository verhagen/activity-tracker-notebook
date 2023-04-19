package com.github.verhagen.atnb;

import com.github.verhagen.atnb.config.AppMetaConfig;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceHelper {
    private Path testDataPath;

    public ResourceHelper(Path testDataPath) {
        if (testDataPath == null) {
            throw new IllegalArgumentException("Argument 'testDataPath' should not be null.");
        }
        else if (! testDataPath.toFile().isDirectory()) {
            throw new IllegalArgumentException("Argument 'testDataPath' with value '" + testDataPath
                    + "' should be an existing directory.");
        }
        this.testDataPath = testDataPath;
    }

    public ResourceHelper(String testDataPathStr) {
        this(Paths.get(testDataPathStr));
    }

    public Path getResource(String resourcePathStr) {
        return testDataPath.resolve(resourcePathStr);
    }
    public Path getResource(String testCaseName, String fileName) {
        return testDataPath.resolve(testCaseName).resolve(fileName);
    }

    public Path getConfigDirPath(String testCaseName) throws IOException {
        Path path = testDataPath.resolve(testCaseName);
        if (! path.toFile().isDirectory()) {
            throw new IOException("Expected to find a directory '" + path + "'.");
        }
        path = path.resolve("." + new AppMetaConfig().getAppId());
        if (! path.toFile().isDirectory()) {
            throw new IOException("Expected to find a directory '" + path + "'.");
        }
        return path;
    }
    public Path getConfigFilePath(String testCaseName) throws IOException {
        Path path = testDataPath.resolve(testCaseName);
        if (! path.toFile().isDirectory()) {
            throw new IOException("Expected to find a directory '" + path + "'.");
        }
        path = path.resolve("." +  new AppMetaConfig().getAppId());
        if (! path.toFile().isDirectory()) {
            throw new IOException("Expected to find a directory '" + path + "'.");
        }
        path = path.resolve(new AppMetaConfig().getAppId() + ".properties");
        if (! path.toFile().isFile()) {
            throw new IOException("Expected to find a file '" + path + "'.");
        }
        return path;
    }
}
