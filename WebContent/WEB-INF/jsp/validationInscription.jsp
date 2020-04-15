<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Résultat de connection</title>
</head>
<body>
	<%  
		String pseudo = (String)request.getAttribute("pseudo");
		String motdepasse  = (String)request.getAttribute("motdepasse");
	%>
	<h1>Utilisateur ajouté en base</h1>
	<p><%=pseudo	%></p>
	<p><%=motdepasse %></p>
	
		<%
	
	if(session.getAttribute("idUtilisateur")!=null){
		int idUtilisateur=(int)session.getAttribute("idUtilisateur");
		%>
		<p>ID :  <%=idUtilisateur %></p>
		
	<%
	}else{
		%>
		
		<p>La session n'existe pas</p>
		
		<%
	}
	
	%>
	
	
</body>
</html>