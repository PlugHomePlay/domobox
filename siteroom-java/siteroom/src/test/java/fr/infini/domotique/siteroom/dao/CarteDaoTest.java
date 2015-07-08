package fr.infini.domotique.siteroom.dao;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.infini.domotique.siteroom.model.Carte;

public class CarteDaoTest {

	private CarteDao instanceUnderTest = CarteDao.getInstance();
	
	private Carte getCarte(){
		Carte carte = new Carte();
		carte.setCreatedAt(new Date());
		carte.setDescription("test de création");
		carte.setName("carte de test");
		carte.setPortName("COM3");
		
		return carte;
	}
	
	@Test
	public void instanceNotNull(){
		Assert.assertNotNull("Dao null, check implementation", instanceUnderTest);
	}
	
	@Test
	public void findAllCards(){
		
		List<Carte> cards = instanceUnderTest.findAll();
		
		Assert.assertNotNull("List is null", cards);
	}
	
	@Test
	public void addCard(){
		
		Carte carte = getCarte();
		
		instanceUnderTest.save(carte);
		
		Assert.assertNotNull("Card returned is null", carte);
		Assert.assertNotNull("Card id returned is null", carte.getId());
	}
	
	@Test
	public void findCard(){
		
		Carte carte = getCarte();
		
		Assert.assertNull("Card id should be null", carte.getId());
		
		carte = instanceUnderTest.save(carte);
		
		Assert.assertNotNull("Card returned is null", carte);
		Assert.assertNotNull("Card id returned is null", carte.getId());
		
		Carte cardFind = instanceUnderTest.findById(carte.getId());
		
		Assert.assertNotNull("Card find returned is null", cardFind);
		Assert.assertNotNull("Card find id returned is null", cardFind.getId());
		
	}
	
	@Test
	public void deleteCard(){
		
		//Number of cards before test
		int sizeOfTableCardsBeforeTest = instanceUnderTest.findAll().size();
		Carte carte = getCarte();
		
		Assert.assertNull("Card id should be null", carte.getId());
		
		carte = instanceUnderTest.save(carte);
		
		Assert.assertNotNull("Card returned is null", carte);
		Assert.assertNotNull("Card id returned is null", carte.getId());
		
		Carte cardFind = instanceUnderTest.findById(carte.getId());
		
		Assert.assertNotNull("Card find returned is null", cardFind);
		Assert.assertNotNull("Card find id returned is null", cardFind.getId());
		
		cardFind = null;
		
		//delete card
		instanceUnderTest.delete(carte.getId());
		
		cardFind = instanceUnderTest.findById(carte.getId());
		
		Assert.assertNull("Card find returned is not null", cardFind);
		
		int sizeOfTableCardsAfterTest = instanceUnderTest.findAll().size();
		
		Assert.assertEquals("The size of table have changed", sizeOfTableCardsBeforeTest, sizeOfTableCardsAfterTest);
	}
}
