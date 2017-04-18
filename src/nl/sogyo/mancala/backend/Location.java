package nl.sogyo.mancala.backend;

import java.io.Serializable;

abstract class Location implements Serializable {

	private static final long serialVersionUID = 1L;

	int stones;
	Location nextLocation;
	Player player;

	Location() {
	}

	Location(int stones, Location firstLocation, int fieldsToGo, Player player) {
		fieldsToGo--;
		this.player = player;
		if (fieldsToGo > 0) {
			if ((fieldsToGo % 7) == 1) {
				this.nextLocation = new Kalaha(stones, firstLocation, fieldsToGo, player);
			} else if ((fieldsToGo % 7) == 0) {
				this.nextLocation = new Field(stones, firstLocation, fieldsToGo, player.getOpponent());
			} else {
				this.nextLocation = new Field(stones, firstLocation, fieldsToGo, player);
			}
		} else {
			this.nextLocation = firstLocation;
		}
	}

	Player getPlayer() {
		return this.player;
	}

	void continueMove(int stonesToGo) {
		this.stones++;
		if (stonesToGo > 1) {
			this.getNextLocation().continueMove(stonesToGo - 1);
		} else if (stonesToGo == 1) {
			this.getPlayer().getOpponent().changeTurn();
		}
	}

	Location getOpposite() {
		return this.getNextLocation().getOpposite().getNextLocation();
	}

	Location getNextLocation() {
		return this.nextLocation;
	}

	int getStones() {
		return this.stones;
	}

	boolean isPlayable() {
		return (this.getPlayer().hasTurn() && (this.getStones() != 0));
	}

	Kalaha getNextKalaha() {
		return this.getNextLocation().getNextKalaha();
	}

	// Mainly debug methods
	Location getNthLocationRelative(int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		} else if (n == 0) {
			return this;
		}
		return this.getNextLocation().getNthLocationRelative(n - 1);
	}

	int getTotalStonesToKalaha() {
		return this.getStones() + this.getNextLocation().getTotalStonesToKalaha();
	}

	boolean hasMove() {
		Kalaha thisPlayersKalaha = this.getNextKalaha();
		Field firstField = (Field) thisPlayersKalaha.getNextLocation().getNextKalaha().getNextLocation();
		int totalStones = firstField.getTotalStonesToKalaha();
		if (totalStones > thisPlayersKalaha.getStones()) {
			return true;
		}
		return false;
	}

	void moveStonesToKalaha() {
		this.getNextLocation().add(this.getStones());
		this.stones = 0;
		this.getNextLocation().moveStonesToKalaha();
	}

	void findWinner() {
		Kalaha kalahaOne = this.getNextKalaha();
		Kalaha kalahaTwo = kalahaOne.getNextLocation().getNextKalaha();
		if (kalahaOne.getStones() > kalahaTwo.getStones()) {
			kalahaOne.getPlayer().setWinner(WinnerRelative.SELF);
			kalahaTwo.getPlayer().setWinner(WinnerRelative.OTHER);
		} else if (kalahaOne.getStones() < kalahaTwo.getStones()) {
			kalahaOne.getPlayer().setWinner(WinnerRelative.OTHER);
			kalahaTwo.getPlayer().setWinner(WinnerRelative.SELF);
		} else {
			kalahaOne.getPlayer().setWinner(WinnerRelative.DRAW);
			kalahaTwo.getPlayer().setWinner(WinnerRelative.DRAW);
		}
	}

	void endGame() {
		Location loc = this.getNextKalaha().getNextLocation();
		loc.moveStonesToKalaha();
		loc = loc.getNextKalaha().getNextLocation();
		loc.moveStonesToKalaha();
		this.findWinner();
	}

	void add(int stones) {
		this.stones += stones;
	}
}
