package client.view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class RankingFrame extends JFrame {
	public RankingFrame() {

		JLabel titleLabel = new JLabel("Rank");
		titleLabel.setBounds(40, 30, 500, 80);
		titleLabel.setFont(new Font("San Serif", Font.PLAIN, 50));
		titleLabel.setVisible(true);
		this.add(titleLabel);

		String[][] ranks = {
				{ "Name1", "10W 0L" },
				{ "Man", "10W 2L" },
				{ "Girl", "7W 2L" },
				{ "8조", "5W 3L" },
				{ "고수", "1W 1L" }
		};

		for (int i = 0; i < ranks.length; i++) {
			JLabel rankNo = new JLabel("" + (i + 1));
			rankNo.setBounds(40, 130 + (i * 70), 50, 70);
			rankNo.setFont(new Font("San Serif", Font.PLAIN, 20));

			JLabel name = new JLabel(ranks[i][0]);
			name.setBounds(100, 130 + (i * 70), 100, 70);
			name.setFont(new Font("San Serif", Font.PLAIN, 20));

			JLabel rate = new JLabel(ranks[i][1]);
			rate.setBounds(250, 130 + (i * 70), 150, 70);
			rate.setFont(new Font("San Serif", Font.PLAIN, 20));

			this.add(rankNo);
			this.add(name);
			this.add(rate);
		}

		// 프레임 설정
		this.setLayout(null);
		this.setSize(400, 600);
		this.setVisible(true);
	}
}
