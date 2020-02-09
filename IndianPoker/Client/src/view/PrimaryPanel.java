package view;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.google.gson.Gson;

import Data.Account;
import Data.ClientGame;
import Data.SendJsonData;

public class PrimaryPanel extends JPanel implements Runnable {//여기서 Client를 담당하는 클래스

	//이 프로그램에서 사용할 프로그램
	public GamePanel		Pgame;
	public LoginPanel		Plogin;
	public SignupPanel		Psign;
	public WaitingRoomPanel	Pwait;

	//서버 관련 데이터
	Logger					logger;
	public String			ip		= "127.0.0.1";		//로컬호스트
	public Socket			socket;						//클라이언트 소켓
	public Thread			thread;
	public boolean			status;
	public BufferedReader	inMsg;
	public PrintWriter		outMsg;
	public Gson				gson	= new Gson();

	//서버 통신으로 주고 받을 객체
	public Account			a		= new Account();	//유저의 정보를 account클래스를 통해 gson으로 전달
	public ClientGame		game	= new ClientGame();	//게임유저가 필요한 정보를 가지는 클래스를 통해 gson으로 전달

	//클라이언트에서 쓸 인스턴스 데이터
	public String			id;
	public String			passwd;
	public String			win;
	public String			lose;
	public String			RoomNumber;					//클라이언트가 사용할 방번호

	public int				priority;					//선공 후공의 값을 저장
	public String			bettingChip;				//칩의 갯수를 저장

	public PrimaryPanel() {
		logger = Logger.getLogger(this.getClass().getName());

		this.setBounds(0, 0, 1000, 800);
		this.setPreferredSize(new Dimension(1000, 800));
		this.setLayout(null);
		Plogin	= new LoginPanel(this);	//우선 로그인화면,회원가입화면 둘다 생성
		Psign	= new SignupPanel(this);//대신 회원가입은 꺼둔다
		Psign.setBounds(0, 0, 1000, 800);
		this.add(Psign);
		this.add(Plogin);
		RoomNumber = "0";
		Psign.setVisible(false);

	}

	//각 타입에 맞게 서버에 데이터 전달
	public void connectServer(String type) {
		try {
			socket = new Socket(ip, 8888);
			logger.log(Level.INFO, "[Client]Server 연결 성공!!");
			SendJsonData data = new SendJsonData();

			inMsg	= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			outMsg	= new PrintWriter(socket.getOutputStream(), true);

			//로그인
			if (type.equals("login")) {
				a = new Account(Plogin.idTextField.getText(), Plogin.pwTextField.getText(), "", "", "", "", "", "", type);
				data.setJsonObject(gson.toJson(a));
				data.setType("Account");
			}

			//로그아웃 //대기방에서 게임 종료
			else if (type.equals("logout") || type.equals("exit")) {
				a = new Account(id, passwd, "", win, lose, "", "", "", type);
				data.setJsonObject(gson.toJson(a));
				data.setType("Account");
			}

			//회원가입
			else if (type.equals("sign")) {
				a = new Account(Psign.idTextField.getText(), Psign.pwTextField.getText(), "", "", "", "", "", "", type);
				data.setJsonObject(gson.toJson(a));
				data.setType("Account");
			}
			//아이디 중복 검사
			else if (type.equals("checkId")) {
				a = new Account(Psign.idTextField.getText(), "", "", "", "", "", "", "", type);
				data.setJsonObject(gson.toJson(a));
				data.setType("Account");
			}
			//게임방 클릭
			else if (type.equals("게임방 확인")) {
				game = new ClientGame(Integer.toString(Pwait.roomTabel.getSelectedRow() + 1), null, type);
				data.setJsonObject(gson.toJson(game));
				data.setType("ClientGame");
			}
			//
			else if (type.equals("게임시작버튼 눌렀음")) {
				priority	= (int) (Math.random() * 100);
				game		= new ClientGame(Integer.toString(priority), null, type);
				data.setJsonObject(gson.toJson(game));
				data.setType("ClientGame");
			}
			/* //
			 * else if (type.equals("선공")) {
			 * bettingChip = Pgame.myBettingTextField.getText();
			 * game = new ClientGame("", "", bettingChip, "", "", "", "", type);
			 * data.setJsonObject(gson.toJson(game));
			 * data.setType("ClientGame");
			 * 
			 * }
			 * //
			 * else if (type.equals("후공")) {
			 * bettingChip = Pgame.myBettingTextField.getText();
			 * game = new ClientGame("", "", bettingChip, "", "", "", "", type);
			 * data.setJsonObject(gson.toJson(game));
			 * data.setType("ClientGame");
			 * }
			 * //
			 * else if (type.equals("나가기")) {
			 * game = new ClientGame("", "", "", "", "", "", "", type);
			 * data.setJsonObject(gson.toJson(game));
			 * data.setType("ClientGame");
			 * }
			 * 
			 * else if (type.equals("배팅")) {
			 * game = new ClientGame("상대방", "", "", "", "", "", "", type);
			 * data.setJsonObject(gson.toJson(game));
			 * data.setType("ClientGame");
			 * }
			 * 
			 * else if (type.equals("카드 요청")) {
			 * game = new ClientGame("상대방", "", "", "", "", "", "", type);
			 * data.setJsonObject(gson.toJson(game));
			 * data.setType("ClientGame");
			 * } */
			outMsg.println(gson.toJson(data));
			thread = new Thread(this);
			thread.start();
		} catch (Exception e) {
			logger.log(Level.WARNING, "connectServer() Exception 발생!!");
			e.printStackTrace();
		}

	}

	@Override
	public void run() {//스레드 런 
		String msg;
		status = true;
		int flag;

		while (status) {
			try {
				msg = inMsg.readLine();
				SendJsonData data = gson.fromJson(msg, SendJsonData.class);
				if (data.getType().equals("Account")) {//상황에 따른 account 또는 clientgame 둘 중 하나를 선택하는 작업
					a		= gson.fromJson(data.getJsonObject(), Account.class);
					flag	= 1;
				} else {
					game	= gson.fromJson(data.getJsonObject(), ClientGame.class);
					flag	= 2;
				}

				System.out.println("서버에서 받은 JSON 확인용: " + msg);

				//Account맵핑
				if (flag == 1) {
					System.out.println(a.getType());

					if (a.getType().equals("연결성공")) {
						id		= a.getId();
						passwd	= a.getPassword();
						win		= a.getWin();
						lose	= a.getLose();
						disableLoginPanel();
						try {
							addWaitingRoomPanel();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} //if
					else if (a.getType().equals("정보없음")) {
						JOptionPane.showMessageDialog(this, "로그인에 실패했습니다!!", "서버 접속 실패", JOptionPane.ERROR_MESSAGE);
						status = false;
					} //else if
					else if (a.getType().equals("접속중")) {
						JOptionPane.showMessageDialog(this, "해당 아이디로 접속 중입니다!!", "서버 접속 실패", JOptionPane.ERROR_MESSAGE);
						try {
							addWaitingRoomPanel();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} //else if
					else if (a.getType().equals("공백")) {
						JOptionPane.showMessageDialog(this, "아이디를 입력하세요!!", "아이디 중복 확인", JOptionPane.ERROR_MESSAGE);
						status = false;
					} //else if
					else if (a.getType().equals("증복아이디 없음")) {
						JOptionPane.showMessageDialog(this, "해당 아이디를 만들 수 있습니다!!", "아이디 중복 확인", JOptionPane.PLAIN_MESSAGE);
						Psign.checkButton.setEnabled(false);
						Psign.idTextField.setEditable(false);
						status = false;
					} //else if
					else if (a.getType().equals("증복아이디 있음")) {
						JOptionPane.showMessageDialog(this, "동일한 아이디가 존재합니다!!", "아이디 중복 확인", JOptionPane.ERROR_MESSAGE);
						status = false;
					} //else if
					else if (a.getType().equals("가입성공")) {
						JOptionPane.showMessageDialog(this, "해당 아이디와 비밀번호로 로그인하세요!!", "회원가입 성공", JOptionPane.PLAIN_MESSAGE);
						disableSignPanel();
						enableLoginPanel();
						status = false;
					} //else if
					else if (a.getType().equals("가입실패")) {
						//만들어두긴 했지만 일단 사용하지는 않음
						status = false;
					} //else if
					else if (a.getType().equals("로그아웃")) {
						status = false;
					} //else if
					else if (a.getType().equals("게임종료")) {
						status = false;
						System.exit(0);
					} //else if
				} //if

				//게임clientgame 입장
				if (flag == 2) {

					System.out.println(game.getType());

					if (game.getType().equals("게임방 꽉 참")) {
						JOptionPane.showMessageDialog(this, "이미 꽉 찬 방입니다!!", "게임방 입장", JOptionPane.ERROR_MESSAGE);
					} //if

					else if (game.getType().equals("게임시작 가능")) {

						JOptionPane.showMessageDialog(this, "게임을 시작합니다", "게임방 입장", JOptionPane.PLAIN_MESSAGE);
						addGamePanel();

						disableWaitingPanel();
					} //else if

					else if (game.getType().equals("대기중")) {
						JOptionPane.showMessageDialog(this, "대기중", "게임방 입장", JOptionPane.PLAIN_MESSAGE);
					} //else if

					/* else if (game.getType().equals("priority")) {
					 * 
					 * Pgame.myMoneyLabel.setText("50");
					 * Pgame.rivalMoneyLabel.setText("50");
					 * if (game.getData()) { //수정한 부분
					 * Pgame.enableOrDisableBettingButton(true);
					 * } //if
					 * 
					 * else {
					 * Pgame.enableOrDisableBettingButton(false);
					 * } //else
					 * } //else if
					 * //내가 배팅을 받을 때
					 * else if (game.getType().equals("배팅")) {
					 * 
					 * int curRival = Integer.parseInt(Pgame.rivalCardLabel.getText());
					 * int curTable = Integer.parseInt(Pgame.totalBetNumberLabel.getText());
					 * 
					 * int receivedRival = Integer.parseInt(game.getRivalchip());
					 * int receivedTable = Integer.parseInt(game.getTablechip());
					 * 
					 * game.setRivalchip(Integer.toString(curRival - receivedRival));
					 * game.setTablechip(Integer.toString(curTable + receivedTable));
					 * 
					 * outMsg.println(gson.toJson(game));
					 * 
					 * } //else if
					 * 
					 * else if (game.getType().equals("카드응답")) {
					 * try {
					 * Pgame.rivalCard = ImageIO.read(new File("img/card.png"));
					 * } catch (Exception e) {
					 * e.printStackTrace();
					 * }
					 * } //else if */

					else if (game.getType().equals("")) {

					} //else if

					else if (game.getType().equals("")) {

					} //else if

				} //if

			} catch (IOException e) {
				logger.log(Level.WARNING, "메세지 스트림 종료!!");
				e.printStackTrace();
			}
		} //while
		logger.info(thread.getName() + "메세지 수신 스레드 종료됨!!");

	}//run()

	public void addGamePanel() throws IOException {
		Pgame = new GamePanel(this);
		Pgame.setBounds(0, 0, 1000, 800);
		Pgame.setVisible(true);
		this.add(Pgame);
		repaint();

	}

	public void addWaitingRoomPanel() throws IOException {
		Pwait = new WaitingRoomPanel(this);
		Pwait.setBounds(0, 0, 1000, 800);
		Pwait.setVisible(true);
		add(Pwait);
		repaint();

	}

	public void disableGamePanel() {
		this.Pgame.setVisible(false);
	}

	public void disableSignPanel() {
		this.Psign.initSignupPanel();
		this.Psign.setVisible(false);
	}

	public void disableWaitingPanel() {
		this.Pwait.setVisible(false);
	}

	public void disableLoginPanel() {
		//this.Plogin.initLoginPanel();
		this.Plogin.setVisible(false);
	}

	public void enableGamePanel() {
		this.Pgame.setVisible(true);
	}

	public void enableSignPanel() {
		this.Psign.setVisible(true);
	}

	public void enableWaitingPanel() {
		this.Pwait.setVisible(true);
	}

	public void enableLoginPanel() {
		this.Plogin.setVisible(true);
	}
}