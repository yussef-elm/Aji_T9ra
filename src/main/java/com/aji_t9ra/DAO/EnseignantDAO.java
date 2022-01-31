package com.aji_t9ra.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.aji_t9ra.Models.Enseignant;
import com.aji_t9ra.Models.Matiere;

public class EnseignantDAO extends AbstractDAO {

	private MatiereDAO matiereDao = new MatiereDAO();

	public EnseignantDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int AddEnseignant(Enseignant newEnseignant) throws SQLException {
		int id = 0;
		String query = "insert into enseignant (`USERNAME`, `Password`, `NOM`, `PRENOM`, `EMAIL`,`ID_USER`,`ORGANISME`,`DESCRIPTION`,`ID`) "
				+ "values (?,?,?,?,?,?,?,?,?)";
		String query0 = "insert into ens_matiere (ID_Matiere,ID_User_ENSEIGNANT,ID_ENSEIGNANT) " + "values (?,?,?)";
		PreparedStatement ps = this.getPrepareStatement(query);
		PreparedStatement ps0 = this.getPrepareStatement(query0);

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
		for (Matiere m : newEnseignant.getListMatieres()) {
			ps0.setInt(1, m.getId());
			ps0.setInt(2, newEnseignant.getId());
			ps0.setInt(3, newEnseignant.getId());
			ps0.executeUpdate();
		}
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next())
			id = rs.getInt(1);

		String query2 = "select id from niveau_scolaire where niveau=?";
		PreparedStatement ps1 = this.getPrepareStatement(query2);
		ps1.setString(1, newEnseignant.getNiveau());
		ResultSet rs1 = ps1.executeQuery();
		rs1.next();
		String query3 = "insert into enseignant_niveau (`ID_Niveau_Scolaire`, `ID_User_ENSEIGNANT`, `ID_ENSEIGNANT`) values (?,?,?)";
		PreparedStatement ps2 = this.getPrepareStatement(query3);
		ps2.setInt(1, rs1.getInt(1));
		ps2.setInt(2, newEnseignant.getId());
		ps2.setInt(3, newEnseignant.getId());
		ps2.executeUpdate();

		return id;
	}

	public List<Enseignant> getEnseignants() throws SQLException {
		List<Enseignant> listEnseignant = new ArrayList();
		String query = " select e.*,s.niveau from enseignant e "
				+ "left join enseignant_niveau n on e.id=n.id_user_enseignant "
				+ "left join niveau_scolaire s on n.id_niveau_scolaire=s.id where e.isApproved=? and isActive=?";
		String query1 = " select m.* from matiere m left join ens_matiere e on e.ID_Matiere=m.id "
				+ "left join enseignant ens on e.ID_ENSEIGNANT=ens.id where ens.id=?";
		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setInt(1, 1);
		ps.setInt(2, 1);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Enseignant newEns = new Enseignant();
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
			PreparedStatement ps1 = this.getPrepareStatement(query1);
			ps1.setInt(1, newEns.getId());
			ResultSet rs1 = ps1.executeQuery();
			List<Matiere> listMatiere = new ArrayList();
			while (rs1.next()) {
				Matiere m = new Matiere(rs1.getInt("id"), rs1.getString("NOM"), rs1.getString("Description"),
						rs1.getBoolean("isAvailable"), rs1.getString("categorie"));
				listMatiere.add(m);
			}
			newEns.setListMatieres(listMatiere);
			listEnseignant.add(newEns);
		}

		return listEnseignant;
	}

	public List<Enseignant> getDesactiveEnseignants() throws SQLException {
		List<Enseignant> listEnseignant = new ArrayList();
		String query = " select e.*,s.niveau from enseignant e "
				+ "left join enseignant_niveau n on e.id=n.id_user_enseignant "
				+ "left join niveau_scolaire s on n.id_niveau_scolaire=s.id where e.isApproved=? and isActive=?";
		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setInt(1, 1);
		ps.setInt(2, 0);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Enseignant newEns = new Enseignant();
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

	public List<Enseignant> getNewEnseignants() throws SQLException {
		List<Enseignant> listNewEnseignant = new ArrayList();
		String query = " select e.*,s.niveau from enseignant e "
				+ "left join enseignant_niveau n on e.id=n.id_user_enseignant "
				+ "left join niveau_scolaire s on n.id_niveau_scolaire=s.id where e.isApproved=?";
		String query1 = " select m.* from matiere m left join ens_matiere e on e.ID_Matiere=m.id "
				+ "left join enseignant ens on e.ID_ENSEIGNANT=ens.id where ens.id=?";
		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setInt(1, 0);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Enseignant newEns = new Enseignant();
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
			PreparedStatement ps1 = this.getPrepareStatement(query1);
			ps1.setInt(1, newEns.getId());
			ResultSet rs1 = ps1.executeQuery();
			List<Matiere> listMatiere = new ArrayList();
			while (rs1.next()) {
				Matiere m = new Matiere(rs1.getInt("id"), rs1.getString("NOM"), rs1.getString("Description"),
						rs1.getBoolean("isAvailable"), rs1.getString("categorie"));
				listMatiere.add(m);
			}
			newEns.setListMatieres(listMatiere);
			listNewEnseignant.add(newEns);

		}

		return listNewEnseignant;
	}

	public Enseignant getEnseignantByID(int id) throws SQLException {
		Enseignant ens = new Enseignant();
		String query = " select e.*,s.niveau from enseignant e "
				+ "left join enseignant_niveau n on e.id=n.id_user_enseignant "
				+ "left join niveau_scolaire s on n.id_niveau_scolaire=s.id where e.id=?";
		String query1 = " select m.* from matiere m left join ens_matiere e on e.ID_Matiere=m.id "
				+ "left join enseignant ens on e.ID_ENSEIGNANT=ens.id where ens.id=?";
		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
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
			PreparedStatement ps1 = this.getPrepareStatement(query1);
			ps1.setInt(1, id);
			ResultSet rs1 = ps1.executeQuery();
			List<Matiere> listMatiere = new ArrayList();
			while (rs1.next()) {
				Matiere m = new Matiere(rs1.getInt("id"), rs1.getString("NOM"), rs1.getString("Description"),
						rs1.getBoolean("isAvailable"), rs1.getString("categorie"));
				listMatiere.add(m);
			}
			ens.setListMatieres(listMatiere);
		}

		return ens;
	}

	public List<Enseignant> getEnseignantsByMatiere(String matiere) throws SQLException {
		List<Enseignant> listEnseignant = new ArrayList();
		String query = "select e.* from enseignant e " + "left join ens_matiere em on e.id=em.id_enseignant "
				+ "left join matiere m on em.id_matiere=m.id where m.nom=? and e.isApproved=? and e.isActive=?";
		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setString(1, matiere);
		ps.setInt(2, 1);
		ps.setInt(3, 1);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Enseignant Ens = new Enseignant();
			Ens.setId(rs.getInt("ID"));
			Ens.setNom(rs.getString("NOM"));
			Ens.setPrenom(rs.getString("PRENOM"));
			Ens.setEmail(rs.getString("EMAIL"));
			Ens.setUsername(rs.getString("USERNAME"));
			// Ens.setNiveau(rs.getString("Niveau"));
			Ens.setOrganisme(rs.getString("organisme"));
			Ens.setDescription(rs.getString("description"));
			Ens.setApproved(true);
			Ens.setRole("enseignant");
			Ens.setAdmin(rs.getBoolean("isAdmin"));
			Ens.setActive(rs.getBoolean("isActive"));

			listEnseignant.add(Ens);

		}

		return listEnseignant;
	}

	public List<Enseignant> getEnseignantsByNiveau(String niveau) throws SQLException {
		List<Enseignant> listEnseignant = new ArrayList();
		String query = "select e.*,n.niveau from enseignant e left join enseignant_niveau en on e.id=en.id_enseignant "
				+ "left join niveau_scolaire n on en.id_niveau_scolaire=n.id"
				+ " where n.niveau=? and e.isApproved=? and e.isActive=?";
		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setString(1, niveau);
		ps.setInt(2, 1);
		ps.setInt(3, 1);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Enseignant Ens = new Enseignant();
			Ens.setId(rs.getInt("ID"));
			Ens.setNom(rs.getString("NOM"));
			Ens.setPrenom(rs.getString("PRENOM"));
			Ens.setEmail(rs.getString("EMAIL"));
			Ens.setUsername(rs.getString("USERNAME"));
			Ens.setNiveau(rs.getString("Niveau"));
			Ens.setOrganisme(rs.getString("organisme"));
			Ens.setDescription(rs.getString("description"));
			Ens.setApproved(true);
			Ens.setRole("enseignant");
			Ens.setAdmin(rs.getBoolean("isAdmin"));
			Ens.setActive(rs.getBoolean("isActive"));
			listEnseignant.add(Ens);

		}

		return listEnseignant;
	}

	public List<Enseignant> getEnseignantsPossible(String niveau, int idmatiere) throws SQLException {
		List<Enseignant> listEnseignant = new ArrayList();
		String query = "select e.*,n.niveau from enseignant e left join enseignant_niveau en "
				+ "on e.id=en.id_enseignant left join niveau_scolaire n on en.id_niveau_scolaire=n.id "
				+ "where n.niveau=? and e.isActive=? and isApproved=? and"
				+ " not exists(select 1 from ens_matiere en where en.id_enseignant=e.id and en.id_matiere=?)";
		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setString(1, niveau);
		ps.setInt(2, 1);
		ps.setInt(3, 1);
		ps.setInt(4, idmatiere);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Enseignant Ens = new Enseignant();
			Ens.setId(rs.getInt("ID"));
			Ens.setNom(rs.getString("NOM"));
			Ens.setPrenom(rs.getString("PRENOM"));
			Ens.setEmail(rs.getString("EMAIL"));
			Ens.setUsername(rs.getString("USERNAME"));
			Ens.setNiveau(rs.getString("Niveau"));
			Ens.setOrganisme(rs.getString("organisme"));
			Ens.setDescription(rs.getString("description"));
			Ens.setApproved(true);
			Ens.setRole("enseignant");
			Ens.setAdmin(rs.getBoolean("isAdmin"));
			Ens.setActive(rs.getBoolean("isActive"));
			listEnseignant.add(Ens);

		}

		return listEnseignant;
	}

	public void SupprimerEnseignantDeMatiere(int id, int idMatiere) throws SQLException {
		String query = "delete from ens_matiere where  id_enseignant=? and id_matiere=? ";
		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setInt(1, id);
		ps.setInt(2, idMatiere);
		ps.executeUpdate();
	}

	public void AjouterEnseignantAmatiere(int id, int idMatiere) throws SQLException {
		String query = "insert into ens_matiere(`ID_Matiere`, `ID_User_ENSEIGNANT`, `ID_ENSEIGNANT`) VALUES (?,?,?)";
		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setInt(1, idMatiere);
		ps.setInt(2, id);
		ps.setInt(3, id);
		ps.executeUpdate();
	}

	public void UpdateDescriptionEnseignant(int id, String description) throws SQLException {
		String query = "update  enseignant set Description=? where id=?";

		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setString(1, description);
		ps.setInt(2, id);
		ps.executeUpdate();

	}

	public void ApproverEnseignant(int id) throws SQLException {
		String query = "update  user set isApproved=?,isActive=? where id=?";
		String query1 = "update  enseignant set isApproved=?,isActive=? where id=?";
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
		String query1 = "update  enseignant set isActive=? where id=?";
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
		String query1 = "update  enseignant set isActive=? where id=?";
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
		String query1 = "delete from user where id=?";
		String query2 = "delete from  enseignant_niveau where id_user_enseignant=?";
		String query3 = "delete from ens_matiere where ID_ENSEIGNANT=?";
		PreparedStatement ps = this.getPrepareStatement(query);
		PreparedStatement ps1 = this.getPrepareStatement(query1);
		PreparedStatement ps2 = this.getPrepareStatement(query2);
		PreparedStatement ps3 = this.getPrepareStatement(query3);
		ps.setInt(1, id);
		ps1.setInt(1, id);
		ps2.setInt(1, id);
		ps3.setInt(1, id);
		ps3.executeUpdate();
		ps3.executeUpdate();
		ps.executeUpdate();
		ps1.executeUpdate();
	}

	public int nombreNosEnseignant() throws SQLException {
		String query = "select count(*) from enseignant where isApproved=? and isActive=?";
		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setInt(1, 1);
		ps.setInt(2, 1);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int nbr = rs.getInt(1);
		return nbr;
	}

	public int nombreDesacEnseignant() throws SQLException {
		String query = "select count(*) from enseignant where isApproved=? and isActive=?";
		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setInt(1, 1);
		ps.setInt(2, 0);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int nbr = rs.getInt(1);
		return nbr;
	}

	public int nombreNouveauEnseignant() throws SQLException {
		String query = "select count(*) from enseignant where isApproved=? and isActive=?";
		PreparedStatement ps = this.getPrepareStatement(query);
		ps.setInt(1, 0);
		ps.setInt(2, 0);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int nbr = rs.getInt(1);
		return nbr;
	}

}
