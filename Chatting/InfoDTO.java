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
	private int roomnumber=0;		//방번호
	private boolean room_existence; //방번호 존재 여부
	private Info command;	
	private Room room;
	
	
	
	//private who whossend = who.me;
	
	public boolean getroomexistence() {
		return room_existence;
	}
	
	public int getroomnumber() {
		return roomnumber;
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
		this.roomnumber=roomnumber;
	}
	
	public void setroomexistence(boolean room_existence) {
		this.room_existence=room_existence;
	}
	
	public void setroomnumber() {
		
		if(room_existence==true) {		//이미방이존재할때는 방을 새로만들어주는 것이아닌
										//원래 존재하던 방을 할당해주는 것
			roomnumber=RoomManager.getdtoroomcount();	//받음
			RoomManager.setdtoroomcount();	//그리고 다음에들어올애들을 위해 방증가시켜줌 
		}
		else if(room_existence==false) {	//방이 존재하지않을때는 방의 수를 증가시켜주고 roomnumber새로할당
		//RoomManager.setroomcount();	//방하나 생성 후
		//roomnumber=RoomManager.getroomcount();		//방번호 할당
		//room=new Room();
		
		roomnumber=RoomManager.getdtoroomcount();	//인덱스일치때문에 0번부터받음
		}
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
}
/*
ChatClient.java ---> ChatClientObject.java
ChatServer.java ---> ChatServerObject.java
CahtHandler.java ---> ChatHandlerObject.java


BufferedReader�� ����ߴٸ� �̹�� ��ü�� �ѱ�
��絥���͸� String�� �ƴ� InfoDTO�� ������ �޴� ��!
ObjectInputStream�� ObjectOutputStream� ����ؾ� ��!!



*/