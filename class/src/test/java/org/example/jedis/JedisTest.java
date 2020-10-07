package org.example.jedis;

import redis.clients.jedis.Jedis;

/**
 * @ClassName JedisTest
 * @Description TODO
 * @Author yoveuio
 * @Date 2020/6/27 14:45
 * @Version 1.0
 */
public class JedisTest {

    public void test(){
        //连接redis
        Jedis jedis = new Jedis("121.36.4.52", 6379);
        //操作数据库
        jedis.set("name", "test");
        String name = jedis.get("name");
        //关闭连接
        jedis.close();

    }

    public static void main(String[] args) {
        JedisTest test = new JedisTest();
        test.test();
    }

}
