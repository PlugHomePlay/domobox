package fr.infini.domobox.boot.initializer;

import lombok.extern.log4j.Log4j2;
import fr.infini.domobox.model.entite.Box;
import fr.infini.domobox.model.entite.Capteur;
import fr.infini.domobox.model.service.BoxServices;
import fr.infini.domobox.model.service.CapteurServices;
import fr.infini.domobox.redis.service.RedisService;
import fr.infini.domobox.utils.RedisHandler;

@Log4j2
public enum RedisInitializer {
	
	INSTANCE;

	private final BoxServices servicesBox = BoxServices.getInstance();
	
	private final CapteurServices servicesCapteur = CapteurServices.getInstance();
	
	
	public void compute(){
		log.info("=====================================================================");
		log.info("|| Lancement des channels de notifications:                        ||");
		log.info("=====================================================================");
		
		log.info("=====================================================================");
		log.info("|| Box                                                   || OK     ||");
		log.info("=====================================================================");
		this.computeBox();
		
		log.info("=====================================================================");
		log.info("|| Capteur                                               || OK     ||");
		log.info("=====================================================================");
		this.computeCapteur();
	}
	
	private void computeBox(){
		for(Box box : servicesBox.listerBox()){
			this.addChannelNotification(RedisHandler.INSTANCE.getRedisName(box));
		}
	}
	
	private void computeCapteur(){
		for(Capteur capteur : servicesCapteur.listerCapteur()){
			this.addChannelNotification(RedisHandler.INSTANCE.getRedisName(capteur));
		}
	}
	
	/**
	 * Ajout d'un nouveau channel d'écoute au démarrage 
	 * @param name
	 */
	private void addChannelNotification(String name){
		RedisService.getInstance().addChannel(name, null);
	}
}
