package fr.m2i.mjardin.flore;

public class Tomate extends Vegetal {

	public Tomate() {
		this(Etat.GRAINE);
	}
	
	public Tomate(final Etat etat) {
		super(etat);
		this.dessin[3] = 't';
		this.dessin[4] = 'T';		
	}
	
}
