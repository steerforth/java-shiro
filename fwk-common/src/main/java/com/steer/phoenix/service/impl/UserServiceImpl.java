package com.steer.phoenix.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.steer.phoenix.entity.User;
import com.steer.phoenix.mapper.UserMapper;
import com.steer.phoenix.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
}
