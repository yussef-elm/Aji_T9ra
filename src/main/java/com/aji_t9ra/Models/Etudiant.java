package com.aji_t9ra.Models;

public class Etudiant extends User{
	
	private String niveau_scolaire;
	private String ecole;
	private String description;
	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Etudiant(int id, String username, String password, String nom, String prenom, String email, boolean isAdmin,
			boolean isApproved) {
		super(id, username, password, nom, prenom, email, isAdmin, isApproved);
		// TODO Auto-generated constructor stub
	}
	public Etudiant(String ecole,String niveau_scolaire, String description) {
		super();
		this.niveau_scolaire=niveau_scolaire;
		this.ecole = ecole;
		this.description = description;
	}


	public String getNiveau_scolaire() {
		return niveau_scolaire;
	}
	public void setNiveau_scolaire(String niveau_scolaire) {
		this.niveau_scolaire = niveau_scolaire;
	}
	public String getEcole() {
		return ecole;
	}
	public void setEcole(String ecole) {
		this.ecole = ecole;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	

}
