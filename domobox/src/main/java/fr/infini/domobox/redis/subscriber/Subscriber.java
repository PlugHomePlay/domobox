package fr.infini.domobox.redis.subscriber;

import lombok.extern.log4j.Log4j2;
import redis.clients.jedis.JedisPubSub;

@Log4j2
public class Subscriber extends JedisPubSub {
	
	@Override
	public void onMessage(String channel, String message) {
		log.info("Message received. Channel: {}, Msg: {}", channel, message);
	}

	@Override
	public void onPMessage(String pattern, String channel, String message) {
	}

	@Override
	public void onPSubscribe(String pattern, int subscribedChannels) {
	}

	@Override
	public void onPUnsubscribe(String pattern, int subscribedChannels) {
	}

	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
	}

	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {
	}

	
}
