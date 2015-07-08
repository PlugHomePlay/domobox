package fr.infini.domotique.model.entite.carte;

import lombok.Getter;
import lombok.Setter;

public class Modele {

	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String mcu;
	
	@Getter
	@Setter
	private long f_cpu;
	
	@Getter
	@Setter
	private int arduino;
	
	@Getter
	@Setter
	private String variant;
	
	@Getter
	@Setter
	private long tailleMax;
}
