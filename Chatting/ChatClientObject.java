/*********************************************/
//성호가 고칠부분 96줄쯤 nickname부분

package Chatting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.io.*;

import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;



class ChatClientObject extends JFrame implements ActionListener, Runnable {
	
	
	private JTextArea output;
	private JTextField input;
	private JButton sendBtn;
	private Socket socket;
	private ObjectInputStream reader = null;
	private ObjectOutputStream writer = null;
	private InfoDTO dto;
	private BufferedReader br;
	private PrintWriter pw;
	// private String msg;
	// private InfoDTO dto;
	private String nickName;
	private Thread t;
	private int roomid;
	public ChatClientObject(int roomid) {
		
		System.out.println("client object check1");
		this.roomid=roomid;
		String Image_Path="D:\\Eclipse\\workspace\\Sinamon\\Image";
		
		/*자기 경로에 맞게 IconImage_path바꾸면될듯*/
		
		//setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\6843w\\OneDrive\\\uBC14\uD0D5 \uD654\uBA74\\\uADF8\uB9BC\uC18C\uC2A4\\Window Icon.png"));
				setIconImage(Toolkit.getDefaultToolkit().getImage(Image_Path+"\\Window Icon.png"));
				setTitle("채팅방");

		/*
		 * frame = new JFrame(); frame.setBounds(100, 100, 500, 700);
		 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * frame.getContentPane().setLayout(null);
		 * 
		 * textField = new JTextField(); textField.setBounds(0, 605, 416, 58);
		 * frame.getContentPane().add(textField); textField.setColumns(10);
		 * 
		 * JButton btnNewButton = new JButton("New button"); btnNewButton.setBounds(415,
		 * 605, 71, 58); frame.getContentPane().add(btnNewButton);
		 * 
		 * JScrollBar scrollBar = new JScrollBar(); scrollBar.setBounds(481, 0, 5, 607);
		 * frame.getContentPane().add(scrollBar);
		 * 
		 * JPanel panel = new JPanel(); panel.setBackground(Color.PINK);
		 * panel.setBounds(0, 0, 486, 606); frame.getContentPane().add(panel);
		 */

		// ���Ϳ� TextArea�����
		output = new JTextArea();
		output.setBackground(new Color(255, 204, 204));
		output.setFont(new Font("CookieRun Regular", Font.PLAIN, 15));
		output.setForeground(new Color(50, 50, 50));
		output.setEditable(false);
		JScrollPane scroll = new JScrollPane(output);
		//scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // �׻� ��ũ�ѹٰ� ���η� �����
		
		ImageIcon img2 = new ImageIcon("C:\\Users\\6843w\\OneDrive\\���� ȭ��\\�׸��ҽ�\\send buttton3.png");
		
	
		
		// �ϴܿ� ��ư�� TextArea�ֱ�
		JPanel bottom = new JPanel();
		bottom.setBackground(Color.WHITE);
		bottom.setForeground(Color.RED);
		bottom.setLayout(new BorderLayout());
		input = new JTextField();
		input.setFont(new Font("CookieRun Regular", Font.PLAIN, 15));
		input.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		sendBtn = new JButton();
		sendBtn.setIcon(new ImageIcon(Image_Path+"\\send button6.png"));
		sendBtn.setFont(new Font("LG Smart UI Light", Font.PLAIN, 12));
		//sendBtn.setSize(60,35);
		//sendBtn.setBackground(Color.yellow);
		sendBtn.setBorderPainted(false); // ��ư �׵θ� ������
		//sendBtn.setOpaque(false);
		//sendBtn.setBorderPainted(false); 
		//sendBtn.setFocusPainted(false); 
		sendBtn.setContentAreaFilled(false);

		
		bottom.add("Center", input); // ���Ϳ� ���̱�
		bottom.add("East", sendBtn); // ���ʿ� ���̱�
		// container�� ���̱�
		Container c = this.getContentPane();
		c.add("Center", scroll); // ���Ϳ� ���̱�
		c.add("South", bottom); // ���ʿ� ���̱�
		// ����� â ���
		setBounds(300, 150, 350, 500);
		setVisible(true);

		// ����� �̺�Ʈ

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// System.exit(0);
				try {
					// InfoDTO dto = new InfoDTO(nickName,Info.EXIT);
					InfoDTO dto = new InfoDTO();
					dto.setNickName(nickName);
					dto.setCommand(Info.EXIT);
					writer.writeObject(dto); // ���������� �ʿ䰡 ���
					writer.flush();
				} catch (IOException io) {
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
		
		String serverIP="49.143.47.208";
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
		try{	//서버에 소켓 보내줌
			
			System.out.println("1");
			socket = new Socket(serverIP,9500);
			System.out.println("3");
			//에러 발생
			
		
			reader= new ObjectInputStream(socket.getInputStream());
			writer = new ObjectOutputStream(socket.getOutputStream());
			//writer.writeObject(dto);
			//writer.flush();
			
			System.out.println("7");
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
			System.out.println("8");
			//서버로 닉네임 보내기
			dto = new InfoDTO();
	
			
			dto.setroomid(roomid);
			dto.setCommand(Info.JOIN);
			//dto.setCommand(Info.NOTICE);
			dto.setNickName(nickName);
			
			
			//System.out.println("dto의 정보:"+dto.getroomnumber());
			//System.out.println("senddto의 정보: "+sendDto.getroomnumber());
			writer.writeObject(dto);  //역슬러쉬가 필요가 없음
			writer.flush();
			System.out.println("9");
			setTitle("채팅방"+dto.getroomid());
		}catch(IOException e){
			e.printStackTrace();
		}
		
		//스레드 생성
		System.out.println("4");
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
					System.out.println("5");
					dto = (InfoDTO) reader.readObject();
					System.out.println("6");
					if(dto.getCommand()==Info.EXIT){  //서버로부터 내 자신의 exit를 받으면 종료됨
						
						/***************************************************
						 * ***************************************
						 */
						//이부분이 종료를담당합니다
						reader.close();
						writer.close();
						socket.close();
						//pw.close();
						//br.close();
						//System.exit(0);	//프로그램전체종료 주석처리
						break;			//한개의 프로그램만 종료되도록 수정
					} else if(dto.getCommand()==Info.SEND){
						output.append(dto.getMessage()+"\n");
						int pos=output.getText().length();
						output.setCaretPosition(pos);
						
					}
					else if (dto.getCommand() == Info.NOTICE) {
						
			            String blank = "";
			            for(int i=0;i<(85-(dto.getMessage().length()*3.5))/2;i++) {
			               blank+=" ";
			            }
			            output.append(blank + dto.getMessage() + "\n\n");

			            int pos = output.getText().length();
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
		
	}
}
//���� ä���� ���� �����带 �������־�� ��

