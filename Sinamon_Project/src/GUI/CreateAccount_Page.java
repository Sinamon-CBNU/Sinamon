package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class CreateAccount_Page {

	protected JFrame frame;
	private JTextField NickName_Field;
	private JTextField Pswd_Field;
	private JTextField Check_Pswd_Field;
	private JTextField Home_Field;
	private JTextField InputID_Field;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccount_Page window = new CreateAccount_Page();
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
	public CreateAccount_Page() {
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//창 꺼지면 프로그램도 종료.
		frame.getContentPane().setLayout(null);
		
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
		
		JLabel InputID_Label = new JLabel("ID");
		InputID_Label.setHorizontalAlignment(SwingConstants.CENTER);
		InputID_Label.setBounds(64, 307, 198, 15);
		Create_Account_Panel.add(InputID_Label);
		
		InputID_Field = new JTextField();
		InputID_Field.setBounds(274, 304, 226, 21);
		Create_Account_Panel.add(InputID_Field);
		InputID_Field.setColumns(10);
		
		Finish_Btn.addActionListener(new ActionListener() {		//회원가입 버튼 클릭했을 때 event 처리
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//if() {		//여기는 데이터베이스에 입력한 닉네임과 같은 것이 있는지 확인할 조건문. 나중에 작성하기
					if((Check_Pswd_Field.getText()).equals(Pswd_Field.getText())) {		//비밀번호와 비밀번호 확인 필드 문자열이 같지 않으면
						JOptionPane.showMessageDialog(null,  "회원가입이 완료되었습니다. 로그인하세요");		//여기서 이 메세지를 띄우도록 했는데, 다음 화면으로 넘어갈 것임
						
						Create_Account_Panel.setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다. 다시 확인하세요");
					}
				//}
				
						
			}
		});
		
		
		
		
		
		
	}
}
