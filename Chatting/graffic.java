package Chatting;

import javax.swing.*;
import java.awt.*;

public class graffic extends JFrame {
	private MyPanel panel = new MyPanel();

	public graffic() {
		setTitle("���� ũ��� ���ϴ� ��ġ�� �̹��� �׸���");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		setSize(400, 550);
		setVisible(true);
	}

	class MyPanel extends JPanel {
		private ImageIcon icon = new ImageIcon("C:\\Users\\6843w\\OneDrive\\���� ȭ��\\�׸��ҽ�\\���� ����.png");
		private Image img = icon.getImage(); // �̹��� ��ü

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, this);
		}
	}

	public static void main(String[] args) {
		new graffic();
	}
}