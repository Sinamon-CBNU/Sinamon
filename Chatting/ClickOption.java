package Chatting;

import java.util.ArrayList;

public class ClickOption {
	
	private static boolean chattingclicked;	//채팅클릭 default는 false
		//채팅버튼 누르면 true로 바꿔주고 알림버튼 누르면 그대로 false
	
	public ClickOption() {
		chattingclicked=false;
	}
	
	
	public static boolean getchattingclicked() {	//채팅클릭여부 리턴
		return chattingclicked;
	}
	
	public static void setchattingclicked()
	{
		chattingclicked=true;
	}
	
	public static void setnoticeclicked() {
		chattingclicked=false;
	}
	
	
	
}
