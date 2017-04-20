package org.helper;

import org.annotation.Action;
import org.bean.Handler;
import org.bean.Request;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Smith on 2017/4/19.
 */
public class ControllerHelper {
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();

    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        for (Class<?> cls: controllerClassSet) {
            /*
            * 获取Controller中定义的方法
            * */
            Method[] methods = cls.getDeclaredMethods();
            for (Method method: methods) {
                /*
                * 获取Action注释规则
                * */
                if (method.isAnnotationPresent(Action.class)) {
                    Action action = method.getAnnotation(Action.class);
                    String mapping = action.path();
                    /*
                    * todo
                    * mapping 验证暂时不写
                    * */
                    String[] array = mapping.split(":");
                    String requestMethod = array[0];
                    String requestPath = array[1];
                    Request request = new Request(requestMethod, requestPath);
                    Handler handler = new Handler(cls, method);
                    /*
                    * 初始化Action Map
                    * */
                    ACTION_MAP.put(request, handler);
                }
            }
        }
    }

    public static Handler getHandler (String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }
}
