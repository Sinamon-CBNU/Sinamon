
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
		int id_num = 0;
		int home_id = 0;
		try {
			if(is_info_exist(id)) {		//id���� �����ϴ� user_id�� ���� ��	
				System.out.println("�̹� �����ϴ� �����Դϴ�.");
				return false;
			}
			else {		//���̵� �������� ������ user_info �Է�
				if(home.equals("'�� ��'")) {			//String�� home�� home_id������ ��ȯ
					home_id = 1;
				}else if(home.equals("'�� ��'")) {
					home_id = 2;
				}else if(home.equals("'�� ��'")) {
					home_id = 3;
				}else if(home.equals("'�� ��'")) {
					home_id = 4;
				}else if(home.equals("'�� �� ��'")) {
					home_id = 5;
				}else if(home.equals("'�� �� ��'")) {
					home_id = 6;
				}else if(home.equals("'�� ��'")) {
					home_id = 7;
				}
				
				String SQL = "INSERT INTO user_info(name, nickname, user_id, password, home_id) VALUES("+ name +"," + nickname + "," + id + "," + pwd + "," + home_id + ");";    
				int record_num = st.executeUpdate(SQL);		//INSERT ������ executeUpdate�� ����Ѵ�. �̰��� ������Ʈ�� �Ǽ��� int�� �����Ѵ�.
				System.out.println("������Ʈ�� �� ��: " + record_num);
				
				String SQL_id = "SELECT id FROM user_info WHERE user_id = " + id + ";";
				rs = st.executeQuery(SQL_id);
				if(rs.next()) {
					id_num = rs.getInt(1);		//���� �Է��� ������ id��
				}
				create_user_sinamon_board(id_num);		//���� �Է��� ������ user_food_id, user_nes_id ���̺� ���� 
			}
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� �Է� ����/ Code: " + e.getMessage());
		}
		return true;
	}
	
	//�������� �����ϴ� �Լ�
	public boolean modify_user_info(int btn_num, String name, String user_id, String pwd, String nickname) {
		try {		
			String SQL = "";
			if(is_info_exist(user_id)) {		//�����ϴ� user_id�� ���� ��	
				switch (btn_num) {
				case 2: {		//�̸�
					SQL = "UPDATE user_info SET name = " + nickname + " WHERE user_id = " + user_id + ";";
					int record_num = st.executeUpdate(SQL);		//INSERT ������ executeUpdate�� ����Ѵ�. �̰��� ������Ʈ�� �Ǽ��� int�� �����Ѵ�.
					System.out.println("������Ʈ�� �� ��: " + record_num);
					break;
				}
				case 3: {		//�г���
					SQL = "UPDATE user_info SET nickname = " + nickname + " WHERE user_id = " + user_id + ";";
					int record_num = st.executeUpdate(SQL);		//INSERT ������ executeUpdate�� ����Ѵ�. �̰��� ������Ʈ�� �Ǽ��� int�� �����Ѵ�.
					System.out.println("������Ʈ�� �� ��: " + record_num);
					break;
				}
				case 4: {		//���̵�
					SQL = "UPDATE user_info SET user_id = " + nickname + " WHERE user_id = " + user_id + ";";
					int record_num = st.executeUpdate(SQL);		//INSERT ������ executeUpdate�� ����Ѵ�. �̰��� ������Ʈ�� �Ǽ��� int�� �����Ѵ�.
					System.out.println("������Ʈ�� �� ��: " + record_num);
					break;
				}
				case 5: {		//���
					SQL = "UPDATE user_info SET password = " + nickname + " WHERE user_id = " + user_id + ";";
					int record_num = st.executeUpdate(SQL);		//INSERT ������ executeUpdate�� ����Ѵ�. �̰��� ������Ʈ�� �Ǽ��� int�� �����Ѵ�.
					System.out.println("������Ʈ�� �� ��: " + record_num);
					break;
				}
				case 6: {		//��
					SQL = "UPDATE user_info SET home = " + nickname + " WHERE user_id = " + user_id + ";";
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
			String SQL = "SELECT * FROM user_info WHERE user_id = " + user_id + ";";
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
	
	//������ ���� �ۿ� ������ ����, ����ǰ �Խ��� ���� ���� ���̺� ���� �Լ�
	public void create_user_sinamon_board(int id) {			
		try {
			String SQL_food = "CREATE TABLE user_food_" + id + "(id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, table_id INT(11) NOT NULL);";
			int record_num1 = st.executeUpdate(SQL_food);		//user_food_id ���̺� ����
			String SQL_nes = "CREATE TABLE user_nes_" + id + "(id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, table_id INT(11) NOT NULL);";
			int record_num2 = st.executeUpdate(SQL_nes);		//user_food_id ���̺� ����
			
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� ���̺� ���� ����/ Code: " + e.getMessage());
		}
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
	
	public Object[][] return_my_board(String nickname){
		Object[][] return_arr = new Object[100][2];
		int nickname_id = convert_nickname_to_nickname_id(nickname);
		int food_last_row_id = 0, nes_last_row_id = 0, cnt = 0; 
		try {
			//���� �Խ��ǿ��� ������ ��������
			String food_SQL1 = "SELECT id FROM food_board ORDER BY id DESC LIMIT 1;";		//������ �� id��
			rs = st.executeQuery(food_SQL1);
			if(rs.next()) {
				food_last_row_id = rs.getInt(1);		//ù��° ���� id���� ������
			}
			String food_SQL2 = "SELECT title, is_complete FROM food_board WHERE nickname_id = " + nickname_id + ";";		//���ϴ� �Խ��ǿ��� id, ���, �ð�, ����, �г��� ��������
			rs = st.executeQuery(food_SQL2);
			int i=0;
			while(rs.next()) {
				cnt++;
				return_arr[i][0] = rs.getString("title");
				return_arr[i][1] = rs.getString("is_complete");
				i++;
			}
			
			//����ǰ �Խ��ǿ��� ������ ��������
			String nes_SQL1 = "SELECT id FROM nes_board ORDER BY id DESC LIMIT 1;";		//������ �� id��
			rs = st.executeQuery(nes_SQL1);
			if(rs.next()) {
				nes_last_row_id = rs.getInt(1);		//ù��° ���� id���� ������
			}
			String nes_SQL2 = "SELECT title, is_complete FROM nes_board WHERE nickname_id = " + nickname_id + ";";		//���ϴ� �Խ��ǿ��� id, ���, �ð�, ����, �г��� ��������
			rs = st.executeQuery(nes_SQL2);
			int j = cnt;	//���� �Խ��� ���� ������ �迭 �ڿ� ����ǰ �Խ��� ���� �̾����
			while(rs.next()) {
				return_arr[j][0] = rs.getString("title");
				return_arr[j][1] = rs.getString("is_complete");
				j++;
			}
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� �˻� ����2/ Code: " + e.getMessage());
		}
		
		return return_arr;		
	}
	
	//���� �ٸ���� �ۿ� ������ ���
	public Object[][] return_get_in_board(String nickname){
		Object[][] return_arr = new Object[100][2];
		int id = convert_nickname_to_nickname_id(nickname);
		int food_last_row_id = 0, nes_last_row_id = 0, cnt = 0; 
		int[] food_board_id = new int[50];
		int[] nes_board_id = new int[50];
		try {
			//���� �Խ��ǿ��� ������ ��������
			String food_SQL1 = "SELECT id FROM user_food_" + id + " ORDER BY id DESC LIMIT 1;";		//������ �� id��
			rs = st.executeQuery(food_SQL1);
			if(rs.next()) {
				food_last_row_id = rs.getInt(1);		//ù��° ���� id���� ������
			}
			String food_SQL2 = "SELECT table_id FROM user_food_" + id + ";";		//user_food_id �Խ��ǿ��� ���� �Խ��� id���� ��������
			rs = st.executeQuery(food_SQL2);
			int i=0;
			while(rs.next()) {
				cnt++;
				food_board_id[i] = rs.getInt("table_id");
				i++;
			}
			
			for(int t=0; t<food_last_row_id; t++) {
				String food_SQL3 = "SELECT title, is_complete FROM food_board WHERE id = " + food_board_id[t] + ";";
				rs = st.executeQuery(food_SQL3);
				int food_idx = 0;
				while(rs.next()) {
					return_arr[food_idx][0] = rs.getString("title");
					return_arr[food_idx][1] = rs.getString("is_complete");
					food_idx++;
				}
			}
			
			//����ǰ �Խ��ǿ��� ������ ��������
			String nes_SQL1 = "SELECT id FROM user_nes_" + id + " ORDER BY id DESC LIMIT 1;";		//������ �� id��
			rs = st.executeQuery(nes_SQL1);
			if(rs.next()) {
				nes_last_row_id = rs.getInt(1);		//ù��° ���� id���� ������
			}
			String nes_SQL2 = "SELECT table_id FROM user_nes_" + id + ";";		//user_nes_id �Խ��ǿ��� ����ǰ �Խ��� id���� ��������
			rs = st.executeQuery(nes_SQL2);
			int j=0;
			while(rs.next()) {
				nes_board_id[j] = rs.getInt("table_id");
				j++;
			}
			
			for(int t=0; t<nes_last_row_id; t++) {
				String food_SQL3 = "SELECT title, is_complete FROM food_board WHERE id = " + nes_board_id[t] + ";";
				rs = st.executeQuery(food_SQL3);
				int nes_idx = cnt;
				while(rs.next()) {
					return_arr[nes_idx][0] = rs.getString("title");
					return_arr[nes_idx][1] = rs.getString("is_complete");
					nes_idx++;
				}
			}
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� �˻� ����2/ Code: " + e.getMessage());
		}
		
		return return_arr;		
		
	}
	
	//������ ������ �� ���� ��� Ȯ�� �гο� �ð�, ���, �޸� �����ϴ� �Լ�
	public Object[] return_reserve_board(String board_name, int id) {
		Object[] return_arr = new Object[3];		//�ð�, ���, �޸� ��� �ִ� �迭
		try {
			if(board_name.equals("food_board")) {
				String SQL = "SELECT time, location, memo from food_reserve_board WHERE board_id = " + id + ";";	//�ش� �Խù� id���� �´� ���� ���
				rs = st.executeQuery(SQL);
				if(rs.next()) {
					return_arr[0] = rs.getString("time");
					return_arr[1] = rs.getString("location");
					return_arr[2] = rs.getString("memo");
				}
			}
			else if(board_name.equals("nes_board")) {
				String SQL = "SELECT time, location, memo from res_reserve_board WHERE board_id = " + id + ";";		//�ش� �Խù� id���� �´� ���� ���
				rs = st.executeQuery(SQL);
				if(rs.next()) {
					return_arr[0] = rs.getString("time");
					return_arr[1] = rs.getString("location");
					return_arr[2] = rs.getString("memo");
				}
			}
			
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� �˻� ����3/ Code: " + e.getMessage());
		}
		return return_arr;
	}
	
	//�Խ��ǿ� �� �Է��ϴ� �Լ�
	public boolean insert_board_contents(String board_name, int home_id, String time, String title, String nickname) {
		try {
			int nickname_id = convert_nickname_to_nickname_id(nickname);
			String SQL = "INSERT INTO " + board_name + " (home_id, time, title, nickname_id, is_complete) VALUES (" + home_id + ", " + time + ", " + title + ", " + nickname_id + ", '����');";
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
