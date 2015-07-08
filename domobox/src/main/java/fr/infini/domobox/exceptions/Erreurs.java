package fr.infini.domobox.exceptions;

import lombok.Getter;

public enum Erreurs {

	NONDEFINIE(666),
	
	ERREURAUTHENTIFICATION(100),
	SESSIONEXPIRE(101),
	SESSIONINEXISTANTE(102);
	
	@Getter
	private final int code;
	
	private Erreurs(int code) {
		this.code = code;
	}
}
