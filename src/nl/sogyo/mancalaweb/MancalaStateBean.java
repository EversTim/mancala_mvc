package nl.sogyo.mancalaweb;

import java.util.List;

public class MancalaStateBean {
	public MancalaStateBean() {
		// Required empty constructor
	}

	private List<Integer> stones;
	private int currentTurn = 0;
	private String winner = "NONE";

	public MancalaStateBean(List<Integer> stones, int currentTurn, String winner) {
		this.stones = stones;
		this.currentTurn = currentTurn;
		this.winner = winner;
	}

	public List<Integer> getStones() {
		return stones;
	}

	public int getCurrentTurn() {
		return currentTurn;
	}

	public String getWinner() {
		return winner;
	}
}
