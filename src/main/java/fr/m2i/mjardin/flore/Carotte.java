package fr.m2i.mjardin.flore;

import java.util.Map;

public class Carotte extends Vegetal implements IRacePure {

	public Carotte() {
		this(Etat.GRAINE);
	}
	
	public Carotte(final Etat etat) {
		super(etat);
		this.dessin[3] = 'c';
		this.dessin[4] = 'C';
	}

	@Override
	public void seReproduire(Map<String, Integer> panier) {
		panier.put("Carotte", panier.getOrDefault("Carotte", 0) + 3);		
	}

}
