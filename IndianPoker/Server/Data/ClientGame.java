package Data;

public class ClientGame {
	private String	roomnumber;
	private GameAccount account;
	private String type;

	public String getType() {
		return type;
	}

	public void setRoomnumber(String roomnumber) {
		this.roomnumber = roomnumber;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ClientGame() {

	}

	public GameAccount getAccount() {
		return account;
	}

	public void setAccount(GameAccount account) {
		this.account = account;
	}

	public ClientGame(String roomnumber, GameAccount account, String type) {
		this.roomnumber = roomnumber;
		this.account = account;
		this.type = type;
	}


	public String getRoomnumber() {
		return roomnumber;
	}
}