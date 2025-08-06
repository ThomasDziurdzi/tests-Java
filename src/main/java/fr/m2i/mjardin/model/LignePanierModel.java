package fr.m2i.mjardin.model;

public record LignePanierModel(
		int id, 
		String nomDuVegetal, 
		int quantiteDeGraines, 
		int jardinId) {}
