package com.steer.phoenix.modular.system.service.impl;

import com.steer.phoenix.modular.system.service.JedisService;
import com.steer.phoenix.redis.proxy.JedisProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JedisServiceImpl implements JedisService {
    @Autowired
    private JedisProxy jedisProxy;
}
