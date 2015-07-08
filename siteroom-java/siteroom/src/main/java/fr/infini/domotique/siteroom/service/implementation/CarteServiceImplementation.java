package fr.infini.domotique.siteroom.service.implementation;

import java.util.List;

import org.bson.types.ObjectId;

import fr.infini.domotique.siteroom.dao.CarteDao;
import fr.infini.domotique.siteroom.model.Carte;
import fr.infini.domotique.siteroom.service.CarteService;

public enum CarteServiceImplementation implements CarteService {
	
	INSTANCE;

	private CarteDao dao = CarteDao.getInstance();
	
	@Override
	public Carte sauvegarder(Carte carte) {
		return dao.save(carte);
	}

	@Override
	public void supprimer(ObjectId id) {
		dao.delete(id);
		
	}

	@Override
	public Carte modifier(Carte carte) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Carte> rechercher() {		
		return dao.findAll();
	}

	@Override
	public Carte rechercher(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
