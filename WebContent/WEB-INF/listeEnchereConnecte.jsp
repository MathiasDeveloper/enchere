<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <title>Liste des enchères</title>
</head>
<body>
<nav class="d-flex justify-content-around text-dark align-items-center p-3 bg-light">
    <h1><a href="Home" class="nav-link">ENI - Enchères</a></h1>
    <a href="Home" class="nav-link">Enchères</a>
    <a href="VendreArticle" class="nav-link">Vendre un article</a>
    <a href="AfficherProfil?id=${utilisateur }" class="nav-link">Mon profil</a>
    <a href="seDeconnecter" class="nav-link">Déconnexion</a>
</nav>
<div class="jumbotron text-center">
    <h1>Liste des enchères</h1>
</div>

<div class="text-center">
    <c:if test="${!empty message }">
        <p>${message }</p>
    </c:if>
</div>
<div class="container">
<form action="ListeEnchereConnecte" method="POST">
        <div class="d-md-flex d-sm-inline -flex justify-content-md-around">
        <div class="form-group">
            <label for="rechercher">Filtres : </label><br>
            <input class="form-control" id="rechercher" name="nom" type="text"
                   placeholder="Nom de l'article">
            <button type="submit" class="btn btn-outline-secondary mt-2">Rechercher</button>
        </div>
        <div class="form-group">
            <label for="categorie">Categorie : </label>
            <select class="form-control" id="categorie" name="categorie">
                <option value="-1">Toutes</option>
                <c:forEach var="categorie" items="${categories}">
                    <option value="${categorie.idCategorie}">${categorie.libelle}</option>
                </c:forEach>
            </select>
        </div>
        <div id="achatvente" class="d-flex justify-content-between">
            <div class="form-check p-3">
                <input class="form-check-input" type="radio" id="achats" name="type" value="achats" checked>
                <label for="achats">Achats</label>
                <div class="ml-4">
                    <div>
                        <input class="form-check-input" type="radio" id="encheresouvertes" name="encheres" value="encheresouvertes" checked>
                        <label for="encheresouvertes">enchères ouvertes</label>
                    </div>
                    <div>
                        <input class="form-check-input" type="radio" id="encheresencours" name="encheres" value="encheresencours">
                        <label for="encheresencours">enchères en cours</label>
                    </div>
                    <div>
                        <input class="form-check-input" type="radio" id="encheresremportees" name="encheres" value="encheresremportees">
                        <label for="encheresremportees">Mes Ventes</label>
                    </div>
                </div>
            </div>
            <div class="form-check p-3">
                <input class="form-check-input" type="radio" id="ventes" name="type" value="vente">
                <label for="ventes">Mes Ventes</label>
                <div id="ventesradio" class="ml-4">
                    <div>
                        <input class="form-check-input" type="radio" id="ventesencours" name="ventes" value="ventesencours" disabled>
                        <label for="ventesencours">mes ventes en cours</label>
                    </div>
                    <div>
                        <input class="form-check-input" type="radio" id="ventesnondebutees" name="ventes" value="ventesnondebutees" disabled>
                        <label for="ventesnondebutees">ventes non débutées</label>
                    </div>
                    <div>
                        <input class="form-check-input" type="radio" id="ventesterminees" name="ventes" value="ventesterminees" disabled>
                        <label for="ventesterminees">ventes terminées</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<div class="container">
    <div class="d-flex flex-wrap justify-content-around mt-4 mb-4">
        <c:forEach var="enchere" items="${encheres}">
            <div class="border mt-3">
                <div>
                    <img src="/images/patate.jpeg">
                </div>
                <div class="mt-3">
                    <c:if test="${!empty enchere.getArticle().getNomArticle()}">
                        <a href="faireEnchere?idArticle=${enchere.getArticle().getIdArticle() }">${enchere.getArticle().getNomArticle() }</a>
                    </c:if>

                    <c:if test="${empty enchere.getArticle().getNomArticle()}">
                        <p> Aucun titre </p>
                    </c:if>

                    <c:if test="${enchere.getMontantEnchere() == 0}">
                        <p>Prix : ${enchere.getArticle().getPrixInitial()}</p>
                    </c:if>
                    <c:if test="${enchere.getMontantEnchere() > 0}">
                        <p>Prix : ${enchere.getMontantEnchere() }</p>
                    </c:if>
                    <p>Fin de l'enchère : ${enchere.getArticle().getDateFinEnchere() }</p>
                    <p>Vendeur : <a
                            href="AfficherProfil?id=${enchere.getUtilisateur().getIdUtilisateur() }">${enchere.getUtilisateur().getPseudo() }</a>
                    </p>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</div>
</body>
<script type="text/javascript" src="assets/js/listeEnchereConnecte.js"></script>
</html>