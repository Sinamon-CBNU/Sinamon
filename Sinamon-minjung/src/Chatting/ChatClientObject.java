/*********************************************/
//�꽦�샇媛� 怨좎튌遺�遺� 96以꾩�� nickname遺�遺�

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

import javax.sound.sampled.ReverbType;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import sinamon.*;

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
		String Image_Path="C:\\Users\\kkssh\\Desktop\\eclipse-workspace\\Sinamon-minjung\\Image";
		
		/*�옄湲� 寃쎈줈�뿉 留욊쾶 IconImage_path諛붽씀硫대맆�벏*/
		//C:\Users\kkssh\Desktop\eclipse-workspace\Sinamon-minjung\Image
		
				setIconImage(Toolkit.getDefaultToolkit().getImage(Image_Path+"\\Window Icon.png"));
				setTitle("梨꾪똿諛�");

		

		// 占쏙옙占싶울옙 TextArea占쏙옙占쏙옙占�
		output = new JTextArea();
		output.setBackground(new Color(255, 204, 204));
		output.setFont(new Font("CookieRun Regular", Font.PLAIN, 15));
		output.setForeground(new Color(50, 50, 50));
		output.setEditable(false);
		JScrollPane scroll = new JScrollPane(output);
		//scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // 占쌓삼옙 占쏙옙크占싼바곤옙 占쏙옙占싸뤄옙 占쏙옙占쏙옙占�
		
		
	
		
		// 占싹단울옙 占쏙옙튼占쏙옙 TextArea占쌍깍옙
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
		sendBtn.setBorderPainted(false); // 占쏙옙튼 占쌓두몌옙 占쏙옙占쏙옙占쏙옙
		//sendBtn.setOpaque(false);
		//sendBtn.setBorderPainted(false); 
		//sendBtn.setFocusPainted(false); 
		sendBtn.setContentAreaFilled(false);
		confirmBtn = new JButton();
		confirmBtn.setIcon(new ImageIcon(Image_Path+"\\confirmBtn.png"));
		confirmBtn.setBorderPainted(false); 
		confirmBtn.setContentAreaFilled(false);
		
		bottom.add("Center", input); // 占쏙옙占싶울옙 占쏙옙占싱깍옙
		bottom.add("East", sendBtn); // 占쏙옙占십울옙 占쏙옙占싱깍옙
		bottom.add("West", confirmBtn);
		// container占쏙옙 占쏙옙占싱깍옙
		Container c = this.getContentPane();
		c.add("Center", scroll); // 占쏙옙占싶울옙 占쏙옙占싱깍옙
		c.add("South", bottom); // 占쏙옙占십울옙 占쏙옙占싱깍옙
		// 占쏙옙占쏙옙占� 창 占쏙옙占�
		setBounds(300, 150, 350, 500);
		setVisible(true);

		// 占쏙옙占쏙옙占� 占싱븝옙트

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// System.exit(0);
				try {
					// InfoDTO dto = new InfoDTO(nickName,Info.EXIT);
					InfoDTO dto = new InfoDTO();
					dto.setNickName(nickName);
					dto.setCommand(Info.EXIT);
					writer.writeObject(dto); // 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙 占십요가 占쏙옙占�
					writer.flush();
				} catch (IOException io) {
					io.printStackTrace();
				}
			}
		});
		
		
	}


	public void service(){
		
		
		
		
		//�꽌踰� IP �엯�젰諛쏄린
		//String serverIP = JOptionPane.showInputDialog(this, "�꽌踰껱P瑜� �엯�젰�븯�꽭�슂","�꽌踰껱P",JOptionPane.INFORMATION_MESSAGE);
		
		/********************************************/
		//String serverIP= JOptionPane.showInputDialog(this,"�꽌踰껱P瑜� �엯�젰�븯�꽭�슂","192.168.136.60");  //湲곕낯�쟻�쑝濡� �븘�씠�뵾 媛믪씠 �엯�젰�릺�뼱 �뱾�뼱媛�寃� �맖
		//留뚯빟 ip�엯�젰�쓣 諛쏄퀬�떢�쑝硫� �뿬湲� 二쇱꽍�빐�젣�븯硫� �맖
		/**********************************************/
		
		/********************************/
		
		String serverIP="192.168.0.3";
		//�꽌踰꾩륫 ip媛� 蹂�寃쎈릺硫� �뿬湲곕�� 蹂�寃쎈맂 �꽌踰꼒p濡� 諛붽퓭二쇰㈃�맂�떎
		/*********************************/
		
		if(serverIP==null || serverIP.length()==0){  //留뚯빟 媛믪씠 �엯�젰�릺吏� �븡�븯�쓣 �븣 李쎌씠 爰쇱쭚
			System.out.println("�꽌踰� IP媛� �엯�젰�릺吏� �븡�븯�뒿�땲�떎.");
			System.exit(0);
		}
		//�땳�꽕�엫 諛쏄린
		/*************************************************/
		//nickName= JOptionPane.showInputDialog(this,"�땳�꽕�엫�쓣 �엯�젰�븯�꽭�슂","�땳�꽕�엫" ,JOptionPane.INFORMATION_MESSAGE);
		
		//�씠遺�遺꾩쓣 db�쓽 nickname�쑝濡쒕컺�븘二쇱냼 
		nickName="김호발";
		
		if(nickName == null || nickName.length()==0){
			nickName="guest";
		}
		try{	//�꽌踰꾩뿉 �냼耳� 蹂대궡以�
			socket = new Socket(serverIP,9500);
			reader= new ObjectInputStream(socket.getInputStream());
			writer = new ObjectOutputStream(socket.getOutputStream());
		} catch(UnknownHostException e ){
			System.out.println("�꽌踰꾨�� 李얠쓣 �닔 �뾾�뒿�땲�떎.");
			e.printStackTrace();
			System.exit(0);
		} catch(IOException e){
			System.out.println("�꽌踰꾩� �뿰寃곗씠 �븞�릺�뿀�뒿�땲�떎.");
			e.printStackTrace();
			System.exit(0);
		}
		try{
			//handler濡� �땳�꽕�엫 蹂대궡湲�
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
			writer.writeObject(dto);  //�뿭�뒳�윭�돩媛� �븘�슂媛� �뾾�쓬
			writer.flush();
			//setTitle("梨꾪똿諛�"+dto.getroomid());
		}catch(IOException e){
			e.printStackTrace();
		}
		
		//�뒪�젅�뱶 �깮�꽦
		t = new Thread(this);
		t.start();
		input.addActionListener(new sendActionListener());
		sendBtn.addActionListener(new sendActionListener());
		confirmBtn.addActionListener(new confirmBtnActionListener());//硫뺤뀡 �씠踰ㅽ듃 異붽�
	}
	//�뒪�젅�뱶 �삤踰꾨씪�씠�뱶 
		@Override
		public void run(){
			//�꽌踰꾨줈遺��꽣 �뜲�씠�꽣 諛쏄린
			InfoDTO dto= null;
			while(true){
				try{
					dto = (InfoDTO) reader.readObject();
					if(dto.getCommand()==Info.EXIT){  //�꽌踰꾨줈遺��꽣 �궡 �옄�떊�쓽 exit瑜� 諛쏆쑝硫� 醫낅즺�맖
						
						/***************************************************
						 * ***************************************
						 */
						//�씠遺�遺꾩씠 醫낅즺瑜쇰떞�떦�빀�땲�떎
						reader.close();
						writer.close();
						socket.close();
						//pw.close();
						//br.close();
						//System.exit(0);	//�봽濡쒓렇�옩�쟾泥댁쥌猷� 二쇱꽍泥섎━
						break;			//�븳媛쒖쓽 �봽濡쒓렇�옩留� 醫낅즺�릺�룄濡� �닔�젙
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
				db_connection connection = new db_connection(); 
				if (e.getSource() == confirmBtn) {
					Object[] curr_user = {1, "김성호", "김호발", "kksshh0612", "ksh1735", "2"};
					sinamon.WriteRev rev = new WriteRev(curr_user, connection);
							System.out.print("확정버튼 클릭");
				}
					

			}

		}
	
	public static void main(String[] args) 
	{
		
	}
}
//占쏙옙占쏙옙 채占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占썲를 占쏙옙占쏙옙占쏙옙占쌍억옙占� 占쏙옙
