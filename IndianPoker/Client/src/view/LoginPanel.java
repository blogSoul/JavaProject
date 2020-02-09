package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import Handler.btnLogInOutListener;

public class LoginPanel extends JPanel {

	//data
	public JLabel			idLabel;
	public JLabel			pwLabel;

	public ImageIcon		bgImage;

	public JTextField		idTextField;
	public JPasswordField		pwTextField;

	public JButton			loginButton;
	public JButton			exitButton;
	public JButton			signupButton;

	private PrimaryPanel		parent;

	//method
	public LoginPanel(PrimaryPanel p) {

		this.parent = p;
		btnLogInOutListener listener = new btnLogInOutListener(parent, this);
		
		bgImage = new ImageIcon("img/mainbg.jpg");		
// ID Label
		idLabel = new JLabel("ID");
		idLabel.setBounds(350, 410, 200, 50);
		idLabel.setForeground(Color.WHITE);
		idLabel.setFont(new Font("휴먼모음T", Font.BOLD, 20));
		idLabel.setVisible(true);
		this.add(idLabel);

		// ID 입력창
		idTextField = new JTextField("");
		idTextField.setBounds(400, 410, 200, 50);
		idTextField.setVisible(true);
		this.add(idTextField);

		// PW Label
		pwLabel = new JLabel("PW");
		pwLabel.setBounds(350, 470, 200, 50);
		pwLabel.setForeground(Color.WHITE);
		pwLabel.setFont(new Font("휴먼모음T", Font.BOLD, 20));
		pwLabel.setVisible(true);
		this.add(pwLabel);

		/// PW 입력창
		pwTextField = new JPasswordField("");
		pwTextField.setBounds(400, 470, 200, 50);
		pwTextField.setVisible(true);
		this.add(pwTextField);

		// 로그인 버튼
		
		loginButton = new JButton(new ImageIcon("img/loginbtn.png"));
		loginButton.setBounds(400, 530, 200, 50);
		loginButton.setContentAreaFilled(false);
		loginButton.setFocusPainted(false);
		loginButton.setVisible(true);
		loginButton.addActionListener(listener);
		this.add(loginButton);

		// 나가기 버튼
		exitButton = new JButton(new ImageIcon("img/exitbtn.png"));
		exitButton.setBounds(400, 590, 91, 50);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.setVisible(true);
		exitButton.addActionListener(listener);
		this.add(exitButton);

		// 회원가입 버튼
		signupButton = new JButton(new ImageIcon("img/signupbtn.png"));
		signupButton.setContentAreaFilled(false);
		signupButton.setFocusPainted(false);
		signupButton.setBounds(510, 590, 90, 50);
		signupButton.setVisible(true);
		signupButton.addActionListener(listener);
		this.add(signupButton);

		// 프레임 설정
		this.setLayout(null);

		this.setSize(1000, 800);
		this.setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(bgImage.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}


	//@@
	public void initLoginPanel() {
		idTextField.setText("");
		pwTextField.setText("");
	}

}//LoginFrame class
