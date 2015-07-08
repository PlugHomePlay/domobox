package fr.infini.domobox.utils;

import fr.infini.domobox.model.entite.Box;
import fr.infini.domobox.model.entite.Capteur;

public enum RedisHandler {

	INSTANCE;
	
	public String getRedisName(Box box){
		return box.getNom() + "-" + box.getId();
	}
	
	public String getRedisName(Capteur capteur){
		return capteur.getNom() + "-" + capteur.getId();
	}
}
