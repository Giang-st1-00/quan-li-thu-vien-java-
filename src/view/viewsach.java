package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class viewsach extends JFrame {

	private JPanel contentPane;
	private JTextField txttentruyen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewsach frame = new viewsach();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void btntrove() {
		
	}
	/**
	 * Create the frame.
	 */
	public viewsach() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel txthinh = new JLabel("");
		txthinh.setHorizontalAlignment(SwingConstants.CENTER);
		txthinh.setIcon(new ImageIcon("D:\\project library\\images\\t\u00E1t d\u00E3.jpg"));
		txthinh.setBounds(169, 11, 333, 307);
		contentPane.add(txthinh);
		
		txttentruyen = new JTextField();
		txttentruyen.setBounds(179, 329, 396, 33);
		contentPane.add(txttentruyen);
		txttentruyen.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("T\u00EAn Truy\u1EC7n");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(44, 329, 125, 33);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("T\u00ECm ki\u1EBFm");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String taptruyen ="D:\\project library\\images\\"+txttentruyen.getText()+".jpg";
				ImageIcon icon = new ImageIcon(taptruyen);
				txthinh.setIcon(icon);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(256, 386, 170, 33);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("TR\u1EDE V\u1EC0");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btntrove();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(578, 408, 115, 39);
		contentPane.add(btnNewButton_1);
	}
}
