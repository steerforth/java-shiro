package com.steer.phoenix.enums;

public enum ResultEnum {
    ok("SUCCESS", 200),
    error("ERROR", -1),
    error_500("内部服务器错误", 500),
    error_400("错误请求", 400);

    private String name;
    private int code;

    ResultEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
