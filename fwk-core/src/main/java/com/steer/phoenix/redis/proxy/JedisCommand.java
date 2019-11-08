package com.steer.phoenix.redis.proxy;

import java.io.Closeable;
import java.util.List;
import java.util.Map;

/**
 * @author fangwk
 */
public interface JedisCommand {
    void hmset(String key, Map<String, String> hash);

    List<String> hmget(String key, String... fields);

    void set(String key, String value);

    String get(String key);

    void hset(String key, String field, String value);

    void hset(String key, Map<String, String> hash);

    String hget(String key, String field);

    Map<String,String> hgetAll(String key);

    Long hdel(String key, String... fields);

    Closeable connect();
}
