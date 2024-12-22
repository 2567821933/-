package com.zhang.usercenter;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.StringUtil;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class UserCenterApplicationTests {


    @Test
    void designTest() throws NoSuchAlgorithmException {
        String s = DigestUtils.md5DigestAsHex(("aaa").getBytes(StandardCharsets.UTF_8));
        System.out.println(s);
    }
    @Test
    void contextLoads() {
    }

}
