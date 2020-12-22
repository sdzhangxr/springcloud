package com.zhangxr.springcloud.test;

import com.zhangxr.springcloud.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @className RedisTest
 * @Description Redis测试类
 * @Author sdzha
 * @Date 2020/12/22 17:40
 * @Version 1.0
 */
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void getString(){
        Object name = redisUtil.get("name");
        System.out.println(name.toString());
//        redisUtil.del("age","stock");
//        redisUtil.set("name","zhangxr");

    }
}
