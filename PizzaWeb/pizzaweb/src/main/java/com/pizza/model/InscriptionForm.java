package com.pizza.model;

import javax.servlet.http.HttpServletRequest;

/****
 * 
 * @author 
 *	Cette classe gère l'inscription d'un utilisateur en insérant les données renseignées par celui dans la base de données
 *
 */
public class InscriptionForm {
	private String resultat;
	
	/****
	 * 
	 * @param request
	 * @return true si aucun problème n'est signalée avec l'inscription
	 * false si l'utilisateur existe déjà ou si les champs ne respectent pas une certaines longueur et que les deux mots de passes fournis sont différents
	 * 
	 */
	public boolean verifierIdentifiantsCon (HttpServletRequest request) {
		
		//on recupère les champs
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String adresse = request.getParameter("adress");
		String mp = request.getParameter("mp");
		String mpc = request.getParameter("mpc");
		//on vérifie que les champs répondent aux critères
		if (nom.length() < 3 || prenom.length() < 3) {
			resultat = new String ("Nom et Prénom doivent au moins être à 3 caractères");
			return false;
		} else if (phone.length() < 12) {
			resultat = new String ("Le format valide est : +33XXXXXXXXXX");
			return false;
		} else if (mp.length() < 6) {
			resultat = new String ("Le mot de passe doit être au moins : 6 caractères dont au moins 1 chiffre et caractère spécial");
			return false;
		} else if (!mp.equals(mpc)) {
			resultat = new String ("les 2 mots sont différents");
			return false;
		}
		//TDO : si toutes les connditions sont respectées : insérer donct les données dans la base de données 
		//Prendre en compte si l'utilisateur existe renvoyé une erreur en renvoyant false et un message result = new String (.....)
		/*****
		 * insertion des données dans la BD
		 */
		return true;
	}
	
	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}
	
}