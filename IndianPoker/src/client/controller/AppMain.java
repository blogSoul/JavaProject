package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.view.LoginFrame;

public class AppMain {

	//data
	private LoginFrame login;

	//method
	public AppMain() {

		login = new LoginFrame();
		AppManager.getInstanceManager().setLogin(login);

		//로그인 창에서 로그인 정보 가져온다
		login.AddLoginListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();

				if (obj == login.loginButton) {
					//login.idTextField.getText();
					//login.pwTextField.getText();

					System.out.println(login.idTextField.getText());
					System.out.println(login.pwTextField.getText());
				}

			}
		});

	}//constructor

	public static void main(String[] args) {

		AppMain app = new AppMain();

	}//main()

}//AppMain class
