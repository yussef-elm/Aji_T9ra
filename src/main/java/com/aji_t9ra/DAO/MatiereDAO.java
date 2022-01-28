package com.aji_t9ra.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aji_t9ra.Models.Enseignant;
import com.aji_t9ra.Models.Matiere;

public class MatiereDAO extends AbstractDAO{

	public MatiereDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<Matiere> getMatiereByNiveau(String niveau) throws SQLException{
		List<Matiere> listMatiere=new ArrayList();
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
	
	

}
