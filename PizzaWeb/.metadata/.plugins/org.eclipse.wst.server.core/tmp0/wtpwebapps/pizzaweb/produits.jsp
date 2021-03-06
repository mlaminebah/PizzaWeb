<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="produits">
	 <c:if test="${!empty pizzas}">
	 		<c:forEach var="pizza" items="${pizzas}">
	 		   <form action="AjouterauPanier" method="POST">
	 		   		
	 		   		<input type="hidden" name="idPizza" value="<c:out value="${pizza.idPizza}"></c:out>"/>
	 		   		<input type="hidden" name="photo" value="<c:out value="${pizza.photo}"></c:out>"/>
	 		   		<input type="hidden" name="taille" value="<c:out value="${pizza.taille}"></c:out>"/>
	 		   		<input type="hidden" name="nom" value="<c:out value="${pizza.nom}"></c:out>"/>
	 		   		<input type="hidden" name="ingredients" value="<c:out value="${pizza.ingredients}"></c:out>"/>
	 		   		<input type="hidden" name="prix" value="<c:out value="${pizza.prix}"></c:out>"/>
	 		   		<input type="hidden" name="qteStock" value="<c:out value="${pizza.qteStock}"></c:out>"/>
	 				<div class="product">
	 					<article class="survolPizza">
	 								<img src="<c:out value="${pizza.photo}"></c:out>"/>
	                             	<article class="overlay">
	                                    <article class="text">
	                                    	<c:out value="${pizza.nom }"></c:out><br/>
	                                    	<c:out value="Taille : ${pizza.taille}"></c:out><br/>
	                                    	<c:out value="Ingredients :"></c:out>
	                                    	<c:forEach var="ingredient" items="${pizza.ingredients}">
							 			 		<c:out value="${ingredient},"></c:out>
							 			 	</c:forEach>                         	
	                                    </article>
	                             	</article>
	                             	<table>
		                              	<tr><td><article class="prix"><c:out value="${pizza.prix} $"></c:out> </article></td>
		                                    <td><article class="icone_add"><button type="submit"><img src="images/adbask.png"/></button></article></td>
		                                </tr>
		                            </table>   
	                     </article> 
	 					</div>
 				  </form>
 			</c:forEach>
	 </c:if>
	                                                                  
</div>
    
    
    