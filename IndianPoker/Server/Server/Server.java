package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.gson.Gson;

import Data.Account;
import Data.ClientGame;
import Data.GameAccount;
import Data.SendJsonData;
import Data.UserDBManager;

public class Server {//accountSystem과 gameSystem을 총괄하는 클라스

	private static ServerSocket		ss			= null;
	private static Socket			s			= null;
	private ArrayList<GameThread>	gameThreads	= new ArrayList<GameThread>();
	private Logger					logger;
	private UserDBManager			dao;

	public void start() {
		logger	= Logger.getLogger(this.getClass().getName());
		dao		= new UserDBManager();
		try {
			ss = new ServerSocket(8888);
			logger.info("Server start");
			while (true) {
				s = ss.accept();
				GameThread game = new GameThread();
				gameThreads.add(game);
				game.start();
			}
		} catch (Exception e) {
			logger.info("[Server]start() Exception발생!!");
			e.printStackTrace();
		}
	}//start()

	class GameThread extends Thread {
		private BufferedReader	In;
		private PrintWriter		Out;
		Gson					gson		= new Gson();
		String					msg;
		Account					account		= new Account();
		ClientGame				clientGame	= new ClientGame();
		private boolean			status		= true;
		ServerController		controller	= null;
		int						roomNum		= 0;				//해당 유저가 있는 방번호
		GameAccount				m_gameAccount;

		public BufferedReader getIn() {
			return In;
		}

		public PrintWriter getOut() {
			return Out;
		}

		//쓰레드가 사용할 인스턴스 데이터
		public boolean isStatus() {
			return status;
		}

		public void setStatus(boolean status) {
			this.status = status;
		}

		public void run() {
			this.m_gameAccount = new GameAccount();
			System.out.println(m_gameAccount);
			try {
				In	= new BufferedReader(new InputStreamReader(s.getInputStream()));
				Out	= new PrintWriter(s.getOutputStream(), true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			while (status) {
				try {
					msg = In.readLine();
					System.out.println("클라이언트에서 받은 JSON 확인용: " + msg);
					SendJsonData jsonObject = gson.fromJson(msg, SendJsonData.class);
					if (jsonObject.getType().equals("Account")) {
						controller = new AccountSystem(gameThreads, this);
					} else if (jsonObject.getType().equals("ClientGame")) {
						controller = new GameSystem(gameThreads, this);
					} else if (jsonObject.getType().equals("BattingRequest")) {
						controller = new BattingStatus(gameThreads, this);
					} else if (jsonObject.getType().equals("CardRequest")) {
						controller = new CardGetStatus(gameThreads, this);
					} else {
						//이긴 값을 DB에 전송하는 클래스
					}
					String output = controller.runMethod(jsonObject.getJsonObject());
					System.out.println(m_gameAccount.id);
					if (output != null) {
						Out.println(output);
					}
				} catch (SocketException se) {
					try {
						s.close();
						status = false;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} //while
			this.interrupt();
		} //run

	}//GameThread class

	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}//main()

}//Server class
