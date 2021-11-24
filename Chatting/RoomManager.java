package Chatting;

import java.util.ArrayList;
import java.util.List;

public class RoomManager {
	
	private static int dtoroomcount=0;
	private static int roomcount=0;		//현재존재하는 방의 개수
	private static int roomid;
	private static List <Room> roomlist=new ArrayList<Room>();	//방 저장할 리스트
	
	public RoomManager() {
		roomlist=new ArrayList<>();
	}
	
	public static void setroom(Room room) {
		System.out.println("몇번호출");
		roomlist.add(room);		//room추가해준 후
		roomcount++;		//roomcount 증가
		roomid=roomcount-1;	
	}
	public static void setdtoroomcount() {
		dtoroomcount++;
	}
	
	public static int getroomid() {
		return roomid;
	}
	
	public static Room getroom(int roomid) {
		//return roomlist.get(roomid-1);
		return roomlist.get(roomid);
	}
	
	public static int getdtoroomcount() {
		return dtoroomcount;
	}
	
	public static int getroomlistsize() {
		return roomlist.size();
	}
}
