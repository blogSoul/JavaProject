package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDBManager {

	//data
	private String				jdbcDriver;
	private String				jdbcUrl;
	private Connection			conn;

	private PreparedStatement	pstmt;
	private ResultSet			rs;

	private String				sql;		//저장할 sql문

	//method
	public UserDBManager() {

		//데이터 생성
		jdbcDriver	= "com.mysql.cj.jdbc.Driver";
		jdbcUrl		= "jdbc:mysql://localhost/poker_db?characterEncoding=UTF-8&serverTimezone=UTC";

	}//constructor 

	//@@DB 연결시도
	public void connectDB() {

		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(jdbcUrl, "root", "0000");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}//connectDB()

	//@@DB 종료
	public void closeDB() {
		try {
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}//closeDB()

	//@@해당 아이디를 가진 유저 정보 반환
	public Message identifyUserID(String id) {

		sql = "select * from poker_user where id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			Message m = new Message();
			if (rs.next()) {
				m.setId(rs.getString(1));
				m.setPasswd(rs.getString(2));
				m.setId_win(rs.getInt(3));
				m.setId_lose(rs.getInt(4));
				m.setId_status(rs.getInt(5));
			}

			return m;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}//identifyUserID

	//@@해당 아이디와 비밀번호를 가진 유저 정보 반환
	public Message identifyUserIdAndPassword(String id, String passwd) {

		connectDB();

		sql = "select * from poker_user where id = ? && passwd = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();

			Message m = new Message();
			if (rs.next()) {
				m.setId(rs.getString(1));
				m.setPasswd(rs.getString(2));
				m.setId_win(rs.getInt(3));
				m.setId_lose(rs.getInt(4));
				m.setId_status(rs.getInt(5));
			}
			closeDB();
			return m;
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeDB();
		return null;
	}//identifyUserIdAndPassword()

	//@@해당 정보를 판별하고, 데이터베이스에 유저 정보 삽입 여부 결정
	public boolean signup(String id, String passwd) {

		connectDB();

		Message m = identifyUserID(id);

		//해당 아이디가 없을 때
		if (m.getId() == null) {
			sql = "insert into poker_user values(?, ?, 0, 0, 0)";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, passwd);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();

				closeDB();
				return false;
			}

			closeDB();
			return true;
		} //if
			//해당 아이디가 있을 때
		else {
			closeDB();
			return false;
		} //else

	}//signup()

	//@@로그인할 때, DB에서 접속 여부 수정
	public void updateUserStatusLogin(String id, String passwd) {
		connectDB();

		sql = "update poker_user set id_status = 1 where id = ? && passwd = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			pstmt.executeUpdate();

			closeDB();
		} catch (Exception e) {
			e.printStackTrace();
			closeDB();
		}
		closeDB();
	}//updateUserStatusLogin()

	public void updateUserStatusLogout(String id, String passwd, String win, String lose) {

		connectDB();

		sql = "update poker_user set id_status = 0, id_win = ?, id_lose = ? where id = ? && passwd = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(win));
			pstmt.setInt(2, Integer.parseInt(lose));
			pstmt.setString(3, id);
			pstmt.setString(4, passwd);
			pstmt.executeUpdate();

			closeDB();
		} catch (Exception e) {
			e.printStackTrace();
			closeDB();
		}
		closeDB();
	}//updateUserStatusLogout()

}//Data.UserDBManager class
