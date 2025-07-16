import java.util.Random;

public class Cup {
	public Action revealAction() {
		Random rand = new Random();
		Action[] actions = Action.values();
		return actions[rand.nextInt(actions.length)];
	}
}
