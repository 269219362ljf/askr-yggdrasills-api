package askr.yaggdrasills.configuration;


import askr.yaggdrasills.redis.RedisSupport;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

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
