package nl.sogyo.mancala.backend;

import static org.junit.Assert.*;

import org.junit.Test;

import nl.sogyo.mancala.backend.Field;
import nl.sogyo.mancala.backend.Location;

public class FieldTest {

	@Test
	public void getStonesFromFreshFieldShouldEqualFour() {
		Location field = new Field();
		assertEquals(4, field.getStones());
	}

	@Test
	public void doMoveShouldEmptyThisField() {
		Field field = new Field();
		field.doMove();
		assertEquals(0, field.getStones());
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotDoMoveOnOpponentSquares() {
		Field field = new Field();
		field.getPlayer().changeTurn();
		field.doMove();
	}

	@Test
	public void doMoveShouldAddOneToNextField() {
		Field field = new Field();
		field.doMove();
		assertEquals(5, field.getNextLocation().getStones());
	}

	@Test
	public void newFieldShouldBePlayable() {
		Location field = new Field();
		assertTrue(field.isPlayable());
	}

	@Test
	public void takeStonesOnNewFieldShouldReturnFour() {
		Field field = new Field();
		int stonesTaken = field.takeStones();
		assertEquals(4, stonesTaken);
	}

	@Test
	public void takeStonesShouldSetStonesToZero() {
		Field field = new Field();
		field.takeStones();
		assertEquals(0, field.getStones());
	}

	@Test
	public void setupForTakeStonesShouldHaveZeroOnFieldFour() {
		Location field = Field.getTakeStonesTestSetup();
		field = field.getNthLocationRelative(4);
		assertEquals(0, field.getStones());
	}

	@Test
	public void landingInOwnEmptyFieldShouldEmptyOpposingField() {
		Field field = Field.getTakeStonesTestSetup();
		field.doMove();
		Location fieldFour = field.getNthLocationRelative(4);
		assertEquals(0, fieldFour.getOpposite().getStones());
	}

	@Test
	public void landingInOwnEmptyFieldShouldEmptyOwnField() {
		Field field = Field.getTakeStonesTestSetup();
		field.doMove();
		Location fieldFour = field.getNthLocationRelative(4);
		assertEquals(0, fieldFour.getStones());
	}

	@Test
	public void askForSixStonesOnNewBoardShouldGiveSixStones() {
		Field field = new Field(6);
		assertEquals(6, field.getStones());
	}

	@Test(expected = IllegalArgumentException.class)
	public void playingEmptyFieldShouldThrowErrorIfOtherMoveExists() {
		Field field = new Field(0);
		field.getNextLocation().add(2);
		field.doMove();
	}

	@Test
	public void endingInStartingFieldShouldStealOpposingStones() {
		Field field = new Field(13);
		field.doMove();
		assertEquals(0, field.getOpposite().getStones());
	}

	@Test
	public void endingInStartingFieldShouldAddOpposingStonesToKalaha() {
		Field field = new Field(13);
		field.doMove();
		assertEquals(16, field.getNextKalaha().getStones());
	}

	@Test
	public void endingInOpponentEmptyFieldShouldNotStealStones() {
		Field field = Field.endInEmptyOpponentFieldTestSetup();
		((Field) field.getNthLocationRelative(3)).doMove();
		assertEquals(5, field.getNthLocationRelative(5).getStones());
	}
}
