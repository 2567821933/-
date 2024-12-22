package com.zhang.usercenter;
import java.util.Date;

import com.zhang.usercenter.model.domain.User;
import com.zhang.usercenter.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@SpringBootTest
public class SampleTest {

    @Resource
    UserService userService;


    /**
     * 并发批量插入用户
     */

    @Test
    public void addListUser() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // 数据添加
        List<CompletableFuture<Void>> completableFutures = new ArrayList<>();
        int j = 0;
        for(int i = 0; i < 10; i++) {
            ArrayList<User> users = new ArrayList<>();
            while(true) {
                j++;
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
                users.add(user);
                if(j % 10000 == 0) {
                    break;
                }
            }
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                userService.saveBatch(users, 10000);
            });

            completableFutures.add(future);

        }

        // 保证所有线程都执行完毕。
        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[]{})).join();

        stopWatch.stop();

        System.out.println(stopWatch.getTotalTimeMillis());
    }

}
