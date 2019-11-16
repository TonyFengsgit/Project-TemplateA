package cn.tony.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;


/**
 * @program: RedisConfig
 * @description: redis配置类
 * @author: Chonzi.xiao
 * @create: 2019-07-16 11:32
 */
//@EnableCaching
//@Configuration
//@ConditionalOnClass(RedisOperations.class)
//@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {

    @Autowired
    private RedisProperties redisProperties;




}
