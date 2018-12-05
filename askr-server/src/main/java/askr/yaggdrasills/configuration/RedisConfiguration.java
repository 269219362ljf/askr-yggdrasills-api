package askr.yaggdrasills.configuration;


import askr.yaggdrasills.utils.FastJsonRedisSerializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.RedisSupport;

import java.lang.reflect.Method;

@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {

    @Bean
    public KeyGenerator KeyGenerator() {
        return RedisSupport.RedisKeyGenerator();
    }

    //缓存管理器
    @Bean
    CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        return RedisSupport.getDefaultRedisCacheManager(connectionFactory);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        return RedisSupport.getDefaultRedisTemplate(factory);
    }
}
