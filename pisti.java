import java.util.Scanner;
import java.util.Random;
import java.nio.file.Paths;
import java.util.Formatter;
import java.io.IOException;
import java.io.FileWriter;

public class pisti {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Random r = new Random();
    Deck deck = new Deck();
    deck.shuffle();
    deck.cutDeck(deck);
    int pointsplayer = 0;
    int pointscomputer = 0;
	int cardofplayer = 0;
	int cardofcomputer = 0;
    int numofboard = 4;
    Card[] playershand = new Card[4];
    Card[] computershand = new Card[4];
    Card[] board = new Card[52];
    for (int i = 0; i < 4; i++) {
      board[i] = deck.cards[i];
    }
    for (int i = 0; i < 48; i++) {
      if (i % 8 == 0) {
        for (int j = 0; j < 4; j++) {
          playershand[j] = deck.cards[i + 2 * j + 4];
          computershand[j] = deck.cards[i + 2 * j + 5];
        }
      }
      if (i % 2 == 0) {
        System.out.println("There are " + numofboard + " cards on the board");
        System.out.println("Now it is your turn to play, your cards are: ");
        for (int j = 0; j < 4; j++) {
          if (playershand[j] == null) continue;
          System.out.println(playershand[j].getView());
        }
        if (numofboard != 0) System.out.println("Card on board is: " + board[numofboard - 1].getView());
        String enterednumber;
        int move;
        while (true) {
          System.out.println("Choose a card to throw (0-3)");
          try {
            enterednumber = sc.nextLine();
            move = Integer.parseInt(enterednumber);
            if (playershand[move] == null) {
              System.out.println("You already used that card.");
              continue;
            }
            board[numofboard] = playershand[move];
          } catch (Exception e) {
            System.out.println("Please enter valid number between 0 and 3");
            continue;
          }
          break;
        }
        board[numofboard] = playershand[move];
        numofboard++;
        if (numofboard == 1) {
          playershand[move] = null;
          continue;
        }
        if (playershand[move].getRank().equals(board[numofboard - 2].getRank()) || playershand[move].getRank().equals("J")) {
          if (board[0].getRank() == board[1].getRank()) {
            pointsplayer += 10;
            System.out.println("You made pisti!");
          }
          for (int k = 0; k < numofboard; k++) {
            pointsplayer += board[k].getPoint();
            board[k] = null;
			cardofplayer++;
          }
          numofboard = 0;
        }
        playershand[move] = null;
      }
      if (i % 2 != 0) {
        numofboard += 1;
        board[numofboard - 1] = computershand[((i % 8) - 1) / 2];
        System.out.println("Your opponent played:" + computershand[((i % 8) - 1) / 2].getView());
        if (numofboard == 1) {
          computershand[((i % 8) - 1) / 2] = null;
          continue;
        }
        if (computershand[((i % 8) - 1) / 2].getRank().equals(board[numofboard - 2].getRank()) || computershand[((i % 8) - 1) / 2].getRank().equals("J")) {
          if (board[0].getRank() == board[1].getRank()) {
            pointscomputer += 10;
            System.out.println("Your opponent made pisti!");
          }
          for (int k = 0; k < numofboard; k++) {
            pointscomputer += board[k].getPoint();
            board[k] = null;
			cardofcomputer++;
          }
          numofboard = 0;
        }
        computershand[((i % 8) - 1) / 2] = null;
      }
      if (i == 47 && numofboard != 0) {
        for (int m = 0; m < numofboard; m++) {
          pointscomputer += board[m].getPoint();
          board[m] = null;
		  cardofcomputer++;
        }
      }
	  if ( i == 47 && cardofcomputer<cardofplayer) pointsplayer += 3;
	  if ( i == 47 && cardofcomputer>cardofplayer) pointscomputer += 3;
    }
    System.out.println("Your point is: " + pointsplayer);
    System.out.println("Computers point is: " + pointscomputer);
    if (pointsplayer < pointscomputer) System.out.println("Computer won!");
    if (pointsplayer > pointscomputer) System.out.println("You won!");
    System.out.print("Enter name: ");
    String name = sc.nextLine();
    String[] score = {
      "Name: ",
      "Score: "
    };
    Scanner reader = null;
    Formatter f = null;
    FileWriter fw = null;
    int place = 0;
    String[] lines = new String[10];

    try {
      reader = new Scanner(Paths.get("top10.txt"));
      for (int i = 0; i < 10; i++) {
        if (reader.hasNextLine()) {
          lines[i] = reader.nextLine();
        } else {
          lines[i] = null;
        }
      }
      reader.close();

      for (int i = 0; i < 10; i++) {
        if (lines[i] == null || pointsplayer > Integer.parseInt(lines[i].split(",")[1])) {
          place = i;
          break;
        }
      }

      for (int i = 9; i > place; i--) {
        lines[i] = lines[i - 1];
      }
      lines[place] = name + "," + Integer.toString(pointsplayer);

      fw = new FileWriter("top10.txt", false);
      for (int i = 0; i < 10; i++) {
        if (lines[i] != null) {
          fw.write(lines[i] + "\n");
        }
      }
      fw.close();
    } catch (Exception e) {
      System.err.println(e);
    } finally {
      if (f != null) {
        f.close();
      }
      if (reader != null) {
        reader.close();
      }
    }
    try {
      reader = new Scanner(Paths.get("top10.txt"));
      while (reader.hasNextLine()) {
        String[] info = reader.nextLine().split(",");
        for (int i = 0; i < info.length; i++) {
          System.out.println(score[i] + info[i].trim());
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        reader.close();
      }
    }

  }
}