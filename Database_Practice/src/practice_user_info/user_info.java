package practice_user_info;

public class user_info {

	public static void main(String[] args) {
		db_connection connection = new db_connection();
		//System.out.println("������ ����: " + connection.is_info_exist("'kksshh0612'"));
		connection.input_user_info("'������'", "'����'", "'smj'", "'0228'", "'����'");
	}

}
