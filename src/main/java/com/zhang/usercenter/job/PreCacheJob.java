package com.zhang.usercenter.job;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhang.usercenter.model.domain.User;
import com.zhang.usercenter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Component
@Slf4j
public class PreCacheJob {

    @Resource
    UserService userService;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Resource
    private Redisson redisson;


    // 重点用户加载
    private List<Long> mainUserId = Arrays.asList(1L); // 重点用户id


    // 预热服务，保证预加载。
    @Scheduled(cron = "0 9 12 * * ?")
    public void onePage() {
        log.info("执行了一次定时任务");

        // 锁获取标志
        boolean success = true;
        // 这里是创建锁的名字，不能用变量，保证名字一样
        RLock lock = redisson.getLock("Time_Lock");

        try{
            success = lock.tryLock(15, -1, TimeUnit.SECONDS);
            for(long l : mainUserId) {
                String format = String.format("zhang:user:recommend:%s:page_1", l);
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                Page<User> page = userService.page(new Page<User>(1, 5), queryWrapper);
                ValueOperations<String, Object> stringObjectValueOperations = redisTemplate.opsForValue();

                try{
                    stringObjectValueOperations.set(format,page,30000, TimeUnit.MILLISECONDS);
                } catch(Exception e) {
                    log.error("redis key set error", e);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // 释放锁
            if(success && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }

    }
}
