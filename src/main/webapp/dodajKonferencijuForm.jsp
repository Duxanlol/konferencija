<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dodaj konferenciju</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div class="container">  
	<div align="left">
		<h2>Dodaj Konferenciju:</h2>
		<form:form action="dodaj" method="post" modelAttribute="konferencija">
			
			<form:label path="organizator">Organizator:</form:label>
			<form:select path="organizator" items="${organizatori}" itemValue="id" itemLabel="imePrezime" />
			<br />
		
			<form:label path="naziv">naziv:</form:label>
			<form:input path="naziv" />
			<br />

			<form:label path="pocetak">Pocetak (yyyy-mm-dd):</form:label>
			<form:input path="pocetak" />
			<br />
			
			<form:label path="kraj">Kraj (yyyy-mm-dd):</form:label>
			<form:input path="kraj" />
			<br />

			<form:label path="cena">Cena:</form:label>
			<form:input path="cena" />
			<br />

			<form:label path="opis">Opis:</form:label>
			<form:textarea path="opis" cols="25" rows="5" />
			<br />
					<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />

			<form:button>Dodaj</form:button>
		</form:form>
	</div>
	</div>
</body>
</html>