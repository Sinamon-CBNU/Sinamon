import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class WriteRev {

	private JFrame frame;
	private JTextArea titleArea;	
	private JTextField timeField;
	private JComboBox placeBox;
	private final String place[]= {"�� ��", "�� ��","�� ��","�� ��","�� ��","�� �� ��","�� �� ��"};
	
	/**
	 * Create the application.
	 */
	public WriteRev(Object[] curr_user, db_connection connection) {
		initialize(curr_user, connection);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Object[] curr_user, db_connection connection) {
		frame = new JFrame();
		frame.setTitle("��� ���ϱ�");
		frame.setBounds(100, 100, 460, 340);
		//frame �ΰ� ������
		Toolkit tk = Toolkit.getDefaultToolkit();
        Image logo=tk.getImage(".\\Image\\logo.png");
        frame.setIconImage(logo);
        
		//db_connection connection = new db_connection();
		
		ImagePanel writePanel = new ImagePanel(new ImageIcon(".\\Image\\rev.png").getImage());
		frame.getContentPane().add(writePanel);
		writePanel.setLayout(null);
		
		timeField = new JTextField();						//������ �ð�
		timeField.setFont(new Font("�� m", Font.PLAIN, 17));
		timeField.setBounds(87, 121, 320, 24);
		timeField.setBorder(null);
		writePanel.add(timeField);
		timeField.setColumns(10);
		
		JComboBox comboBox = new JComboBox(place);			//���
		comboBox.setFont(new Font("�� m", Font.PLAIN, 15));
		comboBox.setBounds(83, 80, 330, 25);
		writePanel.add(comboBox);
		
		JTextArea memoArea = new JTextArea();				//����
		memoArea.setLineWrap(true);
		memoArea.setFont(new Font("�� m", Font.PLAIN, 17));
		memoArea.setBounds(87, 164, 320, 69);
		writePanel.add(memoArea);
		
		JButton revCmpBtn = new JButton("");				//��� ��ư
		revCmpBtn.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
            	String timeString = "'" + timeField.getText() + "'";
            	String placeString = "'" + comboBox.getSelectedItem().toString() + "'";
            	String memoString = "'" + memoArea.getText() + "'";
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
            
            	if(timeString.equals("''") || memoString.equals("''")) {
            		frame.setVisible(false);
            	}
            	else {
            		frame.setVisible(false);
            	}
            }
        });
		revCmpBtn.setIcon(new ImageIcon(".\\Image\\rev_cmp_btn.PNG"));
		revCmpBtn.setBounds(264, 253, 155, 24);
		revCmpBtn.setBorder(null);
		writePanel.add(revCmpBtn);
		
		JButton backSBtn = new JButton("");			//�ڷΰ��� ��ư 
		backSBtn.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
		backSBtn.setIcon(new ImageIcon(".\\Image\\back_s_btn.PNG"));
		backSBtn.setBounds(0, 0, 24, 28);
		backSBtn.setBorder(null);
		writePanel.add(backSBtn);
	
        frame.setVisible(true);
	}
}
