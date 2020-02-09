package Data;

public class Message {
	//data
	private String	id;
	private String	passwd;
	private int		id_win;
	private int		id_lose;
	private int		id_status;

	//method
	public Message() {

	}//constructor

	//id는 아이디, passwd는 비번, id_win는 승수, id_lose는 패수
	//id_status는 회원의 현재 상태를 표시, 0은 로그아웃 1~3은 어디 대기방에 있는지 알아야 합니다.
	public Message(String id, String passwd, int id_win, int id_lose, int id_status) {
		this.id			= id;
		this.passwd		= passwd;
		this.id_win		= id_win;
		this.id_lose	= id_lose;
		this.id_status	= id_status;
	}//constructor2

	//get/set
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public int getId_win() {
		return id_win;
	}

	public void setId_win(int id_win) {
		this.id_win = id_win;
	}

	public int getId_lose() {
		return id_lose;
	}

	public void setId_lose(int id_lose) {
		this.id_lose = id_lose;
	}

	public int getId_status() {
		return id_status;
	}

	public void setId_status(int id_status) {
		this.id_status = id_status;
	}
}//Data.Message class