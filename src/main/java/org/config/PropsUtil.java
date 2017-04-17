package org.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Smith on 2017/4/16.
 */
final class PropsUtil {
    private static final Logger LOGGER = LogManager.getLogger();

    static Properties loadProps(String file) {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (Exception e) {
            LOGGER.error("Can't find framework configuration");
        }
        return properties;
    }
}
