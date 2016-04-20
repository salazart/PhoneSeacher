package com.salazart.db.services;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.salazart.db.interfaces.GenericDao;

public abstract class AbstractJDBCDao <T extends Serializable> implements GenericDao<T>{
	private static final String DB_NAME = "SocialDB";
	private String user = "sa";
    private String password = "";
    private String url = "jdbc:h2:file:" + System.getProperty("user.dir") + File.separator + DB_NAME;
    
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, user, password);
	}
	
	public abstract String queryInsert();
	
	protected abstract void prepareInsert(PreparedStatement ps, T object) throws SQLException;
	
	@Override
	public void insert(T object) throws SQLException {
		PreparedStatement ps = getConnection().prepareStatement(queryInsert());
		prepareInsert(ps, object);
		ps.executeUpdate();
	}
}
