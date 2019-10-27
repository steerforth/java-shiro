package com.steer.phoenix.modular.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.steer.phoenix.modular.system.entity.User;
import com.steer.phoenix.modular.system.mapper.UserMapper;
import com.steer.phoenix.modular.system.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Override
    public User getByAccount(String account) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("account",account);
        wrapper.ne("status",9);
        return super.getOne(wrapper);
    }
}
