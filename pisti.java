import java.util.Scanner;
import java.util.Random;

public class pisti{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		Random r = new Random();
		Deck deck = new Deck();
		deck.shuffle();
		for (int i = 0; i<deck.numofcards; i++){
			System.out.println(deck.cards[i].getView());
		}
		deck.cutDeck(deck);
		int pointsplayer = 0;
		int pointscomputer = 0;
		System.out.println(0%2);
 	}
}