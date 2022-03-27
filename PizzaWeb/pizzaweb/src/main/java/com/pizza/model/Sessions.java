package com.pizza.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Sessions {
	public HttpSession sessions;
	
	public Sessions (HttpServletRequest request,String email,String mp) {
		sessions = request.getSession();
		sessions.setAttribute("email",email);
		sessions.setAttribute("pass", mp);
	}
	public Sessions (HttpServletRequest request,String nom,String prenom,String adresse,String phone) {
		sessions = request.getSession();
		sessions.setAttribute("nom",nom);
		sessions.setAttribute("prenom",prenom);
		sessions.setAttribute("phone",phone);
		sessions.setAttribute("adresse",adresse);

	}
	
	public Sessions (HttpServletRequest request,String nom,String prenom,String phone,String email,String adresse,String mp,String mpc) {
		sessions = request.getSession();
		sessions.setAttribute("nom",nom);
		sessions.setAttribute("prenom",prenom);
		sessions.setAttribute("phone",phone);
		sessions.setAttribute("email",email);
		sessions.setAttribute("adresse",adresse);
		sessions.setAttribute("mp",mp);
		sessions.setAttribute("mpc",mpc);
	}
}