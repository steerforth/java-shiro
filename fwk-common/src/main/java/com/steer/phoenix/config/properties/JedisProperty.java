package com.steer.phoenix.config.properties;

import com.steer.phoenix.redis.constants.JedisMode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@ConfigurationProperties(prefix = "jedis")
public class JedisProperty {
    /**
     * 连接超时
     */
    private int connectionTimeout;
    /**
     * 集群节点,单机配一个即可
     */
    private Set<String> clusterNodes;
    /**
     * 集群节点间超时时间
     */
    private int clusterNodeTimeout;

    private JedisMode mode;
    /**
     * 返回值超时时间
     */
    private int soTimeout;
    /**
     * 最大重试次数
     */
    private int maxAttempts;
    private Pool pool;

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public Set<String> getClusterNodes() {
        return clusterNodes;
    }

    public void setClusterNodes(Set<String> clusterNodes) {
        this.clusterNodes = clusterNodes;
    }

    public int getClusterNodeTimeout() {
        return clusterNodeTimeout;
    }

    public void setClusterNodeTimeout(int clusterNodeTimeout) {
        this.clusterNodeTimeout = clusterNodeTimeout;
    }

    public JedisMode getMode() {
        return mode;
    }

    public void setMode(JedisMode mode) {
        this.mode = mode;
    }

    public int getSoTimeout() {
        return soTimeout;
    }

    public void setSoTimeout(int soTimeout) {
        this.soTimeout = soTimeout;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public Pool getPool() {
        return pool;
    }

    public void setPool(Pool pool) {
        this.pool = pool;
    }

    public static class Pool{
        private int maxTotal;
        private int maxIdle;
        private boolean testOnBorrow;
        private int maxWait;

        public int getMaxTotal() {
            return maxTotal;
        }

        public void setMaxTotal(int maxTotal) {
            this.maxTotal = maxTotal;
        }

        public int getMaxIdle() {
            return maxIdle;
        }

        public void setMaxIdle(int maxIdle) {
            this.maxIdle = maxIdle;
        }

        public boolean isTestOnBorrow() {
            return testOnBorrow;
        }

        public void setTestOnBorrow(boolean testOnBorrow) {
            this.testOnBorrow = testOnBorrow;
        }

        public int getMaxWait() {
            return maxWait;
        }

        public void setMaxWait(int maxWait) {
            this.maxWait = maxWait;
        }
    }
}
