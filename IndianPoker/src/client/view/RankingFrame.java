package client.view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class RankingFrame extends JFrame {

	//data
	public JLabel	titleLabel;

	public JLabel[]	rankNo;
	public JLabel[]	name;
	public JLabel[]	rate;

	//method
	public RankingFrame() {

		titleLabel = new JLabel("Rank");
		titleLabel.setBounds(40, 30, 500, 80);
		titleLabel.setFont(new Font("San Serif", Font.PLAIN, 50));
		titleLabel.setVisible(true);
		this.add(titleLabel);

		//테스트용 문자배열
		String[][] ranks = {
				{ "Name1", "10W 0L" },
				{ "Man", "10W 2L" },
				{ "Girl", "7W 2L" },
				{ "8조", "5W 3L" },
				{ "고수", "1W 1L" }
		};

		rankNo	= new JLabel[5];
		name	= new JLabel[5];
		rate	= new JLabel[5];

		for (int i = 0; i < ranks.length; i++) {
			rankNo[i] = new JLabel("" + (i + 1));
			rankNo[i].setBounds(40, 130 + (i * 70), 50, 70);
			rankNo[i].setFont(new Font("San Serif", Font.PLAIN, 20));

			name[i] = new JLabel(ranks[i][0]);
			name[i].setBounds(100, 130 + (i * 70), 100, 70);
			name[i].setFont(new Font("San Serif", Font.PLAIN, 20));

			rate[i] = new JLabel(ranks[i][1]);
			rate[i].setBounds(250, 130 + (i * 70), 150, 70);
			rate[i].setFont(new Font("San Serif", Font.PLAIN, 20));

			this.add(rankNo[i]);
			this.add(name[i]);
			this.add(rate[i]);
		}

		// 프레임 설정
		this.setLayout(null);
		this.setSize(400, 600);
		this.setVisible(true);
		this.setResizable(false);
		this.setTitle("랭킹");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				closeRanking();
			}
		});
	}

	public void openRanking() {
		this.setVisible(true);
	}

	private void closeRanking() {
		this.setVisible(false);
	}
}//RankingFrame class
