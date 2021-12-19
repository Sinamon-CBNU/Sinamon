package sinamon;
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
	private final String place[]= {"정 문", "중 문","서 문","후 문","본 관","양 성 재","양 진 재"};
	
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
		frame.setTitle("약속 정하기");
		frame.setBounds(100, 100, 460, 340);
		//frame 로고 아이콘
		Toolkit tk = Toolkit.getDefaultToolkit();
        Image logo=tk.getImage(".\\Image\\logo.png");
        frame.setIconImage(logo);
        
		//db_connection connection = new db_connection();
		
		ImagePanel writePanel = new ImagePanel(new ImageIcon(".\\Image\\rev.png").getImage());
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
		
		JTextArea memoArea = new JTextArea();				//제목
		memoArea.setLineWrap(true);
		memoArea.setFont(new Font("고도 m", Font.PLAIN, 17));
		memoArea.setBounds(87, 164, 320, 69);
		writePanel.add(memoArea);
		
		JButton revCmpBtn = new JButton("");				//등록 버튼
		revCmpBtn.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
            	String timeString = "'" + timeField.getText() + "'";
            	String placeString = "'" + comboBox.getSelectedItem().toString() + "'";
            	String memoString = "'" + memoArea.getText() + "'";
            
            	if(timeString.equals("''") || memoString.equals("''")) {
            		frame.setVisible(false);
            	}
            	else {
            		//예약 확정 글 쓰기
            		connection.input_reservation("'food_board'", timeString, placeString, memoString);
            	}
            }
        });
		revCmpBtn.setIcon(new ImageIcon(".\\Image\\rev_cmp_btn.PNG"));
		revCmpBtn.setBounds(264, 253, 155, 24);
		revCmpBtn.setBorder(null);
		writePanel.add(revCmpBtn);
		
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
