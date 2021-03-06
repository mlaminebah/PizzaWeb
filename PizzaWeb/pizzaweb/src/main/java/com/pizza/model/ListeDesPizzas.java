package com.pizza.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ListeDesPizzas {
	private ArrayList <Pizza> pizzas;
	
	public ListeDesPizzas (Connection connexion) throws SQLException {
		pizzas = new ArrayList<>();
		Statement statement = connexion.createStatement();
		//Dans ce requête on recupère toutes nos pizzas
		String pizzasAvecIngredients = "SELECT * from Pizza ORDER BY IDPizza ASC;";
		ResultSet resultat = statement.executeQuery(pizzasAvecIngredients);
		
		
		while (resultat.next()) {
			Pizza pizza = new Pizza ();
			pizza.setIdPizza(Integer.parseInt(resultat.getString("IDPizza")));
			pizza.setNom(resultat.getString("Nom"));
			pizza.setPhoto(resultat.getString("Photo"));
			pizza.setTaille(resultat.getString("Taille"));
			pizza.setPrix(Float.parseFloat(resultat.getString("Prix")));
			pizza.setQteStock(Integer.parseInt(resultat.getString("QteStock")));
			//Récupération des ingrédients de la pizza courante
			String requeteIngredients = "SELECT DISTINCT I.Nom from Pizza P JOIN Composition C ON (C.IDPizza = P.IDPizza) \n"
					+ "JOIN Ingredients I ON (I.IDIngreds = C.IDIngreds)  WHERE P.IDPizza = ?;";
			PreparedStatement stateIngred = connexion.prepareStatement(requeteIngredients);
			stateIngred.setInt(1, pizza.getIdPizza());
			ResultSet ingredients = stateIngred.executeQuery();
			
			
			while (ingredients.next()) {
				pizza.setIngredients(ingredients.getString("Nom"));
			}
			pizzas.add(pizza);
		}
	}
	
	//recupère notre lise de Pizza
	public ArrayList<Pizza> getPizzas() {
		return pizzas;
	}
	
	public void affichePizzas () {
		for (Pizza p : pizzas) {
			System.out.println(p.toString());
		}
	}
}
