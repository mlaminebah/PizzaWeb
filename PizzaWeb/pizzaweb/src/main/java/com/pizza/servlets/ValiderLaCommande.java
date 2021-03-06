package com.pizza.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pizza.model.ListeDesPizzas;
import com.pizza.model.NosPizzas;
import com.pizza.model.Panier;
import com.pizza.model.ValideEtVideLePanier;

/**
 * Servlet implementation class ValiderCommande
 */

public class ValiderLaCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValiderLaCommande() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getSession().getAttribute("idClient") == null)
			request.getServletContext().getRequestDispatcher("/seconnecter.jsp").forward(request, response);
		else {
			request.getServletContext().getRequestDispatcher("/commander.jsp").forward(request, response);
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			ValideEtVideLePanier valid = new ValideEtVideLePanier (request);
			this.getServletContext().getRequestDispatcher("/commander.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
