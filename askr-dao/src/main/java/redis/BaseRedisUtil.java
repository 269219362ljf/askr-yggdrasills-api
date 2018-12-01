package redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BaseRedisUtil {

    protected static Logger log = LoggerFactory.getLogger(BaseRedisUtil.class);

    protected RedisTemplate<String, Object> redisTemplate;

    protected TimeUnit defaultExpiretimeTimeunit = TimeUnit.SECONDS;

    protected long defaultRedisExpiretime = 3600L;

    // ============================失效时间=============================

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, defaultExpiretimeTimeunit);
            }
            return true;
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return false;
        }
    }

    /**
     * 默认指定缓存失效时间
     *
     * @param key 键
     * @return
     */
    public boolean dafaultExpire(String key) {
        try {
            redisTemplate.expire(key, defaultRedisExpiretime, defaultExpiretimeTimeunit);
            return true;
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return false;
        }
    }


// ============================读取=============================


    public Set<String> getAllKeys() {
        return redisTemplate.keys("*");
    }

    public Set<String> getKeysByPattern(String pattern) {
        return redisTemplate.keys(pattern);
    }


    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, defaultExpiretimeTimeunit);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return false;
        }
    }

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }


    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return false;
        }
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return
     */
    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return 0;
        }
    }

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @return
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return
     */
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return null;
        }
    }

    /**
     * 通过索引获取有序列表（带分数）
     *
     * @param key   索引
     * @param start 开始（<0为倒数）
     * @param end   结束 （-1为最后一个）
     * @return
     */
    public Set<ZSetOperations.TypedTuple<Object>> zGetRangeWithScore(String key, long start, long end) {
        try {
            return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return null;
        }
    }

    /**
     * 通过分数获取有序列表
     *
     * @param key   索引
     * @param start 开始分数（<0为倒数）
     * @param end   结束分数 （-1为最后一个）
     * @return
     */
    public Set<ZSetOperations.TypedTuple<Object>> zGetRangeByScoreWithScore(String key, double start, double end) {
        try {
            return redisTemplate.opsForZSet().rangeByScoreWithScores(key, start, end);
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return null;
        }
    }

    /**
     * 通过分数获取有序列表
     *
     * @param key   索引
     * @param start 开始分数（<0为倒数）
     * @param end   结束分数 （-1为最后一个）
     * @return
     */
    public Set<Object> zGetRangeByScore(String key, double start, double end) {
        try {
            return redisTemplate.opsForZSet().rangeByScore(key, start, end);
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return null;
        }
    }


// ============================删除=============================


    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return 0;
        }
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return 0;
        }
    }

    /**
     * 移除N个值为item
     *
     * @param key   键
     * @param items 值 可以是多个
     * @return 移除的个数
     */
    public long zRemove(String key, Object... items) {
        try {
            return redisTemplate.opsForZSet().remove(key, items);
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return 0;
        }
    }


// ============================写入=============================

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            dafaultExpire(key);
            return true;
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, defaultExpiretimeTimeunit);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return false;
        }
    }


    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            dafaultExpire(key);
            return true;
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true成功 false失败
     */
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            dafaultExpire(key);
            return true;
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            dafaultExpire(key);
            return count;
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return 0;
        }
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0)
                expire(key, time);
            return count;
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return 0;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            dafaultExpire(key);
            return true;
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0)
                expire(key, time);
            return true;
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            dafaultExpire(key);
            return true;
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0)
                expire(key, time);
            return true;
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return false;
        }
    }

    /**
     * 将列表放入有序列表
     *
     * @param key    键
     * @param values 值列表
     * @return
     */
    public Long zSet(String key, List<ZSetOperations.TypedTuple> values) {
        try {
            Set<ZSetOperations.TypedTuple<Object>> set = new HashSet<>();
            values.stream().forEach(zSetItem -> set.add(zSetItem));
            Long count = redisTemplate.opsForZSet().add(key, set);
            dafaultExpire(key);
            return count;
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return 0L;
        }
    }

    public boolean zSet(String key, ZSetOperations.TypedTuple item) {
        try {
            redisTemplate.opsForZSet().add(key, item.getValue(), item.getScore());
            dafaultExpire(key);
            return true;
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return false;
        }
    }


// ============================更新=============================

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     */
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return
     */
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            log.error(" RedisUtil error is {} ", e);
            return false;
        }
    }

}
