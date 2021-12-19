package practice_user_info;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
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
	
	//ȸ�����Կ��� ���� �Ѱܹ޾� �����ͺ��̽�(sinamon�� user_info ���̺�)�� �����ϴ� �Լ�
	public boolean input_user_info(String name, String nickname, String id, String pwd, String home) {		
		try {
			if(is_info_exist(id)) {		//id���� �����ϴ� user_id�� ���� ��	
				System.out.println("�̹� �����ϴ� �����Դϴ�.");
				return false;
			}
			else {		//���̵� �������� ������ user_info �Է�
				String SQL = "INSERT INTO user_info(name, nickname, user_id, password, home) VALUES("+ name +"," + nickname + "," + id + "," + pwd + "," + home + ");";    
				int record_num = st.executeUpdate(SQL);		//INSERT ������ executeUpdate�� ����Ѵ�. �̰��� ������Ʈ�� �Ǽ��� int�� �����Ѵ�.
				System.out.println("������Ʈ�� �� ��: " + record_num);
			}
		} 
		catch (Exception e) {
			System.out.println("�����ͺ��̽� �Է� ����/ Code: " + e.getMessage());
		}
		return true;
	}
	
	public boolean is_info_exist(String id) {		//�α��� ������ ���̵� ����� ��ġ�ϴ��� Ȯ��
		try {
			String SQL = "SELECT * FROM user_info WHERE user_id = " + id;
			rs = st.executeQuery(SQL);		//SELECT ������ ���
			if(rs.next()) {
				System.out.println(rs.getString(4));
				System.out.println(rs.getString(5));
				return true;
			}	
		} 
		catch (Exception e) {
			System.out.println("�����ͺ��̽� �˻� ����/ Code: " + e.getMessage());
		}
		return false;	//�˻����� �ʾҴٸ� false ��ȯ
	}
	
	public static void main(String[] args) {
		
		

	}

}
