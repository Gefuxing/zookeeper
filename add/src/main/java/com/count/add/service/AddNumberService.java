package com.count.add.service;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author gefuxing
 * @create 2021/8/15 13:52
 */
@RestController
public class AddNumberService {
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    InterProcessMutex mutex;

    @GetMapping("redis/get/{count}")
    public void testRedis(@PathVariable Integer count) throws Exception {
        for (int i = 0; i <count ; i++) {
            try {
                mutex.acquire();
                String number = (String)redisTemplate.opsForValue().get("number");
                Integer num =Integer.valueOf(number)+1;
                redisTemplate.opsForValue().set("number",num.toString());
                System.out.println("成功》》》》》》》》》》》》》》》》》"+number);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
               mutex.release();
            }
        }
    }

}
