import java.util.*;

public class Gamestate {
	
	
	List<LinkedList<Card>> piles = new ArrayList<LinkedList<Card>>();
	
	Gamestate(LinkedList<Card> pileOne, LinkedList<Card> pileTwo, LinkedList<Card> pileThree, LinkedList<Card> pileFour) {
		piles.add(pileOne);
		piles.add(pileTwo);
		piles.add(pileThree);
		piles.add(pileFour);
	
	}
	void dealCards(Deck deck) {
		for(LinkedList<Card> pile : piles)
			pile.add(deck.drawRandomCard());
		
	}

	void compareAndRemove() {	
		boolean changed = true;
		while (changed) {
			changed = false;
			System.out.println("Checking once");
			for(int i = 0; i < piles.size();i++ ) {
				for (int j = i+1; j < piles.size(); j++ ) {
					if(!piles.get(i).isEmpty() && !piles.get(j).isEmpty()) {
						if (compareAndRemoveLast(piles.get(i),piles.get(j))) {
						changed = true;
						}
					}
				}
			}
		}
	}
	 boolean compareAndRemoveLast(List<Card> firstPile, List<Card> secondPile) {
		 Card lastPileOne = firstPile.get(firstPile.size()-1);
		 Card lastPileTwo = secondPile.get(secondPile.size()-1);
		 if(lastPileOne.suit == lastPileTwo.suit){
			 if(lastPileOne.value > lastPileTwo.value) {
				 secondPile.remove(secondPile.size()-1);
				 return true;
			 } else {
				 firstPile.remove(firstPile.size()-1);
				 return true;
			 }
		 }
		 return false;
	 }
	 
	 
	 void printGamestate() {
		 for(LinkedList<Card> pile : piles) {
			 System.out.println("-----------------------");
			 for(Card c : pile) {
				 c.printCard();
			 }
		 }
	 }
	
}

