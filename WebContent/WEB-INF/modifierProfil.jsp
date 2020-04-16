<%@page import="fr.eni.encheres.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<title>Eni enchere - ${utilisateur.pseudo }</title>
</head>
<body>
<div class="p-5 ml-5">
	<a href="Home" class="h1">ENI - Enchères</a>
</div>
<form action="ModifierProfil" method="POST">
	<div class="container d-flex justify-content-around pt-5 mt-5 mb-5">
		<div class="w-10">
			<p>Pseudo : </p>
			<p>Prénom : </p>
			<p>Téléphone : </p>
			<p>Code Postal : </p>
			<p>Mot de passe actuel : </p>
			<p>Nouveau mot de passe : </p>
			<p>Crédit : </p>
		</div>
		<div class="text-center d-flex flex-column w-10">
			<input class="mb-2" type="text" name="pseudo" value="${utilisateur.pseudo }">
			<input class="mb-2" type="text" name="prenom" value="${utilisateur.prenom }">
			<input class="mb-2" type="tel" name="telephone" value="${utilisateur.telephone }">
			<input class="mb-2" type="text" name="codePostal" value="${utilisateur.codePostal }">
			<input class="mb-2" type="password" name="mdp">
			<input class="mb-3" type="password" name="newmdp">
			<p class="mb-2">${utilisateur.credit }</p>
		</div>
		<div class="w-10">
			<p>Nom : </p>
			<p>Email : </p>
			<p>Rue : </p>
			<p>Ville : </p>
			<p style="visibility: hidden">espace</p>
			<p>Confirmation : </p>
		</div>
		<div class="text-center d-flex flex-column w-10">
			<input class="mb-2" type="text" name="nom" value="${utilisateur.nom }">
			<input class="mb-2" type="email" name="email" value="${utilisateur.email }">
			<input class="mb-2" type="text" name="rue" value="${utilisateur.rue }">
			<input class="mb-2" type="text" name="ville" value="${utilisateur.ville }">
			<p style="visibility: hidden">espace</p>
			<input class="mb-2" type="password" name="confirmation">
			<input class="mb-2" type="hidden" name="utilisateur" value="${utilisateur.idUtilisateur }" >
		</div>
	</div>
	<div class="text-center">
		<c:if test="${!empty message }">
			<p>${message }</p>
		</c:if>
	</div>
	<div class="button d-flex justify-content-center">
        <button class="btn btn-outline-secondary mr-5" type="submit">Enregistrer</button>
        <a class="btn btn-outline-secondary ml-5" href="SupprimerCompte?id=${utilisateur.idUtilisateur }">Supprimer mon compte</a>
    </div>
</form>
</body>
</html>