package com.steer.phoenix.core.redis;

import com.steer.phoenix.BaseTest;
import com.steer.phoenix.check.Crc16Util;
import com.steer.phoenix.redis.proxy.JedisProxy;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class RedisTest extends BaseTest {
    @Autowired
    private JedisProxy proxy;

    @Test
    public void test(){
        Map<String,String> hash = new HashMap<>();
        hash.put("1","2");
        proxy.hmset("1x",hash);
        Assert.assertEquals(1,proxy.hmget("1x","1").size());
        Assert.assertEquals("2",proxy.hmget("1x","1").get(0));

    }

    /**
     * slot范围:0-16383
     */
    @Test
    public void testSlot(){
        String key = "1x";
        int slot =  Crc16Util.CRC16_ccitt(key.getBytes()) & 0x3FFF;
        log.info("[{}]在redis cluster中被分配在slot:[{}]",key,slot);
    }
}
