package fr.m2i.mjardin;

import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.m2i.mjardin.dao.MySQLManager;
import fr.m2i.mjardin.dao.implementations.EmplacementDAO;
import fr.m2i.mjardin.dao.implementations.JardinDAO;
import fr.m2i.mjardin.dao.implementations.PanierDAO;

public class Terre {
	
	private static Logger logger = LogManager.getLogger(Terre.class);

	public static void main(String[] args) {
		
		logger.info("Lancement de l'application.");
		
		MySQLManager manager = MySQLManager.getInstance();		
		JardinDAO jardinDAO = new JardinDAO(manager.getConnection());
		PanierDAO panierDAO = new PanierDAO(manager.getConnection());
		EmplacementDAO emplacementDAO = new EmplacementDAO(manager.getConnection());
		
		Jardin j = new Jardin(1, jardinDAO, emplacementDAO, panierDAO);
				
		Scanner scanner = new Scanner(System.in);
		int res = 0;
		do {

			System.out.println(j);
			System.out.println("Que voulez-vous faire ?");
			System.out.println("1 - Semer");
			System.out.println("2 - Récolter");
			System.out.println("3 - Passer la saison");
			System.out.println("4 - Quitter");

			try {

				res = scanner.nextInt();

				switch (res) {
				case 1:
					j.semer();
					break;
				case 2:
					j.recolter();
					break;
				case 3:
					j.saisonSuivante();
					break;
				case 4:
					j.sauvegarder();					
					System.out.println("Je quitte l'application.");
					break;
				default:
					System.err.println("Saisissez une valeur en 1 et 4.");
				}
			} catch (NoSuchElementException e) {
				System.err.println("Saissiez une valeure numérique entre 1 et 4.");
				scanner.next();
			}

		} while (res != 4);

		scanner.close();

	}

}
