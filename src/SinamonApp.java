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

import Chatting.*;

public class SinamonApp {
	
	//check �ӽ� ID,PW
	String board_name;
	private final String ID="Hello";
	private final String PASS="1234";
	private final String place[]= {"�� ��", "�� ��","�� ��","�� ��","�� ��","�� �� ��","�� �� ��"};
	
	public Object[][] food_board_data = new Object[100][6];		//���� �Խù� �Խ��ǿ� ��� �迭
	public Object[][] nec_board_data = new Object[100][6];		//����ǰ �Խù� �Խ��ǿ� ��� �迭
	public Object[][] my_board_data = new Object[100][2];		//���� �� �Խù� ������������ ��� �迭 �� ��ȣ, ����, �ϷῩ��
	Object[] curr_user = new Object[6];		//���� �α������� ����� ���� ���� �迭
	
	private JFrame frame;
	private JTextField idField;
	private JPasswordField pwField;
	private JPanel currPanel;	//���� �г�
	private JPanel bfPanel;	//�� before �г�
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
	private JTable ftable;			//���� �Խ����� table
	private JTable ntable;			//����ǰ �Խ����� table
	private JTable history1;		//Mypage-���� �� ��
	private JTable history2;		//Mypage-���� �ó��� �� history
	private JScrollPane h1ScrollPane;	//Mypage-���� �� �� ���̺��� ��ũ������
	private JScrollPane h2ScrollPane;	///Mypage-���� �ó��� �� history ���̺��� ��ũ������
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
	private String id="Hello";
	private String password="1234";
	public boolean serverrun=false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ChatServerObject chattingserver=new ChatServerObject();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SinamonApp window = new SinamonApp(chattingserver);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		chattingserver.serverrun();
	}

	/**
	 * Create the application.
	 */
	public SinamonApp(ChatServerObject chattingserver) {
		db_connection connection = new db_connection();			//�����ͺ��̽� ���� ��ü
		//while(true) {
			initialize(connection,chattingserver);
		//}
		
	}
	
	//ä�� ��ư ������ �� ����
	 public class TableCellRenderer extends DefaultTableCellRenderer{ 
		 @Override 
		 public ChatBtnDesign getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column) { 
			 ChatBtnDesign chatBtn = null; 
			 chatBtn = new ChatBtnDesign("채팅버튼");
		 	 chatBtn.setFont(new Font("Sanserif", Font.BOLD, 15));
		 	 //������ �ȵ�....>>���ñ��� �ذ��Ҳ�...
		 	 chatBtn.addActionListener(e -> { System.out.println("good");});
			 return chatBtn; 
		 } 
	 }
	
	/**
	 * Initialize the contents of the frame.
	 */
	//���������� �г� ����
	private JPanel Create_Mypage_Panel(db_connection connection) {
		DefaultTableCellRenderer colC = new DefaultTableCellRenderer();
	    colC.setBackground(new Color(255, 255, 208));	
	    //table�� �� ������ ����
	    DefaultTableCellRenderer celAlignCenter=new DefaultTableCellRenderer();
	    celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
	    DefaultTableCellRenderer celAlignRight=new DefaultTableCellRenderer();
	    celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
	    //'��Ȳ'���� ���� ���� ����� ��� ������ ���� DefaultTableCellRenderer ��ü ����
	    DefaultTableCellRenderer celOrCenter=new DefaultTableCellRenderer();
	    celOrCenter.setForeground(new Color(255, 127, 0));
	    celOrCenter.setHorizontalAlignment(JLabel.CENTER);
	    
		ImagePanel mypagePanel = new ImagePanel(new ImageIcon(".\\Image\\mypage.png").getImage());
		frame.getContentPane().add(mypagePanel);	
		
		mypagePanel.setBounds(0,0,960,540);	//�г� ������
		mypagePanel.setLayout(null);
		
		//history1 ���� - [���� �� ��]
		String[] hHeader=new String[] {"����", "��Ȳ"};	//�����丮 ���̺� ���
		String nickname = "'" + curr_user[2] + "'";
		my_board_data = connection.return_my_board(nickname);
		
		//DefaultTableModel�� ����Ͽ� ���� ���� �Ұ��ϰ�
		DefaultTableModel modH1 = new DefaultTableModel(my_board_data, hHeader) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        history1 =new JTable(modH1);
        //���̺��� "����"�� ���, Ŭ���ϸ� �Խñ� ����â�� ��Ÿ��
        history1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	int row = history1.getSelectedRow();
                int col = history1.getSelectedColumn();
                String value=(String) history1.getValueAt(row,col);		//������ ���� ���� �����Ͽ�
            	if(value.equals("����"))	{								//'����'�� ���
            		new BoardEdit();									//����â�� ��Ÿ��
            	}
            }
        });
		//���̺� ���� ����
        history1.setRowHeight(25);	//���̺� �� ����
		history1.setFont(new Font("Sanserif", Font.BOLD, 15));	//���̺� ��Ʈ
	    history1.getColumn("����").setPreferredWidth(300);			//���̺� �� ����
	    history1.getColumn("��Ȳ").setPreferredWidth(80);
	    history1.getColumn("��Ȳ").setCellRenderer(celOrCenter);	//'��Ȳ': ���� �Ķ���, ��� ����
		history1.setPreferredScrollableViewportSize(new Dimension(380,256));
        history1.getTableHeader().setReorderingAllowed(false); 	// �÷��� �̵� �Ұ�
        history1.getTableHeader().setResizingAllowed(false); 	// �÷� ũ�� ���� �Ұ�
		
        h1ScrollPane = new JScrollPane(history1);	//table�� ��ũ�� ��������
		h1ScrollPane.setBounds(77, 254, 380, 256);
		mypagePanel.add(h1ScrollPane);
		
		//history2 ���� - [���� �ó��� �� history]
		Object[][] hData2 = connection.return_get_in_board(nickname);
		
		//DefaultTableModel�� ����Ͽ� ���� ���� �Ұ��ϰ�
		DefaultTableModel modH2 = new DefaultTableModel(hData2,hHeader) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        history2 =new JTable(modH2);
        //���̺��� "������"�� ���, Ŭ���ϸ� �Խñ� ����â�� ��Ÿ��
        history2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	int row = history2.getSelectedRow();
                int col = history2.getSelectedColumn();
                String value=(String) history2.getValueAt(row,col);         //������ ���� ���� �����Ͽ�
                if(value.equals("������")) {                          //'���� ��'�� ���
                	new BoardLook(board_name,curr_user);               //�Խñ� ���� â�� ��Ÿ��
                }
            }
        });

		//���̺� ���� ����
        history2.setRowHeight(25);	//���̺� �� ����
		history2.setFont(new Font("Sanserif", Font.BOLD, 15));	//���̺� ��Ʈ
	    history2.getColumn("����").setPreferredWidth(300);			//���̺� �� ����
	    history2.getColumn("��Ȳ").setPreferredWidth(80);
	    history2.getColumn("��Ȳ").setCellRenderer(celOrCenter);	//'��Ȳ': ���� �Ķ���, ��� ����
		history2.setPreferredScrollableViewportSize(new Dimension(380,256));
        history2.getTableHeader().setReorderingAllowed(false); 	// �÷��� �̵� �Ұ�
        history2.getTableHeader().setResizingAllowed(false); 	// �÷� ũ�� ���� �Ұ�
		
        h2ScrollPane = new JScrollPane(history2);	//table�� ��ũ�� ��������
		h2ScrollPane.setBounds(499, 254, 380, 256);
		mypagePanel.add(h2ScrollPane);
		
		//Mypage �ڷΰ��� ��ư
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
		
		//ȸ������ ���� ��ư
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
		
		//ä�� �˸�
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(101, 138, 756, 38);
		mypagePanel.add(lblNewLabel);
		
		mypagePanel.setVisible(true);
		
		return mypagePanel;
	}
	
	//����ǰ �г� ����
	private JPanel Create_Nec_Panel(db_connection connection) {
		DefaultTableCellRenderer colC = new DefaultTableCellRenderer();
	    colC.setBackground(new Color(255, 255, 208));	
	    //table�� �� ������ ����
	    DefaultTableCellRenderer celAlignCenter=new DefaultTableCellRenderer();
	    celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
	    DefaultTableCellRenderer celAlignRight=new DefaultTableCellRenderer();
	    celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
	    //'��Ȳ'���� ���� ���� ����� ��� ������ ���� DefaultTableCellRenderer ��ü ����
	    DefaultTableCellRenderer celOrCenter=new DefaultTableCellRenderer();
	    celOrCenter.setForeground(new Color(255, 127, 0));
	    celOrCenter.setHorizontalAlignment(JLabel.CENTER);
	    
		ImagePanel necPanel = new ImagePanel(new ImageIcon(".\\Image\\board.png").getImage());
		frame.getContentPane().add(necPanel);
		
		necPanel.setBounds(0, 0, 960, 540);
		necPanel.setLayout(null);
		/*�Խ��� table*/
		String[] necHeader=new String[] {"��ȣ","���","�ð�","����","�ۼ���","��Ȳ","ä��"};
		//����
		nec_board_data = connection.show_board("nes_board");
		
		DefaultTableModel necMod=new DefaultTableModel(nec_board_data, necHeader) {	// ���� �Ұ�
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		ntable=new JTable(necMod);
		//테이블이 채팅하기 셀을 클릭하면 채팅창이 나타남
        ntable.addMouseListener(new MouseAdapter() {
              @Override
              public void mouseClicked(MouseEvent e) {
                  int row = ntable.getSelectedRow();   //해당 셀의 행을 받아올 수 있음
                   int col = ntable.getSelectedColumn();   //해당 셀의 열을 받아올 수 있음
         if(col==6){
        	 ChatClientObject client=new ChatClientObject(row,"nec");
      	   client.service();
            System.out.println("Row:"+row+"Chat");
         }
                   
      }
          });
	    //���̺� ���� ����
	    ntable.getColumn("��ȣ").setPreferredWidth(40);
        ntable.getColumn("��ȣ").setCellRenderer(celAlignCenter);
        ntable.getColumn("���").setPreferredWidth(60);
        ntable.getColumn("���").setCellRenderer(celAlignCenter);
        ntable.getColumn("�ð�").setPreferredWidth(80);
        ntable.getColumn("�ð�").setCellRenderer(celAlignCenter);
        ntable.getColumn("����").setPreferredWidth(306);
        ntable.getColumn("�ۼ���").setPreferredWidth(100);
        ntable.getColumn("�ۼ���").setCellRenderer(celAlignCenter);
        ntable.getColumn("��Ȳ").setPreferredWidth(80);
        ntable.getColumn("��Ȳ").setCellRenderer(celOrCenter);
        ntable.getColumn("ä��").setPreferredWidth(80);
        TableCellRenderer renderer = new TableCellRenderer();	//ä�� ��ư ����
        ntable.getColumn("ä��").setCellRenderer(renderer);
       
		ntable.setRowHeight(30);
		ntable.setFont(new Font("Sanserif", Font.BOLD, 17));
		ntable.setPreferredScrollableViewportSize(new Dimension(746,392));
		ntable.getTableHeader().setReorderingAllowed(false); // �÷��� �̵� �Ұ�
        ntable.getTableHeader().setResizingAllowed(false); // �÷� ũ�� ���� �Ұ�
        ntable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//table ������ ���� �Ұ�
		JScrollPane scrollPane = new JScrollPane(ntable);
		scrollPane.setBounds(189, 92, 746, 392);;
		necPanel.add(scrollPane);
		
		/*�� ã�� �˻���*/
		searchField = new JTextField("");
		searchField.setToolTipText("");
		searchField.setForeground(Color.DARK_GRAY);
		searchField.setFont(new Font("Dialog", Font.PLAIN, 15));
		searchField.setBounds(686, 29, 149, 36);
		searchField.setBorder(null);
		necPanel.add(searchField);
		searchField.setColumns(10);
		
		/*�� ã�� ��ư*/
		JButton searchBtn = new JButton("");
		searchBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//searchField �����ϱ�
				String search_text = "'" + searchField.getText() + "'";
				nec_board_data = connection.search_post("nes_board", search_text);
				
			}
		});
		
		searchBtn.setIcon(new ImageIcon(".\\Image\\search_btn.PNG"));
		searchBtn.setBounds(836, 25, 40, 41);
		searchBtn.setBorder(null);
		necPanel.add(searchBtn);
		
		/*ȸ������ ��ư*/
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
		
		/*�۾��� ��ư*/
		JButton writeBtn = new JButton("");
		writeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�� �ۼ� event
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
		
		/*�ڷΰ��� ��ư*/
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
		/******************방생성*****************/
		
		Room foodroom;
		Room necroom;
		for(int i=0; i<1000; i++) {
		necroom=new Room();
		foodroom=new Room();
		RoomManager.setnecroom(necroom);
		RoomManager.setfoodroom(foodroom);
		}
		
		/*********************서버 오픈*******************/
		//ChatServerObject chattingserver=new ChatServerObject();
		//chattingserver.serverrun();
		
		DefaultTableCellRenderer colC = new DefaultTableCellRenderer();
	    colC.setBackground(new Color(255, 255, 208));	
	    //table�� �� ������ ����
	    DefaultTableCellRenderer celAlignCenter=new DefaultTableCellRenderer();
	    celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
	    DefaultTableCellRenderer celAlignRight=new DefaultTableCellRenderer();
	    celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
	    //'��Ȳ'���� ���� ���� ����� ��� ������ ���� DefaultTableCellRenderer ��ü ����
	    DefaultTableCellRenderer celOrCenter=new DefaultTableCellRenderer();
	    celOrCenter.setForeground(new Color(255, 127, 0));
	    celOrCenter.setHorizontalAlignment(JLabel.CENTER);
		
		ImagePanel foodPanel = new ImagePanel(new ImageIcon(".\\Image\\board.png").getImage());
		frame.getContentPane().add(foodPanel);
		
		foodPanel.setBounds(0, 0, 960, 540);
		foodPanel.setLayout(null);
		/*�Խ��� table*/
		String[] header=new String[] {"��ȣ","���","�ð�","����","�ۼ���","��Ȳ","ä��"};
		//����
		food_board_data = connection.show_board("food_board");
		
		for(int i=0; i<12; i++) {
			System.out.println(food_board_data[i][0]);
		}
		
		DefaultTableModel foodMod=new DefaultTableModel(food_board_data, header) {	// ���� �Ұ�
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		ftable=new JTable(foodMod);
		
		ftable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = ftable.getSelectedRow();   //해당 셀의 행을 받아올 수 있음
                 int col = ftable.getSelectedColumn();   //해당 셀의 열을 받아올 수 있음

       if(col==6){
    	   ChatClientObject client=new ChatClientObject(row,"food");
    	   client.service();
       }
                 
    }
		});
		
	  //���̺� ���� ����
	    ftable.getColumn("��ȣ").setPreferredWidth(40);
        ftable.getColumn("��ȣ").setCellRenderer(celAlignCenter);
        ftable.getColumn("���").setPreferredWidth(60);
        ftable.getColumn("���").setCellRenderer(celAlignCenter);
        ftable.getColumn("�ð�").setPreferredWidth(80);
        ftable.getColumn("�ð�").setCellRenderer(celAlignCenter);
        ftable.getColumn("����").setPreferredWidth(306);
        ftable.getColumn("�ۼ���").setPreferredWidth(100);
        ftable.getColumn("�ۼ���").setCellRenderer(celAlignCenter);
        ftable.getColumn("��Ȳ").setPreferredWidth(80);
        ftable.getColumn("��Ȳ").setCellRenderer(celOrCenter);
        ftable.getColumn("ä��").setPreferredWidth(80);
        TableCellRenderer renderer = new TableCellRenderer();	//ä�� ��ư ����
        ftable.getColumn("ä��").setCellRenderer(renderer);	//ä�ù�ư ����
     
		ftable.setRowHeight(30);
		ftable.setFont(new Font("Sanserif", Font.BOLD, 17));
		ftable.setPreferredScrollableViewportSize(new Dimension(746, 392));
		ftable.getTableHeader().setReorderingAllowed(false); // �÷��� �̵� �Ұ�
        ftable.getTableHeader().setResizingAllowed(false); // �÷� ũ�� ���� �Ұ�
        ftable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//table ������ ���� �Ұ�
        
        JScrollPane scrollPane = new JScrollPane(ftable);
		scrollPane.setBounds(189, 92, 746, 392);
		foodPanel.add(scrollPane);
		
		/*�� ã�� �˻���*/
		searchField = new JTextField("");
		searchField.setToolTipText("");
		searchField.setForeground(Color.DARK_GRAY);
		searchField.setFont(new Font("�� M",Font.PLAIN,19));
		searchField.setBounds(686, 29, 149, 36);
		searchField.setBorder(null);
		foodPanel.add(searchField);
		searchField.setColumns(10);
		
		/*�� ã�� ��ư*/
		searchBtn = new JButton("");
		searchBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//searchField �����ϱ�
				String search_text = "'" + searchField.getText() + "'";
				connection.search_post("food_board", search_text);
			}
		});
		searchBtn.setIcon(new ImageIcon(".\\Image\\search_btn.PNG"));
		searchBtn.setBounds(836, 25, 40, 41);
		searchBtn.setBorder(null);
		foodPanel.add(searchBtn);
		
		/*ȸ������ ��ư*/
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
		
		/*�۾��� ��ư*/
		JButton writeBtn = new JButton("");
		writeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�� �ۼ� event
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
		
		/*�ڷΰ��� ��ư*/
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
	
	private JPanel Create_Join_Panel(db_connection connection, ChatServerObject chattingserver) {
		ImagePanel joinPanel = new ImagePanel(new ImageIcon(".\\Image\\join.png").getImage());
		frame.getContentPane().add(joinPanel);
		
		joinPanel.setBounds(0, 0, 960, 540);
		joinPanel.setLayout(null);
		
		joinNameField = new JTextField(); //�̸�
		joinNameField.setBounds(235, 175, 199, 25);
		joinNameField.setFont(new Font("�� M",Font.PLAIN,19));
		joinNameField.setColumns(10);
		joinNameField.setBorder(null);
		joinPanel.add(joinNameField);

		joinIdField = new JTextField(); //���̵�
		joinIdField.setBounds(235, 232, 199, 25);
		joinIdField.setFont(new Font("�� M",Font.PLAIN,19));
		joinIdField.setColumns(10);
		joinIdField.setBorder(null);
		joinPanel.add(joinIdField);
		
		joinPwField = new JPasswordField(); //��й�ȣ
		joinPwField.setBounds(235, 287, 199, 25);
		joinPwField.setFont(new Font("Dialog", Font.PLAIN, 17));
		joinPwField.setColumns(10);
		joinPwField.setBorder(null);
		joinPanel.add(joinPwField);
		
		joinPwCheckField = new JPasswordField(); //��й�ȣ Ȯ��
		joinPwCheckField.setBounds(235, 338, 199, 25);
		joinPwCheckField.setFont(new Font("Dialog", Font.PLAIN, 17));
		joinPwCheckField.setColumns(10);
		joinPwCheckField.setBorder(null);
		joinPanel.add(joinPwCheckField);
		
		JComboBox comboBox = new JComboBox(place); //���
		comboBox.setBounds(583, 175, 207, 27);
		comboBox.setFont(new Font("�� M",Font.PLAIN,19));
		joinPanel.add(comboBox);
		
		joinNickField = new JTextField(); //�г���
		joinNickField.setBounds(583, 232, 207, 27);
		joinNickField.setFont(new Font("�� M",Font.PLAIN,19));
		joinNickField.setColumns(10);
		joinNickField.setBorder(null);
		joinPanel.add(joinNickField);

		enrollBtn = new JButton("");				//��� ��ư
		enrollBtn.setBounds(419, 426, 122, 49);
		enrollBtn.setBorder(null);
		enrollBtn.addActionListener(new ActionListener(){	

			@Override
			public void actionPerformed(ActionEvent e) {	//����ϱ� ��ư�� ������ ���� ����
				try {
					String nameString = "'" + joinNameField.getText() + "'";			//�̸�
					String nicknameString = "'" + joinNickField.getText() + "'";		//�г���
					String idString = "'" + joinIdField.getText() + "'";				//���̵�
					String pwdString = "'" + joinPwField.getText() + "'";				//���
					String pwdcheckString = "'" + joinPwCheckField.getText() + "'";		//��� Ȯ��
					String homeString = "'" + comboBox.getSelectedItem().toString() + "'";	//��� ��
					
					
					
					if(pwdString.equals(pwdcheckString)) {		//����� ���Ȯ���� ��ġ�ϸ�
						if(connection.input_user_info(nameString, nicknameString, idString, pwdString, homeString)) {
							JOptionPane.showMessageDialog(null,"ȸ�������� �����մϴ�!");
							currPanel.setVisible(false);
							//loginPanel.setVisible(true);
							currPanel = Create_login_Panel(connection,chattingserver);
							return;
						}
						else {
							JOptionPane.showMessageDialog(null,"�̹� �����ϴ� ���̵��Դϴ�.");	
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"��й�ȣ�� ��ġ���� �ʽ��ϴ�. �ٽ� Ȯ���ϼ���.");
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null,"Unknown Error! ȸ�����Կ� �����Ͽ����ϴ�! �ó��� ������ ����ó: 01030135810");
				}
				/*
				JOptionPane.showMessageDialog(null,"ȸ�������� �����մϴ�!");
				currPanel.setVisible(false);
				loginPanel.setVisible(true);
				currPanel = loginPanel;		
				*/		
			}		
		});
		enrollBtn.setPressedIcon(new ImageIcon(".\\Image\\enroll_click_btn.PNG"));
		joinPanel.add(enrollBtn);
		enrollBtn.setIcon(new ImageIcon(".\\Image\\enroll_btn.PNG"));
		
		//�ڷΰ��� ��ư
		backBtn = new JButton("");		
		backBtn.setBounds(5, 10, 49, 49);
		backBtn.setBorder(null);
		backBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);	//���� �г�(ȸ������) �Ⱥ��̰� �ϰ�
				//loginPanel.setVisible(true);	//�α��� �г��� �ٽ� ���̰� �ϰ�
				currPanel = Create_login_Panel(connection,chattingserver);	//���� �г�=�α��� �г�	
			}		
		});
		backBtn.setIcon(new ImageIcon(".\\Image\\back_btn.PNG"));
		joinPanel.add(backBtn);
		
		joinPanel.setVisible(true);
		
		return joinPanel;
	}
	
	private JPanel Create_login_Panel(db_connection connection,ChatServerObject chattingserver) {
		
		
		ImagePanel loginPanel = new ImagePanel(new ImageIcon(".\\Image\\login.png").getImage());
		frame.getContentPane().add(loginPanel);
		
		loginPanel.setBounds(0, 0, 960, 540);
		loginPanel.setLayout(null);
		currPanel=loginPanel;
		frame.setSize(loginPanel.getDimension());
		frame.setPreferredSize(loginPanel.getDimension());
		
		idField = new JTextField();
		idField.setBounds(183, 274, 198, 24);
		idField.setFont(new Font("�� M",Font.PLAIN,19));
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
				
			
				if(ID.equals(idField.getText()) && PASS.equals(pwField.getText())){
					currPanel.setVisible(false);
					
					currPanel = Create_Choice_Panel(connection);
					curr_user = connection.return_user_info(idString);
					serverrun=true;
					System.out.println(serverrun);
					
				}
				else{
					JOptionPane.showMessageDialog(null,"You Failed to Log In");
				}
				
				if(connection.login(idString, pwdString)) {				//�ش�Ǵ� ���̵�� �н����� ��ġ�ϸ� true ��ȯ
					currPanel.setVisible(false);
					
					currPanel = Create_Choice_Panel(connection);
					curr_user = connection.return_user_info(idString);	//���� �α������� ȸ�� ����
				}
				else {
					JOptionPane.showMessageDialog(null,"���̵�/����� ��ġ���� �ʰų�, �������� �ʴ� �����Դϴ�");
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
				currPanel = Create_Join_Panel(connection,chattingserver);
			}
		});
		joinBtn.setIcon(new ImageIcon(".\\Image\\join_btn.PNG"));
		joinBtn.setPressedIcon(new ImageIcon(".\\Image\\join_click_btn.PNG"));
		loginPanel.add(joinBtn);
		
		loginPanel.setVisible(true);
		return loginPanel;
	}
	
	private void initialize(db_connection connection,ChatServerObject chattingserver) {
		//frame ����
		frame = new JFrame();
		frame.setTitle("�ó���");
		frame.setBounds(100, 100, 960, 540);
		frame.setPreferredSize(new Dimension(960,540));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//ȭ���� ������ ���α׷� ����
		frame.setResizable(false); 		//ũ�� ����
		//
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image logo=tk.getImage(".\\Image\\logo.png");
		frame.setIconImage(logo);

		
		currPanel=Create_login_Panel(connection,chattingserver);
	    System.out.println("hey");
	    
		/**************************** My page (ȸ�� �����丮 �г�)*****************************************/
		
		
		/****************************necPanel (����ǰ �Խ��� �г�)*****************************************/
		
		
		
		/****************************foodPanel (���� �Խ��� �г�)*****************************************/
		
		
		
		/****************************Choice panel (�ó� ���� �ó� ����ǰ)*********************************************/
		
		
		
		
		/**********************************Join panel (ȸ������ �г�)******************************************/
		
		
		
		/***************************************login panel(�α��� �г�)***************************************/
		
		
		Create_Join_Panel(connection,chattingserver).setVisible(false);
		Create_Nec_Panel(connection).setVisible(false);
		Create_Food_Panel(connection).setVisible(false);
		Create_Choice_Panel(connection).setVisible(false);
		Create_Mypage_Panel(connection).setVisible(false);
	}
}

