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
		frame.setLocationRelativeTo(null);			//������ â ����� ��������
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//////////////////////////////////�α��� �г�///////////////////////////////////////
		//��ü �α��� ȭ�� �г�
		JPanel Login_Panel = new JPanel();
		Login_Panel.setBounds(0, 0, 584, 761);
		frame.getContentPane().add(Login_Panel);
		Login_Panel.setLayout(null);
		
		//ID Label
		JLabel ID_Label = new JLabel("ID");
		ID_Label.setBounds(107, 256, 56, 42);
		Login_Panel.add(ID_Label);
		ID_Label.setHorizontalAlignment(SwingConstants.CENTER);
		
		//ID �Է� �ʵ�
		ID_field = new JTextField(15);
		ID_field.setBounds(186, 257, 171, 42);
		Login_Panel.add(ID_field);
		
		//Password Label
		JLabel Password_Label = new JLabel("Password");
		Password_Label.setBounds(106, 305, 57, 42);
		Login_Panel.add(Password_Label);
		
		//Password �Է� �ʵ�
		JPasswordField Password_field = new JPasswordField(15);
		Password_field.setBounds(186, 306, 171, 42);
		Login_Panel.add(Password_field);
		
		//�α��� ��ư
		JButton Login_Button = new JButton("LOGIN");
		Login_Button.setBounds(395, 273, 69, 57);
		Login_Button.setHorizontalAlignment(SwingConstants.TRAILING);
		Login_Panel.add(Login_Button);
		
		//ȸ������ ��ư
		JButton CreateAccount_Button = new JButton("Create Accout");
		CreateAccount_Button.setBounds(221, 377, 142, 42);
		Login_Panel.add(CreateAccount_Button);
		////////////////////////////////////////////////////////////////////////////////
		
		//////////////////////////////////ȸ������ �г�/////////////////////////////////////
		//ȸ������ ��ü �г�
		JPanel Create_Account_Panel = new JPanel();
		Create_Account_Panel.setBounds(0, 0, 584, 761);
		frame.getContentPane().add(Create_Account_Panel);
		Create_Account_Panel.setLayout(null);
				
		//�г��� Label
		JLabel NickName_Label = new JLabel("\uB2C9\uB124\uC784");
		NickName_Label.setHorizontalAlignment(SwingConstants.CENTER);
		NickName_Label.setBounds(54, 275, 208, 15);
		Create_Account_Panel.add(NickName_Label);
		
		//�г��� �Է� �ʵ�
		NickName_Field = new JTextField();
		NickName_Field.setBounds(274, 272, 226, 21);
		Create_Account_Panel.add(NickName_Field);
		NickName_Field.setColumns(20);
		
		//���̵� Label
		JLabel InputID_Label = new JLabel("ID");
		InputID_Label.setHorizontalAlignment(SwingConstants.CENTER);
		InputID_Label.setBounds(64, 307, 198, 15);
		Create_Account_Panel.add(InputID_Label);
		
		//���̵� �Է� �ʵ�
		InputID_Field = new JTextField();
		InputID_Field.setBounds(274, 304, 226, 21);
		Create_Account_Panel.add(InputID_Field);
		InputID_Field.setColumns(10);
				
		//��й�ȣ Label
		JLabel Pswd_Label = new JLabel("\uBE44\uBC00\uBC88\uD638");
		Pswd_Label.setHorizontalAlignment(SwingConstants.CENTER);
		Pswd_Label.setBounds(54, 349, 208, 15);
		Create_Account_Panel.add(Pswd_Label);
				
		//��й�ȣ �Է� �ʵ�
		Pswd_Field = new JTextField();
		Pswd_Field.setBounds(274, 346, 226, 21);
		Create_Account_Panel.add(Pswd_Field);
		Pswd_Field.setColumns(20);
				
		//��й�ȣ Ȯ�� Label
		JLabel CheckPswd_Label = new JLabel("\uBE44\uBC00\uBC88\uD638 \uD655\uC778");
		CheckPswd_Label.setHorizontalAlignment(SwingConstants.CENTER);
		CheckPswd_Label.setBounds(54, 386, 208, 15);
		Create_Account_Panel.add(CheckPswd_Label);
				
		//��й�ȣ Ȯ�� �Է� �ʵ�
		Check_Pswd_Field = new JTextField();
		Check_Pswd_Field.setBounds(274, 383, 226, 21);
		Create_Account_Panel.add(Check_Pswd_Field);
		Check_Pswd_Field.setColumns(20);
				
		//������ Label
		JLabel Home_Label = new JLabel("\uC0AC\uB294 \uACF3(\uC815\uBB38, \uC911\uBB38, \uD6C4\uBB38, \uC11C\uBB38)");
		Home_Label.setHorizontalAlignment(SwingConstants.CENTER);
		Home_Label.setBounds(54, 428, 208, 15);
		Create_Account_Panel.add(Home_Label);
				
		//������ �Է� �ʵ�
		Home_Field = new JTextField();
		Home_Field.setBounds(274, 425, 226, 21);
		Create_Account_Panel.add(Home_Field);
		Home_Field.setColumns(20);
				
		//ȸ������ �Ϸ� ��ư
		JButton Finish_Btn = new JButton("\uD68C\uC6D0\uAC00\uC785 \uC644\uB8CC");
		Finish_Btn.setBounds(225, 498, 133, 48);
		Create_Account_Panel.add(Finish_Btn);
		
		Create_Account_Panel.setVisible(false);		//ù ȭ���� �α��� ȭ�鸸 ����.
		//////////////////////////////////////////////////////////////////////////////////////////
		
		Login_Button.addActionListener(new ActionListener() {		//�α��� ��ư Ŭ������ ��, �߻��ϴ� event ó��
					
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = "kksshh0612";		//�ϴ� ���� �־���� sample. ���� ���̵� ����� ������ �����س��� �� ������ �´��� Ȯ���� ����.
				String pass = "0000";
				if(id.equals(ID_field.getText())&&Arrays.equals(Password_field.getPassword(), pass.toCharArray())) {
					JOptionPane.showMessageDialog(null,  "���̵� ����� ��ġ�մϴ�");		//Ȯ���ϱ� ���� �޼���
					
					//���� ���� ȭ������ �̵�
					
					Login_Panel.setVisible(false);		//���� �α��� ȭ�� �Ⱥ��̰� ó��
				}
				else {
					JOptionPane.showMessageDialog(null, "ID �Ǵ� Password�� ��ġ���� �ʽ��ϴ�");
				}
						
			}
		});
		
		CreateAccount_Button.addActionListener(new ActionListener() {		//ȸ������ ��ư ������ ��, ȸ������ �������� �̵�
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Create_Account_Panel.setVisible(true);	//ȸ������ �гη� �̵�
				Login_Panel.setVisible(false);			//�α��� �г� �Ⱥ��̰� ó��
			}
		});
		
		Finish_Btn.addActionListener(new ActionListener() {		//ȸ������ �Ϸ� ��ư Ŭ������ �� event ó��
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//if() {		//����� �����ͺ��̽��� �ԷµǾ��ִ� �г��Ӱ� ���� ���� �ִ��� Ȯ���� ���ǹ�. ���߿� �ۼ��ϱ�
					if((Check_Pswd_Field.getText()).equals(Pswd_Field.getText())) {		//��й�ȣ�� ��й�ȣ Ȯ�� ���ڿ��� ������
						JOptionPane.showMessageDialog(null,  "ȸ�������� �Ϸ�Ǿ����ϴ�. �α����ϼ���");		//���⼭ �� �޼����� ��쵵�� �ߴµ�, ���� ȭ������ �Ѿ ����
						
						///////���⼭ �����ͺ��̽� ����
						
						Login_Panel.setVisible(true);				//�α��� �гη� �̵�
						Create_Account_Panel.setVisible(false);		//ȸ������ �г� �Ⱥ��̰� ó��
					}
					else {
						JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�. �ٽ� Ȯ���ϼ���");
					}
				//}
				
						
			}
		});
		
	}
	
}
