package com.redis.util;

import com.redis.crawler.Command;
import com.redis.util.repository.HashMapOperator;
import com.redis.util.repository.KeyOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

/**
 * Created by Administrator on 2016/10/8.
 */
public class Example {

    // inject the actual template
    @Autowired
    private RedisTemplate<String, String> template;

    // inject the template as ListOperations
    @Resource(name="redisTemplate")
    private ListOperations<String, String> listOps;

    public void addLink(String userId, URL url) {
        listOps.leftPush(userId, url.toExternalForm());
    }

    public static void main(String[] args) throws MalformedURLException {
        KeyOperator<String, String> keyOperator = new KeyOperator<>();
        Set<String> set = keyOperator.keys("*");
        for (String key : set){
            System.out.println(key);
            keyOperator.rename(key, "command:" + key);
        }
    }
}
