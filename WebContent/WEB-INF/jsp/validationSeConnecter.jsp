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
		String identifiant = (String)request.getAttribute("identifiant");
		String motdepasse  = (String)request.getAttribute("motdepasse");
	%>
	
	<p><%=identifiant	%></p>
	<p><%=motdepasse %></p>
	
	
</body>
</html>