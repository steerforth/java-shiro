package com.steer.phoenix.exception;

public abstract class Assert {
    public static void notNull(Object object, int code) {
        if (object == null) {
            throw new BizException(code);
        }
    }

    public static void notNull(Object object) {
        if (object == null) {
            throw new BizException(20000000);
        }
    }
}
