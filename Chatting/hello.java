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
		JButton btn2=new JButton("1번방");
		JButton btn3=new JButton("방 생성");
		JButton btn4=new JButton("2번방");
		JButton btn5=new JButton("3번방");
		btn2.setBounds(100,100,100,50);
		btn3.setBounds(400,400,100,100);
		btn4.setBounds(210,100,100,50);
		btn5.setBounds(310,100,100,50);
		c.add(btn2);
		c.add(btn3);
		c.add(btn4);
		c.add(btn5);
		
		setSize(500,500);
		setVisible(true);
		
		btn2.addActionListener(new ActionListener() {	//반응
			public void actionPerformed(ActionEvent e) {	//버튼누른사람이 방장이 된다고 가정
				ChatClientObject client=new ChatClientObject(0);
				client.service();
			}
		});
		btn3.addActionListener(new ActionListener() {	//반응
			public void actionPerformed(ActionEvent e) {
				
				Room room;
				 
				for(int i=0; i<3; i++) {
				room=new Room();
				RoomManager.setroom(room);
				}
			}
		});
		btn4.addActionListener(new ActionListener() {	//반응
			public void actionPerformed(ActionEvent e) {
				ChatClientObject client=new ChatClientObject(1);
				client.service();
			}
		});
		btn5.addActionListener(new ActionListener() {	//반응
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