package fr.infini.domobox.model.dao;

import java.util.ArrayList;

import fr.infini.domobox.model.dao.implementation.CapteurDaoImplementation;
import fr.infini.domobox.model.entite.Capteur;

public interface CapteurDao {

	static CapteurDao getInstance() {
		return CapteurDaoImplementation.getInstance();
	}

	void addCapteur(Capteur capteur);

	ArrayList<Capteur> getAll();

	Capteur get(Long id);

	void deleteCapteur(Capteur capteur);
}
