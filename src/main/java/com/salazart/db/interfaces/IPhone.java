package com.salazart.db.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.salazart.db.models.Phone;
import com.salazart.db.models.User;

public interface IPhone {
    List<Phone> getPhonesByUser(User user);

	void insert(Phone phone)  throws SQLException;

}