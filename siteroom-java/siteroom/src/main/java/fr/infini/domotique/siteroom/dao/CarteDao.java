package fr.infini.domotique.siteroom.dao;

import java.util.List;

import org.bson.types.ObjectId;

import fr.infini.domotique.siteroom.dao.implementation.CarteDaoImplementation;
import fr.infini.domotique.siteroom.model.Carte;

public interface CarteDao {

	static CarteDao getInstance(){
		return CarteDaoImplementation.INSTANCE;
	}
	
	Carte save(Carte carte);
	
	List<Carte> findAll();

	Carte findById(ObjectId id);
	
	void delete(ObjectId id);
}
