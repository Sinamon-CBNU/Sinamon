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


BufferedReader�� ����ߴٸ� �̹�� ��ü�� �ѱ�
��絥���͸� String�� �ƴ� InfoDTO�� ������ �޴� ��!
ObjectInputStream�� ObjectOutputStream� ����ؾ� ��!!



*/