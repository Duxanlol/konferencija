<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pregled svih konferencija</title>
</head>
<body>
<jsp:include page="header.jsp" />  
	<div class="container">
	<c:if test="${not empty konferencije}">
		<table class="table table-bordered">
			<tr>
				<th>Naziv</th>
				<th>Opis</th>
				<th>Organizator</th>
				<th>Pocetak</th>
				<th>Kraj</th>
				<th>Akcije</th>
			</tr>
			<c:forEach items="${konferencije}" var="k">

				<tr>
					<td>${k.naziv}</td>
					<td>${k.opis}</td>
					<td>${k.organizator.imePrezime}</td>
					<td>${k.pocetak}</td>
					<td>${k.kraj}</td>
					<td><a href="/konf/konferencije/konferencija?id=${k.id}">Detaljnije</a>
						<security:authorize access="hasRole('ROLE_USER')">
						<a href="/konf/karte/rezervisi?konferencijaId=${k.id}">Rezervisi kartu</a>
						</security:authorize>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<security:authorize access="hasRole('ROLE_ADMIN')">
		<a href="/konf/konferencije/prikaziFormuDodaj">Dodaj konferenciju</a>
	</security:authorize>
	</div>


</body>
</html>