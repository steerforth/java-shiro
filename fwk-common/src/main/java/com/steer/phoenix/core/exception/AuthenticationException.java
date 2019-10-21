package com.steer.phoenix.core.exception;

import com.steer.phoenix.util.ResBundleUtil;

public class AuthenticationException extends Exception {
    private int code;

    public AuthenticationException(int code){
        super(ResBundleUtil.getMessage(String.valueOf(code)));
        this.code = code;
    }

    public AuthenticationException(int code,Throwable cause){
        super(ResBundleUtil.getMessage(String.valueOf(code)),cause);
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
