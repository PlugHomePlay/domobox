package fr.infini.domotique.model.entite.carte;

public class CarteTest {

	public void createCarte(){
		Carte carte = new Carte();
		carte.setNom("Carte relais");
		carte.setDescription("Carte portant la sonde de température");
		carte.setPortSerie("COM3");
		
		Modele modele = new Modele();
		modele.setArduino(106);
		modele.setF_cpu(16000000L);
		modele.setMcu("atmega328p");
		modele.setVariant("standard");
		modele.setName("Atmega328");
		
		carte.setModeleCarte(modele);
	}
}
