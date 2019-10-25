package com.steer.phoenix.resource;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.*;
@Slf4j
public class ResBundle {
    private static final String DEFAULT_NAME = "exception";

    private static Map<String, ResourceBundle> resMap = new HashMap<>();

    private static ResourceBundle getBundle(String resName){
        ResourceBundle bundle = resMap.get(resName);
        if (bundle == null) {
            try {
                bundle = ResourceBundle.getBundle(resName, Locale.getDefault());
                resMap.put(resName, bundle);
            } catch (MissingResourceException e) {
                log.error("can not find the resource property file :{}",e.getClassName());
            }
        }
        return bundle;
    }

    public static String getValue(String resName,String key){
        String value = null;
        try {
            value = new String(getBundle(resName).getString(key).getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("convert the value:{}, encoding exception from {}file",getBundle(resName).getString(key),resName);
        }
        return value==null ? "":value;
    }

    public static String getMessage(String key){
        return getValue(DEFAULT_NAME,key);
    }

}
