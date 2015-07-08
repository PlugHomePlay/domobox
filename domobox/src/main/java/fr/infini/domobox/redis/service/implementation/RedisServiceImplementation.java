package fr.infini.domobox.redis.service.implementation;

import java.util.HashMap;
import java.util.Map;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import fr.infini.domobox.redis.ChannelType;
import fr.infini.domobox.redis.service.RedisService;

@Log4j2
public enum RedisServiceImplementation implements RedisService {

	INSTANCE;
	
	private final Map<String, RedisThread> threadNotification = new HashMap<String, RedisThread>();
	
	@Override
	public void addChannel(String name, ChannelType type) {
		
		log.info("Creating channel {}.", name);
		
		if(!threadNotification.containsKey(name)){
			threadNotification.put(name, createThread(name));
		}
	}

	@Override
	public void removeChannel(String name) {
		log.info("Trying to delete channel {}.", name);
		
		if(threadNotification.containsKey(name)){
			log.debug("Interrupt thread of channel {}.", name);
			threadNotification.get(name).getSubscriberJedis().disconnect();
			log.debug("Remove from map {}.", name);
			threadNotification.remove(name);
		}
	}
	/**
	 * @param name
	 */
	private RedisThread createThread(String name) {
		RedisThread nameRunnable = new RedisThread(name);
		Thread nameThread = new Thread(nameRunnable);		
		nameThread.setName(name);
		nameThread.start();
		
		return nameRunnable;
	}

	@Override
	public long getNumberOfNotification(String keysMember) {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		@Cleanup JedisPool jedisPool = new JedisPool(poolConfig, "192.168.1.24", 6379, 0);
		Jedis notificationJedis = jedisPool.getResource();
		
		return notificationJedis.scard(keysMember);
	}

	
}
