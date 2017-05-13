package org.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Smith on 2017/5/13.
 */
public class Servlet {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public Servlet(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
}
