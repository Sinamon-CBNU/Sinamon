package Chatting;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Room {
	private ObjectInputStream reader;
	private ObjectOutputStream writer;
	private Socket socket;
	private List <ChatHandlerObject> userlist;		//유저리스트
	private int roomid;
	
	public Room() {
		userlist=new ArrayList<ChatHandlerObject>();
	}
	
	public Room(int roomid) {
		this.roomid=roomid;
	}
	
	
	public List<ChatHandlerObject> getuser(){
		return userlist;
	}
	
	public void deleteuser() {
		userlist.clear();
	}
	
	public void setuser(ChatHandlerObject handler) {
		userlist.add(handler);
	}
	
	public int getroomid() {
		return roomid;
	}
	
	public void setroomid(int roomid) {
		this.roomid=roomid;
	}

	public int getuserlistsize() {
		return userlist.size();
	}
	
	
	
	
}
