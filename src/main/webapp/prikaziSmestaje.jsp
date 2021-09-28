<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pregled svih smestaja</title>
</head>
<body>
<jsp:include page="header.jsp" />  
<div class="container">
	<c:if test="${not empty smestaji}">
		<table class="table table-bordered">
			<tr>
				<th>Naziv</th>
				<th>Predstavnik</th>
				<th>Akcije</th>	
			</tr>
			<c:forEach items="${smestaji}" var="s">
				<tr>
					<td>${s.naziv}</td>
					<td>${s.predstavnik.imePrezime}</td>
					<td><a href="/konf/soba/sobe?id=${s.id}">Detaljnije</a>
						<security:authorize access="hasRole('ROLE_USER')">
						<c:if test="${not empty fromRezervisi}">						
							<a href="/konf/soba/sobe?id=${s.id}">Izaberi</a>
						</c:if></security:authorize>
						<security:authorize access="hasRole('ROLE_ADMIN')">
							<a href="/konf/smestaj/booking?id=${s.id}">Generisi booking</a>
						</security:authorize></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<security:authorize access="hasRole('ROLE_ADMIN')">
		<a href="/konf/smestaj/prikaziFormuDodaj">Dodaj smestaj</a>
	</security:authorize>
	</div>
	

</body>
</html>