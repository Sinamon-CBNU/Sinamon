/*파일 이미지 경로 설정이 내 데탑의 경로로 되어있기 때문에 이 파일을 열고 싶으면 파일 경로 설정 다 바꿔야됨*/
/*현재경료를 이용해서 ./이런식으로 하면 Design tool 사용이 안됨*/

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Sinamon {
	
	//check 임시 ID,PW
	private final String ID="Hello";
	private final String PASS="1234";
	
	String place[]= {"정 문", "중 문","서 문","후 문","본 관","양 성 재","양 진 재"};
	
	private JFrame frame;
	private JTextField idField;
	private JPasswordField pwField;
	private JPanel currPanel;	//현재 패널
	private JTextField joinIdField;
	private JTextField joinNameField;
	private JTextField joinPwField;
	private JTextField joinPwCheckField;
	private JTextField joinNickField;
	private JButton enrollBtn;
	private JButton backBtn;
	private JButton clickBtn;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sinamon window = new Sinamon();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Sinamon() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 960, 540);
		frame.setPreferredSize(new Dimension(960,540));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//화면을 닫으면 프로그램 종료
		frame.setResizable(false); 		//크기 고정

		ImagePanel boardPanel = new ImagePanel(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\board.png").getImage());
		frame.getContentPane().add(boardPanel);
		ImagePanel choicePanel = new ImagePanel(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\choice.png").getImage());
		frame.getContentPane().add(choicePanel);
		ImagePanel joinPanel = new ImagePanel(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\join.png").getImage());
		frame.getContentPane().add(joinPanel);
		ImagePanel loginPanel = new ImagePanel(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\login.png").getImage());
		frame.getContentPane().add(loginPanel);
		
		/****************************boardPanel (게시판 패널)*****************************************/
		
		boardPanel.setBounds(0, 10, 960, 540);
		
		String[] heading=new String[] {"닉네임","음  식","시간"};
		//예시
		Object[][] data=new Object[][] {
			{"초코왕자","푸라닭","16:00"},
			{"정현","엽떡","12:00"},
			{"호성","곱창전골","20:00"},
		};
		boardPanel.setLayout(null);
		
		JTable table=new JTable(data,heading);
		table.setPreferredScrollableViewportSize(new Dimension(700,600));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(246, 108, 687, 422);
		boardPanel.add(scrollPane);
		
		
		/****************************Choice panel (시나 음식 시나 생필품)*********************************************/
		
		choicePanel.setBounds(0, 10, 960, 540);
		choicePanel.setLayout(null);
		
		JButton foodBtn = new JButton("");
		foodBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);
				boardPanel.setVisible(true);
				currPanel = boardPanel;
			}	
		});
		foodBtn.setBounds(134, 124, 307, 309);
		foodBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\Ch_food.PNG"));
		choicePanel.add(foodBtn);
		
		JButton necBtn = new JButton("");
		necBtn.setBounds(528, 124, 307, 309);
		necBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\Ch_nec.PNG"));
		choicePanel.add(necBtn);
		
		/**********************************Join panel (회원가입 패널)******************************************/
		
		joinPanel.setBounds(0, 0, 960, 540);
		joinPanel.setLayout(null);
		
		joinIdField = new JTextField();
		joinIdField.setBounds(235, 175, 199, 25);
		joinIdField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		joinIdField.setColumns(10);
		joinIdField.setBorder(null);
		joinPanel.add(joinIdField);
		
		joinNameField = new JTextField();
		joinNameField.setBounds(235, 232, 199, 25);
		joinNameField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		joinNameField.setColumns(10);
		joinNameField.setBorder(null);
		joinPanel.add(joinNameField);
		
		joinPwField = new JTextField();
		joinPwField.setBounds(235, 287, 199, 25);
		joinPwField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		joinPwField.setColumns(10);
		joinPwField.setBorder(null);
		joinPanel.add(joinPwField);
		
		joinPwCheckField = new JTextField();
		joinPwCheckField.setBounds(235, 338, 199, 25);
		joinPwCheckField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		joinPwCheckField.setColumns(10);
		joinPwCheckField.setBorder(null);
		joinPanel.add(joinPwCheckField);
		
		JComboBox comboBox = new JComboBox(place);
		comboBox.setBounds(583, 225, 106, 27);
		//comboBox.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		
		comboBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//사는 곳 정보 저장
			}
		});
		joinPanel.add(comboBox);
		
		joinNickField = new JTextField();
		joinNickField.setBounds(583, 286, 207, 27);
		joinNickField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		joinNickField.setColumns(10);
		joinNickField.setBorder(null);
		joinPanel.add(joinNickField);
		
		enrollBtn = new JButton("");
		enrollBtn.setBounds(419, 426, 122, 49);
		enrollBtn.setBorder(null);
		enrollBtn.addActionListener(new ActionListener(){	

			@Override
			public void actionPerformed(ActionEvent e) {	//회원가입 데이터 저장
				try {
					//getText()로 데이터 저장
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null,"You Failed to Enroll");
				}
				JOptionPane.showMessageDialog(null,"회원가입을 축하합니다!");
				currPanel.setVisible(false);
				loginPanel.setVisible(true);
				currPanel = (JPanel) loginPanel;				
			}		
		});
		enrollBtn.setPressedIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\enroll_click_btn.PNG"));
		joinPanel.add(enrollBtn);
		enrollBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\enroll_btn.PNG"));
		
		backBtn = new JButton("");
		backBtn.setBounds(5, 10, 49, 49);
		backBtn.setBorder(null);
		backBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);
				loginPanel.setVisible(true);
				currPanel = (JPanel) loginPanel;				
			}		
		});
		backBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\back_btn.PNG"));
		joinPanel.add(backBtn);
		
		/***************************************login panel(로그인 패널)***************************************/
		
		loginPanel.setBounds(0, 10, 960, 540);
		loginPanel.setLayout(null);
		currPanel=loginPanel;
		frame.setSize(loginPanel.getDimension());
		frame.setPreferredSize(loginPanel.getDimension());
		
		JButton loginBtn = new JButton("");			//loginBtn
		loginBtn.setBounds(424, 291, 131, 51);
		loginBtn.setBorder(null);
		loginBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(ID.equals(idField.getText()) && PASS.equals(pwField.getText())){
					currPanel.setVisible(false);
					choicePanel.setVisible(true);
					currPanel = choicePanel;
				}
				else{
					JOptionPane.showMessageDialog(null,"You Failed to Log In");
				}
			}
		});
		
		loginBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\login_btn.PNG"));
		loginBtn.setPressedIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\login_click_btn.PNG"));
		loginPanel.add(loginBtn);
		
		JButton joinBtn = new JButton("");		//joinBtn
		joinBtn.setBounds(204, 374, 131, 51);
		joinBtn.setBorder(null);
		joinBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);
				joinPanel.setVisible(true);
				currPanel = joinPanel;
			}
		});
		joinBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\join_btn.PNG"));
		joinBtn.setPressedIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\join_click_btn.PNG"));
		loginPanel.add(joinBtn);
		
		idField = new JTextField();
		idField.setBounds(183, 272, 199, 24);
		idField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		loginPanel.add(idField);
		idField.setColumns(10);
		idField.setBorder(null);
		
		pwField = new JPasswordField();
		pwField.setBounds(183, 322, 198, 26);
		pwField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		loginPanel.add(pwField);
		pwField.setBorder(null);
		
		joinPanel.setVisible(false);
		boardPanel.setVisible(false);
		choicePanel.setVisible(false);
	}

}
