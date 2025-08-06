package fr.m2i.mjardin;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import fr.m2i.mjardin.dao.interfaces.IEmplacementDAO;
import fr.m2i.mjardin.dao.interfaces.IJardinDAO;
import fr.m2i.mjardin.dao.interfaces.IPanierDAO;
import fr.m2i.mjardin.flore.Etat;
import fr.m2i.mjardin.flore.IOgm;
import fr.m2i.mjardin.flore.IRacePure;
import fr.m2i.mjardin.flore.Vegetal;
import fr.m2i.mjardin.model.EmplacementModel;
import fr.m2i.mjardin.model.JardinModel;
import fr.m2i.mjardin.model.LignePanierModel;

public class Jardin {

	/** Modèle de données **/
	private JardinModel jardinModel;
	private List<LignePanierModel> panierModel;
	private List<EmplacementModel> emplacementsModel;
	
	private Emplacement[][] emplacements;
	private HashMap<String, Integer> panier;
	
	/** DAO **/
	private IJardinDAO jardinDAO;
	private IEmplacementDAO emplacementDAO;
	private IPanierDAO panierDAO;
	
	public Jardin(final int idJardin, final IJardinDAO jardinDAO, final IEmplacementDAO emplacementDAO, final IPanierDAO panierDAO) {
		this.jardinDAO = jardinDAO;
		this.emplacementDAO = emplacementDAO;
		this.panierDAO = panierDAO;
		
		this.init(idJardin);
	}
	
	private void init(final int idJardin) {
		this.jardinModel = this.jardinDAO.getJardin(idJardin);		
		this.emplacements = new Emplacement[this.jardinModel.longueur()][this.jardinModel.largeur()];
		
		this.emplacementsModel = emplacementDAO.getEmplacements(jardinModel.id());
		for(EmplacementModel emplacementModel : emplacementsModel) {
			try {				
				Class<?> vegClazz = Class.forName("jardin.flore." + emplacementModel.nomDuVegetal());
				Vegetal veg = (Vegetal) vegClazz.getDeclaredConstructor(Etat.class).newInstance(emplacementModel.etat());
				this.emplacements[emplacementModel.x()][emplacementModel.y()] =
						new Emplacement(veg);				
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Impossible de charger ce vegetal");
			}
		}
		
		this.panier = new HashMap<String, Integer>();
		
		this.panierModel = this.panierDAO.getPanier(1);
		panierModel.forEach((ligne) -> {
			this.ajouterPanier(ligne.nomDuVegetal(), ligne.quantiteDeGraines());
		});
	}

	public void ajouterPanier(final String nomDuVegetal, final int quantite) {
		if (nomDuVegetal != null && !nomDuVegetal.isBlank() && quantite > 0) {
			this.panier.put(nomDuVegetal, this.panier.getOrDefault(nomDuVegetal, 0) + quantite);
		}
	}
	
	public void semer() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel est la valeur X ?");
		int x = scanner.nextInt();
		System.out.println("Quel est la valeur Y ?");
		int y = scanner.nextInt();
		System.out.println("Quel est le nom du végétal ?");
		String nomDuVegetal = scanner.next();

		if( x >= 0 && x < jardinModel.longueur() 
				&& y >= 0 && y < jardinModel.largeur() 
				&& this.panier.getOrDefault(nomDuVegetal, 0) > 0) {
			try {
				Class<?> vegClazz = Class.forName("jardin.flore." + nomDuVegetal);
				Vegetal veg = (Vegetal) vegClazz.getDeclaredConstructor().newInstance();
				this.emplacements[x][y] = new Emplacement(veg);
				this.panier.put(nomDuVegetal, this.panier.get(nomDuVegetal) - 1);
			} catch (Exception e) {
				System.err.println("Impossible de semer ce végétal");
			} 		
		} else {
			System.err.println("Coordonnées X ou Y erronnées ou graines indisponibles.");
		}

	}
	
	public void saisonSuivante() {
		for(int i=0; i<jardinModel.largeur(); i++) {
			for(int j=0; j<jardinModel.longueur(); j++) {
				if(emplacements[j][i] != null) {
					emplacements[j][i].getVegetal().grandir();
				}
			}
		}
	}
	
	public void recolter() {
		for(int i=0; i<jardinModel.largeur(); i++) {
			for(int j=0; j<jardinModel.longueur(); j++) {
				Emplacement e = emplacements[j][i];
				if (e != null && e.getVegetal().estRecoltable()) {
					Vegetal vegetal = e.getVegetal();
					this.emplacements[j][i] = null;
					if (vegetal instanceof IRacePure) {
						IRacePure v = (IRacePure) vegetal;
						v.seReproduire(this.panier);
					} else if (vegetal instanceof IOgm) {
						IOgm v = (IOgm) vegetal;
						SimpleEntry<Integer, Integer> nouvellesCoordonnees = v.seDupliquer(jardinModel.longueur(), jardinModel.largeur());
						this.emplacements[nouvellesCoordonnees.getKey()][nouvellesCoordonnees.getValue()] 
								= new Emplacement(vegetal);
					}
				}
			}
		}
	}
	
	@Override
	public String toString() {		
		StringBuilder sb = new StringBuilder();
		sb.append("Voici notre jardin : \n");
		
		for(int i=0; i<jardinModel.largeur(); i++) {
			for(int j=0; j<jardinModel.longueur(); j++) {
				if(emplacements[j][i] == null) {
					sb.append("o");
				} else {
					sb.append(emplacements[j][i].toString());
				}
			}
			sb.append("\n");
		}
		
		sb.append("Et notre panier contient : \n");
				
		for(String key : this.panier.keySet()) {
			sb.append(key + " : " + this.panier.get(key) + " graine(s)\n");
		}
		
		return sb.toString();
	}

	public void sauvegarder() {
		this.emplacementDAO.saveEmplacements(1, this.emplacements);
		this.panierDAO.savePanier(1, this.panier);		
	}

}