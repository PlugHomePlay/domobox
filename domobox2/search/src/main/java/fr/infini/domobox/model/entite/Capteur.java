package fr.infini.domobox.model.entite;

public class Capteur {

	/**
	 * Identifiant technique du capteur
	 */
	private long uid;
	
	/**
	 * Nom du capteur
	 */
	private String nom;

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
}
