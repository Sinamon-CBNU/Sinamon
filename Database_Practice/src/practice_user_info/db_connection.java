package practice_user_info;

import java.sql.Connection;
import java.sql.DriverManager;

//import com.mysql.cj.protocol.Resultset;
//import com.mysql.cj.xdevapi.Statement;
import java.sql.Statement;

import com.mysql.cj.util.DnsSrv.SrvRecord;

import java.sql.ResultSet;

public class db_connection {
	
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public db_connection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sinamon","root","kksshh1735");
			st =con.createStatement();
		}
		catch(Exception e){
			System.out.println("�����ͺ��̽� ���� ����: " + e.getMessage());
		}
	}
	
	public boolean is_info(String id, String pwd) {
		try {
			String SQL = "SELECT * FROM user_info WHERE user_id = " + id + "and password = " + pwd;
			rs = st.executeQuery(SQL);
			if(rs.next()) {
				System.out.println(rs.getString(4));
				System.out.println(rs.getString(5));
			}
		} 
		catch (Exception e) {
			System.out.println("�����ͺ��̽� �˻� ����: " + e.getMessage());
		}
		return false;	//�˻����� �ʾҴٸ� false ��ȯ
	}
	
	public static void main(String[] args) {
		
		

	}

}
