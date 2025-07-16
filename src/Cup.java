import java.util.Random;

public class Cup {
	int value;

	Cup() {
		this.value = 0;
	}

	public int revealAction() {
		Random rand = new Random();
		return value = rand.nextInt(5) + 1;
	}
}
