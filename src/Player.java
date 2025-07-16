public class Player {
	final private int playerID;
	private boolean hasGift;
	final private Cup chosenCup;
	final private Game game;

	Player(Game game, int playerID) {
		this.hasGift = false;
		this.chosenCup = new Cup();
		this.game = game;
		this.playerID = playerID;
	}

	public int getPlayerID() {
		return playerID;
	}

	public boolean hasGift() {
		return hasGift;
	}

	public void setHasGift(boolean gift) {
		this.hasGift = gift;
	}

	public void takeAction() {
		Action action = chosenCup.revealAction();
		game.performAction(this, action);
	}
}
