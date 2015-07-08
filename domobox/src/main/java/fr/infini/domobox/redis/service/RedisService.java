package fr.infini.domobox.redis.service;

import fr.infini.domobox.redis.ChannelType;
import fr.infini.domobox.redis.service.implementation.RedisServiceImplementation;

public interface RedisService {
	
	static RedisService getInstance(){
		return RedisServiceImplementation.INSTANCE;
	}
	
	/**
	 * Permet d'ajouter un nouveau channel d'�coute pour les notifications.
	 * @param name Nom du channel d'�coute
	 */
	void addChannel(String name, ChannelType type);

	long getNumberOfNotification(String keysMember);
	
	void removeChannel(String name);
}
