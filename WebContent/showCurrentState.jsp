<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Game in progress</title>
<link type="text/css" href="./css/general.css" rel="stylesheet" />
<link type="text/css" href="./css/showCurrentState.css" rel="stylesheet" />
</head>
<body>
	<div id="game">
		<div class="game_player" id="game_playerone">
			<div class="playername">Player 1</div>
			<form method="post" action="/mancala_web/MancalaServlet.html">
				<div class="playerfield" id="playerfield_playerone">
					<c:forEach var="i" begin="0" end="5">
						<div class="singleButtonDiv">
							<button class="doMoveButton" id="doMoveButton_${i}" name="doMove"
								type="submit" value="${i}">${currentState.stones[i]}</button>
						</div>
					</c:forEach>
				</div>
				<div class="kalaha singleButtonDiv">
					<button class="kalahaButton" id="kalaha_playerone" disabled="true">${currentState.stones[6]}</button>
				</div>
			</form>
		</div>
		<div class="game_player" id="game_playertwo">
			<form method="post" action="/mancala_web/MancalaServlet.html">
				<div class="playerfield" id="playerfield_playertwo">
					<c:forEach var="i" begin="7" end="12">
						<div class="singleButtonDiv">
							<button class="doMoveButton" id="doMoveButton_${i}" name="doMove"
								type="submit" value="${i}">${currentState.stones[i]}</button>
						</div>
					</c:forEach>
				</div>
				<div class="kalaha singleButtonDiv">
					<button class="kalahaButton" id="kalaha_playertwo" disabled="true">${currentState.stones[13]}</button>
				</div>
			</form>
			<div class="playername">Player 2</div>
		</div>
	</div>
	<div id="currentTurn">
		It is player
		<c:out value="${currentState.currentTurn}" />
		's turn.
	</div>
	<div id="newGame">
		<form method="post" action="/mancala_web/MancalaServlet.html">
			<button class="newGameButton" name="newGame" type="submit" value="">
				New game?</button>
		</form>
	</div>
</body>
</html>