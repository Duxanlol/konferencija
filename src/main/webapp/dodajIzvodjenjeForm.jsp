<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<fmt:formatDate value="${yourObject.date}" var="dateString" pattern="dd/MM/yyyy" />


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dodaj izvodjenje</title>
</head>
<body>
<jsp:include page="header.jsp" />  
<div class="container">
	<div align="left">
		<h2>Dodaj izvodjenje:</h2>
		<form:form action="dodaj" method="post" modelAttribute="izvodjenje">
			<form:label path="brojMesta">Broj mesta:</form:label>
			<form:input path="brojMesta" />
			<br />

			<form:label path="datum">Pocetak:</form:label>
			<form:input type="datetime-local" path="datum" />
			<br />
					<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />

			<form:button>Dodaj</form:button>
		</form:form>
	</div>
</div>
</body>
</html>