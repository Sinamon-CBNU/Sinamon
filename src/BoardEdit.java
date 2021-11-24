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
		//final int num=vo.getNum(); 	//목차 변경 불가
		final int num=01; 	//목차 변경 불가
		
		setBackground(new Color(249,140,150));
		setBounds(100, 100, 450, 280);
		//setBounds(new Rectangle(600, 0, 450, 280));
        setTitle("게시글 수정");
        getContentPane().setLayout(null);
        
        //음식
        JLabel timeLabel = new JLabel("시간");
        timeLabel.setBounds(12, 25, 57, 15);
        getContentPane().add(timeLabel);
 
        //time = new JTextField(vo.gettime());
        time = new JTextField();
        time.setBounds(81, 22, 340, 21);
        getContentPane().add(time);
        time.setColumns(10);
 
        
        JLabel lblNewLabel_1 = new JLabel("음 식");
        lblNewLabel_1.setBounds(12, 59, 57, 15);
        getContentPane().add(lblNewLabel_1);
 
        //JTextArea textArea = new JTextArea(vo.getFood());
        JTextArea food = new JTextArea();
        food.setLineWrap(true);
        food.setRows(5);
        food.setBounds(81, 53, 340, 69);
        getContentPane().add(food);
 
        JLabel lblNewLabel_2 = new JLabel("작성자");
        lblNewLabel_2.setBounds(12, 140, 57, 15);
        getContentPane().add(lblNewLabel_2);
 
        //name = new JTextField(vo.getName());
        JLabel name = new JLabel("닉네임");
        name.setBounds(81, 137, 116, 21);
        getContentPane().add(name);
        
 
        JButton btnWrite = new JButton("글수정");
        //btnWrite.setIcon(new ImageIcon();
        btnWrite.setBounds(81, 180, 97, 23);
        btnWrite.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
                //DB 객체 생성
                //BoardVO vo = new BoardVO();	//value object 객체 생성
 
                String changefood = food.getText();
                String changeTime = time.getText();
               
                /*vo.setNum(num);
                vo.setfood(food.getText());
                vo.setContent(textArea.getText());
                vo.setName(name.getText());
 
                DB에.update(vo);*/
 
                setVisible(false);
 
            }
        });
        getContentPane().add(btnWrite);
 
        JButton btnDel = new JButton("글삭제");
        //btnDel.setIcon(new ImageIcon());
        btnDel.setBounds(190, 180, 97, 23);
        btnDel.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
            	//DB 객체 생성
                //BoardVO vo = new BoardVO();	//value object 객체 생성
 
                /*vo.setNum(num);	//목차 삭제
 
                DB에.delete(vo);*/
 
                setVisible(false);
            }
        });
        getContentPane().add(btnDel);
 
        JButton btnClose = new JButton("닫기");
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

