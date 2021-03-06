package com.pizza.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pizza.model.ConnexionForm;
import com.pizza.model.ListeDesPizzas;
import com.pizza.model.NosPizzas;

/**
 * Servlet implementation class Seconnecter
 */

public class Seconnecter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Seconnecter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("idClient") == null)
			request.getRequestDispatcher("/seconnecter.jsp").forward(request, response);
		else {
			//on va au passage transmettre à la vue la liste des pizzas disponibles
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
			//on fait appel à notre classe qui vérifie l'authentification
			ConnexionForm conForm;
			conForm = new ConnexionForm();
			//si la fonction renvoie false ou retourne vers la page de connection (échec authentification)
			try {
				if (!conForm.verifierIdentifiantsCon(request)) {
					request.setAttribute("conForm", conForm);
					this.getServletContext().getRequestDispatcher("/seconnecter.jsp").forward(request, response);//renvoie d'une reponse d'erreur à la vue
				} else {
					//on rededirige vers la page lui permettant de commander 
					
					this.getServletContext().getRequestDispatcher("/commander.jsp").forward(request, response);
				}
			} catch (ClassNotFoundException | SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		
	}

}
