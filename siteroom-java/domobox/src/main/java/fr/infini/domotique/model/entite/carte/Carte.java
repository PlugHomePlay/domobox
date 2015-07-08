package fr.infini.domotique.model.entite.carte;

import java.util.ArrayList;
import java.util.List;

import lombok.Delegate;
import lombok.Getter;
import lombok.Setter;
import fr.infini.domotique.model.entite.senseur.Senseur;

public class Carte {

	private interface SimpleSenseurCollection{
		boolean add(Senseur senseur);
		boolean remove(Senseur senseur);
	}
	
	@Getter
	@Setter
	private String portSerie;
	
	@Getter
	@Setter
	private String nom;
	
	@Getter
	@Setter
	private String description;
	
	@Getter
	@Setter
	private Modele modeleCarte;
	
	@Getter
	@Setter
	@Delegate(types=SimpleSenseurCollection.class)
	private List<Senseur> senseurs = new ArrayList<Senseur>();
}
