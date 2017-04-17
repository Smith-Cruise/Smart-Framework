package org.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Smith on 2017/4/16.
 */
public final class ClassUtil {
    private static final Logger LOGGER = LogManager.getLogger();
    private static Set<Class<?>> classSet = new HashSet<>();

    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error("Class not found");
            throw new RuntimeException(e);
        }
        return cls;
    }

    public static Set<Class<?>> getClassSet(String packageName) {
        StringBuffer stringBuffer = new StringBuffer(getClassLoader().getResource("").getFile());
        stringBuffer.deleteCharAt(0);
        String originalPath = stringBuffer.toString().replace("/", "\\");
        try {
            URL url = getClassLoader().getResource(packageName.replace(".", "/"));
            File[] files = new File(url.getPath()).listFiles();
            for (File file: files) {
                if (file.getName().endsWith(".class")) {
                    //System.out.println(file.getAbsolutePath());
                    doAddClass(classSet, file.getAbsolutePath(), originalPath);
                } else if (file.getName().endsWith(".jar")) {

                } else if (file.isDirectory()) {
                    getClassSet(packageName+"."+file.getName());
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return classSet;
    }

    private static void doAddClass(Set<Class<?>> classSet, String path, String originalPath) {
        String packagePath = path.replace(originalPath, "");
        String packageName = packagePath.replace("\\", ".").replace(".class", "");
        classSet.add(loadClass(packageName, false));
    }
}
