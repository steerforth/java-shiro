package com.steer.phoenix.modular.controller;

import com.steer.phoenix.controller.BaseController;
import com.steer.phoenix.core.shiro.ShiroKit;
import com.steer.phoenix.core.shiro.ShiroUser;
import com.steer.phoenix.modular.system.service.UserService;
import com.steer.phoenix.node.MenuNode;
import com.steer.phoenix.web.CookieUtil;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
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
            return "/login";
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
            return "/login";
        }

        List<MenuNode> menus = userService.getUserMenuNodes(roleList);
        model.addAttribute("menus", menus);
        return "/index";
    }

    /**
     * 点击登录执行的动作
     *
     * @Date 2018/12/23 5:42 PM
     */
    @PostMapping(value = "/login")
    public String loginVali() {

        String username = super.getPara("username").trim();
        String password = super.getPara("password").trim();
        String remember = super.getPara("remember");

        Subject currentUser = ShiroKit.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());

        //如果开启了记住我功能
        if ("on".equals(remember)) {
            token.setRememberMe(true);
        } else {
            token.setRememberMe(false);
        }

        //执行shiro登录操作
        currentUser.login(token);

        //登录成功，记录登录日志
        ShiroUser shiroUser = ShiroKit.getUserNotNull();
//        LogManager.me().executeLog(LogTaskFactory.loginLog(shiroUser.getId(), getIp()));

        //FIXME
        ShiroKit.getSession().setAttribute("sessionFlag", true);

        return REDIRECT + "/";
    }

    /**
     * 退出登录
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:42 PM
     */
    @GetMapping(value = "/logout")
    public String logOut() {
//        LogManager.me().executeLog(LogTaskFactory.exitLog(ShiroKit.getUserNotNull().getId(), getIp()));
        ShiroKit.getSubject().logout();
        CookieUtil.deleteAllCookie();
        return REDIRECT + "/login";
    }
}
