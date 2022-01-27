package com.aji_t9ra.Models;

public class User {

	private int id;
	private String username;
	private String password;
	private String nom;
	private String prenom ;
	private String email;
	private boolean isAdmin;
	private boolean isApproved;
	private String role= null;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}



	public User(int id, String username, String password, String nom, String prenom, String email, boolean isAdmin,
			boolean isApproved) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.isAdmin = isAdmin;
		this.isApproved = isApproved;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public boolean isAdmin() {
		return isAdmin;
	}



	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}



	public boolean isApproved() {
		return isApproved;
	}



	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	
	
	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}

	
}
