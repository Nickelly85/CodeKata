public class Game {
	int numPlayers;
	int roundCounter;
	boolean gameOver;
	Player[] players;
	int numGiftsOnTable;

	Game(int numPlayers) {
		this.numPlayers = numPlayers;
		this.players = new Player[numPlayers];
		this.roundCounter = 0;
		this.gameOver = false;
		this.numGiftsOnTable = numPlayers;
	}

	public int playGame() {
		initializePlayers();
		while(!gameOver) {
			for(int j = 0; j < numPlayers; j++) {
				players[j].takeAction();
				isGameOver();
				if(gameOver) {
					break;
				}
			}
			roundCounter++;
		}
		return roundCounter;
	}

	public void isGameOver() {
		if(numGiftsOnTable == 0) {
			gameOver = true;
		}
	}

	public Player findPlayerWithGift(int currentPlayer) {
		for(int i = 0; i < numPlayers; i++) {
			if(players[i].getPlayerID() != currentPlayer && players[i].hasGift()) {
				return players[i];
			}
		}
		return null;
	}

	public void takeAction(Player currentPlayer, Action action) {
		switch(action) {
			case TAKEONE -> takeOne(currentPlayer);
			case TRADE -> trade(currentPlayer);
			case SHIFTLEFT -> shiftLeft();
			case SHIFTRIGHT -> shiftRight();
			case STEAL -> steal(currentPlayer);
		}
	}

	private void initializePlayers() {
		for(int i = 0; i < numPlayers; i++) {
			players[i] = new Player(this, i);
		}
	}

	private void takeOne(Player currentPlayer) {
		if (currentPlayer.hasGift()) {
			return;
		}
		currentPlayer.setHasGift(true);
		giftTakenFromTable();
	}

	private void trade(Player currentPlayer) {
		if(currentPlayer.hasGift()) {
			return;
		}
		currentPlayer.setHasGift(true);
		giftTakenFromTable();
	}

	private void shiftLeft() {
		boolean[] playerGiftStatus = copyPlayerGiftStatus();

		for(int i = 0; i < numPlayers; i++) {
			players[i].setHasGift(playerGiftStatus[(i + 1) % numPlayers]);
		}
	}

	private void shiftRight() {
		boolean[] playerGiftStatus = copyPlayerGiftStatus();

		for(int i = 0; i < numPlayers; i++) {
			players[i].setHasGift(playerGiftStatus[(i - 1 + numPlayers) % numPlayers]);
		}
	}

	private void steal(Player currentPlayer) {
		if(currentPlayer.hasGift()) {
			return;
		}

		Player targettedPlayer = findPlayerWithGift(currentPlayer.getPlayerID());

		if(targettedPlayer != null) {
			currentPlayer.setHasGift(true);
			targettedPlayer.setHasGift(false);
		}
	}

	private boolean[] copyPlayerGiftStatus() {
		boolean[] giftStatus = new boolean[numPlayers];

		for(int i = 0; i < numPlayers; i++) {
			giftStatus[i] = players[i].hasGift();
		}

		return giftStatus;
	}

	private void giftTakenFromTable() {
		if(numGiftsOnTable > 0) {
			numGiftsOnTable--;
		}
	}
}
