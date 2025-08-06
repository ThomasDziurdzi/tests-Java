package fr.m2i.mjardin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MySQLManager {

	private Connection connection = null;
	private String url = "jdbc:mysql://localhost:3306/m2i";
	private String username = "root";
	private String password = "";

	private static MySQLManager instance = null;
	private static Logger logger = LogManager.getLogger(MySQLManager.class);

	public Connection getConnection() {
		return connection;
	}

	public static synchronized MySQLManager getInstance() {
		if (instance == null) {
			instance = new MySQLManager();
		}
		return instance;
	}

	private MySQLManager() {
		try {
			logger.info("Tentative de connection à la DB...");
			this.connection = DriverManager.getConnection(url, username, password);
			logger.info("Connecté à la DB...");
		} catch (SQLException exception) {
			logger.error("Connexion échouée : " + exception.getMessage());
			System.err.println("Impossible de se connecter à la base de données");
		}
	}

}
