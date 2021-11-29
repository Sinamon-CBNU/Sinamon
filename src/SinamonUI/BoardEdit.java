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
	private final String place[]= {"�� ��", "�� ��","�� ��","�� ��","�� ��","�� �� ��","�� �� ��"};
	
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
		frame.setTitle("�Խñ� ����");
		frame.setBounds(100, 100, 460, 340);
		frame.setPreferredSize(new Dimension(450,300));
		//�ΰ�
		Toolkit tk = Toolkit.getDefaultToolkit();
        Image logo=tk.getImage(".\\Image\\logo.png");
        frame.setIconImage(logo);
		
		ImagePanel editPanel = new ImagePanel(new ImageIcon(".\\Image\\edit.png").getImage());
		frame.getContentPane().add(editPanel);
		editPanel.setLayout(null);
		
		chTimeField = new JTextField();		//change time �ð� �ٲٱ�
		chTimeField.setFont(new Font("�� M", Font.PLAIN, 17));
		chTimeField.setBounds(87, 121, 320, 24);
		chTimeField.setBorder(null);
		editPanel.add(chTimeField);
		chTimeField.setColumns(10);
		
		chPlaceBox = new JComboBox(place);		//change place ��� �ٲٱ�
		chPlaceBox.setFont(new Font("�� M", Font.PLAIN, 17));
		chPlaceBox.setBounds(83, 80, 330, 25);
		editPanel.add(chPlaceBox);
		
		chTitleArea = new JTextArea();			//change title ���� �ٲٱ�
		chTitleArea.setLineWrap(true);
		chTitleArea.setBounds(87, 164, 320, 69);
		editPanel.add(chTitleArea);
		
		//���� ��ư
		JButton editBtn = new JButton("");
		editBtn.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
            	/***���� �����ϱ�!!!***/
                frame.setVisible(false);
            }
        });
		editBtn.setIcon(new ImageIcon(".\\Image\\edit_btn.PNG"));
		editBtn.setBounds(257, 251, 80, 24);
		editBtn.setBorder(null);
		editPanel.add(editBtn);
		
		//���� ��ư
		JButton deleteBtn = new JButton("");
		deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	/***���� �����ϱ�!!!***/
                frame.setVisible(false);
            }
        });
		deleteBtn.setIcon(new ImageIcon(".\\Image\\delete_btn.PNG"));
		deleteBtn.setBounds(337, 251, 80, 24);
		deleteBtn.setBorder(null);
		editPanel.add(deleteBtn);
		
		//�ڷΰ��� ��ư
		JButton backBtn = new JButton("");
		backBtn.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);	//�ش� â �ݱ�
            }
        });
		backBtn.setIcon(new ImageIcon(".\\Image\\back_s_btn.PNG"));
		backBtn.setBounds(0, 0, 24, 28);
		backBtn.setBorder(null);
		editPanel.add(backBtn);
 
        frame.setVisible(true);
	}
}
