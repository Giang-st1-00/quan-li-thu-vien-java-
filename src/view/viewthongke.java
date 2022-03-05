package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class viewthongke {
	
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
	
	public viewthongke() {
        ChartPanel chartPanel = new ChartPanel(createChart());
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        JFrame frame = new JFrame();
        frame.getContentPane().add(chartPanel);
        frame.setTitle("Biểu đồ JFreeChart trong Java Swing");
        frame.setSize(706, 390);
        frame.setLocation(50,50);
        frame.setResizable(false);
        frame.setVisible(true);
    }
	
    public static JFreeChart createChart() {
        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ THỂ LOẠI SÁCH THU HÚT ĐỘC GIẢ",
                "Thể loại", "Số lần mượn",
                createDataset(), PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }

    
    public static CategoryDataset createDataset() {
    	Connection conn=getConnect("DESKTOP-RT3IO2H", "QLTV");
		try {
			final String sql1 = "select count(*) from docgia\r\n"
	    			+ "where tensach=N'Trạch mộc nhi tê'\r\n"
	    			+ "or tensach=N'Tát dã'\r\n"
	    			+ "or tensach=N'Thiên quan tứ phúc'\r\n"
	    			+ "or tensach=N'Ngụy trang học tra'\r\n"
	    			+ "or tensach=N'Đề này khó quá rồi'";
			PreparedStatement statement1 = conn.prepareStatement(sql1);
			ResultSet rs1= statement1.executeQuery();
			
			final String sql2 = "select count(*) from docgia\r\n"
					+ "where tensach=N'Tam quốc diễn nghĩa'\r\n"
					+ "or tensach=N'Nhà giả kim'\r\n"
					+ "or tensach=N'Những người khốn khổ'\r\n"
					+ "or tensach=N'Chiến tranh và hòa bình'\r\n"
					+ "or tensach=N'Thần thoại Hy Lạp'";
			PreparedStatement statement2 = conn.prepareStatement(sql2);
			ResultSet rs2= statement2.executeQuery();
			
			final String sql3 = "select count(*) from docgia\r\n"
					+ "where tensach=N'One-piece'\r\n"
					+ "or tensach=N'Conan'\r\n"
					+ "or tensach=N'Thần đồng đất việt'\r\n"
					+ "or tensach=N'Doraemon'\r\n"
					+ "or tensach=N'Shin-cậu bé bút chì'";
			PreparedStatement statement3 = conn.prepareStatement(sql3);
			ResultSet rs3= statement3.executeQuery();
			
			final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			if (rs1.next()) {
				int cot1=rs1.getInt(1);
				dataset.addValue(cot1, "Thể loại", "Đam mỹ");
			}
			
			if (rs2.next()) {
				int cot1=rs2.getInt(1);
				dataset.addValue(cot1, "Thể loại", "Tiểu thuyết");
			}
			
			if (rs3.next()) {
				int cot1=rs3.getInt(1);
				dataset.addValue(cot1, "Thể loại", "Truyện tranh");
			}
	        return dataset;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    
    public static void main(String[] args) {
		new viewthongke();
	}

	
    
    

}
