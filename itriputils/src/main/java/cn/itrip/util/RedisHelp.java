package cn.itrip.util;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class RedisHelp {

    public String getkey(String key)
    {
        Jedis redis=new Jedis("cn.lanye.site");
        redis.auth("lanye");
        String obj=(String)redis.get(key);
        return obj;
    }
    public void setKey(String key,String value,int expireTime)
    {
        Jedis redis=new Jedis("cn.lanye.site");
        redis.auth("lanye");
        redis.setex(key,expireTime,value);
    }
}
