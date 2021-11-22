package Chatting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame
{
	
	private Point initialClick;
	
	public MainFrame()
	{
		setResizable(false);
		setUndecorated(true);
		
		// setTitle("MainFrame")
		setSize(1100, 700);
		setVisible(true);
		
		this.addMouseListener(new moveWindows());
		this.addMouseMotionListener(new moveWindows());
	}
	
	class moveWindows extends MouseAdapter
	{ 
		public void mousePressed(MouseEvent e) 
		{ 
			initialClick = e.getPoint();
			getComponentAt(initialClick);
		}

		public void mouseDragged(MouseEvent e) 
		{
			JFrame jf = (JFrame) e.getSource();
            
			int thisX = jf.getLocation().x;
			int thisY = jf.getLocation().y;
            
			int xMoved = e.getX() - initialClick.x;
			int yMoved = e.getY() - initialClick.y;
            
			int X = thisX + xMoved;
			int Y = thisY + yMoved;
			jf.setLocation(X, Y);
		}
	}

	public static void main(String[] args) 
	{
		MainFrame mf = new MainFrame();
	}
}