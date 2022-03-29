<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>
	<main>
            <section class="main">
                    <%@ include file="categorie.jsp" %>
                    <%@ include file="produits.jsp" %>
            </section>
            
            <aside>
            	<!--<c:out value="${cookie.panierString.value}"></c:out>-->
            	<h4 class="monPan">Mon panier</h4>
            	<c:if test="${!empty resultat}"><p class="erreur"><c:out value="${resultat}"/></p></c:if>
            	<c:if test="${!empty success}"><p class="success"><c:out value="${success}"/></p></c:if>
            	 <c:choose>
						<c:when test="${ empty sessionScope.panier}"></c:when>
						<c:otherwise>
							<form action="ValiderLaCommande" method="post">
								<table class="tablePan"> 
									<c:forEach var="entry" items="${sessionScope.panier}"> 
										<tr> 
											 <td><img class="ppan" alt="pizza" src="<c:out value="${entry.value.photo}"></c:out>"></td>
											 <td><c:out value="${entry.value.qte}"></c:out></td> 
											 <td><c:out value="${entry.value.nom}"></c:out></td>
											 <td><c:out value="*"></c:out></td>
											 <td><c:out value="${entry.value.prix}$ = "/></td>
											 <td><c:out value="${entry.value.prixTotal} $"/></td>
											 <td><a href="#" class="sub">-</a></td>
											 <td><a href="#" class="sup">x</a></td>
										</tr>
									</c:forEach>
									<tr><td class="total" colspan="8"><c:if test="${!empty prixTotalCommande}"><c:out value="Total : ${prixTotalCommande} $"/></c:if></td></tr>
								</table><hr/>
								Me faire Livrer <input type="checkbox" name="livraison"/><br/>
								<button id="valid"  class="valid" type="submit">Acheter</button>
							</form>
						</c:otherwise>
				  </c:choose>
            </aside>
            
        </main>
	</body>
</html>