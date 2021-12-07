package sinamon;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
    
public class InfoEdit {
    
	private JFrame frame;
	private JTextField nameField;
	private JTextField idField;
	private JPasswordField pwField;
	private JPasswordField pwCheckField;
	private JComboBox placeBox;
	private final String place[]= {"�� ��", "�� ��","�� ��","�� ��","�� ��","�� �� ��","�� �� ��"};
	private JTextField nickField;
	
	
	/**
	 * Create the application.
	 */
	public InfoEdit(Object[] curr_user, db_connection connnection) {	//�������� �޾ƿ�
		initialize(curr_user, connnection);
	}
    
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Object[] curr_user, db_connection connection) {
		frame = new JFrame();
		frame.setTitle("ȸ������ ����");
		frame.setBounds(100, 100, 610, 440);
		//�ΰ�
		Toolkit tk = Toolkit.getDefaultToolkit();
        Image logo=tk.getImage(".\\Image\\logo.png");
        frame.setIconImage(logo);
		
		ImagePanel writePanel = new ImagePanel(new ImageIcon(".\\Image\\edit_info.png").getImage());
		frame.getContentPane().add(writePanel);
		writePanel.setLayout(null);
		
		nameField = new JTextField();							//�̸�
		nameField.setFont(new Font("�� M", Font.PLAIN, 17));
		nameField.setColumns(10);
		nameField.setBorder(null);
		nameField.setBounds(110, 121, 165, 25);
		writePanel.add(nameField);
	    
		idField = new JTextField();								//id
		idField.setFont(new Font("�� M", Font.PLAIN, 17));
		idField.setColumns(10);
		idField.setBorder(null);
		idField.setBounds(110, 180, 165, 25);
		writePanel.add(idField);
		
		pwField = new JPasswordField();							//pw
		pwField.setBorder(null);
		pwField.setBounds(110, 236, 165, 25);
		writePanel.add(pwField);
		
		pwCheckField = new JPasswordField();					//pw check
		pwCheckField.setBorder(null);
		pwCheckField.setBounds(110, 292, 165, 25);
		writePanel.add(pwCheckField);
		
		JComboBox comboBox = new JComboBox(place);			//���
		comboBox.setFont(new Font("�� M", Font.PLAIN, 17));
		comboBox.setBounds(377, 119, 175, 28);
		writePanel.add(comboBox);
		
		nickField = new JTextField();						//�г���
		nickField.setFont(new Font("�� M", Font.PLAIN, 17));
		nickField.setColumns(10);
		nickField.setBorder(null);
		nickField.setBounds(382, 180, 165, 25);
		writePanel.add(nickField);
		
		JButton cmpBtn = new JButton("");
	    cmpBtn.addActionListener(new ActionListener() { 
	                  @Override
	            public void actionPerformed(ActionEvent e) {
	                 String nameString = "'" + nameField.getText() + "'";      //�̸�
	                 String user_idString = "'" + idField.getText() + "'";      //���̵�
	                 String pwdString = "'" + pwField.getText() + "'";         //���
	                 String nicknameString = "'" + nickField.getText() + "'";   //�г���
	                 String placeString = "'" + comboBox.getSelectedItem().toString() + "'";      //��°�
	                 
	                 int home_id = 0;
	                 //����1 �߹�2 �Ĺ�3 ����4 �缺��5 ������6 ����7
	                 switch (placeString) {
	                   case "'�� ��'": {
	                      home_id = 1;
	                      break;
	                   }
	                   case "'�� ��'": {
	                      home_id = 2;
	                      break;
	                   }
	                   case "'�� ��'": {
	                      home_id = 3;
	                      break;
	                   }
	                   case "'�� ��'": {
	                      home_id = 4;
	                      break;
	                   }
	                   case "'�� �� ��'": {
	                      home_id = 5;
	                      break;
	                   }
	                   case "'�� �� ��'": {
	                      home_id = 6;
	                      break;
	                   }
	                   case "'�� ��'": {
	                      home_id = 7;
	                      break;
	                   }
	                   default:
	                      throw new IllegalArgumentException("Unexpected value: " + placeString);
	                 }
	                   
	                 int user = Integer.valueOf((String) curr_user[0]);
	                 connection.modify_user_info(user, nameString, user_idString, pwdString, nicknameString, home_id);
	                 frame.setVisible(false);   //â �ݱ�
	                   }
	               });
	      cmpBtn.setIcon(new ImageIcon(".\\Image\\cmp_m_btn.PNG"));
	      cmpBtn.setBounds(450, 317, 105, 38);
	      cmpBtn.setBorder(null);
	      writePanel.add(cmpBtn);

		JButton backSBtn = new JButton("");			//�ڷΰ��� ��ư 
		backSBtn.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);	//â �ݱ�
            }
        });
		backSBtn.setIcon(new ImageIcon(".\\Image\\back_m_btn.PNG"));
		backSBtn.setBounds(2, 1, 35, 32);
		backSBtn.setBorder(null);
		writePanel.add(backSBtn);
		
		
        frame.setVisible(true);		//frame ���̰�
	}
}
