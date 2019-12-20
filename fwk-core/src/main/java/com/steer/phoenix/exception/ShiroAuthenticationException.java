package com.steer.phoenix.exception;

import com.steer.phoenix.resource.ResBundle;

public class ShiroAuthenticationException extends Exception {
    private int code;

    public ShiroAuthenticationException(int code){
        super(ResBundle.getMessage(String.valueOf(code)));
        this.code = code;
    }

    public ShiroAuthenticationException(int code, Throwable cause){
        super(ResBundle.getMessage(String.valueOf(code)),cause);
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
