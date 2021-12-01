package Chatting;

import java.util.*;
import java.io.*;

enum Info {

	JOIN, EXIT, SEND, NOTICE
}
//enum who {
	//me, other, server
//}


class InfoDTO implements Serializable{
	private String nickName;
	private String message;
	private int roomid;		//방번호
	private int necroomid;
	private int foodroomid;
	private boolean room_existence; //방번호 존재 여부
	private Info command;	
	private Room room;
	private String roomname;
	
	
	//private who whossend = who.me;
	
	public boolean getroomexistence() {
		return room_existence;
	}
	
	public int getroomid() {
		return roomid;
	}
	
	public int getnecroomid() {
		return necroomid;
	}
	
	public int getfoodroomid() {
		return foodroomid;
	}
	
	public String getroomname() {
		return roomname;
	}

	
	public String getNickName(){
		return nickName;
	}
	public Info getCommand(){
		return command;
	}
	public List<ChatHandlerObject> getuserlist(){
		return room.getuser();
	}

	
	
	

//	public who getWhossend(){
	//	return whossend;
	//}

	
	public String getMessage(){
		return message;
	}
	
	public void sethandlerroomnumber(int roomnumber) {
		//this.roomnumber=roomnumber;
	}
	
	public void setroomexistence(boolean room_existence) {
		this.room_existence=room_existence;
	}
	
	public void setroomid(int roomid) {
		this.roomid=roomid;
	}
	
	public void setnecroomid(int necroomid) {
		this.necroomid=necroomid;
	}
	
	public void setfoodroomid(int foodroomid) {
		this.foodroomid=foodroomid;
	}
	
	public void setNickName(String nickName){
		this.nickName= nickName;
	}
	public void setCommand(Info command){
		this.command= command;
	}

//	public void setWhossend(who whossend){
	//	this.whossend= whossend;
	//}

	public void setMessage(String message){
		this.message= message; 
	}
	
	public void setroomname(String roomname) {
		this.roomname=roomname;
	}
	
}
/*
ChatClient.java ---> ChatClientObject.java
ChatServer.java ---> ChatServerObject.java
CahtHandler.java ---> ChatHandlerObject.java
BufferedReader�� ����ߴٸ� �̹�� ��ü�� �ѱ�
��絥���͸� String�� �ƴ� InfoDTO�� ������ �޴� ��!
ObjectInputStream�� ObjectOutputStream� ����ؾ� ��!!
*/