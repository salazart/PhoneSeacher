package com.salazart.db.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.salazart.db.utils.QueryDic;
import com.salazart.out.models.OutUser;
import com.salazart.vk.models.VkUser;

public class UserService extends AbstractJDBCDao<VkUser>{

	public List<OutUser> getUsersJoinPhone(int minId, int maxId) {
		List<OutUser> users = new ArrayList<OutUser>();
		try {
			PreparedStatement ps = getConnection().prepareStatement(
					QueryDic.USERS_JOIN_PHONE_BY_IDS);
			ps.setInt(1, minId);
			ps.setInt(2, maxId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				OutUser outUser = new OutUser();
				outUser.setSocialId(rs.getString(1));
				outUser.setFirstName(rs.getString(2));
				outUser.setLastName(rs.getString(3));
				outUser.setBirthDay(rs.getString(4));
				outUser.setAddress(rs.getString(5));
				outUser.setPhone(rs.getString(6));
				users.add(outUser);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return users;
	}
	
	@Override
	public String queryInsert() {
		return "INSERT INTO user"
		    	+ " (socialId, firstName, lastName, birthDay, address, id)"
				+ "VALUES(?, ?, ?, ?, ?, ?);";
	}

	@Override
	protected void prepareInsert(PreparedStatement ps, VkUser vkUser) throws SQLException {
		ps.setLong(1, vkUser.getSocialId());
		ps.setString(2, vkUser.getFirstName());
		ps.setString(3, vkUser.getLastName());
		ps.setString(4, vkUser.getBirthDay());
		ps.setString(5, vkUser.getCity());
		ps.setInt(6, vkUser.getId());
	}
	
	public int get(int id) throws SQLException {
		String sql = "SELECT id FROM user WHERE id>? LIMIT 1";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			return rs.getInt(1);
		}
		return 0;
	}
	
	public int getMaxId() throws SQLException {
		String sql = "SELECT MAX(id) FROM user;";
		try(ResultSet rs = getConnection().createStatement().executeQuery(sql)){
			if(rs.next()){
				return rs.getInt(1);
			}
		}
		return 0;
	}
	
	public void update(int id) throws SQLException {
		String sql = "UPDATE user SET id=?";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
	}

}
