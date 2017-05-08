<%@ page import ="nl.nuggit.kalah.*" %>
<html>
<head>
<title>Kalah</title>
<style>
td {
    border: 1px solid black;
}
</style>
</head>
<body>

<%
Game game = (Game) request.getAttribute("game");
int player = game.getCurrentPlayer();
String name = game.getCurrentPlayerName();
%>

<h3>Your move <%= name %></h3>

<table width="60%">
<tr>
<td>&nbsp;</td>
<%
for(int i=12; i>=7; i--) {
%>
    <td>

    <% if (player == 1) { %>
        <a href="?move=<%=i%>">
    <% } %>

    <%= game.getBoard()[i] %>

    <% if (player == 1) { %>
        </a>
    <% } %>

    </td>
<%
}
%>
<td>&nbsp;</td>
</tr>

<tr>
<td><%= game.getBoard()[13]%></td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td><%= game.getBoard()[6]%></td>
</tr>

<tr>
<td>&nbsp;</td>
<%
for(int i=0; i<=5; i++) {
%>
     <td>

     <% if (player == 0) { %>
         <a href="?move=<%=i%>">
     <% } %>

     <%= game.getBoard()[i] %>

     <% if (player == 0) { %>
         </a>
     <% } %>

     </td>
<%
}
%>
<td>&nbsp;</td>
</tr>

</table>

</body>
</html>