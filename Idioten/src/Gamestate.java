import java.util.*;

public class Gamestate {
	
	
	static List<LinkedList<Card>> piles = new ArrayList<LinkedList<Card>>();
	static LinkedList<Card> pileOne = new LinkedList<Card>();	
	static LinkedList<Card> pileTwo = new LinkedList<Card>();	
	static LinkedList<Card> pileThree = new LinkedList<Card>();	
	static LinkedList<Card> pileFour = new LinkedList<Card>();	
	
	Gamestate() {
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
		 compareLast(piles.get(0),piles.get(1));
		 compareLast(piles.get(0),piles.get(2));
		 compareLast(piles.get(0),piles.get(3));
		 compareLast(piles.get(1),piles.get(2));
		 compareLast(piles.get(1),piles.get(3));
		 compareLast(piles.get(2),piles.get(3));
	 }
	 
	 void compareLast(List<Card> firstPile, List<Card> secondPile) {
		 Card lastPileOne = firstPile.get(firstPile.size()-1);
		 Card lastPileTwo = secondPile.get(secondPile.size()-1);
		 if(lastPileOne.suit == lastPileTwo.suit){
			 if(lastPileOne.value > lastPileTwo.value) {
				 secondPile.remove(secondPile.size()-1);
			 } else {
				 firstPile.remove(firstPile.size()-1);
			 }
		 }
	 }
	 
	 
	 void printGamestate() {
		 for(LinkedList<Card> p : piles) {
			 System.out.println("-----------------------");
			 for(Card c : p) {
				 c.printCard();
			 }
		 }
	 }
	
}

