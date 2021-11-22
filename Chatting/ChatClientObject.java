package Chatting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.io.*;



/*********************************************/
//성호가 고칠부분 96줄쯤 nickname부분

public class  ChatClientObject extends JFrame implements ActionListener,Runnable
{
	private JTextArea output; 
	private JTextField input; 
	private JButton sendBtn;
	private Socket socket;
	private ObjectInputStream reader=null;
	private ObjectOutputStream writer=null; 
	//private String msg;
	//private InfoDTO dto;
	private String nickName;
	private Thread t;
	public ChatClientObject() {
		//센터에 TextArea만들기
		
		output = new JTextArea();
		output.setFont(new Font("맑은 고딕",Font.BOLD,15));
		output.setEditable(false);
		JScrollPane scroll = new JScrollPane(output);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  //항상 스크롤바가 세로로 떠있음
		
		//하단에 버튼과 TextArea넣기 
		JPanel bottom = new JPanel();
		bottom.setLayout(new BorderLayout()); 
		input = new JTextField();
		
		sendBtn = new JButton("보내기");
		
		bottom.add("Center",input);  //센터에 붙이기
		bottom.add("East",sendBtn);  //동쪽에 붙이기
		//container에 붙이기
		Container c = this.getContentPane();
		c.add("Center", scroll);  //센터에 붙이기
		c.add("South", bottom);  //남쪽에 붙이기
		//윈도우 창 설정
		setBounds(300,300,300,300);
		setVisible(true);

		//윈도우 이벤트
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){ 
				//System.exit(0);
				try{
					//InfoDTO dto = new InfoDTO(nickName,Info.EXIT);
					InfoDTO dto = new InfoDTO();
					dto.setNickName(nickName);
					dto.setCommand(Info.EXIT);
					writer.writeObject(dto);  //역슬러쉬가 필요가 없음
					writer.flush();
				}catch(IOException io){
					io.printStackTrace();
				}
			}
		});
	}

	public void service(){
		//서버 IP 입력받기
		//String serverIP = JOptionPane.showInputDialog(this, "서버IP를 입력하세요","서버IP",JOptionPane.INFORMATION_MESSAGE);
		
		/********************************************/
		//String serverIP= JOptionPane.showInputDialog(this,"서버IP를 입력하세요","192.168.136.60");  //기본적으로 아이피 값이 입력되어 들어가게 됨
		//만약 ip입력을 받고싶으면 여기 주석해제하면 됨
		/**********************************************/
		
		/********************************/
		
		String serverIP="192.168.35.155";
		//서버측 ip가 변경되면 여기를 변경된 서버ip로 바꿔주면된다
		/*********************************/
		
		if(serverIP==null || serverIP.length()==0){  //만약 값이 입력되지 않았을 때 창이 꺼짐
			System.out.println("서버 IP가 입력되지 않았습니다.");
			System.exit(0);
		}
		//닉네임 받기
		/*************************************************/
		//nickName= JOptionPane.showInputDialog(this,"닉네임을 입력하세요","닉네임" ,JOptionPane.INFORMATION_MESSAGE);
		
		//이부분을 db의 nickname으로받아주소 
		//nickName=
		
		if(nickName == null || nickName.length()==0){
			nickName="guest";
		}
		try{
			socket = new Socket(serverIP,9500);
			//에러 발생
			reader= new ObjectInputStream(socket.getInputStream());
			writer = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("전송 준비 완료!"); 
			
		} catch(UnknownHostException e ){
			System.out.println("서버를 찾을 수 없습니다.");
			e.printStackTrace();
			System.exit(0);
		} catch(IOException e){
			System.out.println("서버와 연결이 안되었습니다.");
			e.printStackTrace();
			System.exit(0);
		}
		try{
			//서버로 닉네임 보내기
			
			InfoDTO dto = new InfoDTO();
			dto.setCommand(Info.JOIN);
			dto.setNickName(nickName);
			writer.writeObject(dto);  //역슬러쉬가 필요가 없음
			writer.flush();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		//스레드 생성
		
		t = new Thread(this);
		t.start();
		input.addActionListener(this);
		sendBtn.addActionListener(this);  //멕션 이벤트 추가
	}
	//스레드 오버라이드 
		@Override
		public void run(){
			//서버로부터 데이터 받기
			InfoDTO dto= null;
			while(true){
				try{
					dto = (InfoDTO) reader.readObject();
					if(dto.getCommand()==Info.EXIT){  //서버로부터 내 자신의 exit를 받으면 종료됨
						
						/***************************************************
						 * ***************************************
						 */
						//이부분이 종료를담당합니다
						reader.close();
						writer.close();
						socket.close();
						//System.exit(0);	//프로그램전체종료 주석처리
						break;			//한개의 프로그램만 종료되도록 수정
					} else if(dto.getCommand()==Info.SEND){
						output.append(dto.getMessage()+"\n");
						int pos=output.getText().length();
						output.setCaretPosition(pos);
					}
				}catch(IOException e){
					e.printStackTrace();
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}	
			}
		}
    //ActionPerformed
	@Override
	public void actionPerformed(ActionEvent e){
			try{
				//서버로 보냄 
				//JTextField값을 서버로보내기
				//버퍼 비우기
				String msg=input.getText();
				InfoDTO dto = new InfoDTO();
				//dto.setNickName(nickName);
				if(msg.equals("exit")){
					dto.setCommand(Info.EXIT);
				} else {
					dto.setCommand(Info.SEND);
					dto.setMessage(msg);
					dto.setNickName(nickName);
				}
				writer.writeObject(dto);
				writer.flush();
				input.setText("");
				
			}catch(IOException io){
				io.printStackTrace();
			}
	}

	public static void main(String[] args) 
	{
		new ChatClientObject().service();
	}
}
//���� ä���� ���� �����带 �������־�� ��