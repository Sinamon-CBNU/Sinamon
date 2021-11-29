package SinamonUI;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class ChatBtnDesign extends JButton {
	public ChatBtnDesign() { super(); decorate(); } 
    public ChatBtnDesign(String text) { super(text); decorate(); } 
    public ChatBtnDesign(Action action) { super(action); decorate(); } 
    public ChatBtnDesign(Icon icon) { super(icon); decorate(); } 
    public ChatBtnDesign(String text, Icon icon) { super(text, icon); decorate(); }
    protected void decorate() { setBorderPainted(false); setOpaque(false); }
    @Override 
    protected void paintComponent(Graphics g) {
       Color c=new Color(255, 162, 157); //배경색 결정
       Color o=new Color(255, 249, 225); //글자색 결정
       int width = getWidth(); 
       int height = getHeight(); 
       Graphics2D graphics = (Graphics2D) g; 
       graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
       if (getModel().isArmed()) { graphics.setColor(c.darker()); }				//버튼에 마우스가 눌러진 상태면
       else if (getModel().isRollover()) { graphics.setColor(c.brighter()); } 	//버튼에 마우스가 올려져 있으면 
       else { graphics.setColor(c); } 
       graphics.fillRoundRect(0, 0, width, height, 10, 10); 
       FontMetrics fontMetrics = graphics.getFontMetrics(); 
       Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds(); 
       int textX = (width - stringBounds.width) / 2; 
       int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 
       graphics.setColor(o); 
       graphics.setFont(getFont()); 
       graphics.drawString(getText(), textX, textY); 
       graphics.dispose(); 
       super.paintComponent(g); 
       }
}
