<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ENI-Enchères | Inscription</title>

<!-- Bootstrap core CSS -->
<link
	href="<%=request.getContextPath()%>/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="<%=request.getContextPath()%>/css/inscription.css"
	rel="stylesheet">
</head>
<body>
	<a href="Home" class="h3">ENI - Enchères</a>

	<form action="#" method="post">
		<h2>Mon profil</h2>
		<div class="alert alert-danger" role="alert">
 			Pseudo ou email déjà utilisé
		</div>
		<div class="ligneFormulaire">
			<div class="groupeFormulaire">
				<label>Pseudo :</label>
				<input type="text" required name="pseudo" id="psuedo">
			</div>

			<div class="groupeFormulaire">
				<label>Nom :</label>
				<input type="text" required name="nom" id="nom">
			</div>
		</div>

		<br>

		<div class="ligneFormulaire">
			<div class="groupeFormulaire">
				<label>Prénom :</label>
				<input type="text" required name="prenom" id="">
			</div>

			<div class="groupeFormulaire">
				<label>Email :</label>
				<input type="email" required name="email" id="email">
			</div>
		</div>

		<br>

		<div class="ligneFormulaire">
			<div class="groupeFormulaire">
				<label>Téléphone :</label>
				<input type="text" required name="tel" id="">
			</div>

			<div class="groupeFormulaire">
				<label>Rue :</label>
				<input type="text" required name="rue" id="rue">
			</div>
		</div>
		
		<br>

		<div class="ligneFormulaire">
			<div class="groupeFormulaire">
				<label>Code Postal :</label>
				<input type="text" required name="cp" id="cp">
			</div>

			<div class="groupeFormulaire">
				<label>Ville :</label>
				<input type="text" required name="ville" id="ville">
			</div>
		</div>

		<br>

		<div class="ligneFormulaire">
			<div class="groupeFormulaire">
				<label>Mot de passe :</label>
				<input type="password" placeholder="••••••••••••" required name="motdepasse" id="motdepasse">
			</div>
			
			<div class="groupeFormulaire">
				<label>Confirmation :</label>
				<input type="password"placeholder="••••••••••••" required name="confirmation"	id="confirmation">
			</div>
		</div>
		
		<br>
		
		<div class="ligneFormulaire">
			<button class="btn btn-primary" type="submit">Créer</button>
			<button href="#" class="btn btn-success">Annuler</button>
		</div>
	</form>
</body>
</html>
