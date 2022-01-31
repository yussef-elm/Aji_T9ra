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

	public int AddMatiere(Matiere newMatiere) throws SQLException {
		int id = 0;
		String query = "insert into matiere (`nom`, `description`, `categorie`) "
				+ "values (?,?,?)";
		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setString(1, newMatiere.getNom());
		ps.setString(2, newMatiere.getDescription());
		ps.setString(3, newMatiere.getCategorie());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next())
			id = rs.getInt(1);
		String query1 = "select id from niveau_scolaire where niveau=? ";
		PreparedStatement ps1 = this.getPrepareStatement(query1);
		ps1.setString(1, newMatiere.getNiveau());
		ResultSet rs1=ps1.executeQuery();
		rs1.next();int idNiveau=rs1.getInt("id");
		String query2 = "insert into matiere_niveau (`ID_Niveau_Scolaire`, `ID_MATIERE`) "
				+ "values (?,?)";
		PreparedStatement ps2 = this.getPrepareStatement(query2);
		ps2.setInt(1, idNiveau);
		ps2.setInt(2, id);
		ps2.executeUpdate();
		return id;
	}
	public void MatiereIndisponible (int id) throws SQLException {
		String query = "update  matiere set isAvailable=? where id=? ";
		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setInt(1,0);
		ps.setInt(2,id);
		ps.executeUpdate();

	}
	public void MatiereDisponible (int id) throws SQLException {
		String query = "update  matiere set isAvailable=? where id=? ";
		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setInt(1,1);
		ps.setInt(2,id);
		ps.executeUpdate();
	}
	public void UpdateMatiere(Matiere Matiere) throws SQLException {
		int id = 0;
		String query = "update  matiere set nom=?,description=?,categorie=? where id=? ";
		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setString(1, Matiere.getNom());
		ps.setString(2, Matiere.getDescription());
		ps.setString(3, Matiere.getCategorie());
		ps.setInt(4, Matiere.getId());
		ps.executeUpdate();
		String query1 = "select id from niveau_scolaire where niveau=? ";
		PreparedStatement ps1 = this.getPrepareStatement(query1);
		ps1.setString(1, Matiere.getNiveau());
		ResultSet rs1=ps1.executeQuery();
		rs1.next();int idNiveau=rs1.getInt("id");
		String query2 = "update matiere_niveau set ID_Niveau_Scolaire=? where ID_MATIERE=?";
		PreparedStatement ps2 = this.getPrepareStatement(query2);
		ps2.setInt(1, idNiveau);
		ps2.setInt(2, id);
		ps2.executeUpdate();
	}
	
	public List<Matiere> getMatiereByNiveau(String niveau) throws SQLException {
		List<Matiere> listMatiere = new ArrayList();
		String query = "select m.* from matiere m left join matiere_niveau c on m.id=c.ID_Matiere "
				+ "left join niveau_scolaire s on c.id_niveau_scolaire=s.id where s.niveau=? and m.isAvailable=?";
		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setString(1, niveau);
		ps.setInt(2, 1);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Matiere m = new Matiere();
			m.setId(rs.getInt("ID"));
			m.setNom(rs.getString("NOM"));
			m.setDescription(rs.getString("Description"));
			m.setAvailable(rs.getBoolean("isAvailable"));
			listMatiere.add(m);
		}
		return listMatiere;
	}
	public List<Matiere> getUnavailableMatiereByNiveau(String niveau) throws SQLException {
		List<Matiere> listMatiere = new ArrayList();
		String query = "select m.* from matiere m left join matiere_niveau c on m.id=c.ID_Matiere "
				+ "left join niveau_scolaire s on c.id_niveau_scolaire=s.id where s.niveau=? and m.isAvailable=?";
		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setString(1, niveau);
		ps.setInt(2, 0);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Matiere m = new Matiere();
			m.setId(rs.getInt("ID"));
			m.setNom(rs.getString("NOM"));
			m.setDescription(rs.getString("Description"));
			m.setAvailable(rs.getBoolean("isAvailable"));
			listMatiere.add(m);
		}
		return listMatiere;
	}
	public List<Matiere> getMatieresbyNom(String[] matieres) throws SQLException {
		List<Matiere> lisMatieres = new ArrayList();
		String query = "select * from matiere where nom=? and isAvailable=?";
		PreparedStatement ps = this.getPrepareStatement(query);
		for (int i = 0; i < matieres.length; i++) {
			Matiere newM = new Matiere();
			ps.setString(1, matieres[i]);
			ps.setInt(2, 1);
			ResultSet rs = ps.executeQuery();
			rs.next();
			newM.setId(rs.getInt("id"));
			newM.setNom(rs.getString("nom"));
			newM.setDescription(rs.getString("Description"));
			newM.setAvailable(rs.getBoolean("isAvailable"));
			lisMatieres.add(newM);
		}
		return lisMatieres;

	}

	public List<Matiere> getMatiereByNiveauAndCategorie(String categorie,String niveau) throws SQLException {
		List<Matiere> listMatieres = new ArrayList();
		String query = "select m.* from matiere m left join matiere_niveau c on m.id=c.ID_Matiere "
				+ "left join niveau_scolaire s on c.id_niveau_scolaire=s.id where s.niveau=? and m.categorie=? and m.isAvailable=?";
		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setString(2, categorie);
		ps.setString(1, niveau);
		ps.setInt(3, 1);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Matiere m = new Matiere();
			m.setId(rs.getInt("ID"));
			m.setNom(rs.getString("NOM"));
			m.setDescription(rs.getString("Description"));
			m.setAvailable(rs.getBoolean("isAvailable"));
			listMatieres.add(m);
		}
		return listMatieres;

	}
	
	public List<Matiere> getMatiereUnavailableByNiveauAndCategorie(String categorie,String niveau) throws SQLException {
		List<Matiere> listMatieres = new ArrayList();
		String query = "select m.* from matiere m left join matiere_niveau c on m.id=c.ID_Matiere "
				+ "left join niveau_scolaire s on c.id_niveau_scolaire=s.id where s.niveau=? and m.categorie=? and m.isAvailable=?";
		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setString(2, categorie);
		ps.setString(1, niveau);
		ps.setInt(3, 0);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Matiere m = new Matiere();
			m.setId(rs.getInt("ID"));
			m.setNom(rs.getString("NOM"));
			m.setDescription(rs.getString("Description"));
			m.setAvailable(rs.getBoolean("isAvailable"));
			listMatieres.add(m);
		}
		return listMatieres;

	}
	
	public List<Matiere> getMatiereEnseignant(int id,String niveau) throws SQLException {
		List<Matiere> listMatieres = new ArrayList();
		String query="select m.*,s.niveau from matiere m  left join matiere_niveau c on m.id=c.ID_Matiere "
				+ "left join niveau_scolaire s on c.id_niveau_scolaire=s.id "
				+ "where s.niveau=? and m.isAvailable=? and "
				+ "exists (select 1 from ens_matiere en where en.id_enseignant=? and en.id_matiere=m.id )";

		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setString(1,niveau);
		ps.setInt(2, 1);
		ps.setInt(3, id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Matiere m = new Matiere();
			m.setId(rs.getInt("ID"));
			m.setNom(rs.getString("NOM"));
			m.setDescription(rs.getString("Description"));
			m.setAvailable(rs.getBoolean("isAvailable"));
			m.setNiveau(rs.getString("niveau"));
			listMatieres.add(m);
		}
		return listMatieres;

	}
	
	public List<Matiere> getMatiereDisponibleByEnseignantAndCategorie(Enseignant e,String categorie) throws SQLException {
		List<Matiere> listMatieres = new ArrayList();
		String query = " select m.*,s.niveau from matiere m  left join matiere_niveau c on m.id=c.ID_Matiere"
				+ " left join niveau_scolaire s on c.id_niveau_scolaire=s.id "
				+ "where s.niveau=? and m.categorie=? and m.isAvailable=? and "
				+ "not exists (select 1 from ens_matiere en where en.id_enseignant=? and en.id_matiere=m.id );";
		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setString(1, e.getNiveau());
		ps.setString(2, categorie);
		ps.setInt(3, 1);
		ps.setInt(4, e.getId());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Matiere m = new Matiere();
			m.setId(rs.getInt("ID"));
			m.setNom(rs.getString("NOM"));
			m.setDescription(rs.getString("Description"));
			m.setAvailable(rs.getBoolean("isAvailable"));
			m.setNiveau(rs.getString("niveau"));
			listMatieres.add(m);
		}
		return listMatieres;

	}
	
	public List<Matiere> getMatiereDisponibleByEnseignant(Enseignant e) throws SQLException {
		List<Matiere> listMatieres = new ArrayList();
		String query = " select m.*,s.niveau from matiere m  left join matiere_niveau c on m.id=c.ID_Matiere"
				+ " left join niveau_scolaire s on c.id_niveau_scolaire=s.id "
				+ "where s.niveau=? and m.isAvailable=? and "
				+ "not exists (select 1 from ens_matiere en where en.id_enseignant=? and en.id_matiere=m.id );";
		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setString(1, e.getNiveau());
		ps.setInt(2, 1);
		ps.setInt(3, e.getId());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Matiere m = new Matiere();
			m.setId(rs.getInt("ID"));
			m.setNom(rs.getString("NOM"));
			m.setDescription(rs.getString("Description"));
			m.setAvailable(rs.getBoolean("isAvailable"));
			m.setNiveau(rs.getString("niveau"));
			listMatieres.add(m);
		}
		return listMatieres;

	}
	public Matiere getMatiereById(int id) throws SQLException {

		Matiere m = new Matiere();
		String query = "select m.*,n.niveau from matiere m left join matiere_niveau mn on m.id=mn.id_matiere "
				+ "left join niveau_scolaire n on mn.id_niveau_scolaire= n.id where m.id=?";
		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		m.setId(rs.getInt("id"));
		m.setNom(rs.getString("nom"));
		m.setCategorie(rs.getString("categorie"));
		m.setDescription(rs.getString("Description"));
		m.setNiveau(rs.getString("niveau"));
		m.setAvailable(rs.getBoolean("isAvailable"));
		return m;

	}
	
	public Matiere getMatiereByNom(String nom) throws SQLException {

		Matiere m = new Matiere();
		String query = "select * from matiere where nom=?";
		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setString(1, nom);
		ResultSet rs = ps.executeQuery();
		rs.next();
		m.setId(rs.getInt("id"));
		m.setNom(rs.getString("nom"));
		m.setDescription(rs.getString("Description"));
		m.setAvailable(rs.getBoolean("isAvailable"));
		return m;

	}
	
	public List<String> getCategories() throws SQLException
	{
    	List<String> categories =new ArrayList<>();
        String query = "select distinct categorie from matiere";
        PreparedStatement ps = this.getPrepareStatement(query); 
        ResultSet rs  = ps.executeQuery();
        while(rs.next())
        {  String c=rs.getString("categorie");
           categories.add(c);
        }
		return categories; 
	}
	public void DeleteMatiereEnseignant(int idMatiere,int idEnseignant) throws SQLException
	{
        String query = "delete from ens_matiere where id_matiere=? and id_enseignant=?";
        PreparedStatement ps = this.getPrepareStatement(query); 
        ps.setInt(1, idMatiere);
        ps.setInt(2, idEnseignant);
        ps.executeUpdate();

	}
	public void AddMatiereEnseignant(int idMatiere,int idEnseignant) throws SQLException
	{
        String query = "insert into ens_matiere  (`ID_Matiere`, `ID_User_ENSEIGNANT`, `ID_ENSEIGNANT`) VALUES	(?,?,?)";
        PreparedStatement ps = this.getPrepareStatement(query); 
        ps.setInt(1, idMatiere);
        ps.setInt(2, idEnseignant);
        ps.setInt(3, idEnseignant);
        ps.executeUpdate();

	}
	public List<String> getNiveaux() throws SQLException
	{
    	List<String> niveaux =new ArrayList<>();
        String query = "select distinct niveau from niveau_scolaire order by id desc";
        PreparedStatement ps = this.getPrepareStatement(query); 
        ResultSet rs  = ps.executeQuery();
        while(rs.next())
        {  String c=rs.getString("niveau");
        niveaux.add(c);
        }
		return niveaux; 
	}
	

}
