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
		
		//TDO: Il faudra donc remplacer ses instructions suivants par les bonnes (avec les informations de la base de données des utilisateurs
		//vérifier si login est dans la base de données à l'aide d'une requête SQL
		//récupérer le mot de passe du login trouvé dans la BD
		
		//TDO: vérifier si login existe déjà dans la db si tel n'est pas le cas assigné renvoyer la valeur false et assigné à resultat le message approprié
		
		String mpBd = "12345";
		if (!mp.equals(mpBd)) {
			resultat = new String ("Email ou mot de pass incorrect");
			return false;
		}
		return true;
	}

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}
	
}
