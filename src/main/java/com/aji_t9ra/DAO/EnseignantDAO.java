package com.aji_t9ra.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aji_t9ra.Models.Enseignant;

public class EnseignantDAO extends AbstractDAO {

	public EnseignantDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int AddEnseignant(Enseignant newEnseignant) throws SQLException
	{
		int id=0;
		String query = "insert into enseignant (`USERNAME`, `Password`, `NOM`, `PRENOM`, `EMAIL`,`ID_USER`,`ORGANISME`,`DESCRIPTION`,`ID`) "
				+ "values (?,?,?,?,?,?,?,?,?)";

		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setString(1, newEnseignant.getUsername());
		ps.setString(2, newEnseignant.getPassword());
		ps.setString(3, newEnseignant.getNom());
		ps.setString(4, newEnseignant.getPrenom());
		ps.setString(5, newEnseignant.getEmail());
		ps.setInt(6, newEnseignant.getId());
		ps.setString(7, newEnseignant.getOrganisme());
		ps.setString(8, newEnseignant.getDescription());
		ps.setInt(9, newEnseignant.getId());
	    ps.executeUpdate();
	    ResultSet rs = ps.getGeneratedKeys();
	     if (rs.next()) 
	       id =rs.getInt(1);
	     
	   String query2= "select id from niveau_scolaire where niveau=?";
       PreparedStatement ps1 = this.getPrepareStatement(query2);
	   ps1.setString(1, newEnseignant.getNiveau());
	   ResultSet rs1 = ps1.executeQuery();rs1.next();
	   String query3= "insert into enseignant_niveau (`ID_Niveau_Scolaire`, `ID_User_ENSEIGNANT`, `ID_ENSEIGNANT`) values (?,?,?)";
       PreparedStatement ps2 = this.getPrepareStatement(query3);
       ps2.setInt(1, rs1.getInt(1));
       ps2.setInt(2, newEnseignant.getId());
       ps2.setInt(3, newEnseignant.getId());
       ps2.executeUpdate();

		return id;
	}
	
	public List<Enseignant> getEnseignants() throws SQLException
	{
		List<Enseignant> listEnseignant=new ArrayList();
		 String query = " select e.*,s.niveau from enseignant e "
	        		+ "left join enseignant_niveau n on e.id=n.id_user_enseignant "
	        		+ "left join niveau_scolaire s on n.id_niveau_scolaire=s.id where e.isApproved=?";
	        PreparedStatement ps = this.getPrepareStatement(query);
	        ps.setInt(1, 1);
	        ResultSet rs = ps.executeQuery();
	        while(rs.next())
	        {
	        	Enseignant newEns= new Enseignant();
	        	newEns.setId(rs.getInt("ID"));
	        	newEns.setNom(rs.getString("NOM"));
	        	newEns.setPrenom(rs.getString("PRENOM"));
	        	newEns.setEmail(rs.getString("EMAIL"));
	        	newEns.setUsername(rs.getString("USERNAME"));
	        	newEns.setNiveau(rs.getString("Niveau"));
               newEns.setOrganisme(rs.getString("organisme"));
               newEns.setDescription(rs.getString("description"));
               newEns.setApproved(true);
               newEns.setRole("enseignant");
               newEns.setAdmin(rs.getBoolean("isAdmin"));
               newEns.setActive(rs.getBoolean("isActive"));
               
              listEnseignant.add(newEns); 
	        	
	        }
	        
	        
		return listEnseignant;
	}
	
	
	public List<Enseignant> getNewEnseignants() throws SQLException{
		List<Enseignant> listNewEnseignant=new ArrayList();
		 String query = " select e.*,s.niveau from enseignant e "
	        		+ "left join enseignant_niveau n on e.id=n.id_user_enseignant "
	        		+ "left join niveau_scolaire s on n.id_niveau_scolaire=s.id where e.isApproved=?";
	        PreparedStatement ps = this.getPrepareStatement(query);
	        ps.setInt(1, 0);
	        ResultSet rs = ps.executeQuery();
	        while(rs.next())
	        {
	        	Enseignant newEns= new Enseignant();
	        	newEns.setId(rs.getInt("ID"));
	        	newEns.setNom(rs.getString("NOM"));
	        	newEns.setPrenom(rs.getString("PRENOM"));
	        	newEns.setEmail(rs.getString("EMAIL"));
	        	newEns.setUsername(rs.getString("USERNAME"));
	        	newEns.setNiveau(rs.getString("Niveau"));
                newEns.setOrganisme(rs.getString("organisme"));
                newEns.setDescription(rs.getString("description"));
                newEns.setApproved(false);
                newEns.setAdmin(false);
                newEns.setActive(false);
                
               listNewEnseignant.add(newEns); 
	        	
	        }
	        
	        
		return listNewEnseignant;
	}
	
	
	public Enseignant getEnseignantByID(int id) throws SQLException {
		Enseignant ens = new Enseignant();
		 String query = " select e.*,s.niveau from enseignant e "
	        		+ "left join enseignant_niveau n on e.id=n.id_user_enseignant "
	        		+ "left join niveau_scolaire s on n.id_niveau_scolaire=s.id where e.id=?";
	        PreparedStatement ps = this.getPrepareStatement(query);
	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();
	        if(rs.next())
	        {
	        	ens.setId(rs.getInt("ID"));
	        	ens.setNom(rs.getString("NOM"));
	        	ens.setPrenom(rs.getString("PRENOM"));
	        	ens.setEmail(rs.getString("EMAIL"));
	        	ens.setUsername(rs.getString("USERNAME"));
	        	ens.setNiveau(rs.getString("niveau"));
	        	ens.setOrganisme(rs.getString("organisme"));
	        	ens.setDescription(rs.getString("description"));
	        	ens.setApproved(rs.getBoolean("isApproved"));
	        	ens.setAdmin(rs.getBoolean("isAdmin"));
	        	ens.setActive(rs.getBoolean("isActive"));
	        	ens.setRole("enseignant");
   	        	
	        }
			
	      return ens;	
	}
	
	public void ApproverEnseignant(int id) throws SQLException {
		 String query = "update  user set isApproved=?,isActive=? where id=?";
		 String query1="update  enseignant set isApproved=?,isActive=? where id=?";
	        PreparedStatement ps = this.getPrepareStatement(query);
	        PreparedStatement ps1 = this.getPrepareStatement(query1);
	        ps.setInt(1, 1);
	        ps.setInt(2, 1);
	        ps.setInt(3, id);
	        ps1.setInt(1, 1);
	        ps1.setInt(2, 1);
	        ps1.setInt(3, id);
	        ps.executeUpdate();
	        ps1.executeUpdate();

		}
	public void desactiverEnseignant(int id) throws SQLException {
		 String query = "update  user set isActive=? where id=?";
		 String query1= "update  enseignant set isActive=? where id=?";
	        PreparedStatement ps = this.getPrepareStatement(query);
	        PreparedStatement ps1 = this.getPrepareStatement(query1);
	        ps.setInt(1, 0);
	        ps.setInt(2, id);
	        ps1.setInt(1, 0);
	        ps1.setInt(2, id);
	        ps.executeUpdate();
	        ps1.executeUpdate();
		}
	public void activerEnseignant(int id) throws SQLException {
		 String query = "update  user set isActive=? where id=?";
		 String query1= "update  enseignant set isActive=? where id=?";
	        PreparedStatement ps = this.getPrepareStatement(query);
	        PreparedStatement ps1 = this.getPrepareStatement(query1);
	        ps.setInt(1, 1);
	        ps.setInt(2, id);
	        ps1.setInt(1, 1);
	        ps1.setInt(2, id);
	        ps.executeUpdate();
	        ps1.executeUpdate();
		}
	public void supprimerEnseignant(int id) throws SQLException {
		 String query = "delete from  enseignant where id=?";
		 String query1= "delete from user where id=?";
		 String query2= "delete from  enseignant_niveau id_user_enseignant=?";
	        PreparedStatement ps = this.getPrepareStatement(query);
	        PreparedStatement ps1 = this.getPrepareStatement(query1);
	        PreparedStatement ps2 = this.getPrepareStatement(query2);
	        ps.setInt(1, id);
	        ps1.setInt(1, id);
	        ps2.setInt(1, id);
	        ps.executeUpdate();  ps1.executeUpdate();ps2.executeUpdate();

		}

}
