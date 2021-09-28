<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dodaj Sobu</title>
</head>
<body>
<jsp:include page="header.jsp" />  
<div class="container">
	<div align="left">
		<h2>Dodaj Sobu:</h2>
		<form:form action="dodaj" method="post" modelAttribute="soba">
			<form:label path="brojSobe">Soba:</form:label>
			<form:input path="brojSobe" type="number" step="1"/>
			<br>
		
			<form:label path="brojKreveta">Broj kreveta:</form:label>
			<form:input path="brojKreveta" type="number" step="1"/>
			<br>

			<form:label path="cena">Cena sobe:</form:label>
			<form:input path="cena" type="number" step="any"/>
			<br>
			<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
					
			<input type="hidden" name="smestajId" value="<%= request.getParameter("id") %>"/>
			<form:button>Dodaj</form:button>
		</form:form>
	</div>
	</div>
</body>
</html>