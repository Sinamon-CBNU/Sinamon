package Chatting;

import java.util.ArrayList;

public class RoomManager {
	
	
	private static int roomcount=0;
	
	
	public static int getroomcount() {
		return roomcount;
	}
	
	public static void setroomcount()
	{
		roomcount=roomcount+1;
	}
	
}
