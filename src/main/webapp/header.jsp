<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Header</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
	crossorigin="anonymous"></script>
</head>
<body >
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link"
					href="/konf/konferencije/prikaziSve">Konferencije <span
						class="sr-only"></span></a></li>
				<li class="nav-item"><a class="nav-link" href="/konf/smestaj/prikaziSve">Smestaji</a></li>
				
				<security:authorize access="hasRole('ROLE_ADMIN')">
					<li class="nav-item"><a class="nav-link" href="/konf/osobe/prikaziPredavace">Predavaci</a></li>
				</security:authorize>
				
				<security:authorize access="hasRole('ROLE_USER')">
					<li class="nav-item"><a class="nav-link" href="/konf/karte/mojeKarte">Moje
							Karte</a></li>
				</security:authorize>
			</ul>
			<ul class="navbar-nav ">
			<security:authorize access="isAuthenticated()">
				<li class="nav-item "><a href="/konf/auth/logout"
					class="alert alert-danger nav-link ">
						Odjava
				</a></li>
			</security:authorize>
			
			<security:authorize access="!isAuthenticated()">
				<li class="nav-item  "><a href="/konf/auth/login"
					class="alert alert-success nav-link">
						Prijavi se
				</a></li>
			</security:authorize>
			</ul>
		</div>
	</nav>
</body>
</html>