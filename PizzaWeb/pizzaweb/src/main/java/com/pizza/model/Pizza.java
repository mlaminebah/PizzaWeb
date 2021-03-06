package com.pizza.model;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
	private int idPizza;
	private String nom;
	private float prix;
	private String photo;
	private String taille;
	private int qteStock;
	private List <String> ingredients = new ArrayList <> ();
	
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
	public int getQteStock() {
		return qteStock;
	}
	public void setQteStock(int qteStock) {
		this.qteStock = qteStock;
	}
	public List <String> getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredient) {
		this.ingredients.add(ingredient);
	}
	@Override
	public String toString() {
		String ingreds = "Pizza [idPizza=" + idPizza + ", nom=" + nom + ", prix=" + prix + "$, photo=" + photo + ", taille="
				+ taille + ", qteStock=" + qteStock + ", ingredients=[";
		for (String ing : ingredients) {
			ingreds += ","+ing;
		}
		return ingreds+"]]";
	}
}
