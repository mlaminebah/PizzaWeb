<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
		<div class="connexion">
	        <form action="verification.php" method="post" class="formcon">
	            <h1 align="center"> CONNEXION </h1> 
	            <input type="email" placeholder="Adresse email" name="email" required class="log">
	            <input type="password" placeholder="Mot de passe" name="password" required class="mp">
	            <button id="valid"  class="valid" type="submit">valider</button>
	            <p class="noAccount">Vous n'avez pas de compte?  s'inscrire  </p>
	        </form>
   		</div> 
	</body>
</html>