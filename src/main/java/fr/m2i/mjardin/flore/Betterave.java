package fr.m2i.mjardin.flore;

import java.util.AbstractMap.SimpleEntry;
import java.util.Random;

public class Betterave extends Vegetal implements IOgm {
	
	public Betterave() {
		this(Etat.GRAINE);
	}
	
	public Betterave(final Etat etat) {
		super(etat);
		this.dessin[3] = 'b';
		this.dessin[4] = 'B';
	}

	@Override
	public SimpleEntry<Integer, Integer> seDupliquer(int longueur, int largeur) {
		if(!this.estRecoltable()) {
			throw new RuntimeException("Le vegetal n'est pas récoltable!");			
		}
		Random random = new Random();
		int x = random.nextInt(longueur);
		int y = random.nextInt(largeur);
		this.etat = Etat.GRAINE;
		return new SimpleEntry<Integer, Integer>(x, y);
	}
}
