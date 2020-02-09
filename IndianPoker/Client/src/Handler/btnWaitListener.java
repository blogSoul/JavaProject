package Handler;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import Data.Account;
import Data.ClientGame;
import view.PrimaryPanel;
import view.WaitingRoomPanel;

public class btnWaitListener implements ActionListener, MouseListener {

   public PrimaryPanel      parent;
   public WaitingRoomPanel   wait;
   public Account         a      = new Account();
   public ClientGame      game   = new ClientGame();
   public Gson            gson   = new Gson();

   public btnWaitListener(PrimaryPanel p, WaitingRoomPanel w) {
      this.parent   = p;
      this.wait   = w;
   }

   @Override
   public void actionPerformed(ActionEvent e) {

      Object obj = e.getSource();
      if (obj == wait.logoutButton) {
         parent.connectServer("logout");
         parent.outMsg.println(gson.toJson(a));//로그아웃을 눌렀을 때 서버에 전달하는 Gson
         parent.disableWaitingPanel();
         parent.enableLoginPanel();
      } else if (obj == wait.exitButton) {

         int result = JOptionPane.showConfirmDialog(parent, "정말 종료하시겠습니까?", "게임종료", JOptionPane.YES_NO_OPTION);

         if (result == JOptionPane.YES_OPTION) {
            parent.connectServer("exit");
         }
      } 
   }

   @Override
   public void mouseClicked(MouseEvent e) {}

   @Override
   public void mousePressed(MouseEvent e) {
      Point   point   = e.getPoint();
      int      row      = wait.roomTabel.rowAtPoint(point);
      if (e.getClickCount() == 2 && wait.roomTabel.getSelectedRow() != -1) {
         try {

            parent.connectServer("게임방 확인");

         } catch (Exception e1) {
            e1.printStackTrace();
         }
      }
   }

   @Override
   public void mouseReleased(MouseEvent e) {}

   @Override
   public void mouseEntered(MouseEvent e) {}

   @Override
   public void mouseExited(MouseEvent e) {}

}