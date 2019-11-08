package com.steer.phoenix.redis.proxy;



import com.steer.phoenix.properties.JedisProperty;
import com.steer.phoenix.redis.aop.ReConnect;
import com.steer.phoenix.redis.constants.JedisClusterStatus;
import com.steer.phoenix.redis.factory.JedisCommandFactory;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisClusterException;
import redis.clients.jedis.exceptions.JedisClusterMaxAttemptsException;

import java.io.Closeable;
import java.util.List;
import java.util.Map;

/**
 * @author fangwk
 */
public class JedisProxy implements JedisCommand{
    private JedisClusterStatus clusterStatus;
    private Closeable client;
    private JedisProperty jedisProperty;
    private JedisPoolConfig poolConfig;

    @Override
    @ReConnect(value = {JedisClusterException.class, JedisClusterMaxAttemptsException.class})
    public void hmset(String key, Map<String, String> hash) {
        JedisCommand command = JedisCommandFactory.getCommand(this);
        command.hmset(key, hash);
    }

    @Override
    @ReConnect(value = {JedisClusterException.class,JedisClusterMaxAttemptsException.class})
    public List<String> hmget(String key, String... fields) {
        JedisCommand command = JedisCommandFactory.getCommand(this);
        return command.hmget(key, fields);
    }

    @Override
    @ReConnect(value = {JedisClusterException.class,JedisClusterMaxAttemptsException.class})
    public void set(String key, String value) {
        JedisCommand command = JedisCommandFactory.getCommand(this);
        command.set(key, value);
    }

    @Override
    @ReConnect(value = {JedisClusterException.class,JedisClusterMaxAttemptsException.class})
    public String get(String key) {
        JedisCommand command = JedisCommandFactory.getCommand(this);
        return command.get(key);
    }

    @Override
    @ReConnect(value = {JedisClusterException.class,JedisClusterMaxAttemptsException.class})
    public void hset(String key, String field, String value) {
        JedisCommand command = JedisCommandFactory.getCommand(this);
        command.hset(key, field, value);
    }

    @Override
    @ReConnect(value = {JedisClusterException.class,JedisClusterMaxAttemptsException.class})
    public void hset(String key, Map<String, String> hash) {
        JedisCommand command = JedisCommandFactory.getCommand(this);
        command.hset(key, hash);
    }

    @Override
    @ReConnect(value = {JedisClusterException.class,JedisClusterMaxAttemptsException.class})
    public String hget(String key, String field) {
        JedisCommand command = JedisCommandFactory.getCommand(this);
        return command.hget(key, field);
    }

    @Override
    @ReConnect(value = {JedisClusterException.class,JedisClusterMaxAttemptsException.class})
    public Map<String, String> hgetAll(String key) {
        JedisCommand command = JedisCommandFactory.getCommand(this);
        return command.hgetAll(key);
    }

    @Override
    @ReConnect(value = {JedisClusterException.class,JedisClusterMaxAttemptsException.class})
    public Long hdel(String key, String... field) {
        JedisCommand command = JedisCommandFactory.getCommand(this);
        return command.hdel(key);
    }

    @Override
    public Closeable connect() {
        JedisCommand command = JedisCommandFactory.getCommand(this);
        Closeable closeable = command.connect();
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