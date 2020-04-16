<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Eni enchere - ${fn:toUpperCase(article.nomArticle)}</title>
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <form action="faireEnchere" method="POST">

        <c:if test="${!empty messagePrix}">
            <div class="alert alert-danger" role="alert">
                    ${messagePrix}
            </div>
        </c:if>

        <c:if test="${!empty message}">
            <div class="alert alert-danger" role="alert">
                    ${message}
            </div>
        </c:if>

        <c:if test="${!empty messageDate}">
            <div class="alert alert-info" role="alert">
                    ${messageDate}
            </div>
        </c:if>

        <c:if test="${!empty messageUtilisateur}">
            <div class="alert alert-info" role="alert">
                    ${messageUtilisateur}
            </div>
        </c:if>

        <c:if test="${param.isSubmit != null && !empty messageSucces}">
            <div class="alert alert-success" role="alert">
                    ${messageSucces}
            </div>
        </c:if>

        <c:if test="${!empty messageCredit}">
            <div class="alert alert-danger" role="alert">
                    ${messageCredit}
            </div>
        </c:if>

        <c:if test="${utilisateur.idUtilisateur != sessionScope.idUtilisateur && !empty sessionScope.idUtilisateur
        && messageDate == null }">
            <div class="form-group">
                <label for="prix">Montant</label>
                <input id="prix" class="form-control" type="number" name="prix" value="${param.prix}" placeholder="indiquer un montant">
            </div>
            <input type="hidden" name="idArticle" value="${article.idArticle}">
            <input class="btn btn-primary" type="submit" name="isSubmit" role="button" value="Encherir">
        </c:if>
    </form>
</div>

</body>
</html>
