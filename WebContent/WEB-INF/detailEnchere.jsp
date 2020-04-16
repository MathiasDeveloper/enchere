<%@page import="fr.eni.encheres.bo.Utilisateur"%>
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
	<h1>ENI - Enchères</h1>
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
		<p>${articles.nomArticle  }</p>
		<p>${articles.description }</p>
		<p>${articles.prixVente  }</p>
		<p>${articles.prixInitial  }</p>
		<p>${articles.dateDebutEnchere }</p>
		<p>${articles.dateFinEnchere }</p>
		<p>${retraits.rue }</p>
		<p>${utilisateur.pseudo }</p>
	</div>
</div>
</body>
</html>