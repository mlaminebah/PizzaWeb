package com.pizza.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*****
 * 
 * @author minesabry
 * Cette classe est un singleton qui permet de charger le driverJDBC et établit une connexion avec la base de données mysql
 */
public class ConnexionBDChargeDriver {
	private static ConnexionBDChargeDriver instance = null;
	private Connection connexion;
	
	private ConnexionBDChargeDriver () throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizza_web","root", "votre mot de passe");//
		System.out.println("Connexion établie avec succès");
	}
	public static ConnexionBDChargeDriver getInstance () throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new ConnexionBDChargeDriver();
		}
		return instance;
	}
	
	public Connection getConnexion () {
		return connexion;
	}
}
