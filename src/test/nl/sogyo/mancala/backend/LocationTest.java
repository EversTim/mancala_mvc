package nl.sogyo.mancala.backend;

import static org.junit.Assert.*;

import org.junit.Test;

import nl.sogyo.mancala.backend.Field;
import nl.sogyo.mancala.backend.Kalaha;
import nl.sogyo.mancala.backend.Location;
import nl.sogyo.mancala.backend.Player;
import nl.sogyo.mancala.backend.WinnerRelative;

public class LocationTest {

	@Test
	public void doMoveOnNewBoardShouldAddOneToLocationOne() {
		Field field = new Field();
		field.doMove();
		assertEquals(5, field.getNextLocation().getNextLocation().getStones());
	}

	@Test
	public void doMoveOnNewBoardShouldAddOneToLocationFour() {
		Field field = new Field();
		field.doMove();
		Location fieldFour = field.getNextLocation().getNextLocation().getNextLocation().getNextLocation();
		assertEquals(5, fieldFour.getStones());
	}

	@Test
	public void doMoveOnNewBoardShouldAddOneToLocationFourUsingGetNthFieldMethod() {
		Field field = new Field();
		field.doMove();
		Location fieldFour = field.getNthLocationRelative(4);
		assertEquals(5, fieldFour.getStones());
	}

	@Test
	public void doMoveOnNewBoardShouldAddZeroToLocationFive() {
		Field field = new Field();
		field.doMove();
		Location fieldFive = field.getNextLocation().getNextLocation().getNextLocation().getNextLocation()
				.getNextLocation();
		assertEquals(4, fieldFive.getStones());
	}

	@Test
	public void locationFourteenShouldEqualLocationZero() {
		Location field = new Field();
		Location fieldFourteen = field.getNextLocation().getNextLocation().getNextLocation().getNextLocation()
				.getNextLocation().getNextLocation().getNextLocation().getNextLocation().getNextLocation()
				.getNextLocation().getNextLocation().getNextLocation().getNextLocation().getNextLocation();
		assertSame(fieldFourteen, field);
	}

	@Test
	public void locationFourteenShouldEqualLocationZeroUsingGetNthFieldMethod() {
		Location field = new Field();
		Location fieldFourteen = field.getNthLocationRelative(14);
		assertSame(fieldFourteen, field);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getNthLocationRelativeShouldThrowErrorIfNIsNegative() {
		Location field = new Field();
		field.getNthLocationRelative(-1);
	}

	@Test
	public void locationZeroShouldBeOppositeLocationTwelve() {
		Location field = new Field();
		Location opposite = field.getOpposite();
		Location fieldThirteen = field.getNthLocationRelative(12);
		assertSame(fieldThirteen, opposite);
	}

	@Test
	public void locationFiveShouldBeOppositeLocationSeven() {
		Location fieldFive = (new Field()).getNthLocationRelative(5);
		Location opposite = fieldFive.getOpposite();
		Location fieldSeven = fieldFive.getNthLocationRelative(2);
		assertSame(fieldSeven, opposite);
	}

	@Test
	public void locationTwelveShouldBeOppositeLocationZero() {
		Location field = new Field();
		Location fieldThirteen = field.getNthLocationRelative(12);
		Location opposite = fieldThirteen.getOpposite();
		assertSame(field, opposite);
	}

	@Test
	public void locationSevenShouldBeOppositeLocationFive() {
		Location fieldFive = (new Field()).getNthLocationRelative(5);
		Location fieldSeven = fieldFive.getNthLocationRelative(2);
		Location opposite = fieldSeven.getOpposite();
		assertSame(fieldFive, opposite);
	}

	@Test
	public void locationSixShouldBeKalaha() {
		Location field = new Field();
		Location locSix = field.getNthLocationRelative(6);
		assertTrue(locSix instanceof Kalaha);
	}

	@Test
	public void locationThirteenShouldBeKalaha() {
		Location field = new Field();
		Location locThirteen = field.getNthLocationRelative(13);
		assertTrue(locThirteen instanceof Kalaha);
	}

	@Test
	public void ownerOfLocationShouldChangeAfterKalaha() {
		Location field = new Field();
		Location fieldAfterKalaha = field.getNextKalaha().getNextLocation();
		assertNotSame(field.getPlayer(), fieldAfterKalaha.getPlayer());
	}

	@Test
	public void ownerOfLocationShouldSwitchToOpponentAfterKalaha() {
		Location field = new Field();
		Location fieldAfterKalaha = field.getNextKalaha().getNextLocation();
		assertSame(field.getPlayer().getOpponent(), fieldAfterKalaha.getPlayer());
	}

	@Test
	public void getNextKalahaShouldAlwaysBeOwnKalahaFromFieldZero() {
		Location field = new Field();
		Player ownPlayer = field.getPlayer();
		Kalaha nextKalaha = field.getNextKalaha();
		Player kalahaPlayer = nextKalaha.getPlayer();
		assertSame(ownPlayer, kalahaPlayer);
	}

	@Test
	public void getNextKalahaShouldAlwaysBeOwnKalahaFromFieldSix() {
		Location field = (new Field()).getNthLocationRelative(6);
		Player ownPlayer = field.getPlayer();
		Kalaha nextKalaha = field.getNextKalaha();
		Player kalahaPlayer = nextKalaha.getPlayer();
		assertSame(ownPlayer, kalahaPlayer);
	}

	@Test
	public void getNextKalahaShouldAlwaysBeOwnKalahaFromFieldSeven() {
		Location field = (new Field()).getNthLocationRelative(7);
		Player ownPlayer = field.getPlayer();
		Kalaha nextKalaha = field.getNextKalaha();
		Player kalahaPlayer = nextKalaha.getPlayer();
		assertSame(ownPlayer, kalahaPlayer);
	}

	@Test
	public void getNextKalahaShouldAlwaysBeOwnKalahaFromFieldThirteen() {
		Location field = (new Field()).getNthLocationRelative(13);
		Player ownPlayer = field.getPlayer();
		Kalaha nextKalaha = field.getNextKalaha();
		Player kalahaPlayer = nextKalaha.getPlayer();
		assertSame(ownPlayer, kalahaPlayer);
	}

	@Test
	public void getTotalStonesOnNewBoardShouldReturnTwentyFour() {
		Location field = new Field();
		assertEquals(24, field.getTotalStonesToKalaha());
	}

	@Test
	public void currentPlayerShouldWinGetCheckWinnerPlayerOneTestSetup() {
		Field field = Field.getCheckWinnerPlayerOneTestSetup();
		field.doMove();
		assertEquals(WinnerRelative.SELF, field.getPlayer().getWinner());
	}

	@Test
	public void otherPlayerShouldLoseGetCheckWinnerPlayerOneTestSetup() {
		Field field = Field.getCheckWinnerPlayerOneTestSetup();
		field.doMove();
		assertEquals(WinnerRelative.OTHER, field.getPlayer().getOpponent().getWinner());
	}

	@Test
	public void otherPlayerShouldWinGetCheckWinnerPlayerTwoTestSetup() {
		Field field = Field.getCheckWinnerPlayerTwoTestSetup();
		field.doMove();
		assertEquals(WinnerRelative.SELF, field.getPlayer().getOpponent().getWinner());
	}

	@Test
	public void currentPlayerShouldLoseGetCheckWinnerPlayerTwoTestSetup() {
		Field field = Field.getCheckWinnerPlayerTwoTestSetup();
		field.doMove();
		assertEquals(WinnerRelative.OTHER, field.getPlayer().getWinner());
	}

	@Test
	public void newFieldShouldDraw() {
		Field field = new Field();
		field.endGame();
		assertEquals(WinnerRelative.DRAW, field.getPlayer().getWinner());
	}

}
