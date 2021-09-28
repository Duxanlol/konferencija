<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Svi predavaci</title>
</head>
<body>
<jsp:include page="header.jsp" />  
<div class="container">
	<c:if test="${not empty predavaci}">
		<table class="table table-bordered">
			<tr>
				<th>Ime Prezime</th>
				<th>Akcije</th>	
			</tr>
			<c:forEach items="${predavaci}" var="p">
				<tr>
					<td>${p.imePrezime}</td>
						<td>				
							<a href="/konf/osobe/predavacRaspored?id=${p.id}">Generisi Raspored</a>
						</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	</div>
</body>
</html>