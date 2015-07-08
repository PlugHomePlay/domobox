package fr.infini.domobox.security.service;

import fr.infini.domobox.exceptions.security.SecurityException;
import fr.infini.domobox.security.model.Utilisateur;
import fr.infini.domobox.security.service.implementation.SessionServiceImplementation;

public interface SessionService {

	static SessionService getInstance(){
		return SessionServiceImplementation.INSTANCE;
	}
	
	void ajouterSession(Utilisateur utilisateur, String cleSession);
	
	void validerSession(String cleSession) throws SecurityException;
}
