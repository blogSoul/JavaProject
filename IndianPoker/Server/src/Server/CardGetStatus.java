package Server;

import java.util.List;

import Data.GameAccount;
import Data.SendJsonData;

public class CardGetStatus implements ServerController {//카드에 대한 스레드
	Server.GameThread		thread;
	List<Server.GameThread>	list;

	public CardGetStatus(List<Server.GameThread> list, Server.GameThread thread) {
		this.thread	= thread;
		this.list	= list;
	}

	private GameAccount get_Card() {
		for (Server.GameThread t : list) {
			if (t.roomNum == thread.roomNum && t != thread) { return t.m_gameAccount; }//같은 방이면
		}
		return null;
	}

	@Override
	public String runMethod(String string) {
		GameAccount		account	= get_Card();
		SendJsonData	data	= new SendJsonData();
		data.setType("CardResponse");
		data.setJsonObject(gson.toJson(account));
		return gson.toJson(data);
	}
}
