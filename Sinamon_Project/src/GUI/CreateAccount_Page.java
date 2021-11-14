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
		frame.setLocationRelativeTo(null);			//������ â ����� ��������
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//â ������ ���α׷��� ����.
		frame.getContentPane().setLayout(null);
		
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
		
		JLabel InputID_Label = new JLabel("ID");
		InputID_Label.setHorizontalAlignment(SwingConstants.CENTER);
		InputID_Label.setBounds(64, 307, 198, 15);
		Create_Account_Panel.add(InputID_Label);
		
		InputID_Field = new JTextField();
		InputID_Field.setBounds(274, 304, 226, 21);
		Create_Account_Panel.add(InputID_Field);
		InputID_Field.setColumns(10);
		
		Finish_Btn.addActionListener(new ActionListener() {		//ȸ������ ��ư Ŭ������ �� event ó��
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//if() {		//����� �����ͺ��̽��� �Է��� �г��Ӱ� ���� ���� �ִ��� Ȯ���� ���ǹ�. ���߿� �ۼ��ϱ�
					if((Check_Pswd_Field.getText()).equals(Pswd_Field.getText())) {		//��й�ȣ�� ��й�ȣ Ȯ�� �ʵ� ���ڿ��� ���� ������
						JOptionPane.showMessageDialog(null,  "ȸ�������� �Ϸ�Ǿ����ϴ�. �α����ϼ���");		//���⼭ �� �޼����� ��쵵�� �ߴµ�, ���� ȭ������ �Ѿ ����
						
						Create_Account_Panel.setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�. �ٽ� Ȯ���ϼ���");
					}
				//}
				
						
			}
		});
		
		
		
		
		
		
	}
}
