public class Main {
	public static void main(String[] args) {
		int numPlayers = 23;
		int numIterations = 1000;
		int results = 0;
		float avg = 0;

		int[] roundResults = runIterations(numPlayers, numIterations);
		printResults(roundResults);
	}

	private static int[] runIterations(int numPlayers, int numIterations) {
		int[] results = new int[numIterations];

		for(int i = 0; i < numIterations; i++) {
			Game game = new Game(numPlayers);

			results[i] = game.playGame();
		}
		return results;
	}

	private static void printResults(int[] roundResults) {
		int total = 0;

		for (int roundResult : roundResults) {
			total += roundResult;
		}

		float average = (float) total / roundResults.length;
		System.out.println("Average rounds over " + roundResults.length + " iterations: " + average);
	}
}