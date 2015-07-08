package fr.infini.domobox.redis;

public enum ChannelType {

	CAPTEUR,
	BOX;
	
	public String calculateName(String nom){
		return nom;
	}
}
