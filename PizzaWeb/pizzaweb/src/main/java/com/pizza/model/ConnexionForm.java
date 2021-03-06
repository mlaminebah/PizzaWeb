package com.pizza.model;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	//1-se connecter à la base de données mysql
	private Connection connexion;
	
	

	/***
	 * 
	 * @param request
	 * @return true si les identifiants sont bons ce qui nous permettra de savoir s'il faut se rédiriger vers la page du client 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws NoSuchAlgorithmException 
	 */
	public boolean verifierIdentifiantsCon (HttpServletRequest request) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		
		//on crée une variable de session pour pouvoir garder la connexion de l'utilisateur active tout aut long de la session tant que le navigateur n'est pas fermée
		String mail = request.getParameter("email");
		String mp = request.getParameter("password");
		
		Client client = new Client ();
		Sessions sessions = new Sessions (request,mail,mp);
		
		
		//TDO:
		//1 : appel de la fonction qui charger la base de données
		ChargerDatatBase();
		boolean mailTrouve = false;
		
		String requete = "SELECT * FROM Personne P JOIN Client C ON (P.IDPersonne = C.IDClient) WHERE P.Email = ? AND C.MotDePasse = ?;";
		PreparedStatement statement = connexion.prepareStatement(requete);
		Uils ut = new Uils(mp);
		statement.setString(1,mail);
		statement.setString(2,ut.getStr());
		ResultSet resultset = statement.executeQuery();
		
		while (resultset.next() && mailTrouve != true) {
			client.setId(resultset.getInt("IDPersonne"));
			client.setNom(resultset.getString("Nom"));
			client.setPrenom(resultset.getString("Prenom"));
			client.setAdresse(resultset.getString ("Adresse"));
			client.setPhone(resultset.getString ("Phone"));
			client.setEmail(resultset.getString("Email"));
			client.setMotdePass(resultset.getString ("MotDePasse"));
			if (mail.equals(resultset.getString("Email"))) mailTrouve = true;
		}
			
		
		if (mailTrouve != true) {
			resultat  = new String ("Email ou mot de passe incorrect");
			return false;
		}
		
		System.out.println(client.toString());
		new Sessions(request,client.getId(),client.getNom(), client.getPrenom(),client.getAdresse(),client.getPhone());
		//on va au passage transmettre à la vue la liste des pizzas disponibles
		ListeDesPizzas nosPizzas = new ListeDesPizzas(connexion);
		//on envoie la liste des pizzas à la vue
		nosPizzas.affichePizzas();
		request.setAttribute("pizzas", nosPizzas.getPizzas());
		
		return true;
	}

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}
	//Cette fonction fait appel à notre singleton qui charge la dataBase
	private void ChargerDatatBase () throws ClassNotFoundException, SQLException {
		connexion = ConnexionBDChargeDriver.getInstance().getConnexion();
	}
}
