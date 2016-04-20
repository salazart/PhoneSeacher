package com.salazart.db.interfaces;

import java.io.Serializable;
import java.sql.SQLException;

public interface GenericDao <T extends Serializable> {
	public void insert(T object)  throws SQLException;
	
}
