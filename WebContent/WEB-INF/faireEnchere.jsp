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
        <c:if test="${!empty messageGagnant &&  enchere.utilisateur.idUtilisateur != sessionScope.idUtilisateur}">
        	<div class="text-center mt-5">
                    <p>${enchere.utilisateur.pseudo } a ${messageGagnant}</p>
            </div>
        </c:if>
        <c:if test="${!empty messageGagnant &&  enchere.utilisateur.idUtilisateur == sessionScope.idUtilisateur}">
        	<div class="text-center mt-5">
                    <p>Vous avez ${messageGagnant}</p>
            </div>
        </c:if>
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
				<c:if test="${!empty retrait.rue}">
					<p>${retrait.rue} ${retrait.codePostal} ${retrait.ville}</p>
				</c:if>
				<c:if test="${empty retrait.rue}">
					<p>${utilisateur.rue} ${utilisateur.codePostal} ${utilisateur.ville}</p>
				</c:if>
				<p>${utilisateur.pseudo}</p>
			</div>
		</div>
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
