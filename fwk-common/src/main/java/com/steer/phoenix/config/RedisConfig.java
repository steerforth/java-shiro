//package com.steer.phoenix.config;
//
//import com.steer.phoenix.properties.JedisProperty;
//import com.steer.phoenix.redis.proxy.JedisProxy;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import redis.clients.jedis.JedisPoolConfig;
//
///**
// * @Program: gabriel
// * @Author: Steerforth
// * @Description: redis配置
// * @Date: 2018-10-12 12:23
// */
//@Configuration
//public class RedisConfig {
//    @Autowired
//    private JedisProperty property;
//
//    @Bean
//    public JedisPoolConfig jedisPoolConfig() {
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxTotal(property.getPool().getMaxTotal());
//        config.setMaxIdle(property.getPool().getMaxIdle());
//        config.setMaxWaitMillis(property.getPool().getMaxWait());
//        config.setTestOnBorrow(property.getPool().isTestOnBorrow());
//        return config;
//    }
//
//    @Bean
//    public JedisProxy jedisProxy(JedisPoolConfig poolConfig) {
//        JedisProxy proxy = new JedisProxy();
//        proxy.setJedisProperty(property);
//        proxy.setPoolConfig(poolConfig);
//        proxy.connect();
//        return proxy;
//    }
//
//}
