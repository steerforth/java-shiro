package com.steer.phoenix.exception;

import com.steer.phoenix.util.ResBundleUtil;

public class BizException extends RuntimeException {

    private static final int RUNTIME_EXCEPTION_CODE = 10000000;

    private int code;

    public BizException(){
        super(ResBundleUtil.getMessage(String.valueOf(RUNTIME_EXCEPTION_CODE)));
        this.code = RUNTIME_EXCEPTION_CODE;
    }

    public BizException(int code){
        super(ResBundleUtil.getMessage(String.valueOf(code)));
        this.code = code;
    }

    public BizException(int code,Throwable cause){
        super(ResBundleUtil.getMessage(String.valueOf(code)),cause);
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
