<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<fmt:formatDate value="${yourObject.date}" var="dateString"
	pattern="dd/MM/yyyy" />


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dodaj transakciju</title>
</head>
<body>
<jsp:include page="header.jsp" />  
<div class="container">
	<div align="left">
		<h2>Dodaj transakciju:</h2>
		<form action="dodaj" method="post">
		<label for="izvorOsobaId">Od:</label>
			<select name="izvorOsobaId">
				<c:forEach items="${osobe}" var="osoba">
					<option value="${osoba.id}">${osoba.imePrezime}</option>
					${osoba.imePrezime}
				</c:forEach>
			</select>
			<label for="destinacijaOsobaId">Od:</label>
			<select name="destinacijaOsobaId">
				<c:forEach items="${osobe}" var="osoba">
					${osoba.imePrezime}
					<option value="${osoba.id}">${osoba.imePrezime}</option>
				</c:forEach>
			</select>
			
				
			<br /> <label for="valuta">Valuta:</label>
			<input name="valuta" /> <br /> <label for="iznos">Iznos:</label> <input
				name="iznos" type="number" /> <br /> <label for="svrha">Svrha:</label>
									<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			<input name="svrha" /> <br /> <input type="submit" value="Submit" />
		</form>
	</div>
</div>
</body>
</html>