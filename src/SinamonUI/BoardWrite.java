package SinamonUI;
//DB 관련 코드는 잠시 주석처리해둠
//package sinamon_project;

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

public class BoardWrite {

	private JFrame frame;
	private JTextArea titleArea;	
	private JTextField timeField;
	private JComboBox placeBox;
	private final String place[]= {"정 문", "중 문","서 문","후 문","본 관","양 성 재","양 진 재"};
	
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
		frame.setTitle("게시글 쓰기");
		frame.setBounds(100, 100, 460, 340);
		//로고
		Toolkit tk = Toolkit.getDefaultToolkit();
        Image logo=tk.getImage(".\\Image\\logo.png");
        frame.setIconImage(logo);
        
		db_connection connection = new db_connection();
		
		ImagePanel writePanel = new ImagePanel(new ImageIcon(".\\Image\\write.png").getImage());
		frame.getContentPane().add(writePanel);
		writePanel.setLayout(null);
		
		timeField = new JTextField();						//나누는 시간
		timeField.setFont(new Font("고도 m", Font.PLAIN, 17));
		timeField.setBounds(87, 121, 320, 24);
		timeField.setBorder(null);
		writePanel.add(timeField);
		timeField.setColumns(10);
		
		JComboBox comboBox = new JComboBox(place);			//장소
		comboBox.setFont(new Font("고도 m", Font.PLAIN, 15));
		comboBox.setBounds(83, 80, 330, 25);
		writePanel.add(comboBox);
		
		JTextArea titleArea = new JTextArea();				//제목
		titleArea.setLineWrap(true);
		titleArea.setFont(new Font("고도 m", Font.PLAIN, 17));
		titleArea.setBounds(87, 164, 320, 69);
		writePanel.add(titleArea);
		
		JButton enrollBtn = new JButton("");				//등록 버튼
		enrollBtn.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
            	String timeString = "'" + timeField.getText() + "'";
            	String placeString = "'" + comboBox.getSelectedItem().toString() + "'";
            	String titleString = "'" + titleArea.getText() + "'";
            	String nicknameString = "'" + curr_user[2] + "'";		//닉네임
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
            
            	//insert_board_contents(String board_name, int home_id, String time, String title, String nickname)
            	if(timeString.equals("''") || titleString.equals("''")) {
            		frame.setVisible(false);
            	}
            	else {
            		//connection.insert_board_contents(board_name, home_id, timeString, titleString, nicknameString);
            		frame.setVisible(false);
            	}
            }
        });
		enrollBtn.setIcon(new ImageIcon(".\\Image\\cmp_btn.PNG"));
		enrollBtn.setBounds(339, 253, 76, 24);
		enrollBtn.setBorder(null);
		writePanel.add(enrollBtn);
		
		JButton backSBtn = new JButton("");			//뒤로가기 버튼 
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