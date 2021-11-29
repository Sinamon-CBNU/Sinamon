import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class ImagePanel extends JPanel{
	private  Image img;		//이미지 private
	public ImagePanel(Image img) {
		this.img=img;
		setSize(new Dimension(img.getWidth(null),img.getHeight(null)));
		setPreferredSize(new Dimension(img.getWidth(null),img.getHeight(null)));
		setLayout(null);	//중앙 배치
	}
	
	//randering
	public void paintComponent(Graphics g) {
		g.drawImage(img,0,0,null);
	}
	
	//get 가로,세로 길이
	public Dimension getDimension(){
		return new Dimension(img.getWidth(null)+16,img.getHeight(null)+39);
	}
}
