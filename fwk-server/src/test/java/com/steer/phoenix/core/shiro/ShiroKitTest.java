package com.steer.phoenix.core.shiro;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
@Slf4j
public class ShiroKitTest {

    @Test
    public void testGeneratePasswordAndSalt(){
        String salt = ShiroKit.getRandomSalt(5);
        String password = ShiroKit.md5("123456",salt);
        log.info("password:{},salt:{}",password,salt);
    }
}
