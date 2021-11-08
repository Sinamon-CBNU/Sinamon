package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.DropMode;

public class Login_Page {

	private JFrame frame;
	private JTextField ID_field;
	private JTextField NickName_Field;
	private JTextField InputID_Field;
	private JTextField Pswd_Field;
	private JTextField Check_Pswd_Field;
	private JTextField Home_Field;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_Page window = new Login_Page();	
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
	public Login_Page() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 800);		//width:600, height:800
		frame.setTitle("SiNaMon");
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);			//윈도우 창 가운데에 나오도록
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//////////////////////////////////로그인 패널///////////////////////////////////////
		//전체 로그인 화면 패널
		JPanel Login_Panel = new JPanel();
		Login_Panel.setBounds(0, 0, 584, 761);
		frame.getContentPane().add(Login_Panel);
		Login_Panel.setLayout(null);
		
		//ID Label
		JLabel ID_Label = new JLabel("ID");
		ID_Label.setBounds(107, 256, 56, 42);
		Login_Panel.add(ID_Label);
		ID_Label.setHorizontalAlignment(SwingConstants.CENTER);
		
		//ID 입력 필드
		ID_field = new JTextField(15);
		ID_field.setBounds(186, 257, 171, 42);
		Login_Panel.add(ID_field);
		
		//Password Label
		JLabel Password_Label = new JLabel("Password");
		Password_Label.setBounds(106, 305, 57, 42);
		Login_Panel.add(Password_Label);
		
		//Password 입력 필드
		JPasswordField Password_field = new JPasswordField(15);
		Password_field.setBounds(186, 306, 171, 42);
		Login_Panel.add(Password_field);
		
		//로그인 버튼
		JButton Login_Button = new JButton("LOGIN");
		Login_Button.setBounds(395, 273, 69, 57);
		Login_Button.setHorizontalAlignment(SwingConstants.TRAILING);
		Login_Panel.add(Login_Button);
		
		//회원가입 버튼
		JButton CreateAccount_Button = new JButton("Create Accout");
		CreateAccount_Button.setBounds(221, 377, 142, 42);
		Login_Panel.add(CreateAccount_Button);
		////////////////////////////////////////////////////////////////////////////////
		
		//////////////////////////////////회원가입 패널/////////////////////////////////////
		//회원가입 전체 패널
		JPanel Create_Account_Panel = new JPanel();
		Create_Account_Panel.setBounds(0, 0, 584, 761);
		frame.getContentPane().add(Create_Account_Panel);
		Create_Account_Panel.setLayout(null);
				
		//닉네임 Label
		JLabel NickName_Label = new JLabel("\uB2C9\uB124\uC784");
		NickName_Label.setHorizontalAlignment(SwingConstants.CENTER);
		NickName_Label.setBounds(54, 275, 208, 15);
		Create_Account_Panel.add(NickName_Label);
		
		//닉네임 입력 필드
		NickName_Field = new JTextField();
		NickName_Field.setBounds(274, 272, 226, 21);
		Create_Account_Panel.add(NickName_Field);
		NickName_Field.setColumns(20);
		
		//아이디 Label
		JLabel InputID_Label = new JLabel("ID");
		InputID_Label.setHorizontalAlignment(SwingConstants.CENTER);
		InputID_Label.setBounds(64, 307, 198, 15);
		Create_Account_Panel.add(InputID_Label);
		
		//아이디 입력 필드
		InputID_Field = new JTextField();
		InputID_Field.setBounds(274, 304, 226, 21);
		Create_Account_Panel.add(InputID_Field);
		InputID_Field.setColumns(10);
				
		//비밀번호 Label
		JLabel Pswd_Label = new JLabel("\uBE44\uBC00\uBC88\uD638");
		Pswd_Label.setHorizontalAlignment(SwingConstants.CENTER);
		Pswd_Label.setBounds(54, 349, 208, 15);
		Create_Account_Panel.add(Pswd_Label);
				
		//비밀번호 입력 필드
		Pswd_Field = new JTextField();
		Pswd_Field.setBounds(274, 346, 226, 21);
		Create_Account_Panel.add(Pswd_Field);
		Pswd_Field.setColumns(20);
				
		//비밀번호 확인 Label
		JLabel CheckPswd_Label = new JLabel("\uBE44\uBC00\uBC88\uD638 \uD655\uC778");
		CheckPswd_Label.setHorizontalAlignment(SwingConstants.CENTER);
		CheckPswd_Label.setBounds(54, 386, 208, 15);
		Create_Account_Panel.add(CheckPswd_Label);
				
		//비밀번호 확인 입력 필드
		Check_Pswd_Field = new JTextField();
		Check_Pswd_Field.setBounds(274, 383, 226, 21);
		Create_Account_Panel.add(Check_Pswd_Field);
		Check_Pswd_Field.setColumns(20);
				
		//거주지 Label
		JLabel Home_Label = new JLabel("\uC0AC\uB294 \uACF3(\uC815\uBB38, \uC911\uBB38, \uD6C4\uBB38, \uC11C\uBB38)");
		Home_Label.setHorizontalAlignment(SwingConstants.CENTER);
		Home_Label.setBounds(54, 428, 208, 15);
		Create_Account_Panel.add(Home_Label);
				
		//거주지 입력 필드
		Home_Field = new JTextField();
		Home_Field.setBounds(274, 425, 226, 21);
		Create_Account_Panel.add(Home_Field);
		Home_Field.setColumns(20);
				
		//회원가입 완료 버튼
		JButton Finish_Btn = new JButton("\uD68C\uC6D0\uAC00\uC785 \uC644\uB8CC");
		Finish_Btn.setBounds(225, 498, 133, 48);
		Create_Account_Panel.add(Finish_Btn);
		
		Create_Account_Panel.setVisible(false);		//첫 화면은 로그인 화면만 보임.
		//////////////////////////////////////////////////////////////////////////////////////////
		
		Login_Button.addActionListener(new ActionListener() {		//로그인 버튼 클릭했을 때, 발생하는 event 처리
					
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = "kksshh0612";		//일단 내가 넣어놓은 sample. 이제 아이디 비번을 여러개 저장해놓고 그 정보와 맞는지 확인할 것임.
				String pass = "0000";
				if(id.equals(ID_field.getText())&&Arrays.equals(Password_field.getPassword(), pass.toCharArray())) {
					JOptionPane.showMessageDialog(null,  "아이디 비번이 일치합니다");		//확인하기 위한 메세지
					
					//다음 메인 화면으로 이동
					
					Login_Panel.setVisible(false);		//현재 로그인 화면 안보이게 처리
				}
				else {
					JOptionPane.showMessageDialog(null, "ID 또는 Password가 일치하지 않습니다");
				}
						
			}
		});
		
		CreateAccount_Button.addActionListener(new ActionListener() {		//회원가입 버튼 눌렀을 때, 회원가입 페이지로 이동
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Create_Account_Panel.setVisible(true);	//회원가입 패널로 이동
				Login_Panel.setVisible(false);			//로그인 패널 안보이게 처리
			}
		});
		
		Finish_Btn.addActionListener(new ActionListener() {		//회원가입 완료 버튼 클릭했을 때 event 처리
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//if() {		//여기는 데이터베이스에 입력되어있는 닉네임과 같은 것이 있는지 확인할 조건문. 나중에 작성하기
					if((Check_Pswd_Field.getText()).equals(Pswd_Field.getText())) {		//비밀번호와 비밀번호 확인 문자열이 같으면
						JOptionPane.showMessageDialog(null,  "회원가입이 완료되었습니다. 로그인하세요");		//여기서 이 메세지를 띄우도록 했는데, 다음 화면으로 넘어갈 것임
						
						///////여기서 데이터베이스 저장
						
						Login_Panel.setVisible(true);				//로그인 패널로 이동
						Create_Account_Panel.setVisible(false);		//회원가입 패널 안보이게 처리
					}
					else {
						JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다. 다시 확인하세요");
					}
				//}
				
						
			}
		});
		
	}
	
}
