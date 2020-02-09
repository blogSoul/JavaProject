package Handler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.google.gson.Gson;

import Data.ClientGame;
import view.GamePanel;
import view.PrimaryPanel;

public class PriorityMouseListener implements MouseListener {

	private GamePanel		_Pgame;
	private PrimaryPanel	parent;
	public ClientGame		game	= new ClientGame();
	public Gson				gson	= new Gson();

	public PriorityMouseListener(PrimaryPanel p, GamePanel Pgame) {
		this.parent	= p;
		_Pgame		= Pgame;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		if (obj == _Pgame.turnImageLabel) {
			try {

				int num = parent.game.getAccount().getApposite_Card().getNumber();//서버에게 받은 상대방의 카드이미지의 위치를 가져오는 작업

				_Pgame.rivalCard = ImageIO.read(new File("img/card " + num + ".png"));

			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				_Pgame.rivalCardLabel.setIcon(new ImageIcon(_Pgame.rivalCard));
				//_Pgame.rivalCardLabel.setSize(100,145);
				_Pgame.myMoneyLabel.setText("나의 칩 수: " + Integer.toString(parent.game.getAccount().getChipSet()));
				_Pgame.turnImageLabel.setVisible(false);
				_Pgame.turnLabel.setVisible(false);

			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
