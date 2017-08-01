package com.lee.system.exception;

/**
 * Created by libia on 2016/1/30.
 */
public class MyHttpException extends Exception {
    private String responseCode;
    public MyHttpException (String url ,String msg){
        super("访问:" + url +msg );
    }

    public MyHttpException (String msg){
        super(msg );
    }

}
