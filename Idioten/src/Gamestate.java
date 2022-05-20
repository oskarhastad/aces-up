import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Gamestate {

	List<LinkedList<Card>> piles = new ArrayList<LinkedList<Card>>();
	List<LinkedList<Card>> empty = new LinkedList<LinkedList<Card>>();
	List<LinkedList<Card>> canMove = new LinkedList<LinkedList<Card>>();

	Gamestate(LinkedList<Card> pileOne, LinkedList<Card> pileTwo, LinkedList<Card> pileThree,
			LinkedList<Card> pileFour) {
		piles.add(pileOne);
		piles.add(pileTwo);
		piles.add(pileThree);
		piles.add(pileFour);

	}

	void dealCards(Deck deck) {
		for (LinkedList<Card> pile : piles)
			pile.add(deck.drawRandomCard());

	}

	void compareAndRemove() {
		boolean changed = true;
		while (changed) {
			changed = false;
			for (int i = 0; i < piles.size(); i++) {
				for (int j = i + 1; j < piles.size(); j++) {
					if (!piles.get(i).isEmpty() && !piles.get(j).isEmpty()) {
						if (compareAndRemoveLast(piles.get(i), piles.get(j))) {
							changed = true;
						}
					}
				}
			}
		}
	}

	boolean compareAndRemoveLast(LinkedList<Card> firstPile, LinkedList<Card> secondPile) {
		Card lastPileOne = firstPile.getLast();
		Card lastPileTwo = secondPile.getLast();
		if (lastPileOne.suit == lastPileTwo.suit) {
			if (lastPileOne.value > lastPileTwo.value) {
				secondPile.remove(secondPile.size() - 1);
				return true;
			} else {
				firstPile.remove(firstPile.size() - 1);
				return true;
			}
		}
		return false;
	}

	int simulateMany(Deck deck, int simulations) {
		int success = 0;
		for (int i = 0; i < simulations; i++) {
			Gamestate temp = cloneGamestate();
			Deck tempDeck = deck.cloneDeck();
			while (!tempDeck.cards.isEmpty()) {
				temp.compareAndRemove();
				temp.moveCards();
				temp.dealCards(tempDeck);
				temp.compareAndRemove();
				temp.moveCards();
			}
			if (temp.piles.get(0).size() == 1 && temp.piles.get(1).size() == 1 && temp.piles.get(2).size() == 1
					&& temp.piles.get(3).size() == 1) {
				success++;
			}
		}
		return success;
	}

	// void moveCardsWithSimulations(Deck deck) {
	// fillEmptyAndCanMove();
	// if (!empty.isEmpty() && !canMove.isEmpty()) {
	// for (LinkedList<Card> moveTo : empty) {
	// Gamestate candidate = cloneGamestate();
	// candidate.fillEmptyAndCanMove();
	// int success = -1;
	// for (LinkedList<Card> moveFrom : candidate.canMove) {
	// Gamestate candidateTemp = cloneGamestate();
	// candidateTemp.fillEmptyAndCanMove();
	// moveTo.add(moveFrom.getLast());
	// moveFrom.removeLast();
	// int successTemp = candidateTemp.simulateMany(deck, 1000);
	// // System.out.println("SuccessTemp = " + successTemp);
	// if (successTemp >= success) {
	// success = successTemp;
	// candidate = candidateTemp;
	// }
	// }

	// piles = candidate.piles;
	// candidate.fillEmptyAndCanMove();
	// }
	// }

	// }

	void moveCardsWithSimulations(Deck deck) {
		checkEmptyAndCanMove();
			while (!empty.isEmpty() && !canMove.isEmpty()) {
				Gamestate candidate = cloneGamestate();
				int success = 0;
				for (int j = 0; j < canMove.size(); j++) {
					Gamestate temporary = cloneGamestate();
					temporary.checkEmptyAndCanMove();
					temporary.empty.get(0).add(temporary.canMove.get(j).getLast());
					temporary.canMove.get(j).removeLast();
					temporary.compareAndRemove();
					int successTemp = 0;
					if(canMove.size() > 1){
					 successTemp = temporary.simulateMany(deck, 1000);
					}
					if (successTemp >= success) {
						candidate = temporary;
						success = successTemp;
					}
				}

				piles = candidate.piles;
				checkEmptyAndCanMove();
			}
	}

	void moveCards() {
		checkEmptyAndCanMove();
			while (!empty.isEmpty() && !canMove.isEmpty()) {
				Gamestate candidate = cloneGamestate();
				for (int j = 0; j < canMove.size(); j++) {
					Gamestate temporary = cloneGamestate();
					temporary.checkEmptyAndCanMove();
					temporary.empty.get(0).add(temporary.canMove.get(j).getLast());
					temporary.canMove.get(j).removeLast();
					temporary.compareAndRemove();
					if (temporary.amountOfCards() <= candidate.amountOfCards()) {
						candidate = temporary;
					}
				}
				piles = candidate.piles;
				checkEmptyAndCanMove();
			}
	}

	void moveCardsRandom() {
		checkEmptyAndCanMove();
		if (!empty.isEmpty() && !canMove.isEmpty()) {
			for (LinkedList<Card> pile : empty) {
				if (!canMove.isEmpty()) {
					int randomNum = ThreadLocalRandom.current().nextInt(0, canMove.size());
					pile.add(canMove.get(randomNum).getLast());
					canMove.get(randomNum).removeLast();
					if (canMove.get(randomNum).size() < 2) {
						canMove.remove(randomNum);
					}
				}
			}

		}

	}

	void checkEmptyAndCanMove() {
		empty.clear();
		canMove.clear();
		for (LinkedList<Card> checkPile : piles) {
			if (checkPile.isEmpty()) {
				empty.add(checkPile);
			}
			if (checkPile.size() >= 2) {
				canMove.add(checkPile);
			}
		}

	}

	int amountOfCards() {
		int amount = 0;
		for (LinkedList<Card> pile : piles) {
			amount = amount + pile.size();
		}
		return amount;
	}

	Gamestate cloneGamestate() {
		List<LinkedList<Card>> copy = new ArrayList<LinkedList<Card>>();
		for (int i = 0; i < piles.size(); i++) {
			LinkedList<Card> tempList = new LinkedList<Card>();
			copy.add(tempList);
			for (int j = 0; j < piles.get(i).size(); j++) {
				Card tempcard = new Card(piles.get(i).get(j).value, piles.get(i).get(j).suit);
				copy.get(i).add(tempcard);
			}
		}
		return new Gamestate(copy.get(0), copy.get(1), copy.get(2), copy.get(3));
	}

	void printGamestate() {

		System.out.println("Gamestate starts: ");
		for (LinkedList<Card> pile : piles) {
			System.out.println("-----------------------");
			for (Card c : pile) {
				c.printCard();
			}
		}

		System.out.println("Gamestate ends");
	}
}