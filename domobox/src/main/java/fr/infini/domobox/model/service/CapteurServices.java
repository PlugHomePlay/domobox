package fr.infini.domobox.model.service;

import java.util.List;

import fr.infini.domobox.model.entite.Capteur;
import fr.infini.domobox.model.service.implementation.CapteurServicesImplementation;

public interface CapteurServices {

	static CapteurServices getInstance(){
		return CapteurServicesImplementation.INSTANCE;
	}
	
	void ajouterCapteur(Capteur capteur);
	
	List<Capteur> listerCapteur();
	
	Capteur rechercherCapteur(long id);
	
	void supprimerCapteur(Capteur capteur);
}
