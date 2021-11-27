package Chatting;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


class ChatHandlerObject extends Thread //처리해주는 곳(소켓에 대한 정보가 담겨있는 곳. 소켓을 처리함)

{
	private ObjectInputStream reader;
	private ObjectOutputStream writer;
	private Socket socket;
	//private InfoDTO dto;
	///private Info command;
	private List <ChatHandlerObject> userlist;
	private Room room;
	private int roomid;
	
	//생성자

	public ChatHandlerObject(Socket socket, List<ChatHandlerObject> userlist, Room room) throws IOException {
		
		this.socket = socket;
		this.roomid=room.getroomid();
		this.room=room;
		this.userlist=userlist;
		writer = new ObjectOutputStream(socket.getOutputStream());
		reader = new ObjectInputStream(socket.getInputStream());
		
		//순서가 뒤바뀌면 값을 입력받지 못하는 상황이 벌어지기 때문에 반드시 writer부터 생성시켜주어야 함!!!!!!

		
	}
	public void run(){
		InfoDTO dto = null;
		String nickName;
		try{
			while(true){
				
				dto=(InfoDTO)reader.readObject();
				nickName=dto.getNickName();
				int roomnumber=dto.getroomnumber();
				
				
				//if(userlist.size()==2) {
					//System.out.println("제발돼라!!");
				//}

				//System.out.println("배열 크기:"+ar.length);
				//사용자가 접속을 끊었을 경우. 프로그램을 끝내서는 안되고 남은 사용자들에게 퇴장메세지를 보내줘야 한다. 
				if(dto.getCommand()==Info.EXIT){ 
			
					InfoDTO sendDto = new InfoDTO();
					//나가려고 exit를 보낸 클라이언트에게 답변 보내기

					sendDto.setCommand(Info.EXIT);
					writer.writeObject(sendDto);
					writer.flush();

					reader.close();
					writer.close();
					socket.close();

					//room.deleteuser();
					userlist.remove(this);

					sendDto.setCommand(Info.NOTICE);
					//sendDto.setWhossend(who.server);
					sendDto.setMessage(nickName+"님이 퇴장하였습니다.");
					broadcast(sendDto);
					break;
				} else if(dto.getCommand()==Info.JOIN){
					//��� ����ڿ��� �޼��� ������
					//nickName = dto.getNickName();
					//��� Ŭ���̾�Ʈ���� ���� �޼��� ������ ��
					InfoDTO sendDto = new InfoDTO();
					sendDto.setCommand(Info.NOTICE);
					//sendDto.setWhossend(who.server);
					boolean a=ClickOption.getchattingclicked();
					sendDto.setMessage(nickName+"님이 입장하였습니다."+a);
					
					sendDto.sethandlerroomnumber(dto.getroomnumber());
					broadcast(sendDto);
				} else if(dto.getCommand()==Info.SEND){
					System.out.println("내가속한 룸:"+roomid);
					InfoDTO sendDto = new InfoDTO();
					sendDto.setCommand(Info.SEND);
					sendDto.setMessage("["+nickName+"] "+ dto.getMessage());
					broadcast(sendDto);
				}
			}//while

		} catch(IOException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	
		
	}
	
	

	//다른 클라이언트에게 전체 메세지 보내주기
	public void broadcast(InfoDTO sendDto) throws IOException {
		
		Room eachroom=RoomManager.getroom(roomid);
		
		for(ChatHandlerObject handler : eachroom.getuser()) {
			
			handler.writer.writeObject(sendDto); //핸들러 안의 writer에 값을 보내기
			handler.writer.flush();  //핸들러 안의 writer 값 비워주기
		}
	}
	
	
	/*public void broadcast(InfoDTO sendDto) throws IOException {
		
		if(userlist==null) {
			System.out.println("userlist는 null입니다");
		}
		
		
		for(ChatHandlerObject handler : userlist) {
			handler.writer.writeObject(sendDto); //핸들러 안의 writer에 값을 보내기
			handler.writer.flush();  //핸들러 안의 writer 값 비워주기
		}
	}*/
	
}

