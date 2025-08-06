package fr.m2i.mjardin.flore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.AbstractMap.SimpleEntry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import fr.m2i.mjardin.flore.Betterave;
import fr.m2i.mjardin.flore.Etat;

public class BetteraveTest {
	
	@Test
	public void testSeDupliquer() {
		//Arrange
		int longueur = 2;
		int largeur = 5;
		Betterave betterave = new Betterave(Etat.FLEUR);
		
		//Act
		SimpleEntry<Integer, Integer> result = betterave.seDupliquer(longueur, largeur);
		
		//Assert
		int x = result.getKey();
		int y = result.getValue();
		assertTrue(x >= 0 && x < longueur);
		assertTrue(y>= 0 && y < largeur);
		assertEquals(Etat.GRAINE, betterave.getEtat());
	}
	
	@Test
	public void testSeDupliquerEtatNonFleur() {
		//Arrange
		int longueur = 2;
		int largeur = 5;
		
		for(Etat e : Etat.values()) {
			if(e != Etat.FLEUR) {
				Betterave betterave = new Betterave(e);
				
				//Act
				Executable action = () -> betterave.seDupliquer(longueur, largeur);
				
				//Assert
				assertThrows(RuntimeException.class, action);
			}
		}
		
	}

}
