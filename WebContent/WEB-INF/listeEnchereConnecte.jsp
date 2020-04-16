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
		<a href="Home" class="h1">ENI - Enchères</a>
	</div>
	<div class="d-flex justify-content-around w-25 mr-5 mt-3">
		<a href="/Home">Enchères</a>
		<a href="VendreArticle">Vendre un article</a>
		<a href="AfficherProfil?id=${utilisateur }">Mon profil</a>
		<a href="seDeconnecter">Déconnexion</a>
	</div>
</div>
<div class="text-center">
	<h1 class="mt-5">Liste des enchères</h1>
</div>
<div class="text-center">
	<c:if test="${!empty message }">
		<p>${message }</p>
	</c:if>
</div>
<form class="d-flex justify-content-around mt-5" action="ListeEnchereConnecte" method="POST">
	<div class="w-25">
		<label>Filtres : </label><br>
		<input style="width: 400px;" name="nom" type="text" placeholder="Le nom de l'article contient">
		<label class="mt-3	" for="categorie">Categorie : </label>
        <select id="categorie" name="categorie">
        	<option value="-1">Toutes</option>
        	<c:forEach  var="categorie" items="${categories}">
        		<option value="${categorie.idCategorie}">${categorie.libelle}</option>
        	</c:forEach>
        </select>
        <div id="achatvente" class="d-flex justify-content-between">
	        <div>
			  <input type="radio" id="achats" name="type" value="achats" checked>
			  <label for="achats">Achats</label>
			</div>
			<div class="pr-5 mr-5">
			  <input type="radio" id="ventes" name="type" value="vente">
			  <label for="ventes">Mes Ventes</label>
			</div>
		</div>
		<div class="d-flex justify-content-between">
	        <div class="ml-4">
				<div>
				  <input type="radio" id="encheresouvertes" name="encheres" value="encheresouvertes" checked>
				  <label for="encheresouvertes">enchères ouvertes</label>
				</div>
				<div>
				  <input type="radio" id="encheresencours" name="encheres" value="encheresencours">
				  <label for="encheresencours">enchères en cours</label>
				</div>
				<div>
				  <input type="radio" id="encheresremportees" name="encheres" value="encheresremportees">
				  <label for="encheresremportees">Mes Ventes</label>
				</div>
			</div>
			<div id="ventesradio" class="ml-4">
	  		  	<div>
				  <input type="radio" id="ventesencours" name="ventes" value="ventesencours" disabled>
				  <label for="ventesencours">mes ventes en cours</label>
				</div>
				<div>
				  <input type="radio" id="ventesnondebutees" name="ventes" value="ventesnondebutees" disabled>
				  <label for="ventesnondebutees">ventes non débutées</label>
				</div>
				<div>
				  <input type="radio" id="ventesterminees" name="ventes" value="ventesterminees" disabled>
				  <label for="ventesterminees">ventes terminées</label>
				</div>
			</div>
		</div>
	</div>
	<div class="d-flex align-items-center">
		<button type="submit" class="btn btn-outline-secondary">Rechercher</button>
	</div>
</form>
<div class=" d-flex mt-4 w-100 justify-content-around">
	<c:forEach  var="enchere" items="${encheres}">
		<div class="border w-25 d-flex justify-content-around mt-3">
			<div>
				<img src="">
			</div>
			<div class="mt-3">
				<a href="#">${enchere.getArticle().getNomArticle() }</a>
				<p>Prix : ${enchere.getMontantEnchere() }</p>
				<p>Fin de l'enchère : ${enchere.getArticle().getDateFinEnchere() }</p>
				<p>Vendeur : <a href="AfficherProfil?id=${enchere.getUtilisateur().getIdUtilisateur() }">${enchere.getUtilisateur().getPseudo() }</a></p>
			</div>
		</div>
	</c:forEach>
</div>
</body>
<script type="text/javascript" src="assets/js/listeEnchereConnecte.js"></script>
</html>