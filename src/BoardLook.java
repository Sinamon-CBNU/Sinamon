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

public class BoardLook {

	private JFrame frame;
	private JTextArea titleArea;	
	private JTextField timeField;
	private JComboBox placeBox;
	private final String place[]= {"정 문", "중 문","서 문","후 문","본 관","양 성 재","양 진 재"};
	
	/**
	 * Create the application.
	 */
	public BoardLook(String board_name, Object[] curr_user) {
		initialize(board_name, curr_user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String board_name, Object[] curr_user) {
		frame = new JFrame();
		frame.setTitle("게시글 보기");
		frame.setBounds(100, 100, 460, 340);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//db_connection connection = new db_connection();
		
		ImagePanel writePanel = new ImagePanel(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\view.png").getImage());
		frame.getContentPane().add(writePanel);
		writePanel.setLayout(null);
		
		timeField = new JTextField();						//시간
		timeField.setFont(new Font("HY엽서M", Font.PLAIN, 15));
		timeField.setBounds(87, 121, 320, 24);
		timeField.setBorder(null);
		writePanel.add(timeField);
		timeField.setColumns(10);
		
		JComboBox comboBox = new JComboBox(place);			//장소
		comboBox.setFont(new Font("HY엽서M", Font.PLAIN, 15));
		comboBox.setBounds(84, 80, 328, 23);
		writePanel.add(comboBox);
		
		JTextArea titleArea = new JTextArea();
		titleArea.setLineWrap(true);
		titleArea.setBounds(87, 164, 320, 69);
		writePanel.add(titleArea);
		
		JButton cmpBtn = new JButton("");			//진행 완료 버튼
		cmpBtn.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
            	/*
            	 * '현황': 진행 중 -> 완료 바꾸기
            	 */
        		frame.setVisible(false);	//창 닫기	
            }
        });
		cmpBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\done_btn.PNG"));
		cmpBtn.setBounds(263, 253, 155, 24);
		cmpBtn.setBorder(null);
		writePanel.add(cmpBtn);
		
		JButton backSBtn = new JButton("");			//뒤로가기 버튼
		backSBtn.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);		//창닫기
            }
        });
		backSBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\back_s_btn.PNG"));
		backSBtn.setBounds(3, 3, 20, 23);
		backSBtn.setBorder(null);
		writePanel.add(backSBtn);

        frame.setVisible(true);
	}
}
