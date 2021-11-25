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
		} 
		catch (Exception e) {
			System.out.println("데이터베이스 입력 오류/ Code: " + e.getMessage());
		}
		return true;
	}
	
	public boolean is_info_exist(String id) {		//로그인 했을때 아이디 비번이 일치하는지 확인
		try {
			String SQL = "SELECT * FROM user_info WHERE user_id = " + id;
			rs = st.executeQuery(SQL);		//SELECT 구문에 사용
			if(rs.next()) {
				System.out.println(rs.getString(4));
				System.out.println(rs.getString(5));
				return true;
			}	
		} 
		catch (Exception e) {
			System.out.println("데이터베이스 검색 오류/ Code: " + e.getMessage());
		}
		return false;	//검색되지 않았다면 false 반환
	}
	
	public static void main(String[] args) {
		
		

	}

}
