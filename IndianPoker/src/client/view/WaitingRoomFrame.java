package client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import client.controller.AppManager;

public class WaitingRoomFrame extends JFrame {

	//data
	public JLabel			profileLabel;
	public JLabel			idLabel;

	public BufferedImage	backPicture;

	public JButton			rankButton;
	public JButton			logoutButton;
	public JButton			exitButton;

	public JTable			roomTabel;

	public WaitingRoomFrame	waitingRoomFrame;
	public LoginFrame		loginFrame;
	public RankingFrame		rankingFrame;

	//method
	public WaitingRoomFrame() throws IOException {

		waitingRoomFrame	= this;
		loginFrame			= AppManager.getInstanceManager().getLogin();

		// 프로필사진 레이블
		profileLabel		= new JLabel();
		backPicture			= ImageIO.read(new File("img/profile.jpg"));
		profileLabel.setIcon(new ImageIcon(backPicture));
		profileLabel.setBounds(50, 80, 200, 145);
		profileLabel.setHorizontalAlignment(SwingConstants.CENTER);
		profileLabel.setVisible(true);
		this.add(profileLabel);

		// ID 레이블
		idLabel = new JLabel("홍길동");
		idLabel.setBounds(50, 200, 200, 80);
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setFont(new Font("San Serif", Font.PLAIN, 25));
		idLabel.setVisible(true);
		this.add(idLabel);

		// 랭킹 레이블
		rankButton = new JButton("Rank");
		rankButton.setBounds(75, 300, 150, 40);
		rankButton.setFont(new Font("San Serif", Font.PLAIN, 25));
		rankButton.setHorizontalAlignment(SwingConstants.CENTER);
		rankButton.setVisible(true);
		rankButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (rankingFrame == null) {
					rankingFrame = new RankingFrame();
					AppManager.getInstanceManager().setRanking(rankingFrame);
				} else {
					AppManager.getInstanceManager().getRanking().openRanking();
				}
			}
		});
		this.add(rankButton);

		// 로그아웃 버튼
		logoutButton = new JButton("Logout");
		logoutButton.setBounds(720, 30, 100, 40);
		logoutButton.setVisible(true);
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeWaitingRoom();
				loginFrame.openLoginFrame();
			}
		});
		this.add(logoutButton);

		// 나가기 버튼
		exitButton = new JButton("Exit");
		exitButton.setBounds(830, 30, 100, 40);
		exitButton.setVisible(true);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		this.add(exitButton);

		// 방 목록
		String[][]	roomList			= {
				{ "방 제목1", "1 / 2" },
				{ "방 제목2", "2 / 2" },
				{ "방 제목3", "1 / 2" }
		};

		// 테이블의 각 컬럼이름 (실제로 노출은 안됨)
		String[]	tableColumnNames	= { "방 제목", "인원 수" };
		// 방 목록을 보여주는 테이블
		roomTabel = new JTable(roomList, tableColumnNames);
		roomTabel.setDefaultEditor(Object.class, null);
		roomTabel.setBounds(400, 80, 530, 480);
		roomTabel.setRowHeight(160);
		roomTabel.setFont(new Font("San Serif", Font.PLAIN, 20));
		// 테두리 설정
		roomTabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		roomTabel.setVisible(true);
		// 각 컬럼의 넓이 조절
		roomTabel.getColumnModel().getColumn(0).setPreferredWidth(400);
		roomTabel.getColumnModel().getColumn(1).setPreferredWidth(130);

		// '인원 수' 컬럼의 텍스트 중앙정렬
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		roomTabel.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		roomTabel.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

		roomTabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				Point	point	= mouseEvent.getPoint();
				int		row		= roomTabel.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 2 && roomTabel.getSelectedRow() != -1) {
					try {
						GameFrame gameFrame = new GameFrame(waitingRoomFrame);
					} catch (IOException e) {
						e.printStackTrace();
					}
					closeWaitingRoom();
				}
			}
		});

		this.add(roomTabel);

		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 800);
		this.setVisible(true);
		this.setResizable(false);
		this.setTitle("대기실");

	}

	private void closeWaitingRoom() {
		this.setVisible(false);
	}

}//WaitingRoomFrame class
