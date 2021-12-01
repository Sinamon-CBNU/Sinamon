import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class SinamonApp {
	
	//check 임시 ID,PW
	String board_name;
	
	private final String place[]= {"정 문", "중 문","서 문","후 문","본 관","양 성 재","양 진 재"};
	
	public Object[][] food_board_data = new Object[100][6];		//음식 게시물 게시판에 띄울 배열
	public Object[][] nec_board_data = new Object[100][6];		//생필품 게시물 게시판에 띄울 배열
	public Object[][] my_board_data = new Object[100][2];		//내가 쓴 게시물 마이페이지에 띄울 배열 글 번호, 제목, 완료여부
	Object[] curr_user = new Object[6];		//현재 로그인중인 사용자 정보 담을 배열
	
	private JFrame frame;
	private JTextField idField;
	private JPasswordField pwField;
	private JPanel currPanel;	//현재 패널
	private JPanel bfPanel;	//전 before 패널
	private JTextField joinNameField;
	private JTextField joinIdField;
	private JTextField joinPwField;
	private JTextField joinPwCheckField;
	private JTextField joinNickField;
	private JButton enrollBtn;
	private JButton backBtn;
	private JButton clickBtn;
	private JButton recBtn;
	private JButton searchBtn;
	private JTable ftable;			//음식 게시판의 table
	private JTable ntable;			//생필품 게시판의 table
	private JTable history1;		//Mypage-내가 쓴 글
	private JTable history2;		//Mypage-내가 시나몬 한 history
	private JScrollPane h1ScrollPane;	//Mypage-내가 쓴 글 테이블의 스크롤패인
	private JScrollPane h2ScrollPane;	///Mypage-내가 시나몬 한 history 테이블의 스크롤패인
	private JCheckBox frontCkBox;
	private JCheckBox centralCkBox;
	private JCheckBox westCkBox;
	private JCheckBox backCkBox;
	private JCheckBox bonCkBox;
	private JCheckBox yangseongCkBox;
	private JCheckBox yangjinCkBox;
	private JTextField searchField;
	private JButton MPbackBtn;
	private JCheckBox frontCkBx;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SinamonApp window = new SinamonApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SinamonApp() {
		db_connection connection = new db_connection();			//데이터베이스 연동 객체
		//while(true) {
			initialize(connection);
		//}
		
	}
	
	//채팅 버튼 눌렀을 때 동작
	 public class TableCellRenderer extends DefaultTableCellRenderer{ 
		 @Override 
		 public ChatBtnDesign getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column) { 
			 ChatBtnDesign chatBtn = null; 
			 chatBtn = new ChatBtnDesign("채팅하기");
		 	 chatBtn.setFont(new Font("Sanserif", Font.BOLD, 15));
		 	 //동작이 안돼....>>오늘까지 해결할꼐...
		 	 chatBtn.addActionListener(e -> { System.out.println("good");}); 
			 return chatBtn; 
		 } 
	 }
	
	/**
	 * Initialize the contents of the frame.
	 */
	//마이페이지 패널 생성
	private JPanel Create_Mypage_Panel(db_connection connection) {
		DefaultTableCellRenderer colC = new DefaultTableCellRenderer();
	    colC.setBackground(new Color(255, 255, 208));	
	    //table의 글 정렬을 위한
	    DefaultTableCellRenderer celAlignCenter=new DefaultTableCellRenderer();
	    celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
	    DefaultTableCellRenderer celAlignRight=new DefaultTableCellRenderer();
	    celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
	    //'현황'셀의 글자 색깔 변경과 가운데 정룔을 위한 DefaultTableCellRenderer 객체 선언
	    DefaultTableCellRenderer celOrCenter=new DefaultTableCellRenderer();
	    celOrCenter.setForeground(new Color(255, 127, 0));
	    celOrCenter.setHorizontalAlignment(JLabel.CENTER);
	    
		ImagePanel mypagePanel = new ImagePanel(new ImageIcon(".\\Image\\mypage.png").getImage());
		frame.getContentPane().add(mypagePanel);	
		
		mypagePanel.setBounds(0,0,960,540);	//패널 사이즈
		mypagePanel.setLayout(null);
		
		//history1 구현 - [내가 쓴 글]
		String[] hHeader=new String[] {"제목", "현황"};	//히스토리 테이블 헤더
		String nickname = "'" + curr_user[2] + "'";
		my_board_data = connection.return_my_board(nickname);
		
		//DefaultTableModel을 사용하여 내용 수정 불가하게
		DefaultTableModel modH1 = new DefaultTableModel(my_board_data, hHeader) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        history1 =new JTable(modH1);
        //테이블이 "예정"일 경우, 클릭하면 게시글 수정창이 나타남
        history1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	int row = history1.getSelectedRow();
                int col = history1.getSelectedColumn();
                String value=(String) history1.getValueAt(row,col);		//선택한 셀의 값을 저장하여
            	if(value.equals("예정"))	{								//'에정'일 경우
            		new BoardEdit();									//수정창이 나타남
            	}
            }
        });
		//테이블 세부 설정
        history1.setRowHeight(25);	//테이블 행 간격
		history1.setFont(new Font("Sanserif", Font.BOLD, 15));	//테이블 폰트
	    history1.getColumn("제목").setPreferredWidth(300);			//테이블 열 간격
	    history1.getColumn("현황").setPreferredWidth(80);
	    history1.getColumn("현황").setCellRenderer(celOrCenter);	//'현황': 글자 파란색, 가운데 정렬
		history1.setPreferredScrollableViewportSize(new Dimension(380,256));
        history1.getTableHeader().setReorderingAllowed(false); 	// 컬럼들 이동 불가
        history1.getTableHeader().setResizingAllowed(false); 	// 컬럼 크기 조절 불가
		
        h1ScrollPane = new JScrollPane(history1);	//table을 스크롤 패인으로
		h1ScrollPane.setBounds(77, 254, 380, 256);
		mypagePanel.add(h1ScrollPane);
		
		//history2 구현 - [내가 시나몬 한 history]
		Object[][] hData2 = connection.return_get_in_board(nickname);
		
		//DefaultTableModel을 사용하여 내용 수정 불가하게
		DefaultTableModel modH2 = new DefaultTableModel(hData2,hHeader) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        history2 =new JTable(modH2);
        //테이블이 "진행중"일 경우, 클릭하면 게시글 보기창이 나타남
        history2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	int row = history2.getSelectedRow();
                int col = history2.getSelectedColumn();
                String value=(String) history2.getValueAt(row,col);         //선택한 셀의 값을 저장하여
                if(value.equals("진행중")) {                          //'진행 중'일 경우
                	new BoardLook(board_name,curr_user);               //게시글 보기 창이 나타남
                }
            }
        });

		//테이블 세부 설정
        history2.setRowHeight(25);	//테이블 행 간격
		history2.setFont(new Font("Sanserif", Font.BOLD, 15));	//테이블 폰트
	    history2.getColumn("제목").setPreferredWidth(300);			//테이블 열 간격
	    history2.getColumn("현황").setPreferredWidth(80);
	    history2.getColumn("현황").setCellRenderer(celOrCenter);	//'현황': 글자 파란색, 가운데 정렬
		history2.setPreferredScrollableViewportSize(new Dimension(380,256));
        history2.getTableHeader().setReorderingAllowed(false); 	// 컬럼들 이동 불가
        history2.getTableHeader().setResizingAllowed(false); 	// 컬럼 크기 조절 불가
		
        h2ScrollPane = new JScrollPane(history2);	//table을 스크롤 패인으로
		h2ScrollPane.setBounds(499, 254, 380, 256);
		mypagePanel.add(h2ScrollPane);
		
		//Mypage 뒤로가기 버튼
		MPbackBtn = new JButton("");
		MPbackBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);
				bfPanel.setVisible(true);
				currPanel=bfPanel;
			}
		});
		MPbackBtn.setIcon(new ImageIcon(".\\Image\\back_btn.PNG"));
		MPbackBtn.setBounds(10, 10, 51, 46);
		MPbackBtn.setBorder(null);
		mypagePanel.add(MPbackBtn);
		
		//회원정보 수정 버튼
		JButton editInfoBtn = new JButton("");
		editInfoBtn.setIcon(new ImageIcon(".\\Image\\edit_info_btn.PNG"));
		editInfoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InfoEdit(curr_user, connection);
			}
		});
		editInfoBtn.setBounds(348, 22, 155, 46);
		editInfoBtn.setBorder(null);
		mypagePanel.add(editInfoBtn);
		
		//채팅 알림
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(101, 138, 756, 38);
		mypagePanel.add(lblNewLabel);
		
		mypagePanel.setVisible(true);
		
		return mypagePanel;
	}
	
	//생필품 패널 생성
	private JPanel Create_Nec_Panel(db_connection connection) {
		DefaultTableCellRenderer colC = new DefaultTableCellRenderer();
	    colC.setBackground(new Color(255, 255, 208));	
	    //table의 글 정렬을 위한
	    DefaultTableCellRenderer celAlignCenter=new DefaultTableCellRenderer();
	    celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
	    DefaultTableCellRenderer celAlignRight=new DefaultTableCellRenderer();
	    celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
	    //'현황'셀의 글자 색깔 변경과 가운데 정룔을 위한 DefaultTableCellRenderer 객체 선언
	    DefaultTableCellRenderer celOrCenter=new DefaultTableCellRenderer();
	    celOrCenter.setForeground(new Color(255, 127, 0));
	    celOrCenter.setHorizontalAlignment(JLabel.CENTER);
	    
		ImagePanel necPanel = new ImagePanel(new ImageIcon(".\\Image\\board.png").getImage());
		frame.getContentPane().add(necPanel);
		
		necPanel.setBounds(0, 0, 960, 540);
		necPanel.setLayout(null);
		/*게시판 table*/
		String[] necHeader=new String[] {"번호","장소","시간","제목","작성자","현황","채팅"};
		//예시
		nec_board_data = connection.show_board("nes_board");
		
		DefaultTableModel necMod=new DefaultTableModel(nec_board_data, necHeader) {	// 수정 불가
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		ntable=new JTable(necMod);
        
	    //테이블 간격 조정
	    ntable.getColumn("번호").setPreferredWidth(40);
        ntable.getColumn("번호").setCellRenderer(celAlignCenter);
        ntable.getColumn("장소").setPreferredWidth(60);
        ntable.getColumn("장소").setCellRenderer(celAlignCenter);
        ntable.getColumn("시간").setPreferredWidth(80);
        ntable.getColumn("시간").setCellRenderer(celAlignCenter);
        ntable.getColumn("제목").setPreferredWidth(306);
        ntable.getColumn("작성자").setPreferredWidth(100);
        ntable.getColumn("작성자").setCellRenderer(celAlignCenter);
        ntable.getColumn("현황").setPreferredWidth(80);
        ntable.getColumn("현황").setCellRenderer(celOrCenter);
        ntable.getColumn("채팅").setPreferredWidth(80);
        TableCellRenderer renderer = new TableCellRenderer();	//채팅 버튼 구현
        ntable.getColumn("채팅").setCellRenderer(renderer);
       
		ntable.setRowHeight(30);
		ntable.setFont(new Font("Sanserif", Font.BOLD, 17));
		ntable.setPreferredScrollableViewportSize(new Dimension(746,392));
		ntable.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
        ntable.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가
        ntable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//table 여러개 선택 불가
		JScrollPane scrollPane = new JScrollPane(ntable);
		scrollPane.setBounds(189, 92, 746, 392);;
		necPanel.add(scrollPane);
		
		/*글 찾기 검색란*/
		searchField = new JTextField("");
		searchField.setToolTipText("");
		searchField.setForeground(Color.DARK_GRAY);
		searchField.setFont(new Font("Dialog", Font.PLAIN, 15));
		searchField.setBounds(686, 29, 149, 36);
		searchField.setBorder(null);
		necPanel.add(searchField);
		searchField.setColumns(10);
		
		/*글 찾기 버튼*/
		JButton searchBtn = new JButton("");
		searchBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//searchField 저장하기
				String search_text = "'" + searchField.getText() + "'";
				nec_board_data = connection.search_post("nes_board", search_text);
				
			}
		});
		
		searchBtn.setIcon(new ImageIcon(".\\Image\\search_btn.PNG"));
		searchBtn.setBounds(836, 25, 40, 41);
		searchBtn.setBorder(null);
		necPanel.add(searchBtn);
		
		/*회원정보 버튼*/
		JButton myBtn = new JButton("");
		myBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);
				bfPanel=currPanel;
				//Create_Mypage_Panel(connection);
				//mypagePanel.setVisible(true);
				currPanel=Create_Mypage_Panel(connection);
			}
		});
		myBtn.setIcon(new ImageIcon(".\\Image\\my_btn.PNG"));
		myBtn.setBounds(886, 20, 49, 48);
		myBtn.setBorder(null);
		necPanel.add(myBtn);
		
		/*글쓰기 버튼*/
		JButton writeBtn = new JButton("");
		writeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//글 작성 event
				BoardWrite boardWrite = new BoardWrite("nes_board", curr_user);
				if(boardWrite.return_is_enrolled()==true) {
					currPanel.setVisible(false);
					currPanel = Create_Nec_Panel(connection);
				}
				else {
					currPanel.setVisible(false);
					currPanel = Create_Nec_Panel(connection);
				}
			}
		});
		writeBtn.setIcon(new ImageIcon(".\\Image\\write_btn.PNG"));
		writeBtn.setBounds(864, 488, 78, 35);
		writeBtn.setBorder(null);
		necPanel.add(writeBtn);
		
		/*뒤로가기 버튼*/
		JButton boardBackBtn = new JButton("");
		boardBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);
				currPanel = Create_Choice_Panel(connection);
			}
		});
		boardBackBtn.setIcon(new ImageIcon(".\\Image\\back_btn.PNG"));
		boardBackBtn.setBounds(0, 491, 49, 45);
		boardBackBtn.setBorder(null);
		necPanel.add(boardBackBtn);
		
		necPanel.setVisible(true);
		
		return necPanel;
	}
	
	private JPanel Create_Food_Panel(db_connection connection) {
		DefaultTableCellRenderer colC = new DefaultTableCellRenderer();
	    colC.setBackground(new Color(255, 255, 208));	
	    //table의 글 정렬을 위한
	    DefaultTableCellRenderer celAlignCenter=new DefaultTableCellRenderer();
	    celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
	    DefaultTableCellRenderer celAlignRight=new DefaultTableCellRenderer();
	    celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
	    //'현황'셀의 글자 색깔 변경과 가운데 정룔을 위한 DefaultTableCellRenderer 객체 선언
	    DefaultTableCellRenderer celOrCenter=new DefaultTableCellRenderer();
	    celOrCenter.setForeground(new Color(255, 127, 0));
	    celOrCenter.setHorizontalAlignment(JLabel.CENTER);
		
		ImagePanel foodPanel = new ImagePanel(new ImageIcon(".\\Image\\board.png").getImage());
		frame.getContentPane().add(foodPanel);
		
		foodPanel.setBounds(0, 0, 960, 540);
		foodPanel.setLayout(null);
		/*게시판 table*/
		String[] header=new String[] {"번호","장소","시간","제목","작성자","현황","채팅"};
		//예시
		food_board_data = connection.show_board("food_board");
		
		for(int i=0; i<12; i++) {
			System.out.println(food_board_data[i][0]);
		}
		
		DefaultTableModel foodMod=new DefaultTableModel(food_board_data, header) {	// 수정 불가
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		ftable=new JTable(foodMod);
	  //테이블 간격 조정
	    ftable.getColumn("번호").setPreferredWidth(40);
        ftable.getColumn("번호").setCellRenderer(celAlignCenter);
        ftable.getColumn("장소").setPreferredWidth(60);
        ftable.getColumn("장소").setCellRenderer(celAlignCenter);
        ftable.getColumn("시간").setPreferredWidth(80);
        ftable.getColumn("시간").setCellRenderer(celAlignCenter);
        ftable.getColumn("제목").setPreferredWidth(306);
        ftable.getColumn("작성자").setPreferredWidth(100);
        ftable.getColumn("작성자").setCellRenderer(celAlignCenter);
        ftable.getColumn("현황").setPreferredWidth(80);
        ftable.getColumn("현황").setCellRenderer(celOrCenter);
        ftable.getColumn("채팅").setPreferredWidth(80);
        TableCellRenderer renderer = new TableCellRenderer();	//채팅 버튼 구현
        ftable.getColumn("채팅").setCellRenderer(renderer);	//채팅버튼 구현
     
		ftable.setRowHeight(30);
		ftable.setFont(new Font("Sanserif", Font.BOLD, 17));
		ftable.setPreferredScrollableViewportSize(new Dimension(746, 392));
		ftable.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
        ftable.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가
        ftable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//table 여러개 선택 불가
        
        JScrollPane scrollPane = new JScrollPane(ftable);
		scrollPane.setBounds(189, 92, 746, 392);
		foodPanel.add(scrollPane);
		
		/*글 찾기 검색란*/
		searchField = new JTextField("");
		searchField.setToolTipText("");
		searchField.setForeground(Color.DARK_GRAY);
		searchField.setFont(new Font("고도 M",Font.PLAIN,19));
		searchField.setBounds(686, 29, 149, 36);
		searchField.setBorder(null);
		foodPanel.add(searchField);
		searchField.setColumns(10);
		
		/*글 찾기 버튼*/
		searchBtn = new JButton("");
		searchBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//searchField 저장하기
				String search_text = "'" + searchField.getText() + "'";
				connection.search_post("food_board", search_text);
			}
		});
		searchBtn.setIcon(new ImageIcon(".\\Image\\search_btn.PNG"));
		searchBtn.setBounds(836, 25, 40, 41);
		searchBtn.setBorder(null);
		foodPanel.add(searchBtn);
		
		/*회원정보 버튼*/
		JButton myBtn = new JButton("");
		myBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);
				bfPanel=currPanel;
				currPanel=Create_Mypage_Panel(connection);
			}
		});
		myBtn.setIcon(new ImageIcon(".\\Image\\my_btn.PNG"));
		myBtn.setBounds(886, 20, 49, 48);
		myBtn.setBorder(null);
		foodPanel.add(myBtn);
		
		/*글쓰기 버튼*/
		JButton writeBtn = new JButton("");
		writeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//글 작성 event
				BoardWrite boardWrite = new BoardWrite("food_board", curr_user);
				if(boardWrite.return_is_enrolled()==true) {
					currPanel.setVisible(false);
					currPanel = Create_Nec_Panel(connection);
				}
				else {
					currPanel.setVisible(false);
					currPanel = Create_Nec_Panel(connection);
				}
			}
		});
		
		writeBtn.setIcon(new ImageIcon(".\\Image\\write_btn.PNG"));
		writeBtn.setBounds(864, 488, 78, 35);
		writeBtn.setBorder(null);
		foodPanel.add(writeBtn);
		
		/*뒤로가기 버튼*/
		JButton boardBackBtn = new JButton("");
		boardBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);
				//choicePanel.setVisible(true);
				currPanel = Create_Choice_Panel(connection);
			}
		});
		boardBackBtn.setIcon(new ImageIcon(".\\Image\\back_btn.PNG"));
		boardBackBtn.setBounds(0, 491, 49, 45);
		boardBackBtn.setBorder(null);
		foodPanel.add(boardBackBtn);
		
		foodPanel.setVisible(true);
		
		return foodPanel;
	}
	
	private JPanel Create_Choice_Panel(db_connection connection) {
		ImagePanel choicePanel = new ImagePanel(new ImageIcon(".\\Image\\choice.png").getImage());
		frame.getContentPane().add(choicePanel);
		
		choicePanel.setBounds(0, 0, 960, 540);
		choicePanel.setLayout(null);
		
		JButton foodBtn = new JButton("");
		foodBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);
				//foodPanel.setVisible(true);
				currPanel = Create_Food_Panel(connection);
			}	
		});
		foodBtn.setBounds(129, 156, 306, 307);
		foodBtn.setIcon(new ImageIcon(".\\Image\\Ch_food.PNG"));
		foodBtn.setBorder(null);
		choicePanel.add(foodBtn);
		
		JButton necBtn = new JButton("");
		necBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);
				currPanel = Create_Nec_Panel(connection);
			}	
		});
		necBtn.setBounds(526, 157, 305, 305);
		necBtn.setIcon(new ImageIcon(".\\Image\\Ch_nec.PNG"));
		necBtn.setBorder(null);
		choicePanel.add(necBtn);
		
		choicePanel.setVisible(true);
		
		return choicePanel;
	}
	
	private JPanel Create_Join_Panel(db_connection connection) {
		ImagePanel joinPanel = new ImagePanel(new ImageIcon(".\\Image\\join.png").getImage());
		frame.getContentPane().add(joinPanel);
		
		joinPanel.setBounds(0, 0, 960, 540);
		joinPanel.setLayout(null);
		
		joinNameField = new JTextField(); //이름
		joinNameField.setBounds(235, 175, 199, 25);
		joinNameField.setFont(new Font("고도 M",Font.PLAIN,19));
		joinNameField.setColumns(10);
		joinNameField.setBorder(null);
		joinPanel.add(joinNameField);

		joinIdField = new JTextField(); //아이디
		joinIdField.setBounds(235, 232, 199, 25);
		joinIdField.setFont(new Font("고도 M",Font.PLAIN,19));
		joinIdField.setColumns(10);
		joinIdField.setBorder(null);
		joinPanel.add(joinIdField);
		
		joinPwField = new JPasswordField(); //비밀번호
		joinPwField.setBounds(235, 287, 199, 25);
		joinPwField.setFont(new Font("Dialog", Font.PLAIN, 17));
		joinPwField.setColumns(10);
		joinPwField.setBorder(null);
		joinPanel.add(joinPwField);
		
		joinPwCheckField = new JPasswordField(); //비밀번호 확인
		joinPwCheckField.setBounds(235, 338, 199, 25);
		joinPwCheckField.setFont(new Font("Dialog", Font.PLAIN, 17));
		joinPwCheckField.setColumns(10);
		joinPwCheckField.setBorder(null);
		joinPanel.add(joinPwCheckField);
		
		JComboBox comboBox = new JComboBox(place); //장소
		comboBox.setBounds(583, 175, 207, 27);
		comboBox.setFont(new Font("고도 M",Font.PLAIN,19));
		joinPanel.add(comboBox);
		
		joinNickField = new JTextField(); //닉네임
		joinNickField.setBounds(583, 232, 207, 27);
		joinNickField.setFont(new Font("고도 M",Font.PLAIN,19));
		joinNickField.setColumns(10);
		joinNickField.setBorder(null);
		joinPanel.add(joinNickField);

		enrollBtn = new JButton("");				//등록 버튼
		enrollBtn.setBounds(419, 426, 122, 49);
		enrollBtn.setBorder(null);
		enrollBtn.addActionListener(new ActionListener(){	

			@Override
			public void actionPerformed(ActionEvent e) {	//등록하기 버튼을 눌렀을 때의 동작
				try {
					String nameString = "'" + joinNameField.getText() + "'";			//이름
					String nicknameString = "'" + joinNickField.getText() + "'";		//닉네임
					String idString = "'" + joinIdField.getText() + "'";				//아이디
					String pwdString = "'" + joinPwField.getText() + "'";				//비번
					String pwdcheckString = "'" + joinPwCheckField.getText() + "'";		//비번 확인
					String homeString = "'" + comboBox.getSelectedItem().toString() + "'";	//사는 곳
					
					if(pwdString.equals(pwdcheckString)) {		//비번과 비번확인이 일치하면
						if(connection.input_user_info(nameString, nicknameString, idString, pwdString, homeString)) {
							JOptionPane.showMessageDialog(null,"회원가입을 축하합니다!");
							currPanel.setVisible(false);
							//loginPanel.setVisible(true);
							currPanel = Create_login_Panel(connection);
							return;
						}
						else {
							JOptionPane.showMessageDialog(null,"이미 존재하는 아이디입니다.");	
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"비밀번호가 일치하지 않습니다. 다시 확인하세요.");
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null,"Unknown Error! 회원가입에 실패하였습니다! 시나몬 개발자 연락처: 01030135810");
				}
				/*
				JOptionPane.showMessageDialog(null,"회원가입을 축하합니다!");
				currPanel.setVisible(false);
				loginPanel.setVisible(true);
				currPanel = loginPanel;		
				*/		
			}		
		});
		enrollBtn.setPressedIcon(new ImageIcon(".\\Image\\enroll_click_btn.PNG"));
		joinPanel.add(enrollBtn);
		enrollBtn.setIcon(new ImageIcon(".\\Image\\enroll_btn.PNG"));
		
		//뒤로가기 버튼
		backBtn = new JButton("");		
		backBtn.setBounds(5, 10, 49, 49);
		backBtn.setBorder(null);
		backBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);	//현재 패널(회원가입) 안보이게 하고
				//loginPanel.setVisible(true);	//로그인 패널을 다시 보이게 하고
				currPanel = Create_login_Panel(connection);	//현재 패널=로그인 패널	
			}		
		});
		backBtn.setIcon(new ImageIcon(".\\Image\\back_btn.PNG"));
		joinPanel.add(backBtn);
		
		joinPanel.setVisible(true);
		
		return joinPanel;
	}
	
	private JPanel Create_login_Panel(db_connection connection) {
		ImagePanel loginPanel = new ImagePanel(new ImageIcon(".\\Image\\login.png").getImage());
		frame.getContentPane().add(loginPanel);
		
		loginPanel.setBounds(0, 0, 960, 540);
		loginPanel.setLayout(null);
		currPanel=loginPanel;
		frame.setSize(loginPanel.getDimension());
		frame.setPreferredSize(loginPanel.getDimension());
		
		idField = new JTextField();
		idField.setBounds(183, 274, 198, 24);
		idField.setFont(new Font("고도 M",Font.PLAIN,19));
		loginPanel.add(idField);
		idField.setColumns(10);
		idField.setBorder(null);
		
		pwField = new JPasswordField();
		pwField.setBounds(183, 322, 198, 26);
		pwField.setFont(new Font("Dialog", Font.BOLD, 20));
		loginPanel.add(pwField);
		pwField.setBorder(null);
		
		JButton loginBtn = new JButton("");			//loginBtn
		loginBtn.setBounds(424, 291, 131, 51);
		loginBtn.setBorder(null);
		
		loginBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String idString = "'" + idField.getText() + "'";		//id
				String pwdString = "'" + pwField.getText() + "'";		//password
				if(connection.login(idString, pwdString)) {				//해당되는 아이디와 패스워드 일치하면 true 반환
					currPanel.setVisible(false);
					
					currPanel = Create_Choice_Panel(connection);
					curr_user = connection.return_user_info(idString);	//현재 로그인중인 회원 정보
				}
				else {
					JOptionPane.showMessageDialog(null,"아이디/비번이 일치하지 않거나, 존재하지 않는 계정입니다");
				}
			}
		});
		loginBtn.setIcon(new ImageIcon(".\\Image\\login_btn.PNG"));
		loginBtn.setPressedIcon(new ImageIcon(".\\Image\\login_click_btn.PNG"));
		loginPanel.add(loginBtn);
		
		JButton joinBtn = new JButton("");		//joinBtn
		joinBtn.setBounds(204, 374, 131, 51);
		joinBtn.setBorder(null);
		joinBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);
				currPanel = Create_Join_Panel(connection);
			}
		});
		joinBtn.setIcon(new ImageIcon(".\\Image\\join_btn.PNG"));
		joinBtn.setPressedIcon(new ImageIcon(".\\Image\\join_click_btn.PNG"));
		loginPanel.add(joinBtn);
		
		loginPanel.setVisible(true);
		
		return loginPanel;
	}
	
	private void initialize(db_connection connection) {
		//frame 설정
		frame = new JFrame();
		frame.setTitle("시나몬");
		frame.setBounds(100, 100, 960, 540);
		frame.setPreferredSize(new Dimension(960,540));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//화면을 닫으면 프로그램 종료
		frame.setResizable(false); 		//크기 고정
		//
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image logo=tk.getImage(".\\Image\\logo.png");
		frame.setIconImage(logo);

		
		currPanel=Create_login_Panel(connection);
	    
	    
		/**************************** My page (회원 히스토리 패널)*****************************************/
		
		
		/****************************necPanel (생필품 게시판 패널)*****************************************/
		
		
		
		/****************************foodPanel (음식 게시판 패널)*****************************************/
		
		
		
		/****************************Choice panel (시나 음식 시나 생필품)*********************************************/
		
		
		
		
		/**********************************Join panel (회원가입 패널)******************************************/
		
		
		
		/***************************************login panel(로그인 패널)***************************************/
		
		
		Create_Join_Panel(connection).setVisible(false);
		Create_Nec_Panel(connection).setVisible(false);
		Create_Food_Panel(connection).setVisible(false);
		Create_Choice_Panel(connection).setVisible(false);
		Create_Mypage_Panel(connection).setVisible(false);
	}
}

