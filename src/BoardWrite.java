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
	private JTextArea foodArea;	
	private JTextField timeField;
	private JComboBox placeBox;
	private final String place[]= {"정 문", "중 문","서 문","후 문","본 관","양 성 재","양 진 재"};
	/**
	 * Create the application.
	 */
	public BoardWrite() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("게시글 쓰기");
		frame.setBounds(100, 100, 460, 340);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImagePanel writePanel = new ImagePanel(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\write.png").getImage());
		frame.getContentPane().add(writePanel);
		writePanel.setLayout(null);
		
		timeField = new JTextField();
		timeField.setFont(new Font("HY엽서M", Font.PLAIN, 15));
		timeField.setBounds(87, 121, 320, 24);
		timeField.setBorder(null);
		writePanel.add(timeField);
		timeField.setColumns(10);
		
		JComboBox comboBox = new JComboBox(place);
		comboBox.setFont(new Font("HY엽서M", Font.PLAIN, 15));
		comboBox.setBounds(87, 80, 320, 23);
		writePanel.add(comboBox);
		
		JTextArea foodArea = new JTextArea();
		foodArea.setBounds(87, 164, 320, 69);
		writePanel.add(foodArea);
		
		JButton deleteBtn = new JButton("");
		deleteBtn.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
            	/***삭제 구현하기!!!***/
                frame.setVisible(false);
            }
        });
		deleteBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\cmp_btn.PNG"));
		deleteBtn.setBounds(337, 253, 78, 24);
		deleteBtn.setBorder(null);
		writePanel.add(deleteBtn);
		
		JButton backSBtn = new JButton("");
		backSBtn.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
		backSBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\back_s_btn.PNG"));
		backSBtn.setBounds(4, 5, 20, 23);
		backSBtn.setBorder(null);
		writePanel.add(backSBtn);
	
		
 
        frame.setVisible(true);
	}

}
