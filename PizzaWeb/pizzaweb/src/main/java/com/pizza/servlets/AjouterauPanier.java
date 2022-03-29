package com.pizza.servlets;

import java.io.IOException;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pizza.model.ListeDesPizzas;
import com.pizza.model.NosPizzas;
import com.pizza.model.Panier;

/**
 * Servlet implementation class AjouterPanier
 */

public class AjouterauPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterauPanier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("idClient") != null) {
			ListeDesPizzas nosPizzas;
			try {
				nosPizzas = new NosPizzas().getListeDesPizzas();
				//on envoie la liste des pizzas à la vue
				request.setAttribute("pizzas", nosPizzas.getPizzas());
				request.getRequestDispatcher("/commander.jsp").forward(request, response);
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else 
			this.getServletContext().getRequestDispatcher("/seconnecter.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			Panier panier = Panier.getInstance();
			panier.ajouterPizzaAuPanier(request,response);
			panier.affiche ();
			//retour vers la page d'affichage commande pour voir le contenu panier à droite
			this.getServletContext().getRequestDispatcher("/commander.jsp").forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
