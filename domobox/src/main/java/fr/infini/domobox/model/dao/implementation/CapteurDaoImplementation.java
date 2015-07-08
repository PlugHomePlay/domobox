package fr.infini.domobox.model.dao.implementation;

import fr.infini.domobox.model.dao.CapteurDao;
import fr.infini.domobox.model.entite.Capteur;
import fr.infini.domobox.utils.dao.GenericDaoImpl;

public class CapteurDaoImplementation extends GenericDaoImpl<Capteur, Long> implements CapteurDao {

	private CapteurDaoImplementation() {
	}

	private static class CapteurDaoHolder {
		private final static CapteurDao instance = new CapteurDaoImplementation();
	}

	public static CapteurDao getInstance() {
		return CapteurDaoHolder.instance;
	}

	@Override
	public void addCapteur(Capteur capteur) {
		this.save(capteur);
	}

	@Override
	public void deleteCapteur(Capteur capteur) {
		this.delete(capteur);
	}
}
