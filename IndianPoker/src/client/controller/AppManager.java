package client.controller;

import client.view.LoginFrame;
import client.view.RankingFrame;
import client.view.SignupFrame;
import client.view.WaitingRoomFrame;

public class AppManager {

	//data
	private static AppManager	s_instance;

	private LoginFrame			login;
	private SignupFrame			signup;
	private WaitingRoomFrame	waiting;
	private RankingFrame		ranking;

	//method
	private AppManager() {

	}//constructor

	public static AppManager getInstanceManager() {
		if (s_instance == null) {
			s_instance = new AppManager();
		}
		return s_instance;
	}

	//get/set
	public LoginFrame getLogin() {
		return login;

	}

	public SignupFrame getSignup() {
		return signup;
	}

	public WaitingRoomFrame getWaiting() {
		return waiting;
	}

	public RankingFrame getRanking() {
		return ranking;
	}

	public void setLogin(LoginFrame login) {
		this.login = login;
	}

	public void setSignup(SignupFrame signup) {
		this.signup = signup;
	}

	public void setWaiting(WaitingRoomFrame waiting) {
		this.waiting = waiting;
	}

	public void setRanking(RankingFrame ranking) {
		this.ranking = ranking;
	}

}//AppManager class
