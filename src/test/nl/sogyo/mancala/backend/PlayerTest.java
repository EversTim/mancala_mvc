package nl.sogyo.mancala.backend;

import static org.junit.Assert.*;

import org.junit.Test;

import nl.sogyo.mancala.backend.Field;
import nl.sogyo.mancala.backend.Player;

public class PlayerTest {

	@Test
	public void playerShouldBeOpponentsOpponent() {
		Player player = new Player();
		assertSame(player, player.getOpponent().getOpponent());
	}

	@Test
	public void firstPlayerShouldStartWithTurn() {
		Player player = new Player();
		assertTrue(player.hasTurn());
	}

	@Test
	public void ifPlayerHasTurnOpponentDoesNot() {
		Player player = new Player();
		assertFalse(player.getOpponent().hasTurn());
	}

	@Test
	public void playerAndOpponentShouldHaveDifferentTurns() {
		Player player = new Player();
		assertNotEquals(player.hasTurn(), player.getOpponent().hasTurn());
	}

	@Test
	public void playerAndOpponentShouldHaveDifferentTurnsAfterSwitch() {
		Player player = new Player();
		player.changeTurn();
		assertNotEquals(player.hasTurn(), player.getOpponent().hasTurn());
	}

	@Test
	public void playerShouldEndTurnAfterMove() {
		Field field = new Field();
		field.doMove();
		assertFalse(field.getPlayer().hasTurn());
	}

	@Test
	public void opponentShouldGetTurnAfterMove() {
		Field field = new Field();
		field.doMove();
		assertTrue(field.getPlayer().getOpponent().hasTurn());
	}

	@Test
	public void playerShouldKeepMoveAfterEndingInOwnKalaha() {
		Field field = (Field) (new Field()).getNthLocationRelative(2);
		field.doMove();
		assertTrue(field.getPlayer().hasTurn());
	}
}
