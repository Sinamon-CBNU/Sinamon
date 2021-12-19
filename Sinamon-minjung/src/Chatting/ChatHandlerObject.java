package Chatting;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;


class ChatHandlerObject extends Thread //泥섎━�빐二쇰뒗 怨�(�냼耳볦뿉 ���븳 �젙蹂닿� �떞寃⑥엳�뒗 怨�. �냼耳볦쓣 泥섎━�븿)

{
	private ObjectInputStream reader;
	private ObjectOutputStream writer;
	public int a=0;
	private Socket socket;
	//private InfoDTO dto;
	///private Info command;
	private List <ChatHandlerObject> userlist;
	private Room room;
	private int roomid;
	private ChatHandlerObject handler;
	private String roomname;
	//�깮�꽦�옄

	public ChatHandlerObject(Socket socket) throws IOException {
		
		this.socket = socket;
		writer = new ObjectOutputStream(socket.getOutputStream());
		reader = new ObjectInputStream(socket.getInputStream());
		
		//�닚�꽌媛� �뮘諛붾�뚮㈃ 媛믪쓣 �엯�젰諛쏆� 紐삵븯�뒗 �긽�솴�씠 踰뚯뼱吏�湲� �븣臾몄뿉 諛섎뱶�떆 writer遺��꽣 �깮�꽦�떆耳쒖＜�뼱�빞 �븿!!!!!!

		
	}
	public ChatHandlerObject(ChatHandlerObject handler) {
		this.handler=handler;
		writer=handler.getwriter();
		reader=handler.getreader();
	}
	
	public Socket getsocket() {
		return socket;
	}
	public ObjectOutputStream getwriter() {
		return writer;
	}
	public ObjectInputStream getreader() {
		return reader;
	}
	public int getroomid() {
		return roomid;
	}
	public void run(){
		InfoDTO dto = null;
		String nickName;
		try{
			int loopcount=0;
			while(true){
	            dto=(InfoDTO)reader.readObject();
				if(loopcount==0) {	//猷⑦봽泥ル컮�대븣留� 諛⑹깮�꽦諛� handler異붽��빐以�
				
				roomname=dto.getroomname();
				
				if(roomname.equals("nec")) {
					roomid=dto.getnecroomid();
					Room eachnecroom=RoomManager.getnecroom(roomid);
					eachnecroom.setuser(handler);
				}
				else if(roomname.equals("food")) {
					roomid=dto.getfoodroomid();
					Room eachfoodroom=RoomManager.getfoodroom(roomid);
					eachfoodroom.setuser(handler);
				}
				else {
					System.out.println("roomname �삤瑜�");
				}
				}
				
				nickName=dto.getNickName();
				
				

	
				//�궗�슜�옄媛� �젒�냽�쓣 �걡�뿀�쓣 寃쎌슦. �봽濡쒓렇�옩�쓣 �걹�궡�꽌�뒗 �븞�릺怨� �궓�� �궗�슜�옄�뱾�뿉寃� �눜�옣硫붿꽭吏�瑜� 蹂대궡以섏빞 �븳�떎. 
				if(dto.getCommand()==Info.EXIT){ 
			
					InfoDTO sendDto = new InfoDTO();
					//�굹媛��젮怨� exit瑜� 蹂대궦 �겢�씪�씠�뼵�듃�뿉寃� �떟蹂� 蹂대궡湲�
					sendDto.setMessage(nickName+"이 퇴장하였습니다.");
					sendDto.setCommand(Info.NOTICE);
					broadcast(sendDto);
					
					writer=handler.getwriter();
					writer.writeObject(sendDto);
					writer.flush();

		
					

					sendDto.setCommand(Info.EXIT);
				
					break;
				} else if(dto.getCommand()==Info.JOIN){
					//占쏙옙占� 占쏙옙占쏙옙悶占쏙옙占� 占쌨쇽옙占쏙옙 占쏙옙占쏙옙占쏙옙
					//nickName = dto.getNickName();
					//占쏙옙占� 클占쏙옙占싱억옙트占쏙옙占쏙옙 占쏙옙占쏙옙 占쌨쇽옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙
					InfoDTO sendDto = new InfoDTO();
					sendDto.setCommand(Info.NOTICE);
					sendDto.setMessage(nickName+"이(가) 입장했습니다.");
					sendDto.sethandlerroomnumber(dto.getroomid());
					broadcast(sendDto);
				} else if(dto.getCommand()==Info.SEND){
					InfoDTO sendDto = new InfoDTO();
					sendDto.setCommand(Info.SEND);
					sendDto.setMessage("["+nickName+"] "+ dto.getMessage());
					broadcast(sendDto);
				}
				loopcount++;
			}//while

		} catch(IOException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	
		
	}
	
	

	//�떎瑜� �겢�씪�씠�뼵�듃�뿉寃� �쟾泥� 硫붿꽭吏� 蹂대궡二쇨린
	public void broadcast(InfoDTO sendDto) throws IOException {
		Room eachroom;
		if(roomname.equals("nec")) {
			 eachroom=RoomManager.getnecroom(roomid);
		}
		else if(roomname.equals("food")) {
			 eachroom=RoomManager.getfoodroom(roomid);
		}
		else {
			eachroom=RoomManager.getnecroom(roomid);
			System.out.println("roomname error!");
		}
		
		//Room eachroom2=RoomManager.getroom(roomid);		//諛⑸쾲�샇瑜쇳넻�빐 RoomManager�뿉�꽌諛⑹쓣 諛쏆븘���꽌
		for(ChatHandlerObject handler : eachroom.getuser()) {
			handler.writer.writeObject(sendDto); //�빖�뱾�윭 �븞�쓽 writer�뿉 媛믪쓣 蹂대궡湲�
			handler.writer.flush();  //�빖�뱾�윭 �븞�쓽 writer 媛� 鍮꾩썙二쇨린
		}
	}
	
	
	/*public void broadcast(InfoDTO sendDto) throws IOException {
		
		if(userlist==null) {
			System.out.println("userlist�뒗 null�엯�땲�떎");
		}
		
		
		for(ChatHandlerObject handler : userlist) {
			handler.writer.writeObject(sendDto); //�빖�뱾�윭 �븞�쓽 writer�뿉 媛믪쓣 蹂대궡湲�
			handler.writer.flush();  //�빖�뱾�윭 �븞�쓽 writer 媛� 鍮꾩썙二쇨린
		}
	}*/
	
}