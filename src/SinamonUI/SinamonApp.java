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
	
	//check �ӽ� ID,PW
	private final String ID="Hello";
	private final String PASS="1234";
	//BoardWrite �������ؼ� ��� �����ص� >>�����
	String food_board;		
	String nec_board;
	String board_name;
	Object[] curr_user;
	/*
	 * ID PW ����
	 *  
	 * */
	
	private final String place[]= {"�� ��", "�� ��","�� ��","�� ��","�� ��","�� �� ��","�� �� ��"};
	
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
	private JScrollPane h2ScrollPane;	///Mypage-���� �ó��� �� history ���̺��� ��ũ����
	private JTextField searchField;
	private JButton MPbackBtn;
	private JCheckBox frontCkBx;
	private JPanel curMenuPanel;		//board���� ��� ��ư ó���� ���� ���� ��ư ����
	
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
				 chatBtn = new JButton("ä���ϱ�");
				 chatBtn.setFont(new Font("�� M", Font.BOLD, 13));
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
		//frame ����
		frame = new JFrame();
		frame.setTitle("�ó���");
		frame.setBounds(100, 100, 960, 540);
		frame.setPreferredSize(new Dimension(960,540));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//ȭ���� ������ ���α׷� ����
		frame.setResizable(false); 		//ũ�� ����
		//�ΰ�
		Toolkit tk = Toolkit.getDefaultToolkit();
        Image logo=tk.getImage(".\\Image\\logo.png");
        frame.setIconImage(logo);
		
		
		//��Ʈ ����
		//Font Jua=new Font("����ǹ��� �־�",Font.PLAIN,19);
		Font Jua=new Font("�� M",Font.PLAIN,19);
		
		//�г� �̹���
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
		
		//table�� 'ä���ϱ�' ���� ���� ������ ���� DefaultTableCellRenderer ��ü ����
		DefaultTableCellRenderer colC = new DefaultTableCellRenderer();
	    colC.setBackground(new Color(0, 118, 134));	
	    //table�� �� ������ ����
	    DefaultTableCellRenderer celAlignCenter=new DefaultTableCellRenderer();
	    celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
	    DefaultTableCellRenderer celAlignRight=new DefaultTableCellRenderer();
	    celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
	    //'��Ȳ'���� ���� ���� ����� ��� ������ ���� DefaultTableCellRenderer ��ü ����
	    DefaultTableCellRenderer celOrCenter=new DefaultTableCellRenderer();
	    celOrCenter.setForeground(new Color(255, 127, 0));
	    celOrCenter.setHorizontalAlignment(JLabel.CENTER);
	    
	    /**************************** My page (ȸ�� �����丮 �г�)*****************************************/
		mypagePanel.setBounds(0,0,960,540);	//�г� ������
		mypagePanel.setLayout(null);
		
		//history1 ���� - [���� �� ��]
		String[] hHeader=new String[] {"����", "��Ȳ"};	//�����丮 ���̺� ���
		Object[][] hData1=new Object[][] {					
			{"Ǫ��� ���� ���","�Ϸ�"},
			{"���� ���� ���","�Ϸ�"},
			{"��â���� ���� ���","�Ϸ�"},
			{"���� ���� ���","����"},
			{"���� ���� ���","���� ��"}
		};
		
		//DefaultTableModel�� ����Ͽ� ���� ���� �Ұ��ϰ�
		DefaultTableModel modH1 = new DefaultTableModel(hData1,hHeader) {
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
            	if(value.equals("����"))									//'����'�� ���
            		new BoardEdit();									//����â�� ��Ÿ��
            }
        });
		//���̺� ���� ����
        history1.setRowHeight(25);	//���̺� �� ����
		history1.setFont(new Font("Sanserif", Font.BOLD, 15));	//���̺� ��Ʈ
	    history1.getColumn("����").setPreferredWidth(300);			//���̺� �� ����
	    history1.getColumn("��Ȳ").setPreferredWidth(80);
	    history1.getColumn("��Ȳ").setCellRenderer(celOrCenter);	//'��Ȳ': ���� ��Ȳ, ��� ����
		history1.setPreferredScrollableViewportSize(new Dimension(380,256));
        history1.getTableHeader().setReorderingAllowed(false); 	// �÷��� �̵� �Ұ�
        history1.getTableHeader().setResizingAllowed(false); 	// �÷� ũ�� ���� �Ұ�
		
        h1ScrollPane = new JScrollPane(history1);	//table�� ��ũ�� ��������
		h1ScrollPane.setBounds(77, 254, 380, 256);
		mypagePanel.add(h1ScrollPane);
		
		//history2 ���� - [���� �ó��� �� history]
		Object[][] hData2=new Object[][] {
			{"Ǫ���","�Ϸ�"},
			{"����","�Ϸ�"},
			{"��â����","�Ϸ�"},
			{"����","���� ��"},
			{"����","���� ��"}
		};
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
                String value=(String) history2.getValueAt(row,col);			//������ ���� ���� �����Ͽ�
	        	if(value.equals("���� ��"))									//'���� ��'�� ���
	        		new BoardLook(board_name,curr_user);					//�Խñ� ���� â�� ��Ÿ��
            }
        });
		//���̺� ���� ����
        history2.setRowHeight(25);	//���̺� �� ����
		history2.setFont(new Font("Sanserif", Font.BOLD, 15));	//���̺� ��Ʈ
	    history2.getColumn("����").setPreferredWidth(300);			//���̺� �� ����
	    history2.getColumn("��Ȳ").setPreferredWidth(80);
	    history2.getColumn("��Ȳ").setCellRenderer(celOrCenter);	//'��Ȳ': ���� ��Ȳ��, ��� ����
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
				new InfoEdit(curr_user);
			}
		});
		editInfoBtn.setBounds(348, 22, 155, 46);
		editInfoBtn.setBorder(null);
		mypagePanel.add(editInfoBtn);
		
		//ä�� �˸�
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(101, 138, 756, 38);
		mypagePanel.add(lblNewLabel);
	    
		/****************************necPanel (����ǰ �Խ��� �г�)*****************************************/
		
		necPanel.setBounds(0, 0, 960, 540);
		necPanel.setLayout(null);
		/*�Խ��� table*/
		String[] necHeader=new String[] {"��ȣ","���","�ð�","����","�ۼ���","��Ȳ","ä��"};
		//����
		Object[][] necData=new Object[][] {
			{"01","������","16��","���� ������","���ڿ���","����",""},
			{"02","�߹�","12-2��", "�ø��� 1+1 ������", "���ڰ���","���� ��",""}		
		};
		DefaultTableModel necMod=new DefaultTableModel(necData,necHeader) {	// ���� �Ұ�
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		ntable=new JTable(necMod);
        
	    //���̺� ���� ����
	    ntable.getColumn("��ȣ").setPreferredWidth(40);
        ntable.getColumn("��ȣ").setCellRenderer(celAlignCenter);
        ntable.getColumn("���").setPreferredWidth(70);
        ntable.getColumn("���").setCellRenderer(celAlignCenter);
        ntable.getColumn("�ð�").setPreferredWidth(70);
        ntable.getColumn("�ð�").setCellRenderer(celAlignCenter);
        ntable.getColumn("����").setPreferredWidth(306);
        ntable.getColumn("�ۼ���").setPreferredWidth(100);
        ntable.getColumn("�ۼ���").setCellRenderer(celAlignCenter);
        ntable.getColumn("��Ȳ").setPreferredWidth(80);
        ntable.getColumn("��Ȳ").setCellRenderer(celOrCenter);
        ntable.getColumn("ä��").setPreferredWidth(80);
       
		ntable.setRowHeight(30);
		ntable.setFont(new Font("Sanserif", Font.BOLD, 17));
		ntable.setPreferredScrollableViewportSize(new Dimension(746,392));
		ntable.getTableHeader().setReorderingAllowed(false); // �÷��� �̵� �Ұ�
        ntable.getTableHeader().setResizingAllowed(false); // �÷� ũ�� ���� �Ұ�
        ntable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//table ������ ���� �Ұ�
		JScrollPane scrollPane = new JScrollPane(ntable);
		DefaultTableCellRenderer renderer = new MyTableCellRenderer();	//ä�� ��ư ����
        ntable.getColumn("ä��").setCellRenderer(renderer);
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
				/*
				 * �� ã��
				 * 
				 * */
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
				mypagePanel.setVisible(true);
				currPanel=mypagePanel;
			}
		});
		myBtn.setIcon(new ImageIcon(".\\Image\\my_btn.PNG"));
		myBtn.setBounds(886, 20, 48, 47);
		myBtn.setBorder(null);
		necPanel.add(myBtn);
		
		/*�۾��� ��ư*/
		JButton writeBtn = new JButton("");
		writeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�� �ۼ� event
				/*�۾��� ��ư*/
				new BoardWrite("nes_board", curr_user);
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
				choicePanel.setVisible(true);
				currPanel = choicePanel;
			}
		});
		boardBackBtn.setIcon(new ImageIcon(".\\Image\\back_btn.PNG"));
		boardBackBtn.setBounds(0, 491, 49, 45);
		boardBackBtn.setBorder(null);
		necPanel.add(boardBackBtn);
		
		/*
		 * ��� �޴� ��ư ����
		 */
		ChatBtnDesign nAllBtn = new ChatBtnDesign("��� �� ����");		//��� �� ����
		nAllBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		nAllBtn.setFont(new Font("����ǹ��� �־�", Font.BOLD, 25));
		nAllBtn.setBounds(0, 96, 158, 48);
		necPanel.add(nAllBtn);
		
		ChatBtnDesign nCentralBtn = new ChatBtnDesign(" �� �� ");		//�߹�
		nCentralBtn.setFont(new Font("����ǹ��� �־�", Font.PLAIN, 23));
		nCentralBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		nCentralBtn.setBounds(0, 154, 158, 36);
		necPanel.add(nCentralBtn);
		
		ChatBtnDesign nFrontBtn = new ChatBtnDesign(" �� �� ");		//����
		nFrontBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		nFrontBtn.setFont(new Font("����ǹ��� �־�", Font.PLAIN, 23));
		nFrontBtn.setBounds(0, 191, 158, 36);
		necPanel.add(nFrontBtn);
		
		ChatBtnDesign nWestBtn = new ChatBtnDesign(" �� �� ");			//����
		nWestBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		nWestBtn.setFont(new Font("����ǹ��� �־�", Font.PLAIN, 23));
		nWestBtn.setBounds(0, 228, 158, 36);
		necPanel.add(nWestBtn);
		
		ChatBtnDesign nBackGateBtn = new ChatBtnDesign(" �� �� ");		//�Ĺ�
		nBackGateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		nBackGateBtn.setFont(new Font("����ǹ��� �־�", Font.PLAIN, 23));
		nBackGateBtn.setBounds(0, 265, 158, 36);
		necPanel.add(nBackGateBtn);
		
		ChatBtnDesign nBonBtn = new ChatBtnDesign(" �� �� ");			//����
		nBonBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		nBonBtn.setFont(new Font("����ǹ��� �־�", Font.PLAIN, 23));
		nBonBtn.setBounds(0, 303, 158, 36);
		necPanel.add(nBonBtn);
		
		ChatBtnDesign nYSungBtn = new ChatBtnDesign(" �缺�� ");		//�缺��
		nYSungBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		nYSungBtn.setFont(new Font("����ǹ��� �־�", Font.PLAIN, 23));
		nYSungBtn.setBounds(0, 340, 158, 36);
		necPanel.add(nYSungBtn);
		
		ChatBtnDesign nYJinBtn = new ChatBtnDesign(" ������");			//������
		nYJinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		nYJinBtn.setFont(new Font("����ǹ��� �־�", Font.PLAIN, 23));
		nYJinBtn.setBounds(0, 377, 158, 36);
		necPanel.add(nYJinBtn);
		
		/****************************foodPanel (���� �Խ��� �г�)*****************************************/
		
		foodPanel.setBounds(0, 0, 960, 540);
		foodPanel.setLayout(null);
		/*�Խ��� table*/
		String[] header=new String[] {"��ȣ","���","�ð�","����","�ۼ���","��Ȳ","ä��"};
		//����
		Object[][] data=new Object[][] {
			{"01","������","�ƹ�����","Ǫ��� �����Ծ��","���ڿ���","���� ��"},
			{"01","����","16��","Ǫ��� �����Ծ��","���ڿ���","���� ��"},
			{"01","����","16��","Ǫ��� �����Ծ��","���ڿ���","���� ��"},
			{"01","����","16��","Ǫ��� �����Ծ��","���ڿ���","���� ��"},
			{"01","����","16��","Ǫ��� �����Ծ��","���ڿ���","���� ��"},
			{"01","����","16��","Ǫ��� �����Ծ��","���ڿ���","���� ��"},
			{"01","����","16��","Ǫ��� �����Ծ��","���ڿ���","���� ��"},
			{"01","����","16��","Ǫ��� �����Ծ��","���ڿ���","���� ��"},
			{"01","����","16��","Ǫ��� �����Ծ��","���ڿ���","���� ��"},
			{"01","����","16��","Ǫ��� �����Ծ��","���ڿ���","���� ��"},
			{"01","����","16��","Ǫ��� �����Ծ��","���ڿ���","���� ��"},
			{"01","����","16��","Ǫ��� �����Ծ��","���ڿ���","���� ��"},
			{"01","����","16��","Ǫ��� �����Ծ��","���ڿ���","���� ��"},
			{"01","����","16��","Ǫ��� �����Ծ��","���ڿ���","���� ��"},
			{"01","����","16��","Ǫ��� �����Ծ��","���ڿ���","���� ��"},
			{"01","����","16��","Ǫ��� �����Ծ��","���ڿ���","���� ��"},
			{"01","����","16��","Ǫ��� �����Ծ��","���ڿ���","���� ��"},
			{"01","����","16��","Ǫ��� �����Ծ��","���ڿ���","���� ��"},
			{"01","����","16��","Ǫ��� �����Ծ��","���ڿ���","���� ��"},
			{"01","����","16��","Ǫ��� �����Ծ��","���ڿ���","���� ��"},
			{"01","����","16��","Ǫ��� �����Ծ��","���ڿ���","���� ��"},
			{"01","����","16��","Ǫ��� �����Ծ��","���ڿ���","���� ��"}
		};
		DefaultTableModel foodMod=new DefaultTableModel(data,header) {	// ���� �Ұ�
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		ftable=new JTable(foodMod);
		//���̺� ���� ����
	    ftable.getColumn("��ȣ").setPreferredWidth(40);
        ftable.getColumn("��ȣ").setCellRenderer(celAlignCenter);
        ftable.getColumn("���").setPreferredWidth(70);
        ftable.getColumn("���").setCellRenderer(celAlignCenter);
        ftable.getColumn("�ð�").setPreferredWidth(80);
        ftable.getColumn("�ð�").setCellRenderer(celAlignCenter);
        ftable.getColumn("����").setPreferredWidth(306);
        ftable.getColumn("�ۼ���").setPreferredWidth(100);
        ftable.getColumn("�ۼ���").setCellRenderer(celAlignCenter);
        ftable.getColumn("��Ȳ").setPreferredWidth(70);
        ftable.getColumn("��Ȳ").setCellRenderer(celOrCenter);
        ftable.getColumn("ä��").setPreferredWidth(90);
        ftable.getColumn("ä��").setCellRenderer(renderer);	//ä�ù�ư ����
     
		ftable.setRowHeight(30);
		ftable.setFont(Jua);
		ftable.setPreferredScrollableViewportSize(new Dimension(746, 392));
		ftable.getTableHeader().setReorderingAllowed(false); // �÷��� �̵� �Ұ�
        ftable.getTableHeader().setResizingAllowed(false); // �÷� ũ�� ���� �Ұ�
        ftable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//table ������ ���� �Ұ�
        
		scrollPane = new JScrollPane(ftable);
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
				/*
				 * �� ã��
				 * 
				 * */
				System.out.print("good");
			}
		});
		searchBtn.setIcon(new ImageIcon(".\\Image\\search_btn.PNG"));
		searchBtn.setBounds(836, 25, 41, 39);
		searchBtn.setBorder(null);
		foodPanel.add(searchBtn);
		
		/*ȸ������ ��ư*/
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
		
		/*�۾��� ��ư*/
		writeBtn = new JButton("");
		writeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�� �ۼ� event
				new BoardWrite(nec_board,curr_user);
			}
		});
		writeBtn.setIcon(new ImageIcon(".\\Image\\write_btn.PNG"));
		writeBtn.setBounds(864, 488, 78, 35);
		writeBtn.setBorder(null);
		foodPanel.add(writeBtn);
		
		/*�ڷΰ��� ��ư*/
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
		 * ��� �޴� ��ư ����
		 */
		ChatBtnDesign fAllBtn = new ChatBtnDesign("��� �� ����");		//��� �� ����
		fAllBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		fAllBtn.setFont(new Font("����ǹ��� �־�", Font.BOLD, 25));
		fAllBtn.setBounds(0, 96, 158, 48);
		foodPanel.add(fAllBtn);
		
		ChatBtnDesign fCentralBtn = new ChatBtnDesign(" �� �� ");		//�߹�
		fCentralBtn.setFont(new Font("����ǹ��� �־�", Font.PLAIN, 23));
		fCentralBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		fCentralBtn.setBounds(0, 154, 158, 36);
		foodPanel.add(fCentralBtn);
		
		ChatBtnDesign fFrontBtn = new ChatBtnDesign(" �� �� ");		//����
		fFrontBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		fFrontBtn.setFont(new Font("����ǹ��� �־�", Font.PLAIN, 23));
		fFrontBtn.setBounds(0, 191, 158, 36);
		foodPanel.add(fFrontBtn);
		
		ChatBtnDesign fWestBtn = new ChatBtnDesign(" �� �� ");			//����
		fWestBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		fWestBtn.setFont(new Font("����ǹ��� �־�", Font.PLAIN, 23));
		fWestBtn.setBounds(0, 228, 158, 36);
		foodPanel.add(fWestBtn);
		
		ChatBtnDesign nfBackGateBtn = new ChatBtnDesign(" �� �� ");		//�Ĺ�
		nfBackGateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		nfBackGateBtn.setFont(new Font("����ǹ��� �־�", Font.PLAIN, 23));
		nfBackGateBtn.setBounds(0, 265, 158, 36);
		foodPanel.add(nfBackGateBtn);
		
		ChatBtnDesign fBonBtn = new ChatBtnDesign(" �� �� ");			//����
		fBonBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		fBonBtn.setFont(new Font("����ǹ��� �־�", Font.PLAIN, 23));
		fBonBtn.setBounds(0, 303, 158, 36);
		foodPanel.add(fBonBtn);
		
		ChatBtnDesign fYSungBtn = new ChatBtnDesign(" �缺�� ");		//�缺��
		fYSungBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		fYSungBtn.setFont(new Font("����ǹ��� �־�", Font.PLAIN, 23));
		fYSungBtn.setBounds(0, 340, 158, 36);
		foodPanel.add(fYSungBtn);
		
		ChatBtnDesign fYJinBtn = new ChatBtnDesign(" ������");			//������
		fYJinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		fYJinBtn.setFont(new Font("����ǹ��� �־�", Font.PLAIN, 23));
		fYJinBtn.setBounds(0, 377, 158, 36);
		foodPanel.add(fYJinBtn);
		
		/****************************Choice panel (�ó� ���� �ó� ����ǰ)*********************************************/
		
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
		
		
		/**********************************Join panel (ȸ������ �г�)******************************************/
		
		joinPanel.setBounds(0, 0, 960, 540);
		joinPanel.setLayout(null);
		
		joinNameField = new JTextField();		//�̸�
		joinNameField.setBounds(235, 175, 199, 25);
		joinNameField.setFont(Jua);
		joinNameField.setColumns(10);
		joinNameField.setBorder(null);
		joinPanel.add(joinNameField);
		
		joinIdField = new JTextField();			//���̵�
		joinIdField.setBounds(235, 232, 199, 25);
		joinIdField.setFont(Jua);
		joinIdField.setColumns(10);
		joinIdField.setBorder(null);
		joinPanel.add(joinIdField);
		
		joinPwField = new JPasswordField();			//��й�ȣ
		joinPwField.setBounds(235, 287, 199, 25);
		joinPwField.setFont(new Font("Dialog", Font.PLAIN, 17));
		joinPwField.setColumns(10);
		joinPwField.setBorder(null);
		joinPanel.add(joinPwField);
		
		joinPwCheckField = new JPasswordField();	//��й�ȣ Ȯ��
		joinPwCheckField.setBounds(235, 338, 199, 25);
		joinPwCheckField.setFont(new Font("Dialog", Font.PLAIN, 17));
		joinPwCheckField.setColumns(10);
		joinPwCheckField.setBorder(null);
		joinPanel.add(joinPwCheckField);
		
		JComboBox comboBox = new JComboBox(place);	//���
		comboBox.setBounds(583, 175, 207, 27);
		comboBox.setFont(Jua);
		joinPanel.add(comboBox);
			
		joinNickField = new JTextField();			//�г���
		joinNickField.setBounds(583, 232, 207, 27);
		joinNickField.setFont(Jua);
		joinNickField.setColumns(10);
		joinNickField.setBorder(null);
		joinPanel.add(joinNickField);
		
		enrollBtn = new JButton("");				//��� ��ư
		enrollBtn.setBounds(419, 432, 122, 49);
		enrollBtn.setBorder(null);
		enrollBtn.addActionListener(new ActionListener(){	

			@Override
			public void actionPerformed(ActionEvent e) {	//����ϱ� ��ư�� ������ ���� ����
				try {
					/*
					 * ȸ������ ������ �����ϱ�
					 *  
					 * */
					//getText()�� ������ ����
					//���(ComboBox)-comboBox.getSelectedItem().toString()�� ������ ����
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null,"You Failed to Enroll");
				}
				JOptionPane.showMessageDialog(null,"ȸ�������� �����մϴ�!");
				currPanel.setVisible(false);
				loginPanel.setVisible(true);
				currPanel = loginPanel;				
			}		
		});
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
				loginPanel.setVisible(true);	//�α��� �г��� �ٽ� ���̰� �ϰ�
				currPanel = loginPanel;	//���� �г�=�α��� �г�	
			}		
		});
		backBtn.setIcon(new ImageIcon(".\\Image\\back_btn.PNG"));
		joinPanel.add(backBtn);
		
		/***************************************login panel(�α��� �г�)***************************************/
		
		loginPanel.setBounds(0, 0, 960, 540);
		loginPanel.setLayout(null);
		currPanel=loginPanel;
		frame.setSize(loginPanel.getDimension());
		frame.setPreferredSize(loginPanel.getDimension());
		
		idField = new JTextField();
		idField.setBounds(183, 275, 198, 24);
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
				/*
				 * ID PW ��
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

