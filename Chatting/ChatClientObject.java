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
	// private String msg;
	// private InfoDTO dto;
	private String nickName;

	public ChatClientObject() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\6843w\\OneDrive\\\uBC14\uD0D5 \uD654\uBA74\\\uADF8\uB9BC\uC18C\uC2A4\\Window Icon.png"));
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

		// 센터에 TextArea만들기
		output = new JTextArea();
		output.setBackground(new Color(255, 204, 204));
		output.setFont(new Font("CookieRun Regular", Font.PLAIN, 15));
		output.setForeground(new Color(50, 50, 50));
		output.setEditable(false);
		JScrollPane scroll = new JScrollPane(output);
		//scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // 항상 스크롤바가 세로로 떠있음
		ImageIcon img2 = new ImageIcon("C:\\Users\\6843w\\OneDrive\\바탕 화면\\그림소스\\send buttton3.png");
		// 하단에 버튼과 TextArea넣기
		JPanel bottom = new JPanel();
		bottom.setBackground(Color.WHITE);
		bottom.setForeground(Color.RED);
		bottom.setLayout(new BorderLayout());
		input = new JTextField();
		input.setFont(new Font("CookieRun Regular", Font.PLAIN, 15));
		input.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		sendBtn = new JButton();
		sendBtn.setIcon(new ImageIcon("C:\\Users\\6843w\\OneDrive\\\uBC14\uD0D5 \uD654\uBA74\\\uADF8\uB9BC\uC18C\uC2A4\\send button6.png"));
		sendBtn.setFont(new Font("LG Smart UI Light", Font.PLAIN, 12));
		//sendBtn.setSize(60,35);
		//sendBtn.setBackground(Color.yellow);
		sendBtn.setBorderPainted(false); // 버튼 테두리 설정해제
		//sendBtn.setOpaque(false);
		//sendBtn.setBorderPainted(false); 
		//sendBtn.setFocusPainted(false); 
		sendBtn.setContentAreaFilled(false);

		
		bottom.add("Center", input); // 센터에 붙이기
		bottom.add("East", sendBtn); // 동쪽에 붙이기
		// container에 붙이기
		Container c = this.getContentPane();
		c.add("Center", scroll); // 센터에 붙이기
		c.add("South", bottom); // 남쪽에 붙이기
		// 윈도우 창 설정
		setBounds(300, 150, 350, 500);
		setVisible(true);

		// 윈도우 이벤트

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// System.exit(0);
				try {
					// InfoDTO dto = new InfoDTO(nickName,Info.EXIT);
					InfoDTO dto = new InfoDTO();
					dto.setNickName(nickName);
					dto.setCommand(Info.EXIT);
					writer.writeObject(dto); // 역슬러쉬가 필요가 없음
					writer.flush();
				} catch (IOException io) {
					io.printStackTrace();
				}
			}
		});
	}
	
	
	
	public void service() {
		// 서버 IP 입력받기
		// String serverIP = JOptionPane.showInputDialog(this, "서버IP를
		// 입력하세요","서버IP",JOptionPane.INFORMATION_MESSAGE);
		String serverIP = JOptionPane.showInputDialog(this, "서버IP를 입력하세요", "192.168.0.3"); // 기본적으로 아이피 값이 입력되어 들어가게 됨
		if (serverIP == null || serverIP.length() == 0) { // 만약 값이 입력되지 않았을 때 창이 꺼짐
			System.out.println("서버 IP가 입력되지 않았습니다.");
			System.exit(0);
		}
		// 닉네임 받기
		nickName = JOptionPane.showInputDialog(this, "닉네임을 입력하세요", "닉네임", JOptionPane.INFORMATION_MESSAGE);
		if (nickName == null || nickName.length() == 0) {
			nickName = "guest";
		}
		try {
			socket = new Socket(serverIP, 9500);
			// 에러 발생
			reader = new ObjectInputStream(socket.getInputStream());
			writer = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("전송 준비 완료!");

		} catch (UnknownHostException e) {
			System.out.println("서버를 찾을 수 없습니다.");
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			System.out.println("서버와 연결이 안되었습니다.");
			e.printStackTrace();
			System.exit(0);
		}
		try {
			// 서버로 닉네임 보내기

			InfoDTO dto = new InfoDTO();
			dto.setCommand(Info.JOIN);
			dto.setNickName(nickName);
			writer.writeObject(dto); // 역슬러쉬가 필요가 없음
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 스레드 생성

		Thread t = new Thread(this);
		t.start();
		input.addActionListener(this);
		sendBtn.addActionListener(this); // 멕션 이벤트 추가
	}

	// 스레드 오버라이드
	@Override
	public void run() {
		// 서버로부터 데이터 받기
		InfoDTO dto = null;
		while (true) {
			try {
				dto = (InfoDTO) reader.readObject();
				if (dto.getCommand() == Info.EXIT) { // 서버로부터 내 자신의 exit를 받으면 종료됨
					reader.close();
					writer.close();
					socket.close();
					System.exit(0);
				} else if (dto.getCommand() == Info.SEND) {
					/*if (dto.getWhossend() == who.server) {

					output.setFont(new Font("맑은 고딕", Font.BOLD, 10));
					output.append(dto.getMessage() + "\n");

					int pos = output.getText().length();
					output.setCaretPosition(pos);

					output.setFont(new Font("맑은 고딕", Font.BOLD, 15));
				}
				else {*/
					output.append(dto.getMessage() + "\n");

					int pos = output.getText().length();
					output.setCaretPosition(pos);
				//}
			}else if (dto.getCommand() == Info.NOTICE) {
				/*if (dto.getWhossend() == who.server) {

				output.setFont(new Font("맑은 고딕", Font.BOLD, 10));
				output.append(dto.getMessage() + "\n");

				int pos = output.getText().length();
				output.setCaretPosition(pos);

				output.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			}
			else {*/
				String blank = "";
				for(int i=0;i<(85-(dto.getMessage().length()*3.5))/2;i++) {
					blank+=" ";
				}
				output.append(blank + dto.getMessage() + "\n\n");

				int pos = output.getText().length();
				output.setCaretPosition(pos);
			//}
		}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	// ActionPerformed
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			// 서버로 보냄
			// JTextField값을 서버로보내기
			// 버퍼 비우기
			String msg = input.getText();
			InfoDTO dto = new InfoDTO();
			// dto.setNickName(nickName);
			if (msg.equals("exit")) {
				dto.setCommand(Info.EXIT);
				dto.setNickName(nickName);
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

	public static void main(String[] args) {
		new ChatClientObject().service();
	}
}
//동시 채팅을 위해 쓰레드를 생성해주어야 함