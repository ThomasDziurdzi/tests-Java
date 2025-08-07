package fr.m2i.mjardin.flore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ExempleTest {
	@Mock
	BusinessHelper h; // Déclaration d'un attribut/propriété de type BusinessHelper
	
	BusinessExecutor be; // Déclaration d'un attribut de type BusinessExecutor

	@BeforeEach
	public void init() {
		be = new BusinessExecutor(h); // instancie BusinessExecutor avec "passé en paramètre du constructeur, 
		// l'instance est affectée é l'attribut be.
	}
	@Test // Indique que la fonction suivante sera exécutée durant les tests
	void test() {
		// Arrange
		when(h.treatment()).thenReturn(4); // Quand la méthode treatment sur h est appelé, alors on renvoit 4
		
		// Act
		int result = be.execute(2); // LA méthode execute est appelée sur l'instance be et 
		// son resultat est affecté à result
		
		//Assert
		verify(h).treatment(); // On vérifie que le mock a bien été sollicité via la méthode treatment
		assertEquals(8, result); // On vérifie que le résultat obtenu est égal à 8
	}
}
