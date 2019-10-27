package com.steer.phoenix.core.shiro.service.impl;

import cn.hutool.core.convert.Convert;
import com.steer.phoenix.core.shiro.ShiroKit;
import com.steer.phoenix.core.shiro.ShiroUser;
import com.steer.phoenix.core.shiro.service.UserAuthService;
import com.steer.phoenix.modular.system.entity.User;
import com.steer.phoenix.modular.system.mapper.MenuMapper;
import com.steer.phoenix.modular.system.service.RoleService;
import com.steer.phoenix.modular.system.service.UserService;
import com.steer.phoenix.spring.SpringContextHolder;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@DependsOn("springContextHolder")
@Transactional(readOnly = true)
public class UserAuthServiceImpl implements UserAuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Resource
    private MenuMapper menuMapper;

    public static UserAuthService me() {
        return SpringContextHolder.getBean(UserAuthService.class);
    }

    @Override
    public User user(String account) {

        User user = userService.getByAccount(account);

        // 账号不存在
        if (null == user) {
            throw new CredentialsException();
        }
        // 账号被冻结
//        if (!user.getStatus().equals(ManagerStatus.OK.getCode())) {
//            throw new LockedAccountException();
//        }
        return user;
    }

    @Override
    public ShiroUser shiroUser(User user) {

        ShiroUser shiroUser = ShiroKit.createShiroUser(user);

        //用户角色数组
        Long[] roleArray = Convert.toLongArray(user.getRoleId());

        //获取用户角色列表
        List<Long> roleList = new ArrayList<>();
        List<String> roleNameList = new ArrayList<>();
        for (Long roleId : roleArray) {
            roleList.add(roleId);
            roleNameList.add(this.findRoleNameByRoleId(roleId));
        }
        shiroUser.setRoleList(roleList);
        shiroUser.setRoleNames(roleNameList);

        return shiroUser;
    }

    @Override
    public List<String> findPermissionsByRoleId(Long roleId) {
//        return menuMapper.getResUrlsByRoleId(roleId);
        return null;
    }

    @Override
    public String findRoleNameByRoleId(Long roleId) {
        return roleService.getSingleRoleName(roleId);
    }

    @Override
    public SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realmName) {
        String credentials = user.getPassword();
        // 密码加盐处理
        String source = user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(source);
        return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
    }
}
