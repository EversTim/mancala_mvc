package nl.sogyo.mancala.backend;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class MancalaTest {

	@Test
	public void doMoveOnFirstFieldShouldEmptyFirstField() {
		Mancala mancala = new Mancala();
		mancala.doMove(1);
		assertEquals(new Integer(0), mancala.getStoneAmounts().get(0));
	}

	@Test
	public void getStoneAmountsOnFieldShouldReturnEnclosedArray() {
		Integer[] expected = { 4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0 };
		Mancala mancala = new Mancala();
		ArrayList<Integer> stones = mancala.getStoneAmounts();
		Integer[] actual = mancala.getStoneAmounts().toArray(new Integer[stones.size()]);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void getStoneAmountsOnFieldSixShouldReturnEnclosedArray() {
		Integer[] expected = { 6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0 };
		Mancala mancala = new Mancala(6);
		ArrayList<Integer> stones = mancala.getStoneAmounts();
		Integer[] actual = mancala.getStoneAmounts().toArray(new Integer[stones.size()]);
		assertArrayEquals(expected, actual);
	}

	@Test
	public void newBoardShouldNotHaveWinner() {
		Mancala mancala = new Mancala();
		assertFalse(mancala.hasWinner());
	}

	@Test
	public void newBoardShouldHaveMoveForCurrentPlayer() {
		Mancala mancala = new Mancala();
		assertTrue(mancala.canCurrentPlayerMove());
	}

	@Test
	public void emptyBoardShouldNotHaveMoveForCurrentPlayer() {
		Mancala mancala = new Mancala(0);
		assertFalse(mancala.canCurrentPlayerMove());
	}
}
