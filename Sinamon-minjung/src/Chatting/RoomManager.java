package Chatting;

import java.util.ArrayList;
import java.util.List;

public class RoomManager {
	
	private static int dtoroomcount=0;
	private static int roomcount=0;		//현재존재하는 방의 개수
	private static int roomid;
	private static int necroomid;
	private static int foodroomid;
	private static int necroomcount=0;
	private static int foodroomcount=0;
	private static List<Room> roomlist=new ArrayList<Room>();	//방 저장할 리스트
	private static List<Room> necroomlist=new ArrayList<Room>();
	private static List<Room> foodroomlist=new ArrayList<Room>();
	
	public RoomManager() {
		roomlist=new ArrayList<>();
	}
	
	public static void setroomhandler(ChatHandlerObject handler) {
		Room eachroom=roomlist.get(roomid);		//해당 인덱스의 방을 받아서
		eachroom.setuser(handler);		//방의 userlist에 handler를 추가해준다
	}
	
	public static void setroomid() {
		roomid=9999;
	}
	
	public static void setnecroom(Room room) {
		necroomlist.add(room);		//room추가해준 후
		necroomcount++;		//roomcount 증가
		necroomid=necroomcount-1;	
	}
	public static void setfoodroom(Room room) {
		foodroomlist.add(room);		//room추가해준 후
		foodroomcount++;		//roomcount 증가
		foodroomid=foodroomcount-1;	
	}
	public static void setroom(Room room) {
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
		return roomlist.get(roomid);
	}
	
	public static Room getnecroom(int necroomid) {
		return necroomlist.get(necroomid);
	}
	
	public static Room getfoodroom(int foodroomid) {
		return foodroomlist.get(foodroomid);
	}
	
	public static int getdtoroomcount() {
		return dtoroomcount;
	}
	
	public static int getroomlistsize() {
		return roomlist.size();
	}
	
	public static int eachroomusersize() {
		Room eachroom=roomlist.get(roomid);
		return eachroom.getuserlistsize();
	}
	
	public static List<Room> getroomlistt(){
		return roomlist;
	}
}