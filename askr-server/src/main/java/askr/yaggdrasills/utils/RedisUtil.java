package askr.yaggdrasills.utils;


import askr.yaggdrasills.constant.Constants;
import askr.yaggdrasills.redis.BaseRedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


@Component
public class RedisUtil extends BaseRedisUtil {

    @Resource
    private RedisTemplate<String, Object> realRedisTemplate;

    @Autowired
    private Constants constants;

    @Value("${REDIS_EXPIRETIME}")
    private long REDIS_EXPIRETIME;

    @PostConstruct
    public void redisUtilInit() {
        redisTemplate = realRedisTemplate;
        defaultExpiretimeTimeunit = constants.getRedisExpiretimeTimeunit();
        defaultRedisExpiretime = REDIS_EXPIRETIME;
    }
}
