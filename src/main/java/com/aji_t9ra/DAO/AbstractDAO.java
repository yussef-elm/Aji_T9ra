package com.aji_t9ra.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.aji_t9ra.Tools.DBConnection;


public abstract class AbstractDAO {
	 private Connection connection;

	 AbstractDAO(){
	        this.connection = new DBConnection().connectSource();
	    }
	 
	  public Statement getStatement(){
	        try {
	            return this.connection.createStatement();
	        } catch (SQLException throwable) {
	            throwable.printStackTrace();
	        }
	        return null;
	    }
	  
	    
	   public PreparedStatement getPrepareStatement(String query){
	        try {
	       
	          return this.connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
	        } catch (SQLException throwable) {
	            throwable.printStackTrace();
	        }
	        return null;
	    }
	    

}
