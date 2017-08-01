package com.lee.system.http;

import java.io.Serializable;

/**
 * Created by libia on 2016/1/30.
 */
public class HttpRequestHead implements Serializable {

    /**
     * 发送请求 get 请求格式  /index.htm HTTP/1.1
     */

    private String contentType;
    private String host;
    private String accept;
    private String agent;
    private Integer connetction; // 是否保持长链接




    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public Integer getConnetction() {
        return connetction;
    }

    public void setConnetction(Integer connetction) {
        this.connetction = connetction;
    }
}



