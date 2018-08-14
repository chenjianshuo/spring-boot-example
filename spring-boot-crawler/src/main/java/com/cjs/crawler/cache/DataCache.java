package com.cjs.crawler.cache;

import com.alibaba.fastjson.JSON;
import com.cjs.crawler.domin.Blog;
import com.cjs.crawler.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by chenjianshuo on 2018/8/14 11:05.
 */

@Component
public class DataCache {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void save(Blog blog) {
        stringRedisTemplate.opsForValue().set(blog.getUrlMd5(), JSON.toJSONString(blog));
    }

    public Blog get (String url){
        String md5Url = MD5Utils.md5(url);
        String blogStr = stringRedisTemplate.opsForValue().get(md5Url);
        if (StringUtils.isEmpty(blogStr)) {
            return new Blog();
        }
        return JSON.parseObject(blogStr, Blog.class);
    }

}
