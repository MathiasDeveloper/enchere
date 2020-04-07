<%--
  Created by IntelliJ IDEA.
  User: mathias
  Date: 06/04/2020
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>ENI-Encheres</title>
  </head>
  <body>
 	<form action="" method="post">
 		<input type="text" name="identifiant" required placeholder="NineJea" id="identifiant"/>
 		<br>
 		<input type="password" name="motdepasse" required placeholder="•••••••••••" id="motdepasse"/>
 		<div>
 			<button type="submit">Connexion</button>
 			<div>
 				<input type="checkbox" />
 				<a href="#">Mot de passe oublié</a>
 			</div>
 		</div>
 	</form>
 	
 	<button>Créer un compte</button>
  </body>
</html>
