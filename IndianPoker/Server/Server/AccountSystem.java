package Server;

import java.util.ArrayList;

import Data.Account;
import Data.Message;
import Data.SendJsonData;
import Data.UserDBManager;
import Server.Server.GameThread;

public class AccountSystem implements ServerController {
	private static UserDBManager			dao	= new UserDBManager();//데이터베이스 접근
	private ArrayList<Server.GameThread>	list;
	private GameThread						status;

	public AccountSystem(ArrayList<Server.GameThread> list, Server.GameThread status) {
		this.list	= list;
		this.status	= status;
	}

	@Override
	public String runMethod(String object) {
		Account account = gson.fromJson(object, Account.class);
		if (account.getType().equals("login")) {
			Message m = dao.identifyUserIdAndPassword(account.getId(), account.getPassword());
			//일치하는 유저정보 존재
			if (m.getId() != null) {
				account.setId(m.getId());
				account.setPassword(m.getPasswd());
				account.setWin(Integer.toString(m.getId_win()));
				account.setLose(Integer.toString(m.getId_lose()));
				//로그아웃 상태
				if (m.getId_status() == 0) {
					account.setType("연결성공");
					status.m_gameAccount.setId(account.getId());
					dao.updateUserStatusLogin(account.getId(), account.getPassword());
				}
				//접속 중인 상태
				else if (m.getId_status() == 1) {
					account.setType("접속중");
				}

			} //if
				//일치하는 유저정보 없음
			else {
				account.setType("정보없음");
				list.remove(status);
				status.setStatus(false);
			}
		}
		//로그아웃
		else if (account.getType().equals("logout") || account.getType().equals("exit")) {
			dao.updateUserStatusLogout(account.getId(), account.getPassword(), account.getWin(), account.getLose());
			if (account.getType().equals("logout")) {
				account.setType("로그아웃");
			} else if (account.getType().equals("exit")) {
				account.setType("게임종료");
			}
			list.remove(status);
			status.setStatus(false);
		}
		//회원가입
		else if (account.getType().equals("sign")) {

			//가입성공
			if (dao.signup(account.getId(), account.getPassword())) {
				account.setType("가입성공");
				list.remove(status);
				status.setStatus(false);
			}
			//가입실패
			else {
				account.setType("가입실패");
				list.remove(status);
				status.setStatus(false);
			}

		} //else if

		//중복체크 확인
		else if (account.getType().equals("checkId")) {

			//공백이 오는 경우
			if (account.getId().equals("")) {
				account.setType("공백");
			} //if
				//공백이 아닌 경우
			else {
				dao.connectDB();
				Message m = dao.identifyUserID(account.getId());
				dao.closeDB();
				//증복아이디 없음
				if (m.getId() == null) {
					account.setType("증복아이디 없음");
				} //if

				//증복아이디 있음
				else {
					account.setType("증복아이디 있음");
				}
			} //else
		}
		SendJsonData data = new SendJsonData();
		data.setType("Account");
		data.setJsonObject(gson.toJson(account));
		return gson.toJson(data);
	}
}