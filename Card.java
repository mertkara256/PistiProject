class Card {
  private String suit;
  private String rank;
  private int point;
  
  public Card(String suit, String rank, int point) {
    this.suit = suit;
    this.rank = rank;
	this.point = point;
  }
  public String getView() {
  return rank + "" + suit;
}
  
  public String getSuit() {
    return suit;
  }
  
  public String getRank() {
    return rank;
  }
  public int getPoint()   {
	return point;
  }
}