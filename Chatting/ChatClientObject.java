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
		setTitle("ä�ù�");

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
		//scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // �׻� ��ũ�ѹٰ� ���η� ������
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
		sendBtn.setIcon(new ImageIcon("C:\\Users\\6843w\\OneDrive\\\uBC14\uD0D5 \uD654\uBA74\\\uADF8\uB9BC\uC18C\uC2A4\\send button6.png"));
		sendBtn.setFont(new Font("LG Smart UI Light", Font.PLAIN, 12));
		//sendBtn.setSize(60,35);
		//sendBtn.setBackground(Color.yellow);
		sendBtn.setBorderPainted(false); // ��ư �׵θ� ��������
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
		// ������ â ����
		setBounds(300, 150, 350, 500);
		setVisible(true);

		// ������ �̺�Ʈ

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// System.exit(0);
				try {
					// InfoDTO dto = new InfoDTO(nickName,Info.EXIT);
					InfoDTO dto = new InfoDTO();
					dto.setNickName(nickName);
					dto.setCommand(Info.EXIT);
					writer.writeObject(dto); // ���������� �ʿ䰡 ����
					writer.flush();
				} catch (IOException io) {
					io.printStackTrace();
				}
			}
		});
	}
	
	
	
	public void service() {
		// ���� IP �Է¹ޱ�
		// String serverIP = JOptionPane.showInputDialog(this, "����IP��
		// �Է��ϼ���","����IP",JOptionPane.INFORMATION_MESSAGE);
		String serverIP = JOptionPane.showInputDialog(this, "����IP�� �Է��ϼ���", "192.168.0.3"); // �⺻������ ������ ���� �ԷµǾ� ���� ��
		if (serverIP == null || serverIP.length() == 0) { // ���� ���� �Էµ��� �ʾ��� �� â�� ����
			System.out.println("���� IP�� �Էµ��� �ʾҽ��ϴ�.");
			System.exit(0);
		}
		// �г��� �ޱ�
		nickName = JOptionPane.showInputDialog(this, "�г����� �Է��ϼ���", "�г���", JOptionPane.INFORMATION_MESSAGE);
		if (nickName == null || nickName.length() == 0) {
			nickName = "guest";
		}
		try {
			socket = new Socket(serverIP, 9500);
			// ���� �߻�
			reader = new ObjectInputStream(socket.getInputStream());
			writer = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("���� �غ� �Ϸ�!");

		} catch (UnknownHostException e) {
			System.out.println("������ ã�� �� �����ϴ�.");
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			System.out.println("������ ������ �ȵǾ����ϴ�.");
			e.printStackTrace();
			System.exit(0);
		}
		try {
			// ������ �г��� ������

			InfoDTO dto = new InfoDTO();
			dto.setCommand(Info.JOIN);
			dto.setNickName(nickName);
			writer.writeObject(dto); // ���������� �ʿ䰡 ����
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// ������ ����

		Thread t = new Thread(this);
		t.start();
		input.addActionListener(this);
		sendBtn.addActionListener(this); // �߼� �̺�Ʈ �߰�
	}

	// ������ �������̵�
	@Override
	public void run() {
		// �����κ��� ������ �ޱ�
		InfoDTO dto = null;
		while (true) {
			try {
				dto = (InfoDTO) reader.readObject();
				if (dto.getCommand() == Info.EXIT) { // �����κ��� �� �ڽ��� exit�� ������ �����
					reader.close();
					writer.close();
					socket.close();
					System.exit(0);
				} else if (dto.getCommand() == Info.SEND) {
					/*if (dto.getWhossend() == who.server) {

					output.setFont(new Font("���� ���", Font.BOLD, 10));
					output.append(dto.getMessage() + "\n");

					int pos = output.getText().length();
					output.setCaretPosition(pos);

					output.setFont(new Font("���� ���", Font.BOLD, 15));
				}
				else {*/
					output.append(dto.getMessage() + "\n");

					int pos = output.getText().length();
					output.setCaretPosition(pos);
				//}
			}else if (dto.getCommand() == Info.NOTICE) {
				/*if (dto.getWhossend() == who.server) {

				output.setFont(new Font("���� ���", Font.BOLD, 10));
				output.append(dto.getMessage() + "\n");

				int pos = output.getText().length();
				output.setCaretPosition(pos);

				output.setFont(new Font("���� ���", Font.BOLD, 15));
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
			// ������ ����
			// JTextField���� �����κ�����
			// ���� ����
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
//���� ä���� ���� �����带 �������־�� ��