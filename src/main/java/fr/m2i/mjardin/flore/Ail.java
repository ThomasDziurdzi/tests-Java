package fr.m2i.mjardin.flore;

import java.util.Map;

public class Ail extends Vegetal implements IRacePure {

	public Ail() {
		this(Etat.GRAINE);
	}
	
	public Ail(final Etat etat) {
		super(etat);
		this.dessin[3] = 'a';
		this.dessin[4] = 'A';
	}

	@Override
	public void seReproduire(Map<String, Integer> panier) {
		panier.put("Ail", panier.getOrDefault("Ail", 0) + 3);		
	}
	
}
