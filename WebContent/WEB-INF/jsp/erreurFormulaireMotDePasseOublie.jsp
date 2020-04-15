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
	<h3>ENI-Enchères</h3>

	<form action="motDePasseOublie" method="post">
		<div class="alert alert-danger" role="alert">
  			L'email n'existe pas
		</div>
		<div class="groupeFormulaire">
			<label>Email :</label> <input type="email" placeholder="Insérer votre email"
				required name="email" id="email">
		</div>
		<br>
		<div class="groupeFormulaire">
			<button class="btn btn-primary">Vérifier</button>
		</div>
	</form>
</body>
</html>