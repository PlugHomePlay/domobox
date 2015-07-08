package fr.infini.domobox.security.dao;

import fr.infini.domobox.security.dao.implementation.UtilisateurDaoImplementation;
import fr.infini.domobox.security.model.Utilisateur;

public interface UtilisateurDao {

	static UtilisateurDao getInstance(){
		return UtilisateurDaoImplementation.getInstance();
	}
	
	Utilisateur findByIdentifiant(String login);

	Utilisateur save(Utilisateur utilisateur);
	
}
