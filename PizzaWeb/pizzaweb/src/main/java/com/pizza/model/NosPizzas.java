package com.pizza.model;

import java.sql.Connection;
import java.sql.SQLException;

public class NosPizzas {
	private Connection connexion;
	private ListeDesPizzas nosPizzas;
	
	public NosPizzas () throws SQLException, ClassNotFoundException {
		ChargerDatatBase ();
		nosPizzas = new ListeDesPizzas(connexion);
	}
	public ListeDesPizzas getListeDesPizzas () {
		return nosPizzas;
	}
	private void ChargerDatatBase () throws ClassNotFoundException, SQLException {
		connexion = ConnexionBDChargeDriver.getInstance().getConnexion();
	}
}
