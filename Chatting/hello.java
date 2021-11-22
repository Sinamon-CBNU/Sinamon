package Chatting;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class hello extends JFrame {
	public hello(){
		setTitle("hello");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c=getContentPane();
		c.setLayout(null);
		JButton btn1=new JButton("서버오픈");
		JButton btn2=new JButton("채팅접속");
		JButton btn3=new JButton("서버종료");
		btn1.setBounds(250,250,100,100);
		btn2.setBounds(100,100,100,100);
		btn3.setBounds(400,400,50,50);
		c.add(btn1);
		c.add(btn2);
		c.add(btn3);
		btn1.addActionListener(new ActionListener() {	//반응
			public void actionPerformed(ActionEvent e) {
				new ChatServerObject();
			}
		});
		btn2.addActionListener(new ActionListener() {	//반응
			public void actionPerformed(ActionEvent e) {
				new ChatClientObject().service();
			}
		});
		btn2.addActionListener(new ActionListener() {	//반응
			public void actionPerformed(ActionEvent e) {
				//serverThread.interrupted(true);
			}
		});
		
		setSize(500,500);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new hello();
	}
}