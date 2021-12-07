package sinamon;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BoardLook {

	private JFrame frame;
	//private JTextArea titleArea;	
	//private JTextField timeField;
	//private JComboBox placeBox;
	private JLabel timeLb;
	private JTextArea memoArea;
	private JScrollPane memoScrollPane;
	private final String place[]= {"정 문", "중 문","서 문","후 문","본 관","양 성 재","양 진 재"};
	
	/**
	 * Create the application.
	 */
	public BoardLook(String board_name, Object[] curr_user, db_connection connection, String title) {
		initialize(board_name, curr_user, connection, title);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String board_name, Object[] curr_user, db_connection connection, String title) {
		frame = new JFrame();
		frame.setTitle("게시글 보기");
		frame.setBounds(100, 100, 460, 340);
		
		String [] my_rev_data=new String[3];               //데이터 리스트 선언
		String nickname = "'" + curr_user[2] + "'";
        my_rev_data = connection.return_memo(board_name, title);      //데이터 리스트르 받아옴
        String place=my_rev_data[0];   
        String time=my_rev_data[1];
        String memo=my_rev_data[2];
        
        System.out.println(place);
        System.out.println(time);
        System.out.println(memo);
        
        ImagePanel writePanel = new ImagePanel(new ImageIcon(".\\Image\\view.png").getImage());
        frame.getContentPane().add(writePanel);
        writePanel.setLayout(null);
      
        JLabel placeLb = new JLabel(place);         //장소
        placeLb.setFont(new Font("고도 m", Font.PLAIN, 15));
        placeLb.setBounds(84, 80, 329, 25);
        writePanel.add(placeLb);
      
        timeLb = new JLabel(time);                  //시간
        timeLb.setFont(new Font("고도 m", Font.PLAIN, 17));
        timeLb.setBounds(87, 121, 320, 24);
        timeLb.setBorder(null);
        writePanel.add(timeLb);
      
        //JLabel은 자동으로 줄바꿈이 안돼서 JTextArea를 수정하지 못하게 하여 사용
        memoArea = new JTextArea(memo);
        memoArea.setFont(new Font("고도 m", Font.PLAIN, 17));   //메모
        memoArea.setEditable(false);        //편집 불가
        memoArea.setCursor(null);           //커서 없애기
        memoArea.setWrapStyleWord(true);   //오른쪽 끝을 만나면 다음줄로 넘어가는 기능
        memoArea.setWrapStyleWord(true);   //다음줄로 넘어갈 때 단어가 나뉘어지지 않도록
        memoArea.setBounds(87, 164, 320, 69);
        JScrollPane memoScrollPane = new JScrollPane(memoArea);   //memoArea을 스크롤 패인으로
        memoScrollPane.setBounds(84, 160, 331, 79);
        writePanel.add(memoScrollPane);
		
		JButton cmpBtn = new JButton("");			//진행 완료 버튼
		cmpBtn.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
            	/*
            	 * '현황': 진행 중 -> 완료 바꾸기
            	 */
        		frame.setVisible(false);	//창 닫기	
            }
        });
		cmpBtn.setIcon(new ImageIcon(".\\Image\\done_btn.PNG"));
		cmpBtn.setBounds(263, 253, 155, 24);
		cmpBtn.setBorder(null);
		writePanel.add(cmpBtn);
		
		JButton backSBtn = new JButton("");			//뒤로가기 버튼
		backSBtn.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);		//창닫기
            }
        });
		backSBtn.setIcon(new ImageIcon(".\\Image\\back_s_btn.PNG"));
		backSBtn.setBounds(3, 3, 20, 23);
		backSBtn.setBorder(null);
		writePanel.add(backSBtn);

        frame.setVisible(true);
	}
}
