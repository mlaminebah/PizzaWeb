package com.pizza.model;

import java.util.ArrayList;
import java.util.List;

public class PizzaPanier {
	private int idPizza;
	private String nom;
	private float prix;
	private String photo;
	private String taille;
	private int  qte = 0;
	private float  prixTotal;
	private String ingredients;
	
	public PizzaPanier(int idPizza, String nom, float prix, String photo, String taille,String ingredients) {
		super();
		this.idPizza = idPizza;
		this.nom = nom;
		this.prix = prix;
		this.photo = photo;
		this.taille = taille;
		this.ingredients = ingredients;
	}
	
	public void calcul () {
		qte ++;
		prixTotal = qte*prix;
	}
	public int getQte() {
		return qte;
	}
	public float getPrixTotal () {
		return prixTotal;
	}
	
	public String MytoString() {
		return "PizzaPanier [idPizza=" + idPizza + ", nom=" + nom + ", prix=" + prix + "$, photo=" + photo + ", taille="
				+ taille + ", qte=" + qte + ", prixTotal=" + prixTotal + " $]";
	}
	public String toString () {
		return nom+","+prix+","+""+photo+","+taille+","+qte+","+prixTotal;
	}
	public int getIdPizza() {
		return idPizza;
	}

	public void setIdPizza(int idPizza) {
		this.idPizza = idPizza;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getTaille() {
		return taille;
	}

	public void setTaille(String taille) {
		this.taille = taille;
	}

}
