<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Welcome</title>
		<link href="css/header.css" rel="stylesheet" type="text/css"/>
		<link href="css/basket.css" rel="stylesheet"/>
        <link href="css/dropdown.css" rel="stylesheet"/>
        <link href="css/header.css" rel="stylesheet"/>
        <link href="css/survolPizza.css" rel="stylesheet"/>
        <link href="css/produits.css" rel="stylesheet"/>
        <link rel="stylesheet" href="css/signup.css">
        <link rel="stylesheet" href="css/login.css" type="text/css">
	</head>
	<body>
		<header>
				<nav class="header-left">
			         <a><img class="logo" id="logo" src="images/logo.jpg"/></a>
			    </nav>
			    <nav class="header-right">
			    	 
			         <a href="#"><img class="profile" id="profile" src="images/user.png"/></a>
			         <span class="nom"><c:if test="${!empty sessionScope.prenom}"><c:out value="${sessionScope.prenom}"></c:out></c:if></span>
			    </nav>
		</header>