package org.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bean.Servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Smith on 2017/5/13.
 */
public class ServletHelper {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final ThreadLocal<Servlet> SERVLET_THREAD_LOCAL = new ThreadLocal<>();

    public static void init(HttpServletRequest request, HttpServletResponse response) {
        Servlet servlet = new Servlet(request, response);
        SERVLET_THREAD_LOCAL.set(servlet);
    }

    public static Servlet get() {
        return SERVLET_THREAD_LOCAL.get();
    }

}
