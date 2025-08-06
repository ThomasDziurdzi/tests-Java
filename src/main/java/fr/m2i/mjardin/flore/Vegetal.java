package fr.m2i.mjardin.flore;

public abstract class Vegetal {

	protected Etat etat;
	protected char[] dessin;

	public Etat getEtat() {
		return etat;
	}
	
	public Vegetal(final Etat etat) {
		this.dessin = new char[6];
		this.dessin[0] = '_';
		this.dessin[1] = '.';
		this.dessin[2] = '|';
		this.dessin[5] = '#';
		this.etat = etat;
	}

	@Override
	public String toString() {
		return String.valueOf(this.dessin[this.etat.ordinal()]);
	}

	public void grandir() {
		if (this.etat != Etat.MORT) {
			this.etat = Etat.values()[this.etat.ordinal() + 1];
		}
	}
	
	public boolean estRecoltable() {
		return this.etat == Etat.FLEUR;
	}

}
