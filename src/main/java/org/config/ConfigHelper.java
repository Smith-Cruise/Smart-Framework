package org.config;

import java.util.Properties;

/**
 * Created by Smith on 2017/4/16.
 */
public final class ConfigHelper {
    private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    public static String getAppBasePackage() {
        return CONFIG_PROPS.getProperty(ConfigConstant.APP_BASE_PACKAGE);
    }

    public static String getAppJspPath() {
        return CONFIG_PROPS.getProperty(ConfigConstant.APP_JSP_PATH);
    }

    public static String getAppAssetPath() {
        return CONFIG_PROPS.getProperty(ConfigConstant.APP_ASSET_PATH);
    }

}
