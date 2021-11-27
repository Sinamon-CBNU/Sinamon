package Chatting;

import java.util.ArrayList;

public class ClickOption {
	
	
	private static ArrayList<Integer> chattingoption;
	private static ArrayList<Integer> noticeoption;
	private static int chattingoptioncount=-1;
	private static int noticeoptioncount=-1;
	private static int roomid;
	public static boolean chattingclicked;	//채팅클릭 default는 false
		//채팅버튼 누르면 true로 바꿔주고 알림버튼 누르면 그대로 false
	
	
	public static int getchattingoption() {
		return chattingoption.get(chattingoptioncount);
	}
	public static int getnoticeoption() {
		return noticeoption.get(noticeoptioncount);
	}
	public static boolean getchattingclicked() {	//채팅클릭여부 리턴
		System.out.println("getchattingclicked:"+chattingclicked);
		return chattingclicked;
	}
	
	public static void setchattingclicked()
	{
		chattingclicked=true;
		System.out.println("chattingclicked:"+chattingclicked);
	}
	
	public static void setchattingoption() {
		chattingoptioncount++;
		chattingoption.add(1);
	}
	public static void setnoticeoptioncount() {
		noticeoptioncount++;
		noticeoption.add(1);
	}
	
	
}
