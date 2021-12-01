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
		
		Room room;
		for(int i=0; i<1000; i++) {
		room=new Room();
		RoomManager.setroom(room);
		}
		
		Container c=getContentPane();
		c.setLayout(null);
		JButton btn1=new JButton("1번방");
		JButton btn2=new JButton("2번방");
		JButton btn3=new JButton("3번방");
		btn1.setBounds(100,100,100,50);
		btn2.setBounds(210,100,100,50);
		btn3.setBounds(310,100,100,50);
		c.add(btn1);
		c.add(btn2);
		c.add(btn3);
		
		setSize(500,500);
		setVisible(true);
		
		
		
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				ChatClientObject client=new ChatClientObject(0);
				client.service();
			}
		});
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChatClientObject client=new ChatClientObject(1);
				client.service();
			}
		});
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChatClientObject client=new ChatClientObject(2);
				client.service();
			}
		});
		
	}
	
	public static void main(String[] args) {
		new hello();
		
	}
}
