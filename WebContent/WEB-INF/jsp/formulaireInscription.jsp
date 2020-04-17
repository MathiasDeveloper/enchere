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
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="assets/css/inscription.css"rel="stylesheet">
</head>
<body>
	<a href="Home" class="h3">ENI - Enchères</a>

	<form action="#" method="post">
		<h2>Mon profil</h2>

		<div class="ligneFormulaire">
			<div class="groupeFormulaire">
				<label>Pseudo :</label>
				<input type="text" name="pseudo" id="psuedo">
			</div>

			<div class="groupeFormulaire">
				<label>Nom :</label>
				<input type="text" name="nom" id="nom">
			</div>
		</div>

		<br>

		<div class="ligneFormulaire">
			<div class="groupeFormulaire">
				<label>Prénom :</label>
				<input type="text" name="prenom" id="">
			</div>

			<div class="groupeFormulaire">
				<label>Email :</label>
				<input type="email" name="email" id="email">
			</div>
		</div>

		<br>

		<div class="ligneFormulaire">
			<div class="groupeFormulaire">
				<label>Téléphone :</label>
				<input type="text" name="tel" id="">
			</div>

			<div class="groupeFormulaire">
				<label>Rue :</label>
				<input type="text" name="rue" id="rue">
			</div>
		</div>
		
		<br>

		<div class="ligneFormulaire">
			<div class="groupeFormulaire">
				<label>Code Postal :</label>
				<input type="text" name="cp" id="cp">
			</div>

			<div class="groupeFormulaire">
				<label>Ville :</label>
				<input type="text" name="ville" id="ville">
			</div>
		</div>

		<br>

		<div class="ligneFormulaire">
			<div class="groupeFormulaire">
				<label>Mot de passe :</label>
				<input type="password" placeholder="••••••••••••" name="motdepasse" id="motdepasse">
			</div>
			
			<div class="groupeFormulaire">
				<label>Confirmation :</label>
				<input type="password"placeholder="••••••••••••" name="confirmation"	id="confirmation">
			</div>
		</div>
		
		<br>
		
		<div class="ligneFormulaire">
			<button class="btn btn-primary" type="submit">Créer</button>
			<button href="Home" class="btn btn-success">Annuler</button>
		</div>
	</form>
</body>
</html>
