import java.util.Scanner;
import java.util.Random;

class Deck {
  public static String[] suits = {"\u2660","\u2663","\u2665","\u2666"};
  public static String[] ranks = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
  
  private Random r = new Random();
  public static int numofcards = suits.length * ranks.length;

  public Card[] cards;
  public int currentCard;

  public Deck() {
    cards = new Card[numofcards];
    currentCard = 0;
    for (int i = 0; i < suits.length; i++) {
      for (int j = 0; j < ranks.length; j++) {
		  int m = 1;
		  if (suits[i] == "\u2666" && ranks[j] == "10") m=3;
		  if (suits[i] == "\u2663" && ranks[j] == "2") m=2;
        cards[currentCard++] = new Card(suits[i], ranks[j], m);
      }
    }
  }

  public void shuffle() {
    for (int i = cards.length - 1; i > 0; i--) {
      int random = r.nextInt(52);
      Card temp = cards[i];
      cards[i] = cards[random];
      cards[random] = temp;
    }
  }
  public static void cutDeck(Deck deck) {
    Scanner sc = new Scanner(System.in);

    String usersplitpoint;
	int splitpoint;
	while(true) {
              System.out.print("Enter the index where you want to cut the deck (1-52): ");
            try {
				usersplitpoint = sc.nextLine();
                splitpoint = Integer.parseInt(usersplitpoint);
			} 
             catch (Exception e) {
                System.out.println("Please enter valid number between 1 and 52.");
                continue;
            }
            break;
			}

    Card[] tempDeck = new Card[deck.numofcards];

    for (int i = splitpoint; i < deck.numofcards; i++) {
      tempDeck[i - splitpoint] = deck.cards[i];
    }

    for (int i = 0; i < splitpoint; i++) {
      tempDeck[i + deck.numofcards - splitpoint] = deck.cards[i];
    }

    deck.cards = tempDeck;
  }
}
