package SinamonUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
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
	private final String ID="Hello";
	private final String PASS="1234";
	//BoardWrite 열기위해서 잠시 선언만해둠 >>지우기
	String food_board;		
	String nec_board;
	String board_name;
	Object[] curr_user;
	/*
	 * ID PW 저장
	 *  
	 * */
	
	private final String place[]= {"정 문", "중 문","서 문","후 문","본 관","양 성 재","양 진 재"};
	
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
	private JScrollPane h2ScrollPane;	///Mypage-내가 시나몬 한 history 테이블의 스크롤패
	private JTextField searchField;
	private JButton MPbackBtn;
	private JCheckBox frontCkBx;
	private JPanel curMenuPanel;		//board에서 장소 버튼 처리를 위해 현재 버튼 생성
	
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
		initialize();
	}
	
	 public class MyTableCellRenderer extends DefaultTableCellRenderer{ 
		 @Override 
		 public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column) { 
			 Component chatBtn = null; 
			 if(column==6){
				 chatBtn = new JButton("채팅하기");
				 chatBtn.setFont(new Font("고도 M", Font.BOLD, 13));
				 ((JButton) chatBtn).addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							System.out.print("good");
						}
					});

			}
			 return chatBtn; 
		 }

	 }
	 class MyMouserAdapter extends MouseAdapter{
			public void mouseClicked(MouseEvent e) {
				System.out.print("good");
			}
		}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//frame 설정
		frame = new JFrame();
		frame.setTitle("시나몬");
		frame.setBounds(100, 100, 960, 540);
		frame.setPreferredSize(new Dimension(960,540));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//화면을 닫으면 프로그램 종료
		frame.setResizable(false); 		//크기 고정
		//로고
		Toolkit tk = Toolkit.getDefaultToolkit();
        Image logo=tk.getImage(".\\Image\\logo.png");
        frame.setIconImage(logo);
		
		
		//폰트 설정
		//Font Jua=new Font("배달의민족 주아",Font.PLAIN,19);
		Font Jua=new Font("고도 M",Font.PLAIN,19);
		
		//패널 이미지
		ImagePanel mypagePanel = new ImagePanel(new ImageIcon(".\\Image\\mypage.png").getImage());
		frame.getContentPane().add(mypagePanel);
		ImagePanel foodPanel = new ImagePanel(new ImageIcon(".\\Image\\board.png").getImage());
		frame.getContentPane().add(foodPanel);
		ImagePanel necPanel = new ImagePanel(new ImageIcon(".\\Image\\board.png").getImage());
		frame.getContentPane().add(necPanel);
		ImagePanel choicePanel = new ImagePanel(new ImageIcon(".\\Image\\choice.png").getImage());
		frame.getContentPane().add(choicePanel);
		ImagePanel joinPanel = new ImagePanel(new ImageIcon(".\\Image\\join.png").getImage());
		frame.getContentPane().add(joinPanel);
		ImagePanel loginPanel = new ImagePanel(new ImageIcon(".\\Image\\login.png").getImage());
		frame.getContentPane().add(loginPanel);
		
		//table의 '채팅하기' 셀의 색깔 변경을 위한 DefaultTableCellRenderer 객체 선언
		DefaultTableCellRenderer colC = new DefaultTableCellRenderer();
	    colC.setBackground(new Color(0, 118, 134));	
	    //table의 글 정렬을 위한
	    DefaultTableCellRenderer celAlignCenter=new DefaultTableCellRenderer();
	    celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
	    DefaultTableCellRenderer celAlignRight=new DefaultTableCellRenderer();
	    celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
	    //'현황'셀의 글자 색깔 변경과 가운데 정룔을 위한 DefaultTableCellRenderer 객체 선언
	    DefaultTableCellRenderer celOrCenter=new DefaultTableCellRenderer();
	    celOrCenter.setForeground(new Color(255, 127, 0));
	    celOrCenter.setHorizontalAlignment(JLabel.CENTER);
	    
	    /**************************** My page (회원 히스토리 패널)*****************************************/
		mypagePanel.setBounds(0,0,960,540);	//패널 사이즈
		mypagePanel.setLayout(null);
		
		//history1 구현 - [내가 쓴 글]
		String[] hHeader=new String[] {"제목", "현황"};	//히스토리 테이블 헤더
		Object[][] hData1=new Object[][] {					
			{"푸라닭 먹을 사람","완료"},
			{"엽떡 먹을 사람","완료"},
			{"곱창전골 먹을 사람","완료"},
			{"휴지 나눌 사람","예정"},
			{"스시 먹을 사람","진행 중"}
		};
		
		//DefaultTableModel을 사용하여 내용 수정 불가하게
		DefaultTableModel modH1 = new DefaultTableModel(hData1,hHeader) {
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
            	if(value.equals("예정"))									//'에정'일 경우
            		new BoardEdit();									//수정창이 나타남
            }
        });
		//테이블 세부 설정
        history1.setRowHeight(25);	//테이블 행 간격
		history1.setFont(new Font("Sanserif", Font.BOLD, 15));	//테이블 폰트
	    history1.getColumn("제목").setPreferredWidth(300);			//테이블 열 간격
	    history1.getColumn("현황").setPreferredWidth(80);
	    history1.getColumn("현황").setCellRenderer(celOrCenter);	//'현황': 글자 주황, 가운데 정렬
		history1.setPreferredScrollableViewportSize(new Dimension(380,256));
        history1.getTableHeader().setReorderingAllowed(false); 	// 컬럼들 이동 불가
        history1.getTableHeader().setResizingAllowed(false); 	// 컬럼 크기 조절 불가
		
        h1ScrollPane = new JScrollPane(history1);	//table을 스크롤 패인으로
		h1ScrollPane.setBounds(77, 254, 380, 256);
		mypagePanel.add(h1ScrollPane);
		
		//history2 구현 - [내가 시나몬 한 history]
		Object[][] hData2=new Object[][] {
			{"푸라닭","완료"},
			{"엽떡","완료"},
			{"곱창전골","완료"},
			{"휴지","진행 중"},
			{"스시","진행 중"}
		};
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
                String value=(String) history2.getValueAt(row,col);			//선택한 셀의 값을 저장하여
	        	if(value.equals("진행 중"))									//'진행 중'일 경우
	        		new BoardLook(board_name,curr_user);					//게시글 보기 창이 나타남
            }
        });
		//테이블 세부 설정
        history2.setRowHeight(25);	//테이블 행 간격
		history2.setFont(new Font("Sanserif", Font.BOLD, 15));	//테이블 폰트
	    history2.getColumn("제목").setPreferredWidth(300);			//테이블 열 간격
	    history2.getColumn("현황").setPreferredWidth(80);
	    history2.getColumn("현황").setCellRenderer(celOrCenter);	//'현황': 글자 주황색, 가운데 정렬
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
				new InfoEdit(curr_user);
			}
		});
		editInfoBtn.setBounds(348, 22, 155, 46);
		editInfoBtn.setBorder(null);
		mypagePanel.add(editInfoBtn);
		
		//채팅 알림
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(101, 138, 756, 38);
		mypagePanel.add(lblNewLabel);
	    
		/****************************necPanel (생필품 게시판 패널)*****************************************/
		
		necPanel.setBounds(0, 0, 960, 540);
		necPanel.setLayout(null);
		/*게시판 table*/
		String[] necHeader=new String[] {"번호","장소","시간","제목","작성자","현황","채팅"};
		//예시
		Object[][] necData=new Object[][] {
			{"01","양진재","16시","휴지 나눠요","초코왕자","예정",""},
			{"02","중문","12-2시", "플리스 1+1 나눠요", "초코공주","진행 중",""}		
		};
		DefaultTableModel necMod=new DefaultTableModel(necData,necHeader) {	// 수정 불가
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		ntable=new JTable(necMod);
		//테이블이 채팅하기 셀을 클릭하면 채팅창이 나타남
        	ntable.addMouseListener(new MouseAdapter() {
           		@Override
           		public void mouseClicked(MouseEvent e) {
            			int row = ntable.getSelectedRow();	//해당 셀의 행을 받아올 수 있음
                		int col = ntable.getSelectedColumn();	//해당 셀의 열을 받아올 수 있음
				if(col==6){
					/*
					* 채팅창 구현!!
					*/
					//확인용
					System.out.print("Chat");
				}
                		
			}
       		});
        
	    //테이블 간격 조정
	    ntable.getColumn("번호").setPreferredWidth(40);
        ntable.getColumn("번호").setCellRenderer(celAlignCenter);
        ntable.getColumn("장소").setPreferredWidth(70);
        ntable.getColumn("장소").setCellRenderer(celAlignCenter);
        ntable.getColumn("시간").setPreferredWidth(70);
        ntable.getColumn("시간").setCellRenderer(celAlignCenter);
        ntable.getColumn("제목").setPreferredWidth(306);
        ntable.getColumn("작성자").setPreferredWidth(100);
        ntable.getColumn("작성자").setCellRenderer(celAlignCenter);
        ntable.getColumn("현황").setPreferredWidth(80);
        ntable.getColumn("현황").setCellRenderer(celOrCenter);
        ntable.getColumn("채팅").setPreferredWidth(80);
       
		ntable.setRowHeight(30);
		ntable.setFont(new Font("Sanserif", Font.BOLD, 17));
		ntable.setPreferredScrollableViewportSize(new Dimension(746,392));
		ntable.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
        ntable.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가
        ntable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//table 여러개 선택 불가
		JScrollPane scrollPane = new JScrollPane(ntable);
		DefaultTableCellRenderer renderer = new MyTableCellRenderer();	//채팅 버튼 구현
        ntable.getColumn("채팅").setCellRenderer(renderer);
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
				/*
				 * 글 찾기
				 * 
				 * */
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
				mypagePanel.setVisible(true);
				currPanel=mypagePanel;
			}
		});
		myBtn.setIcon(new ImageIcon(".\\Image\\my_btn.PNG"));
		myBtn.setBounds(886, 20, 48, 47);
		myBtn.setBorder(null);
		necPanel.add(myBtn);
		
		/*글쓰기 버튼*/
		JButton writeBtn = new JButton("");
		writeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//글 작성 event
				/*글쓰기 버튼*/
				new BoardWrite("nes_board", curr_user);
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
				choicePanel.setVisible(true);
				currPanel = choicePanel;
			}
		});
		boardBackBtn.setIcon(new ImageIcon(".\\Image\\back_btn.PNG"));
		boardBackBtn.setBounds(0, 491, 49, 45);
		boardBackBtn.setBorder(null);
		necPanel.add(boardBackBtn);
		
		/*
		 * 장소 메뉴 버튼 구현
		 */
		ChatBtnDesign nAllBtn = new ChatBtnDesign("모든 글 보기");		//모든 글 보기
		nAllBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		nAllBtn.setFont(new Font("배달의민족 주아", Font.BOLD, 25));
		nAllBtn.setBounds(0, 96, 158, 48);
		necPanel.add(nAllBtn);
		
		ChatBtnDesign nCentralBtn = new ChatBtnDesign(" 중 문 ");		//중문
		nCentralBtn.setFont(new Font("배달의민족 주아", Font.PLAIN, 23));
		nCentralBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		nCentralBtn.setBounds(0, 154, 158, 36);
		necPanel.add(nCentralBtn);
		
		ChatBtnDesign nFrontBtn = new ChatBtnDesign(" 정 문 ");		//정문
		nFrontBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		nFrontBtn.setFont(new Font("배달의민족 주아", Font.PLAIN, 23));
		nFrontBtn.setBounds(0, 191, 158, 36);
		necPanel.add(nFrontBtn);
		
		ChatBtnDesign nWestBtn = new ChatBtnDesign(" 서 문 ");			//서문
		nWestBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		nWestBtn.setFont(new Font("배달의민족 주아", Font.PLAIN, 23));
		nWestBtn.setBounds(0, 228, 158, 36);
		necPanel.add(nWestBtn);
		
		ChatBtnDesign nBackGateBtn = new ChatBtnDesign(" 후 문 ");		//후문
		nBackGateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		nBackGateBtn.setFont(new Font("배달의민족 주아", Font.PLAIN, 23));
		nBackGateBtn.setBounds(0, 265, 158, 36);
		necPanel.add(nBackGateBtn);
		
		ChatBtnDesign nBonBtn = new ChatBtnDesign(" 본 관 ");			//본관
		nBonBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		nBonBtn.setFont(new Font("배달의민족 주아", Font.PLAIN, 23));
		nBonBtn.setBounds(0, 303, 158, 36);
		necPanel.add(nBonBtn);
		
		ChatBtnDesign nYSungBtn = new ChatBtnDesign(" 양성재 ");		//양성재
		nYSungBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		nYSungBtn.setFont(new Font("배달의민족 주아", Font.PLAIN, 23));
		nYSungBtn.setBounds(0, 340, 158, 36);
		necPanel.add(nYSungBtn);
		
		ChatBtnDesign nYJinBtn = new ChatBtnDesign(" 양진재");			//양진재
		nYJinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		nYJinBtn.setFont(new Font("배달의민족 주아", Font.PLAIN, 23));
		nYJinBtn.setBounds(0, 377, 158, 36);
		necPanel.add(nYJinBtn);
		
		/****************************foodPanel (음식 게시판 패널)*****************************************/
		
		foodPanel.setBounds(0, 0, 960, 540);
		foodPanel.setLayout(null);
		/*게시판 table*/
		String[] header=new String[] {"번호","장소","시간","제목","작성자","현황","채팅"};
		//예시
		Object[][] data=new Object[][] {
			{"01","양진재","아무때나","푸라닭 나눠먹어요","초코왕자","진행 중"},
			{"01","서문","16시","푸라닭 나눠먹어요","초코왕자","진행 중"},
			{"01","서문","16시","푸라닭 나눠먹어요","초코왕자","진행 중"},
			{"01","서문","16시","푸라닭 나눠먹어요","초코왕자","진행 중"},
			{"01","서문","16시","푸라닭 나눠먹어요","초코왕자","진행 중"},
			{"01","서문","16시","푸라닭 나눠먹어요","초코왕자","진행 중"},
			{"01","서문","16시","푸라닭 나눠먹어요","초코왕자","진행 중"},
			{"01","서문","16시","푸라닭 나눠먹어요","초코왕자","진행 중"},
			{"01","서문","16시","푸라닭 나눠먹어요","초코왕자","진행 중"},
			{"01","서문","16시","푸라닭 나눠먹어요","초코왕자","진행 중"},
			{"01","서문","16시","푸라닭 나눠먹어요","초코왕자","진행 중"},
			{"01","서문","16시","푸라닭 나눠먹어요","초코왕자","진행 중"},
			{"01","서문","16시","푸라닭 나눠먹어요","초코왕자","진행 중"},
			{"01","서문","16시","푸라닭 나눠먹어요","초코왕자","진행 중"},
			{"01","서문","16시","푸라닭 나눠먹어요","초코왕자","진행 중"},
			{"01","서문","16시","푸라닭 나눠먹어요","초코왕자","진행 중"},
			{"01","서문","16시","푸라닭 나눠먹어요","초코왕자","진행 중"},
			{"01","서문","16시","푸라닭 나눠먹어요","초코왕자","진행 중"},
			{"01","서문","16시","푸라닭 나눠먹어요","초코왕자","진행 중"},
			{"01","서문","16시","푸라닭 나눠먹어요","초코왕자","진행 중"},
			{"01","서문","16시","푸라닭 나눠먹어요","초코왕자","진행 중"},
			{"01","서문","16시","푸라닭 나눠먹어요","초코왕자","진행 중"}
		};
		DefaultTableModel foodMod=new DefaultTableModel(data,header) {	// 수정 불가
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		ftable=new JTable(foodMod);
		//테이블이 채팅하기 셀을 클릭하면 채팅창이 나타남
        	ftable.addMouseListener(new MouseAdapter() {
           		@Override
           		public void mouseClicked(MouseEvent e) {
            			int row = ftable.getSelectedRow();	//해당 셀의 행을 받아올 수 있음
                		int col = ftable.getSelectedColumn();	//해당 셀의 열을 받아올 수 있음
				if(col==6){	
					/*
					* 채팅창 구현!!
					*/
					//확인용
					System.out.print("Chat");
				}
                		
			}
       		});
		//테이블 간격 조정
	    ftable.getColumn("번호").setPreferredWidth(40);
        ftable.getColumn("번호").setCellRenderer(celAlignCenter);
        ftable.getColumn("장소").setPreferredWidth(70);
        ftable.getColumn("장소").setCellRenderer(celAlignCenter);
        ftable.getColumn("시간").setPreferredWidth(80);
        ftable.getColumn("시간").setCellRenderer(celAlignCenter);
        ftable.getColumn("제목").setPreferredWidth(306);
        ftable.getColumn("작성자").setPreferredWidth(100);
        ftable.getColumn("작성자").setCellRenderer(celAlignCenter);
        ftable.getColumn("현황").setPreferredWidth(70);
        ftable.getColumn("현황").setCellRenderer(celOrCenter);
        ftable.getColumn("채팅").setPreferredWidth(90);
        ftable.getColumn("채팅").setCellRenderer(renderer);	//채팅버튼 구현
     
		ftable.setRowHeight(30);
		ftable.setFont(Jua);
		ftable.setPreferredScrollableViewportSize(new Dimension(746, 392));
		ftable.getTableHeader().setReorderingAllowed(false); // 컬럼들 이동 불가
        ftable.getTableHeader().setResizingAllowed(false); // 컬럼 크기 조절 불가
        ftable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//table 여러개 선택 불가
        
		scrollPane = new JScrollPane(ftable);
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
				/*
				 * 글 찾기
				 * 
				 * */
				System.out.print("good");
			}
		});
		searchBtn.setIcon(new ImageIcon(".\\Image\\search_btn.PNG"));
		searchBtn.setBounds(836, 25, 41, 39);
		searchBtn.setBorder(null);
		foodPanel.add(searchBtn);
		
		/*회원정보 버튼*/
		myBtn = new JButton("");
		myBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);
				bfPanel=currPanel;
				mypagePanel.setVisible(true);
				currPanel=mypagePanel;
			}
		});
		myBtn.setIcon(new ImageIcon(".\\Image\\my_btn.PNG"));
		myBtn.setBounds(886, 20, 49, 48);
		myBtn.setBorder(null);
		foodPanel.add(myBtn);
		
		/*글쓰기 버튼*/
		writeBtn = new JButton("");
		writeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//글 작성 event
				new BoardWrite(nec_board,curr_user);
			}
		});
		writeBtn.setIcon(new ImageIcon(".\\Image\\write_btn.PNG"));
		writeBtn.setBounds(864, 488, 78, 35);
		writeBtn.setBorder(null);
		foodPanel.add(writeBtn);
		
		/*뒤로가기 버튼*/
		boardBackBtn = new JButton("");
		boardBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);
				choicePanel.setVisible(true);
				currPanel = choicePanel;
			}
		});
		boardBackBtn.setIcon(new ImageIcon(".\\Image\\back_btn.PNG"));
		boardBackBtn.setBounds(0, 491, 49, 45);
		boardBackBtn.setBorder(null);
		foodPanel.add(boardBackBtn);
		
		/*
		 * 장소 메뉴 버튼 구현
		 */
		ChatBtnDesign fAllBtn = new ChatBtnDesign("모든 글 보기");		//모든 글 보기
		fAllBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		fAllBtn.setFont(new Font("배달의민족 주아", Font.BOLD, 25));
		fAllBtn.setBounds(0, 96, 158, 48);
		foodPanel.add(fAllBtn);
		
		ChatBtnDesign fCentralBtn = new ChatBtnDesign(" 중 문 ");		//중문
		fCentralBtn.setFont(new Font("배달의민족 주아", Font.PLAIN, 23));
		fCentralBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		fCentralBtn.setBounds(0, 154, 158, 36);
		foodPanel.add(fCentralBtn);
		
		ChatBtnDesign fFrontBtn = new ChatBtnDesign(" 정 문 ");		//정문
		fFrontBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		fFrontBtn.setFont(new Font("배달의민족 주아", Font.PLAIN, 23));
		fFrontBtn.setBounds(0, 191, 158, 36);
		foodPanel.add(fFrontBtn);
		
		ChatBtnDesign fWestBtn = new ChatBtnDesign(" 서 문 ");			//서문
		fWestBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		fWestBtn.setFont(new Font("배달의민족 주아", Font.PLAIN, 23));
		fWestBtn.setBounds(0, 228, 158, 36);
		foodPanel.add(fWestBtn);
		
		ChatBtnDesign nfBackGateBtn = new ChatBtnDesign(" 후 문 ");		//후문
		nfBackGateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		nfBackGateBtn.setFont(new Font("배달의민족 주아", Font.PLAIN, 23));
		nfBackGateBtn.setBounds(0, 265, 158, 36);
		foodPanel.add(nfBackGateBtn);
		
		ChatBtnDesign fBonBtn = new ChatBtnDesign(" 본 관 ");			//본관
		fBonBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		fBonBtn.setFont(new Font("배달의민족 주아", Font.PLAIN, 23));
		fBonBtn.setBounds(0, 303, 158, 36);
		foodPanel.add(fBonBtn);
		
		ChatBtnDesign fYSungBtn = new ChatBtnDesign(" 양성재 ");		//양성재
		fYSungBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		fYSungBtn.setFont(new Font("배달의민족 주아", Font.PLAIN, 23));
		fYSungBtn.setBounds(0, 340, 158, 36);
		foodPanel.add(fYSungBtn);
		
		ChatBtnDesign fYJinBtn = new ChatBtnDesign(" 양진재");			//양진재
		fYJinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		fYJinBtn.setFont(new Font("배달의민족 주아", Font.PLAIN, 23));
		fYJinBtn.setBounds(0, 377, 158, 36);
		foodPanel.add(fYJinBtn);
		
		/****************************Choice panel (시나 음식 시나 생필품)*********************************************/
		
		choicePanel.setBounds(0, 0, 960, 540);
		choicePanel.setLayout(null);
		
		JButton foodBtn = new JButton("");
		foodBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);
				foodPanel.setVisible(true);
				currPanel = foodPanel;
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
				necPanel.setVisible(true);
				currPanel = necPanel;
			}	
		});
		necBtn.setBounds(526, 157, 305, 305);
		necBtn.setIcon(new ImageIcon(".\\Image\\Ch_nec.PNG"));
		necBtn.setBorder(null);
		choicePanel.add(necBtn);
		
		
		/**********************************Join panel (회원가입 패널)******************************************/
		
		joinPanel.setBounds(0, 0, 960, 540);
		joinPanel.setLayout(null);
		
		joinNameField = new JTextField();		//이름
		joinNameField.setBounds(235, 175, 199, 25);
		joinNameField.setFont(Jua);
		joinNameField.setColumns(10);
		joinNameField.setBorder(null);
		joinPanel.add(joinNameField);
		
		joinIdField = new JTextField();			//아이디
		joinIdField.setBounds(235, 232, 199, 25);
		joinIdField.setFont(Jua);
		joinIdField.setColumns(10);
		joinIdField.setBorder(null);
		joinPanel.add(joinIdField);
		
		joinPwField = new JPasswordField();			//비밀번호
		joinPwField.setBounds(235, 287, 199, 25);
		joinPwField.setFont(new Font("Dialog", Font.PLAIN, 17));
		joinPwField.setColumns(10);
		joinPwField.setBorder(null);
		joinPanel.add(joinPwField);
		
		joinPwCheckField = new JPasswordField();	//비밀번호 확인
		joinPwCheckField.setBounds(235, 338, 199, 25);
		joinPwCheckField.setFont(new Font("Dialog", Font.PLAIN, 17));
		joinPwCheckField.setColumns(10);
		joinPwCheckField.setBorder(null);
		joinPanel.add(joinPwCheckField);
		
		JComboBox comboBox = new JComboBox(place);	//장소
		comboBox.setBounds(583, 175, 207, 27);
		comboBox.setFont(Jua);
		joinPanel.add(comboBox);
			
		joinNickField = new JTextField();			//닉네임
		joinNickField.setBounds(583, 232, 207, 27);
		joinNickField.setFont(Jua);
		joinNickField.setColumns(10);
		joinNickField.setBorder(null);
		joinPanel.add(joinNickField);
		
		enrollBtn = new JButton("");				//등록 버튼
		enrollBtn.setBounds(419, 432, 122, 49);
		enrollBtn.setBorder(null);
		enrollBtn.addActionListener(new ActionListener(){	

			@Override
			public void actionPerformed(ActionEvent e) {	//등록하기 버튼을 눌렀을 때의 동작
				try {
					/*
					 * 회원가입 데이터 저장하기
					 *  
					 * */
					//getText()로 데이터 저장
					//장소(ComboBox)-comboBox.getSelectedItem().toString()로 데이터 저장
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null,"You Failed to Enroll");
				}
				JOptionPane.showMessageDialog(null,"회원가입을 축하합니다!");
				currPanel.setVisible(false);
				loginPanel.setVisible(true);
				currPanel = loginPanel;				
			}		
		});
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
				loginPanel.setVisible(true);	//로그인 패널을 다시 보이게 하고
				currPanel = loginPanel;	//현재 패널=로그인 패널	
			}		
		});
		backBtn.setIcon(new ImageIcon(".\\Image\\back_btn.PNG"));
		joinPanel.add(backBtn);
		
		/***************************************login panel(로그인 패널)***************************************/
		
		loginPanel.setBounds(0, 0, 960, 540);
		loginPanel.setLayout(null);
		currPanel=loginPanel;
		frame.setSize(loginPanel.getDimension());
		frame.setPreferredSize(loginPanel.getDimension());
		
		idField = new JTextField();
		idField.setBounds(183, 275, 198, 24);
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
				/*
				 * ID PW 비교
				 *  
				 * */
				if(ID.equals(idField.getText()) && PASS.equals(pwField.getText())){
					currPanel.setVisible(false);
					choicePanel.setVisible(true);
					currPanel = choicePanel;
				}
				else{
					JOptionPane.showMessageDialog(null,"You Failed to Log In");
				}
			}
		});
		loginBtn.setIcon(new ImageIcon(".\\Image\\login_btn.PNG"));
		loginPanel.add(loginBtn);
		
		JButton joinBtn = new JButton("");		//joinBtn
		joinBtn.setBounds(204, 374, 131, 51);
		joinBtn.setBorder(null);
		joinBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);
				joinPanel.setVisible(true);
				currPanel = joinPanel;
			}
		});
		joinBtn.setIcon(new ImageIcon(".\\Image\\join_btn.PNG"));
		loginPanel.add(joinBtn);
		
		joinPanel.setVisible(false);
		necPanel.setVisible(false);
		foodPanel.setVisible(false);
		choicePanel.setVisible(false);
		mypagePanel.setVisible(false);
	}
}

