<%@ page import ="nl.nuggit.kalah.*" %>
<html>
<head>
<title>Kalah</title>
</head>
<body>

<%
Game game = (Game) request.getAttribute("game");
%>

<h3>The game has ended</h3>

<%= game.getPlayers()[0] %> : <%= game.getPoints()[0] %> points <br/>

<%= game.getPlayers()[1] %> : <%= game.getPoints()[1] %> points <br/>

</body>
</html>