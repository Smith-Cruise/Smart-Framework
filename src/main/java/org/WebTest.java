package org;

import org.annotation.Action;
import org.annotation.Controller;
import org.bean.Data;
import org.bean.Param;
import org.bean.Servlet;
import org.bean.View;
import org.helper.ServletHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Smith on 2017/4/20.
 */
@Controller
public class WebTest {

    @Action(path = "get:/hello")
    public Data echo(Param param) {
        Map<String, Object> map = param.getMap();
        return new Data("hello world"+map.get("id"));
    }

    @Action(path = "get:/hello_jsp")
    public View echoJsp() {
        Servlet servlet = ServletHelper.get();
        HttpServletRequest request = servlet.getRequest();
        System.out.println(request.getProtocol());
        return new View("/hello.jsp");
    }

    @Action(path = "get:/hello_index")
    public View echoIndex(Param param) {
        return new View("/index.jsp").addModel("name", "smith");
    }
}
