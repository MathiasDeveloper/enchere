<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ENI-Enchères | Se connecter</title>

<!-- Bootstrap core CSS -->
<link
	href="<%=request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="<%=request.getContextPath()%>/css/seConnecter.css"
	rel="stylesheet">
</head>
<body>
	<% 
		String email = (String)request.getAttribute("email");
		session.setAttribute("emailFin", email);
	%>

	<h3>ENI-Enchères</h3>
	<form action="changementMotDePasse" method="post">
		<div class="alert alert-success" role="alert">
  			Email trouvée, saisissez votre nouveau mot de passe
		</div>
		<div class="groupeFormulaire">
			<label>Mot de passe :</label> <input type="password"
				placeholder="••••••••••••" required name="motdepasse"
				id="motdepasse">
		</div>
		<br>
		<div class="groupeFormulaire">
			<label>Confirmation :</label> <input type="password"
				placeholder="••••••••••••" required name="confirmation"
				id="confirmation">
		</div>
		<br>
		<div class="groupeFormulaire">
			<button class="btn btn-primary">Confirmer le changement</button>
		</div>
		<br>
		
	</form>
</body>
</html>