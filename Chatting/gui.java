package Chatting;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
 
public class gui extends JFrame {
    JScrollPane scrollPane;
    ImageIcon icon;
 
    public gui() {
        icon = new ImageIcon("C:\\Users\\6843w\\OneDrive\\���� ȭ��\\�׸��ҽ�\\���� ����.png");
       
        //��� Panel ������ �������������� ����      
        JPanel background = new JPanel() {
            public void paintComponent(Graphics g) {
                // Approach 1: Dispaly image at at full size
                g.drawImage(icon.getImage(), 0, 0, null);
                // Approach 2: Scale image to size of component
                // Dimension d = getSize();
                // g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                // Approach 3: Fix the image position in the scroll pane
                // Point p = scrollPane.getViewport().getViewPosition();
                // g.drawImage(icon.getImage(), p.x, p.y, null);
                setOpaque(false); //�׸��� ǥ���ϰ� ����,�����ϰ� ����
                super.paintComponent(g);
            }
        };

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 550);
        setVisible(true);
       
       
        JButton button = new JButton("��ư");
        button.setLocation(110, 100);
        button.setSize(20, 20);
        background.add(button);
        scrollPane = new JScrollPane(background);
        setContentPane(scrollPane);
    }
 
    public static void main(String[] args) {
        gui frame = new gui();
    }
}