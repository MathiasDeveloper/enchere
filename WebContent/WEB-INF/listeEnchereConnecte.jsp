<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<title>Liste des enchères</title>
</head>
<body>
<div class="d-flex justify-content-between">
	<div class="ml-5">
		<h1>ENI - Enchères</h1>
	</div>
	<div class="d-flex justify-content-around w-25 mr-5 mt-3">
		<a href="/Home">Enchères</a>
		<a>Vendre un article</a>
		<a href="/AfficherProfil?id=${utilisateur }">Mon profil</a>
		<a>Déconnexion</a>
	</div>
</div>
<div class="text-center">
	<h1 class="mt-5">Liste des enchères</h1>
</div>
<form class="d-flex justify-content-around mt-5">
	<div>
		<label>Filtres : </label>
		<input style="width: 200px;" type="text" placeholder="Le nom de l'article contient">
		<label for="categorie">Categorie</label>
        <select class="form-control" id="categorie" name="categorie">
        	<c:forEach  var="categorie" items="${categories}">
        		<option value="${categorie.idCategorie}">${categorie.libelle}</option>
        	</c:forEach>
        </select>
	</div>
	<div>
		<button type="submit" class="btn btn-outline-secondary">Rechercher</button>
	</div>
</form>
</body>
</html>