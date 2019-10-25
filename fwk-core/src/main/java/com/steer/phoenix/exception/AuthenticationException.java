package com.steer.phoenix.exception;

import com.steer.phoenix.resource.ResBundle;

public class AuthenticationException extends Exception {
    private int code;

    public AuthenticationException(int code){
        super(ResBundle.getMessage(String.valueOf(code)));
        this.code = code;
    }

    public AuthenticationException(int code,Throwable cause){
        super(ResBundle.getMessage(String.valueOf(code)),cause);
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
