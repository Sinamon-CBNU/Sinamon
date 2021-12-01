
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
		int id_num = 0;
		int home_id = 0;
		try {
			if(is_info_exist(id)) {		//id값과 존재하는 user_id가 있을 때	
				System.out.println("이미 존재하는 계정입니다.");
				return false;
			}
			else {		//아이디가 존재하지 않으면 user_info 입력
				if(home.equals("'정 문'")) {			//String인 home을 home_id값으로 변환
					home_id = 1;
				}else if(home.equals("'중 문'")) {
					home_id = 2;
				}else if(home.equals("'후 문'")) {
					home_id = 3;
				}else if(home.equals("'서 문'")) {
					home_id = 4;
				}else if(home.equals("'양 성 재'")) {
					home_id = 5;
				}else if(home.equals("'양 진 재'")) {
					home_id = 6;
				}else if(home.equals("'본 관'")) {
					home_id = 7;
				}
				
				String SQL = "INSERT INTO user_info(name, nickname, user_id, password, home_id) VALUES("+ name +"," + nickname + "," + id + "," + pwd + "," + home_id + ");";    
				int record_num = st.executeUpdate(SQL);		//INSERT 쿼리는 executeUpdate를 사용한다. 이것은 업데이트된 건수를 int로 리턴한다.
				System.out.println("업데이트된 건 수: " + record_num);
				
				String SQL_id = "SELECT id FROM user_info WHERE user_id = " + id + ";";
				rs = st.executeQuery(SQL_id);
				if(rs.next()) {
					id_num = rs.getInt(1);		//새로 입력한 계정의 id값
				}
				create_user_sinamon_board(id_num);		//새로 입력한 계정의 user_food_id, user_nes_id 테이블 생성 
			}
		} catch (Exception e) {
			System.out.println("데이터베이스 입력 오류/ Code: " + e.getMessage());
		}
		return true;
	}
	
	//개인정보 수정하는 함수
	public boolean modify_user_info(int btn_num, String name, String user_id, String pwd, String nickname) {
		try {		
			String SQL = "";
			if(is_info_exist(user_id)) {		//존재하는 user_id가 있을 때	
				switch (btn_num) {
				case 2: {		//이름
					SQL = "UPDATE user_info SET name = " + nickname + " WHERE user_id = " + user_id + ";";
					int record_num = st.executeUpdate(SQL);		//INSERT 쿼리는 executeUpdate를 사용한다. 이것은 업데이트된 건수를 int로 리턴한다.
					System.out.println("업데이트된 건 수: " + record_num);
					break;
				}
				case 3: {		//닉네임
					SQL = "UPDATE user_info SET nickname = " + nickname + " WHERE user_id = " + user_id + ";";
					int record_num = st.executeUpdate(SQL);		//INSERT 쿼리는 executeUpdate를 사용한다. 이것은 업데이트된 건수를 int로 리턴한다.
					System.out.println("업데이트된 건 수: " + record_num);
					break;
				}
				case 4: {		//아이디
					SQL = "UPDATE user_info SET user_id = " + nickname + " WHERE user_id = " + user_id + ";";
					int record_num = st.executeUpdate(SQL);		//INSERT 쿼리는 executeUpdate를 사용한다. 이것은 업데이트된 건수를 int로 리턴한다.
					System.out.println("업데이트된 건 수: " + record_num);
					break;
				}
				case 5: {		//비번
					SQL = "UPDATE user_info SET password = " + nickname + " WHERE user_id = " + user_id + ";";
					int record_num = st.executeUpdate(SQL);		//INSERT 쿼리는 executeUpdate를 사용한다. 이것은 업데이트된 건수를 int로 리턴한다.
					System.out.println("업데이트된 건 수: " + record_num);
					break;
				}
				case 6: {		//집
					SQL = "UPDATE user_info SET home = " + nickname + " WHERE user_id = " + user_id + ";";
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
			String SQL = "SELECT * FROM user_info WHERE user_id = " + user_id + ";";
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
	
	//계정당 상대방 글에 참여한 음식, 생필품 게시판 정보 담을 테이블 만들 함수
	public void create_user_sinamon_board(int id) {			
		try {
			String SQL_food = "CREATE TABLE user_food_" + id + "(id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, table_id INT(11) NOT NULL);";
			int record_num1 = st.executeUpdate(SQL_food);		//user_food_id 테이블 생성
			String SQL_nes = "CREATE TABLE user_nes_" + id + "(id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, table_id INT(11) NOT NULL);";
			int record_num2 = st.executeUpdate(SQL_nes);		//user_food_id 테이블 생성
			
		} catch (Exception e) {
			System.out.println("데이터베이스 테이블 생성 오류/ Code: " + e.getMessage());
		}
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
	
	public Object[][] return_my_board(String nickname){
		Object[][] return_arr = new Object[100][2];
		int nickname_id = convert_nickname_to_nickname_id(nickname);
		int food_last_row_id = 0, nes_last_row_id = 0, cnt = 0; 
		try {
			//음식 게시판에서 데이터 가져오기
			String food_SQL1 = "SELECT id FROM food_board ORDER BY id DESC LIMIT 1;";		//마지막 행 id값
			rs = st.executeQuery(food_SQL1);
			if(rs.next()) {
				food_last_row_id = rs.getInt(1);		//첫번째 열인 id값을 가져옴
			}
			String food_SQL2 = "SELECT title, is_complete FROM food_board WHERE nickname_id = " + nickname_id + ";";		//원하는 게시판에서 id, 장소, 시간, 제목, 닉네임 가져오기
			rs = st.executeQuery(food_SQL2);
			int i=0;
			while(rs.next()) {
				cnt++;
				return_arr[i][0] = rs.getString("title");
				return_arr[i][1] = rs.getString("is_complete");
				i++;
			}
			
			//생필품 게시판에서 데이터 가져오기
			String nes_SQL1 = "SELECT id FROM nes_board ORDER BY id DESC LIMIT 1;";		//마지막 행 id값
			rs = st.executeQuery(nes_SQL1);
			if(rs.next()) {
				nes_last_row_id = rs.getInt(1);		//첫번째 열인 id값을 가져옴
			}
			String nes_SQL2 = "SELECT title, is_complete FROM nes_board WHERE nickname_id = " + nickname_id + ";";		//원하는 게시판에서 id, 장소, 시간, 제목, 닉네임 가져오기
			rs = st.executeQuery(nes_SQL2);
			int j = cnt;	//음식 게시판 정보 저장한 배열 뒤에 생필품 게시판 정보 이어붙임
			while(rs.next()) {
				return_arr[j][0] = rs.getString("title");
				return_arr[j][1] = rs.getString("is_complete");
				j++;
			}
		} catch (Exception e) {
			System.out.println("데이터베이스 검색 오류2/ Code: " + e.getMessage());
		}
		
		return return_arr;		
	}
	
	//내가 다른사람 글에 참여한 목록
	public Object[][] return_get_in_board(String nickname){
		Object[][] return_arr = new Object[100][2];
		int id = convert_nickname_to_nickname_id(nickname);
		int food_last_row_id = 0, nes_last_row_id = 0, cnt = 0; 
		int[] food_board_id = new int[50];
		int[] nes_board_id = new int[50];
		try {
			//음식 게시판에서 데이터 가져오기
			String food_SQL1 = "SELECT id FROM user_food_" + id + " ORDER BY id DESC LIMIT 1;";		//마지막 행 id값
			rs = st.executeQuery(food_SQL1);
			if(rs.next()) {
				food_last_row_id = rs.getInt(1);		//첫번째 열인 id값을 가져옴
			}
			String food_SQL2 = "SELECT table_id FROM user_food_" + id + ";";		//user_food_id 게시판에서 음식 게시판 id값들 가져오기
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
			
			//생필품 게시판에서 데이터 가져오기
			String nes_SQL1 = "SELECT id FROM user_nes_" + id + " ORDER BY id DESC LIMIT 1;";		//마지막 행 id값
			rs = st.executeQuery(nes_SQL1);
			if(rs.next()) {
				nes_last_row_id = rs.getInt(1);		//첫번째 열인 id값을 가져옴
			}
			String nes_SQL2 = "SELECT table_id FROM user_nes_" + id + ";";		//user_nes_id 게시판에서 생필품 게시판 id값들 가져오기
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
			System.out.println("데이터베이스 검색 오류2/ Code: " + e.getMessage());
		}
		
		return return_arr;		
		
	}
	
	//진행중 눌렀을 때 현재 약속 확정 패널에 시간, 장소, 메모 리턴하는 함수
	public Object[] return_reserve_board(String board_name, int id) {
		Object[] return_arr = new Object[3];		//시간, 장소, 메모 담고 있는 배열
		try {
			if(board_name.equals("food_board")) {
				String SQL = "SELECT time, location, memo from food_reserve_board WHERE board_id = " + id + ";";	//해당 게시물 id값에 맞는 정보 출력
				rs = st.executeQuery(SQL);
				if(rs.next()) {
					return_arr[0] = rs.getString("time");
					return_arr[1] = rs.getString("location");
					return_arr[2] = rs.getString("memo");
				}
			}
			else if(board_name.equals("nes_board")) {
				String SQL = "SELECT time, location, memo from res_reserve_board WHERE board_id = " + id + ";";		//해당 게시물 id값에 맞는 정보 출력
				rs = st.executeQuery(SQL);
				if(rs.next()) {
					return_arr[0] = rs.getString("time");
					return_arr[1] = rs.getString("location");
					return_arr[2] = rs.getString("memo");
				}
			}
			
		} catch (Exception e) {
			System.out.println("데이터베이스 검색 오류3/ Code: " + e.getMessage());
		}
		return return_arr;
	}
	
	//게시판에 글 입력하는 함수
	public boolean insert_board_contents(String board_name, int home_id, String time, String title, String nickname) {
		try {
			int nickname_id = convert_nickname_to_nickname_id(nickname);
			String SQL = "INSERT INTO " + board_name + " (home_id, time, title, nickname_id, is_complete) VALUES (" + home_id + ", " + time + ", " + title + ", " + nickname_id + ", '예정');";
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
