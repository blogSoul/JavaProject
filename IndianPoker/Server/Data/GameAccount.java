package Data;

import Server.Card;

public class GameAccount {
	public Card		apposite_Card;//상대방 카드
	public int		chipSet;//칩 갯수
	public String	id;//유저
	public boolean	priority;//우선권

	public Card getApposite_Card() {
		return apposite_Card;
	}

	public void setApposite_Card(Card apposite_Card) {
		this.apposite_Card = apposite_Card;
	}

	public int getNumber() {
		return apposite_Card.getNumber();
	}
	public int getChipSet() {
		return chipSet;
	}

	public void setChipSet(int chipSet) {
		this.chipSet = chipSet;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isPriority() {
		return priority;
	}

	public void setPriority(boolean priority) {
		this.priority = priority;
	}

}
