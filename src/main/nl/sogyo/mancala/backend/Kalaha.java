package nl.sogyo.mancala.backend;

public class Kalaha extends Location {

	private static final long serialVersionUID = 1L;

	Kalaha() {
		this.stones = 0;
		this.nextLocation = this;
	}

	Kalaha(int stones, Location firstLocation, int fieldsToGo, Player player) {
		super(stones, firstLocation, fieldsToGo, player);
		this.stones = 0;
	}

	@Override
	boolean isPlayable() {
		return false;
	}

	@Override
	void continueMove(int stonesToGo) {
		if (this.getPlayer().hasTurn()) {
			super.continueMove(stonesToGo);
			if (stonesToGo == 1) {
				this.getPlayer().getOpponent().changeTurn();
			}
		} else {
			this.getNextLocation().continueMove(stonesToGo);
		}
	}

	@Override
	int getTotalStonesToKalaha() {
		return this.getStones();
	}

	@Override
	void moveStonesToKalaha() {
		return;
	}

	@Override
	Location getOpposite() {
		return this;
	}

	@Override
	Kalaha getNextKalaha() {
		return this;
	}
}
