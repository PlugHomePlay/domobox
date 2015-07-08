package fr.infini.domobox.exceptions.security;

import fr.infini.domobox.exceptions.Erreurs;

public class AuthentificationFailureException extends SecurityException {

	private static final long serialVersionUID = 1L;

	public AuthentificationFailureException(Throwable cause){
		this.setMessage("Echec lors de l'authentification.");
		this.setCause(cause);
		this.setErreurs(Erreurs.ERREURAUTHENTIFICATION);
	}
}
