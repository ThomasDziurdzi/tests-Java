package fr.m2i.mjardin.flore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;


public class AilTest {

	

	@Test
	public void testSeReproduireVide() {
		//Arrange
		java.util.HashMap<String, Integer> panier = new HashMap<String, Integer>();
		Ail ail = new Ail();
		
		//Act
		ail.seReproduire(panier);
		int resultatObtenu = panier.get("Ail");
		
		//Assert
		int resultatAttendu = 3;
		assertEquals(resultatAttendu, resultatObtenu);
	}
	
	@Test
	public void testSeReproduireNonVide() {
		//Arrange
		java.util.HashMap<String, Integer> panier = new HashMap<String, Integer>();
		panier.put("Ail", 4);
		Ail ail = new Ail();
		
		//Act
		ail.seReproduire(panier);
		int resultatObtenu = panier.get("Ail");
		
		//Assert
		int resultatAttendu = 7;
		assertEquals(resultatAttendu, resultatObtenu);
	}
}
