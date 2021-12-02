package SinamonUI;
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
	private JComboBox placeBox;
	private final String place[]= {"정 문", "중 문","서 문","후 문","본 관","양 성 재","양 진 재"};
	private JTextField nameField;
	private JTextField idField;
	private JPasswordField pwField;
	private JPasswordField pwCheckField;
	private JTextField nickField;
	
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
		frame.setBounds(100, 100, 610, 440);
		//로고
		Toolkit tk = Toolkit.getDefaultToolkit();
        Image logo=tk.getImage(".\\Image\\logo.png");
        frame.setIconImage(logo);
        
		//db_connection connection = new db_connection();
		
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
		
		
		JButton cmpBtn = new JButton("");
      		cmpBtn.addActionListener(new ActionListener() { 
                	@Override
                 	public void actionPerformed(ActionEvent e) {
            			/*
            			* 등록 이벤트 구현!!
            			*/
                      		frame.setV	isible(false);   //창 닫기
			}
           	});
      		cmpBtn.setIcon(new ImageIcon(".\\Image\\cmp_m_btn.PNG"));
      		cmpBtn.setBounds(450, 317, 105, 38);
      		cmpBtn.setBorder(null);
      		writePanel.add(cmpBtn);
		

       		frame.setVisible(true);		//frame 보이게
	}
}
