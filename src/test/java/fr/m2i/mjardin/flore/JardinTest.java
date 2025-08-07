package fr.m2i.mjardin.flore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.m2i.mjardin.Jardin;
import fr.m2i.mjardin.dao.MySQLManager;
import fr.m2i.mjardin.dao.implementations.EmplacementDAO;
import fr.m2i.mjardin.dao.implementations.JardinDAO;
import fr.m2i.mjardin.dao.implementations.PanierDAO;
import fr.m2i.mjardin.model.EmplacementModel;
import fr.m2i.mjardin.model.JardinModel;
import fr.m2i.mjardin.model.LignePanierModel;

@ExtendWith(MockitoExtension.class)
public class JardinTest {
	@Mock
	Scanner scanner;
	@Mock
	JardinDAO jardinDAO;
	@Mock
	PanierDAO panierDAO;
	@Mock
	EmplacementDAO emplacementDAO;
	int idJArdin = 1;
	
	@BeforeEach
	private void init() {
		JardinModel jModel = new JardinModel(idJArdin, 3, 2);
		List<EmplacementModel> eModel = new ArrayList<EmplacementModel>();
		List<LignePanierModel> pModel = new ArrayList<LignePanierModel>();
		pModel.add(new LignePanierModel(1, "Ail", 1, 1));	
		
		when(jardinDAO.getJardin(idJArdin)).thenReturn(jModel);
		when(emplacementDAO.getEmplacements(idJArdin)).thenReturn(eModel);
		when(panierDAO.getPanier(idJArdin)).thenReturn(pModel);
	}
	
	@AfterEach
	private void close() {
		verify(jardinDAO).getJardin(idJArdin);
		verify(emplacementDAO).getEmplacements(idJArdin);
		verify(panierDAO).getPanier(idJArdin);
	}

	@Test
	public void testSemer() {
		// Arrange
		when(scanner.nextInt()).thenReturn(0, 1);
		when(scanner.next()).thenReturn("Ail");
		
		Map<String, Integer> panier = new HashMap<String, Integer>();
		Jardin j = new Jardin(1, panier, jardinDAO, emplacementDAO, panierDAO, scanner);
		int nbGraines = panier.get("Ail");
		
		// Act
		j.semer();

		// Assert
		verify(scanner, times(2)).nextInt();
		verify(scanner).next();
		assertEquals(nbGraines - 1, panier.get("Ail"));
	}

	@Test
	public void testAjouterPanier() {
		// Arrange
	
		Map<String, Integer> panier = new HashMap<String, Integer>();
		Jardin j = new Jardin(1, panier, jardinDAO, emplacementDAO, panierDAO, scanner);
		
		String vegetal = "Ail";
		int nbGraines = panier.get(vegetal);
		int quantiteAjoutee = 3;

		// Act
		j.ajouterPanier(vegetal, quantiteAjoutee);

		// Assert
		assertEquals(nbGraines + quantiteAjoutee, panier.get(vegetal));
	}
	@Test
	public void testArracher() {
		// Arrange
		eModel.add(EmplacementModel(1,0,0, "Ail", Etat.GRAINE, idJArdin));
		Map<String, Integer> panier = new HashMap<String, Integer>();
	    Jardin j = new Jardin(1, panier, jardinDAO, emplacementDAO, panierDAO, scanner);
	    
		// Act
		
		// Assert
	}
}
