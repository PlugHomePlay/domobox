package fr.infini.domobox.exceptions.security;

public class SecurityInconsistantException extends SecurityException {

	private static final long serialVersionUID = 1L;

	public SecurityInconsistantException(Throwable cause) {
		this.setMessage("Les donn�es en base de donn�es semblent �tre alt�r�.");
		this.setCause(cause);
	}
}
