package Server;

import java.util.ArrayList;

import Data.ClientGame;
import Data.SendJsonData;
import Server.Server.GameThread;

public class GameSystem implements ServerController {
	private ArrayList<Server.GameThread>	list;
	private GameThread						status;

	public GameSystem(ArrayList<Server.GameThread> list, Server.GameThread status) {
		this.list	= list;
		this.status	= status;
	}

	public void sendOpposite(SendJsonData data) {
		ClientGame obj = new ClientGame();
		for (GameThread t : list) {
			if (t.roomNum == status.roomNum && t != status) {//자기자신과 아니고 상대방과 내가 같은 방일때
				t.m_gameAccount.apposite_Card = new Card((int) (Math.random() * 10 + 1), (int) (Math.random() * 20 + 1));//임의의 카드를 받는다
				obj.setAccount(t.m_gameAccount);
				obj.setType("게임시작 가능");
				obj.setRoomnumber(String.valueOf(t.roomNum));
				data.setJsonObject(gson.toJson(obj));
				t.getOut().println(gson.toJson(data));
				return;
			}
		}
	}

	@Override
	public String runMethod(String object) {
		ClientGame gameData = gson.fromJson(object, ClientGame.class);
		if (gameData.getType().equals("게임방 확인")) {
			String s = checkGameRoomUserNum(gameData.getRoomnumber());
			//게임방 꽉찬 상태
			if (s.equals("2")) {
				gameData.setType("게임방 꽉 참");
			} //if
				//게임방에 한 명만 있는 있는 상태
			else if (s.equals("1")) {
				gameData.setType("게임시작 가능");
				status.roomNum						= Integer.valueOf(gameData.getRoomnumber());
				status.m_gameAccount.priority		= false;
				status.m_gameAccount.chipSet		= 50;
				status.m_gameAccount.apposite_Card	= new Card((int) (Math.random() * 10 + 1), (int) (Math.random() * 20 + 1));
				gameData.setRoomnumber(String.valueOf(status.roomNum));
				gameData.setAccount(status.m_gameAccount);
				SendJsonData oppsite = new SendJsonData();
				oppsite.setType("ClientGame");
				sendOpposite(oppsite);
			} //else if
				//게임방 비어있는 상태
			else if (s.equals("0")) {
				gameData.setType("대기중");
				status.roomNum					= Integer.valueOf(gameData.getRoomnumber());
				status.m_gameAccount.priority	= true;
				status.m_gameAccount.chipSet	= 50;
			} //else if
		}
		SendJsonData data = new SendJsonData();
		data.setType("ClientGame");
		data.setJsonObject(gson.toJson(gameData));
		return gson.toJson(data);
	}//runMethod()

	//@@
	public String checkGameRoomUserNum(String roomNum) {
		int count = 0;

		for (GameThread user : list) {
			if (user.roomNum == Integer.parseInt(roomNum)) {
				count++;
			} //if
				//방이 꽉찬 경우
			if (count == 2) { return "2"; } //if
		} //for
		return Integer.toString(count);
	}//checkGameRoomUserNum()

	class GameRoom {

		private int			user1Chip;
		private int			user2Chip;
		private int			tabelChip;
		private int			user1Card;
		private int			user2Card;

		private GameThread	user1;
		private GameThread	user2;

		public int getUser1Chip() {
			return user1Chip;
		}

		public void setUser1Chip(int user1Chip) {
			this.user1Chip = user1Chip;
		}

		public int getUser2Chip() {
			return user2Chip;
		}

		public void setUser2Chip(int user2Chip) {
			this.user2Chip = user2Chip;
		}

		public int getTabelChip() {
			return tabelChip;
		}

		public void setTabelChip(int tabelChip) {
			this.tabelChip = tabelChip;
		}

		public int getUser1Card() {
			return user1Card;
		}

		public void setUser1Card(int user1Card) {
			this.user1Card = user1Card;
		}

		public int getUser2Card() {
			return user2Card;
		}

		public void setUser2Card(int user2Card) {
			this.user2Card = user2Card;
		}

	}//GameRoom class

}//GameSystem class
