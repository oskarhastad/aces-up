import java.util.*;

public class Main {
	
	public static void main(String args[]){
		int failed = 0;
		int success = 0;

		for(int i = 0; i < 1000; i++) {
			Deck deck = new Deck();
			Gamestate game = new Gamestate(new LinkedList<Card>(), new LinkedList<Card>(), new LinkedList<Card>(), new LinkedList<Card>());
		
			while(!deck.deck.isEmpty()) {
				game.dealCards(deck);
				game.compareAndRemove();
				game.moveCards();
			}
			
			if(game.piles.get(0).size() == 1 && game.piles.get(1).size() == 1 && game.piles.get(2).size() == 1 &&game.piles.get(3).size() == 1) {
				success++;
			} else {
				failed++;
			}
			
		}
		System.out.println("Fails = " + failed);
		System.out.println("Success = " + success);
		
	}
	

	


	 

	
}