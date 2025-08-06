package fr.m2i.mjardin.flore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class AilTest {

	@Test
	public void testSeReproduireVide() {
		// Arrange
		Ail ail = new Ail();
		HashMap<String, Integer> panier = new HashMap<>();

		// Act
		ail.seReproduire(panier);
		int resultObtenu = panier.get("Ail");
		// Assert
		int resultatAttendu = 3;
		assertEquals(resultatAttendu, resultObtenu);
	}
	
	@Test
	public void testSeReproduireNonVide() {
		// Arrange
		Ail ail = new Ail();
		HashMap<String, Integer> panier = new HashMap<>();
		panier.put("Ail", 4);

		// Act
		ail.seReproduire(panier);
		int resultObtenu = panier.get("Ail");
		// Assert
		int resultatAttendu = 7;
		assertEquals(resultatAttendu, resultObtenu);
	}
}
