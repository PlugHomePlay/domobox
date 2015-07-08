package fr.infini.domobox.exceptions;

import org.codehaus.jackson.annotate.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class TechnicalException extends Exception {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@JsonProperty
	private String message;
	
	@Getter
	@Setter
	private Throwable cause;
	
	@Getter
	@Setter
	@JsonProperty
	private Erreurs erreurs = Erreurs.NONDEFINIE;
}
