package com.aji_t9ra.Models;

import java.util.List;

public class Matiere {

	private int id;
	private String nom;
	private String description;
	private boolean isAvailable;
	private String categorie;
	private String niveau;
	private List<Enseignant> listEnseignant;
	
	public Matiere() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Matiere(int id, String nom, String description, boolean isAvailable, String categorie) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.isAvailable = isAvailable;
		this.categorie = categorie;
	}



	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public List<Enseignant> getListEnseignant() {
		return listEnseignant;
	}

	public void setListEnseignant(List<Enseignant> listEnseignant) {
		this.listEnseignant = listEnseignant;
	}

	public String getDescription() {
		return description;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
}
