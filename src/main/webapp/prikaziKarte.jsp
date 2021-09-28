<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Karte</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">
	<c:if test="${not empty karte}">
	<c:forEach items="${karte}" var="k">
	
	<div class="d-flex p-2 flex-row">
	<div class="card" style="width: 60rem;">
		<div class="card-body">
			<h5 class="card-title">${k.konferencija.naziv}</h5>
			<h6 class="card-subtitle mb-2 text-muted">${k.konferencija.pocetak} - ${k.konferencija.kraj}</h6>
			<p class="card-text"> ${k.konferencija.opis} </p>
			<h6 class="card-subtitle mb-2 ">\$${k.cena}</h6>
			<a href="/konf/karte/karta?kartaId=${k.id}" class="card-link">Detaljnije</a>
		</div>
	</div>
	</div>
	</c:forEach>
	
	</c:if>
	<c:if test="${empty karte}">
	<h1>Nema karata.</h1>
	</c:if>
	</div>
	
	
</body>
</html>