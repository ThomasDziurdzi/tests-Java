package fr.m2i.mjardin.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.m2i.mjardin.dao.interfaces.IJardinDAO;
import fr.m2i.mjardin.model.JardinModel;

public class JardinDAO implements IJardinDAO {
	
	private Connection connection;
	
	public JardinDAO(final Connection connection) {
		this.connection = connection;
	}
	
	public JardinModel getJardin(final int idJardin) {
		
		String query = "SELECT * FROM jardin WHERE idjardin=?";
		
		try {
			PreparedStatement stat = this.connection.prepareStatement(query);
			stat.setInt(1, idJardin);
			ResultSet result = stat.executeQuery();
			result.next(); // on se place sur l'unique ligne retounée
			
			int id = result.getInt(1);
			int longueur = result.getInt(2);
			int largeur = result.getInt(3);
			
			JardinModel jardinModel = new JardinModel(id, longueur, largeur);
			return jardinModel;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

}
