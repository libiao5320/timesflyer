package com.lee.system.http;

import java.io.Serializable;
import java.util.List;

/**
 * Created by libia on 2016/1/30.
 */
public class HttpPageView implements Serializable {


        private String title;
        private String url ;
        private String pageStr ;
        private List  anchors;
        private List  imgs ;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPageStr() {
        return pageStr;
    }

    public void setPageStr(String pageStr) {
        this.pageStr = pageStr;
    }

    public List getAnchors() {
        return anchors;
    }

    public void setAnchors(List anchors) {
        this.anchors = anchors;
    }

    public List getImgs() {
        return imgs;
    }

    public void setImgs(List imgs) {
        this.imgs = imgs;
    }
}
