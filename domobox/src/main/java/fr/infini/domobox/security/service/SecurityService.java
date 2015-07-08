package fr.infini.domobox.security.service;

import fr.infini.domobox.exceptions.TechnicalException;
import fr.infini.domobox.security.model.Utilisateur;
import fr.infini.domobox.security.service.implementation.SecurityServiceImplementation;

public interface SecurityService {

	static SecurityService getInstance(){
		return SecurityServiceImplementation.INSTANCE;
	}
	
	Utilisateur authenticate(String login, String password) throws TechnicalException;
	
	boolean creerUtilisateur(String login, String password, String email) throws TechnicalException;
	
	String creerJeton() throws TechnicalException;
}
