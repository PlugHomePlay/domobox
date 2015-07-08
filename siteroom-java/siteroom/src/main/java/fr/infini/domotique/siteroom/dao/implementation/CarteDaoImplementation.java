package fr.infini.domotique.siteroom.dao.implementation;

import java.util.List;

import org.bson.types.ObjectId;

import fr.infini.domotique.siteroom.dao.CarteDao;
import fr.infini.domotique.siteroom.model.Carte;

public enum CarteDaoImplementation implements CarteDao {
	
	INSTANCE;

	private CarteDaoImplementation() {
		
	}
	
	@Override
	public Carte save(Carte carte) {
		DaoHelper.INSTANCE.getDataStore().save(carte);
		return carte;
	}

	@Override
	public List<Carte> findAll() {
		return DaoHelper.INSTANCE.getDataStore().find(Carte.class).asList();
	}

	@Override
	public Carte findById(ObjectId id) {
		return  DaoHelper.INSTANCE.getDataStore().find(Carte.class).field("id").equal(id).get();
	}

	@Override
	public void delete(ObjectId id) {
		DaoHelper.INSTANCE.getDataStore().delete(Carte.class, id);
	}
}
