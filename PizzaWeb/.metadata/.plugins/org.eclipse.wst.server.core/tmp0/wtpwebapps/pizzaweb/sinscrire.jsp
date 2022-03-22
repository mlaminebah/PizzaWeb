<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>
		<section class="signup">
			<form action="Sinscrire" method="post">
		        <img src="images/user.png" alt="profil" class="profil">
		        <h1 class="sinscrire">Etre client</h1>
		        <input type="text" placeholder="Nom" class="sig" name="nom" required>
		        <input type="text" placeholder="Prénom" class="sig" name="prenom" required>
		        <input type="text" placeholder="" value="+336" class="sig" name="phone" required>
		        <input type="text" placeholder="Adresse" class="sig" name="adress" required>
		        <input type="email" placeholder="Adresse email" class="sig" name="email" required>
		        <input type="password" placeholder="entrer mot de passe" name="mp" class="sig" required>
		        <input type="password" placeholder="Confirmer mot de passe"  name="mpc" class="sig" required>
		        <button type="submit" class="bsins">s'inscrire</button>
		        <c:if test="${!empty InsForm.resultat}"><p class="erreur"><c:out value="${InsForm.resultat}"/></p></c:if>
		    </form>
	    </section>
	</body>
</html>