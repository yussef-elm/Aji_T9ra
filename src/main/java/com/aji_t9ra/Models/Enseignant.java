package com.aji_t9ra.Models;

import java.util.List;

public class Enseignant extends User{
   
	private String description;
	private String organisme;
	private String niveau;
	private String[] listMatieres;
	
	
	public Enseignant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Enseignant(int id, String username, String password, String nom, String prenom, String email, boolean isAdmin,
			boolean isApproved) {
		super(id, username, password, nom, prenom, email, isAdmin, isApproved);
		// TODO Auto-generated constructor stub
	}
	public Enseignant(String description,String niveau, String organisme) {
		super();
		this.niveau=niveau;
		this.description = description;
		this.organisme = organisme;
	}
	
	

	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOrganisme() {
		return organisme;
	}
	public void setOrganisme(String organisme) {
		this.organisme = organisme;
	}
	public String[] getListMatieres() {
		return listMatieres;
	}
	public void setListMatieres(String[] listMatieres) {
		this.listMatieres = listMatieres;
	}


	
	
}
