package Test;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class TestAPI {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.set("k1","v1");
        jedis.set("k2","v2");
        jedis.set("k3","v3");
        System.out.println(jedis.get("k3"));
        Set<String> sets = jedis.keys("*");
        System.out.println(sets);
    }

}
