package com.pizza.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Sessions {
	public HttpSession sessions;
	
	public Sessions (HttpServletRequest request,String email,String mp) {
		sessions = request.getSession();
		sessions.setAttribute("email",email);
		sessions.setAttribute("pass", mp);
	}
	public Sessions (HttpServletRequest request,int id,String nom,String prenom,String adresse,String phone) {
		sessions = request.getSession();
		sessions.setAttribute("nom",nom);
		sessions.setAttribute("prenom",prenom);
		sessions.setAttribute("phone",phone);
		sessions.setAttribute("adresse",adresse);
		sessions.setAttribute("idClient",id);
		

	}
	
	public Sessions (HttpServletRequest request,int id,String nom,String prenom,String phone,String email,String adresse,String mp,String mpc) {
		sessions = request.getSession();
		sessions.setAttribute("nom",nom);
		sessions.setAttribute("prenom",prenom);
		sessions.setAttribute("phone",phone);
		sessions.setAttribute("email",email);
		sessions.setAttribute("adresse",adresse);
		sessions.setAttribute("mp",mp);
		sessions.setAttribute("mpc",mpc);
		sessions.setAttribute("idClient",id);
	}
	public Sessions (HttpServletRequest request,int id) {
		sessions.setAttribute("idClient",id);
	}
	public Sessions (HttpServletRequest request,ListeDesPizzas nosPizzas) {
		request.setAttribute("pizzas", nosPizzas.getPizzas());
	}
	
	public Sessions() {
		// TODO Auto-generated constructor stub
	}
	public void deconnexion (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("nom");
		request.getSession().removeAttribute("prenom");
		request.getSession().removeAttribute("phone");
		request.getSession().removeAttribute("adresse");
		request.getSession().removeAttribute("mp");
		request.getSession().removeAttribute("mpc");
		request.getSession().removeAttribute("idClient");
		request.getServletContext().getRequestDispatcher("/seconnecter.jsp").forward(request, response);
	}
}
