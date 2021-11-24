import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BoardEdit extends JFrame {
	private JTextField food;	
	private JTextField time;	
	
	//public BoardEdit(BoardVO vo) {
	public BoardEdit() { 
		//final int num=vo.getNum(); 	//���� ���� �Ұ�
		final int num=01; 	//���� ���� �Ұ�
		
		setBackground(new Color(249,140,150));
		setBounds(100, 100, 450, 280);
		//setBounds(new Rectangle(600, 0, 450, 280));
        setTitle("�Խñ� ����");
        getContentPane().setLayout(null);
        
        //����
        JLabel timeLabel = new JLabel("�ð�");
        timeLabel.setBounds(12, 25, 57, 15);
        getContentPane().add(timeLabel);
 
        //time = new JTextField(vo.gettime());
        time = new JTextField();
        time.setBounds(81, 22, 340, 21);
        getContentPane().add(time);
        time.setColumns(10);
 
        
        JLabel lblNewLabel_1 = new JLabel("�� ��");
        lblNewLabel_1.setBounds(12, 59, 57, 15);
        getContentPane().add(lblNewLabel_1);
 
        //JTextArea textArea = new JTextArea(vo.getFood());
        JTextArea food = new JTextArea();
        food.setLineWrap(true);
        food.setRows(5);
        food.setBounds(81, 53, 340, 69);
        getContentPane().add(food);
 
        JLabel lblNewLabel_2 = new JLabel("�ۼ���");
        lblNewLabel_2.setBounds(12, 140, 57, 15);
        getContentPane().add(lblNewLabel_2);
 
        //name = new JTextField(vo.getName());
        JLabel name = new JLabel("�г���");
        name.setBounds(81, 137, 116, 21);
        getContentPane().add(name);
        
 
        JButton btnWrite = new JButton("�ۼ���");
        //btnWrite.setIcon(new ImageIcon();
        btnWrite.setBounds(81, 180, 97, 23);
        btnWrite.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
                //DB ��ü ����
                //BoardVO vo = new BoardVO();	//value object ��ü ����
 
                String changefood = food.getText();
                String changeTime = time.getText();
               
                /*vo.setNum(num);
                vo.setfood(food.getText());
                vo.setContent(textArea.getText());
                vo.setName(name.getText());
 
                DB��.update(vo);*/
 
                setVisible(false);
 
            }
        });
        getContentPane().add(btnWrite);
 
        JButton btnDel = new JButton("�ۻ���");
        //btnDel.setIcon(new ImageIcon());
        btnDel.setBounds(190, 180, 97, 23);
        btnDel.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
            	//DB ��ü ����
                //BoardVO vo = new BoardVO();	//value object ��ü ����
 
                /*vo.setNum(num);	//���� ����
 
                DB��.delete(vo);*/
 
                setVisible(false);
            }
        });
        getContentPane().add(btnDel);
 
        JButton btnClose = new JButton("�ݱ�");
        //btnClose.setIcon(new ImageIcon());
        btnClose.setBounds(299, 180, 97, 23);
        btnClose.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        getContentPane().add(btnClose);
 
        setVisible(true);
	}
	//UI Check
	 //public static void main(String[] args) { new BoardEdit(); }
}

