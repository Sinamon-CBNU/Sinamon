/* ## Read ##
 * 1. ���� �̹��� ��� ������ �� ��ž�� ��η� �Ǿ��ֱ� ������ �� ������ ���� ������ ���� ��� ���� �� �ٲ�ߵ�
 * 		�����Ḧ �̿��ؼ� [./]�̷������� �ϸ� Design tool ����� �ȵ�
 * 2. �Խñ� ����� �Խñ� ������ x�� ������ App�� ���� >> �ϴ� ������ ������ �ڷΰ��� ��ư�� �����ߵ�
 * 3. */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
	private JTextField joinIdField;
	private JTextField joinNameField;
	private JTextField joinPwField;
	private JTextField joinPwCheckField;
	private JTextField joinNickField;
	private JButton enrollBtn;
	private JButton backBtn;
	private JButton clickBtn;
	private JButton recBtn;
	private JButton searchBtn;
	private JTable table;
	private JTable history;
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
		initialize();
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
		
		//����η� �ϴ� �� �˾ƺ��� ��... 
		//ImagePanel foodPanel = new ImagePanel(Toolkit.getDefaultToolkit().getImage(SinamonApp.class.getResource("/Image/board.png")));
		
		ImagePanel mypagePanel = new ImagePanel(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\mypage.png").getImage());
		frame.getContentPane().add(mypagePanel);
		ImagePanel foodPanel = new ImagePanel(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\board.png").getImage());
		frame.getContentPane().add(foodPanel);
		ImagePanel necPanel = new ImagePanel(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\board.png").getImage());
		frame.getContentPane().add(necPanel);
		ImagePanel choicePanel = new ImagePanel(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\choice.png").getImage());
		frame.getContentPane().add(choicePanel);
		ImagePanel joinPanel = new ImagePanel(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\join.png").getImage());
		frame.getContentPane().add(joinPanel);
		ImagePanel loginPanel = new ImagePanel(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\login.png").getImage());
		frame.getContentPane().add(loginPanel);
	
		/**************************** My page (ȸ�� �����丮 �г�)*****************************************/
		mypagePanel.setBounds(0,0,960,540);	//�г� ������
		mypagePanel.setLayout(null);
		
		String[] hHeader=new String[] {"��ȣ","��¥","�ð�","����/����ǰ", "�ó��� ��Ȳ"};
		//����
		Object[][] hData=new Object[][] {
			{"01","11/1","12:00","Ǫ���","�Ϸ�"},
			{"02","11/3","17:00","����","�Ϸ�"},
			{"03","11/4","21:00","��â����","�Ϸ�"},
			{"04","11/20","23:00","����","�Ϸ�"},
			{"05","12/1","12:00","����","����"},
		};
		
		 //���� ���� �Ұ� 
        DefaultTableModel modH = new DefaultTableModel(hData,hHeader) {
        	public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        history =new JTable(modH);
        history.setRowHeight(25);
		history.setFont(new Font("Sanserif", Font.BOLD, 15));
		DefaultTableCellRenderer colH = new DefaultTableCellRenderer();
	    colH.setForeground(Color.BLUE);
	    history.getColumnModel().getColumn(4).setCellRenderer(colH);
		history.setPreferredScrollableViewportSize(new Dimension(700,600));
        history.getTableHeader().setReorderingAllowed(false); // �÷��� �̵� �Ұ�
        history.getTableHeader().setResizingAllowed(false); // �÷� ũ�� ���� �Ұ�
		JScrollPane hScrollPane = new JScrollPane(history);		//��ũ�� ����
		hScrollPane.setBounds(102, 187, 753, 321);
		mypagePanel.add(hScrollPane);
		
		MPbackBtn = new JButton("");
		MPbackBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);
				bfPanel.setVisible(true);
				currPanel=bfPanel;
			}
		});
		MPbackBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\back_btn.PNG"));
		MPbackBtn.setBounds(10, 10, 51, 46);
		MPbackBtn.setBorder(null);
		mypagePanel.add(MPbackBtn);
		
		/****************************necPanel (�Խ��� �г�)*****************************************/
		
		necPanel.setBounds(0, 0, 960, 540);
		necPanel.setLayout(null);
		/*�Խ��� table*/
		String[] necHeader=new String[] {"��ȣ","���","�ð�","�� �� ǰ","�ۼ���","ä��"};
		//����
		Object[][] necData=new Object[][] {
			{"01","16��","����","���� ������","���ڿ���","ä���ϱ�"},
			{"01","16��","����","���� ������","���ڿ���","ä���ϱ�"},
			{"01","16��","����","���� ������","���ڿ���","ä���ϱ�"},
			{"01","16��","����","���� ������","���ڿ���","ä���ϱ�"},
			{"01","16��","����","���� ������","���ڿ���","ä���ϱ�"},
			{"01","16��","����","���� ������","���ڿ���","ä���ϱ�"},
			{"01","16��","����","���� ������","���ڿ���","ä���ϱ�"},
			{"01","16��","����","���� ������","���ڿ���","ä���ϱ�"},
			{"01","16��","����","���� ������","���ڿ���","ä���ϱ�"},
			{"01","16��","����","���� ������","���ڿ���","ä���ϱ�"},
			{"01","16��","����","���� ������","���ڿ���","ä���ϱ�"},
			{"01","16��","����","���� ������","���ڿ���","ä���ϱ�"},
			{"01","16��","����","���� ������","���ڿ���","ä���ϱ�"},
			{"01","16��","����","���� ������","���ڿ���","ä���ϱ�"},
			{"01","16��","����","���� ������","���ڿ���","ä���ϱ�"},
			{"01","16��","����","���� ������","���ڿ���","ä���ϱ�"}
		};
		DefaultTableModel necMod=new DefaultTableModel(necData,necHeader) {	// ���� �Ұ�
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		table=new JTable(necMod);
		DefaultTableCellRenderer colC = new DefaultTableCellRenderer();
	    colC.setBackground(new Color(255, 255, 208));
	    table.getColumnModel().getColumn(5).setCellRenderer(colC);	//���̺� ä��-> �����
	    //���̺� �� �� ����
        table.getColumnModel().getColumn(0).setPreferredWidth(1);
        table.getColumnModel().getColumn(1).setPreferredWidth(1);
        table.getColumnModel().getColumn(2).setPreferredWidth(1);
        table.getColumnModel().getColumn(4).setPreferredWidth(1);
        table.getColumnModel().getColumn(5).setPreferredWidth(1);
		table.setRowHeight(30);
		table.setFont(new Font("Sanserif", Font.BOLD, 17));
		table.setPreferredScrollableViewportSize(new Dimension(700,600));
		table.getTableHeader().setReorderingAllowed(false); // �÷��� �̵� �Ұ�
        table.getTableHeader().setResizingAllowed(false); // �÷� ũ�� ���� �Ұ�
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//table ������ ���� �Ұ�
        /*�����ϰ� ���� ���̺��� Ŭ���ϸ� �Խñ� ����â�� ��Ÿ��*/
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowNum = table.getSelectedRow();
                //�ش� �Խñ��� �����͸� value object�� ��Ƽ� ����â�� ������ �� �ֵ���
                BoardVO vo = new BoardVO();
                //�ش� ���� �Խñ� ������(list)�� �ְ�
                //vo = list.get(rowNum);
                //����â ����!
                new BoardEdit();
            }
        });
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(243, 88, 687, 392);
		necPanel.add(scrollPane);
		
		frontCkBox = new JCheckBox("");
		frontCkBox.setForeground(Color.WHITE);
		frontCkBox.setBackground(Color.PINK);
		frontCkBox.setFont(new Font("HY����M", Font.BOLD, 30));
		frontCkBox.setBounds(62, 175, 21, 21);
		necPanel.add(frontCkBox);
		
		centralCkBox = new JCheckBox("");
		centralCkBox.setForeground(Color.WHITE);
		centralCkBox.setFont(new Font("HY����M", Font.BOLD, 30));
		centralCkBox.setBackground(Color.PINK);
		centralCkBox.setBounds(133, 175, 21, 21);
		necPanel.add(centralCkBox);
		
		westCkBox = new JCheckBox("");
		westCkBox.setForeground(Color.WHITE);
		westCkBox.setFont(new Font("HY����M", Font.BOLD, 30));
		westCkBox.setBackground(Color.PINK);
		westCkBox.setBounds(62, 382, 21, 21);
		necPanel.add(westCkBox);
		
		backCkBox = new JCheckBox("");
		backCkBox.setForeground(Color.WHITE);
		backCkBox.setFont(new Font("HY����M", Font.BOLD, 30));
		backCkBox.setBackground(Color.PINK);
		backCkBox.setBounds(133, 382, 21, 21);
		necPanel.add(backCkBox);
		
		bonCkBox = new JCheckBox("");
		bonCkBox.setForeground(Color.WHITE);
		bonCkBox.setFont(new Font("HY����M", Font.BOLD, 30));
		bonCkBox.setBackground(new Color(255, 245, 238));
		bonCkBox.setBounds(133, 210, 21, 21);
		necPanel.add(bonCkBox);
		
		yangseongCkBox = new JCheckBox("");
		yangseongCkBox.setForeground(Color.WHITE);
		yangseongCkBox.setFont(new Font("HY����M", Font.BOLD, 30));
		yangseongCkBox.setBackground(new Color(255, 245, 238));
		yangseongCkBox.setBounds(145, 254, 21, 21);
		necPanel.add(yangseongCkBox);
		
		yangjinCkBox = new JCheckBox("");
		yangjinCkBox.setForeground(Color.WHITE);
		yangjinCkBox.setFont(new Font("HY����M", Font.BOLD, 30));
		yangjinCkBox.setBackground(new Color(255, 245, 238));
		yangjinCkBox.setBounds(145, 298, 21, 21);
		necPanel.add(yangjinCkBox);
		
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
		searchBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\search_btn.PNG"));
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
		myBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\my_btn.PNG"));
		myBtn.setBounds(886, 20, 49, 48);
		myBtn.setBorder(null);
		necPanel.add(myBtn);
		
		/*�����õ ��ư*/
		JButton recBtn = new JButton("");
		recBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�����õ event
			}
		});
		recBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\rec_btn.png"));
		recBtn.setBounds(55, 485, 102, 37);
		recBtn.setBorder(null);
		necPanel.add(recBtn);
		
		/*�۾��� ��ư*/
		JButton writeBtn = new JButton("");
		writeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�� �ۼ� event
				new BoardWrite();
			}
		});
		writeBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\write_btn.PNG"));
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
		boardBackBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\back_btn.PNG"));
		boardBackBtn.setBounds(0, 3, 49, 45);
		boardBackBtn.setBorder(null);
		necPanel.add(boardBackBtn);
		

		/****************************foodPanel (�Խ��� �г�)*****************************************/
		
		foodPanel.setBounds(0, 0, 960, 540);
		foodPanel.setLayout(null);
		/*�Խ��� table*/
		String[] header=new String[] {"��ȣ","���","�ð�","��  ��","�ۼ���","ä��"};
		//����
		Object[][] data=new Object[][] {
			{"01","16��","����","Ǫ��� �����Ծ��","���ڿ���","ä���ϱ�"},
			{"01","16��","����","Ǫ��� �����Ծ��","���ڿ���","ä���ϱ�"},
			{"01","16��","����","Ǫ��� �����Ծ��","���ڿ���","ä���ϱ�"},
			{"01","16��","����","Ǫ��� �����Ծ��","���ڿ���","ä���ϱ�"},
			{"01","16��","����","Ǫ��� �����Ծ��","���ڿ���","ä���ϱ�"},
			{"01","16��","����","Ǫ��� �����Ծ��","���ڿ���","ä���ϱ�"},
			{"01","16��","����","Ǫ��� �����Ծ��","���ڿ���","ä���ϱ�"},
			{"01","16��","����","Ǫ��� �����Ծ��","���ڿ���","ä���ϱ�"},
			{"01","16��","����","Ǫ��� �����Ծ��","���ڿ���","ä���ϱ�"},
			{"01","16��","����","Ǫ��� �����Ծ��","���ڿ���","ä���ϱ�"},
			{"01","16��","����","Ǫ��� �����Ծ��","���ڿ���","ä���ϱ�"},
			{"01","16��","����","Ǫ��� �����Ծ��","���ڿ���","ä���ϱ�"},
			{"01","16��","����","Ǫ��� �����Ծ��","���ڿ���","ä���ϱ�"},
			{"01","16��","����","Ǫ��� �����Ծ��","���ڿ���","ä���ϱ�"},
			{"01","16��","����","Ǫ��� �����Ծ��","���ڿ���","ä���ϱ�"}
			
		};
		DefaultTableModel foodMod=new DefaultTableModel(data,header) {	// ���� �Ұ�
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		table=new JTable(foodMod);
	    table.getColumnModel().getColumn(5).setCellRenderer(colC);	//���̺� ä��-> �����
	    //���̺� �� �� ����
        table.getColumnModel().getColumn(0).setPreferredWidth(1);
        table.getColumnModel().getColumn(1).setPreferredWidth(1);
        table.getColumnModel().getColumn(2).setPreferredWidth(1);
        table.getColumnModel().getColumn(4).setPreferredWidth(1);
        table.getColumnModel().getColumn(5).setPreferredWidth(1);
		table.setRowHeight(30);
		table.setFont(new Font("Sanserif", Font.BOLD, 17));
		table.setPreferredScrollableViewportSize(new Dimension(700,600));
		table.getTableHeader().setReorderingAllowed(false); // �÷��� �̵� �Ұ�
        table.getTableHeader().setResizingAllowed(false); // �÷� ũ�� ���� �Ұ�
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//table ������ ���� �Ұ�
        /*�����ϰ� ���� ���̺��� Ŭ���ϸ� �Խñ� ����â�� ��Ÿ��*/
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowNum = table.getSelectedRow();
                //�ش� �Խñ��� �����͸� value object�� ��Ƽ� ����â�� ������ �� �ֵ���
                BoardVO vo = new BoardVO();
                //�ش� ���� �Խñ� ������(list)�� �ְ�
                //vo = list.get(rowNum);
                //����â ����!
                new BoardEdit();
            }
        });
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(243, 88, 687, 392);
		foodPanel.add(scrollPane);
		
		frontCkBx = new JCheckBox("");
		frontCkBx.setForeground(Color.WHITE);
		frontCkBx.setBackground(Color.PINK);
		frontCkBx.setFont(new Font("HY����M", Font.BOLD, 30));
		frontCkBx.setBounds(62, 175, 21, 21);
		foodPanel.add(frontCkBx);
		
		centralCkBox = new JCheckBox("");
		centralCkBox.setForeground(Color.WHITE);
		centralCkBox.setFont(new Font("HY����M", Font.BOLD, 30));
		centralCkBox.setBackground(Color.PINK);
		centralCkBox.setBounds(133, 175, 21, 21);
		foodPanel.add(centralCkBox);
		
		westCkBox = new JCheckBox("");
		westCkBox.setForeground(Color.WHITE);
		westCkBox.setFont(new Font("HY����M", Font.BOLD, 30));
		westCkBox.setBackground(Color.PINK);
		westCkBox.setBounds(62, 382, 21, 21);
		foodPanel.add(westCkBox);
		
		backCkBox = new JCheckBox("");
		backCkBox.setForeground(Color.WHITE);
		backCkBox.setFont(new Font("HY����M", Font.BOLD, 30));
		backCkBox.setBackground(Color.PINK);
		backCkBox.setBounds(133, 382, 21, 21);
		foodPanel.add(backCkBox);
		
		bonCkBox = new JCheckBox("");
		bonCkBox.setForeground(Color.WHITE);
		bonCkBox.setFont(new Font("HY����M", Font.BOLD, 30));
		bonCkBox.setBackground(new Color(255, 245, 238));
		bonCkBox.setBounds(133, 210, 21, 21);
		foodPanel.add(bonCkBox);
		
		yangseongCkBox = new JCheckBox("");
		yangseongCkBox.setForeground(Color.WHITE);
		yangseongCkBox.setFont(new Font("HY����M", Font.BOLD, 30));
		yangseongCkBox.setBackground(new Color(255, 245, 238));
		yangseongCkBox.setBounds(145, 254, 21, 21);
		foodPanel.add(yangseongCkBox);
		
		yangjinCkBox = new JCheckBox("");
		yangjinCkBox.setForeground(Color.WHITE);
		yangjinCkBox.setFont(new Font("HY����M", Font.BOLD, 30));
		yangjinCkBox.setBackground(new Color(255, 245, 238));
		yangjinCkBox.setBounds(145, 298, 21, 21);
		foodPanel.add(yangjinCkBox);
		
		/*�� ã�� �˻���*/
		searchField = new JTextField("");
		searchField.setToolTipText("");
		searchField.setForeground(Color.DARK_GRAY);
		searchField.setFont(new Font("Dialog", Font.PLAIN, 15));
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
			}
		});
		searchBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\search_btn.PNG"));
		searchBtn.setBounds(836, 25, 40, 41);
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
		myBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\my_btn.PNG"));
		myBtn.setBounds(886, 20, 49, 48);
		myBtn.setBorder(null);
		foodPanel.add(myBtn);
		
		/*�����õ ��ư*/
		recBtn = new JButton("");
		recBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�����õ event
			}
		});
		recBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\rec_btn.png"));
		recBtn.setBounds(55, 485, 102, 37);
		recBtn.setBorder(null);
		foodPanel.add(recBtn);
		
		/*�۾��� ��ư*/
		writeBtn = new JButton("");
		writeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�� �ۼ� event
				new BoardWrite();
			}
		});
		writeBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\write_btn.PNG"));
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
		boardBackBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\back_btn.PNG"));
		boardBackBtn.setBounds(0, 3, 49, 45);
		boardBackBtn.setBorder(null);
		foodPanel.add(boardBackBtn);
		
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
		foodBtn.setBounds(127, 154, 307, 309);
		foodBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\Ch_food.PNG"));
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
		necBtn.setBounds(525, 154, 307, 309);
		necBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\Ch_nec.PNG"));
		necBtn.setBorder(null);
		choicePanel.add(necBtn);
		
		
		/**********************************Join panel (ȸ������ �г�)******************************************/
		
		joinPanel.setBounds(0, 0, 960, 540);
		joinPanel.setLayout(null);
		
		joinIdField = new JTextField();
		joinIdField.setBounds(235, 175, 199, 25);
		joinIdField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		joinIdField.setColumns(10);
		joinIdField.setBorder(null);
		joinPanel.add(joinIdField);
		
		joinNameField = new JTextField();
		joinNameField.setBounds(235, 232, 199, 25);
		joinNameField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		joinNameField.setColumns(10);
		joinNameField.setBorder(null);
		joinPanel.add(joinNameField);
		
		joinPwField = new JTextField();
		joinPwField.setBounds(235, 287, 199, 25);
		joinPwField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		joinPwField.setColumns(10);
		joinPwField.setBorder(null);
		joinPanel.add(joinPwField);
		
		joinPwCheckField = new JTextField();
		joinPwCheckField.setBounds(235, 338, 199, 25);
		joinPwCheckField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		joinPwCheckField.setColumns(10);
		joinPwCheckField.setBorder(null);
		joinPanel.add(joinPwCheckField);
		
		JComboBox comboBox = new JComboBox(place);
		comboBox.setBounds(583, 225, 106, 27);
		comboBox.setFont(new Font("HY����M", Font.PLAIN, 17));
		joinPanel.add(comboBox);
		
		joinNickField = new JTextField();
		joinNickField.setBounds(583, 286, 207, 27);
		joinNickField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		joinNickField.setColumns(10);
		joinNickField.setBorder(null);
		joinPanel.add(joinNickField);
		
		enrollBtn = new JButton("");
		enrollBtn.setBounds(419, 426, 122, 49);
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
				currPanel = (JPanel) loginPanel;				
			}		
		});
		enrollBtn.setPressedIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\enroll_click_btn.PNG"));
		joinPanel.add(enrollBtn);
		enrollBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\enroll_btn.PNG"));
		
		backBtn = new JButton("");
		backBtn.setBounds(5, 10, 49, 49);
		backBtn.setBorder(null);
		backBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);
				loginPanel.setVisible(true);
				currPanel = (JPanel) loginPanel;				
			}		
		});
		backBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\back_btn.PNG"));
		joinPanel.add(backBtn);
		
		/***************************************login panel(�α��� �г�)***************************************/
		
		loginPanel.setBounds(0, 0, 960, 540);
		loginPanel.setLayout(null);
		currPanel=loginPanel;
		frame.setSize(loginPanel.getDimension());
		frame.setPreferredSize(loginPanel.getDimension());
		
		idField = new JTextField();
		idField.setBounds(183, 274, 198, 24);
		idField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		loginPanel.add(idField);
		idField.setColumns(10);
		idField.setBorder(null);
		
		pwField = new JPasswordField();
		pwField.setBounds(183, 322, 198, 26);
		pwField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
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
		loginBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\login_btn.PNG"));
		loginBtn.setPressedIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\login_click_btn.PNG"));
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
		joinBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\join_btn.PNG"));
		joinBtn.setPressedIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\join_click_btn.PNG"));
		loginPanel.add(joinBtn);
		
		joinPanel.setVisible(false);
		necPanel.setVisible(false);
		foodPanel.setVisible(false);
		choicePanel.setVisible(false);
		mypagePanel.setVisible(false);
	}
}

