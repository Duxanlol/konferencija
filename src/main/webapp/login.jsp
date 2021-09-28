<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta charset="UTF-8">
<title>Prijava</title>
</head>


<body>
	<jsp:include page="header.jsp" />
	<c:if test="${not empty param.error}">
		<div class="alert alert-danger">
			<p>Pogresni podaci.</p>
		</div>
	</c:if>
	<div class="container">
	<div class="d-flex p-2">
		<c:url var="loginUrl" value="/login" />
		<aside class="col-sm-4">
			<div class="card">
				<article class="card-body">
					<a href="/konf/osobe/prikaziFormuDodaj"
						class="float-right btn btn-outline-primary">Registruj se</a>
					<h4 class="card-title mb-4 mt-1">Prijava</h4>
					<form action="${loginUrl}" method="post">
						<div class="form-group">
							<label>Email</label> <input name="username" class="form-control"
								placeholder="Email" type="email">
						</div>
						<!-- form-group// -->
						<div class="form-group">
							<label>Lozinka</label> <input class="form-control"
								name="password" placeholder="******" type="password" required>
						</div>
						<!-- form-group// -->
						<div class="form-group">
							<div class="checkbox">
								<label> <input name="remember-me" type="checkbox">
									Zapamti me
								</label>
							</div>

						</div>

						<div class="form-group">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<button type="submit" class="btn btn-primary btn-block">
								Login</button>
						</div>
						<!-- form-group// -->
					</form>
				</article>
			</div>
		</aside>
	</div>
	</div>
</body>
</html>