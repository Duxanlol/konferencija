<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<fmt:formatDate value="${yourObject.date}" var="dateString" pattern="dd/MM/yyyy" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dodaj Smestaj</title>
</head>
<body>
<jsp:include page="header.jsp" />  
	<div class="container">
		<h2>Dodaj Smestaj:</h2>
		<form:form action="dodaj" method="post" modelAttribute="smestaj">
			<form:label path="naziv">Naziv objekta:</form:label>
			<form:input path="naziv" />
			<br />

			<form:label path="predstavnik">Predstavnik:</form:label>
			<form:select path="predstavnik" items="${predstavnici}" itemValue="id" itemLabel="imePrezime" />
			<br />
					<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			<form:button>Dodaj</form:button>
		</form:form>
	</div>
</body>
</html>