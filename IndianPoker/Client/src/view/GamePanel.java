package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Handler.BtnBettingListener;
import Handler.BtnExitRoomListener;
import Handler.BtnGiveupListener;
import Handler.PriorityMouseListener;

public class GamePanel extends JPanel {
	public PrimaryPanel		parent;

	public JTextArea		messageArea;

	public JLabel			myImageLabel;
	public JLabel			myEmoticonLabel;

	public JLabel			rivalImageLabel;
	public JLabel			myCardLabel;
	public JLabel			rivalCardLabel;

	public JLabel			myMoneyLabel;
	public JLabel			rivalMoneyLabel;

	public JLabel			totalBetLabel;
	public JLabel			resultLabel;
	public JLabel			totalBetNumberLabel;

	public JTextField		myBettingTextField;

	public JButton			upBetting;
	public JButton			downBetting;
	public JButton			saveBettingButton;
	public JButton			bettingButton;
	public JButton			giveupButton;

	public JLabel			turnImageLabel;
	public JLabel			turnLabel;

	public JButton			exitRoomButton;

	public BufferedImage	rivalCard;

	public boolean			turn;

	public ImageIcon		bgImage;

	public GamePanel(PrimaryPanel p) throws IOException {
		this.parent	= p;
		bgImage		= new ImageIcon("img/testbg.jpg");
		// 메세지 영역
		messageArea	= new JTextArea();
		messageArea.setBorder(BorderFactory.createLineBorder(Color.white, 1));
		messageArea.setBackground(Color.black);
		messageArea.setForeground(Color.white);
		messageArea.setBounds(10, 20, 350, 150);
		messageArea.setEditable(false);
		messageArea.setVisible(true);
		this.add(messageArea);

		// 내 이미지
		myImageLabel = new JLabel();
		BufferedImage myImage = ImageIO.read(new File("img/그림4.png"));
		myImageLabel.setIcon(new ImageIcon(myImage));
		myImageLabel.setBounds(30, 580, 150, 150);
		myImageLabel.setVisible(true);
		this.add(myImageLabel);

		// 라이벌 이미지
		rivalImageLabel = new JLabel();
		BufferedImage rivalImage = ImageIO.read(new File("img/그림4.png"));
		rivalImageLabel.setIcon(new ImageIcon(rivalImage));
		rivalImageLabel.setBounds(800, 30, 150, 150);
		rivalImageLabel.setVisible(true);
		this.add(rivalImageLabel);

		// 내 카드
		myCardLabel = new JLabel();
		BufferedImage myCard = ImageIO.read(new File("img/profile.jpg"));
		myCardLabel.setIcon(new ImageIcon(myCard));
		myCardLabel.setBounds(130, 400, 100, 145);
		myCardLabel.setVisible(true);
		this.add(myCardLabel);

		// 라이벌 카드
		rivalCardLabel	= new JLabel();
		rivalCard		= ImageIO.read(new File("img/profile.jpg"));
		rivalCardLabel.setIcon(new ImageIcon(rivalCard));
		rivalCardLabel.setBounds(750, 200, 100, 145);
		rivalCardLabel.setVisible(true);
		this.add(rivalCardLabel);

		// 내 자금
		myMoneyLabel = new JLabel();
		myMoneyLabel.setText("나의 칩 수 : 50");
		myMoneyLabel.setBounds(200, 630, 200, 40);
		myMoneyLabel.setFont(new Font("San Serif", Font.PLAIN, 20));
		myMoneyLabel.setForeground(Color.white);
		myMoneyLabel.setVisible(true);
		this.add(myMoneyLabel);

		// 라이벌 자금
		rivalMoneyLabel = new JLabel();
		rivalMoneyLabel.setText("상대방 칩 수: 50");
		rivalMoneyLabel.setBounds(680, 100, 200, 40);
		rivalMoneyLabel.setFont(new Font("San Serif", Font.PLAIN, 20));
		rivalMoneyLabel.setForeground(Color.white);
		rivalMoneyLabel.setVisible(true);
		this.add(rivalMoneyLabel);

		/* // 총 금액
		 * totalBetLabel = new JLabel("총 금액");
		 * totalBetLabel.setBounds(430, 250, 100, 145);
		 * totalBetLabel.setFont(new Font("San Serif", Font.PLAIN, 30));
		 * totalBetLabel.setHorizontalAlignment(SwingConstants.CENTER);
		 * totalBetLabel.setVisible(false);
		 * this.add(totalBetLabel); */

		// 승 패 알림
		BufferedImage winimg = ImageIO.read(new File("img/winimg2.png"));
		resultLabel = new JLabel();
		resultLabel.setIcon(new ImageIcon(winimg));
		resultLabel.setBounds(300, 280, 387, 228);
		resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		resultLabel.setVisible(false);
		this.add(resultLabel);

		/* // 총 금액 숫자
		 * totalBetNumberLabel = new JLabel("0");
		 * totalBetNumberLabel.setBounds(430, 300, 100, 145);
		 * totalBetNumberLabel.setFont(new Font("San Serif", Font.PLAIN, 30));
		 * totalBetNumberLabel.setForeground(Color.RED);
		 * totalBetNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		 * totalBetNumberLabel.setVisible(false);
		 * this.add(totalBetNumberLabel); */

		// 나의 배팅 금액
		myBettingTextField = new JTextField("0");
		myBettingTextField.setForeground(Color.white);
		myBettingTextField.setBackground(Color.GRAY);
		myBettingTextField.setHorizontalAlignment(SwingConstants.CENTER);
		myBettingTextField.setBounds(450, 600, 50, 30);
		myBettingTextField.setVisible(false);
		this.add(myBettingTextField);

		// 배팅 금액 상향
		BufferedImage upimg = ImageIO.read(new File("img/upbtn.png"));
		upBetting = new JButton();
		upBetting.setIcon(new ImageIcon(upimg));
		upBetting.setHorizontalAlignment(SwingConstants.CENTER);
		upBetting.setBounds(510, 590, 50, 25);
		upBetting.setContentAreaFilled(false);
		upBetting.setFocusPainted(false);
		upBetting.setVisible(false);
		upBetting.addActionListener(new BtnBettingListener(parent, this));
		this.add(upBetting);

		// 배팅 금액 하향
		BufferedImage dwnimg = ImageIO.read(new File("img/dwnbtn.png"));
		downBetting = new JButton();
		downBetting.setIcon(new ImageIcon(dwnimg));
		downBetting.setHorizontalAlignment(SwingConstants.CENTER);
		downBetting.setBounds(510, 620, 50, 25);
		downBetting.setContentAreaFilled(false);
		downBetting.setFocusPainted(false);
		downBetting.setVisible(false);
		downBetting.addActionListener(new BtnBettingListener(parent, this));
		this.add(downBetting);

		// 배팅 금액 확정
		BufferedImage savebetimg = null;
		savebetimg			= ImageIO.read(new File("img/okbtn.png"));
		saveBettingButton	= new JButton();
		saveBettingButton.setIcon(new ImageIcon(savebetimg));
		saveBettingButton.setHorizontalAlignment(SwingConstants.CENTER);
		saveBettingButton.setBounds(450, 650, 110, 25);
		saveBettingButton.setContentAreaFilled(false);
		saveBettingButton.setVisible(false);
		saveBettingButton.addActionListener(new BtnBettingListener(parent, this));
		this.add(saveBettingButton);

		// 배팅 버튼
		BufferedImage batimg = null;
		batimg			= ImageIO.read(new File("img/batbtn.png"));
		bettingButton	= new JButton();
		bettingButton.setIcon(new ImageIcon(batimg));
		bettingButton.setBounds(450, 680, 100, 40);
		bettingButton.setContentAreaFilled(false);
		bettingButton.setVisible(true);
		bettingButton.addActionListener(new BtnBettingListener(parent, this));
		this.add(bettingButton);

		// 포기 버튼
		BufferedImage giveupimg = null;
		giveupimg		= ImageIO.read(new File("img/giveupbtn.png"));
		giveupButton	= new JButton();
		giveupButton.setIcon(new ImageIcon(giveupimg));
		giveupButton.setBounds(550, 680, 100, 40);
		giveupButton.setContentAreaFilled(false);
		giveupButton.setVisible(true);
		giveupButton.addActionListener(new BtnGiveupListener(this));
		this.add(giveupButton);

		// 선/후공 모델에서 받아오기
		// 선/후공 이미지
		turn			= parent.game.getAccount().isPriority();
		turnImageLabel	= new JLabel();
		BufferedImage turnImage = null;
		if (turn == true) {
			turnImage = ImageIO.read(new File("img/turn_a.png"));
		} else if (turn == false) {
			turnImage = ImageIO.read(new File("img/turn_b.png"));
		}
		turnImageLabel.setIcon(new ImageIcon(turnImage));
		turnImageLabel.setBounds(300, 270, 150, 150);
		turnImageLabel.setVisible(true);
		turnImageLabel.addMouseListener(new PriorityMouseListener(parent, this));
		this.add(turnImageLabel);

		// 선/후공 레이블
		turnLabel = new JLabel();
		if (turn == true) {
			turnLabel.setText("선공");
		} else if (turn == false) {
			turnLabel.setText("후공");
		}
		turnLabel.setFont(new Font("San Serif", Font.BOLD, 60));
		turnLabel.setForeground(Color.white);
		turnLabel.setBounds(480, 270, 150, 150);
		turnLabel.setVisible(true);
		this.add(turnLabel);

		/* Timer timer = new Timer(3000, e -> {
		 * // 10초 경과 후 선/후공 숨기고 나머지 노출
		 * turnLabel.setVisible(false);
		 * turnImageLabel.setVisible(false);
		 * 
		 * totalBetLabel.setVisible(true);
		 * totalBetNumberLabel.setVisible(true);
		 * resultLabel.setVisible(true);
		 * 
		 * // 타이머 정지
		 * ((Timer) e.getSource()).stop();
		 * });
		 * timer.start(); */

		// 나가기 버튼
		BufferedImage exitimg = ImageIO.read(new File("img/exitbtn3.png"));
		exitRoomButton = new JButton();
		exitRoomButton.setIcon(new ImageIcon(exitimg));
		exitRoomButton.setBounds(820, 680, 120, 40);
		exitRoomButton.setContentAreaFilled(false);
		exitRoomButton.setVisible(true);
		exitRoomButton.addActionListener(new BtnExitRoomListener(parent, this));
		this.add(exitRoomButton);

		this.setBounds(0, 0, 1000, 800);
		this.setLayout(null);

		/* this.addWindowListener(new java.awt.event.WindowAdapter() {
		 * 
		 * @Override
		 * public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		 * if (JOptionPane.showConfirmDialog(_this,
		 * "게임에서 나가시겠습니까?", "게임에서 나가시겠습니까?",
		 * JOptionPane.YES_NO_OPTION,
		 * JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
		 * parent.disableGamePanel();
		 * parent.enableWaitingPanel();
		 * }
		 * }
		 * }); */

	}

	public void paintComponent(Graphics g) {
		g.drawImage(bgImage.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}

	public void enableOrDisableBettingButton(boolean flag) {

		upBetting.setEnabled(flag);
		downBetting.setEnabled(flag);
		saveBettingButton.setEnabled(flag);
		bettingButton.setEnabled(flag);
		giveupButton.setEnabled(flag);

	}//enableOrDisableBettingButton()

}//GamePanel class
