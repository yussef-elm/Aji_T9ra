package com.aji_t9ra.Models;

public class Matiere {

	private int id;
	private String nom;
	public Matiere() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Matiere(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
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
