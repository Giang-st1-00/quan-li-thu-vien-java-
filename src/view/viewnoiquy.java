package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class viewnoiquy extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewnoiquy frame = new viewnoiquy();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void btntrove() {
		this.dispose();
	}
	/**
	 * Create the frame.
	 */
	public viewnoiquy() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 247);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 442, 208);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("N\u1ED8I QUY");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 442, 49);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("-S\u00E1ch kh\u00F4ng \u0111\u01B0\u1EE3c m\u01B0\u1EE3n c\u00F9ng l\u00FAc 2 quy\u1EC3n gi\u1ED1ng nhau \r\n\r\n");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(10, 58, 422, 29);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("-Ch\u1EC9 \u0111\u01B0\u1EE3c m\u01B0\u1EE3n trong th\u1EDDi gian 7 ng\u00E0y\r\n");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 91, 422, 29);
		panel.add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("Tr\u1EDD v\u1EC1");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btntrove();
			}
		});
		btnNewButton.setBounds(343, 168, 89, 29);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("-M\u1ED9t ng\u01B0\u1EDDi kh\u00F4ng \u0111\u01B0\u1EE3c m\u01B0\u1EE3n qu\u00E1 3 cu\u1ED1n s\u00E1ch\r\n");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(10, 131, 422, 29);
		panel.add(lblNewLabel_1_1_1);
	}
}
