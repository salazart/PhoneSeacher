package com.salazart.db.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.salazart.db.models.Phone;
import com.salazart.db.models.User;
import com.salazart.db.utils.QueryDic;

public class PhoneService extends AbstractJDBCDao<Phone> {

	public List<Phone> getPhonesByUser(User user) {
		List<Phone> phones = new ArrayList<Phone>();
		try {
			PreparedStatement ps = getConnection().prepareStatement(
					QueryDic.PHONE_BY_SOCIAL_ID);
			ps.setLong(1, user.getSocialId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Phone phone = new Phone();
				phone.setId(rs.getInt(1));
				phone.setSocialId(rs.getLong(2));
				phone.setPhone(rs.getString(3));
				phones.add(phone);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		return phones;
	}

	@Override
	public String queryInsert() {
		return QueryDic.PHONE_INSERT;
	}

	@Override
	protected void prepareInsert(PreparedStatement ps, Phone phone) throws SQLException {
		ps.setLong(1, phone.getSocialId());
		ps.setString(2, phone.getPhone());
	}

}
