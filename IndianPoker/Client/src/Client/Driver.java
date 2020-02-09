package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import com.google.gson.Gson;

import Data.Account;
import view.PrimaryPanel;


public class Driver {//frame을 생성한 다음에 primaryPanel(여기서 Client 다룸)을 생성한 것을 add한다.

	public static void main(String[] args) {

		JFrame frame = new JFrame("Indian Poker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PrimaryPanel primary = new PrimaryPanel();
		frame.getContentPane().add(primary);
		
		frame.pack();
		frame.setVisible(true);
		
	}

}
