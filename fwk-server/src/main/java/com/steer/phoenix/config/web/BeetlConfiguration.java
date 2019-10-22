package com.steer.phoenix.config.web;

import org.beetl.ext.spring.BeetlGroupUtilConfiguration;

import java.util.HashMap;
import java.util.Map;

public class BeetlConfiguration extends BeetlGroupUtilConfiguration {
    @Override
    public void initOther() {

        //全局共享变量
        Map<String, Object> shared = new HashMap<>();
        shared.put("systemName", "steer管理系统");
        shared.put("welcomeTip", "欢迎使用steer管理系统");
        groupTemplate.setSharedVars(shared);

        //全局共享方法
//        groupTemplate.registerFunctionPackage("shiro", new ShiroExt());
//        groupTemplate.registerFunctionPackage("tool", new ToolUtil());
//        groupTemplate.registerFunctionPackage("kaptcha", new KaptchaUtil());
    }
}
