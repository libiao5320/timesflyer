package com.lee.system.http;

import java.io.Serializable;

/**
 * Created by libia on 2016/1/30.
 */
public class HttpRequestContnet implements Serializable {

    public HttpRequestContnet(String url){
        this.url = url;
    };

    private String url ;
    private HttpRequestHead httpRequestHead;
    private Object params  ;

    public HttpRequestHead getHttpRequestHead() {
        return httpRequestHead;
    }

    public void setHttpRequestHead(HttpRequestHead httpRequestHead) {
        this.httpRequestHead = httpRequestHead;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
