package fr.m2i.mjardin.model;

import fr.m2i.mjardin.flore.Etat;

public record EmplacementModel(
		int id,
		int x,
		int y,
		String nomDuVegetal,
		Etat etat,
		int idJardin
		) {}
