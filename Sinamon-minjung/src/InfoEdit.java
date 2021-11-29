import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
	public InfoEdit(Object[] curr_user) {	//�������� �޾ƿ�
		initialize(curr_user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Object[] curr_user) {
		frame = new JFrame();
		frame.setTitle("ȸ������ ����");
		frame.setBounds(100, 100, 460, 340);
		//db_connection connection = new db_connection();
		
		ImagePanel writePanel = new ImagePanel(new ImageIcon(".\\Image\\edit_info.png").getImage());
		frame.getContentPane().add(writePanel);
		writePanel.setLayout(null);
		
		nameField = new JTextField();						//�̸�
		nameField.setFont(new Font("HY����M", Font.PLAIN, 15));
		nameField.setBounds(79, 89, 130, 22);
		nameField.setBorder(null);
		writePanel.add(nameField);
		nameField.setColumns(10);
		
		idField = new JTextField();							//id
		idField.setFont(new Font("HY����M", Font.PLAIN, 15));
		idField.setColumns(10);
		idField.setBorder(null);
		idField.setBounds(79, 134, 130, 22);
		writePanel.add(idField);
		
		pwField = new JTextField();							//pw			
		pwField.setFont(new Font("HY����M", Font.PLAIN, 15));
		pwField.setColumns(10);
		pwField.setBorder(null);
		pwField.setBounds(79, 176, 130, 22);
		writePanel.add(pwField);
		
		pwCheckField = new JTextField();					//pw check
		pwCheckField.setFont(new Font("HY����M", Font.PLAIN, 15));
		pwCheckField.setColumns(10);
		pwCheckField.setBorder(null);
		pwCheckField.setBounds(79, 218, 130, 22);
		writePanel.add(pwCheckField);
		
		nickNameField = new JTextField();					//�г���
		nickNameField.setFont(new Font("HY����M", Font.PLAIN, 15));
		nickNameField.setColumns(10);
		nickNameField.setBorder(null);
		nickNameField.setBounds(285, 134, 128, 22);
		writePanel.add(nickNameField);
	
		
		JComboBox comboBox = new JComboBox(place);			//���
		comboBox.setFont(new Font("HY����M", Font.PLAIN, 15));
		comboBox.setBounds(282, 89, 133, 23);
		writePanel.add(comboBox);
		
		JButton enrollBtn = new JButton("");				//�ۼ� �Ϸ� ��ư
		enrollBtn.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	String placeString = "'" + comboBox.getSelectedItem().toString() + "'";
            	
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
