package com.steer.phoenix.modular.controller;

import com.steer.phoenix.controller.BaseController;
import com.steer.phoenix.core.shiro.ShiroKit;
import com.steer.phoenix.core.shiro.ShiroUser;
import com.steer.phoenix.modular.system.service.UserService;
import com.steer.phoenix.node.MenuNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;
    /**
     * 跳转到登录页面
     *
     */
    @GetMapping(value = "/login")
    public String login() {
        if (ShiroKit.isAuthenticated() || ShiroKit.getUser() != null) {
            return REDIRECT + "/";
        } else {
            return "/login.html";
        }
    }

    /**
     * 跳转到主页
     */
    @GetMapping(value = "/")
    public String index(Model model) {

        //获取当前用户角色列表
        ShiroUser user = ShiroKit.getUserNotNull();
        List<Long> roleList = user.getRoleList();

        if (roleList == null || roleList.size() == 0) {
            ShiroKit.getSubject().logout();
            model.addAttribute("tips", "该用户没有角色，无法登陆");
            return "/login.html";
        }

        List<MenuNode> menus = userService.getUserMenuNodes(roleList);
        model.addAttribute("menus", menus);
        return "/index.html";
    }
}
