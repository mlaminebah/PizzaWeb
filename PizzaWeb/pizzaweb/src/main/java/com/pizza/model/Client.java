package com.pizza.model;
/****
 * 
 * @author minesabry
 * Cette classe sauvegarde dans un objet client les informations d'un client
 */
public class Client {
	private int id;
	private String nom;
	private String prenom;
	private String email;
	private String adresse;
	private String phone;
	private String motdePass;
	
	public String getMotdePass() {
		return motdePass;
	}
	public void setMotdePass(String motdePass) {
		this.motdePass = motdePass;
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
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", adresse=" + adresse
				+ ", phone=" + phone+"]";
	}
}
