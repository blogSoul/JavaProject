package Data;

public class Account {//유저관리하는 dto

	private String	id;
	private String	password;
	private String	visit;
	private String	win;
	private String	lose;
	private String	RoomNum;
	private String	Chips;
	private String	PokerCard;
	private String	type;

	public Account() {

	}

	public Account(String id, String password,
			String visit, String win,
			String lose, String RoomNum,
			String Chips, String PokerCard, String type) {
		this.id			= id;
		this.password	= password;
		this.visit		= visit;
		this.win		= win;
		this.lose		= lose;
		this.RoomNum	= RoomNum;
		this.Chips		= Chips;
		this.PokerCard	= PokerCard;
		this.type		= type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChips() {
		return Chips;
	}

	public void setChips(String chips) {
		Chips = chips;
	}

	public String getPokerCard() {
		return PokerCard;
	}

	public void setPokerCard(String pokerCard) {
		PokerCard = pokerCard;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVisit() {
		return visit;
	}

	public void setVisit(String visit) {
		this.visit = visit;
	}

	public String getWin() {
		return win;
	}

	public void setWin(String win) {
		this.win = win;
	}

	public String getLose() {
		return lose;
	}

	public void setLose(String lose) {
		this.lose = lose;
	}

	public String getRoomNum() {
		return RoomNum;
	}

	public void setRoomNum(String roomNum) {
		RoomNum = roomNum;
	}

}
