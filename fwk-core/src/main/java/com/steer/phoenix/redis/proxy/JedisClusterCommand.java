package com.steer.phoenix.redis.proxy;

import com.steer.phoenix.properties.JedisProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.Closeable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author fangwk
 */
public class JedisClusterCommand implements JedisCommand{
    private Logger log = LoggerFactory.getLogger(JedisClusterCommand.class);
    private JedisProxy proxy;

    public JedisClusterCommand(JedisProxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public void hmset(String key, Map<String, String> hash) {
        JedisCluster cluster = (JedisCluster) proxy.getClient();
        cluster.hmset(key,hash);
    }

    @Override
    public List<String> hmget(String key,String... fields) {
        JedisCluster cluster = (JedisCluster) proxy.getClient();
        return cluster.hmget(key,fields);
    }

    @Override
    public void set(String key, String value) {
        JedisCluster cluster = (JedisCluster) proxy.getClient();
        cluster.set(key,value);
    }

    @Override
    public String get(String key) {
        JedisCluster cluster = (JedisCluster) proxy.getClient();
        return cluster.get(key);
    }

    @Override
    public void hset(String key, String field, String value) {
        JedisCluster cluster = (JedisCluster) proxy.getClient();
        cluster.hset(key, field, value);
    }

    @Override
    public void hset(String key, Map<String, String> hash) {
        JedisCluster cluster = (JedisCluster) proxy.getClient();
        cluster.hset(key, hash);
    }

    @Override
    public String hget(String key, String field) {
        JedisCluster cluster = (JedisCluster) proxy.getClient();
        return cluster.hget(key, field);
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        JedisCluster cluster = (JedisCluster) proxy.getClient();
        return cluster.hgetAll(key);
    }

    @Override
    public Long hdel(String key, String... fields) {
        JedisCluster cluster = (JedisCluster) proxy.getClient();
        return cluster.hdel(key, fields);
    }

    @Override
    public Closeable connect() {
        JedisProperty property = proxy.getJedisProperty();
        Set<String> servers = property.getClusterNodes();
        Set<HostAndPort> nodes = new HashSet<>(servers.size());
        for (String ipPort:servers){
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(),Integer.valueOf(ipPortPair[1].trim())));
            log.info("开启redis集群模式,[{}:{}]",ipPortPair[0].trim(),ipPortPair[1].trim());
        }
        return new JedisCluster(nodes,property.getConnectionTimeout(),property.getSoTimeout(),property.getMaxAttempts(),proxy.getPoolConfig());
    }

}
