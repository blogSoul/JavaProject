package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Handler.btnSignupListener;

public class SignupPanel extends JPanel {

	//data
	public JLabel			idLabel;
	public JLabel			pwLabel;
	public JLabel			pwReLabel;

	public ImageIcon		bgImage;

	public JTextField		idTextField;
	public JPasswordField	pwTextField;
	public JPasswordField	pwReTextField;

	public JLabel			agreeLabel;

	public JCheckBox		agreeButton;
	public JCheckBox		disagreeButton;

	public JButton			checkButton;
	public JButton			signupButton;
	public JButton			exitButton;

	private PrimaryPanel	parent;

	//method
	public SignupPanel(PrimaryPanel p) {

		this.parent = p;
		btnSignupListener listener = new btnSignupListener(parent, this);

		bgImage	= new ImageIcon("img/bg.jpg");

		// ID 레이블
		idLabel	= new JLabel("ID");
		idLabel.setBounds(250, 200, 150, 50);
		idLabel.setForeground(Color.WHITE);
		idLabel.setFont(new Font("휴먼모음T", Font.BOLD, 30));
		idLabel.setVisible(true);
		this.add(idLabel);

		// ID 입력창
		idTextField = new JTextField("");
		idTextField.setBounds(350, 200, 300, 50);
		idTextField.setVisible(true);
		this.add(idTextField);

		// 체크버튼

		checkButton = new JButton(new ImageIcon("img/checkbtn.png"));
		checkButton.setBounds(660, 200, 120, 50);
		checkButton.setContentAreaFilled(false);
		checkButton.setFocusPainted(false);
		checkButton.setVisible(true);
		checkButton.addActionListener(listener);
		this.add(checkButton);

		// PW 레이블
		pwLabel = new JLabel("PW");
		pwLabel.setBounds(250, 280, 150, 50);
		pwLabel.setForeground(Color.WHITE);
		pwLabel.setFont(new Font("휴먼모음T", Font.BOLD, 30));
		pwLabel.setVisible(true);
		this.add(pwLabel);

		// PW 입력창
		pwTextField = new JPasswordField("");
		pwTextField.setBounds(350, 280, 300, 50);
		pwTextField.setVisible(true);
		this.add(pwTextField);

		// PW 재입력 레이블
		pwReLabel = new JLabel("PW 확인");
		pwReLabel.setBounds(250, 360, 150, 50);
		pwReLabel.setForeground(Color.WHITE);
		pwReLabel.setFont(new Font("휴먼모음T", Font.BOLD, 23));
		pwReLabel.setVisible(true);
		this.add(pwReLabel);

		// PW 재입력 입력창
		pwReTextField = new JPasswordField("");
		pwReTextField.setBounds(350, 360, 300, 50);
		pwReTextField.setVisible(true);
		this.add(pwReTextField);

		agreeLabel = new JLabel(new ImageIcon("img/약관.png"));
		agreeLabel.setBounds(350, 420, 300, 54);
		agreeLabel.setVisible(true);
		this.add(agreeLabel);

		//동의/비동의
		agreeButton = new JCheckBox("동의");
		agreeButton.setForeground(Color.WHITE);
		agreeButton.setBounds(370, 470, 180, 20);
		agreeButton.setContentAreaFilled(false);
		agreeButton.setVisible(true);
		agreeButton.addActionListener(listener);
		this.add(agreeButton);

		disagreeButton = new JCheckBox("비동의");
		disagreeButton.setForeground(Color.WHITE);
		disagreeButton.setBounds(570, 470, 180, 20);
		disagreeButton.setContentAreaFilled(false);
		disagreeButton.setVisible(true);
		disagreeButton.addActionListener(listener);
		this.add(disagreeButton);

		// 확인 버튼
		signupButton = new JButton(new ImageIcon("img/confirmbtn.png"));
		signupButton.setBounds(350, 510, 120, 50);
		signupButton.setContentAreaFilled(false);
		signupButton.setFocusPainted(false);
		signupButton.setVisible(true);
		//signupButton.setEnabled(false);
		signupButton.addActionListener(listener);
		this.add(signupButton);

		// 나가기 버튼
		exitButton = new JButton(new ImageIcon("img/cancelbtn.png"));
		exitButton.setBounds(530, 510, 120, 50);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.setVisible(true);
		exitButton.addActionListener(listener);
		this.add(exitButton);

		// 프레임 설정
		this.setLayout(null);
		this.setSize(400, 400);
		this.setVisible(true);
	}//constructor

	public void paintComponent(Graphics g) {
		g.drawImage(bgImage.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}

	//@@
	public void initSignupPanel() {
		idTextField.setText("");
		idTextField.setEditable(true);
		pwTextField.setText("");
		pwReTextField.setText("");
		agreeButton.setSelected(false);
		disagreeButton.setSelected(false);
		checkButton.setEnabled(true);
	}

}//SignupFrame class
