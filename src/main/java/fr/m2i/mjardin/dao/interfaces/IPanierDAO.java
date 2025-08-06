package fr.m2i.mjardin.dao.interfaces;

import java.util.HashMap;
import java.util.List;

import fr.m2i.mjardin.model.LignePanierModel;

public interface IPanierDAO {

	public List<LignePanierModel> getPanier(final int idJardin);
	public void savePanier(final int idJardin, final HashMap<String, Integer> panier);
	
}
