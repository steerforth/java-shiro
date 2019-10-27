package com.steer.phoenix.web;

import com.alibaba.fastjson.JSON;
import com.steer.phoenix.exception.BizException;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RenderUtil {
    public static void renderJson(HttpServletResponse response, Object jsonObject) {
        try {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(jsonObject));
        } catch (IOException var3) {
            throw new BizException(10000010);
        }
    }
}
