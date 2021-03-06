package com.pizza.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ValideEtVideLePanier {
	private Connection connexion;
	
	public ValideEtVideLePanier (HttpServletRequest request) throws SQLException, ClassNotFoundException {
		HttpSession session = request.getSession();
		System.out.println("le client "+session.getAttribute("email") +" valide  son panier");
		Panier panier = Panier.getInstance();
		//panier.affiche ();
		System.out.println("*************affiche son panier**************");
		
		ChargerDatatBase ();
		ListeDesPizzas nosPizzas = new ListeDesPizzas(connexion);
		nosPizzas.affichePizzas();
		request.setAttribute("pizzas", nosPizzas.getPizzas());
		//on insère la commande dans la table commandes
		int idClient =(int) request.getSession().getAttribute("idClient");
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
		String requInserToCommandes = "INSERT INTO Commandes (IdClient,QteCom,Total,DateCommande) VALUES (?,?,?,?)";
		PreparedStatement statementComma =  connexion.prepareStatement(requInserToCommandes);
		statementComma.setInt(1, idClient);
		statementComma.setInt(2, panier.getPanier().size());
		statementComma.setFloat(3, panier.totalCommande());
		statementComma.setString(4,formattedDate);
		statementComma.executeUpdate();
		//on récupère d'abord le numéro de commande du client
		String requComClient = "SELECT IdCom FROM Commandes  WHERE IdClient = ?";
		PreparedStatement statementC =  connexion.prepareStatement(requComClient);
		statementC.setInt(1, idClient);
		//statementC.executeQuery();
		ResultSet resultset = statementC.executeQuery();
		int idCom = 0;
		while (resultset.next()) {
			idCom = Integer.parseInt(resultset.getString("IdCom"));
		}
		String success = new String ();
		//on insère aussi pour chaque commande passé par un client la liste des pizzas commandées
		for (Integer k : panier.getPanier().keySet()) {
			
			PizzaPanier p = panier.getPanier().get(k);
			
			String reqStock = "SELECT QteStock FROM Pizza WHERE IDPizza = ?";
			PreparedStatement statementStock = connexion.prepareStatement(reqStock);
			statementStock.setInt(1, p.getIdPizza());
			statementStock.executeQuery();
			int qteStock = 0;
			ResultSet rs = statementStock.executeQuery();
			while (rs.next()) {
				qteStock = Integer.parseInt(rs.getString("QteStock"));
			}
			if (qteStock <= 0) {
				success = new String ("Oups vous avez été devancé par quelqu'un d'autres");
				break;
			}
			
			String requLigneCom = "INSERT INTO LigneCommande VALUES (?,?,?,?)";
			PreparedStatement statementLC = connexion.prepareStatement(requLigneCom);
			statementLC.setInt(1, p.getIdPizza());
			statementLC.setInt(2, idCom);
			statementLC.setInt(3, p.getQte());
			statementLC.setFloat(4, p.getPrixTotal());
			statementLC.executeUpdate();
			//pour la pizza on récupère sa quantité en stock
			
			
			//on fait une mise à jour de notre table en changeant la quantité en stock de la pizza
			String requPizza = "UPDATE Pizza SET QteStock = ? WHERE IDPizza = ?";
			PreparedStatement statementMP = connexion.prepareStatement(requPizza);
			statementMP.setInt(1, qteStock - p.getQte());
			statementMP.setInt(2, p.getIdPizza());
			statementMP.executeUpdate();
			
			success = new String ("Vous venez d'éffectuer une commande");
			
		}
		
		request.setAttribute("success",success);
		request.removeAttribute("resultat");
		request.getSession().removeAttribute("panier");
		panier.videPanier();
	}
	//Cette fonction fait appel à notre singleton qui charge la dataBase
	private void ChargerDatatBase () throws ClassNotFoundException, SQLException {
		connexion = ConnexionBDChargeDriver.getInstance().getConnexion();
	}
	
	

}
