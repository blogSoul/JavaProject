package Data;

public class GameAccount {
	/*게임하는 동안 
	 * 쓰이는 인스턴스 값들이 있다
	 * */
	public Card		apposite_Card;
	public int		chipSet;
	public String	id;
	public boolean	priority;

	public Card getApposite_Card() {
		return apposite_Card;
	}

	public void setApposite_Card(Card apposite_Card) {
		this.apposite_Card = apposite_Card;
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
