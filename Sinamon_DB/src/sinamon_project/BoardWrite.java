package sinamon_project;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BoardWrite {

	private JFrame frame;
	private JTextArea titleArea;	
	private JTextField timeField;
	private JComboBox placeBox;
	private final String place[]= {"�� ��", "�� ��","�� ��","�� ��","�� ��","�� �� ��","�� �� ��"};
	
	/**
	 * Create the application.
	 */
	public BoardWrite(String board_name, Object[] curr_user) {
		initialize(board_name, curr_user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String board_name, Object[] curr_user) {
		frame = new JFrame();
		frame.setTitle("�Խñ� ����");
		frame.setBounds(100, 100, 460, 340);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		db_connection connection = new db_connection();
		
		ImagePanel writePanel = new ImagePanel(new ImageIcon("C:\\Users\\kkssh\\Desktop\\eclipse-workspace\\Sinamon_DB\\Image\\write.png").getImage());
		frame.getContentPane().add(writePanel);
		writePanel.setLayout(null);
		
		timeField = new JTextField();						//������ �ð�
		timeField.setFont(new Font("HY����M", Font.PLAIN, 15));
		timeField.setBounds(87, 121, 320, 24);
		timeField.setBorder(null);
		writePanel.add(timeField);
		timeField.setColumns(10);
		
		JComboBox comboBox = new JComboBox(place);			//��� 
		comboBox.setFont(new Font("HY����M", Font.PLAIN, 15));
		comboBox.setBounds(87, 80, 320, 23);
		writePanel.add(comboBox);
		
		JTextArea titleArea = new JTextArea();				//����
		titleArea.setBounds(87, 164, 320, 69);
		writePanel.add(titleArea);
		
		JButton enrollBtn = new JButton("");
		enrollBtn.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
            	String timeString = "'" + timeField.getText() + "'";
            	String placeString = "'" + comboBox.getSelectedItem().toString() + "'";
            	String titleString = "'" + titleArea.getText() + "'";
            	String nicknameString = "'" + curr_user[2] + "'";		//�г���
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
            
            	//insert_board_contents(String board_name, int home_id, String time, String title, String nickname)
            	if(timeString.equals("''") || titleString.equals("''")) {
            		frame.setVisible(false);
            	}
            	else {
            		connection.insert_board_contents(board_name, home_id, timeString, titleString, nicknameString);
            		frame.setVisible(false);
            	}
            }
        });
		enrollBtn.setIcon(new ImageIcon("C:\\Users\\kkssh\\Desktop\\eclipse-workspace\\Sinamon_DB\\Image\\cmp_btn.PNG"));
		enrollBtn.setBounds(337, 253, 78, 24);
		enrollBtn.setBorder(null);
		writePanel.add(enrollBtn);
		
		JButton backSBtn = new JButton("");			//�ڷΰ��� 
		backSBtn.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
		backSBtn.setIcon(new ImageIcon("C:\\Users\\kkssh\\Desktop\\eclipse-workspace\\Sinamon_DB\\Image\\back_s_btn.PNG"));
		backSBtn.setBounds(4, 5, 20, 23);
		backSBtn.setBorder(null);
		writePanel.add(backSBtn);
	
		
 
        frame.setVisible(true);
	}

}
