package fr.infini.domotique.model.entite.senseur;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import fr.infini.domotique.model.entite.atmega.CodeSketch;
import lombok.Delegate;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Primael
 *
 */
public abstract class Senseur {

	private interface SimpleComposantCollection {
		boolean add(Composant item);
		boolean remove(Composant item);
	}
	/**
	 * Identifiant materiel.
	 * Unique pour les senseurs
	 */
	@Getter
	@Setter
	private UUID identifiant;
	
	/**
	 * Composants �lectronique permettant le fonctionnement du capteur.
	 */
	@Getter
	@Setter
	@Delegate(types=SimpleComposantCollection.class)
	private List<Composant> composants = new ArrayList<Composant>();
	
	/**
	 * Port de connection du capteur sur la carte.
	 * Si -1, alors le capteur communique via de la radio-fr�quence.
	 */
	@Getter
	@Setter
	private int portAttach = -1;
	
	/**
	 * Code permettant � l'ATMEGA de discuter avec le capteur.
	 */
	@Getter
	@Setter
	private CodeSketch codeSketch;
	
}
