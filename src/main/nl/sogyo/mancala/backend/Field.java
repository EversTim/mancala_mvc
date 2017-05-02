package nl.sogyo.mancala.backend;

class Field extends Location {

	private static final long serialVersionUID = 1L;

	Field() {
		this(4);
	}

	Field(int stones, Location firstLocation, int fieldsToGo, Player player) {
		super(stones, firstLocation, fieldsToGo, player);
		this.stones = stones;
	}

	Field(int stones) {
		this.stones = stones;
		this.player = new Player();
		this.nextLocation = new Field(stones, this, 13, this.player);
	}

	void doMove() {
		if (this.hasMove()) {
			if (this.isPlayable()) {
				int stonesToMove = this.getStones();
				this.empty();
				this.getNextLocation().continueMove(stonesToMove);
			} else {
				throw new IllegalArgumentException("Cannot move opponents fields or your empty fields!");
			}
		} else {
			this.endGame();
		}
	}

	int takeStones() {
		int stonesToTake = this.getStones();
		this.empty();
		return stonesToTake;
	}

	@Override
	void continueMove(int stonesToGo) {
		super.continueMove(stonesToGo);
		if (stonesToGo == 1) {
			if (this.getStones() == 1) {
				if (!this.getPlayer().hasTurn()) {
					int stonesTaken = ((Field) this.getOpposite()).takeStones();
					this.empty();
					Kalaha ownKalaha = this.getNextKalaha();
					ownKalaha.add(stonesTaken + 1);
				}
			}
		}
	}

	private void empty() {
		this.stones = 0;
	}

	// For testing
	static Field getTakeStonesTestSetup() {
		Field field = new Field();
		Field fieldFour = (Field) field.getNthLocationRelative(4);
		fieldFour.stones = 0;
		return field;
	}

	static Field endInEmptyOpponentFieldTestSetup() {
		Field field = new Field();
		field.getNextKalaha().getNextLocation().stones = 0;
		return field;
	}

	static Field getCheckWinnerPlayerOneTestSetup() {
		Field field = new Field(0);
		Kalaha kal = field.getNextKalaha();
		kal.stones = 6;
		return field;
	}

	static Field getCheckWinnerPlayerTwoTestSetup() {
		Field field = Field.getCheckWinnerPlayerOneTestSetup();
		Location secondOppField = field.getNextKalaha().getNextLocation().getNextLocation();
		secondOppField.stones = 7;
		return field;
	}
}
