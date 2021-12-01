package Chatting;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	private boolean chattingclicked;
	private boolean serverrun=true;
	//private boolean chattingclicked;		//채팅클릭인지 알림클릭인지 구별해줌
	public ChatServerObject() {
		
	
		
		//BufferedReader br=null;
		//PrintWriter pw=null;

		/*	try {  //서버 소켓 생성 작업
				//new hello();
				serverSocket = new ServerSocket(9500);
				
				System.out.println("서버 준비 완료");
				userlist=new ArrayList<ChatHandlerObject>();
				room=new Room();
				InfoDTO dto= null;
				
				while(serverrun) {
					Socket socket = serverSocket.accept();
					ChatHandlerObject handler1 = new ChatHandlerObject(socket);
					ChatHandlerObject handler2=new ChatHandlerObject(handler1);
					
					handler2.start();  //스레드 시작- 스레드 실행
					userlist.add(handler1);
					userlistsize=RoomManager.eachroomusersize();
				}	
			}
			
			 catch (IOException e) {
				e.printStackTrace();
		
		}
*/
	}
	public void serverrun() {
		try {  //서버 소켓 생성 작업
			new hello();
			serverSocket = new ServerSocket(9500);
			
			System.out.println("채팅서버 준비 완료");
			userlist=new ArrayList<ChatHandlerObject>();
			room=new Room();
			InfoDTO dto= null;
			
			while(serverrun) {
				System.out.println("check1");
				Socket socket = serverSocket.accept();
				ChatHandlerObject handler1 = new ChatHandlerObject(socket);
				ChatHandlerObject handler2=new ChatHandlerObject(handler1);
				
				handler2.start();  //스레드 시작- 스레드 실행
				userlist.add(handler1);
				userlistsize=RoomManager.eachroomusersize();
			}	
			System.out.println("check2");
		}
		
		 catch (IOException e) {
			e.printStackTrace();
	
	}
	}
	
	public void serverdown() {
		serverrun=false;
		System.out.println("채팅서버 종료");
		
	}
	

	public static void main(String[] args) 
	{
		new ChatServerObject().serverrun();
		
	}

}