package com.aji_t9ra.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aji_t9ra.Models.User;

public class LoginDAO extends AbstractDAO {

	public LoginDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    public User login(String email, String password) {
    	User user = null;

        try {
            String query = "select * from user where email=? and password=?";
            PreparedStatement ps = this.getPrepareStatement(query); 
            ps.setString(1,email);
            ps.setString(2, password);
            ResultSet rs  = ps.executeQuery();
            if(rs.next()){
            	user = new User();
            	user.setId(rs.getInt("id"));
            	user.setUsername(rs.getString("username"));
            	user.setNom(rs.getString("nom"));
            	user.setPrenom(rs.getString("prenom"));
            	user.setEmail(rs.getString("email"));
            	user.setPassword(rs.getString("password"));
            	user.setAdmin(rs.getBoolean("isAdmin"));
            	user.setActive(rs.getBoolean("isActive"));

            	if(!user.isAdmin() ) {
                    if( isEtudiant(user)) {
            		user.setRole("etudiant");
                    }else {
                		user.setRole("enseignant");
                    }
            	}else {
            		user.setRole("admin");
            	}
            	user.setApproved(rs.getBoolean("isApproved"));
            	 	
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    public boolean isEtudiant(User user) throws SQLException {
        String query = "select * from Etudiant where id_user=?";
        PreparedStatement ps = this.getPrepareStatement(query); 
        ps.setInt(1,user.getId());
        ResultSet rs  = ps.executeQuery();
        if(rs.next())
        	return true;
        else
        	return false;
    }
    public boolean isEtudiant(int id) throws SQLException {
        String query = "select * from Etudiant where id_user=?";
        PreparedStatement ps = this.getPrepareStatement(query); 
        ps.setInt(1,id);
        ResultSet rs  = ps.executeQuery();
        if(rs.next())
        	return true;
        else
        	return false;
    }
    public boolean isEnseignant(User user) throws SQLException {
        String query = "select * from Enseignant where id_user=?";
        PreparedStatement ps = this.getPrepareStatement(query); 
        ps.setInt(1,user.getId());
        ResultSet rs  = ps.executeQuery();
        if(rs.next())
        	return true;
        else
        	return false;
    }
    
    public boolean isEnseignant(int id) throws SQLException {
        String query = "select * from Enseignant where id_user=?";
        PreparedStatement ps = this.getPrepareStatement(query); 
        ps.setInt(1,id);
        ResultSet rs  = ps.executeQuery();
        if(rs.next())
        	return true;
        else
        	return false;
    }
    
    public boolean isAdmin(int id) throws SQLException {
        String query = "select * from user where id=? and isAdmin=?";
        PreparedStatement ps = this.getPrepareStatement(query); 
        ps.setInt(1,id);
        ps.setInt(1,1);
        ResultSet rs  = ps.executeQuery();
        if(rs.next())
        	return true;
        else
        	return false;
    }
    
}
