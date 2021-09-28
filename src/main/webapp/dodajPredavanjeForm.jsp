<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<fmt:formatDate value="${yourObject.date}" var="dateString" pattern="dd/MM/yyyy" />


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dodaj predavanje</title>
</head>
<body>
<jsp:include page="header.jsp" />  
<div class="container">
	<div align="left">
		<h2>Dodaj Predavanje:</h2>
		<form:form action="dodaj" method="post" modelAttribute="predavanje">
			
			<form:label path="predavac">Predavac:</form:label>
			<form:select path="predavac" items="${predavaci}" itemValue="id" itemLabel="imePrezime" />
			<br />
		
			<form:label path="naziv">naziv:</form:label>
			<form:input path="naziv" />
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