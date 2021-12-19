/*********************************************/
//성호가 고칠부분 96줄쯤 nickname부분

package Chatting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class ChatClientObject extends JFrame implements Runnable {
	
	
	private JTextArea output;
	private JTextField input;
	private JButton sendBtn;
	private JButton confirmBtn;
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
	private String roomname;
	public ChatClientObject(int roomid,String roomname) {
		this.roomid=roomid;
		this.roomname=roomname;
		String Image_Path="D:\\Eclipse\\workspace\\Sinamon\\Image";
		
		/*자기 경로에 맞게 IconImage_path바꾸면될듯*/
		
		
				setIconImage(Toolkit.getDefaultToolkit().getImage(Image_Path+"\\Window Icon.png"));
				setTitle("채팅방");
				
		

		// ���Ϳ� TextArea�����
		output = new JTextArea();
		output.setBackground(new Color(255, 204, 204));
		output.setFont(new Font("CookieRun Regular", Font.PLAIN, 15));
		output.setForeground(new Color(50, 50, 50));
		output.setEditable(false);
		JScrollPane scroll = new JScrollPane(output);
		//scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // �׻� ��ũ�ѹٰ� ���η� �����
		
		
	
		
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
		confirmBtn = new JButton();
		confirmBtn.setIcon(new ImageIcon(Image_Path+"\\confirmBtn.png"));
		confirmBtn.setBorderPainted(false); 
		confirmBtn.setContentAreaFilled(false);
		
		bottom.add("Center", input); // ���Ϳ� ���̱�
		bottom.add("East", sendBtn); // ���ʿ� ���̱�
		bottom.add("West", confirmBtn);
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
		nickName="초코공주";
		
		if(nickName == null || nickName.length()==0){
			nickName="guest";
		}
		try{	//서버에 소켓 보내줌
			socket = new Socket(serverIP,9500);
			reader= new ObjectInputStream(socket.getInputStream());
			writer = new ObjectOutputStream(socket.getOutputStream());
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
			//handler로 닉네임 보내기
			dto = new InfoDTO();
			
			if(roomname.equals("nec")) {
				dto.setroomname(roomname);
				dto.setnecroomid(roomid);
				setTitle("생필품 채팅방"+dto.getnecroomid());
			}
			else if(roomname.equals("food")) {
				dto.setroomname(roomname);
				dto.setfoodroomid(roomid);
				setTitle("음식 채팅방"+dto.getfoodroomid());
			}
			else {
				System.out.println("roomname error!!");
			}
			
			dto.setroomid(roomid);
			dto.setCommand(Info.JOIN);
			//dto.setCommand(Info.NOTICE);
			dto.setNickName(nickName);
			writer.writeObject(dto);  //역슬러쉬가 필요가 없음
			writer.flush();
			//setTitle("채팅방"+dto.getroomid());
		}catch(IOException e){
			e.printStackTrace();
		}
		
		//스레드 생성
		t = new Thread(this);
		t.start();
		input.addActionListener(new sendActionListener());
		sendBtn.addActionListener(new sendActionListener());
		confirmBtn.addActionListener(new confirmBtnActionListener());//멕션 이벤트 추가
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
				}
				catch(IOException e){
					e.printStackTrace();
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}	
				
			}
		}
		
		class sendActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					String msg = input.getText();
					InfoDTO dto = new InfoDTO();
					if (msg.equals("exit")) {
						dto.setCommand(Info.EXIT);
					} else {
						dto.setCommand(Info.SEND);
						dto.setMessage(msg);
						dto.setNickName(nickName);
					}
					writer.writeObject(dto);
					writer.flush();
					input.setText("");

				} catch (IOException io) {
					io.printStackTrace();
				}
			}
		}

		class confirmBtnActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == confirmBtn)
					System.out.print("확정버튼 누르셨습니다.");

			}

		}
	
	public static void main(String[] args) 
	{
		
	}
}
//���� ä���� ���� �����带 �������־�� ��