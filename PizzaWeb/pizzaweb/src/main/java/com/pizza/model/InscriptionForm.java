package com.pizza.model;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mysql.cj.util.Util;

/****
 * 
 * @author 
 *	Cette classe gère l'inscription d'un utilisateur en insérant les données renseignées par celui dans la base de données
 *
 */
public class InscriptionForm {
	private String resultat;
	private Connection connexion;
	
	/****
	 * 
	 * @param request
	 * @return true si aucun problème n'est signalée avec l'inscription
	 * false si l'utilisateur existe déjà ou si les champs ne respectent pas une certaines longueur et que les deux mots de passes fournis sont différents
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws NoSuchAlgorithmException 
	 * 
	 */
	public boolean verifierIdentifiantsCon (HttpServletRequest request) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		
		//on recupère les champs
		Client client = new Client ();
		client.setNom(request.getParameter("nom"));
		client.setPrenom(request.getParameter("prenom"));
		client.setPhone(request.getParameter("phone"));
		client.setEmail(request.getParameter("email"));
		client.setAdresse(request.getParameter("adress"));
		client.setMotdePass(request.getParameter("mp"));
		
		String mpc = request.getParameter("mpc");
		
		//on garde les infos en session
		new Sessions (request,client.getId(),client.getNom(),client.getPrenom(),client.getPhone(),client.getEmail(),client.getAdresse(),client.getMotdePass(),mpc);
		
		//on vérifie que les champs répondent aux critères
		if (client.getNom ().length() < 3 || client.getPrenom().length() < 3) {
			resultat = new String ("Nom et Prénom doivent au moins être à 3 caractères");
			return false;
		} else if (client.getPhone().length() < 12) {
			resultat = new String ("Le format valide est : +33XXXXXXXXXX");
			return false;
		} else if (client.getMotdePass().length() < 6) {
			resultat = new String ("Le mot de passe doit être au moins : 6 caractères dont au moins 1 chiffre et caractère spécial");
			return false;
		} else if (!client.getMotdePass().equals(mpc)) {
			resultat = new String ("les 2 mots de pass doivent être identiques");
			return false;
		}
		
		//Connexion à la base de données
		ChargerDatatBase();
		
		//si la clientExiste vaut true dans ce cas on renvoie un message d'erreur
		if (verifSiClientExiste(client.getEmail())) {
			resultat = new String ("Un client existe déjà avec ce login");
			return false;
		}
		//si le client n'existe pas on l'insère donc dans la base de données
		ajouterClient (client,request);
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
	//renvoie true si ce client existe déjà en se basant sur son email
	private boolean verifSiClientExiste (String email) throws SQLException {
		String requete = "SELECT * FROM Personne WHERE Email = ?";
		PreparedStatement statement = connexion.prepareStatement(requete);
		statement.setString(1,email);
		ResultSet resultset = statement.executeQuery();
		//on vérifie s'il existe déjà un client avec ce login dans la liste retournée par la requete
		boolean clientExiste = false;
		while (resultset.next() && clientExiste != true) {
			String emailVerifcation = resultset.getString("Email");
			if (email.equals(emailVerifcation)) clientExiste = true;
		}
		if (clientExiste) return true;
		else return false;
		
	}
	//cette fonction ajoute un client dans la base de données
	public void ajouterClient (Client client,HttpServletRequest request) throws SQLException, NoSuchAlgorithmException {
		//Ajout dans la table personne
		String requeteAjouterPesonne = "INSERT INTO Personne (Nom,Prenom,Email,Adresse,Phone) VALUES (?,?,?,?,?);";
		PreparedStatement statementPersonne =  connexion.prepareStatement(requeteAjouterPesonne);
		System.out.println(client.getNom()+" "+client.getPrenom()+" "+client.getEmail()+" "+client.getAdresse()+" "+client.getPhone());
		statementPersonne.setString(1, client.getNom());
		statementPersonne.setString(2, client.getPrenom());
		statementPersonne.setString(3, client.getEmail());
		statementPersonne.setString(4, client.getAdresse());
		statementPersonne.setString(5, client.getPhone());
		statementPersonne.executeUpdate();
		//Ajout dans la table client
		//il faut d'abord récupérer l'id du client dans la table personne
		String requeteTrouvIdMail = "SELECT IDPersonne,Email FROM Personne WHERE Email = ?;";
		PreparedStatement statementId = connexion.prepareStatement(requeteTrouvIdMail);
		statementId.setString(1, client.getEmail());
		ResultSet resultset = statementId.executeQuery();
		boolean trouve = false;
		while (resultset.next() && trouve != true) {
			String s = resultset.getString("Email");
			System.out.println(s);
			if (s.equals(client.getEmail())) {
				client.setId(Integer.parseInt(resultset.getString(1)));
				trouve = true;
			}
		}
		
		System.out.println(client.getEmail()+" "+client.getId());
		//on insère le client dans la table client
		String requeterAjouterClient = "INSERT INTO Client VALUES (?,?)";
		PreparedStatement statementClient = connexion.prepareStatement(requeterAjouterClient);
		statementClient.setInt(1, client.getId());
		Uils ut = new Uils(client.getMotdePass());
		statementClient.setString(2, ut.getStr());
		statementClient.executeUpdate();
		System.out.println("Ajout de "+client.toString());
	
		
		//on va au passage transmettre à la vue la liste des pizzas disponibles
		ListeDesPizzas nosPizzas = new ListeDesPizzas(connexion);
				//on envoie la liste des pizzas à la vue
		nosPizzas.affichePizzas();
		request.setAttribute("pizzas", nosPizzas.getPizzas());
		
	}
}

