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
	private boolean chattingclicked;		//채팅클릭인지 알림클릭인지 구별해줌
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
					
					
					chattingclicked=ClickOption.getchattingclicked();
					System.out.println("아잌"+chattingclicked);
					if(userlistsize==2) {		//두명이 다들어왔으면 (지금은 3명접속받은상태)
						//room.deleteuser();					// 방에있는 인원 제거(이미 이 방의 정보는 
						room=new Room();	//room 새로 할당
						userlistsize=room.getuserlistsize();//ChatHandlerObject로 전달해주었다)
					}
					 
					
					 if(userlistsize==0) {	//아무도없을때 한명이 방을들어오면 방을 생성해준다
						RoomManager.setroom(room);
						roomid=RoomManager.getroomid();
						System.out.println("!"+roomid);
						room.setroomid(roomid);
					}
					 //chattingclicked=ClickOption.chattingclicked;
					// System.out.println("어이"+chattingclicked);
					
					Socket socket = serverSocket.accept();
					
					System.out.println(RoomManager.getroomid());
					
					//ClickOption.setchattingclicked();
					 System.out.println("아잌"+chattingclicked);
					ChatHandlerObject handler = new  ChatHandlerObject(socket,userlist,room);
					//ChatHandlerObject handler = new  ChatHandlerObject(socket,userlist,roomlist.get(방번호));
					
					//여기서 추가해주고
					
					handler.start();  //스레드 시작- 스레드 실행
					userlist.add(handler);
					//room.setuser(handler);	//room에다가 user추가해줌
					RoomManager.setroomhandler(handler);
					userlistsize=RoomManager.eachroomusersize();
					
					
					
					
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

