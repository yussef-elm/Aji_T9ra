package com.aji_t9ra.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aji_t9ra.Models.User;

public class UserDAO extends AbstractDAO {
	private LoginDAO loginDao = new LoginDAO();

	public UserDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int AddUser(User user) throws SQLException {
        int id=0;
		if (loginDao.login(user.getEmail(), user.getPassword()) == null) {

			String query = "insert into user (`USERNAME`, `Password`, `NOM`, `PRENOM`, `EMAIL`) "
					+ "values (?,?,?,?,?)";

			PreparedStatement ps = this.getPrepareStatement(query);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getNom());
			ps.setString(4, user.getPrenom());
			ps.setString(5, user.getEmail());
		    ps.executeUpdate();
		    ResultSet rs = ps.getGeneratedKeys();
		      if (rs.next()) 
		            id=rs.getInt(1); 
		}
		return id;
	}
	
	public List<User> getNewUsers() throws SQLException{
		List<User> listNewUsers = new ArrayList();
        String query = " select e.*,s.niveau from enseignant e "
        		+ "left join enseignant_niveau n on e.id=n.id_user_enseignant "
        		+ "left join niveau_scolaire s on n.id_niveau_scolaire=s.id";
        PreparedStatement ps = this.getPrepareStatement(query);
        ps.setInt(1, 0);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
        	User newUser=new User();
        	newUser.setId(rs.getInt("ID"));
        	newUser.setNom(rs.getString("nom"));
        	newUser.setPrenom(rs.getString("prenom"));
        	newUser.setUsername(rs.getString("username"));
        //	newUser.setEmail(rs.);
        }
		
		
		return listNewUsers;
	}

	

	
	public int nombreDesacUser() throws SQLException {
		String query ="select count(*) from user where isApproved=? and isActive=?";
		PreparedStatement ps = this.getPrepareStatement(query);
        ps.setInt(1, 1);
        ps.setInt(2, 0);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int nbr=rs.getInt(1);
		return nbr;
	}
	
	
	
}
