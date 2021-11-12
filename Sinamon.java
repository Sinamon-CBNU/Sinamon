/*파일 이미지 경로 설정이 내 데탑의 경로로 되어있기 때문에 이 파일을 열고 싶으면 파일 경로 설정 다 바꿔야됨*/
/*현재경료를 이용해서 ./이런식으로 하면 Design tool 사용이 안됨*/

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
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;

public class Sinamon {
	
	//check 임시 ID,PW
	private final String ID="Hello";
	private final String PASS="1234";
	
	String place[]= {"정 문", "중 문","학 교","서 문","후 문"};
	
	private JFrame frame;
	private JTextField idField;
	private JPasswordField pwField;
	private JPanel currPanel;
	private JPanel loginPanel;
	private JTextField joinIdField;
	private JTextField joinNameField;
	private JTextField joinPwField;
	private JTextField joinPwCheckField;
	private JTextField joinNickField;
	private JButton enrollBtn;
	private JButton clickBtn;

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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/****************************BulletinPanel (게시판 패널)*****************************************/
		ImagePanel bulletinPanel = new ImagePanel(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\JAVAprac\\Image\\Bulletin_page.png").getImage());
		frame.getContentPane().add(bulletinPanel);
		bulletinPanel.setBounds(0, 10, 960, 540);
		bulletinPanel.setLayout(null);
		
		/*****************************Place Panel (장소 선택 패널)******************************************/
		ImagePanel placePanel = new ImagePanel(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\JAVAprac\\Image\\Place_page.png").getImage());
		frame.getContentPane().add(placePanel);
		placePanel.setBounds(0, 10, 960, 540);
		placePanel.setLayout(null);
		//선택 버튼
		clickBtn = new JButton("click");
		clickBtn.addActionListener(new ActionListener() {	//반응
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);
				bulletinPanel.setVisible(true);
				currPanel = bulletinPanel;
			}
		});
		clickBtn.setBounds(141, 118, 97, 23);
		placePanel.add(clickBtn);
		
		/****************************Choice panel (시나 음식 시나 생필품)*********************************************/
		ImagePanel choicePanel = new ImagePanel(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\JAVAprac\\Image\\choice_page.png").getImage());
		frame.getContentPane().add(choicePanel);
		choicePanel.setBounds(0, 10, 960, 540);
		choicePanel.setLayout(null);
		
		JButton foodBtn = new JButton("");
		foodBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);
				placePanel.setVisible(true);
				currPanel = placePanel;
			}	
		});
		foodBtn.setBounds(134, 132, 299, 302);
		foodBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\JAVAprac\\Image\\Ch_food.PNG"));
		choicePanel.add(foodBtn);
		
		JButton necBtn = new JButton("");
		necBtn.setBounds(528, 132, 299, 301);
		necBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\JAVAprac\\Image\\Ch_nec.PNG"));
		choicePanel.add(necBtn);
		
		/**********************************Join panel (회원가입 패널)******************************************/
		ImagePanel joinPanel = new ImagePanel(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\JAVAprac\\Image\\join_page.png").getImage());
		frame.getContentPane().add(joinPanel);
		joinPanel.setBounds(0, 10, 960, 540);
		joinPanel.setLayout(null);
		
		
		joinPanel.setBounds(0, 10, 960, 540);
		
		joinIdField = new JTextField();
		joinIdField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		joinIdField.setBounds(230, 207, 207, 27);
		joinIdField.setColumns(10);
		joinIdField.setBorder(null);
		joinPanel.add(joinIdField);
		
		joinNameField = new JTextField();
		joinNameField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		joinNameField.setBounds(230, 263, 207, 27);
		joinNameField.setColumns(10);
		joinNameField.setBorder(null);
		joinPanel.add(joinNameField);
		
		joinPwField = new JTextField();
		joinPwField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		joinPwField.setBounds(230, 319, 207, 27);
		joinPwField.setColumns(10);
		joinPwField.setBorder(null);
		joinPanel.add(joinPwField);
		
		joinPwCheckField = new JTextField();
		joinPwCheckField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		joinPwCheckField.setColumns(10);
		joinPwCheckField.setBounds(230, 372, 207, 27);
		joinPwCheckField.setBorder(null);
		joinPanel.add(joinPwCheckField);
		
		JComboBox comboBox = new JComboBox();
		//comboBox.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uC815 \uBB38", "\uC911 \uBB38", "\uD559 \uAD50", "\uC11C \uBB38", "\uD6C4 \uBB38"}));
		
		comboBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//사는 곳 정보 저장
			}
		});
		comboBox.setBounds(613, 261, 106, 27);
		joinPanel.add(comboBox);
		
		joinNickField = new JTextField();
		joinNickField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		joinNickField.setColumns(10);
		joinNickField.setBounds(613, 319, 207, 27);
		joinNickField.setBorder(null);
		joinPanel.add(joinNickField);
		
		enrollBtn = new JButton("");
		enrollBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);
				loginPanel.setVisible(true);
				currPanel = loginPanel;				
			}		
		});
		enrollBtn.setPressedIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\JAVAprac\\Image\\enroll_btn.PNG"));
		enrollBtn.setBounds(423, 463, 116, 47);
		joinPanel.add(enrollBtn);
		enrollBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\JAVAprac\\Image\\enroll_click_btn.PNG"));
		
		/***************************************login panel(로그인 패널)***************************************/
		ImagePanel loginPanel = new ImagePanel(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\JAVAprac\\Image\\login_page.png").getImage());
		loginPanel.setBounds(0, 10, 960, 540);
		loginPanel.setLayout(null);
		frame.getContentPane().add(loginPanel);
		currPanel=loginPanel;
		frame.setSize(loginPanel.getDimension());
		frame.setPreferredSize(loginPanel.getDimension());
		
		JButton loginBtn = new JButton("");			//loginBtn
		loginBtn.setBounds(714, 243, 122, 53);
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
		
		loginBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\JAVAprac\\Image\\logIN_btn.PNG"));
		loginPanel.add(loginBtn);
		
		JButton joinBtn = new JButton("");
		joinBtn.setBounds(532, 347, 122, 42);
		joinBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);
				joinPanel.setVisible(true);
				currPanel = joinPanel;
			}
		});
		joinBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\JAVAprac\\Image\\join_btn.PNG"));
		loginPanel.add(joinBtn);
		
		idField = new JTextField();
		idField.setBounds(492, 239, 199, 24);
		idField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		loginPanel.add(idField);
		idField.setColumns(10);
		idField.setBorder(null);
		
		pwField = new JPasswordField();
		pwField.setBounds(492, 283, 198, 26);
		pwField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		loginPanel.add(pwField);
		pwField.setBorder(null);
				
		joinPanel.setVisible(false);
		choicePanel.setVisible(false);
		placePanel.setVisible(false);
		bulletinPanel.setVisible(false);
		
	}

	private ActionListener ActionListener() {
		// TODO Auto-generated method stub
		return null;
	}
}
