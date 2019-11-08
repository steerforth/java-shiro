package com.steer.phoenix.redis.proxy;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.Closeable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author fangwk
 */
public class JedisPoolCommand implements JedisCommand {
    private Logger log = LoggerFactory.getLogger(JedisPoolCommand.class);
    private JedisProxy proxy;

    public JedisPoolCommand(JedisProxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public void hmset(String key, Map<String, String> hash) {
        try (Jedis jedis = ((JedisPool) proxy.getClient()).getResource()){
            jedis.hmset(key,hash);
        }
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        try (Jedis jedis = ((JedisPool) proxy.getClient()).getResource()){
            return jedis.hmget(key,fields);
        }
    }

    @Override
    public void set(String key, String value) {
        try (Jedis jedis = ((JedisPool) proxy.getClient()).getResource()){
            jedis.set(key,value);
        }
    }

    @Override
    public String get(String key) {
        try (Jedis jedis = ((JedisPool) proxy.getClient()).getResource()){
            return jedis.get(key);
        }
    }

    @Override
    public void hset(String key, String field, String value) {
        try (Jedis jedis = ((JedisPool) proxy.getClient()).getResource()){
            jedis.hset(key, field, value);
        }
    }

    @Override
    public void hset(String key, Map<String, String> hash) {
        try (Jedis jedis = ((JedisPool) proxy.getClient()).getResource()){
            jedis.hset(key, hash);
        }
    }

    @Override
    public String hget(String key, String field) {
        try (Jedis jedis = ((JedisPool) proxy.getClient()).getResource()){
            return jedis.hget(key, field);
        }
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        try (Jedis jedis = ((JedisPool) proxy.getClient()).getResource()){
            return jedis.hgetAll(key);
        }
    }

    @Override
    public Long hdel(String key, String... fields) {
        try (Jedis jedis = ((JedisPool) proxy.getClient()).getResource()){
            return jedis.hdel(key, fields);
        }
    }

    @Override
    public Closeable connect() {
        Set<String> servers = proxy.getJedisProperty().getClusterNodes();
        if (servers.size() == 0){
            throw new IllegalArgumentException("jedis未配置host和port");
        }
        String[] ipAndPort = servers.iterator().next().split(":");
        log.info("开启redis线程池模式,[{}:{}]",ipAndPort[0],ipAndPort[1]);
        return new JedisPool(proxy.getPoolConfig(), ipAndPort[0], Integer.valueOf(ipAndPort[1].trim()));
    }
}
