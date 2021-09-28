<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pregled svih soba</title>
</head>
<body>
<jsp:include page="header.jsp" />  
	<div class="container">
	<c:if test="${not empty sobe}">
		<table class="table table-bordered">
			<tr>
				<th>Soba</th>
				<th>Broj kreveta</th>
				<th>Cena</th>
				<security:authorize access="hasRole('ROLE_USER')">
				<c:if test="${fromRezervisi}">
					<th>Akcije</th>
				</c:if>
				</security:authorize>
			</tr>
			<c:forEach items="${sobe}" var="s">
				<tr>
					<td>${s.brojSobe}</td>
					<td>${s.brojKreveta}</td>
					<td>${s.cena}</td>
						<security:authorize access="hasRole('ROLE_USER')">					
						<c:if test="${fromRezervisi}">
							<td><a href="/konf/karte/rezervisiSobu?sobaId=${s.id}">Izaberi</a></td>
						</c:if>
						</security:authorize>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<security:authorize access="hasRole('ROLE_ADMIN')">
	<a href="/konf/soba/prikaziFormuDodaj?id=${smestajId}">Dodaj sobu</a>
	</security:authorize>
	</div>
</body>
</html>

