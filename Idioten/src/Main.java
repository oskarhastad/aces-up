
public class Main {
	
	public static void main(String args[]){
		Deck deck = new Deck();
		Gamestate game = new Gamestate();
		game.dealCards(deck);
		game.printGamestate();
		game.dealCards(deck);
		game.printGamestate();
		game.compareAndRemove();
		game.printGamestate();
		
		

		}
	

	


	 

	
}