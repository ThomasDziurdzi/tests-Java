package fr.m2i.mjardin.flore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.AbstractMap.SimpleEntry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class BetteraveTest {

	@Test
	public void testSeDupliquer() {
		// Arrange
		int longueur = 4;
		int largeur = 2;
		Betterave betterave = new Betterave();

		// Act
		Executable action = () -> betterave.seDupliquer(longueur, largeur);
		
		// Assert
		assertThrows(RuntimeException.class, action);
	}
	
	@Test
	public void testSeDupliquerEtatNonFleur() {
		// Arrange
		Betterave betterave = new Betterave(Etat.FLEUR);
		int longueur = 4;
		int largeur = 2;

		// Act
		SimpleEntry<Integer, Integer> result = betterave.seDupliquer(longueur, largeur);
		

		// Assert
		int x = result.getKey();
		int y = result.getValue();

		assertTrue(x >= 0 && x < longueur);
		assertTrue(y >= 0 && y < largeur);
		assertEquals(Etat.GRAINE, betterave.getEtat());
	}
	
}
