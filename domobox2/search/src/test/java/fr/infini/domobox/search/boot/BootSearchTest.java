package fr.infini.domobox.search.boot;

import java.io.IOException;

import org.junit.Test;

/**
 * Classe de test du moteur elasticsearch
 * 
 * @author Primael
 *
 */
public class BootSearchTest {

	@Test
	public void testIndexation() throws IOException{
		
		//On prend une instance de test
		//On index genre
		BootSearch.INSTANCE.indexer("genre");
		
	}
}
