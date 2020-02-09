package Server;

import java.util.List;

import Data.BattingData;

public class BattingStatus implements ServerController {
	Server.GameThread		thread;
	List<Server.GameThread>	list;

	public BattingStatus(List<Server.GameThread> threads, Server.GameThread thread) {
		this.list	= threads;
		this.thread	= thread;
	}

	private void sendMessage(String string) {
		for (Server.GameThread t : list) {
			if (t.roomNum == thread.roomNum && t != thread) {//같은 방이면
				t.getOut().println(string);
				break;
			}
		}
	}

	@Override
	public String runMethod(String string) {
		BattingData data = gson.fromJson(string, BattingData.class);
		sendMessage(string);
		return null;
	}
}
