package fr.infini.domotique.siteroom.service;

import java.util.List;

import org.bson.types.ObjectId;

import fr.infini.domotique.siteroom.model.Carte;
import fr.infini.domotique.siteroom.service.implementation.CarteServiceImplementation;

public interface CarteService {

	static CarteService getInstance(){
		return CarteServiceImplementation.INSTANCE;
	}
	
	Carte sauvegarder(Carte carte);
	
	void supprimer(ObjectId id);
	
	Carte modifier(Carte carte);
	
	List<Carte> rechercher();
	
	Carte rechercher(String id);
}
