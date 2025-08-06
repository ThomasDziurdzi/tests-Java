package fr.m2i.mjardin.flore;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
 
import java.util.HashMap;
import java.util.Map;
 
import org.junit.jupiter.api.Test;
 
import fr.m2i.mjardin.Jardin;
import fr.m2i.mjardin.dao.MySQLManager;
import fr.m2i.mjardin.dao.implementations.EmplacementDAO;
import fr.m2i.mjardin.dao.implementations.JardinDAO;
import fr.m2i.mjardin.dao.implementations.PanierDAO;
 
public class JardinTest {
	@Test
	public void testAjouterPanier () {
		//Arrange
		MySQLManager manager = MySQLManager.getInstance();		
		JardinDAO jardinDAO = new JardinDAO(manager.getConnection());
		PanierDAO panierDAO = new PanierDAO(manager.getConnection());
		EmplacementDAO emplacementDAO = new EmplacementDAO(manager.getConnection());
		Map<String, Integer> panier = new HashMap<String, Integer>();
		Jardin j = new Jardin(1, panier, jardinDAO, emplacementDAO, panierDAO);
		
		String vegetal = "Ail";
		
		//Act
		j.ajouterPanier(vegetal, 3);
		
		//Assert
		assertEquals(3, panier.get(vegetal));
	}
}
 