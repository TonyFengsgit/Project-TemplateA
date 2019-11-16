package cn.tony.service;


import cn.tony.pojo.User;
import cn.tony.pojo.Token;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Slf4j
@Service
public class BuildTokenService {

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 写入redis缓存（设置expire存活时间）
     * @return
     */

    public String  BuildTokenKey(String username ,String password){

        StringBuilder builder = new StringBuilder();
        String tokenKey = builder.append(username + "_" + password).toString();
        return tokenKey;
    }

    public boolean set(final String key,  Token value, Long expire){
        boolean result = false;
        String toeknjsonString = JSON.toJSONString(value);
        try {
            ValueOperations operations = redisTemplate.opsForValue();
            operations.set(key, toeknjsonString);
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            log.error("写入redis缓存（设置expire存活时间）失败！错误信息为：" + e.getMessage());
        }
        return result;
    }


    /**
     * 读取redis缓存
     * @param key
     * @return
     */
    public Object get(final String key){
        Object result = null;
        try {
            ValueOperations operations = redisTemplate.opsForValue();
            result = operations.get(key);
        } catch (Exception e) {
            log.error("读取redis缓存失败！错误信息为：" + e.getMessage());
        }
        return result;
    }

    /**
     * 判断redis缓存中是否有对应的key
     * @param key
     * @return
     */
    public boolean exists(final String key){
        boolean result = false;
        try {
            result = redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error("判断redis缓存中是否有对应的key失败！错误信息为：" + e.getMessage());
        }
        return result;
    }

    /**
     * redis根据key删除对应的value
     * @param key
     * @return
     */
    public boolean remove(final String key){
        boolean result = false;
        try {
            if(exists(key)){
                redisTemplate.delete(key);
            }
            result = true;
        } catch (Exception e) {
            log.error("redis根据key删除对应的value失败！错误信息为：" + e.getMessage());
        }
        return result;
    }

    /**
     * redis根据keys批量删除对应的value
     * @param keys
     * @return
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }

    }

    public Token builedToken(User user){

        return null;
    }

}
