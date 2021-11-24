package Chatting;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StreamCorruptedException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/******************/
//sinamon에선 서버가 열려있다 가정하고 서버오픈버튼을 지움

public class ChatServerObject 

{
	
	private ServerSocket serverSocket;
	private List <ChatHandlerObject> userlist;
	private List <Room> roomlist;
	private ObjectInputStream reader = null;
	private ObjectOutputStream writer = null;
	private Room room;
	private int roomid;
	private int userlistsize=0;
	public ChatServerObject() {
	
		//BufferedReader br=null;
		//PrintWriter pw=null;

			try {  //서버 소켓 생성 작업
		
				serverSocket = new ServerSocket(9500);
				
				System.out.println("서버 준비 완료");
				userlist=new ArrayList<ChatHandlerObject>();
				room=new Room();
				InfoDTO dto= null;
				
				while(true) {
					
					
					if(userlistsize==2) {		//두명이 다들어왔으면 (지금은 3명접속받은상태)
						room.deleteuser();					// 방에있는 인원 제거(이미 이 방의 정보는 
						userlistsize=room.getuserlistsize();//ChatHandlerObject로 전달해주었다)
					}
					 
					
					else if(userlistsize==0) {	//아무도없을때 한명이 방을들어오면 방을 생성해준다
						RoomManager.setroom(room);
						roomid=RoomManager.getroomid();
						System.out.println("서버에용roomid="+roomid);
						System.out.println("roomlist의 길이="+RoomManager.getroomlistsize());
						room.setroomid(roomid);
					}
					
					System.out.println("checkpoint9");
					Socket socket = serverSocket.accept();
					System.out.println("준엽이형도와줘"+room.getuser().size());
					System.out.println("userlistsize의크기는"+userlistsize);
					//ChatHandlerObject handler = new  ChatHandlerObject(socket,room);
					ChatHandlerObject handler = new  ChatHandlerObject(socket,userlist,room);
					
					
					
					handler.start();  //스레드 시작- 스레드 실행
					userlist.add(handler);
					room.setuser(handler);	//room에다가 user추가해줌
					userlistsize=room.getuserlistsize();
					
					/*if(room.getuser().size()==2) {		//2명되면 
						RoomManager.setroom(room);		//방을 roommanager에 추가해줌
						roomid=RoomManager.getroomcount();  //현재 방번호 roommanager에서 받아서 roomidset해줌
						room.setroomid(roomid);	// roomidset해줌
						room.deleteuser();             //전달해주고 room 비워줌
					}*/
					 System.out.println("userlist의 사이즈(3):"+room.getuserlistsize());
					/* if(room.getuser().size()==2) {		//두명이 다들어왔으면 (지금은 3명접속받은상태)
						room.deleteuser();					// 방에있는 인원 제거(이미 이 방의 정보는 
						userlistsize=room.getuserlistsize();//ChatHandlerObject로 전달해주었다)
					}
					 
					
					else if(userlistsize==0) {	//아무도없을때 한명이 방을들어오면 방을 생성해준다
						RoomManager.setroom(room);
						roomid=RoomManager.getroomid();
						System.out.println("서버에용roomid="+roomid);
						System.out.println("roomlist의 길이="+RoomManager.getroomlistsize());
						room.setroomid(roomid);
					}
				*/
					 System.out.println("userlist의 사이즈(1):"+room.getuserlistsize());
					
					System.out.println("checkpoint10");
					//br = new BufferedReader(
					       // new InputStreamReader(socket.getInputStream()));
					//pw = new PrintWriter(socket.getOutputStream());
					
					//writer = new ObjectOutputStream(socket.getOutputStream());
					System.out.println("checkpoint7");
					//reader = new ObjectInputStream(socket.getInputStream());
					System.out.println("checkpoint8");
					/*try{
						dto = (InfoDTO) reader.readObject();
					} catch(IOException e){
						e.printStackTrace();
					}catch(ClassNotFoundException e){
						e.printStackTrace();
					}	*/
					//System.out.println(dto.getroomnumber());
					
					//System.out.println(br.readLine());
					
					
					
				
				/*
					System.out.println("checkpoint11");
					handler.start();  //스레드 시작- 스레드 실행
					System.out.println("checkpoint12");
					userlist.add(handler);
					room.setuser(handler);	//room에다가 user추가해줌
					userlistsize=room.getuserlistsize();
					 System.out.println("userlist의 사이즈(2):"+room.getuserlistsize());
					System.out.println("checkpoint13");
					*/
					
				}	
			}
			
			 catch (IOException e) {
				e.printStackTrace();
		
		}

	}
	

	public static void main(String[] args) 
	{
		new ChatServerObject();
	}

}

