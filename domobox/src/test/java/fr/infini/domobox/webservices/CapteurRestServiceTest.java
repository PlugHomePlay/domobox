package fr.infini.domobox.webservices;

import java.util.UUID;

import org.junit.Test;

import fr.infini.domobox.model.entite.Capteur;
import fr.infini.domobox.model.entite.Fabricant;
import fr.infini.domobox.model.entite.Role;
import fr.infini.domobox.model.entite.TypeDeCapteur;

public class CapteurRestServiceTest {

	private CapteurRestService serviceUnderTest = new CapteurRestService();
	
	@Test
	public void ajouterBox(){
		
		Fabricant fabricant = new Fabricant();
		fabricant.setNom("fabricant test");
		
		Role role = new Role();
		role.setNom("Role test 2");
		
		TypeDeCapteur typeDeCapteur = new TypeDeCapteur();
		typeDeCapteur.setLibelle("Type de capteur test 2");
		
		Capteur capteur = new Capteur();
		capteur.setDescription("description test 2");
		capteur.setFabricant(fabricant);
		capteur.setNom("nom test 2");
		capteur.setRole(role);
		capteur.setTypeDeCapteur(typeDeCapteur);
		capteur.setSerial(UUID.randomUUID().toString());
		
		serviceUnderTest.ajouterCapteur(capteur);
		
	}
}
