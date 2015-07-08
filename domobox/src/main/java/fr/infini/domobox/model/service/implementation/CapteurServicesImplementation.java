package fr.infini.domobox.model.service.implementation;

import java.util.List;

import fr.infini.domobox.model.dao.CapteurDao;
import fr.infini.domobox.model.entite.Capteur;
import fr.infini.domobox.model.service.CapteurServices;

public enum CapteurServicesImplementation implements CapteurServices {

	INSTANCE;

	private final CapteurDao dao = CapteurDao.getInstance();
	
	@Override
	public void ajouterCapteur(Capteur capteur) {
		dao.addCapteur(capteur);
	}

	@Override
	public List<Capteur> listerCapteur() {
		return dao.getAll();
	}

	@Override
	public Capteur rechercherCapteur(long id) {
		return dao.get(id);
	}

	@Override
	public void supprimerCapteur(Capteur capteur) {
		dao.deleteCapteur(capteur);
	}
	
	
}
