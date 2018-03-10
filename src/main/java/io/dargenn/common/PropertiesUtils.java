package io.dargenn.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class PropertiesUtils {
    private PropertiesUtils() {
    }

    static Properties getProperties() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream("configuration.properties");
        properties.load(inputStream);
        return properties;
    }
}
