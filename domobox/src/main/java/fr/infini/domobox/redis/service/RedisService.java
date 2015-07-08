package fr.infini.domobox.redis.service;

import fr.infini.domobox.redis.ChannelType;
import fr.infini.domobox.redis.service.implementation.RedisServiceImplementation;

public interface RedisService {
	
	static RedisService getInstance(){
		return RedisServiceImplementation.INSTANCE;
	}
	
	/**
	 * Permet d'ajouter un nouveau channel d'écoute pour les notifications.
	 * @param name Nom du channel d'écoute
	 */
	void addChannel(String name, ChannelType type);

	long getNumberOfNotification(String keysMember);
	
	void removeChannel(String name);
}
