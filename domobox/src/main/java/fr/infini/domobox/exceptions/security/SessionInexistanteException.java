package fr.infini.domobox.exceptions.security;

import fr.infini.domobox.exceptions.Erreurs;

public class SessionInexistanteException extends SecurityException {

	private static final long serialVersionUID = 1L;

	public SessionInexistanteException(Throwable cause) {
		this.setMessage("La session n'existe pas, veuillez vous (re)connectez.");
		this.setErreurs(Erreurs.SESSIONINEXISTANTE);
		this.setCause(cause);
	}
}
