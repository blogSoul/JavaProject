package Handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.google.gson.Gson;

import Data.ClientGame;
import view.GamePanel;
import view.PrimaryPanel;


/*
 * 엑시트를 눌렀을 때 변화*/
public class BtnExitRoomListener implements ActionListener {

	private PrimaryPanel	_parent;
	private GamePanel		_Pgame;
	public ClientGame		game	= new ClientGame();
	public Gson				gson	= new Gson();

	public BtnExitRoomListener(PrimaryPanel parent, GamePanel Pgame) {
		this._parent	= parent;
		_Pgame			= Pgame;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == _Pgame.exitRoomButton) {

			_parent.disableGamePanel();
			_parent.enableWaitingPanel();
		} //if
	}//actionPerformed()

}//BtnExitRoomListener class
