package nl.sogyo.mancala.backend;

import java.io.Serializable;

class Player implements Serializable {

	private static final long serialVersionUID = 1L;

	private Player opponent;
	private boolean hasTurn;
	private WinnerRelative winner;

	Player() {
		this.opponent = new Player(this);
		this.hasTurn = true;
		this.winner = WinnerRelative.UNDECIDED;
	}

	Player(Player opponent) {
		this.opponent = opponent;
		this.hasTurn = false;
		this.winner = WinnerRelative.UNDECIDED;
	}

	Player getOpponent() {
		return this.opponent;
	}

	boolean hasTurn() {
		return this.hasTurn;
	}

	void changeTurn() {
		this.getOpponent().setTurn(this.hasTurn);
		this.hasTurn = !this.hasTurn;
	}

	void setTurn(boolean hasTurn) {
		this.hasTurn = hasTurn;
	}

	WinnerRelative getWinner() {
		return this.winner;
	}

	void setWinner(WinnerRelative winner) {
		this.winner = winner;
	}
}
