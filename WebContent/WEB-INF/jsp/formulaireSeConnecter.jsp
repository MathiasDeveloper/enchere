<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ENI-Enchères | Se connecter</title>

<!-- Bootstrap core CSS -->
<link href="<%=request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="<%=request.getContextPath()%>/css/seConnecter.css" rel="stylesheet">
</head>
<body>
	<h3>ENI-Enchères</h3>
	
	<form action="#" method="post">
		<div class="groupeFormulaire">
			<label>Identifiant :</label>
			<input type="text" placeholder="NineJea" required name="identifiant" id="identifiant">
		</div>
		<br>
		<div class="groupeFormulaire">
			<label>Mot de passe :</label>
			<input type="password" placeholder="••••••••••••" required name="motdepasse" id="motdepasse">
		</div>
		<br>
		<div class="groupeFormulaire">
			<button class="btn btn-primary">Connexion</button>
			<div>
			<input type="checkbox" name="remember" id="remember"> Se souvenir de moi
			<br>
			<a href="#">Mot de passe oublié</a>
			</div>
		</div>
		<br>
	</form>
	
	<button class="btn btn-success">Créer un compte</button>
</body>
</html>