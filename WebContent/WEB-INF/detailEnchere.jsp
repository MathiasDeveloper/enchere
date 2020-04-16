<%@page import="fr.eni.encheres.bo.Utilisateur"%>
<%@page import="fr.eni.encheres.bo.Article"%>
<%@page import="fr.eni.encheres.bo.Enchere"%>
<%@page import="fr.eni.encheres.bo.Retrait"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<title>${utilisateur.pseudo }</title>
</head>
<body>
<div class="p-5 ml-5">
	<a href="Home" class="h1">ENI - Enchères</a>
</div>
<div class="container d-flex justify-content-center pt-5 mt-5">
	<div class="pr-5">
		<p>Article : </p>
		<p>Description : </p>
		<p>Meilleur offre : </p>
		<p>Mise à prix : </p>
		<p>Début enchère : </p>
		<p>Fin Enchère : </p>
		<p>Adresse de retrait : </p>
		<p>Vendeur : </p>
	</div>
	<div class="text-center pl-5">
		<p>${article.nomArticle }</p>
		<p>${article.description}</p>
		<p>${enchere.montantEnchere }</p>
		<p>${article.prixInitial}</p>
		<p>${article.dateDebutEnchere}</p>
		<p>${article.dateFinEnchere}</p>
		<p>${retrait.rue} ${retrait.codePostal} ${retrait.ville}</p>
		<p>${utilisateur.pseudo}</p>
	</div>
</div>
<div class="text-center">
		<a class="btn btn-outline-secondary" href="faireEnchere?idArticle=${article.idArticle }">Encherir</a>
	</div>
</body>
</html>