package fr.infini.domobox.model.service;

import java.util.List;

import fr.infini.domobox.model.entite.Box;
import fr.infini.domobox.model.service.implementation.BoxServicesImplementation;

public interface BoxServices {

	static BoxServices getInstance(){
		return BoxServicesImplementation.INSTANCE;
	}
	
	void ajouterBox(Box box);
	
	List<Box> listerBox();
	
	Box rechercherBox(long id);
	
	void supprimerBox(Box box);
}
