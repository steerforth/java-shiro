package com.steer.phoenix.modular.system.enums;

public enum Sex {
    Unknown("未知",0),
    Male("男性",1),
    Female("女性",2);

    private String code;
    private Integer idx;

    Sex(String code, Integer idx) {
        this.code = code;
        this.idx = idx;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }
}
