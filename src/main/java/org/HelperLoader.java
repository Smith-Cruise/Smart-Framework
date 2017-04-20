package org;

import org.helper.BeanHelper;
import org.helper.ClassHelper;
import org.helper.IocHelper;
import org.util.ClassUtil;
import org.helper.ControllerHelper;

/**
 * Created by Smith on 2017/4/20.
 */
public final class HelperLoader {
    public static void init() {
        Class<?>[] classes = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls: classes) {
            ClassUtil.loadClass(cls.getName(), true);
        }
    }


}
