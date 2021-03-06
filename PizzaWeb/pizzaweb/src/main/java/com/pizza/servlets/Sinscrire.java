package com.pizza.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pizza.model.ConnexionForm;
import com.pizza.model.InscriptionForm;

/**
 * Servlet implementation class Sinscrire
 */

public class Sinscrire extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Sinscrire() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/sinscrire.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		InscriptionForm InsForm = new InscriptionForm();
		try {
			if (!InsForm.verifierIdentifiantsCon(request)) {//si les données ne repondent pas au critètres
				request.setAttribute("InsForm", InsForm);
					this.getServletContext().getRequestDispatcher("/sinscrire.jsp").forward(request, response);//renvoie d'une reponse d'erreur à la vue
			} else {
					//on rededirige vers la page lui permettant de commander 
					this.getServletContext().getRequestDispatcher("/seconnecter.jsp").forward(request, response);				
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
