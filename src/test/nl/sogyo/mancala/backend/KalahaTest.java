package nl.sogyo.mancala.backend;

import static org.junit.Assert.*;

import org.junit.Test;

import nl.sogyo.mancala.backend.Field;
import nl.sogyo.mancala.backend.Kalaha;
import nl.sogyo.mancala.backend.Location;

public class KalahaTest {

	@Test
	public void kalahaIsNeverPlayable() {
		Kalaha k = new Kalaha();
		assertFalse(k.isPlayable());
	}

	@Test
	public void kalahaIsItsOwnOpposite() {
		Kalaha k = new Kalaha();
		Location opp = k.getOpposite();
		assertSame(k, opp);
	}

	@Test
	public void opponentKalahaShouldNotTakeAStone() {
		Field field = new Field();
		field.stones = 14;
		field.doMove();
		field = (Field) field.getNthLocationRelative(12);
		assertEquals(0, field.getNextLocation().getStones());
	}

	@Test
	public void ownKalahaShouldTakeAStone() {
		Field field = new Field();
		field = (Field) field.getNthLocationRelative(5);
		field.doMove();
		assertEquals(1, field.getNextLocation().getStones());
	}

	@Test
	public void doMoveOnTakeStoneBoardShouldHaveFiveInKalaha() {
		Field field = Field.getTakeStonesTestSetup();
		field.doMove();
		Kalaha kal = field.getNextKalaha();
		assertEquals(5, kal.getStones());
	}

	@Test
	public void kalahaShouldHaveOneStoneAfterEndingInKalaha() {
		Field field = (Field) (new Field()).getNthLocationRelative(2);
		field.doMove();
		assertEquals(1, field.getNextKalaha().getStones());
	}
}
