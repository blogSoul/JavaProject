package client.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.controller.AppManager;

public class LoginFrame extends JFrame {

	//data
	public JLabel			titleLabel;
	public JLabel			idLabel;
	public JLabel			pwLabel;

	public JTextField		idTextField;
	public JPasswordField	pwTextField;

	public JButton			loginButton;
	public JButton			exitButton;
	public JButton			signupButton;

	public LoginFrame		loginFrame;
	public WaitingRoomFrame	waitingRoomFrame;
	public SignupFrame		signupFrame;

	//method
	public LoginFrame() {

		loginFrame	= this;

		// 타이틀 레이블
		titleLabel	= new JLabel("Indian Poker");
		titleLabel.setBounds(300, 70, 800, 80);
		titleLabel.setFont(new Font("San Serif", Font.PLAIN, 70));
		titleLabel.setVisible(true);
		this.add(titleLabel);

		// ID Label
		idLabel = new JLabel("ID");
		idLabel.setBounds(350, 290, 200, 50);
		idLabel.setFont(new Font("San Serif", Font.PLAIN, 20));
		idLabel.setVisible(true);
		this.add(idLabel);

		// ID 입력창
		idTextField = new JTextField("");
		idTextField.setBounds(400, 290, 200, 50);
		idTextField.setVisible(true);
		this.add(idTextField);

		// PW Label
		pwLabel = new JLabel("PW");
		pwLabel.setBounds(350, 350, 200, 50);
		pwLabel.setFont(new Font("San Serif", Font.PLAIN, 20));
		pwLabel.setVisible(true);
		this.add(pwLabel);

		/// PW 입력창
		pwTextField = new JPasswordField("");
		pwTextField.setBounds(400, 350, 200, 50);
		pwTextField.setVisible(true);
		this.add(pwTextField);

		// 로그인 버튼
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(400, 410, 200, 50);
		loginButton.setVisible(true);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (waitingRoomFrame == null) {
					try {
						waitingRoomFrame = new WaitingRoomFrame();
						AppManager.getInstanceManager().setWaiting(waitingRoomFrame);

					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					waitingRoomFrame.setVisible(true);
				}

				System.out.println(idTextField.getText());
				System.out.println(pwTextField.getText());

				closeLoginFrame();
			}
		});
		this.add(loginButton);

		// 나가기 버튼
		JButton exitButton = new JButton("Exit");
		exitButton.setBounds(400, 470, 90, 50);
		exitButton.setVisible(true);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		this.add(exitButton);

		// 회원가입 버튼
		JButton signupButton = new JButton("Sign up");
		signupButton.setBounds(510, 470, 90, 50);
		signupButton.setVisible(true);
		signupButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (signupFrame == null) {
					signupFrame = new SignupFrame();
					AppManager.getInstanceManager().setSignup(signupFrame);
				} else {

					AppManager.getInstanceManager().getSignup().openSignup();
				}
			}
		});
		this.add(signupButton);

		// 프레임 설정
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 800);
		this.setVisible(true);
		this.setResizable(false);
		this.setTitle("인디언 포커");

	}

	public void openLoginFrame() {
		this.setVisible(true);
	}

	private void closeLoginFrame() {
		this.setVisible(false);
	}

	public void AddLoginListener(ActionListener listner) {
		loginButton.addActionListener(listner);
	}

}//LoginFrame class
