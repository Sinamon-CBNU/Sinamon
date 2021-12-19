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
	private final String place[]= {"정 문", "중 문","서 문","후 문","본 관","양 성 재","양 진 재"};
	private JTextField nickField;
	
	
	/**
	 * Create the application.
	 */
	public InfoEdit(Object[] curr_user, db_connection connnection) {	//유저정보 받아옴
		initialize(curr_user, connnection);
	}
    
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Object[] curr_user, db_connection connection) {
		frame = new JFrame();
		frame.setTitle("회원정보 수정");
		frame.setBounds(100, 100, 610, 440);
		//로고
		Toolkit tk = Toolkit.getDefaultToolkit();
        Image logo=tk.getImage(".\\Image\\logo.png");
        frame.setIconImage(logo);
		
		ImagePanel writePanel = new ImagePanel(new ImageIcon(".\\Image\\edit_info.png").getImage());
		frame.getContentPane().add(writePanel);
		writePanel.setLayout(null);
		
		nameField = new JTextField();							//이름
		nameField.setFont(new Font("고도 M", Font.PLAIN, 17));
		nameField.setColumns(10);
		nameField.setBorder(null);
		nameField.setBounds(110, 121, 165, 25);
		writePanel.add(nameField);
	    
		idField = new JTextField();								//id
		idField.setFont(new Font("고도 M", Font.PLAIN, 17));
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
		
		JComboBox comboBox = new JComboBox(place);			//장소
		comboBox.setFont(new Font("고도 M", Font.PLAIN, 17));
		comboBox.setBounds(377, 119, 175, 28);
		writePanel.add(comboBox);
		
		nickField = new JTextField();						//닉네임
		nickField.setFont(new Font("고도 M", Font.PLAIN, 17));
		nickField.setColumns(10);
		nickField.setBorder(null);
		nickField.setBounds(382, 180, 165, 25);
		writePanel.add(nickField);
		
		JButton cmpBtn = new JButton("");
	    cmpBtn.addActionListener(new ActionListener() { 
	                  @Override
	            public void actionPerformed(ActionEvent e) {
	                 String nameString = "'" + nameField.getText() + "'";      //이름
	                 String user_idString = "'" + idField.getText() + "'";      //아이디
	                 String pwdString = "'" + pwField.getText() + "'";         //비번
	                 String nicknameString = "'" + nickField.getText() + "'";   //닉네임
	                 String placeString = "'" + comboBox.getSelectedItem().toString() + "'";      //사는곳
	                 
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
	                   
	                 int user = Integer.valueOf((String) curr_user[0]);
	                 connection.modify_user_info(user, nameString, user_idString, pwdString, nicknameString, home_id);
	                 frame.setVisible(false);   //창 닫기
	                   }
	               });
	      cmpBtn.setIcon(new ImageIcon(".\\Image\\cmp_m_btn.PNG"));
	      cmpBtn.setBounds(450, 317, 105, 38);
	      cmpBtn.setBorder(null);
	      writePanel.add(cmpBtn);

		JButton backSBtn = new JButton("");			//뒤로가기 버튼 
		backSBtn.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);	//창 닫기
            }
        });
		backSBtn.setIcon(new ImageIcon(".\\Image\\back_m_btn.PNG"));
		backSBtn.setBounds(2, 1, 35, 32);
		backSBtn.setBorder(null);
		writePanel.add(backSBtn);
		
		
        frame.setVisible(true);		//frame 보이게
	}
}
