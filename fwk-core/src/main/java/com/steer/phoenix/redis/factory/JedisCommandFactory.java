package com.steer.phoenix.redis.factory;


import com.steer.phoenix.redis.proxy.*;

/**
 * @author fangwk
 */
public class JedisCommandFactory {
    public static JedisCommand getCommand(JedisProxy proxy){
        switch (proxy.getJedisProperty().getMode()){
            case single:
                return new JedisSingleCommand(proxy);
            case pool:
                return new JedisPoolCommand(proxy) ;
            case cluster:
                return new JedisClusterCommand(proxy);
            default:
                throw new IllegalArgumentException("不支持模式");
        }
    }
}
