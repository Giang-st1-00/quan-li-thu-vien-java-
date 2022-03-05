package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class option extends JFrame {

	private JPanel contentPane;
	private JTextField txtChnPhnBn;
	private QLTVview dg;

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
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					option frame = new option();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void btnsach() {
		viewsach vs=new viewsach();
		vs.setVisible(true);
		
	}
	
	public void btnthoat() {
		this.dispose();
	}
	
	public void btndocgia() {
		Connection conn=getConnect("DESKTOP-RT3IO2H", "QLTV");
		QLTVview tv = new QLTVview();
		tv.initTable();
		tv.loadAllEmployees();
		tv.setVisible(true);
		this.dispose();
	}
	
	public option() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 498, 241);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 239, 213));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtChnPhnBn = new JTextField();
		txtChnPhnBn.setBackground(new Color(245, 222, 179));
		txtChnPhnBn.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtChnPhnBn.setHorizontalAlignment(SwingConstants.CENTER);
		txtChnPhnBn.setText("Ch\u1ECDn ph\u1EA7n b\u1EA1n mu\u1ED1n qu\u1EA3n l\u00FD");
		txtChnPhnBn.setBounds(47, 52, 370, 42);
		contentPane.add(txtChnPhnBn);
		txtChnPhnBn.setColumns(10);
		
		JButton btnNewButton = new JButton("  \u0110\u1ED9c gi\u1EA3");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btndocgia();
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setIcon(new ImageIcon("D:\\project library\\\u0111\u1ED9c gi\u1EA3.png"));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(47, 93, 189, 62);
		contentPane.add(btnNewButton);
		
		JButton btnSch = new JButton("S\u00E1ch");
		btnSch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnsach();
			}
		});
		btnSch.setIcon(new ImageIcon("D:\\project library\\62863-books-icon.png"));
		btnSch.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSch.setBounds(235, 93, 182, 62);
		contentPane.add(btnSch);
		
		JButton btnNewButton_1 = new JButton("Tho\u00E1t");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnthoat();
			}
		});
		btnNewButton_1.setBounds(328, 166, 89, 23);
		contentPane.add(btnNewButton_1);
	}

}
