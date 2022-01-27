package com.aji_t9ra.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
}
