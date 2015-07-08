package fr.infini.domobox.security.dao.implementation;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Preconditions;

import fr.infini.domobox.security.dao.UtilisateurDao;
import fr.infini.domobox.security.model.Utilisateur;
import fr.infini.domobox.utils.dao.GenericDaoImpl;

public class UtilisateurDaoImplementation extends GenericDaoImpl<Utilisateur, Long> implements UtilisateurDao {

	private UtilisateurDaoImplementation() {

	}

	private static class UtilisateurDaoHolder {
		private final static UtilisateurDao instance = new UtilisateurDaoImplementation();
	}

	public static UtilisateurDao getInstance() {
		return UtilisateurDaoHolder.instance;
	}

	@Override
	public Utilisateur findByIdentifiant(String login) {
		Preconditions.checkNotNull(login, "Got unexpected null 'login' passed to the method.");
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("login", login);

		Utilisateur utilisateur = null;

		try {
			utilisateur = this.findOneWhithNamedQuery("utilisateur.findByLogin", parameters);
		} catch (Exception exception) {
			// Utilisateur non trouvé
			// on continue
		}

		return utilisateur;
	}

}
