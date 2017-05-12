package org;

import org.helper.*;
import org.util.ClassUtil;

/**
 * Created by Smith on 2017/4/20.
 */
public final class HelperLoader {
    public static void init() {
        Class<?>[] classes = {
                // 加载所有类，未实例化
                ClassHelper.class,
                // 加载所有实例化后的类，放在BEAN_MAP中
                BeanHelper.class,
                // 将代理完毕的类重新放进BEAN_MAP中
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls: classes) {
            ClassUtil.loadClass(cls.getName(), true);
        }
    }


}
