package practice_user_info;

public class user_info {

	public static void main(String[] args) {
		db_connection connection = new db_connection();
		System.out.println("관리자 여부: " + connection.is_info("'kksshh0612'", "'kksshh1735'"));
	}

}
