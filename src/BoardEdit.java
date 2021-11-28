import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
	private JTextArea chFoodArea;	
	private JTextField chTimeField;
	private JComboBox chPlaceBox;
	//final int num=vo.getNum(); 	//목차 변경 불가
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
		frame.getContentPane().setBackground(Color.PINK);
		frame.setBounds(100, 100, 460, 340);
		frame.setPreferredSize(new Dimension(450,300));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImagePanel editPanel = new ImagePanel(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\edit.png").getImage());
		frame.getContentPane().add(editPanel);
		editPanel.setLayout(null);
		
		chTimeField = new JTextField();
		chTimeField.setFont(new Font("HY엽서M", Font.PLAIN, 15));
		chTimeField.setBounds(87, 121, 320, 24);
		chTimeField.setBorder(null);
		editPanel.add(chTimeField);
		chTimeField.setColumns(10);
		
		chPlaceBox = new JComboBox(place);
		chPlaceBox.setFont(new Font("HY엽서M", Font.PLAIN, 15));
		chPlaceBox.setBounds(87, 80, 320, 23);
		editPanel.add(chPlaceBox);
		
		chFoodArea = new JTextArea();
		chFoodArea.setLineWrap(true);
		chFoodArea.setBounds(87, 164, 320, 69);
		editPanel.add(chFoodArea);
		
		JButton editBtn = new JButton("");
		editBtn.addActionListener(new ActionListener() {
			 
            @Override
            public void actionPerformed(ActionEvent e) {
            	/***수정 구현하기!!!***/
                frame.setVisible(false);
            }
        });
		editBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\edit_btn.PNG"));
		editBtn.setBounds(257, 253, 80, 24);
		editPanel.add(editBtn);
		
		JButton deleteBtn = new JButton("");
		deleteBtn.addActionListener(new ActionListener() {
			 
            @Override
            public void actionPerformed(ActionEvent e) {
            	/***삭제 구현하기!!!***/
                frame.setVisible(false);
            }
        });
		deleteBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\delete_btn.PNG"));
		deleteBtn.setBounds(337, 253, 80, 24);
		editPanel.add(deleteBtn);
	
		JButton chBackBtn = new JButton("");
		chBackBtn.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
		chBackBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\back_s_btn.PNG"));
		chBackBtn.setBounds(4, 5, 20, 23);
		chBackBtn.setBorder(null);
		editPanel.add(chBackBtn);
 
        frame.setVisible(true);
	}
}
