package com.steer.phoenix.core.kafka;

import com.steer.phoenix.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class KafkaTest extends BaseTest {
    @Test
    public void testPartition(){
        int partitions = 3;
        String key = "ff8081816c07e4b5016c081070810009";
        int partitionPos =  toPositive(murmur2(key.getBytes())) % partitions;
        log.info("key:{}分配的分区为：{}",key,partitionPos);
    }

    @Test
    public void testOffset(){
        String groupId = "gabriel-13e5904801ef4398a9dee4fd5a4bcd4f";
        int consumerOffset = Math.abs(groupId.hashCode()) % 50;
        log.info("{}存在__consumer_offsets_{}文件中",groupId,consumerOffset);
    }

    public int murmur2(byte[] data) {
        int length = data.length;
        int seed = -1756908916;
        int h = seed ^ length;
        int length4 = length / 4;

        for(int i = 0; i < length4; ++i) {
            int i4 = i * 4;
            int k = (data[i4 + 0] & 255) + ((data[i4 + 1] & 255) << 8) + ((data[i4 + 2] & 255) << 16) + ((data[i4 + 3] & 255) << 24);
            k *= 1540483477;
            k ^= k >>> 24;
            k *= 1540483477;
            h *= 1540483477;
            h ^= k;
        }

        switch(length % 4) {
            case 3:
                h ^= (data[(length & -4) + 2] & 255) << 16;
            case 2:
                h ^= (data[(length & -4) + 1] & 255) << 8;
            case 1:
                h ^= data[length & -4] & 255;
                h *= 1540483477;
            default:
                h ^= h >>> 13;
                h *= 1540483477;
                h ^= h >>> 15;
                return h;
        }
    }

    public int toPositive(int number) {
        return number & 2147483647;
    }
}
