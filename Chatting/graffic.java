package Chatting;

import javax.swing.*;
import java.awt.*;

public class graffic extends JFrame {
	private MyPanel panel = new MyPanel();

	public graffic() {
		setTitle("원본 크기로 원하는 위치에 이미지 그리기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		setSize(400, 550);
		setVisible(true);
	}

	class MyPanel extends JPanel {
		private ImageIcon icon = new ImageIcon("C:\\Users\\6843w\\OneDrive\\바탕 화면\\그림소스\\제목 없음.png");
		private Image img = icon.getImage(); // 이미지 객체

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, this);
		}
	}

	public static void main(String[] args) {
		new graffic();
	}
}