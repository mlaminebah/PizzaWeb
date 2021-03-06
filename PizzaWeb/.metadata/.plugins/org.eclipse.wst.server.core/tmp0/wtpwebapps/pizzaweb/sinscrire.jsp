<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>
	<!-- Récupération des données de session -->
	<c:if test="${!empty sessionScope.nom || !empty sessionScope.prenom || !empty sessionScope.phone || !empty sessionScope.email || 
	!empty sessionScope.adresse || !empty sessionScope.mp || !empty sessionScope.mpc}">
		<c:set var="nom" value="${sessionScope.nom}" scope="page" />
		<c:set var="prenom" value="${sessionScope.prenom}" scope="page" />
		<c:set var="phone" value="${sessionScope.phone}" scope="page" />
		<c:set var="email" value="${sessionScope.email}" scope="page" />
		<c:set var="adresse" value="${sessionScope.adresse}" scope="page" />
		<c:set var="mp" value="${sessionScope.mp}" scope="page" />
		<c:set var="mpc" value="${sessionScope.mpc}" scope="page" />
	</c:if>
		<section class="signup">
			<form action="Sinscrire" method="post">
				<c:if test="${!empty InsForm.resultat}"><p class="erreur"><c:out value="${InsForm.resultat}"/></p></c:if>
		        <img src="images/user.png" alt="profil" class="profil">
		        <h1 class="sinscrire">Etre client</h1>
		        <input type="text" placeholder="Nom" value ="${nom}" class="sig" name="nom" required>
		        <input type="text" placeholder="Prénom" value ="${prenom}" class="sig" name="prenom" required>
		        <input type="text" placeholder="+336" value ="${phone}" class="sig" name="phone" required>
		        <input type="text" placeholder="Adresse" value ="${adresse}" class="sig" name="adress" required>
		        <input type="email" placeholder="Adresse email" value ="${email}" class="sig" name="email" required>
		        <input type="password" placeholder="entrer mot de passe" value ="${mp}" name="mp" class="sig" required>
		        <input type="password" placeholder="Confirmer mot de passe"  value ="${mpc}" name="mpc" class="sig" required>
		        <button type="submit" class="bsins">s'inscrire</button>
		        
		    </form>
	    </section>
	</body>
</html>