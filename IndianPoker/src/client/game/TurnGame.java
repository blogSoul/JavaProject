package client.game;

import java.util.Scanner;

public class TurnGame {

	private Dealer	dealer;
	private Player	playerA;
	private Player	playerB;
	private int		coin;
	private int		money;
	private int		equalMoney;

	public TurnGame(Player player1, Player player2, Dealer dealer) {
		equalMoney	= 0;
		playerA		= player1;
		playerB		= player2;
		Scanner scan = new Scanner(System.in);
		this.dealer = dealer;
		this.dealer.shuffleCard();
		if (dealer.getTurnRound() == 10) {
			if (dealer.predecessor == 1) {
				System.out.println("Player가 선공!");
				for (int i = 0; i < dealer.getTurnRound(); i++) {
					player1.setPokerCard(dealer.Card.get(i * 2));
					player2.setPokerCard(dealer.Card.get(i * 2 + 1));
					System.out.println("상대방의 카드 숫자" + Integer.toString(player2.getPokerCard()));
					System.out.println("나의 카드 숫자" + Integer.toString(player1.getPokerCard()));
					do {
						System.out.println("배팅할 칩의 숫자를 적으세요");
						coin = scan.nextInt();
					} while (coin > dealer.getPlayerChips());
					if (dealer.getRivalChips() > coin)
						money = coin;
					else
						money = dealer.getRivalChips();
					dealer.BattingChipsPlayer(coin);
					dealer.BattingChipsRival(money);
					dealer.turnTableChips(coin, money);
					if (player1.getPokerCard() > player2.getPokerCard()) {
						System.out.println("player가 이겼습니다.");
						dealer.InChipsPlayer(dealer.getTableChips() + equalMoney);
						dealer.initTableChips();
						System.out.println("나의 칩 갯수 현황 :" + dealer.getPlayerChips() + ", " + "상대방의 칩 갯수 현황: " + dealer.getRivalChips());
						equalMoney = 0;

					} else if (player1.getPokerCard() == player2.getPokerCard()) {
						System.out.println("같은 크기의 숫가자 나왔습니다");
						System.out.println("나의 칩 갯수 현황 :" + dealer.getPlayerChips() + ", " + "상대방의 칩 갯수 현황: " + dealer.getRivalChips());
						equalMoney += dealer.getTableChips();
					} else {
						System.out.println("상대방이 이겼습니다.");
						dealer.InChipsRival(dealer.getTableChips() + equalMoney);
						dealer.initTableChips();
						System.out.println("나의 칩 갯수 현황 :" + dealer.getPlayerChips() + ", " + "상대방의 칩 갯수 현황: " + dealer.getRivalChips());
						equalMoney = 0;

					}

					if (dealer.getPlayerChips() == 0 && dealer.getTableChips() == 0) {
						System.out.println("패배");
						break;
					} else if (dealer.getRivalChips() == 0 && dealer.getTableChips() == 0) {
						System.out.println("승리");
						break;
					}

				}
			} else {
				System.out.println("상대방이 먼저 선공!");
				for (int i = 0; i < dealer.getTurnRound(); i++) {
					player1.setPokerCard(dealer.Card.get(i * 2));
					player2.setPokerCard(dealer.Card.get(i * 2 + 1));
					System.out.println("상대방의 카드 숫자" + Integer.toString(player2.getPokerCard()));
					System.out.println("나의 카드 숫자" + Integer.toString(player1.getPokerCard()));
					do {
						System.out.println("배팅할 칩의 숫자를 적으세요");
						coin = scan.nextInt();
					} while (coin > dealer.getPlayerChips());
					if (dealer.getRivalChips() > coin)
						money = coin;
					else
						money = dealer.getRivalChips();
					dealer.BattingChipsPlayer(coin);
					dealer.BattingChipsRival(money);
					dealer.turnTableChips(coin, money);
					if (player1.getPokerCard() > player2.getPokerCard()) {
						System.out.println("player가 이겼습니다.");
						dealer.InChipsPlayer(dealer.getTableChips() + equalMoney);
						dealer.initTableChips();
						System.out.println("나의 칩 갯수 현황 :" + dealer.getPlayerChips() + ", " + "상대방의 칩 갯수 현황: " + dealer.getRivalChips());
						equalMoney = 0;

					} else if (player1.getPokerCard() == player2.getPokerCard()) {
						System.out.println("같은 크기의 숫가자 나왔습니다");
						System.out.println("나의 칩 갯수 현황 :" + dealer.getPlayerChips() + ", " + "상대방의 칩 갯수 현황: " + dealer.getRivalChips());
						equalMoney += dealer.getTableChips();
					} else {
						System.out.println("상대방이 이겼습니다.");
						dealer.InChipsRival(dealer.getTableChips() + equalMoney);
						dealer.initTableChips();
						System.out.println("나의 칩 갯수 현황 :" + dealer.getPlayerChips() + ", " + "상대방의 칩 갯수 현황: " + dealer.getRivalChips());
						equalMoney = 0;

					}

					if (dealer.getPlayerChips() == 0 && dealer.getTableChips() == 0) {
						System.out.println("패배");
						break;
					} else if (dealer.getRivalChips() == 0 && dealer.getTableChips() == 0) {
						System.out.println("승리");
						break;
					}
				}
			}
		}

	}
}
