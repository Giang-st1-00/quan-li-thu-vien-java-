package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import java.awt.Panel;
import java.awt.ScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import view.viewdangnhap;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

public class viewdangky extends JFrame  {
	private JTextField txtusername;
	private viewdangnhap dn;
	private JTextField txtgmail;
	private JPasswordField txtpassword;
	private JPasswordField txtretype;
	/**
	 * Launch the application.
	 */
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
					viewdangky frame = new viewdangky();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public void thoat() {
		dn =new viewdangnhap();
		dn.setVisible(true);
		this.dispose();
	}
	
	
	public void luu() {
		try {
			Connection conn=getConnect("DESKTOP-RT3IO2H", "QLTV");
			String sql="insert into dangnhap values (?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1,txtgmail.getText());
			statement.setString(2,txtusername.getText());
			statement.setString(3,txtpassword.getText());
			statement.setString(4,txtretype.getText());
			
			int n = statement.executeUpdate();
			
			if (txtgmail.getText().equals("") || txtpassword.getText().equals("") || txtusername.getText().equals("") || txtretype.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Không được để trống thông tin !");
			}
			else if (n!=0) {
				JOptionPane.showMessageDialog(this, "Đăng kí thành công");
			}
			else {
				JOptionPane.showMessageDialog(this, "Đăng kí thất bại");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Create the frame.
	 */
	
	public viewdangky() {
		getContentPane().setBackground(new Color(152, 251, 152));
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Đăng ký tài khoản thư viện");
		lblNewLabel_1.setBounds(209, 101, 315, 69);
		getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel = new JLabel("Username : ");
		lblNewLabel.setBounds(170, 206, 107, 25);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		txtusername = new JTextField();
		txtusername.setBounds(280, 207, 309, 25);
		getContentPane().add(txtusername);
		txtusername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtusername.setColumns(10);
		
		JLabel lblGmail = new JLabel("Gmail    :");
		lblGmail.setBounds(170, 242, 92, 25);
		getContentPane().add(lblGmail);
		lblGmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		txtgmail = new JTextField();
		txtgmail.setBounds(280, 242, 309, 25);
		getContentPane().add(txtgmail);
		txtgmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtgmail.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password  :");
		lblPassword.setBounds(170, 278, 92, 25);
		getContentPane().add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		txtpassword = new JPasswordField();
		txtpassword.setBounds(280, 278, 309, 25);
		getContentPane().add(txtpassword);
		
		JLabel lblRetype = new JLabel("Re-type    :");
		lblRetype.setBounds(170, 314, 92, 25);
		getContentPane().add(lblRetype);
		lblRetype.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		txtretype = new JPasswordField();
		txtretype.setBounds(280, 314, 309, 25);
		getContentPane().add(txtretype);
		
		JButton btndangky = new JButton("\u0110\u0103ng k\u00FD");
		btndangky.setBounds(256, 357, 125, 35);
		getContentPane().add(btndangky);
		btndangky.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				luu();
			}
		});
		btndangky.setFont(new Font("Tahoma", Font.BOLD, 16));
		btndangky.setForeground(new Color(0, 0, 0));
		
		JButton btndangnhap = new JButton("Đăng nhập");
		btndangnhap.setBounds(391, 357, 133, 35);
		getContentPane().add(btndangnhap);
		btndangnhap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				thoat();
			}
		});
		btndangnhap.setForeground(Color.BLACK);
		btndangnhap.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("D:\\project library\\pngtree-book-border-png-image_2423929 (2).jpg"));
		lblNewLabel_2.setBounds(0, -11, 718, 518);
		getContentPane().add(lblNewLabel_2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 734, 535);
	}
}