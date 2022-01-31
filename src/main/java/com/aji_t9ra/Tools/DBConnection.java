package com.aji_t9ra.Tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {

	private Properties properties= new Properties();
	boolean isClear=false;
	private static Connection connection;


	public DBConnection() {
		// TODO Auto-generated constructor stub
		try {
			this.properties.load(DBConnection.class.getClassLoader().
					getResourceAsStream("application.properties"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
  public Connection connectSource() {
	try {
		Class.forName(properties.getProperty("jdbcPath"));
		String url="jdbc:"+properties.getProperty("protocolSource")+"://"+properties.getProperty("ipsource")+"/"+properties.getProperty("dbName")+"?useUnicode=true&characterEncoding=UTF-8";
		connection=DriverManager.getConnection(url,properties.getProperty("usernameSource"),properties.getProperty("passwordSource"));
        return connection;
        
        }
  catch(ClassNotFoundException | SQLException e) 
	    {
    	  e.printStackTrace();
        }
	return connection;
  }
}
