import java.util.*;

public class DeckOfCards {
	public static class BlackJack {
		BlackJackPlayer p1;
		BlackJackPlayer p2;
		BlackJackPlayer nextPlayer;
		
		Deck52 deck52;
		
		public BlackJack(BlackJackPlayer p1, BlackJackPlayer p2) {
			this.deck52 = new Deck52();
			this.p1 = p1;
			this.p2 = p2;
			this.nextPlayer = p1;		
		}
		
		public BlackJackPlayer getWinner() {
			int player1Score = p1.getBestScoreInHand();
			if(player1Score > 21) {
				return p2;
			}
			int player2Score = p1.getBestScoreInHand();
			if(player2Score > 21) {
				return p1;
			}
			if(p1.isStanding() && p2.isStanding()) {
				if(player1Score == player2Score) {
					return null;
				} else if(player1Score > player2Score) {
					return p1;
				} else {
					return p2;
				}
			}
			return null;
		}
		
		public BlackJackPlayer getNextPlayer() {
			return this.nextPlayer;
		}
		
		public boolean takeAction(BlackJackPlayer player, BlackJack_Action action) {
			if(player != nextPlayer) {
				return false;
			}
			if(!getOtherPlayer().isStanding()) {
				nextPlayer = getOtherPlayer();
			}
			
			if(action == BlackJack_Action.HIT) {
				player.addCardToHand(deck52.getNextCard());
				return true;				
			} else if(action == BlackJack_Action.STAND) {
				if(getOtherPlayer().isStanding()) {
					nextPlayer = null;
				}
				return true;
			} else {
				return false; //should not happen
			}
		}
		
		public BlackJackPlayer getOtherPlayer() {
			if(nextPlayer == p1) {
				return p1;
			} else {
				return p2;
			}
		}
	}
	
	public static class BlackJackPlayer {
		LinkedList<Card> cardsInHand;
		boolean isStanding;
		
		public BlackJackPlayer() {
			cardsInHand = new LinkedList<Card>();
			isStanding = false;
		}
		
		public boolean isStanding() {
			return this.isStanding;
		}
		
		public void setStanding() {
			this.isStanding = true;
		}
		
		public void addCardToHand(Card card) {
			cardsInHand.add(card);
		}
		
		public int getBestScoreInHand() {
			int aces = 0;
			int score = 0;
			for(Card card : cardsInHand) {
				if(card.value == Value.ACE) {
					aces++;
				} else {
					score += card.value.getScore();
				}
			}
			while(aces-- > 0) {
				if(score + aces <= 10) {
					score += 11;
				} else {
					score++;
				}
			}
			return score;
		}
	}
	
	private static enum BlackJack_Action {
		STAND,HIT;
	}
	
	private static class Deck52 extends Deck {
		public Deck52() {
			super();
			for(Type type : Type.values()) {
				for(Value value : Value.values()) {
					if(value == Value.ELEVEN || value == Value.TWELVE) {
						continue;
					}
					this.addCard(new Card(type,value));
				}
			}
			this.shuffle();
		}
	}
	
	
	private static abstract class Deck {
		Queue<Card> cards;
		
		private Deck() {
			cards = new LinkedList<Card>();
		}
		
		public void shuffle() {
			Card[] cardsArray = cards.toArray(new Card[cards.size()]);
			for(int i = cardsArray.length-1; i > 0; i--) {
				int r = (int) Math.floor(Math.random() * i);
				Card tmp = cardsArray[r];
				cardsArray[r] = cardsArray[i];
				cardsArray[i] = tmp;
			}
			cards.clear();
			Collections.addAll(cards,cardsArray);
		}
		
		public void addCard(Card card) {
			cards.add(card);
		}
		
		public boolean hasNextCard() {
			return cards.size() > 0;
		}
		
		public Card getNextCard() {
			return cards.poll();
		}
	}
	
	private static class Card {
		Type type;
		Value value;
		
		public Card(Type t, Value v) {
			this.type = t;
			this.value = v;
		}
		
		public String toString() {
			return this.value + " of " + this.type;
		}
	}
	
	private static enum Type {
		DIAMOND, HEART, CLUB, SPADE;
		
		public String toString() {
			return this.name().charAt(0) + this.name().substring(1,this.name().length()).toLowerCase() + "s";
		}
	}
	
	private static enum Value {
		TWO(2),
		THREE(3),
		FOUR(4),
		FIVE(5),
		SIX(6),
		SEVEN(7),
		EIGHT(8),
		NINE(9),
		TEN(10),
		ELEVEN(11),
		TWELVE(12),
		JACK(10),
		QUEEN(10),
		KING(10),
		ACE(11);
		
		private int score;
		
		private Value(int score) {
			this.score = score;
		}
		
		public int getScore() {
			return this.score;
		}

		public String toString() {
			return this.name().charAt(0) + this.name().substring(1,this.name().length()).toLowerCase();
		}
	}
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		// Core Function here
		Deck52 deck52 = new Deck52();
		while(deck52.hasNextCard()) {
			System.out.println(deck52.getNextCard().toString());
		}
		double duration = System.currentTimeMillis() - startTime;
		System.out.println();
		System.out.print("Processing time: ");
		System.out.format("%.3f", duration / 1000);
		System.out.println(" seconds.");

    }
}
