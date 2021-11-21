package Chatting;

import java.net.Socket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

class ChatServerObject 
{
	private ServerSocket serverSocket;
	private List <ChatHandlerObject> list;
	public ChatServerObject(){
		try{
			serverSocket= new ServerSocket (9500);
			System.out.println("���� �غ� �Ϸ�");
			list = new  ArrayList<ChatHandlerObject>();
			while(true){
				Socket socket = serverSocket.accept();
				ChatHandlerObject handler = new  ChatHandlerObject(socket,list);  //�����带 ������ ���̶� ������! ������ �������־�� 
				handler.start();  //������ ����- ������ ����
				list.add(handler);  //�ڵ鷯�� ����( �� ����Ʈ�� ������ Ŭ���̾�Ʈ�� ����!!)
			}//while
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) 
	{
		new ChatServerObject();
	}
}
