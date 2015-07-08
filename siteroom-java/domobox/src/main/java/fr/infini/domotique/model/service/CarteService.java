package fr.infini.domotique.model.service;

import fr.infini.domotique.model.entite.carte.Carte;
import fr.infini.domotique.model.service.implementation.CarteServiceImplementation;

public interface CarteService {

	static CarteService getInstance(){
		return CarteServiceImplementation.INSTANCE;
	}
	
	void compilerCode(Carte carte);
}
