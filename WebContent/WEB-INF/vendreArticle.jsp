<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vendre article</title>
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
</head>
<body>
<div class="jumbotron">
    <div class="container">
        <h1>ENI - Enchères</h1>
    </div>
</div>
<div class="container">
    <c:if test="${!empty messageArticle}">
        <div class="alert alert-danger" role="alert">
            <p>${messageArticle}</p>
        </div>
    </c:if>
    <c:if test="${!empty messageEnchere}">
        <div class="alert alert-danger" role="alert">
            <p>${messageEnchere}</p>
        </div>
    </c:if>
    <c:if test="${empty messageEnchere && empty messageArticle && param.formSubmit != null}">
        <div class="alert alert-success" role="alert">
            <p>${messageSucces}</p>
        </div>
    </c:if>
    <form action="VendreArticle" method="POST">
        <div class="mb-5">
            <h2>Article</h2>
            <hr>
            <div class="form-group">
                <label for="nomArticle">Nom de l'article</label>
                <input type="text"
                       class="form-control"
                       id="nomArticle"
                       name="nomArticle"
                       value="<c:out value="${param.nomArticle}"/>"
                       placeholder="Nom de l'article"
                       >
            </div>
            <div class="form-group">
                <label for="descriptionArticle">Description</label>
                <textarea class="form-control"
                          name="descriptionArticle"
                          id="descriptionArticle"
                          placeholder="Description de votre article"
                          rows="3">${param.descriptionArticle.trim()}</textarea>
            </div>
            <div class="form-group">
                <label for="categorie">Categorie</label>
                <select class="form-control"
                        id="categorie"
                        name="categorie"
                        >
                    <c:forEach  var="categorie" items="${categories}">
                            <option value="${categorie.idCategorie}">${categorie.libelle}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <hr>
        <h2>Enchere</h2>
        <hr>

        <div class="form-group">
            <label for="prix">Prix de l'enchere</label>
                <input type="number"
                       id="prix"
                       class="form-control"
                       name="prixInitial"
                       value="<c:out value="${param.prixInitial}"/>"
                       placeholder="Prix initial de la vente"
                       >
        </div>
        <div class="d-flex justify-content-between">
            <div class="form-group">
                <label for="dateDebut">Date de début de l'enchere</label>
                <input type="date"
                       id="dateDebut"
                       class="form-control"
                       name="dateDebutEnchere"
                       value="<c:out value="${param.dateDebutEnchere}"/>"
                       placeholder="Prix initial de la vente"
                       >
            </div>
            <div class="form-group">
                <label for="heureDebutEnchere">Heure de début de l'enchere</label>
                <input type="time"
                       id="heureDebutEnchere"
                       class="form-control"
                       name="heureDebutEnchere"
                       value="<c:out value="${param.heureDebutEnchere}"/>"
                       >
            </div>
            <div class="form-group">
                <label for="dateFinEnchere">Date de fin de l'enchere</label>
                <input type="date"
                       id="dateFinEnchere"
                       class="form-control"
                       name="dateFinEnchere"
                       value="<c:out value="${param.dateFinEnchere}"/>"
                       >
            </div>
            <div class="form-group">
                <label for="heureFinEnchere">Heure de fin de l'enchere</label>
                <input type="time"
                       id="heureFinEnchere"
                       class="form-control"
                       name="heureFinEnchere"
                       value="<c:out value="${param.heureFinEnchere}"/>"
                       >
            </div>
        </div>
        <input type="hidden" name="formSubmit">
        <input type="submit" role="button" class="btn btn-primary">
    </form>
</div>

</body>
</html>
