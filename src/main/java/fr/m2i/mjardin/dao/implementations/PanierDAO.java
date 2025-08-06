package fr.m2i.mjardin.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.m2i.mjardin.dao.interfaces.IPanierDAO;
import fr.m2i.mjardin.model.LignePanierModel;

public class PanierDAO implements IPanierDAO {

	private Connection connection;
	
	public PanierDAO(final Connection connection) {
		this.connection = connection;
	}
	
	public List<LignePanierModel> getPanier(final int idJardin) {
		ArrayList<LignePanierModel> panier = new ArrayList<LignePanierModel>();
		
		String query = "SELECT * FROM panier WHERE idjardin=?";
		
        try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setInt(1, idJardin);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				int id = result.getInt(1);
				String nomDuVegetal = result.getString(2);
				int quantiteDeGraines = result.getInt(3);
				
				LignePanierModel ligne = new LignePanierModel(id, nomDuVegetal, quantiteDeGraines, idJardin);
				panier.add(ligne);				
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return panier;		
	}
	
	public void savePanier(final int idJardin, final Map<String, Integer> panier)  {
		
		for(String nomDuVegetal : panier.keySet()) {
			
			String query = "UPDATE panier SET quantite=? WHERE idjardin=? AND vegetal=?";
			
			try {
				PreparedStatement statement = this.connection.prepareStatement(query);
				statement.setInt(1, panier.get(nomDuVegetal));
				statement.setInt(2, idJardin);
				statement.setString(3, nomDuVegetal);
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
			
		}
		
	}
	
}
