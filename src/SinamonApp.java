/*���� �̹��� ��� ������ �� ��ž�� ��η� �Ǿ��ֱ� ������ �� ������ ���� ������ ���� ��� ���� �� �ٲ�ߵ�*/
/*�����Ḧ �̿��ؼ� ./�̷������� �ϸ� Design tool ����� �ȵ�*/

import java.awt.BorderLayout;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.SystemColor;

public class SinamonApp {
	
	//check �ӽ� ID,PW
	private final String ID="Hello";
	private final String PASS="1234";
	//ID PW
	/************************/
	/************************/
	/************************/
	/************************/
	
	private final String place[]= {"�� ��", "�� ��","�� ��","�� ��","�� ��","�� �� ��","�� �� ��"};
	
	private JFrame frame;
	private JTextField idField;
	private JPasswordField pwField;
	private JPanel currPanel;	//���� �г�
	private JTextField joinIdField;
	private JTextField joinNameField;
	private JTextField joinPwField;
	private JTextField joinPwCheckField;
	private JTextField joinNickField;
	private JButton enrollBtn;
	private JButton backBtn;
	private JButton clickBtn;
	private JTable table;
	private JTable history;
	private JCheckBox centralChckbx_1;
	private JCheckBox centralChckbx_2;
	private JCheckBox westChckbx_3;
	private JCheckBox bonChckbx_4;
	private JCheckBox yangseongChckbx_5;
	private JCheckBox yangjinChckbx_6;
	private JTextField searchField;
	private JButton MPbackBtn;

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
		frame.setBounds(100, 100, 960, 540);
		frame.setPreferredSize(new Dimension(960,540));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//ȭ���� ������ ���α׷� ����
		frame.setResizable(false); 		//ũ�� ����
		
		//����η� �ϴ� �� �˾ƺ��� ��... 
		//ImagePanel boardPanel = new ImagePanel(Toolkit.getDefaultToolkit().getImage(SinamonApp.class.getResource("/Image/board.png")));
		
		ImagePanel mypagePanel = new ImagePanel(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\mypage.png").getImage());
		frame.getContentPane().add(mypagePanel);
		ImagePanel boardPanel = new ImagePanel(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\board.png").getImage());
		frame.getContentPane().add(boardPanel);
		ImagePanel choicePanel = new ImagePanel(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\choice.png").getImage());
		frame.getContentPane().add(choicePanel);
		ImagePanel joinPanel = new ImagePanel(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\join.png").getImage());
		frame.getContentPane().add(joinPanel);
		ImagePanel loginPanel = new ImagePanel(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\login.png").getImage());
		frame.getContentPane().add(loginPanel);
	
		/**************************** My page (ȸ�� �����丮 �г�)*****************************************/
		mypagePanel.setBounds(0,0,960,540);	//�г� ������
		mypagePanel.setLayout(null);
		
		String[] hHeader=new String[] {"��ȣ","��¥","�ð�","�� ��", "�ó��� ��Ȳ"};
		//����
		Object[][] hData=new Object[][] {
			{"01","11/1","12:00","Ǫ���","�Ϸ�"},
			{"02","11/3","17:00","����","�Ϸ�"},
			{"03","11/4","21:00","��â����","�Ϸ�"},
			{"04","11/20","23:00","�߹�","�Ϸ�"},
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
				boardPanel.setVisible(true);
				currPanel=boardPanel;
			}
		});
		MPbackBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\back_btn.PNG"));
		MPbackBtn.setBounds(10, 10, 51, 46);
		MPbackBtn.setBorder(null);
		mypagePanel.add(MPbackBtn);
		

		/****************************boardPanel (�Խ��� �г�)*****************************************/
		
		boardPanel.setBounds(0, 0, 960, 540);
		boardPanel.setLayout(null);
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
			{"01","16��","����","Ǫ��� �����Ծ��","���ڿ���","ä���ϱ�"}
			
		};
		DefaultTableModel mod=new DefaultTableModel(data,header) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		table=new JTable(mod);
		DefaultTableCellRenderer colC = new DefaultTableCellRenderer();
	    colC.setBackground(new Color(255, 255, 208));
	    table.getColumnModel().getColumn(5).setCellRenderer(colC);	//���̺� ä��-> �����
	    //���̺� �� �� ����
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(10);
        table.getColumnModel().getColumn(2).setPreferredWidth(10);
        table.getColumnModel().getColumn(4).setPreferredWidth(10);
        table.getColumnModel().getColumn(5).setPreferredWidth(10);
		table.setRowHeight(30);
		table.setFont(new Font("Sanserif", Font.BOLD, 17));
		table.setPreferredScrollableViewportSize(new Dimension(700,600));
		table.getTableHeader().setReorderingAllowed(false); // �÷��� �̵� �Ұ�
        table.getTableHeader().setResizingAllowed(false); // �÷� ũ�� ���� �Ұ�
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
		boardPanel.add(scrollPane);
		
		JCheckBox frontChckbx = new JCheckBox("");
		frontChckbx.setForeground(Color.WHITE);
		frontChckbx.setBackground(Color.PINK);
		frontChckbx.setFont(new Font("HY����M", Font.BOLD, 30));
		frontChckbx.setBounds(62, 175, 21, 21);
		boardPanel.add(frontChckbx);
		
		centralChckbx_1 = new JCheckBox("");
		centralChckbx_1.setForeground(Color.WHITE);
		centralChckbx_1.setFont(new Font("HY����M", Font.BOLD, 30));
		centralChckbx_1.setBackground(Color.PINK);
		centralChckbx_1.setBounds(133, 175, 21, 21);
		boardPanel.add(centralChckbx_1);
		
		centralChckbx_2 = new JCheckBox("");
		centralChckbx_2.setForeground(Color.WHITE);
		centralChckbx_2.setFont(new Font("HY����M", Font.BOLD, 30));
		centralChckbx_2.setBackground(Color.PINK);
		centralChckbx_2.setBounds(62, 382, 21, 21);
		boardPanel.add(centralChckbx_2);
		
		westChckbx_3 = new JCheckBox("");
		westChckbx_3.setForeground(Color.WHITE);
		westChckbx_3.setFont(new Font("HY����M", Font.BOLD, 30));
		westChckbx_3.setBackground(Color.PINK);
		westChckbx_3.setBounds(133, 382, 21, 21);
		boardPanel.add(westChckbx_3);
		
		bonChckbx_4 = new JCheckBox("");
		bonChckbx_4.setForeground(Color.WHITE);
		bonChckbx_4.setFont(new Font("HY����M", Font.BOLD, 30));
		bonChckbx_4.setBackground(new Color(255, 245, 238));
		bonChckbx_4.setBounds(133, 210, 21, 21);
		boardPanel.add(bonChckbx_4);
		
		yangseongChckbx_5 = new JCheckBox("");
		yangseongChckbx_5.setForeground(Color.WHITE);
		yangseongChckbx_5.setFont(new Font("HY����M", Font.BOLD, 30));
		yangseongChckbx_5.setBackground(new Color(255, 245, 238));
		yangseongChckbx_5.setBounds(145, 254, 21, 21);
		boardPanel.add(yangseongChckbx_5);
		
		yangjinChckbx_6 = new JCheckBox("");
		yangjinChckbx_6.setForeground(Color.WHITE);
		yangjinChckbx_6.setFont(new Font("HY����M", Font.BOLD, 30));
		yangjinChckbx_6.setBackground(new Color(255, 245, 238));
		yangjinChckbx_6.setBounds(145, 298, 21, 21);
		boardPanel.add(yangjinChckbx_6);
		
		/*�� ã�� �˻���*/
		searchField = new JTextField("");
		searchField.setToolTipText("");
		searchField.setForeground(Color.DARK_GRAY);
		searchField.setFont(new Font("Dialog", Font.PLAIN, 15));
		searchField.setBounds(686, 29, 149, 36);
		searchField.setBorder(null);
		boardPanel.add(searchField);
		searchField.setColumns(10);
		
		/*�� ã�� ��ư*/
		JButton searchBtn = new JButton("");
		searchBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//�� ã��!!!
				//searchField �����ϱ�
				/************************/
				/************************/
				/************************/
				/************************/
			}
			
		});
		searchBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\search_btn.PNG"));
		searchBtn.setBounds(836, 25, 40, 41);
		searchBtn.setBorder(null);
		boardPanel.add(searchBtn);
		
		JButton myBtn = new JButton("");
		myBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);
				mypagePanel.setVisible(true);
				currPanel=mypagePanel;
			}
		});
		
		myBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\my_btn.PNG"));
		myBtn.setBounds(886, 20, 49, 48);
		myBtn.setBorder(null);
		boardPanel.add(myBtn);
		
		JButton recBtn = new JButton("");
		recBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�����õ event
			}
		});
		recBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\rec_btn.png"));
		recBtn.setBounds(54, 487, 105, 36);
		recBtn.setBorder(null);
		boardPanel.add(recBtn);
		
		JButton writeBtn = new JButton("");
		writeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�� �ۼ� event
				new BoardWrite();
			}
		});
		writeBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\write_btn.PNG"));
		writeBtn.setBounds(861, 487, 78, 35);
		boardPanel.add(writeBtn);
		
		/****************************Choice panel (�ó� ���� �ó� ����ǰ)*********************************************/
		
		choicePanel.setBounds(0, 0, 960, 540);
		choicePanel.setLayout(null);
		
		JButton foodBtn = new JButton("");
		foodBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);
				boardPanel.setVisible(true);
				currPanel = boardPanel;
			}	
		});
		foodBtn.setBounds(127, 154, 307, 309);
		foodBtn.setIcon(new ImageIcon("C:\\Users\\SeoMinjung\\eclipse-workspace\\Sinamon\\Image\\Ch_food.PNG"));
		foodBtn.setBorder(null);
		choicePanel.add(foodBtn);
		
		JButton necBtn = new JButton("");
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
		
		comboBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//��� �� ���� ����
			}
		});
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
			public void actionPerformed(ActionEvent e) {	//ȸ������ ������ ����
				try {
					/*****ȣ���̰� �����ؾߵſ�����*********/
					//getText()�� ������ ����
					
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
		idField.setBounds(183, 272, 199, 24);
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
		boardPanel.setVisible(false);
		choicePanel.setVisible(false);
		mypagePanel.setVisible(false);
	}
}

