package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.QLTVmodel;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultCaret;
import javax.swing.AbstractButton;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Panel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Date;
import view.QLTVview;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.JToggleButton;
import javax.swing.JFrame;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import java.util.Date;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;

public class QLTVview extends JFrame {
	static DefaultTableModel tblmodel;
	private JPanel contentPane;
	QLTVmodel model;
	private JTextField cmnd1;
	private JRadioButton nam;
	private JRadioButton nu;
	private static JTable table;
	private static JTextField cmnd;
	private static JTextField hovaten;
	private static JTextField ngaysinh;
	private static JTextField ngaymuon;
	private static JTextField ngaytra;
	private static JComboBox tensach1;
	private static JComboBox tensach;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel gioitinh;
	protected String selectedItem="";
	private option o;
	private viewthongke tk;
	
	//thủ tục kết nối
	public static Connection getConnect(String strServer, String strDatabase) {
		Connection conn=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl="jdbc:sqlserver://"+strServer+":1433;databaseName="+strDatabase+";integratedSecurity=true;";
			conn=DriverManager.getConnection(connectionUrl);
		}catch (SQLException e) {
			System.out.println("SQL Exception: " + e.toString());
		}catch (ClassNotFoundException cE) {
			System.out.println("Class Not Found Exeption: " + cE.toString());
		}
		return conn;
	}
	
	//phần main
	public static void main(String[] args) {
		new QLTVview();
		initTable();
		loadAllEmployees();
		
		Connection conn=getConnect("DESKTOP-RT3IO2H", "QLTV");
		try {
			Statement statement = conn.createStatement();
			final String sql = "SELECT * FROM DOCGIA";
			ResultSet rs= statement.executeQuery(sql);
			while (rs.next()) {
				String tensach = rs.getString("tensach");
				String cmnd=rs.getString("CMND");
				String hovaten=rs.getString("HOVATEN");
				String ngaysinh=rs.getString("NGAYSINH");
				boolean gioitinh=rs.getBoolean("GIOITINH");
				String ngaymuon=rs.getString("NGAYMUON");
				String ngaytra=rs.getString("NGAYTRA");
			
				System.out.println(tensach +" "+cmnd+" "+hovaten+" "+ngaysinh+" "+gioitinh+" "+ngaymuon+" " + ngaytra);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//Manage employee form
	
	//* static *
	//  initTable :ham khởi tạo table
	public static void initTable() {
		tblmodel = new DefaultTableModel();
		tblmodel.setColumnIdentifiers(new String[] {"tensach","cmnd","hovaten","ngaysinh","gioitinh","ngaymuon","ngaytra" ,"tentacgia"});
		table.setModel(tblmodel);	
	}
	
	// loadAllEmployee : tải lại tất cả các đối tượng trong bảng docgia
	public static void loadAllEmployees() {
		try {
			Connection conn=getConnect("DESKTOP-RT3IO2H", "QLTV");
			//cục bộ nên k cần rs.close()
			final String sql = "SELECT * FROM DOCGIA";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs= statement.executeQuery();
			tblmodel.setRowCount(0);
			while (rs.next()) {
				String[] row = new String[] {
					rs.getString("TENSACH"),rs.getString("CMND"),rs.getString("HOVATEN"),rs.getString("NGAYSINH"),rs.getString("GIOITINH"),rs.getString("NGAYMUON"),rs.getString("NGAYTRA")
				};
				tblmodel.addRow(row);
			}
			tblmodel.fireTableDataChanged();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void btnsaveexcel() {
		if (JOptionPane.showConfirmDialog(this,"BẠN MUỐN LƯU KHÔNG")==JOptionPane.NO_OPTION) {
			return;
		}
		new openexcel();
	}
	
	public void btnthongketheongay() {
		new viewthongketheloaibdtron();
	}
	
	public void btnthongke() {
		new viewthongke();
		
		
	}
	
	public void btnnoiquy() {
		viewnoiquy nq = new viewnoiquy();
		nq.setVisible(true);
	}
	
	public void btntrove() {
		o= new option();
		o.setVisible(true);
		this.dispose();
	}
	
	public void btnfind() {
		try {
			Connection conn=getConnect("DESKTOP-RT3IO2H", "QLTV");
			//cục bộ nên k cần rs.close()
			final String sql;
			int len=cmnd1.getText().length();
			
			if (len>0) {
				sql = "SELECT * FROM DOCGIA \r\n"
						+ "WHERE CMND='"+cmnd1.getText()+"' \r\n"
						+ "AND TENSACH=N'"+tensach1.getSelectedItem()+"'";
			}
			else {
				sql = "SELECT * FROM DOCGIA \r\n"
						+ "where TENSACH=N'"+tensach1.getSelectedItem()+"'";
			}
			
			
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			ResultSet rs= statement.executeQuery();
			
			tblmodel.setRowCount(0);
			while (rs.next()) {
				String[] row = new String[] {
					rs.getString("TENSACH"),rs.getString("CMND"),rs.getString("HOVATEN"),rs.getString("NGAYSINH"),rs.getString("GIOITINH"),rs.getString("NGAYMUON"),rs.getString("NGAYTRA")
				};
				tblmodel.addRow(row);
			}
			tblmodel.fireTableDataChanged();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void btnsaptangcmnd() {
		try {
			Connection conn=getConnect("DESKTOP-RT3IO2H", "QLTV");
			//cục bộ nên k cần rs.close()
			final String sql = "select * from docgia\r\n"
								+ "order by cmnd asc";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			ResultSet rs= statement.executeQuery();
			tblmodel.setRowCount(0);
			while (rs.next()) {
				String[] row = new String[] {
					rs.getString("TENSACH"),rs.getString("CMND"),rs.getString("HOVATEN"),rs.getString("NGAYSINH"),rs.getString("GIOITINH"),rs.getString("NGAYMUON"),rs.getString("NGAYTRA")
				};
				tblmodel.addRow(row);
			}
			tblmodel.fireTableDataChanged();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void btnsapgiamcmnd() {
		try {
			Connection conn=getConnect("DESKTOP-RT3IO2H", "QLTV");
			//cục bộ nên k cần rs.close()
			final String sql = "select * from docgia\r\n"
								+ "order by cmnd desc";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			ResultSet rs= statement.executeQuery();
			tblmodel.setRowCount(0);
			while (rs.next()) {
				String[] row = new String[] {
					rs.getString("TENSACH"),rs.getString("CMND"),rs.getString("HOVATEN"),rs.getString("NGAYSINH"),rs.getString("GIOITINH"),rs.getString("NGAYMUON"),rs.getString("NGAYTRA")
				};
				tblmodel.addRow(row);
			}
			tblmodel.fireTableDataChanged();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void btnsapnam() {
		try {
			Connection conn=getConnect("DESKTOP-RT3IO2H", "QLTV");
			//cục bộ nên k cần rs.close()
			final String sql = "select * from docgia\r\n"
							+ "where gioitinh='1'";	
			PreparedStatement statement = conn.prepareStatement(sql);
			
			ResultSet rs= statement.executeQuery();
			tblmodel.setRowCount(0);
			while (rs.next()) {
				String[] row = new String[] {
					rs.getString("TENSACH"),rs.getString("CMND"),rs.getString("HOVATEN"),rs.getString("NGAYSINH"),rs.getString("GIOITINH"),rs.getString("NGAYMUON"),rs.getString("NGAYTRA")
				};
				tblmodel.addRow(row);
			}
			tblmodel.fireTableDataChanged();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void btnsapnu() {
		try {
			Connection conn=getConnect("DESKTOP-RT3IO2H", "QLTV");
			//cục bộ nên k cần rs.close()
			final String sql = "select * from docgia\r\n"
							+ "where gioitinh='0'";	
			PreparedStatement statement = conn.prepareStatement(sql);
			
			ResultSet rs= statement.executeQuery();
			tblmodel.setRowCount(0);
			while (rs.next()) {
				String[] row = new String[] {
					rs.getString("TENSACH"),rs.getString("CMND"),rs.getString("HOVATEN"),rs.getString("NGAYSINH"),rs.getString("GIOITINH"),rs.getString("NGAYMUON"),rs.getString("NGAYTRA")
				};
				tblmodel.addRow(row);
			}
			tblmodel.fireTableDataChanged();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void btnsave() {
		if (JOptionPane.showConfirmDialog(this,"BẠN MUỐN LƯU KHÔNG")==JOptionPane.NO_OPTION) {
			return;
		}
		try {
			
			Connection conn=getConnect("DESKTOP-RT3IO2H", "QLTV");
			//cục bộ nên k cần rs.close()
			final String sql = "insert into docgia (tensach,cmnd,hovaten,ngaysinh,gioitinh,ngaymuon,ngaytra) values(?,?,?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1,(String) tensach.getSelectedItem());
			statement.setString(2,cmnd.getText());
			statement.setString(3,hovaten.getText());
			statement.setString(4,ngaysinh.getText());
			//lấy btngroup
			String a =buttonGroup.getSelection().getActionCommand();
			if (a=="nam") {
				a="1";
			}
			else {
				a="0";
			}
			statement.setString(5,a);
			statement.setString(6,ngaymuon.getText());
			System.out.print(ngaymuon.getText());
			statement.setString(7,ngaytra.getText());
			
			final String sql1 = "select * from docgia where cmnd=? and tensach=?";
			PreparedStatement statement1 = conn.prepareStatement(sql1);
			statement1.setString(1,cmnd.getText());
			statement1.setString(2, (String) tensach.getSelectedItem());
			
			ResultSet rs1= statement1.executeQuery();
			
			final String sql2 = "SELECT DATEDIFF(dayofyear, ?, ?)";
			PreparedStatement statement2 = conn.prepareStatement(sql2);
			statement2.setString(1, ngaymuon.getText());
			statement2.setString(2, ngaytra.getText());
			
			ResultSet rs2= statement2.executeQuery();
			
			final String sql3 = "select count(*) from docgia \r\n"
								+ "where cmnd= ?";
			PreparedStatement statement3 = conn.prepareStatement(sql3);
			statement3.setString(1, cmnd.getText());
			
			ResultSet rs3= statement3.executeQuery();
			
			if (rs1.next()) {
				JOptionPane.showMessageDialog(this, "Lưu ý : Sách không được mượn cùng lúc 2 quyển giống nhau");
			} else if (rs2.next()) {
				int a1=rs2.getInt(1);
				if (a1>7) {
					JOptionPane.showMessageDialog(this, "Lưu ý : Sách không được mượn quá 7 ngày");
				}else if (rs3.next()) {
					
					if (rs3.getInt(1)>=3) {
						JOptionPane.showMessageDialog(this, "Lưu ý : 1 người không được mượn quá 3 cuốn .");
					}
					else {
						JOptionPane.showMessageDialog(this ,"ĐÃ LƯU THÀNH CÔNG");
						statement.executeUpdate();
						loadAllEmployees();
						statement.close();
						conn.close();	
					}
				}
				
			} else {
				
				JOptionPane.showMessageDialog(this ,"ĐÃ LƯU THÀNH CÔNG");
				statement.executeUpdate();
				loadAllEmployees();
				statement.close();
				conn.close();	
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void btnupdate() {
		if (JOptionPane.showConfirmDialog(this,"BẠN MUỐN CẬP NHẬT KHÔNG")==JOptionPane.NO_OPTION) {
			return;
		}
		try {
			
			Connection conn=getConnect("DESKTOP-RT3IO2H", "QLTV");
			//cục bộ nên k cần rs.close()
			
			
			final String sql = "update docgia set tensach= ?, hovaten=? ,\r\n"
					+ "ngaysinh=?,gioitinh=?,ngaymuon=?,ngaytra=?\r\n"
					+ "where cmnd = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1,(String) tensach.getSelectedItem());
			statement.setString(7,cmnd.getText());
			statement.setString(2,hovaten.getText());
			statement.setString(3,ngaysinh.getText());
			System.out.print(ngaysinh.getText());
			//lấy btngroup
			String a =buttonGroup.getSelection().getActionCommand();
			if (a=="nam") {
				a="1";
			}
			else {
				a="0";
			}
			statement.setString(4,a);
			statement.setString(5,ngaymuon.getText());
			statement.setString(6,ngaytra.getText());
			
			JOptionPane.showMessageDialog(this ,"ĐÃ CẬP THÀNH CÔNG");
			
			
			loadAllEmployees();
			statement.executeUpdate();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void btndelete() {
		if (JOptionPane.showConfirmDialog(this,"BẠN MUỐN XÓA KHÔNG")==JOptionPane.NO_OPTION) {
			return;
		}
		try {
			
			Connection conn=getConnect("DESKTOP-RT3IO2H", "QLTV");
			//cục bộ nên k cần rs.close()
			
			
			final String sql = "DELETE from docgia where cmnd=? and tensach=?";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(1, cmnd.getText());
			statement.setString(2,(String) tensach.getSelectedItem());
			JOptionPane.showMessageDialog(this ,"ĐÃ XÓA THÀNH CÔNG");
			statement.executeUpdate();
			
			loadAllEmployees();
			
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void loadlistsach() {
		Connection conn=getConnect("DESKTOP-RT3IO2H", "QLTV");
		try {
			//cục bộ nên k cần rs.close()
			Statement statement = conn.createStatement();
			final String sql = "SELECT TENSACH FROM SACH";
			ResultSet rs= statement.executeQuery(sql);
			while (rs.next()) {
				tensach.addItem(rs.getString("TENSACH"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// phần view
	public QLTVview() {
		
		Connection conn=getConnect("DESKTOP-RT3IO2H", "QLTV");
		this.model= new QLTVmodel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1165, 707);
		this.setLocation(100,0);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 240));
		panel.setBounds(10, 11, 1101, 70);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("  Số CMND");
		lblNewLabel.setIcon(new ImageIcon("D:\\project library\\identity-card-icon (1).png"));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(73, 11, 152, 48);
		panel.add(lblNewLabel);
		
		cmnd1 = new JTextField();
		cmnd1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cmnd1.setBounds(235, 12, 228, 48);
		panel.add(cmnd1);
		cmnd1.setColumns(10);
		
		JLabel lblMSch = new JLabel("Tên sách");
		lblMSch.setIcon(new ImageIcon("D:\\project library\\62863-books-icon.png"));
		lblMSch.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMSch.setBounds(485, 11, 137, 48);
		panel.add(lblMSch);
		
		JButton btnNewButton = new JButton("   FIND");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setIcon(new ImageIcon("D:\\project library\\search-icon.png"));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnfind();
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(927, 11, 145, 48);
		panel.add(btnNewButton);
		
		tensach1 = new JComboBox();
		tensach1.setEditable(true);
		tensach1.setBackground(new Color(255, 255, 255));
		tensach1.setBounds(632, 11, 211, 48);
		panel.add(tensach1);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(719, 11, 26, 14);
		panel.add(lblNewLabel_5);
		try {
			Statement statement = conn.createStatement();
			final String sql = "SELECT TENSACH FROM SACH";
			ResultSet rs= statement.executeQuery(sql);
			while (rs.next()) {
				tensach1.addItem(rs.getString("TENSACH"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row= table.getSelectedRow();
				if (row>=0) {
					tensach.setSelectedItem(table.getValueAt(row, 0).toString());
					cmnd.setText(table.getValueAt(row, 1).toString());
					hovaten.setText(table.getValueAt(row, 2).toString());
					ngaysinh.setText(table.getValueAt(row, 3).toString());
					
					ngaymuon.setText(table.getValueAt(row, 5).toString());
					ngaytra.setText(table.getValueAt(row, 6).toString());
					
				}
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 s\u00E1ch", "S\u1ED1 cmnd", "H\u1ECD v\u00E0 t\u00EAn", " Ng\u00E0y sinh", "Gi\u1EDBi t\u00EDnh", "Ng\u00E0y m\u01B0\u1EE3n ", "Ng\u00E0y tr\u1EA3", "T\u00EAn t\u00E1c gi\u1EA3", "Tên tác giả"
			}
		));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 132, 1101, 184);
		contentPane.add(scrollPane);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(104, 92, 666, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel_2 = new JLabel("  Thông tin độc giả");
		lblNewLabel_2.setIcon(new ImageIcon("D:\\project library\\độc giả.png"));
		lblNewLabel_2.setBackground(new Color(211, 211, 211));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(60, 336, 216, 33);
		contentPane.add(lblNewLabel_2);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(104, 327, 666, 2);
		contentPane.add(separator_1);
		
		JLabel lblNewLabel_3 = new JLabel("S\u1ED1 CMND");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setBackground(Color.BLACK);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(30, 380, 71, 33);
		contentPane.add(lblNewLabel_3);
		
		cmnd = new JTextField();
		cmnd.setBackground(new Color(255, 250, 240));
		cmnd.setBounds(104, 380, 135, 33);
		contentPane.add(cmnd);
		cmnd.setColumns(10);
		
		JLabel lblNewLabel_3_1 = new JLabel("H\u1ECD v\u00E0 t\u00EAn");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_1.setBounds(30, 423, 71, 33);
		contentPane.add(lblNewLabel_3_1);
		
		hovaten = new JTextField();
		hovaten.setBackground(new Color(255, 250, 240));
		hovaten.setColumns(10);
		hovaten.setBounds(104, 423, 135, 33);
		contentPane.add(hovaten);
		
		JLabel lblNewLabel_3_2 = new JLabel("Ng\u00E0y sinh");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_2.setBounds(30, 467, 77, 33);
		contentPane.add(lblNewLabel_3_2);
		
		ngaysinh = new JTextField();
		ngaysinh.setBackground(new Color(255, 250, 240));
		ngaysinh.setColumns(10);
		ngaysinh.setBounds(104, 467, 135, 33);
		contentPane.add(ngaysinh);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(454, 372, 2, 173);
		contentPane.add(separator_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("  Thông tin sách");
		lblNewLabel_2_1.setIcon(new ImageIcon("D:\\project library\\Books-2-icon.png"));
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(507, 336, 200, 33);
		contentPane.add(lblNewLabel_2_1);
		
		gioitinh = new JLabel("Giới tính");
		gioitinh.setFont(new Font("Tahoma", Font.BOLD, 14));
		gioitinh.setBounds(263, 392, 58, 33);
		contentPane.add(gioitinh);
		
		
		nam = new JRadioButton("Nam");
		buttonGroup.add(nam);
		nam.setActionCommand("nam");
		nam.setBounds(328, 405, 58, 30);
		contentPane.add(nam);
		
		nu = new JRadioButton("Nữ");
		buttonGroup.add(nu);
		nu.setActionCommand("nu");
		nu.setBounds(388, 405, 58, 30);
		contentPane.add(nu);
		
		JLabel lblNewLabel_3_3 = new JLabel("Tên sách");
		lblNewLabel_3_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_3.setBounds(482, 380, 64, 33);
		contentPane.add(lblNewLabel_3_3);
		
		JLabel lblNewLabel_3_5 = new JLabel("Ng\u00E0y m\u01B0\u1EE3n ");
		lblNewLabel_3_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_5.setBounds(482, 423, 92, 33);
		contentPane.add(lblNewLabel_3_5);
		
		ngaymuon = new JTextField();
		ngaymuon.setBackground(new Color(255, 250, 240));
		ngaymuon.setColumns(10);
		ngaymuon.setBounds(574, 425, 135, 33);
		contentPane.add(ngaymuon);
		
		JLabel lblNewLabel_3_6 = new JLabel("Ng\u00E0y tr\u1EA3");
		lblNewLabel_3_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_6.setBounds(482, 467, 64, 33);
		contentPane.add(lblNewLabel_3_6);
		
		ngaytra = new JTextField();
		ngaytra.setBackground(new Color(255, 250, 240));
		ngaytra.setColumns(10);
		ngaytra.setBounds(574, 469, 135, 33);
		contentPane.add(ngaytra);
		
		JButton btnthem = new JButton("TH\u00CAM");
		btnthem.setBackground(new Color(255, 250, 240));
		btnthem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cmnd.setText("");
				hovaten.setText("");
				ngaysinh.setText("");
				ngaymuon.setText("");
				ngaytra.setText("");
				tensach.removeAllItems();	
				selectedItem="";
				loadlistsach();
				
			}
			
		});
		btnthem.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnthem.setBounds(42, 577, 116, 39);
		contentPane.add(btnthem);
		
		JButton btncapnhat = new JButton("C\u1EACP NH\u1EACT");
		btncapnhat.setBackground(new Color(245, 245, 245));
		btncapnhat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnupdate();
			}
		});
		btncapnhat.setFont(new Font("Tahoma", Font.BOLD, 18));
		btncapnhat.setBounds(179, 577, 148, 39);
		contentPane.add(btncapnhat);
		
		JButton btnluu = new JButton("L\u01AFU");
		btnluu.setBackground(new Color(255, 250, 240));
		btnluu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnsave(); 
			}
		});
		btnluu.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnluu.setBounds(350, 577, 116, 39);
		contentPane.add(btnluu);
		
		JButton btnxoa = new JButton("XÓA");
		btnxoa.setBackground(new Color(255, 250, 240));
		btnxoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btndelete();
			}
		});
		btnxoa.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnxoa.setBounds(488, 577, 116, 39);
		contentPane.add(btnxoa);
		
		tensach = new JComboBox();
		tensach.addActionListener(new ActionListener() {
			//tìm actionPerformed
			public void actionPerformed(ActionEvent e) {
				
				selectedItem = (String) tensach.getSelectedItem();
				System.out.print(selectedItem);
			}
		});
		
		
		tensach.setBounds(574, 378, 135, 33);
		contentPane.add(tensach);
		
		JButton btnSpXpTng_1 = new JButton("Tăng dần");
		btnSpXpTng_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnsaptangcmnd();
			}
			
		});
		btnSpXpTng_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSpXpTng_1.setBackground(new Color(255, 250, 240));
		btnSpXpTng_1.setBounds(904, 402, 116, 33);
		contentPane.add(btnSpXpTng_1);
		
		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setOrientation(SwingConstants.VERTICAL);
		separator_2_1.setBounds(782, 372, 2, 173);
		contentPane.add(separator_2_1);
		
		JLabel lblSpXp = new JLabel("Sắp xếp:");
		lblSpXp.setBackground(Color.WHITE);
		lblSpXp.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSpXp.setBounds(794, 372, 100, 33);
		contentPane.add(lblSpXp);
		
		JButton btnSpXpTng_1_1 = new JButton("Giảm dần");
		btnSpXpTng_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnsapgiamcmnd();
			}
		});
		btnSpXpTng_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btnSpXpTng_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSpXpTng_1_1.setBackground(new Color(255, 250, 240));
		btnSpXpTng_1_1.setBounds(904, 435, 116, 33);
		contentPane.add(btnSpXpTng_1_1);
		
		JLabel lblGiiTnh = new JLabel("+Giới tính :");
		lblGiiTnh.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGiiTnh.setBounds(816, 474, 77, 33);
		contentPane.add(lblGiiTnh);
		
		JLabel lblSpXp_1_1 = new JLabel("+CMND : ");
		lblSpXp_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSpXp_1_1.setBounds(816, 402, 70, 33);
		contentPane.add(lblSpXp_1_1);
		
		JButton btnSpXpTng_1_2 = new JButton("Nam");
		
		btnSpXpTng_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnsapnam();
			}
		});
		btnSpXpTng_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSpXpTng_1_2.setBackground(new Color(255, 250, 240));
		btnSpXpTng_1_2.setBounds(904, 474, 71, 33);
		contentPane.add(btnSpXpTng_1_2);
		
		JButton btnSpXpTng_1_3 = new JButton("Nữ");
		btnSpXpTng_1_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnsapnu();
			}
		});
		btnSpXpTng_1_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSpXpTng_1_3.setBackground(new Color(255, 250, 240));
		btnSpXpTng_1_3.setBounds(904, 507, 71, 33);
		contentPane.add(btnSpXpTng_1_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 248, 220));
		panel_2.setBounds(165, 92, 178, 39);
		contentPane.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Danh s\u00E1ch \u0111\u1ED9c gi\u1EA3");
		panel_2.add(lblNewLabel_1);
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(new Color(255, 248, 220));
		panel_2_1.setBounds(636, 92, 161, 39);
		contentPane.add(panel_2_1);
		
		JLabel lblNewLabel_6 = new JLabel("Ghi chú: 1: nam ,0: nữ");
		panel_2_1.add(lblNewLabel_6);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnTrV = new JButton("Trở về");
		btnTrV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btntrove();
			}
		});
		btnTrV.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnTrV.setBackground(new Color(255, 250, 240));
		btnTrV.setBounds(766, 577, 116, 39);
		contentPane.add(btnTrV);
		
		JButton btnNiQuy = new JButton("Nội quy");
		btnNiQuy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnnoiquy();
			}
		});
		btnNiQuy.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNiQuy.setBackground(new Color(255, 250, 240));
		btnNiQuy.setBounds(626, 577, 116, 39);
		contentPane.add(btnNiQuy);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Tra cứu và thống kê");
		lblNewLabel_2_1_1.setBounds(821, 336, 199, 32);
		contentPane.add(lblNewLabel_2_1_1);
		lblNewLabel_2_1_1.setIcon(new ImageIcon("D:\\project library\\chart-search-icon (1).png"));
		lblNewLabel_2_1_1.setBackground(new Color(238, 232, 170));
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnSpXpTng_1_1_1 = new JButton("Thống kê thể loại");
		btnSpXpTng_1_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnthongke();
				btnthongketheongay();
			}
		});
		btnSpXpTng_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btnSpXpTng_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSpXpTng_1_1_1.setBackground(new Color(255, 250, 240));
		btnSpXpTng_1_1_1.setBounds(904, 548, 181, 33);
		contentPane.add(btnSpXpTng_1_1_1);
		
		JButton btnSpXpTng_1_1_1_1 = new JButton("Thống kê theo ngày");
		btnSpXpTng_1_1_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
			}
		});
		
		btnSpXpTng_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSpXpTng_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSpXpTng_1_1_1_1.setBackground(new Color(255, 250, 240));
		btnSpXpTng_1_1_1_1.setBounds(904, 581, 181, 33);
		contentPane.add(btnSpXpTng_1_1_1_1);
		
		JButton btnSpXpTng_1_4 = new JButton("Lưu vào Excel");
		btnSpXpTng_1_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnsaveexcel();
			}
		});
		btnSpXpTng_1_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSpXpTng_1_4.setBackground(new Color(255, 250, 240));
		btnSpXpTng_1_4.setBounds(955, 92, 156, 33);
		contentPane.add(btnSpXpTng_1_4);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon("D:\\project library\\iPad-Air-2020-gradient-wallpaper-idownloadblog-Sky-Blue-by-AR7-2-1536x1536-1.png"));
		lblNewLabel_4.setBounds(0, 0, 1149, 668);
		contentPane.add(lblNewLabel_4);
		
		loadlistsach();
		
		this.setVisible(true);
	}
}
