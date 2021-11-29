
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
			System.out.println("데이터베이스 연결 오류: " + e.getMessage());
		}
	}
	
	//회원가입에서 정보 넘겨받아 데이터베이스(sinamon의 user_info 테이블)에 저장하는 함수
	public boolean input_user_info(String name, String nickname, String id, String pwd, String home) {		
		try {
			if(is_info_exist(id)) {		//id값과 존재하는 user_id가 있을 때	
				System.out.println("이미 존재하는 계정입니다.");
				return false;
			}
			else {		//아이디가 존재하지 않으면 user_info 입력
				String SQL = "INSERT INTO user_info(name, nickname, user_id, password, home) VALUES("+ name +"," + nickname + "," + id + "," + pwd + "," + home + ");";    
				int record_num = st.executeUpdate(SQL);		//INSERT 쿼리는 executeUpdate를 사용한다. 이것은 업데이트된 건수를 int로 리턴한다.
				System.out.println("업데이트된 건 수: " + record_num);
			}
		} catch (Exception e) {
			System.out.println("데이터베이스 입력 오류/ Code: " + e.getMessage());
		}
		return true;
	}
	
	//개인정보 수정하는 함수
	public boolean modify_user_info(int btn_num, String user_id, String data) {
		try {		
			String SQL = "";
			if(is_info_exist(user_id)) {		//존재하는 user_id가 있을 때	
				switch (btn_num) {
				case 2: {		//이름
					SQL = "UPDATE user_info SET name = " + data + " WHERE user_id = " + user_id + ";";
					int record_num = st.executeUpdate(SQL);		//INSERT 쿼리는 executeUpdate를 사용한다. 이것은 업데이트된 건수를 int로 리턴한다.
					System.out.println("업데이트된 건 수: " + record_num);
					break;
				}
				case 3: {		//닉네임
					SQL = "UPDATE user_info SET nickname = " + data + " WHERE user_id = " + user_id + ";";
					int record_num = st.executeUpdate(SQL);		//INSERT 쿼리는 executeUpdate를 사용한다. 이것은 업데이트된 건수를 int로 리턴한다.
					System.out.println("업데이트된 건 수: " + record_num);
					break;
				}
				case 4: {		//아이디
					SQL = "UPDATE user_info SET user_id = " + data + " WHERE user_id = " + user_id + ";";
					int record_num = st.executeUpdate(SQL);		//INSERT 쿼리는 executeUpdate를 사용한다. 이것은 업데이트된 건수를 int로 리턴한다.
					System.out.println("업데이트된 건 수: " + record_num);
					break;
				}
				case 5: {		//비번
					SQL = "UPDATE user_info SET password = " + data + " WHERE user_id = " + user_id + ";";
					int record_num = st.executeUpdate(SQL);		//INSERT 쿼리는 executeUpdate를 사용한다. 이것은 업데이트된 건수를 int로 리턴한다.
					System.out.println("업데이트된 건 수: " + record_num);
					break;
				}
				case 6: {		//집
					SQL = "UPDATE user_info SET home = " + data + " WHERE user_id = " + user_id + ";";
					int record_num = st.executeUpdate(SQL);		//INSERT 쿼리는 executeUpdate를 사용한다. 이것은 업데이트된 건수를 int로 리턴한다.
					System.out.println("업데이트된 건 수: " + record_num);
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected btn_num : " + btn_num);
				}
			}
			return true;
		} catch (Exception e) {
			System.out.println("데이터베이스 입력 오류/ Code: " + e.getMessage());
		}
		return false;
	}
	
	//아이디를 문자열로 받아 user_info 테이블에 존재하는지 확인하는 함수
	public boolean is_info_exist(String user_id) {		
		try {
			String SQL = "SELECT * FROM user_info WHERE user_id = " + user_id;
			rs = st.executeQuery(SQL);		//SELECT 구문에 사용
			if(rs.next()) {
				//System.out.println(rs.getString(4));
				//System.out.println(rs.getString(5));
				return true;
			}	
		} catch (Exception e) {
			System.out.println("데이터베이스 검색 오류/ Code: " + e.getMessage());
		}
		return false;	//검색되지 않았다면 false 반환
	}
	
	//아이디와 비번 문자열로 받아 user_info 테이블에 아이디와 비번 일치하는 행 있으면 true 리턴
	public boolean login(String user_id, String pwd) {	
		try {
			String SQL = "SELECT * FROM user_info WHERE user_id = " + user_id + "and password = " + pwd;
			rs = st.executeQuery(SQL);		//SELECT 구문에 사용
			if(rs.next()) {
				//System.out.println(rs.getString(4));
				//System.out.println(rs.getString(5));
				return true;
			}	
		} catch (Exception e) {
			System.out.println("데이터베이스 검색 오류/ Code: " + e.getMessage());
		}
		return false;	//검색되지 않았다면 false 반환
	}
	
	public Object[] return_user_info(String user_id) {
		Object[] user_info = new Object[6];
		try {
			String SQL = "SELECT * FROM user_info WHERE user_id = " + user_id;
			rs = st.executeQuery(SQL);		//SELECT 구문에 사용
			if(rs.next()) {
				user_info[0] = rs.getString("id");			
				user_info[1] = rs.getString("name");
				user_info[2] = rs.getString("nickname");
				user_info[3] = rs.getString("user_id");
				user_info[4] = rs.getString("password");
				user_info[5] = rs.getString("home_id");
			}
		} catch (Exception e) {
			System.out.println("데이터베이스 검색 오류/ Code: " + e.getMessage());
		}
		return user_info;
	}
	
	//게시판에서 장소, 시간, 제목, 닉네임 리턴하기
	public Object[][] show_board(String board_name) {
		Object[][] return_arr = new Object[100][6];
		int last_row_id = 0;
		try {
			String SQL1 = "SELECT id FROM " + board_name + " ORDER BY id DESC LIMIT 1;";		//마지막 행 id값
			rs = st.executeQuery(SQL1);
			if(rs.next()) {
				last_row_id = rs.getInt(1);		//첫번째 열인 id값을 가져옴
			}
			System.out.println("총 수: " + last_row_id);
			// SELECT food_board.id, home_board.home_id, time, title, user_info.nickname, is_complete
			// FROM food_board JOIN home_board ON food_board.home_id = home_board.id
			// JOIN user_info ON food_board.nickname_id = user_info.id;
			String SQL = "SELECT " + board_name + ".id, home_board.home_id, time, title, user_info.nickname, is_complete FROM " + board_name + " JOIN home_board ON " + board_name + ".home_id = home_board.id JOIN user_info ON " + board_name + ".nickname_id = user_info.id WHERE " + board_name + ".id;";		//원하는 게시판에서 id, 장소, 시간, 제목, 닉네임 가져오기
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
			System.out.println("데이터베이스 검색 오류1/ Code: " + e.getMessage());
		}
		
		return return_arr;		
	}
	
	//게시판에서 단어 입력하면 그에 맞는 게시글 찾는 함수
	public Object[][] search_post(String board_name, String search_word){		
		Object[][] return_arr = new Object[100][6];
		int last_row_id = 0; 
		try {
			String SQL1 = "SELECT id FROM " + board_name + " ORDER BY id DESC LIMIT 1;";		//마지막 행 id값
			rs = st.executeQuery(SQL1);
			if(rs.next()) {
				last_row_id = rs.getInt(1);		//첫번째 열인 id값을 가져옴
			}
			// SELECT food_board.id, home_board.home_id, time, title, user_info.nickname, is_complete
			// FROM food_board JOIN home_board ON food_board.home_id = home_board.id
			// JOIN user_info ON food_board.nickname_id = user_info.id;
			String SQL2 = "SELECT " + board_name + ".id, home_board.home_id, time, title, user_info.nickname, is_complete FROM " + board_name + " JOIN home_board ON " + board_name + ".home_id = home_board.id JOIN user_info ON " + board_name + ".nickname_id = user_info.id WHERE " + board_name + ".id BETWEEN 1 and 10";		//원하는 게시판에서 id, 장소, 시간, 제목, 닉네임 가져오기
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
			System.out.println("데이터베이스 검색 오류1/ Code: " + e.getMessage());
		}
		return return_arr;
	}
	
	//게시판에 닉네임이 직접 들어가는데 아니라, id값이 들어가기 때문에 닉네임을 스트링으로 받아서 int로 변환해 반환해주는 함수
	public int convert_nickname_to_nickname_id(String nickname) {
		int nickname_id = 0;
		try {
			String SQL1 = "SELECT id FROM user_info WHERE nickname = " + nickname + ";";
			rs = st.executeQuery(SQL1);
			if(rs.next()) {
				nickname_id = Integer.parseInt(rs.getString("id"));
			}
		} catch (Exception e) {
			System.out.println("데이터베이스 검색 오류/ Code: " + e.getMessage());
		}
		return nickname_id;
	}
	
	public Object[][] return_my_board(String board_name, String nickname){
		Object[][] return_arr = new Object[100][2];
		int nickname_id = convert_nickname_to_nickname_id(nickname);
		int last_row_id = 0; 
		try {
			String SQL1 = "SELECT id FROM " + board_name + " ORDER BY id DESC LIMIT 1;";		//마지막 행 id값
			rs = st.executeQuery(SQL1);
			if(rs.next()) {
				last_row_id = rs.getInt(1);		//첫번째 열인 id값을 가져옴
			}
			String SQL = "SELECT title, is_complete FROM " + board_name + " WHERE nickname_id = " + nickname_id + ";";		//원하는 게시판에서 id, 장소, 시간, 제목, 닉네임 가져오기
			rs = st.executeQuery(SQL);
			for(int i=0; i<last_row_id; i++) {
				if(rs.next()) {
					return_arr[i][0] = rs.getString("title");
					return_arr[i][1] = rs.getString("is_complete");
				}
			}
			
		} catch (Exception e) {
			System.out.println("데이터베이스 검색 오류2/ Code: " + e.getMessage());
		}
		return return_arr;		
	}
	
	//게시판에 글 입력하는 함수
	public boolean insert_board_contents(String board_name, int home_id, String time, String title, String nickname) {
		try {
			int nickname_id = convert_nickname_to_nickname_id(nickname);
			String SQL = "INSERT INTO " + board_name + " (home_id, time, title, nickname_id, is_complete) VALUES (" + home_id + ", " + time + ", " + title + ", " + nickname_id + ", '진행중');";
			int record_num = st.executeUpdate(SQL);		//INSERT 쿼리는 executeUpdate를 사용한다. 이것은 업데이트된 건수를 int로 리턴한다.
			System.out.println("업데이트된 건 수: " + record_num);
			
			//id값 초기화시키고 1부터 증가하도록 만드는 코드
			String SQL1 = "ALTER TABLE " + board_name + " AUTO_INCREMENT = 1;";
			st.executeUpdate(SQL1);		
			String SQL2 = "SET @count = 0;";
			st.executeUpdate(SQL2);
			String SQL3 = "UPDATE " + board_name + " SET id = @count := @count+1;";
			st.executeUpdate(SQL3);
			
			return true;
		} catch (Exception e) {
			System.out.println("데이터베이스 입력 오류/ Code: " + e.getMessage());
		}
		return false;		//데이터베이스 입력 이루어지지 않았을 때.
	}
	
	//게시판에서 글 삭제하는 함수
	public boolean delete_board_contents(String board_name, String delete_id) {
		try {
			String SQL = "DELETE FROM " + board_name + " WHERE id=" + delete_id + ";";
			int record_num = st.executeUpdate(SQL);		//INSERT 쿼리는 executeUpdate를 사용한다. 이것은 업데이트된 건수를 int로 리턴한다.
			System.out.println("업데이트된 건 수: " + record_num);
			
			//id값 초기화시키고 1부터 증가하도록 만드는 코드
			String SQL1 = "ALTER TABLE " + board_name + " AUTO_INCREMENT = 1;";
			st.executeUpdate(SQL1);		
			String SQL2 = "SET @count = 0;";
			st.executeUpdate(SQL2);
			String SQL3 = "UPDATE " + board_name + " SET id = @count := @count+1;";
			st.executeUpdate(SQL3);
			
			//System.out.println("업데이트된 건 수: " + record_num4);
			return true;
		} catch (Exception e) {
			System.out.println("데이터베이스 검색 오류/ Code: " + e.getMessage());
		}
		return false;		
	}
	


}
