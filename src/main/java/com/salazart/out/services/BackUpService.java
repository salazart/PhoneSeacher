package com.salazart.out.services;

import java.io.File;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import com.salazart.db.services.AbstractJDBCDao;
import com.salazart.vk.services.PropService;

public class BackUpService extends AbstractJDBCDao<Serializable>{
	private static final String BACK_UP_DAY = "backUpOfDay";
	private static final String BACK_UP_PATH = "backUpPath";
	private static final String BACK_UP_FILE = "_backup.zip";
	private int dayOfBackUp;
	private String backUpPath;
	
	public BackUpService(){
		this.dayOfBackUp = Integer.valueOf(PropService.getValue(BACK_UP_DAY));
		this.backUpPath = PropService.getValue(BACK_UP_PATH);
	}
	
	public void startBackUp(){
		Calendar calendar = Calendar.getInstance();
		
		if(calendar.get(Calendar.DAY_OF_MONTH) != this.dayOfBackUp){
			
			String backUpFileName = this.backUpPath + File.separator 
					+ String.valueOf(this.dayOfBackUp) + BACK_UP_FILE;
			
			backUp(backUpFileName);
			
			PropService.setValue(BACK_UP_DAY, 
					String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
			
			this.dayOfBackUp = calendar.get(Calendar.DAY_OF_MONTH);
		}
	}
	
	protected void executeUpdate(String query) {
		try {
			Statement stat = getConnection().createStatement();
			stat.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void backUp(String fileName){
		String query = "BACKUP TO \'" + fileName + "\'";
		executeUpdate(query);
	}

	@Override
	public String queryInsert() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareInsert(PreparedStatement ps, Serializable object) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
