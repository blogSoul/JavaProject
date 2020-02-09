package Handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.LoginPanel;
import view.PrimaryPanel;


/*로그인 창에 대한 핸들러*/
public class btnLogInOutListener implements ActionListener/* , Runnable */ {

	LoginPanel		login;
	PrimaryPanel	parent;

	public btnLogInOutListener(PrimaryPanel p, LoginPanel login) {
		this.parent	= p;
		this.login	= login;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == login.loginButton) {
			parent.connectServer("login");
		} //로그인
		else if (obj == login.exitButton) {
			System.exit(0);
		} //나가기
		else if (obj == login.signupButton) {
			parent.disableLoginPanel();
			parent.enableSignPanel();
		}

	}

}
