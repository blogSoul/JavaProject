package Handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.PrimaryPanel;
import view.SignupPanel;

public class btnSignupListener implements ActionListener {
	public PrimaryPanel	parent;
	public SignupPanel	sign;

	public btnSignupListener(PrimaryPanel p, SignupPanel sign) {
		this.parent	= p;
		this.sign	= sign;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == sign.checkButton) {
			parent.connectServer("checkId");
		}

		//동의 버튼
		else if (obj == sign.agreeButton) {
			if (sign.disagreeButton.isSelected()) {
				sign.disagreeButton.setSelected(false);
				sign.signupButton.setEnabled(true);
			} //if
		} //else if

		//비동의 버튼
		else if (obj == sign.disagreeButton) {
			if (sign.agreeButton.isSelected()) {
				sign.agreeButton.setSelected(false);
				sign.disagreeButton.setSelected(true);
			}
		} //else if

		//확인 버튼
		else if (obj == sign.signupButton) {

			//1. 중복검사를 하지 않은 경우
			if (sign.checkButton.isSelected()) {
				JOptionPane.showMessageDialog(parent, "아이디 중복 검사를 맡으세요!!", "아이디 중복 확인", JOptionPane.WARNING_MESSAGE);
			} //if

			//1. 중복검사를 한 경우
			else {
				String	pw		= sign.pwTextField.getText();
				String	rePw	= sign.pwReTextField.getText();

				//2. 비밀번호가 공백
				if (pw == "" || rePw == "") {
					JOptionPane.showMessageDialog(parent, "비밀번호는 공백일 수 없습니다!!", "비밀번호 확인", JOptionPane.WARNING_MESSAGE);
				} //if

				//2. 비밀번호가 공백 아님
				else {

					//3. 비밀번호가 일치
					if (pw.equals(rePw)) {

						//4. 동의 버튼 활성화
						if (sign.agreeButton.isSelected()) {
							parent.connectServer("sign");
						}

						//4. 동의 버튼 비활성
						else {
							JOptionPane.showMessageDialog(parent, "개인정보 약관에 동의해야 합니다!!", "개인정보 약관 확인", JOptionPane.WARNING_MESSAGE);
						}

					} //if

					//3. 비밀번호 불일치
					else {
						JOptionPane.showMessageDialog(parent, "비밀번호가 맞지 않습니다!!", "비밀번호 확인", JOptionPane.WARNING_MESSAGE);
					} //else

				} //else

			} //else

		} //else if

		//취소 버튼
		else if (obj == sign.exitButton) {
			parent.disableSignPanel();
			parent.enableLoginPanel();
		} //else if
	}

}
