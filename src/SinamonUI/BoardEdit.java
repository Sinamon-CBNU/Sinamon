package SinamonUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
//import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BoardEdit {

	private JFrame frame;
	private JTextArea chTitleArea;	
	private JTextField chTimeField;
	private JComboBox chPlaceBox;
	private final String place[]= {"정 문", "중 문","서 문","후 문","본 관","양 성 재","양 진 재"};
	
	/**
	 * Create the application.
	 */
	public BoardEdit() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("게시글 수정");
		frame.setBounds(100, 100, 460, 340);
		frame.setPreferredSize(new Dimension(450,300));
		//로고
		Toolkit tk = Toolkit.getDefaultToolkit();
        Image logo=tk.getImage(".\\Image\\logo.png");
        frame.setIconImage(logo);
		
		ImagePanel editPanel = new ImagePanel(new ImageIcon(".\\Image\\edit.png").getImage());
		frame.getContentPane().add(editPanel);
		editPanel.setLayout(null);
		
		chTimeField = new JTextField();		//change time 시간 바꾸기
		chTimeField.setFont(new Font("고도 M", Font.PLAIN, 17));
		chTimeField.setBounds(87, 121, 320, 24);
		chTimeField.setBorder(null);
		editPanel.add(chTimeField);
		chTimeField.setColumns(10);
		
		chPlaceBox = new JComboBox(place);		//change place 장소 바꾸기
		chPlaceBox.setFont(new Font("고도 M", Font.PLAIN, 17));
		chPlaceBox.setBounds(83, 80, 330, 25);
		editPanel.add(chPlaceBox);
		
		chTitleArea = new JTextArea();			//change title 제목 바꾸기
		chTitleArea.setLineWrap(true);
		chTitleArea.setBounds(87, 164, 320, 69);
		editPanel.add(chTitleArea);
		
		//수정 버튼
		JButton editBtn = new JButton("");
		editBtn.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
            	/***수정 구현하기!!!***/
                frame.setVisible(false);
            }
        });
		editBtn.setIcon(new ImageIcon(".\\Image\\edit_btn.PNG"));
		editBtn.setBounds(257, 251, 80, 24);
		editBtn.setBorder(null);
		editPanel.add(editBtn);
		
		//삭제 버튼
		JButton deleteBtn = new JButton("");
		deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	/***삭제 구현하기!!!***/
                frame.setVisible(false);
            }
        });
		deleteBtn.setIcon(new ImageIcon(".\\Image\\delete_btn.PNG"));
		deleteBtn.setBounds(337, 251, 80, 24);
		deleteBtn.setBorder(null);
		editPanel.add(deleteBtn);
		
		//뒤로가기 버튼
		JButton backBtn = new JButton("");
		backBtn.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);	//해당 창 닫기
            }
        });
		backBtn.setIcon(new ImageIcon(".\\Image\\back_s_btn.PNG"));
		backBtn.setBounds(0, 0, 24, 28);
		backBtn.setBorder(null);
		editPanel.add(backBtn);
 
        frame.setVisible(true);
	}
}
