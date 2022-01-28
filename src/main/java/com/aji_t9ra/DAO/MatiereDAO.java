package com.aji_t9ra.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aji_t9ra.Models.Enseignant;
import com.aji_t9ra.Models.Matiere;

public class MatiereDAO extends AbstractDAO {

	public MatiereDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Matiere> getMatiereByNiveau(String niveau) throws SQLException {
		List<Matiere> listMatiere = new ArrayList();
		String query = "select m.* from matiere m left join matiere_niveau c on m.id=c.ID_Matiere "
				+ "left join niveau_scolaire s on c.id_niveau_scolaire=s.id where s.niveau=?";
		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setString(1, niveau);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Matiere m = new Matiere();
			m.setId(rs.getInt("ID"));
			m.setNom(rs.getString("NOM"));
			listMatiere.add(m);
		}
		return listMatiere;
	}

	public List<Matiere> getMatieresbyNom(String[] matieres) throws SQLException {
		List<Matiere> lisMatieres = new ArrayList();
		String query = "select * from matiere where nom=?";
		PreparedStatement ps = this.getPrepareStatement(query);
		for (int i = 0; i < matieres.length; i++) {
			Matiere newM = new Matiere();
			ps.setString(1, matieres[i]);
			ResultSet rs = ps.executeQuery();
			rs.next();
			newM.setId(rs.getInt("id"));
			newM.setNom(rs.getString("nom"));
			lisMatieres.add(newM);
		}
		return lisMatieres;

	}

	public Matiere getMatiereById(int id) throws SQLException {

		Matiere m = new Matiere();
		String query = "select * from matiere where id=?";
		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		m.setId(rs.getInt("id"));
		m.setNom(rs.getString("nom"));
		return m;

	}

}
