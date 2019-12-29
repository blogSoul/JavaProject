package client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class GameFrame extends JFrame {
	public GameFrame(WaitingRoomFrame waitingRoomFrame) throws IOException {
		GameFrame	_this		= this;
		// 메세지 영역
		JTextArea	messageArea	= new JTextArea("A가 2 배팅\nB가 4 배팅");
		messageArea.setBounds(10, 20, 350, 150);
		messageArea.setEditable(false);
		messageArea.setVisible(true);
		this.add(messageArea);

		// 내 이미지
		JLabel			myImageLabel	= new JLabel();
		BufferedImage	myImage			= ImageIO.read(new File("img/rivalImg.png"));
		myImageLabel.setIcon(new ImageIcon(myImage));
		myImageLabel.setBounds(30, 580, 150, 150);
		myImageLabel.setVisible(true);
		this.add(myImageLabel);

		JLabel myEmoticonLabel = new JLabel();
		myEmoticonLabel.setBounds(190, 580, 100, 70);
		myEmoticonLabel.setVisible(true);
		this.add(myEmoticonLabel);

		// 라이벌 이미지
		JLabel			rivalImageLabel	= new JLabel();
		BufferedImage	rivalImage		= ImageIO.read(new File("img/rivalImg.png"));
		rivalImageLabel.setIcon(new ImageIcon(rivalImage));
		rivalImageLabel.setBounds(800, 30, 150, 150);
		rivalImageLabel.setVisible(true);
		this.add(rivalImageLabel);

		// 내 카드
		JLabel			myCardLabel	= new JLabel();
		BufferedImage	myCard		= ImageIO.read(new File("img/profile.jpg"));
		myCardLabel.setIcon(new ImageIcon(myCard));
		myCardLabel.setBounds(130, 400, 100, 145);
		myCardLabel.setVisible(true);
		this.add(myCardLabel);

		// 라이벌 카드
		JLabel			rivalCardLabel	= new JLabel();
		BufferedImage	rivalCard		= ImageIO.read(new File("img/card 20.png"));
		rivalCardLabel.setIcon(new ImageIcon(rivalCard));
		rivalCardLabel.setBounds(750, 200, 100, 145);
		rivalCardLabel.setVisible(true);
		this.add(rivalCardLabel);

		// 내 자금
		JLabel myMoneyLabel = new JLabel("금액 : 18");
		myMoneyLabel.setBounds(200, 630, 100, 40);
		myMoneyLabel.setFont(new Font("San Serif", Font.PLAIN, 20));
		myMoneyLabel.setVisible(true);
		this.add(myMoneyLabel);

		// 라이벌 자금
		JLabel rivalMoneyLabel = new JLabel("금액 : 16");
		rivalMoneyLabel.setBounds(680, 30, 100, 40);
		rivalMoneyLabel.setFont(new Font("San Serif", Font.PLAIN, 20));
		rivalMoneyLabel.setVisible(true);
		this.add(rivalMoneyLabel);

		// 총 금액
		JLabel totalBetLabel = new JLabel("총 금액");
		totalBetLabel.setBounds(430, 250, 100, 145);
		totalBetLabel.setFont(new Font("San Serif", Font.PLAIN, 30));
		totalBetLabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalBetLabel.setVisible(false);
		this.add(totalBetLabel);

		// 승 패 알림
		JLabel resultLabel = new JLabel("WIN");
		resultLabel.setBounds(400, 380, 150, 145);
		resultLabel.setFont(new Font("San Serif", Font.PLAIN, 40));
		resultLabel.setForeground(Color.BLUE);
		resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		resultLabel.setVisible(false);
		this.add(resultLabel);

		// 총 금액 숫자
		JLabel totalBetNumberLabel = new JLabel("530");
		totalBetNumberLabel.setBounds(430, 300, 100, 145);
		totalBetNumberLabel.setFont(new Font("San Serif", Font.PLAIN, 30));
		totalBetNumberLabel.setForeground(Color.RED);
		totalBetNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalBetNumberLabel.setVisible(false);
		this.add(totalBetNumberLabel);

		// 나의 배팅 금액
		JTextField myBettingTextField = new JTextField("0");
		myBettingTextField.setHorizontalAlignment(SwingConstants.CENTER);
		myBettingTextField.setBounds(450, 600, 50, 30);
		myBettingTextField.setVisible(false);
		this.add(myBettingTextField);

		// 배팅 금액 상향
		JButton upBetting = new JButton("▲");
		upBetting.setHorizontalAlignment(SwingConstants.CENTER);
		upBetting.setBounds(510, 590, 50, 25);
		upBetting.setVisible(false);
		upBetting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String	valueString	= myBettingTextField.getText();
				Integer	value		= Integer.parseInt(valueString);
				value += 1;
				myBettingTextField.setText(value.toString());
			}
		});
		this.add(upBetting);

		// 배팅 금액 하향
		JButton downBetting = new JButton("▼");
		downBetting.setHorizontalAlignment(SwingConstants.CENTER);
		downBetting.setBounds(510, 620, 50, 25);
		downBetting.setVisible(false);
		downBetting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String	valueString	= myBettingTextField.getText();
				Integer	value		= Integer.parseInt(valueString);
				value -= 1;
				if (value < 0) {
					value = 0;
				}
				myBettingTextField.setText(value.toString());
			}
		});
		this.add(downBetting);

		// 배팅 금액 확정
		JButton saveBettingButton = new JButton("OK");
		saveBettingButton.setHorizontalAlignment(SwingConstants.CENTER);
		saveBettingButton.setBounds(450, 650, 110, 25);
		saveBettingButton.setVisible(false);
		saveBettingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myBettingTextField.setVisible(false);
				upBetting.setVisible(false);
				downBetting.setVisible(false);
				saveBettingButton.setVisible(false);
			}
		});
		this.add(saveBettingButton);

		// 배팅 버튼
		JButton bettingButton = new JButton("배팅");
		bettingButton.setBounds(450, 680, 100, 40);
		bettingButton.setFont(new Font("San Serif", Font.PLAIN, 20));
		bettingButton.setVisible(true);
		bettingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myBettingTextField.setVisible(true);
				upBetting.setVisible(true);
				downBetting.setVisible(true);
				saveBettingButton.setVisible(true);
			}
		});
		this.add(bettingButton);

		// 포기 버튼
		JButton giveupButton = new JButton("포기");
		giveupButton.setBounds(550, 680, 100, 40);
		giveupButton.setFont(new Font("San Serif", Font.PLAIN, 20));
		giveupButton.setVisible(true);
		this.add(giveupButton);

		MouseAdapter	emoticonEvent	= new MouseAdapter() {
											@Override
											public void mousePressed(MouseEvent e) {
												JLabel	emoticonLabel	= (JLabel) e.getSource();
												Icon	icon			= emoticonLabel.getIcon();
												myEmoticonLabel.setIcon(icon);
											}
										};

		// 이모티콘1
		JLabel			emoticon1Label	= new JLabel();
		BufferedImage	emoticon1		= ImageIO.read(new File("img/던말.jpg"));
		emoticon1Label.setIcon(new ImageIcon(emoticon1));
		emoticon1Label.setBounds(650, 530, 100, 70);
		emoticon1Label.setVisible(false);
		emoticon1Label.addMouseListener(emoticonEvent);
		this.add(emoticon1Label);

		// 이모티콘2
		JLabel			emoticon2Label	= new JLabel();
		BufferedImage	emoticon2		= ImageIO.read(new File("img/묻더블.jpg"));
		emoticon2Label.setIcon(new ImageIcon(emoticon2));
		emoticon2Label.setBounds(750, 530, 100, 70);
		emoticon2Label.addMouseListener(emoticonEvent);
		emoticon2Label.setVisible(false);
		this.add(emoticon2Label);

		// 이모티콘3
		JLabel			emoticon3Label	= new JLabel();
		BufferedImage	emoticon3		= ImageIO.read(new File("img/미심쩍.jpg"));
		emoticon3Label.setIcon(new ImageIcon(emoticon3));
		emoticon3Label.setBounds(850, 530, 100, 70);
		emoticon3Label.addMouseListener(emoticonEvent);
		emoticon3Label.setVisible(false);
		this.add(emoticon3Label);

		// 이모티콘4
		JLabel			emoticon4Label	= new JLabel();
		BufferedImage	emoticon4		= ImageIO.read(new File("img/속시원.jpg"));
		emoticon4Label.setIcon(new ImageIcon(emoticon4));
		emoticon4Label.setBounds(650, 600, 100, 70);
		emoticon4Label.addMouseListener(emoticonEvent);
		emoticon4Label.setVisible(false);
		this.add(emoticon4Label);

		// 이모티콘5
		JLabel			emoticon5Label	= new JLabel();
		BufferedImage	emoticon5		= ImageIO.read(new File("img/왜이럼.jpg"));
		emoticon5Label.setIcon(new ImageIcon(emoticon5));
		emoticon5Label.setBounds(750, 600, 100, 70);
		emoticon5Label.addMouseListener(emoticonEvent);
		emoticon5Label.setVisible(false);
		this.add(emoticon5Label);

		// 이모티콘 버튼
		JButton emoticonButton = new JButton("이모티콘");
		emoticonButton.setBounds(650, 680, 100, 40);
		emoticonButton.setFont(new Font("San Serif", Font.PLAIN, 15));
		emoticonButton.setVisible(true);
		emoticonButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (emoticon1Label.isVisible()) {
					emoticon1Label.setVisible(false);
					emoticon2Label.setVisible(false);
					emoticon3Label.setVisible(false);
					emoticon4Label.setVisible(false);
					emoticon5Label.setVisible(false);
				} else {
					emoticon1Label.setVisible(true);
					emoticon2Label.setVisible(true);
					emoticon3Label.setVisible(true);
					emoticon4Label.setVisible(true);
					emoticon5Label.setVisible(true);
				}
			}
		});
		this.add(emoticonButton);

		// 선/후공 모델에서 받아오기
		int				turn			= getTurn();
		// 선/후공 이미지
		JLabel			turnImageLabel	= new JLabel();
		BufferedImage	turnImage		= null;
		if (turn == 0) {
			turnImage = ImageIO.read(new File("img/turn_a.jpg"));
		} else if (turn == 1) {
			turnImage = ImageIO.read(new File("img/turn_b.png"));
		}
		turnImageLabel.setIcon(new ImageIcon(turnImage));
		turnImageLabel.setBounds(300, 270, 150, 150);
		turnImageLabel.setVisible(true);
		this.add(turnImageLabel);

		// 선/후공 레이블
		JLabel turnLabel = new JLabel();
		if (turn == 0) {
			turnLabel.setText("선공");
		} else if (turn == 1) {
			turnLabel.setText("후공");
		}
		turnLabel.setFont(new Font("San Serif", Font.BOLD, 60));
		turnLabel.setBounds(480, 270, 150, 150);
		turnLabel.setVisible(true);
		this.add(turnLabel);

		Timer timer = new Timer(3000, e -> {
			// 10초 경과 후 선/후공 숨기고 나머지 노출
			turnLabel.setVisible(false);
			turnImageLabel.setVisible(false);

			totalBetLabel.setVisible(true);
			totalBetNumberLabel.setVisible(true);
			resultLabel.setVisible(true);

			// 타이머 정지
			((Timer) e.getSource()).stop();
		});
		timer.start();

		// 나가기 버튼
		JButton exitButton = new JButton("나가기");
		exitButton.setBounds(820, 680, 120, 40);
		exitButton.setFont(new Font("San Serif", Font.PLAIN, 20));
		exitButton.setVisible(true);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeGame();
			}
		});
		this.add(exitButton);

		// 프레임 설정
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(1000, 800);
		this.setVisible(true);
		this.setResizable(false);
		this.setTitle("인디언 포커");

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(_this,
						"게임에서 나가시겠습니까?", "게임에서 나가시겠습니까?",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					_this.setVisible(false);
					waitingRoomFrame.setVisible(true);
				}
			}
		});

	}

	private int getTurn() {
		Random r = new Random();
		return r.nextInt(2);
	}

	private void closeGame() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
