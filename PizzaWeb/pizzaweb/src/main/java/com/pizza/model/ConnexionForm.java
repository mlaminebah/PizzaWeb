package com.pizza.model;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author minesabry
 *
 *Cette classe vérifie les données envoyées par le client pour établir une connexion et en fonction de cela un message d'erreur 
 *est édité ou pas
 */
public class ConnexionForm {
	private String resultat;

	/***
	 * 
	 * @param request
	 * @return true si les identifiants sont bons ce qui nous permettra de savoir s'il faut se rédiriger vers la page du client 
	 */
	public boolean verifierIdentifiantsCon (HttpServletRequest request) {
		String email = request.getParameter("email");
		String mp = request.getParameter("password");
		
		//TDO:
		//1-se connecter à la base de données mysql
		//2-véfrifier que le login existe 
		//3-si oui verifier que le mot de pass est le bon
		//4-si le mot de passe fournit diffère de celui enregistré renvoyé 
		String mpBd = "12345";//à changer par le mot de récupérer dans la base
		if (!mp.equals(mpBd)) {
			resultat = new String ("Email ou mot de pass incorrect");
			return false;
		}
		//5-si l'identifiant n'existe dans la base de donées pas alors 
		/****
		 * resultat = new String ("Compte inexistant")
		 * return false;
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
