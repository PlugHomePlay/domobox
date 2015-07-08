package fr.infini.domobox.exceptions.security;

public class CreateCredentialException extends SecurityException {

	private static final long serialVersionUID = 1L;

	public CreateCredentialException(Throwable cause) {
		this.setMessage("Erreur lors de la tentative de création d'un credential");
		this.setCause(cause);
	}
}
