import java.util.*;

public class Main {
	
	public static void main(String args[]){
		Deck deck = new Deck();
		Gamestate game = new Gamestate(new LinkedList<Card>(), new LinkedList<Card>(), new LinkedList<Card>(), new LinkedList<Card>());
		game.dealCards(deck);
		game.printGamestate();
		game.dealCards(deck);
		game.dealCards(deck);
		game.dealCards(deck);
		game.dealCards(deck);
		game.printGamestate();
		game.compareAndRemove();
		game.printGamestate();

		
		

		}
	

	


	 

	
}