package be.helha.dao.daoimpl;

import be.helha.dao.BiereDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
	private static final String URL = "jdbc:postgresql://localhost:5432/";
	private static final String USER = "postgres";
	private static final String MDP = "1234";
	private static DaoFactory instance;

	private DaoFactory() {		
	}
	
	public static DaoFactory getInstance() {
		if ( instance==null) instance=new DaoFactory();
		return instance;
	}
	
	public BiereDao getBiereDao() {
		return new BiereDaoImpl(getInstance());
	}

	public Connection getConnexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, MDP);
	}
}
