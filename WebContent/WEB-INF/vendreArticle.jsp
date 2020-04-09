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
                       placeholder="Nom de l'article"
                       required>
            </div>
            <div class="form-group">
                <label for="descriptionArticle">Description</label>
                <textarea class="form-control"
                          name="descriptionArticle"
                          id="descriptionArticle"
                          placeholder="Description de votre article"
                          rows="3"
                          required>
        </textarea>
            </div>
            <div class="form-group">
                <label for="categorie">Categorie</label>
                <select class="form-control"
                        id="categorie"
                        name="categorie"
                        required>
                    <c:forEach  var="categorie" items="${categories}">
                            <option value="${categorie.libelle}">${categorie.libelle}</option>
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
                       name="prixVente"
                       placeholder="prix de la vente"
                       required>
        </div>
    </form>
</div>

</body>
</html>
