package org;

import org.bean.Data;
import org.bean.Handler;
import org.bean.Param;
import org.bean.View;
import org.config.ConfigHelper;
import org.helper.BeanHelper;
import org.helper.ControllerHelper;
import org.helper.ServletHelper;
import org.util.JsonUtil;
import org.util.ReflectionUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Smith on 2017/4/20.
 */
@WebServlet(name = "DispatcherServlet", urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 初始化Helper类
        HelperLoader.init();

        ServletContext servletContext = config.getServletContext();
        // 注册jsp的Servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath()+"/*");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletHelper.init(req, resp);

        String requestMethod = req.getMethod().toLowerCase();
        String requestPath = req.getPathInfo();

        // 获取Action处理器
        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
        if (handler != null) {
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);
            // 创建请求参数对象
            Map<String, Object> paramMap = new HashMap<>();
            Enumeration<String> paramNames = req.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                String paramValue = req.getParameter(paramName);
                paramMap.put(paramName, paramValue);
            }

            Param param = new Param(paramMap);
            System.out.println(paramMap.isEmpty());
            // 调用Action方法
            Method actionMethod = handler.getActionMethod();
            Object result = null;
            if (paramMap.isEmpty()) {
                result = ReflectionUtil.invokeMethod(controllerBean, actionMethod);
            } else {
                result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
            }
            /*
            * 返回数据
            * */
            if (result instanceof View) {
                // jsp
                View view = (View) result;
                String path = view.getPath();
                if (path.length()>3) {
                    Map<String, Object> model = view.getModel();
                    req.setAttribute("model", model);
                    req.getRequestDispatcher(ConfigHelper.getAppJspPath()+path).forward(req,resp);
                }
            } else if (result instanceof Data) {
                // json
                Data data = (Data) result;
                Object model = data.getModel();
                if (model != null) {
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("utf-8");
                    String json = JsonUtil.toJson(model);
                    resp.getWriter().write(json);
                }
            }
        } else {
            System.out.println("Can't find:"+requestMethod+requestPath);
        }
    }

}
