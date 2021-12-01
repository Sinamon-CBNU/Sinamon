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
	private JTextArea titleArea;	
	private JTextField nameField;
	private JComboBox placeBox;
	private final String place[]= {"�� ��", "�� ��","�� ��","�� ��","�� ��","�� �� ��","�� �� ��"};
	private JTextField idField;
	private JTextField pwField;
	private JTextField pwCheckField;
	private JTextField nickNameField;
	
	/**
	 * Create the application.
	 */
	public InfoEdit(Object[] curr_user, db_connection connection) {	//�������� �޾ƿ�
		initialize(curr_user, connection);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Object[] curr_user, db_connection connection) {
		frame = new JFrame();
		frame.setTitle("ȸ������ ����");
		frame.setBounds(100, 100, 640, 440);
		
		ImagePanel writePanel = new ImagePanel(new ImageIcon(".\\Image\\edit_info.png").getImage());
		frame.getContentPane().add(writePanel);
		writePanel.setLayout(null);
		
		nameField = new JTextField(); //�̸�
		nameField.setFont(new Font("�� M", Font.PLAIN, 17));
		nameField.setColumns(10);
		nameField.setBorder(null);
		nameField.setBounds(110, 121, 165, 25);
		writePanel.add(nameField);
		
		idField = new JTextField(); //id
		idField.setFont(new Font("�� M", Font.PLAIN, 17));
		idField.setColumns(10);
		idField.setBorder(null);
		idField.setBounds(110, 180, 165, 25);
		writePanel.add(idField);
		
		pwField = new JPasswordField(); //pw
		pwField.setBorder(null);
		pwField.setBounds(110, 236, 165, 25);
		writePanel.add(pwField);
		
		pwCheckField = new JPasswordField(); //pw check
		pwCheckField.setBorder(null);
		pwCheckField.setBounds(110, 292, 165, 25);
		writePanel.add(pwCheckField);
		
		nickNameField = new JTextField();					//�г���
		nickNameField.setFont(new Font("�� M", Font.PLAIN, 17));
		nickNameField.setColumns(10);
		nickNameField.setBorder(null);
		nickNameField.setBounds(382, 180, 165, 25);
		writePanel.add(nickNameField);
	
		
		JComboBox comboBox = new JComboBox(place);			//���
		comboBox.setFont(new Font("�� M", Font.PLAIN, 17));
		comboBox.setBounds(377, 119, 175, 28);
		writePanel.add(comboBox);
		
		JButton enrollBtn = new JButton("");				//�ۼ� �Ϸ� ��ư
		enrollBtn.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
            	String nameString = "'" + nameField.getText() + "'";		//�̸�
            	String user_idString = "'" + idField.getText() + "'";		//���̵�
            	String pwdString = "'" + pwField.getText() + "'";			//���
            	String nicknameString = "'" + nickNameField.getText() + "'";	//�г���
            	String placeString = "'" + comboBox.getSelectedItem().toString() + "'";		//��°�
            	
            	int home_id = 0;
            	//����1 �߹�2 �Ĺ�3 ����4 �缺��5 ������6 ����7
            	switch (placeString) {
            	case "'정 문'": {
					home_id = 1;
					break;
				}
				case "'중 문'": {
					home_id = 2;
					break;
				}
				case "'후 문'": {
					home_id = 3;
					break;
				}
				case "'서 문'": {
					home_id = 4;
					break;
				}
				case "'양 성 재'": {
					home_id = 5;
					break;
				}
				case "'양 진 재'": {
					home_id = 6;
					break;
				}
				case "'본 관'": {
					home_id = 7;
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + placeString);
				}
            connection.modify_user_info(home_id, placeString, placeString)
        		frame.setVisible(false);	//â �ݱ�
            }
        });
		enrollBtn.setIcon(new ImageIcon(".\\Image\\cmp_btn.PNG"));	//edit complete button
		enrollBtn.setBounds(337, 253, 78, 24);
		enrollBtn.setBorder(null);
		writePanel.add(enrollBtn);
		
		JButton backSBtn = new JButton("");			//�ڷΰ��� ��ư 
		backSBtn.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);	//â �ݱ�
            }
        });
		backSBtn.setIcon(new ImageIcon(".\\Image\\back_s_btn.PNG"));
		backSBtn.setBounds(3, 1, 20, 23);
		backSBtn.setBorder(null);
		writePanel.add(backSBtn);
		
        frame.setVisible(true);		//frame ���̰�
	}
}
