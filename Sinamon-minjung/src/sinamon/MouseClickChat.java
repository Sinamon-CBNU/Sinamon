package sinamon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

public class MouseClickChat implements MouseListener {
	JTable table;
	@Override
	public void mouseClicked(MouseEvent e) {	//마우스 클릭
		int row=table.getSelectedRow();		//클릭한 행 저장
		/*****해당 행의 채팅버튼 창이 열리도록 구현!!*****/
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
