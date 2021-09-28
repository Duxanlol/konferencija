<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registracija</title>
</head>
<body>
<jsp:include page="header.jsp" />  
<div class="container">
	<div class="d-flex p-2">
		<aside class="col-sm-4">
			<div class="card">
				<article class="card-body">
					<h4 class="card-title mb-4 mt-1">Registracija</h4>
					<form action="dodaj" method="post">						
						<div class="form-group">
							<label>Ime</label> <input name="ime" class="form-control"
								placeholder="ime" type="text">
						</div>
						<div class="form-group">
							<label>Prezime</label> <input name="prezime" class="form-control"
								placeholder="prezime" type="text">
						</div>
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
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<button type="submit" class="btn btn-primary btn-block">
								Registruj se</button>
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

