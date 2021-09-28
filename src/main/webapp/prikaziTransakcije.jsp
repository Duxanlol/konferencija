<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pregled svih transakcija</title>
</head>
<body>
<jsp:include page="header.jsp" />  
	<c:if test="${not empty transakcije}">
		<table style="width: 100%">
			<tr>
				<th>Od</th>
				<th>Do</th>
				<th>Valuta</th>
				<th>Iznos</th>
				<th>Svrha</th>
			</tr>
			<c:forEach items="${transakcije}" var="t">

				<tr>
					<td>${t.izvor.osoba.imePrezime}</td>
					<td>${t.destinacija.osoba.imePrezime}</td>
					<td>${t.valuta}</td>
					<td>${t.iznos}</td>
					<td>${t.svrha}</td>
					<td><a href="/konf/transakcije/transakcija?id=${t.id}">Detaljnije</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
<a href="/konf/transakcije/prikaziFormuDodaj">Dodaj transakciju</a>

</body>
</html>