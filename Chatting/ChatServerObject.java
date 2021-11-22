package Chatting;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/******************/
//sinamon에선 서버가 열려있다 가정하고 서버오픈버튼을 지움

public class ChatServerObject 
{
	private ServerSocket serverSocket;
	private List <ChatHandlerObject> list;
	public ChatServerObject(){
			ServerThread serverThread = new ServerThread();
			serverThread.start();
	}
	
	class ServerThread extends Thread {

		@Override

		public void run() {			

			try {  //서버 소켓 생성 작업

				serverSocket = new ServerSocket(9500);
				System.out.println("서버 준비 완료");
				list = new  ArrayList<ChatHandlerObject>();

				while(true) {
					Socket socket = serverSocket.accept();
					ChatHandlerObject handler = new  ChatHandlerObject(socket,list);  //스레드를 생성한 것이랑 동일함! 떄문에 시자해주어야 
					handler.start();  //스레드 시작- 스레드 실행
					list.add(handler);  //핸들러를 담음( 이 리스트의 개수가 클라이언트의 갯수!!)
				}				

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
	
	public static void main(String[] args) 
	{
		new ChatServerObject();
	}
}