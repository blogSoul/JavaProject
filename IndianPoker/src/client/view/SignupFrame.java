package client.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SignupFrame extends JFrame {
	public SignupFrame() {

		// ID 레이블
		JLabel idLabel = new JLabel("ID");
		idLabel.setBounds(50, 50, 150, 30);
		idLabel.setFont(new Font("San Serif", Font.PLAIN, 20));
		idLabel.setVisible(true);
		this.add(idLabel);

		// ID 입력창
		JTextField idTextField = new JTextField("");
		idTextField.setBounds(110, 50, 150, 30);
		idTextField.setVisible(true);
		this.add(idTextField);

		// 로그인버튼
		JButton checkButton = new JButton("중복체크");
		checkButton.setBounds(270, 50, 100, 30);
		checkButton.setVisible(true);
		checkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {}
		});
		this.add(checkButton);

		// PW 레이블
		JLabel pwLabel = new JLabel("PW");
		pwLabel.setBounds(50, 100, 150, 30);
		pwLabel.setFont(new Font("San Serif", Font.PLAIN, 20));
		pwLabel.setVisible(true);
		this.add(pwLabel);

		// PW 입력창
		JTextField pwTextField = new JTextField("");
		pwTextField.setBounds(110, 100, 150, 30);
		pwTextField.setVisible(true);
		this.add(pwTextField);

		// PW 재입력 레이블
		JLabel pwReLabel = new JLabel("비밀번호 확인");
		pwReLabel.setBounds(10, 150, 150, 30);
		pwReLabel.setFont(new Font("San Serif", Font.PLAIN, 13));
		pwReLabel.setVisible(true);
		this.add(pwReLabel);

		// PW 재입력 입력창
		JTextField pwReTextField = new JTextField("");
		pwReTextField.setBounds(110, 150, 150, 30);
		pwReTextField.setVisible(true);
		this.add(pwReTextField);

		JRadioButton agreeButton = new JRadioButton("동의");
		agreeButton.setBounds(100, 200, 100, 30);
		agreeButton.setVisible(true);
		this.add(agreeButton);

		JRadioButton disagreeButton = new JRadioButton("비동의");
		disagreeButton.setBounds(200, 200, 150, 30);
		disagreeButton.setVisible(true);
		this.add(disagreeButton);

		// 나가기 버튼
		JButton signupButton = new JButton("확인");
		signupButton.setBounds(70, 300, 100, 30);
		signupButton.setVisible(true);
		signupButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeSignup();
			}
		});
		this.add(signupButton);

		// 나가기 버튼
		JButton exitButton = new JButton("취소");
		exitButton.setBounds(180, 300, 100, 30);
		exitButton.setVisible(true);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeSignup();
			}
		});
		this.add(exitButton);

		// 프레임 설정
		this.setLayout(null);
		this.setSize(400, 400);
		this.setVisible(true);

	}

	private void closeSignup() {
		this.setVisible(false);
	}
}
