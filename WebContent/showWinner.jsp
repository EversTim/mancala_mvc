<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Winner!</title>
</head>
<body>
	Player
	<c:out value="${currentState.winner}" />
	has won!
	<br />
		<form method="post" action="/mancala_web/MancalaServlet.html">
		<input name="newGame" type="submit" value="New game?"/>
	</form>
</body>
</html>