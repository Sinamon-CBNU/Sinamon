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
	private final String place[]= {"정 문", "중 문","서 문","후 문","본 관","양 성 재","양 진 재"};
	private JTextField idField;
	private JTextField pwField;
	private JTextField pwCheckField;
	private JTextField nickNameField;
	
	/**
	 * Create the application.
	 */
	public InfoEdit(Object[] curr_user) {	//유저정보 받아옴
		initialize(curr_user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Object[] curr_user) {
		frame = new JFrame();
		frame.setTitle("회원정보 수정");
		frame.setBounds(100, 100, 460, 340);
		//db_connection connection = new db_connection();
		
		ImagePanel writePanel = new ImagePanel(new ImageIcon(".\\Image\\edit_info.png").getImage());
		frame.getContentPane().add(writePanel);
		writePanel.setLayout(null);
		
		nameField = new JTextField();						//이름
		nameField.setFont(new Font("HY엽서M", Font.PLAIN, 15));
		nameField.setBounds(79, 89, 130, 22);
		nameField.setBorder(null);
		writePanel.add(nameField);
		nameField.setColumns(10);
		
		idField = new JTextField();							//id
		idField.setFont(new Font("HY엽서M", Font.PLAIN, 15));
		idField.setColumns(10);
		idField.setBorder(null);
		idField.setBounds(79, 134, 130, 22);
		writePanel.add(idField);
		
		pwField = new JTextField();							//pw			
		pwField.setFont(new Font("HY엽서M", Font.PLAIN, 15));
		pwField.setColumns(10);
		pwField.setBorder(null);
		pwField.setBounds(79, 176, 130, 22);
		writePanel.add(pwField);
		
		pwCheckField = new JTextField();					//pw check
		pwCheckField.setFont(new Font("HY엽서M", Font.PLAIN, 15));
		pwCheckField.setColumns(10);
		pwCheckField.setBorder(null);
		pwCheckField.setBounds(79, 218, 130, 22);
		writePanel.add(pwCheckField);
		
		nickNameField = new JTextField();					//닉네임
		nickNameField.setFont(new Font("HY엽서M", Font.PLAIN, 15));
		nickNameField.setColumns(10);
		nickNameField.setBorder(null);
		nickNameField.setBounds(285, 134, 128, 22);
		writePanel.add(nickNameField);
	
		
		JComboBox comboBox = new JComboBox(place);			//장소
		comboBox.setFont(new Font("HY엽서M", Font.PLAIN, 15));
		comboBox.setBounds(282, 89, 133, 23);
		writePanel.add(comboBox);
		
		JButton enrollBtn = new JButton("");				//작성 완료 버튼
		enrollBtn.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	String placeString = "'" + comboBox.getSelectedItem().toString() + "'";
            	
            	int home_id = 0;
            	//정문1 중문2 후문3 서문4 양성재5 양진재6 본관7
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
            
        		frame.setVisible(false);	//창 닫기
            }
        });
		enrollBtn.setIcon(new ImageIcon(".\\Image\\cmp_btn.PNG"));	//edit complete button
		enrollBtn.setBounds(337, 253, 78, 24);
		enrollBtn.setBorder(null);
		writePanel.add(enrollBtn);
		
		JButton backSBtn = new JButton("");			//뒤로가기 버튼 
		backSBtn.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);	//창 닫기
            }
        });
		backSBtn.setIcon(new ImageIcon(".\\Image\\back_s_btn.PNG"));
		backSBtn.setBounds(3, 1, 20, 23);
		backSBtn.setBorder(null);
		writePanel.add(backSBtn);
		
        frame.setVisible(true);		//frame 보이게
	}
}
