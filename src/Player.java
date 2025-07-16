public class Player {
	int playerID;
	boolean hasGift;
	Cup chosenCup;
	final private Game game;

	Player(Game game, int playerID) {
		this.hasGift = false;
		this.chosenCup = new Cup();
		this.game = game;
		this.playerID = playerID;
	}

	public void takeAction() {
		int action = chosenCup.revealAction();

		switch (action) {
			case 1 -> takeOne();
			case 2 -> trade();
			case 3 -> shiftLeft();
			case 4 -> shiftRight();
			case 5 -> steal();
		}
	}

	private void takeOne() {
		if (hasGift) {
			return;
		}
		hasGift = true;
		game.giftTakenFromTable();
	}

	private void trade() {
		if(hasGift) {
			return;
		}
		hasGift = true;
		game.giftTakenFromTable();
	}

	private void shiftLeft() {
		boolean[] playerGiftStatus = copyPlayerGiftStatus();

		for(int i = 0; i < game.numPlayers; i++) {
			game.players[i].hasGift = playerGiftStatus[(i + 1) % game.numPlayers];
		}
	}

	private void shiftRight() {
		boolean[] playerGiftStatus = copyPlayerGiftStatus();

		for(int i = 0; i < game.numPlayers; i++) {
			game.players[i].hasGift = playerGiftStatus[(i - 1 + game.numPlayers) % game.numPlayers];
		}
	}

	private void steal() {
		if(hasGift) {
			return;
		}

		Player targettedPlayer = game.findPlayerWithGift(playerID);

		if(targettedPlayer != null) {
			hasGift = true;
			targettedPlayer.hasGift = false;
		}
	}

	private boolean[] copyPlayerGiftStatus() {
		boolean[] giftStatus = new boolean[game.numPlayers];

		for(int i = 0; i < game.numPlayers; i++) {
			giftStatus[i] = game.players[i].hasGift;
		}

		return giftStatus;
	}
}
