package com.steer.phoenix.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Program: gabriel
 * @Author: Steerforth
 * @Description: kafka属性
 * @Date: 2018-12-07 10:28
 */
@Component
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {
    private Producer producer;
    private Consumer consumer;
    private String bootstrapServer;
    private String group;
    private int timeout;
    private int pollTime;

    protected static class Producer{
        private int retries;
        private String acks;
        private int batchSize;
        private long bufferMemory;
        private int lingerMs;
        private int maxBlockMs;

        public int getRetries() {
            return retries;
        }

        public void setRetries(int retries) {
            this.retries = retries;
        }

        public String getAcks() {
            return acks;
        }

        public void setAcks(String acks) {
            this.acks = acks;
        }

        public int getBatchSize() {
            return batchSize;
        }

        public void setBatchSize(int batchSize) {
            this.batchSize = batchSize;
        }

        public long getBufferMemory() {
            return bufferMemory;
        }

        public void setBufferMemory(long bufferMemory) {
            this.bufferMemory = bufferMemory;
        }

        public int getLingerMs() {
            return lingerMs;
        }

        public void setLingerMs(int lingerMs) {
            this.lingerMs = lingerMs;
        }

        public int getMaxBlockMs() {
            return maxBlockMs;
        }

        public void setMaxBlockMs(int maxBlockMs) {
            this.maxBlockMs = maxBlockMs;
        }
    }

    protected static class Consumer{
        private boolean enableAutoCommit;
        private int autoCommitInterval;
        private String autoOffsetReset;
        private int maxPollIntervalMs;
        private int maxPollRecords;

        public boolean isEnableAutoCommit() {
            return enableAutoCommit;
        }

        public void setEnableAutoCommit(boolean enableAutoCommit) {
            this.enableAutoCommit = enableAutoCommit;
        }

        public int getAutoCommitInterval() {
            return autoCommitInterval;
        }

        public void setAutoCommitInterval(int autoCommitInterval) {
            this.autoCommitInterval = autoCommitInterval;
        }

        public String getAutoOffsetReset() {
            return autoOffsetReset;
        }

        public void setAutoOffsetReset(String autoOffsetReset) {
            this.autoOffsetReset = autoOffsetReset;
        }

        public int getMaxPollIntervalMs() {
            return maxPollIntervalMs;
        }

        public void setMaxPollIntervalMs(int maxPollIntervalMs) {
            this.maxPollIntervalMs = maxPollIntervalMs;
        }

        public int getMaxPollRecords() {
            return maxPollRecords;
        }

        public void setMaxPollRecords(int maxPollRecords) {
            this.maxPollRecords = maxPollRecords;
        }
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public String getBootstrapServer() {
        return bootstrapServer;
    }

    public void setBootstrapServer(String bootstrapServer) {
        this.bootstrapServer = bootstrapServer;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getPollTime() {
        return pollTime;
    }

    public void setPollTime(int pollTime) {
        this.pollTime = pollTime;
    }

}
