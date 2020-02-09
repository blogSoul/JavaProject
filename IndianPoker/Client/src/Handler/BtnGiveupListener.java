package Handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.GamePanel;
/*
 * 포기버튼을 눌렀을 때 변화*/
public class BtnGiveupListener implements ActionListener {

	private GamePanel _Pgame;

	public BtnGiveupListener(GamePanel Pgame) {
		_Pgame = Pgame;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == _Pgame.giveupButton) {
			if (JOptionPane.showConfirmDialog(_Pgame,
					"턴을 포기하시겠습니까?\n 이번 턴을 포기하고 새 카드를 뽑게됩니다.", "이번 턴 포기",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				//새로운 턴 설정(??
			}
		}

	}//actionPerformed()

}//BtnGiveupListener class
