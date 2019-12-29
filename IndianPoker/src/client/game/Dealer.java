package client.game;

import java.util.ArrayList;
import java.util.Collections;

public class Dealer {

	public static int					predecessor;
	public static ArrayList<Integer>	Card;
	private Player						player1;
	private Player						player2;
	private static int					tableChips;
	private int							turnRound;
	public static int					PlayerChips;
	public static int					RivalChips;

	public Dealer(Player playerA, Player playerB) {
		this.player1	= playerA;
		this.player2	= playerB;
		PlayerChips		= 20;
		RivalChips		= 20;
		tableChips		= 0;
		turnRound		= 10;
		predecessor		= (int) (Math.random() * 2);
		Card			= new ArrayList<Integer>();
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < 10; i++) {
				Card.add(i + 1);
			}
		}
	}

	public void BattingChipsPlayer(int coin) {
		PlayerChips -= coin;
	}

	public void BattingChipsRival(int coin) {
		RivalChips -= coin;
	}

	public void InChipsPlayer(int coin) {
		PlayerChips += coin;
	}

	public void InChipsRival(int coin) {
		RivalChips += coin;
	}

	public int getPlayerChips() {
		return PlayerChips;
	}

	public int getRivalChips() {
		return RivalChips;
	}

	public void shuffleCard() {
		Collections.shuffle(Card);
	}

	public int getTableChips() {
		return tableChips;
	}

	public void setTableChips(int chips) {
		tableChips = chips;
	}

	public int getTurnRound() {
		return turnRound;
	}

	public void setTurnRound(int turnRound) {
		this.turnRound = turnRound;
	}

	public void turnTableChips(int coin, int money) {
		tableChips = coin + money;
	}

	public void initTableChips() {
		tableChips = 0;
	}

}
