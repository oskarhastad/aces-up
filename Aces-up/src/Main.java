import java.util.LinkedList;

public class Main {

	public static void main(String args[]) {

		int success = 0;
		int simulations = 1000;
		
		for (int i = 0; i < simulations; i++) {
			System.out.println("Running simulation no: " + (i+1));

			Deck deck = new Deck();
			Gamestate game = new Gamestate(new LinkedList<Card>(), new LinkedList<Card>(), new LinkedList<Card>(), new LinkedList<Card>());

			while (!deck.cards.isEmpty()) {
				game.dealCards(deck);
				game.removeCards();
				game.moveCardsSimulations(deck);
			}


			if (game.piles.get(0).size() == 1 && game.piles.get(1).size() == 1 && game.piles.get(2).size() == 1
					&& game.piles.get(3).size() == 1) {
				success++;
			}

		}

		System.out.println("Completed " + success + " out of " + simulations + ". Thus a success-rate of " + (( (float) success) / ((float) simulations)) * 100 + "%");

	}

}