package fr.m2i.mjardin.flore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class VegetalTest {

	@Test
	public void testGrandir() {
		// Arrange
		Ail vegetal = new Ail();

		String[] etatsAttendus = { "GERME", "TIGE", "FEUILLE", "FLEUR", "MORT" };

		for (String etatAttendu : etatsAttendus) {
			// Act
			vegetal.grandir();

			// Assert
			assertEquals(etatAttendu, vegetal.getEtat().toString());
		}
	}
}
