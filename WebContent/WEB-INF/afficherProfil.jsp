<%@page import="fr.eni.encheres.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
	Utilisateur utilisateur = (Utilisateur)request.getAttribute("utilisateur");
%>
<title><%=utilisateur.getPseudo() %></title>
</head>
<body>
<p>
	Pseudo : <%=utilisateur.getPseudo() %><br>
	Nom : <%=utilisateur.getNom() %><br>
	Prénom : <%=utilisateur.getPrenom() %><br>
	Email : <%=utilisateur.getEmail() %><br>
	Téléphone : <%=utilisateur.getTelephone() %><br>
	Rue : <%=utilisateur.getRue() %><br>
	Code postal : <%=utilisateur.getCodePostal() %><br>
	Ville : <%=utilisateur.getVille() %><br>
</p>
</body>
</html>