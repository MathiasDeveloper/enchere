<%@page import="fr.eni.encheres.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<%
	Utilisateur utilisateur = (Utilisateur)request.getAttribute("utilisateur");
%>
<title><%=utilisateur.getPseudo() %></title>
</head>
<body>
<div class="p-5 ml-5">
	<h1>ENI - Enchères</h1>
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
			<input class="mb-2" type="text" name="pseudo" value="<%=utilisateur.getPseudo() %>">
			<input class="mb-2" type="text" name="prenom" value="<%=utilisateur.getPrenom() %>">
			<input class="mb-2" type="tel" name="telephone" value="<%=utilisateur.getTelephone() %>">
			<input class="mb-2" type="text" name="codePostal" value="<%=utilisateur.getCodePostal() %>">
			<input class="mb-2" type="password" name="mdp">
			<input class="mb-3" type="password" name="newmdp">
			<p class="mb-2"><%=utilisateur.getCredit() %></p>
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
			<input class="mb-2" type="text" name="nom" value="<%=utilisateur.getNom() %>">
			<input class="mb-2" type="email" name="email" value="<%=utilisateur.getEmail() %>">
			<input class="mb-2" type="text" name="rue" value="<%=utilisateur.getRue() %>">
			<input class="mb-2" type="text" name="ville" value="<%=utilisateur.getVille() %>">
			<p style="visibility: hidden">espace</p>
			<input class="mb-2" type="password" name="confirmation">
			<input class="mb-2" type="hidden" name="utilisateur" value="<%=utilisateur.getIdUtilisateur() %>" >
		</div>
	</div>
	<div class="text-center">
		<%
			if(request.getAttribute("message")!=null){
		%>		
				<p><%=request.getAttribute("message") %></p>
		<%		
			}
		%>
	</div>
	<div class="button d-flex justify-content-center">
        <button type="submit">Enregistrer</button>
    </div>
</form>
</body>
</html>