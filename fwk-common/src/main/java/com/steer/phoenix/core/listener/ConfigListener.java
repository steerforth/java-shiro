package com.steer.phoenix.core.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.Map;


@Deprecated
public class ConfigListener implements ServletContextListener {
    private static final String CONTEXT_PATH = "contextPath";
    private static final String REAL_PATH = "realPath";
    private static Map<String, String> conf = new HashMap<>();

    public static Map<String, String> getConf() {
        return conf;
    }

    private static String getContextPath(){
        return conf.get(CONTEXT_PATH);
    }

    private static String getRealPath(){
        return conf.get(REAL_PATH);
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        conf.clear();
    }

    @Override
    public void contextInitialized(ServletContextEvent evt) {
        ServletContext sc = evt.getServletContext();
        //项目发布,当前运行环境的绝对路径
        conf.put(REAL_PATH, sc.getRealPath("/").replaceFirst("/", ""));
        //servletContextPath,默认""
        conf.put(CONTEXT_PATH, sc.getContextPath());
    }
}
