package fr.m2i.mjardin.flore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import fr.m2i.mjardin.flore.Carotte;

public class CarotteTest {
	
	@Test
	public void testSeReproduire() {
		// Arrange
		Carotte carotte = new Carotte();
		HashMap<String, Integer> panier = new HashMap<String, Integer>();
		// Act
		carotte.seReproduire(panier);
		int resultatObtenu = panier.get("Carotte");
		// Assert
		int resultatAttendu = 3;
		assertEquals(resultatAttendu, resultatObtenu);
	}
}
