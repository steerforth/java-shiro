package com.steer.phoenix.redis.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.Closeable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author fangwk
 */
public class JedisSingleCommand implements JedisCommand {
    private Logger log = LoggerFactory.getLogger(JedisSingleCommand.class);
    private JedisProxy proxy;

    public JedisSingleCommand(JedisProxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public void hmset(String key, Map<String, String> hash) {
        Jedis jedis = (Jedis) proxy.getClient();
        jedis.hmset(key,hash);
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        Jedis jedis = (Jedis) proxy.getClient();
        return jedis.hmget(key, fields);
    }

    @Override
    public void set(String key, String value) {
        Jedis jedis = (Jedis) proxy.getClient();
        jedis.set(key, value);
    }

    @Override
    public String get(String key) {
        Jedis jedis = (Jedis) proxy.getClient();
        return jedis.get(key);
    }

    @Override
    public void hset(String key, String field, String value) {
        Jedis jedis = (Jedis) proxy.getClient();
        jedis.hset(key, field, value);
    }

    @Override
    public void hset(String key, Map<String, String> hash) {
        Jedis jedis = (Jedis) proxy.getClient();
        jedis.hset(key, hash);
    }

    @Override
    public String hget(String key, String field) {
        Jedis jedis = (Jedis) proxy.getClient();
        return jedis.hget(key , field);
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        Jedis jedis = (Jedis) proxy.getClient();
        return jedis.hgetAll(key);
    }

    @Override
    public Long hdel(String key, String... fields) {
        Jedis jedis = (Jedis) proxy.getClient();
        return jedis.hdel(key, fields);
    }

    @Override
    public Closeable connect() {
        Set<String> servers = proxy.getJedisProperty().getClusterNodes();
        if (servers.size() == 0){
            throw new IllegalArgumentException("jedis未配置host和port");
        }
        String[] ipAndPort = servers.iterator().next().split(":");
        log.info("开启redis单机模式[{}:{}]",ipAndPort[0],ipAndPort[1]);
        Jedis jedis = new Jedis(ipAndPort[0].trim(),Integer.valueOf(ipAndPort[1].trim()));
        return jedis;
    }
}
