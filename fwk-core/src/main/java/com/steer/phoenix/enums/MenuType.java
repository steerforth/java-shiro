package com.steer.phoenix.enums;

public enum MenuType {
    Menu("菜单",0),
    Button("按钮",1);
    private String name;
    private Integer idx;

    MenuType(String name, Integer idx) {
        this.name = name;
        this.idx = idx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }
}
