package org.bean;

/**
 * Created by Smith on 2017/4/19.
 */
public class Request {
    /*
    * 请求方法
    * */
    private String requestMethod;

    /*
    * 请求路径
    * */
    private String requestPath;

    public Request(String requestMethod, String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    @Override
    public int hashCode() {
        return requestPath.hashCode()+requestMethod.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Request request = (Request) obj;
        if (requestMethod.equals(request.getRequestMethod()) && requestPath.equals(request.getRequestPath()))
            return true;
        else
            return false;
    }
}
