<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Winner!</title>
<link type="text/css" href="./css/general.css" rel="stylesheet" />
</head>
<body>
	<div id="text">
		<c:out value="${currentState.winner}" />
	</div>
	<div id="newGame">
		<form method="post" action="/mancala_web/MancalaServlet.html">
			<input class="newGameButton" name="newGame" type="submit"
				value="New game?" />
		</form>
	</div>
</body>
</html>