package com.salazart.db.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.salazart.db.models.Target;
import com.salazart.db.utils.QueryDic;

public class TargetService extends AbstractJDBCDao<Target> {

	public void update(Target target) throws SQLException {
		try (PreparedStatement ps = getConnection().prepareStatement(
					QueryDic.TARGET_UPDATE_BY_SOCIAL_ID);){
			ps.setBoolean(1, target.getStatusTarget());
			ps.setLong(2, target.getSocialId());
			ps.executeUpdate();
		}
	}
	
	public Target getOneTargetByStatus() {
		try {
			Statement stat = getConnection().createStatement();
			ResultSet rs = stat.executeQuery(QueryDic.TARGET_BY_STATUS_TARGET);
			rs.next();
			return new Target(rs.getLong(1), rs.getBoolean(2));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return new Target();
		}
	}
	
	public Target getTargetBySocialId(long socialId) {
		try {
			PreparedStatement ps = getConnection().prepareStatement(QueryDic.TARGET_BY_SOCIAL_ID);
			ps.setLong(1, socialId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return new Target(rs.getLong(1), rs.getBoolean(2));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return new Target();
	}

	@Override
	public String queryInsert() {
		return QueryDic.TARGET_INSERT;
	}

	@Override
	protected void prepareInsert(PreparedStatement ps, Target target) throws SQLException {
		ps.setLong(1, target.getSocialId());
		ps.setBoolean(2, target.getStatusTarget());
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

}
