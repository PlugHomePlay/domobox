package fr.infini.domobox.webservices;

import java.util.UUID;

import org.junit.Test;

import fr.infini.domobox.model.entite.Box;

public class BoxRestServiceTest {

	private BoxRestService servicesUnderTest = new BoxRestService();
	
	@Test
	public void ajouterBox(){
		Box box = new Box();
		box.setNom("Chambre C�me");
		box.setDescription("Domobox de la chambre de C�me. Gestion de la temp�rature, humidit�, lumi�re et prise �lectrique");
		
		servicesUnderTest.ajouterDomobox(box);
	}
	
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString()); //36 carac
	}
}
