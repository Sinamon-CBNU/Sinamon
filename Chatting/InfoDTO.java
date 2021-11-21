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
	private Info command;	
	//private who whossend = who.me;
	
	public String getNickName(){
		return nickName;
	}
	public Info getCommand(){
		return command;
	}
//	public who getWhossend(){
	//	return whossend;
	//}
	public String getMessage(){
		return message;
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

BufferedReader를 사용했다면 이번에는 객체로 넘김
모든데이터를 String이 아닌 InfoDTO로 보내고 받는 것!
ObjectInputStream과 ObjectOutputStream을 사용해야 함!!


*/