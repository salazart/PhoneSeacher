package com.salazart.db.utils;

public class QueryDic {
    public static final String TARGET_CREATE = "CREATE TABLE IF NOT EXISTS target (socialId BIGINT, statusTarget BOOLEAN NOT NULL DEFAULT FALSE, PRIMARY KEY (socialId));";
    
    public static final String TARGET_DROP = "DROP TABLE IF EXISTS target";
    public static final String TARGET_INSERT = "INSERT INTO target (socialId, statusTarget) VALUES(?, ?);";
    public static final String TARGET_ALL = "SELECT * FROM target;";
    public static final String TARGET_BY_STATUS_TARGET = "SELECT socialId, statusTarget  FROM target WHERE statusTarget=FALSE LIMIT 1;";
    public static final String TARGET_BY_SOCIAL_ID = "SELECT socialId, statusTarget  FROM target WHERE socialId=? LIMIT 1;";
    public static final String TARGET_UPDATE_BY_SOCIAL_ID = "UPDATE target SET statusTarget=? WHERE socialId=?";
    public static final String TARGET_ALL_SOCIAL_ID = "SELECT socialId FROM target;";
    public static final String TARGET_UNHANDLE_COUNT = "SELECT count(socialId) FROM target WHERE statusTarget=FALSE;";
    public static final String TARGET_DELETE_BY_SOCIAL_ID = "DELETE FROM target WHERE socialId=?;";
    
    public static final String USER_CREATE = "CREATE TABLE IF NOT EXISTS user"
		+ " (socialId BIGINT(255), "
		+ "firstName VARCHAR(255), "
		+ "lastName VARCHAR(255), "
		+ "birthDay VARCHAR(255), "
		+ "address VARCHAR(255), "
		+ "PRIMARY KEY (socialId));";
    
    public static final String USER_JOIN_PHONE = "SELECT u.socialId, u.firstName, u.lastName, u.birthDay, u.address, "
    		+ " p.phone "
    		+ "FROM user u "
    		+ "RIGHT JOIN phone p ON p.socialId=u.socialId "
    		+ "WHERE u.socialId=?";
    
    public static final String USERS_JOIN_PHONE_BY_IDS = "SELECT u.socialId, u.firstName, u.lastName, u.birthDay, u.address, "
    		+ " p.phone "
    		+ "FROM user u "
    		+ "RIGHT JOIN phone p ON p.socialId=u.socialId "
    		+ "WHERE u.id>? AND u.id<?";
    
    public static final String USER_DROP = "DROP TABLE IF EXISTS user";
    
    public static final String USER_ALL = "SELECT * FROM user;";
    public static final String USER_ALL_SOCIAL_ID = "SELECT socialId FROM user;";
    
    public static final String USER_DELETE_BY_SOCIAL_ID = "DELETE FROM user WHERE socialId=?;";
    public static final String USER_BY_SOCIAL_ID = "SELECT socialId, firstName, lastName, birthDay, address "
    		+ "FROM user WHERE socialId=?;";
    
    public static final String PHONE_CREATE = "CREATE TABLE IF NOT EXISTS phone"
		+ " (id SERIAL AUTO_INCREMENT, "
		+ "socialId BIGINT, "
		+ "phone VARCHAR(30), "
		+ "PRIMARY KEY (id));";
    public static final String PHONE_DROP = "DROP TABLE IF EXISTS phone";
    public static final String PHONE_INSERT = "INSERT INTO phone"
	    	+ " (id, socialId, phone)"
		+ "VALUES(DEFAULT, ?, ?);";
    public static final String PHONE_ALL = "SELECT * FROM phone;";
    public static final String PHONE_BY_SOCIAL_ID = "SELECT id, phone, socialId FROM phone WHERE socialId=?;";
    public static final String PHONE_UPDATE = "UPDATE phone SET socialId=? WHERE id=?";
    
    public static final String CITY_CREATE = "CREATE TABLE IF NOT EXISTS city"
    		+ " (id BIGINT, name VARCHAR(100), PRIMARY KEY (id));";
    public static final String CITY_BY_ID = "SELECT id, name FROM city WHERE id=?;";
}
