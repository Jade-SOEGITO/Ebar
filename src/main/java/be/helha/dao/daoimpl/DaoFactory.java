package be.helha.dao.daoimpl;

import be.helha.dao.BiereDao;

public class DaoFactory {

	private static DaoFactory instance;

	private DaoFactory() {		
	}
	
	public static DaoFactory getInstance() {
		if ( instance==null) instance=new DaoFactory();
		return instance;
	}
	
	public BiereDao getBiereDao() {
		return new BiereDaoMockImpl();
	}
}
