import java.util.Scanner;
import java.util.Random;

public class pisti{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		Random r = new Random();
		Deck deck = new Deck();
		deck.shuffle();
		deck.cutDeck(deck);	
		int pointsplayer = 0;
		int pointscomputer = 0;
		int numofboard = 4;
		Card[] playershand = new Card[4];
		Card[] computershand = new Card[4];
		Card[] board = new Card[52];
		for (int i = 0; i<4; i++){
			board[i] = deck.cards[i];
		}
		for(int i = 0; i<48; i++){
			if(i%8==0){
				for (int j = 0; j<4; j++){
					playershand[j] = deck.cards[i+2*j+4];
					computershand[j] = deck.cards[i+2*j+5];
				}
			}
			if(i%2==0){
				System.out.println("Now it is your turn to play, your cards are: ");
				for (int j = 0; j<4; j++){
					if (playershand[j]==null) continue;
					System.out.println(playershand[j].getView());
				}
				System.out.println("Enter number between 0-3");
				int move = sc.nextInt();
				board[numofboard] = playershand[move];
				numofboard++;
				if (playershand[move].getRank().equals(board[numofboard-1]) || playershand[move].getRank().equals("J")){
					for(int k = 0; k<numofboard; k++){
						pointsplayer += board[k].getPoint();
						board[k] = null;
					}
					numofboard = 0;
				}
				playershand[move] = null;
			}
			if(i%2!=0){
				if(i%8==1) board[numofboard] = computershand[0];
				if(i%8==3) board[numofboard] = computershand[1];
				if(i%8==5) board[numofboard] = computershand[2];
				if(i%8==7) board[numofboard] = computershand[3];
			}
		}
 	}
	
}