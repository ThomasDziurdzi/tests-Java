package fr.m2i.mjardin.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.m2i.mjardin.Emplacement;
import fr.m2i.mjardin.dao.interfaces.IEmplacementDAO;
import fr.m2i.mjardin.flore.Etat;
import fr.m2i.mjardin.flore.Vegetal;
import fr.m2i.mjardin.model.EmplacementModel;

public class EmplacementDAO implements IEmplacementDAO {
	
	private Connection connection;
	
	public EmplacementDAO(final Connection connection) {
		this.connection = connection;
	}
	

	public List<EmplacementModel> getEmplacements(final int idJardin) {
		
		ArrayList<EmplacementModel> emplacements = new ArrayList<EmplacementModel>();
		
		String query = "SELECT * FROM emplacements WHERE idJardin=?";
		
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setInt(1, idJardin);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) { 
				int id = result.getInt(1);
				int x = result.getInt(2);
				int y = result.getInt(3);
				String nomDuVegetal = result.getString(4);
				Etat etat = Etat.valueOf(result.getString(5));
				
				EmplacementModel emplacementModel = new EmplacementModel(id, x, y, nomDuVegetal, etat, idJardin);
				emplacements.add(emplacementModel);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return emplacements;
	}
	
	public void saveEmplacements(final int idJardin, final Emplacement[][] emplacements) {
		
		String query = "TRUNCATE TABLE emplacements";
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.executeUpdate();
			
			int longueur = emplacements.length;
			int largeur = emplacements[longueur-1].length;
			
			for(int i=0; i<largeur; i++) {
				for(int j=0; j<longueur; j++) {
					if(emplacements[j][i] != null) {
						Vegetal vegetal = emplacements[j][i].getVegetal();
						
						query = "INSERT INTO emplacements(x,y,vegetal,statut,idjardin) "
								+ "VALUES(?,?,?,?,?)";
						PreparedStatement statementInsert = this.connection.prepareStatement(query);
						statementInsert.setInt(1, j);
						statementInsert.setInt(2, i);
						statementInsert.setString(3, vegetal.getClass().getSimpleName());
						statementInsert.setString(4, vegetal.getEtat().toString());
						statementInsert.setInt(5, idJardin);						
						statementInsert.executeUpdate();						
					}
				}
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}