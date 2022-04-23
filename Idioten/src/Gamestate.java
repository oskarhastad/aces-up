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
	
	
	void removeAndMove() {
		
		
		
	}

	void compareAndRemove() {	
		boolean changed = true;
		while (changed) {
			changed = false;
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
	 boolean compareAndRemoveLast(LinkedList<Card> firstPile, LinkedList<Card> secondPile) {
		 Card lastPileOne = firstPile.getLast();
		 Card lastPileTwo = secondPile.getLast();
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
	 
	 
	 void moveCards() {
		 LinkedList<LinkedList<Card>> empty = new LinkedList<LinkedList<Card>>();
		 LinkedList<LinkedList<Card>> canMove = new LinkedList<LinkedList<Card>>();
		 for (int i = 0; i < piles.size(); i++) {
			 if(piles.get(i).isEmpty()) {
				 empty.add(piles.get(i));
			 }
			 if(piles.get(i).size() >= 2) {
				 canMove.add(piles.get(i));
			 }
		 }
		 
		 if(!empty.isEmpty() && !canMove.isEmpty()) {
			 for (LinkedList<Card> pile : empty) {
				 if(!canMove.isEmpty()) {
					 pile.add(canMove.getLast().getLast());
					 canMove.getLast().removeLast();
					 if(canMove.getLast().size() < 2) {
						 canMove.removeLast();
					 }
				 }
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
	 
	 
	 	Gamestate cloneGamestate(){
	 		List<LinkedList<Card>> copy = new ArrayList<LinkedList<Card>>();
	 		for(int i = 0; i < piles.size(); i++) {
	 			LinkedList<Card> tempList = new LinkedList<Card>();
	 			copy.add(tempList);
	 			for(int j = 0; j < piles.get(i).size(); j++) {
	 				Card tempcard = new Card(piles.get(i).get(j).value, piles.get(i).get(j).suit);
	 				copy.get(i).add(tempcard);
	 			}
	 		}
		 return new Gamestate(copy.get(0),copy.get(1),copy.get(2),copy.get(3));
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

