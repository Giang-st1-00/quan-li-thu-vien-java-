package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.zip.CheckedInputStream;
import javax.swing.ImageIcon;

public class viewdangnhap extends JFrame {
	private JPanel contentPane;
	private JTextField txtusername;
	private JButton btnSignIn;
	private JPasswordField txtpassword;
	private JRadioButton checkbox;
	private viewdangky dk;
	private ResultSet rs;
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
					viewdangnhap frame = new viewdangnhap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void sign_in() {
		try {
				Connection conn=getConnect("DESKTOP-RT3IO2H", "QLTV");
				
				String sql="select * from dangnhap where tendangnhap=? and matkhau=?";
				PreparedStatement statement = conn.prepareStatement(sql);
				
				
				statement.setString(1,txtusername.getText());
				statement.setString(2, txtpassword.getText());
				ResultSet rs= statement.executeQuery();
				if (txtusername.getText().equals("") || txtpassword.getText().equals("")) {
					JOptionPane.showMessageDialog(this,"Chưa nhập tên đăng nhập hoặc mật khẩu.");
				}
				else if (rs.next()){
					option o = new option();
					o.setVisible(true);
					
					this.dispose();
					JOptionPane.showMessageDialog(this, "Đăng nhập thành công.");
				}
				else {
					JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu.");
				}
			
		} catch (Exception e) {
			
		}
	}
	
	public void btndk() {
		dk = new viewdangky();
		dk.setVisible(true);
		this.dispose();
	}
	
	public void btnreset() {
		txtusername.setText("");
		txtpassword.setText("");
		checkbox.setSelected(true);
	}
	/**
	 * Create the frame.
	 */
	public viewdangnhap() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 540);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(152, 251, 152));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên đăng nhập :");
		lblNewLabel.setBounds(102, 123, 154, 35);
		contentPane.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("D:\\project library\\Users-Name-icon (1).png"));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblPassword = new JLabel("Mật khẩu     :");
		lblPassword.setBounds(122, 169, 135, 35);
		contentPane.add(lblPassword);
		lblPassword.setIcon(new ImageIcon("D:\\project library\\Security-Password-2-icon.png"));
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		btnSignIn = new JButton("Đăng nhập");
		btnSignIn.setBounds(128, 242, 128, 35);
		contentPane.add(btnSignIn);
		btnSignIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sign_in();
			}
		});
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
			});
		btnSignIn.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton btnSignUp = new JButton("Đăng ký");
		btnSignUp.setBounds(282, 242, 114, 35);
		contentPane.add(btnSignUp);
		btnSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btndk();
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton btnreset = new JButton("Làm mới");
		btnreset.setBounds(416, 242, 109, 35);
		contentPane.add(btnreset);
		btnreset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnreset();
			}
		});
		btnreset.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblNewLabel_1 = new JLabel("\u0110\u0102NG NH\u1EACP");
		lblNewLabel_1.setBounds(180, 77, 307, 35);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		checkbox = new JRadioButton("L\u01B0u m\u1EADt kh\u1EA9u");
		checkbox.setBounds(255, 213, 109, 23);
		contentPane.add(checkbox);
		
		txtpassword = new JPasswordField();
		txtpassword.setBounds(255, 169, 286, 35);
		contentPane.add(txtpassword);
		
		txtusername = new JTextField();
		txtusername.setBounds(255, 124, 286, 35);
		contentPane.add(txtusername);
		txtusername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtusername.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("D:\\project library\\pngtree-cute-book-border-decoration-png-image_4506904 (1).jpg"));
		lblNewLabel_2.setBounds(0, 0, 743, 501);
		contentPane.add(lblNewLabel_2);
	}
}

