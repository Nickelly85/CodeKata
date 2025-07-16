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

	private void initializePlayers() {
		for(int i = 0; i < numPlayers; i++) {
			players[i] = new Player(this, i);
		}
	}

	public void isGameOver() {
		if(numGiftsOnTable == 0) {
			gameOver = true;
		}
	}

	public void giftTakenFromTable() {
		if(numGiftsOnTable > 0) {
			numGiftsOnTable--;
		}
	}

	public Player findPlayerWithGift(int currentPlayer) {
		for(int i = 0; i < numPlayers; i++) {
			if(players[i].playerID != currentPlayer && players[i].hasGift) {
				return players[i];
			}
		}
		return null;
	}
}
