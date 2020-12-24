package com.zhangxr.springcloud.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @className RedisUtil
 * @Description redis工具类
 * @Author sdzha
 * @Date 2020/12/22 17:08
 * @Version 1.0
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /***
     * @Author sdzha
     * @Description 指定缓存失效时间
     * @Date 2020/12/22 17:09
     * @Param [key, time] [键,时间(秒)]
     * @return boolean
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /***
     * @Author sdzha
     * @Description 根据key 获取过期时间
     * @Date 2020/12/22 17:11
     * @Param [key]  键 不能为null
     * @return long 时间(秒) 返回代表为永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /***
     * @Author sdzha
     * @Description 判断key是否存在
     * @Date 2020/12/22 17:12
     * @Param [key] 键
     * @return boolean true 存在 false不存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /***
     * @Author sdzha
     * @Description 删除缓存
     * @Date 2020/12/22 17:13
     * @Param [key] 可以传一个值 或多个
     * @return void
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

    // ============================String=============================

    /***
     * @Author sdzha
     * @Description 普通缓存获取
     * @Date 2020/12/22 17:14
     * @Param [key] 键
     * @return java.lang.Object 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /***
     * @Author sdzha
     * @Description 普通缓存放入
     * @Date 2020/12/22 17:14
     * @Param [key, value] [键,值]
     * @return boolean true成功 false失败
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /***
     * @Author sdzha
     * @Description 普通缓存放入并设置时间
     * @Date 2020/12/22 17:15
     * @Param [key, value, time] [键,值,时间(秒) time要大于0 如果time小于等于 将设置无限期]
     * @return boolean true成功 false 失败
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /***
     * @Author sdzha
     * @Description 递增
     * @Date 2020/12/22 17:17
     * @Param [key, delta] [键,要增加几(大于0)]
     * @return long
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /***
     * @Author sdzha
     * @Description 递减
     * @Date 2020/12/22 17:18
     * @Param [key, delta] [键,要减少几(小于0)]
     * @return long
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    // ================================Map=================================

    /***
     * @Author sdzha
     * @Description HashGet
     * @Date 2020/12/22 17:19
     * @Param [key, item] [键 不能为null, 项 不能为null]
     * @return java.lang.Object
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /***
     * @Author sdzha
     * @Description 获取hashKey对应的所有键值
     * @Date 2020/12/22 17:20
     * @Param [key] 键
     * @return java.util.Map<java.lang.Object,java.lang.Object> 对应的多个键值
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /***
     * @Author sdzha
     * @Description HashSet
     * @Date 2020/12/22 17:20
     * @Param [key, map] [键,对应多个键值]
     * @return boolean 成功 false 失败
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /***
     * @Author sdzha
     * @Description HashSet 并设置时间
     * @Date 2020/12/22 17:21
     * @Param [key, map, time] [键,对应多个键值,时间(秒)]
     * @return boolean true成功 false失败
     */
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /***
     * @Author sdzha
     * @Description 向一张hash表中放入数据,如果不存在将创建
     * @Date 2020/12/22 17:22
     * @Param [key, item, value] [键,项,值]
     * @return boolean true 成功 false失败
     */
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /***
     * @Author sdzha
     * @Description 向一张hash表中放入数据,如果不存在将创建
     * @Date 2020/12/22 17:23
     * @Param [key, item, value, time] [键,项,值,过期时间(秒)] 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return boolean
     */
    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /***
     * @Author sdzha
     * @Description 删除hash表中的值
     * @Date 2020/12/22 17:24
     * @Param [key, item] [键 不能为null,项 可以使多个 不能为null]
     * @return void
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /***
     * @Author sdzha
     * @Description 判断hash表中是否有该项的值
     * @Date 2020/12/22 17:24
     * @Param [key, item] [键 不能为null,项 不能为null]
     * @return boolean true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /***
     * @Author sdzha
     * @Description hash递增 如果不存在,就会创建一个 并把新增后的值返回
     * @Date 2020/12/22 17:25
     * @Param [key, item, by] [键,项,要增加几(大于0)]
     * @return double
     */
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /***
     * @Author sdzha
     * @Description hash递减
     * @Date 2020/12/22 17:25
     * @Param [key, item, by] [键,项,要增加几(小于0)]
     * @return double
     */
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    // ============================set=============================

    /***
     * @Author sdzha
     * @Description 根据key获取Set中的所有值
     * @Date 2020/12/22 17:27
     * @Param [key] 键
     * @return java.util.Set<java.lang.Object>
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /***
     * @Author sdzha
     * @Description 根据value从一个set中查询,是否存在
     * @Date 2020/12/22 17:27
     * @Param [key, value] [键,值]
     * @return boolean 存在 false不存在
     */
    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /***
     * @Author sdzha
     * @Description 将数据放入set缓存
     * @Date 2020/12/22 17:28
     * @Param [key, values] [键,值 可以是多个]
     * @return long 成功个数
     */
    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /***
     * @Author sdzha
     * @Description 将set数据放入缓存
     * @Date 2020/12/22 17:29
     * @Param [key, time, values] [键,时间(秒),值 可以是多个]
     * @return long 成功个数
     */
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /***
     * @Author sdzha
     * @Description 获取set缓存的长度
     * @Date 2020/12/22 17:30
     * @Param [key] 键
     * @return long
     */
    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /***
     * @Author sdzha
     * @Description 移除值为value的
     * @Date 2020/12/22 17:30
     * @Param [key, values] [键,值 可以是多个]
     * @return long 移除的个数
     */
    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // ===============================list=================================

    /**
     * @Author sdzha
     * @Description 获取list缓存的内容
     * @Date 2020/12/22 17:31
     * @Param [key, start, end] [键,开始,结束  到 -代表所有值]
     * @return java.util.List<java.lang.Object>
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /***
     * @Author sdzha
     * @Description 获取list缓存的长度
     * @Date 2020/12/22 17:31
     * @Param [key] 键
     * @return long
     */
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /***
     * @Author sdzha
     * @Description 通过索引 获取list中的值
     * @Date 2020/12/22 17:32
     * @Param [key, index] [键,索引] index>=0时，表头，二个元素，依次类推；index<0时，-，表尾，-倒数第二个元素，依次类推
     * @return java.lang.Object
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /***
     * @Author sdzha
     * @Description 将list放入缓存
     * @Date 2020/12/22 17:33
     * @Param [key, value] [键,值]
     * @return boolean
     */
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /***
     * @Author sdzha
     * @Description 将list放入缓存
     * @Date 2020/12/22 17:33
     * @Param [key, value, time] [键,值,过期时间(秒)]
     * @return boolean
     */
    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /***
     * @Author sdzha
     * @Description 将list放入缓存
     * @Date 2020/12/22 17:34
     * @Param [key, value] [键,值]
     * @return boolean
     */
    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /***
     * @Author sdzha
     * @Description 将list放入缓存，并设置过期时间
     * @Date 2020/12/22 17:34
     * @Param [key, value, time] [键,值,过期时间(秒)]
     * @return boolean
     */
    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /***
     * @Author sdzha
     * @Description 根据索引修改list中的某条数据
     * @Date 2020/12/22 17:35
     * @Param [key, index, value] [键,索引,值]
     * @return boolean
     */
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @Author sdzha
     * @Description 移除N个值为value
     * @Date 2020/12/22 17:36
     * @Param [key, count, value] [键,移除多少个,值]
     * @return long 移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }
}


















