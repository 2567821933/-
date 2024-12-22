package com.zhang.usercenter;


import com.zhang.usercenter.model.domain.User;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RList;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

@SpringBootTest
public class RedisTest {


    @Resource
    RedisTemplate<String,String> redisTemplate;


    @Resource
    StringRedisTemplate redisTemplate2;

    @Resource
    Redisson redisson;

    @Test
    public void RedisTestOne() {
        // 获取操作字符串的对象
        ValueOperations valueOperations = redisTemplate.opsForValue();

        valueOperations.set("zhang1", "yuhao");
        valueOperations.set("zhang2", "yuhao");
        valueOperations.set("zhang3", "yuhao");
        valueOperations.set("zhang4", "yuhao");
        valueOperations.set("zhang5", "yuhao");
        valueOperations.set("zhang6", "yuhao");

        Object zhang1 = valueOperations.get("zhang1");
        System.out.println("yuhao".equals(zhang1));
    }

    @Test
    public void RedissonTest() {

        RList<Object> list = redisson.getList("user:test:1");
        User user = new User();
        user.setUsername("zhang");
        user.setUserAccount("11111");
        user.setAvatarUrl("11111");
        user.setGender(0);
        user.setUserPassword("11111");
        user.setPhone("11111");
        user.setEmail("@qq.com");
        user.setUserStatus(0);
        user.setUserRole(0);
        user.setPlanetCode("1234");
        user.setTags("");
        list.add(user);

        Object o = list.get(0);
        System.out.println(o.toString());

    }
}
