package com.steer.phoenix.redis.proxy;


import com.steer.phoenix.properties.JedisProperty;
import com.steer.phoenix.redis.aop.ReConnect;
import com.steer.phoenix.redis.constants.JedisClusterStatus;
import com.steer.phoenix.redis.factory.JedisCommandStrategyFactory;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisClusterException;
import redis.clients.jedis.exceptions.JedisClusterMaxAttemptsException;

import java.io.Closeable;
import java.util.List;
import java.util.Map;

/**
 * 声明为单例使用
 * @author fangwk
 */
public class JedisProxy implements JedisCommand{
    private JedisClusterStatus clusterStatus;
    private Closeable client;
    private JedisProperty jedisProperty;
    private JedisPoolConfig poolConfig;
    private JedisCommand command;
    private final Object mutex = new Object();

    private JedisCommand getCommand(){
        if (this.command == null){
            synchronized (mutex){
                if (this.command == null){
                    this.command = JedisCommandStrategyFactory.getCommand(this);
                }
            }
        }
        return this.command;
    }

    @Override
    @ReConnect(value = {JedisClusterException.class, JedisClusterMaxAttemptsException.class})
    public void hmset(String key, Map<String, String> hash) {
        this.getCommand().hmset(key, hash);
    }

    @Override
    @ReConnect(value = {JedisClusterException.class,JedisClusterMaxAttemptsException.class})
    public List<String> hmget(String key, String... fields) {
        return this.getCommand().hmget(key, fields);
    }

    @Override
    @ReConnect(value = {JedisClusterException.class,JedisClusterMaxAttemptsException.class})
    public void set(String key, String value) {
        this.getCommand().set(key, value);
    }

    @Override
    @ReConnect(value = {JedisClusterException.class,JedisClusterMaxAttemptsException.class})
    public String get(String key) {
        return this.getCommand().get(key);
    }

    @Override
    @ReConnect(value = {JedisClusterException.class,JedisClusterMaxAttemptsException.class})
    public void hset(String key, String field, String value) {
        this.getCommand().hset(key, field, value);
    }

    @Override
    @ReConnect(value = {JedisClusterException.class,JedisClusterMaxAttemptsException.class})
    public void hset(String key, Map<String, String> hash) {
        this.getCommand().hset(key, hash);
    }

    @Override
    @ReConnect(value = {JedisClusterException.class,JedisClusterMaxAttemptsException.class})
    public String hget(String key, String field) {
        return this.getCommand().hget(key, field);
    }

    @Override
    @ReConnect(value = {JedisClusterException.class,JedisClusterMaxAttemptsException.class})
    public Map<String, String> hgetAll(String key) {
        return this.getCommand().hgetAll(key);
    }

    @Override
    @ReConnect(value = {JedisClusterException.class,JedisClusterMaxAttemptsException.class})
    public Long hdel(String key, String... field) {
        return this.getCommand().hdel(key);
    }

    @Override
    public Closeable connect() {
        Closeable closeable = this.getCommand().connect();
        this.setClient(closeable);
        return closeable;
    }

    public JedisProperty getJedisProperty() {
        return jedisProperty;
    }

    public void setJedisProperty(JedisProperty jedisProperty) {
        this.jedisProperty = jedisProperty;
    }

    public JedisPoolConfig getPoolConfig() {
        return poolConfig;
    }

    public void setPoolConfig(JedisPoolConfig poolConfig) {
        this.poolConfig = poolConfig;
    }

    public JedisClusterStatus getClusterStatus() {
        return clusterStatus;
    }

    public void setClusterStatus(JedisClusterStatus clusterStatus) {
        this.clusterStatus = clusterStatus;
    }

    public Closeable getClient() {
        return client;
    }

    public void setClient(Closeable client) {
        this.client = client;
    }
}