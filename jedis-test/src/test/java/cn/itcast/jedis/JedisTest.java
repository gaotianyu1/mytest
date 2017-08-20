package cn.itcast.jedis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

/**
 * JedisTest
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2017年8月14日 下午4:47:24
 * @version 1.0
 */
public class JedisTest {
	
	/** Jedis操作Redis */
	@Test
	public void test1(){
		/** 创建Jedis对象 */
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		/** 写数据到Redis数据库中 */
		String status = jedis.set("itcast", "Jedis");
		System.out.println("状态码：" + status);
		/** 从Redis中获取数据 */
		System.out.println(jedis.get("itcast"));
		/** 关闭 */
		jedis.close();
	}
	
	/** JedisPool连接池操作Redis */
	@Test
	public void test2(){
		/** 创建JedisPoolconfig配置信息对象 */
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		/** 设置最大连接数 */
		poolConfig.setMaxTotal(20);
		/** 创建JedisPool连接池对象 */
		JedisPool jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379);
		/** 获取Jedis */
		Jedis jedis = jedisPool.getResource();
		
		jedis.set("test", "JedisPool连接池操作Redis");
		
		System.out.println(jedis.get("test"));
		
		/** 关闭 */
		jedis.close();
		jedisPool.close();
	}
	
	/** ShardedJedis分片式操作Redis */
	@Test
	public void test3(){
		
		/** 创建List集合封装两台Redis服务器信息 */
		List<JedisShardInfo> shards = new ArrayList<>();
		/** 添加分片 */
		shards.add(new JedisShardInfo("127.0.0.1", 6379));
		shards.add(new JedisShardInfo("127.0.0.1", 6380));
		/** 创建ShardedJedis分片式对象 */
		ShardedJedis sj = new ShardedJedis(shards);
		
		for (int i = 0; i < 100; i++){
			sj.set("itcast" + i, "分片" + i);
		}
		
		System.out.println(sj.get("itcast10"));
		/** 关闭 */
		sj.close();
	}
}