<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Karta</title>
</head>
<body>
<jsp:include page="header.jsp" />  
	<c:if test="${not empty karta}">
	
	<div class="container" style="border:1px solid #cecece;">
		<h1>${karta.konferencija.naziv}</h1>
		<h6 class="text-muted">${karta.konferencija.pocetak} - ${karta.konferencija.kraj} , \$${karta.cena}</h6>
		
		<h6 class="text-muted"> <a href="/konf/karte/getTimeTablePDF?kartaId=${karta.id}">Stampaj</a></h6>		
		
		<h3>${karta.soba.smestaj.naziv}, Soba: ${karta.soba.brojSobe}</h3>
		<br><br>
		<table class="table table-bordered">
  			<thead>
    			<tr>
      				<th scope="col">Predavanje</th>
      				<th scope="col">Vreme</th>
    			</tr>
  			</thead>
		<tbody>
		<c:forEach items="${karta.mesta}" var="mesto">
    		<tr>
      			<td>${mesto.izvodjenje.predavanje.naziv}</td>
      			<fmt:parseDate value="${mesto.izvodjenje.datum}" pattern="yyyy-MM-dd'T'HH:mm" var="datum" type="both"/>
      			<td><fmt:formatDate type = "both" value = "${datum}" /></td>      			
    		</tr>
    		</c:forEach>
    		</tbody>
    	</table>
		</div>
	</c:if>
	
</body>
</html>