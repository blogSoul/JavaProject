package Handler;

import java.awt.event.ActionEvent;
/*
 * 배팅버튼에 대한 리스너로서
 * 위,아래버튼에 따른 숫자변화와
 * 확인을 눌렀을 때의 변화를 주었다*/
import java.awt.event.ActionListener;

import com.google.gson.Gson;

import Data.ClientGame;
import view.GamePanel;
import view.PrimaryPanel;

public class BtnBettingListener implements ActionListener {

	private GamePanel		_Pgame;
	private PrimaryPanel	parent;
	public ClientGame		game	= new ClientGame();
	public Gson				gson	= new Gson();

	public BtnBettingListener(PrimaryPanel p, GamePanel Pgame) {
		this.parent	= p;
		_Pgame		= Pgame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		//배팅버튼
		if (obj == _Pgame.bettingButton) {
			_Pgame.myBettingTextField.setVisible(true);
			_Pgame.upBetting.setVisible(true);
			_Pgame.downBetting.setVisible(true);
			_Pgame.saveBettingButton.setVisible(true);
		} //if
			//
		else if (obj == _Pgame.upBetting) {
			String	valueString	= _Pgame.myBettingTextField.getText();
			Integer	value		= Integer.parseInt(valueString);
			value += 1;
			_Pgame.myBettingTextField.setText(value.toString());
		} //else if
			//
		else if (obj == _Pgame.downBetting) {
			String	valueString	= _Pgame.myBettingTextField.getText();
			Integer	value		= Integer.parseInt(valueString);
			value -= 1;
			if (value < 0) {
				value = 0;
			}
			_Pgame.myBettingTextField.setText(value.toString());
		} //else if
			//
		else if (obj == _Pgame.saveBettingButton) {
			parent.connectServer("배팅");//확인을 눌렀을 때 베팅이라는 유형으로 서버와 연결을 시도

			_Pgame.myBettingTextField.setVisible(false);
			_Pgame.upBetting.setVisible(false);
			_Pgame.downBetting.setVisible(false);
			_Pgame.saveBettingButton.setVisible(false);
		} //else if

	}//actionPerformed()

}//BtnBettingListener class
