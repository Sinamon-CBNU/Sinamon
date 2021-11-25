package sinamon_project;
	
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
		} catch(Exception e){
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
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� �Է� ����/ Code: " + e.getMessage());
		}
		return true;
	}
	
	//�������� �����ϴ� �Լ�
	public boolean modify_user_info(int btn_num, String user_id, String data) {
		try {		
			String SQL = "";
			if(is_info_exist(user_id)) {		//�����ϴ� user_id�� ���� ��	
				switch (btn_num) {
				case 2: {		//�̸�
					SQL = "UPDATE user_info SET name = " + data + " WHERE user_id = " + user_id + ";";
					int record_num = st.executeUpdate(SQL);		//INSERT ������ executeUpdate�� ����Ѵ�. �̰��� ������Ʈ�� �Ǽ��� int�� �����Ѵ�.
					System.out.println("������Ʈ�� �� ��: " + record_num);
					break;
				}
				case 3: {		//�г���
					SQL = "UPDATE user_info SET nickname = " + data + " WHERE user_id = " + user_id + ";";
					int record_num = st.executeUpdate(SQL);		//INSERT ������ executeUpdate�� ����Ѵ�. �̰��� ������Ʈ�� �Ǽ��� int�� �����Ѵ�.
					System.out.println("������Ʈ�� �� ��: " + record_num);
					break;
				}
				case 4: {		//���̵�
					SQL = "UPDATE user_info SET user_id = " + data + " WHERE user_id = " + user_id + ";";
					int record_num = st.executeUpdate(SQL);		//INSERT ������ executeUpdate�� ����Ѵ�. �̰��� ������Ʈ�� �Ǽ��� int�� �����Ѵ�.
					System.out.println("������Ʈ�� �� ��: " + record_num);
					break;
				}
				case 5: {		//���
					SQL = "UPDATE user_info SET password = " + data + " WHERE user_id = " + user_id + ";";
					int record_num = st.executeUpdate(SQL);		//INSERT ������ executeUpdate�� ����Ѵ�. �̰��� ������Ʈ�� �Ǽ��� int�� �����Ѵ�.
					System.out.println("������Ʈ�� �� ��: " + record_num);
					break;
				}
				case 6: {		//��
					SQL = "UPDATE user_info SET home = " + data + " WHERE user_id = " + user_id + ";";
					int record_num = st.executeUpdate(SQL);		//INSERT ������ executeUpdate�� ����Ѵ�. �̰��� ������Ʈ�� �Ǽ��� int�� �����Ѵ�.
					System.out.println("������Ʈ�� �� ��: " + record_num);
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected btn_num : " + btn_num);
				}
			}
			return true;
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� �Է� ����/ Code: " + e.getMessage());
		}
		return false;
	}
	
	//���̵� ���ڿ��� �޾� user_info ���̺� �����ϴ��� Ȯ���ϴ� �Լ�
	public boolean is_info_exist(String user_id) {		
		try {
			String SQL = "SELECT * FROM user_info WHERE user_id = " + user_id;
			rs = st.executeQuery(SQL);		//SELECT ������ ���
			if(rs.next()) {
				//System.out.println(rs.getString(4));
				//System.out.println(rs.getString(5));
				return true;
			}	
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� �˻� ����/ Code: " + e.getMessage());
		}
		return false;	//�˻����� �ʾҴٸ� false ��ȯ
	}
	
	//���̵�� ��� ���ڿ��� �޾� user_info ���̺� ���̵�� ��� ��ġ�ϴ� �� ������ true ����
	public boolean login(String user_id, String pwd) {	
		try {
			String SQL = "SELECT * FROM user_info WHERE user_id = " + user_id + "and password = " + pwd;
			rs = st.executeQuery(SQL);		//SELECT ������ ���
			if(rs.next()) {
				//System.out.println(rs.getString(4));
				//System.out.println(rs.getString(5));
				return true;
			}	
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� �˻� ����/ Code: " + e.getMessage());
		}
		return false;	//�˻����� �ʾҴٸ� false ��ȯ
	}
	
	public Object[] return_user_info(String user_id) {
		Object[] user_info = new Object[6];
		try {
			String SQL = "SELECT * FROM user_info WHERE user_id = " + user_id;
			rs = st.executeQuery(SQL);		//SELECT ������ ���
			if(rs.next()) {
				user_info[0] = rs.getString("id");			
				user_info[1] = rs.getString("name");
				user_info[2] = rs.getString("nickname");
				user_info[3] = rs.getString("user_id");
				user_info[4] = rs.getString("password");
				user_info[5] = rs.getString("home_id");
			}
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� �˻� ����/ Code: " + e.getMessage());
		}
		return user_info;
	}
	
	//�Խ��ǿ��� ���, �ð�, ����, �г��� �����ϱ�
	public Object[][] show_board(String board_name) {
		Object[][] return_arr = new Object[10][5];
		try {
			String SQL = "SELECT " + board_name + ".id, home_board.home_id, time, title, nickname FROM " + board_name + " LEFT JOIN home_board ON " + board_name + ".home_id = home_board.id WHERE " + board_name + ".id BETWEEN 1 and 10";		//���ϴ� �Խ��ǿ��� id, ���, �ð�, ����, �г��� ��������
			rs = st.executeQuery(SQL);
			for(int i=0; i<10; i++) {
				if(rs.next()) {
					return_arr[i][0] = rs.getString("id");
					return_arr[i][1] = rs.getString("home_id");
					return_arr[i][2] = rs.getString("time");
					return_arr[i][3] = rs.getString("title");
					return_arr[i][4] = rs.getString("nickname");
				}
			}
				
			//�׳� ����غ�. Ȯ����... ���߿� �������..
			/*
			for(int i=0; i<2; i++) {
				System.out.println(return_arr[i][0]);
				System.out.println(return_arr[i][1]);
				System.out.println(return_arr[i][2]);
				System.out.println(return_arr[i][3]);
				System.out.println(return_arr[i][4]);
				
			}*/
			
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� �˻� ����/ Code: " + e.getMessage());
		}
		return return_arr;		//����� ���� �������� �������� flase ����
	}
	
	//�Խ��ǿ� �� �Է��ϴ� �Լ�
	public boolean insert_board_contents(String board_name, int home_id, String time, String title, String nickname) {
		try {
			String SQL = "INSERT INTO " + board_name + " (home_id, time, title, nickname) VALUES (" + home_id + ", " + time + ", " + title + ", " + nickname + ");";
			int record_num = st.executeUpdate(SQL);		//INSERT ������ executeUpdate�� ����Ѵ�. �̰��� ������Ʈ�� �Ǽ��� int�� �����Ѵ�.
			System.out.println("������Ʈ�� �� ��: " + record_num);
			
			//id�� �ʱ�ȭ��Ű�� 1���� �����ϵ��� ����� �ڵ�
			String SQL2 = "ALTER TABLE " + board_name + " AUTO_INCREMENT = 1;";
			st.executeUpdate(SQL2);		
			String SQL3 = "SET @count = 0;";
			st.executeUpdate(SQL3);
			String SQL4 = "UPDATE " + board_name + " SET id = @count := @count+1;";
			st.executeUpdate(SQL4);
			
			return true;
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� �Է� ����/ Code: " + e.getMessage());
		}
		return false;		//�����ͺ��̽� �Է� �̷������ �ʾ��� ��.
	}
	
	//�Խ��ǿ��� �� �����ϴ� �Լ�
	public boolean delete_board_contents(String board_name, String delete_id) {
		try {
			String SQL = "DELETE FROM " + board_name + " WHERE id=" + delete_id + ";";
			int record_num = st.executeUpdate(SQL);		//INSERT ������ executeUpdate�� ����Ѵ�. �̰��� ������Ʈ�� �Ǽ��� int�� �����Ѵ�.
			System.out.println("������Ʈ�� �� ��: " + record_num);
			
			//id�� �ʱ�ȭ��Ű�� 1���� �����ϵ��� ����� �ڵ�
			String SQL2 = "ALTER TABLE " + board_name + " AUTO_INCREMENT = 1;";
			st.executeUpdate(SQL2);		
			String SQL3 = "SET @count = 0;";
			st.executeUpdate(SQL3);
			String SQL4 = "UPDATE " + board_name + " SET id = @count := @count+1;";
			st.executeUpdate(SQL4);
			
			//System.out.println("������Ʈ�� �� ��: " + record_num4);
			return true;
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� �˻� ����/ Code: " + e.getMessage());
		}
		return false;		
	}

}
