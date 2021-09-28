<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Konferencija</title>
</head>
<body>
<jsp:include page="header.jsp" />  
	<div class="container">
	<c:if test="${not empty konferencija}">
		<table class="table table-bordered">
			<tr>
				<th>Naziv</th>
				<th>Opis</th>
				<th>Organizator</th>
				<th>Pocetak</th>
				<th>Kraj</th>
			</tr>
			<tr>
				<td>${konferencija.naziv}</td>
				<td>${konferencija.opis}</td>
				<td>${konferencija.organizator.imePrezime}</td>
				<td>${konferencija.pocetak}</td>
				<td>${konferencija.kraj}</td>
			</tr>
		</table>
		<br>
		<security:authorize access="hasRole('ROLE_USER')">
			<form action="rezervisiKartu" method="post" name="izvodjenja">
		</security:authorize>
		
		<c:forEach items="${konferencija.predavanja}" var="p">
		<div class="container" style="border: 1px solid #cecece">			
					<h3>${p.naziv} </h3>
					<h5 class="text-muted">${p.predavac.imePrezime}</h5>
					<h6 class="text-muted">${p.opis}</h6>
					<security:authorize access="hasRole('ROLE_ADMIN')">
					<a href="/konf/izvodjenja/prikaziFormuDodaj?konferencijaId=${konferencija.id}&predavanjeId=${p.id}">Dodaj
							izvodjenje</a>
					</security:authorize>
					
					
				<h3>Izvodjenja:</h3>
						<table class="table table-bordered">
  							<thead>
    						<tr>
      							<th scope="col">Broj preostalih mesta</th>
      							<th scope="col">Vreme</th>
      							<security:authorize access="hasRole('ROLE_USER')">
								<c:if test="${not empty fromRezervisi}">
									<th scope="col">Rezervisi</th>
								</c:if>
								</security:authorize>
    						</tr>
  							</thead>
						<tbody>
									
					<c:forEach items="${p.izvodjenja}" var="i">
						<tr>
							<td>${i.brojMesta}</td>
							<fmt:parseDate value="${i.datum}" pattern="yyyy-MM-dd'T'HH:mm" var="datum" type="both"/>
      						<td><fmt:formatDate type = "both" value = "${datum}" /></td>   
							
							<security:authorize access="hasRole('ROLE_USER')">
								<c:if test="${not empty fromRezervisi}">
									<td><input type="radio" name="${p.id}" value="${i.id}"></td>
								</c:if>
							</security:authorize>
						</tr>
						</c:forEach>
					</table>
					</div>
		</c:forEach>
		<security:authorize access="hasRole('ROLE_USER')">
		<c:if test="${fromRezervisi}">
		<input type="submit" value="Rezervisi" />
							<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
		</c:if>
		</security:authorize>
		<security:authorize access="hasRole('ROLE_USER')">
			</form>
		</security:authorize>
		
		
		<security:authorize access="hasRole('ROLE_ADMIN')">
		<a href="/konf/predavanja/prikaziFormuDodaj?id=${konferencija.id}">Dodaj
			predavanje</a>
		</security:authorize>
		</div>
		
	</c:if>
</body>
</html>