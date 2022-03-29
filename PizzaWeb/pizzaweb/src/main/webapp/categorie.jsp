<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="select">
	<div class="dropdown">
		<button class="dropbtn">Taille</button>
        <div class="dropdown-content">
	        	<c:if test="${!empty pizzas}">
	        		<c:forEach var="pizza" items="${pizzas}">
						<a hre="#"><c:out value="Taille : ${pizza.taille}"></c:out></a>                        	
		        	</c:forEach>
	        	</c:if>
        </div>
    </div>
</div>