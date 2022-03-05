package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class viewthongketheloaibdtron {

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
	
    private static JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Biểu đồ thống kê theo thể loại", dataset, true, true, true);
        return chart;
    }

    private static PieDataset createDataset() {
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
			
			 DefaultPieDataset dataset = new DefaultPieDataset();
			
			if (rs1.next()) {
				if (rs2.next()) {
					if (rs3.next()) {
						float cot2=rs2.getInt(1);
						float cot3=rs3.getInt(1);
						float cot1=rs1.getInt(1);
						DecimalFormat df = new DecimalFormat("#.##");
						
						float s=cot1+cot2+cot3;
						float pt1=(cot1/s);
						float pt2=(cot2/s);
						float pt3=(cot3/s);
						dataset.setValue("Đam mỹ :" +cot1 +" lần,chiếm "+df.format(pt1)+"%", new Double(cot1) );
						dataset.setValue("Tiểu thuyết:" +cot2 +" lần,chiếm " +df.format(pt2) +"%", new Double(cot2));
						dataset.setValue("Truyện tranh: "+cot3 +" lần,chiếm " +df.format(pt3)+"%", new Double(cot3));
					}
					
				}
				
			}
		        return dataset;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }

    public static void main(String[] args) {
        new viewthongketheloaibdtron();
    }
    
    public viewthongketheloaibdtron() {
    	JFreeChart pieChart = createChart(createDataset());
        ChartPanel chartPanel = new ChartPanel(pieChart);
        JFrame frame = new JFrame();
        frame.getContentPane().add(chartPanel);
        frame.setTitle("Biểu đồ JFreeChart trong Java Swing");
        frame.setSize(628, 378);
        frame.setLocation(700,50);
        frame.setResizable(false);
        frame.setVisible(true);
    }

}

