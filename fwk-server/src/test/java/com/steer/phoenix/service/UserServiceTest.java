package com.steer.phoenix.service;

import com.steer.phoenix.BaseTest;
import com.steer.phoenix.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest extends BaseTest {
    @Autowired
    UserService service;

    @Test
    public void testInsert(){
        User user = new User();
        user.setSex("");
        user.setAccount("11");
        user.setAvatar("333");
        user.setName("hehe");
        service.save(user);
    }

    @Test
    public void testSelect(){
        User user = service.getById(1L);
        Assert.assertEquals("22", user.getName());
    }
}
