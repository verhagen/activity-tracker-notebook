package com.github.verhagen.atnb.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileUtils {
    private Logger logger = LoggerFactory.getLogger(FileUtils.class);
    private Pattern variablePattern = Pattern.compile(".*\\{\\{([a-z-]*)\\}\\}.*");
    private static Map<String, String> JAVA_PROPERTY_KEYS = new HashMap<>();
    private static Map<String, String> map = new HashMap<>();

    static {
        JAVA_PROPERTY_KEYS.put("user.dir", "base-dir");
        JAVA_PROPERTY_KEYS.put("user.home", "user-home");
        JAVA_PROPERTY_KEYS.put("java.io.tmpdir", "temp-dir");
    }

    public FileUtils() {
        JAVA_PROPERTY_KEYS.keySet().stream().forEach(k -> map.put(JAVA_PROPERTY_KEYS.get(k), System.getProperty(k)));
    }

    public Path resolveVariables(String pathStr) throws IOException {
        return resolveVariables(pathStr, new Properties());
    }
    public Path resolveVariables(String pathStr, Properties props) throws IOException {
        if (! pathStr.contains("{{")) {
            return Paths.get(pathStr);
        }

        while (pathStr.contains("{{")) {
            Matcher matcher = variablePattern.matcher(pathStr);
            if (matcher.matches()) {
                String key = matcher.group(1);
                logger.info("variable: " + key);
                if (props.containsKey(key)) {
                    pathStr = pathStr.replace("{{" + key + "}}", props.getProperty(key));
                }
                else if (! JAVA_PROPERTY_KEYS.values().contains(key)) {
                    throw new IOException("Argument 'pathStr' with value '" + pathStr
                            + "' contains variable '{{" + key + "}}, which is not a known variable."
                            + " Known variable names are [" + JAVA_PROPERTY_KEYS.values().stream().collect(Collectors.joining(", ")) + "]."
                            + (props.size() > 0 ? " And it is also not a known key in the given Properties instance."
                            + " Known property-keys: [" + props.keySet().stream().map(k -> k.toString()).collect(Collectors.joining(", ")) + "].": ""));
                }
                else {
                    pathStr = pathStr.replace("{{" + key + "}}", map.get(key));
                }
            }
        }
        return Paths.get(pathStr);
    }

}
