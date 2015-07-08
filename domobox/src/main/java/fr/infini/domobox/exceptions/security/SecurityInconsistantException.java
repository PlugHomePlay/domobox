package fr.infini.domobox.exceptions.security;

public class SecurityInconsistantException extends SecurityException {

	private static final long serialVersionUID = 1L;

	public SecurityInconsistantException(Throwable cause) {
		this.setMessage("Les données en base de données semblent être altéré.");
		this.setCause(cause);
	}
}
