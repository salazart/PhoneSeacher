package com.salazart.db.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.salazart.db.models.City;

public class CityService extends AbstractJDBCDao<City>{
	
	public City getById(City city){
		String sql = "SELECT name FROM city WHERE id=? LIMIT 1";
		
		try (PreparedStatement ps = getConnection().prepareStatement(sql)){
			ps.setLong(1, city.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				city.setName(rs.getString(1));
			}
		} catch (SQLException e) {
			System.err.println(e);
		}

		return city;
	}
	
	@Override
	public String queryInsert() {
		return "INSERT INTO city (id, name) VALUES(?, ?);";
	}

	@Override
	protected void prepareInsert(PreparedStatement ps, City city) throws SQLException {
		ps.setLong(1, city.getId());
		ps.setString(2, city.getName());
	}
}
