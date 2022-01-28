package com.aji_t9ra.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aji_t9ra.Models.Enseignant;
import com.aji_t9ra.Models.Etudiant;

public class EtudiantDAO extends AbstractDAO {

	public EtudiantDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int AddEtudiant(Etudiant newEtudiant) throws SQLException
	{
		int id=0;
		String query = "insert into etudiant (`USERNAME`, `Password`, `NOM`, `PRENOM`, `EMAIL`,`ID_USER`,`ECOLE`,`DESCRIPTION`,`ID`,`ID_NIVEAU_SCOLAIRE`) "
				+ "values (?,?,?,?,?,?,?,?,?,?)";
        String query2= "select id from niveau_scolaire where niveau=?";
		PreparedStatement ps1 = this.getPrepareStatement(query2);
		ps1.setString(1, newEtudiant.getNiveau_scolaire());
		ResultSet rs1 = ps1.executeQuery();
		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setString(1, newEtudiant.getUsername());
		ps.setString(2, newEtudiant.getPassword());
		ps.setString(3, newEtudiant.getNom());
		ps.setString(4, newEtudiant.getPrenom());
		ps.setString(5, newEtudiant.getEmail());
		ps.setInt(6, newEtudiant.getId());
		ps.setString(7, newEtudiant.getEcole());
		ps.setString(8, newEtudiant.getDescription());
		ps.setInt(9, newEtudiant.getId());
		rs1.next();
		ps.setInt(10, rs1.getInt(1));

	    ps.executeUpdate();
	    
	    ResultSet rs = ps.getGeneratedKeys();
	    if (rs.next()) 
		       id =rs.getInt(1);
		return id;
	}
	
	public List<Etudiant> getEtudiants() throws SQLException
	{
		 List<Etudiant> listEtudiant=new ArrayList();
		 String query = "  select e.*,n.niveau from etudiant e left join "
		 		+ "niveau_scolaire n on e.ID_NIVEAU_SCOLAIRE=n.id where e.isApproved=? and isActive=?";
	        PreparedStatement ps = this.getPrepareStatement(query);
	        ps.setInt(1, 1);
	        ps.setInt(2,1);
	        ResultSet rs = ps.executeQuery();
	        while(rs.next())
	        {
	        	Etudiant newEtu= new Etudiant();
	        	newEtu.setId(rs.getInt("ID"));
	        	newEtu.setNom(rs.getString("NOM"));
	        	newEtu.setPrenom(rs.getString("PRENOM"));
	        	newEtu.setEmail(rs.getString("EMAIL"));
	        	newEtu.setUsername(rs.getString("USERNAME"));
	        	newEtu.setNiveau_scolaire(rs.getString("niveau"));
	        	newEtu.setEcole(rs.getString("ecole"));
	        	newEtu.setDescription(rs.getString("description"));
	        	newEtu.setApproved(true);
	        	newEtu.setRole("etudiant");
	        	newEtu.setAdmin(rs.getBoolean("isAdmin"));
	        	newEtu.setActive(rs.getBoolean("isActive"));
               
              listEtudiant.add(newEtu); 
	        	
	        }
	        
	        
		return listEtudiant;
	}
	public List<Etudiant> getDescativeEtudiants() throws SQLException
	{
		 List<Etudiant> listEtudiant=new ArrayList();
		 String query = "  select e.*,n.niveau from etudiant e left join "
		 		+ "niveau_scolaire n on e.ID_NIVEAU_SCOLAIRE=n.id where e.isApproved=? and isActive=?";
	        PreparedStatement ps = this.getPrepareStatement(query);
	        ps.setInt(1, 1);
	        ps.setInt(2,0);
	        ResultSet rs = ps.executeQuery();
	        while(rs.next())
	        {
	        	Etudiant newEtu= new Etudiant();
	        	newEtu.setId(rs.getInt("ID"));
	        	newEtu.setNom(rs.getString("NOM"));
	        	newEtu.setPrenom(rs.getString("PRENOM"));
	        	newEtu.setEmail(rs.getString("EMAIL"));
	        	newEtu.setUsername(rs.getString("USERNAME"));
	        	newEtu.setNiveau_scolaire(rs.getString("niveau"));
	        	newEtu.setEcole(rs.getString("ecole"));
	        	newEtu.setDescription(rs.getString("description"));
	        	newEtu.setApproved(true);
	        	newEtu.setRole("etudiant");
	        	newEtu.setAdmin(rs.getBoolean("isAdmin"));
	        	newEtu.setActive(rs.getBoolean("isActive"));
               
              listEtudiant.add(newEtu); 
	        	
	        }
	        
	        
		return listEtudiant;
	}
	
	public List<Etudiant> getNewEtudiants() throws SQLException{
		List<Etudiant> listNewEtudiant=new ArrayList();
		 String query = "  select e.*,n.niveau from etudiant e left join "
			 		+ "niveau_scolaire n on e.ID_NIVEAU_SCOLAIRE=n.id where e.isApproved=?";
	        PreparedStatement ps = this.getPrepareStatement(query);
	        ps.setInt(1, 0);
	        ResultSet rs = ps.executeQuery();
	        while(rs.next())
	        {
	        	Etudiant newEtu= new Etudiant();
	        	newEtu.setId(rs.getInt("ID"));
	        	newEtu.setNom(rs.getString("NOM"));
	        	newEtu.setPrenom(rs.getString("PRENOM"));
	        	newEtu.setEmail(rs.getString("EMAIL"));
	        	newEtu.setUsername(rs.getString("USERNAME"));
	        	newEtu.setNiveau_scolaire(rs.getString("niveau"));
	        	newEtu.setEcole(rs.getString("ecole"));
	        	newEtu.setDescription(rs.getString("description"));
	        	newEtu.setApproved(false);
	        	newEtu.setAdmin(false);
	        	newEtu.setActive(false);
                
               listNewEtudiant.add(newEtu); 
	        	
	        }
	        
	        
		return listNewEtudiant;
	}
	
	
	public Etudiant getEtudiantByID(int id) throws SQLException {
		Etudiant etd = new Etudiant();
		String query = "  select e.*,n.niveau from etudiant e left join "
		 		+ "niveau_scolaire n on e.ID_NIVEAU_SCOLAIRE=n.id where e.id=?";
	        PreparedStatement ps = this.getPrepareStatement(query);
	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();
	        if(rs.next())
	        {
	        	etd.setId(rs.getInt("ID"));
	        	etd.setNom(rs.getString("NOM"));
	        	etd.setPrenom(rs.getString("PRENOM"));
	        	etd.setEmail(rs.getString("EMAIL"));
	        	etd.setUsername(rs.getString("USERNAME"));
	        	etd.setNiveau_scolaire(rs.getString("niveau"));
	        	etd.setEcole(rs.getString("ecole"));
	        	etd.setDescription(rs.getString("description"));
	        	etd.setApproved(rs.getBoolean("isApproved"));
	        	etd.setAdmin(rs.getBoolean("isAdmin"));
	        	etd.setActive(rs.getBoolean("isActive"));
	        	etd.setRole("etudiant");
   	        	
	        }
			
	      return etd;	
	}
	
	public void ApproverEtudiant(int id) throws SQLException {
		 String query = "update  user set isApproved=?,isActive=? where id=?";
		 String query1="update  etudiant set isApproved=?,isActive=? where id=?";
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
	public void desactiverEtudiant(int id) throws SQLException {
		 String query = "update  user set isActive=? where id=?";
		 String query1= "update  etudiant set isActive=? where id=?";
	        PreparedStatement ps = this.getPrepareStatement(query);
	        PreparedStatement ps1 = this.getPrepareStatement(query1);
	        ps.setInt(1, 0);
	        ps.setInt(2, id);
	        ps1.setInt(1, 0);
	        ps1.setInt(2, id);
	        ps.executeUpdate();
	        ps1.executeUpdate();
		}
	public void activerEtudiant(int id) throws SQLException {
		 String query = "update  user set isActive=? where id=?";
		 String query1= "update  etudiant set isActive=? where id=?";
	        PreparedStatement ps = this.getPrepareStatement(query);
	        PreparedStatement ps1 = this.getPrepareStatement(query1);
	        ps.setInt(1, 1);
	        ps.setInt(2, id);
	        ps1.setInt(1, 1);
	        ps1.setInt(2, id);
	        ps.executeUpdate();
	        ps1.executeUpdate();
		}
	public void supprimerEtudiant(int id) throws SQLException {
		 String query = "delete from  etudiant where id=?";
		 String query1= "delete from user where id=?";
	        PreparedStatement ps = this.getPrepareStatement(query);
	        PreparedStatement ps1 = this.getPrepareStatement(query1);
	        ps.setInt(1, id);
	        ps1.setInt(1, id);
	        ps.executeUpdate();  ps1.executeUpdate();

		}
	
	
	
}
