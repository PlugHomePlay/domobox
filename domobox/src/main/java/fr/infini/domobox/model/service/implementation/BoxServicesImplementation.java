package fr.infini.domobox.model.service.implementation;

import java.util.List;

import fr.infini.domobox.model.dao.BoxDao;
import fr.infini.domobox.model.dao.implementation.BoxDaoImplementation;
import fr.infini.domobox.model.entite.Box;
import fr.infini.domobox.model.service.BoxServices;

public enum BoxServicesImplementation implements BoxServices {

	INSTANCE;

	private final BoxDao dao = BoxDaoImplementation.getInstance();
	
	@Override
	public void ajouterBox(Box box) {
		dao.addBox(box);
	}

	@Override
	public List<Box> listerBox() {
		return dao.getAll();
	}

	@Override
	public Box rechercherBox(long id) {
		return dao.get(id);
	}

	public List<Box> rechercherBox(String criterion) {
		
		return null;
	}
	
	@Override
	public void supprimerBox(Box box) {
		this.dao.deleteBox(box);
	}
	
	
}
