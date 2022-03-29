package com.pizza.model;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.util.Util;
/*****
 * 
 * @author minesabry
 * Dans cette classe on s'occupe de notre panier
 * on enregistre les pizza qu'il souhaite commander dans un cookie
 *
 */
public class Panier {
	private static Panier instance = null;
	private static HashMap<Integer,PizzaPanier> panier;
	private Connection connexion;
	private String resultat;
	
	private  Panier () {
		panier = new HashMap<>();
	}
	
	public static Panier getInstance () {
		if (instance == null) instance = new Panier();
		return instance;
	}
	//ajouter une pizza dans le panier
	public void ajouterPizzaAuPanier (HttpServletRequest request,HttpServletResponse response) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
		//on recharge la base de données
		ChargerDatatBase ();
		ListeDesPizzas nosPizzas = new ListeDesPizzas(connexion);
		//on envoie la liste des pizzas à la vue
		nosPizzas.affichePizzas();
		
		request.setAttribute("pizzas", nosPizzas.getPizzas());
		PizzaPanier mapizza = new PizzaPanier (Integer.parseInt(request.getParameter("idPizza")), 
				request.getParameter("nom"), Float.parseFloat(request.getParameter("prix")), 
				request.getParameter("photo"), request.getParameter("taille"),request.getParameter("ingredients"));
			
		//Avant d'ajouter la pizza u panier on vérifie si la quantité en stock est suffisante
		if (Integer.parseInt(request.getParameter("qteStock"))  <= 0) {
			resultat = "Cette pizza est finie pour l'instant vous ne pourrez plus l'ajouter";
		} else {
			if (!panier.containsKey(mapizza.getIdPizza())) {
				mapizza.calcul();
				panier.put(mapizza.getIdPizza(), mapizza);
			} else {
				if (Integer.parseInt(request.getParameter("qteStock")) > panier.get(mapizza.getIdPizza()).getQte())
					panier.get(mapizza.getIdPizza()).calcul();
				else 
					resultat = new String ("Oups nous n'avons que "+Integer.parseInt(request.getParameter("qteStock"))+" pour cette pizza, vous ne pouvez plus en rajouter plus");
			}
			totalCommande ();
		}
		Uils ut = new Uils("");
		System.out.println(ut.convertWithIteration(panier));
		
		
		
		
		//on envoie le panier à la vue par le biais du controleur
		//on stocke les informations dans un cookie
		//request.setAttribute("panier",panier);
		request.getSession().setAttribute("panier", panier);
		request.setAttribute("resultat", resultat);
		request.setAttribute("prixTotalCommande", totalCommande());
		
		
	}
	///Affiche le contenu du panier
	public void affiche () {
		for (Integer k : panier.keySet()) {
			PizzaPanier p =panier.get(k);
			System.out.println(p.MytoString());
		}
	}
	//charger la base de donnée
	private void ChargerDatatBase () throws ClassNotFoundException, SQLException {
		connexion = ConnexionBDChargeDriver.getInstance().getConnexion();
	}
	
	//Calcul le prix total de la commande à chaque fois qu'on ajoute une pizza au panier
	public float totalCommande () {
		float prixTotalCommande = 0;
		for (Integer k : panier.keySet()) {
			PizzaPanier p =panier.get(k);
			prixTotalCommande += p.getPrixTotal();
		}
		return prixTotalCommande;
	}
	//vide le panier
	public void videPanier () {
		panier.clear();
	}

	public static HashMap<Integer, PizzaPanier> getPanier() {
		return panier;
	}

	public static void setPanier(HashMap<Integer, PizzaPanier> panier) {
		Panier.panier = panier;
	}
	
}
