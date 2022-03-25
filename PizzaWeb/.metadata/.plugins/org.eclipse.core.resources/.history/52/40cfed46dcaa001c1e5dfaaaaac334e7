<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>
		<div class="connexion">
			<!-- L'action est renvoyée vers la servlet Seconnecter -->
	        <form action="Seconnecter" method="post" class="formcon">
	            <h1 align="center"> CONNEXION </h1> 
	            <input type="email" placeholder="Adresse email" name="email" required class="log">
	            <input type="password" placeholder="Mot de passe" name="password" required class="mp">
	            <button id="valid"  class="valid" type="submit">valider</button>
	            <p class="noAccount">Vous n'avez pas de compte?  <a href="sinscrire.jsp">s'inscrire</a> </p>
	            <!-- Affichage du message d'erreur en cas d'erreur d'authentification avec du jstl -->
	           <c:if test="${!empty conForm.resultat}"><p class="erreur"><c:out value="${conForm.resultat}"/></p></c:if>
	        </form>
	        
   		</div> 
	</body>
</html>