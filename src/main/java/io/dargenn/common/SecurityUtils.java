package io.dargenn.common;

import lombok.SneakyThrows;

import java.util.Properties;

public class SecurityUtils {
    @SneakyThrows
    public static void prepareSecurity() {
        Properties properties = PropertiesUtils.getProperties();
        System.setProperty("java.security.policy", properties.getProperty("security.policy.file.path"));
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
    }
}
