package fr.infini.domobox.redis.service.implementation;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import fr.infini.domobox.redis.subscriber.Subscriber;

@Log4j2
public class RedisThread implements Runnable {

	private String name;
	
	private Jedis subscriberJedis;
	
	public RedisThread(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		try {
			JedisPoolConfig poolConfig = new JedisPoolConfig();
			@Cleanup JedisPool jedisPool = new JedisPool(poolConfig, "192.168.1.24", 6379);
			subscriberJedis = jedisPool.getResource();
			
			Subscriber subscriber = new Subscriber();
			log.info("Subscribing to {}. This thread will be blocked.", name);
			subscriberJedis.subscribe(subscriber, name);
			log.info("Subscribing ended to {}.", name);
		} catch (Exception exception) {
			log.info("Subscribing to {} ended.", name);
		} 
	}

	public Jedis getSubscriberJedis() {
		return subscriberJedis;
	}
}
