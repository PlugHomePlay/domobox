package fr.infini.domobox.exceptions.security;

import fr.infini.domobox.exceptions.Erreurs;

public class SessionExpireException extends SecurityException {

	private static final long	serialVersionUID	= 1L;
	
	public SessionExpireException(Throwable cause) {
		this.setMessage("La session a expir�e, veuillez vous reconnectez.");
		this.setErreurs(Erreurs.SESSIONEXPIRE);
		this.setCause(cause);
	}
}
