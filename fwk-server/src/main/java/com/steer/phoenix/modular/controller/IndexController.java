package com.steer.phoenix.modular.controller;

import com.steer.phoenix.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/open/")
public class IndexController extends BaseController {
    @GetMapping("")
    public String index(){
        return "hello";
    }

    @GetMapping("/test")
    public String hello(){
        return "test"+SUFFIX;
    }


}
