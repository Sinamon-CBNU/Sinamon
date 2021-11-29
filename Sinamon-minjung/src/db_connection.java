
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
		Object[][] return_arr = new Object[100][6];
		int last_row_id = 0;
		try {
			String SQL1 = "SELECT id FROM " + board_name + " ORDER BY id DESC LIMIT 1;";		//������ �� id��
			rs = st.executeQuery(SQL1);
			if(rs.next()) {
				last_row_id = rs.getInt(1);		//ù��° ���� id���� ������
			}
			System.out.println("�� ��: " + last_row_id);
			// SELECT food_board.id, home_board.home_id, time, title, user_info.nickname, is_complete
			// FROM food_board JOIN home_board ON food_board.home_id = home_board.id
			// JOIN user_info ON food_board.nickname_id = user_info.id;
			String SQL = "SELECT " + board_name + ".id, home_board.home_id, time, title, user_info.nickname, is_complete FROM " + board_name + " JOIN home_board ON " + board_name + ".home_id = home_board.id JOIN user_info ON " + board_name + ".nickname_id = user_info.id WHERE " + board_name + ".id;";		//���ϴ� �Խ��ǿ��� id, ���, �ð�, ����, �г��� ��������
			rs = st.executeQuery(SQL);
	
			for(int i=0; i<last_row_id; i++) {
				if(rs.next()) {
					return_arr[i][0] = rs.getString("id");
					return_arr[i][1] = rs.getString("home_id");
					return_arr[i][2] = rs.getString("time");
					return_arr[i][3] = rs.getString("title");
					return_arr[i][4] = rs.getString("nickname");
					return_arr[i][5]=  rs.getString("is_complete");
				}
			}
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� �˻� ����1/ Code: " + e.getMessage());
		}
		
		return return_arr;		
	}
	
	//�Խ��ǿ��� �ܾ� �Է��ϸ� �׿� �´� �Խñ� ã�� �Լ�
	public Object[][] search_post(String board_name, String search_word){		
		Object[][] return_arr = new Object[100][6];
		int last_row_id = 0; 
		try {
			String SQL1 = "SELECT id FROM " + board_name + " ORDER BY id DESC LIMIT 1;";		//������ �� id��
			rs = st.executeQuery(SQL1);
			if(rs.next()) {
				last_row_id = rs.getInt(1);		//ù��° ���� id���� ������
			}
			// SELECT food_board.id, home_board.home_id, time, title, user_info.nickname, is_complete
			// FROM food_board JOIN home_board ON food_board.home_id = home_board.id
			// JOIN user_info ON food_board.nickname_id = user_info.id;
			String SQL2 = "SELECT " + board_name + ".id, home_board.home_id, time, title, user_info.nickname, is_complete FROM " + board_name + " JOIN home_board ON " + board_name + ".home_id = home_board.id JOIN user_info ON " + board_name + ".nickname_id = user_info.id WHERE " + board_name + ".id BETWEEN 1 and 10";		//���ϴ� �Խ��ǿ��� id, ���, �ð�, ����, �г��� ��������
			rs = st.executeQuery(SQL2);
			for(int i=0; i<last_row_id; i++) {
				if(rs.next()) {
					String content = rs.getString("title");
					if(content.contains(search_word)) {
						return_arr[i][0] = rs.getString("id");
						return_arr[i][1] = rs.getString("home_id");
						return_arr[i][2] = rs.getString("time");
						return_arr[i][3] = rs.getString("title");
						return_arr[i][4] = rs.getString("nickname");
						return_arr[i][5]=  rs.getString("is_complete");
					}
				}
			}
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� �˻� ����1/ Code: " + e.getMessage());
		}
		return return_arr;
	}
	
	//�Խ��ǿ� �г����� ���� ���µ� �ƴ϶�, id���� ���� ������ �г����� ��Ʈ������ �޾Ƽ� int�� ��ȯ�� ��ȯ���ִ� �Լ�
	public int convert_nickname_to_nickname_id(String nickname) {
		int nickname_id = 0;
		try {
			String SQL1 = "SELECT id FROM user_info WHERE nickname = " + nickname + ";";
			rs = st.executeQuery(SQL1);
			if(rs.next()) {
				nickname_id = Integer.parseInt(rs.getString("id"));
			}
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� �˻� ����/ Code: " + e.getMessage());
		}
		return nickname_id;
	}
	
	public Object[][] return_my_board(String board_name, String nickname){
		Object[][] return_arr = new Object[100][2];
		int nickname_id = convert_nickname_to_nickname_id(nickname);
		int last_row_id = 0; 
		try {
			String SQL1 = "SELECT id FROM " + board_name + " ORDER BY id DESC LIMIT 1;";		//������ �� id��
			rs = st.executeQuery(SQL1);
			if(rs.next()) {
				last_row_id = rs.getInt(1);		//ù��° ���� id���� ������
			}
			String SQL = "SELECT title, is_complete FROM " + board_name + " WHERE nickname_id = " + nickname_id + ";";		//���ϴ� �Խ��ǿ��� id, ���, �ð�, ����, �г��� ��������
			rs = st.executeQuery(SQL);
			for(int i=0; i<last_row_id; i++) {
				if(rs.next()) {
					return_arr[i][0] = rs.getString("title");
					return_arr[i][1] = rs.getString("is_complete");
				}
			}
			
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� �˻� ����2/ Code: " + e.getMessage());
		}
		return return_arr;		
	}
	
	//�Խ��ǿ� �� �Է��ϴ� �Լ�
	public boolean insert_board_contents(String board_name, int home_id, String time, String title, String nickname) {
		try {
			int nickname_id = convert_nickname_to_nickname_id(nickname);
			String SQL = "INSERT INTO " + board_name + " (home_id, time, title, nickname_id, is_complete) VALUES (" + home_id + ", " + time + ", " + title + ", " + nickname_id + ", '������');";
			int record_num = st.executeUpdate(SQL);		//INSERT ������ executeUpdate�� ����Ѵ�. �̰��� ������Ʈ�� �Ǽ��� int�� �����Ѵ�.
			System.out.println("������Ʈ�� �� ��: " + record_num);
			
			//id�� �ʱ�ȭ��Ű�� 1���� �����ϵ��� ����� �ڵ�
			String SQL1 = "ALTER TABLE " + board_name + " AUTO_INCREMENT = 1;";
			st.executeUpdate(SQL1);		
			String SQL2 = "SET @count = 0;";
			st.executeUpdate(SQL2);
			String SQL3 = "UPDATE " + board_name + " SET id = @count := @count+1;";
			st.executeUpdate(SQL3);
			
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
			String SQL1 = "ALTER TABLE " + board_name + " AUTO_INCREMENT = 1;";
			st.executeUpdate(SQL1);		
			String SQL2 = "SET @count = 0;";
			st.executeUpdate(SQL2);
			String SQL3 = "UPDATE " + board_name + " SET id = @count := @count+1;";
			st.executeUpdate(SQL3);
			
			//System.out.println("������Ʈ�� �� ��: " + record_num4);
			return true;
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� �˻� ����/ Code: " + e.getMessage());
		}
		return false;		
	}
	


}
