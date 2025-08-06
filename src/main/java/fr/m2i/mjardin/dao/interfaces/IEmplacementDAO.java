package fr.m2i.mjardin.dao.interfaces;

import java.util.List;

import fr.m2i.mjardin.Emplacement;
import fr.m2i.mjardin.model.EmplacementModel;

public interface IEmplacementDAO {

	public List<EmplacementModel> getEmplacements(final int idJardin);
	public void saveEmplacements(final int idJardin, final Emplacement[][] emplacements);

}