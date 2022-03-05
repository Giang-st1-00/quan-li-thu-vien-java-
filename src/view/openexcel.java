package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.QLTVmodel;
import model.docgia;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import view.QLTVview;
public class openexcel {
	
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
		new openexcel();
	}
	
    public openexcel() {
    	Connection conn=getConnect("DESKTOP-RT3IO2H", "QLTV");
        try {
        	
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Độc giả");
            

            XSSFRow row = null;
            Cell cell = null;

            row = spreadsheet.createRow((short) 2);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("DANH SÁCH ĐỘC GIẢ");

            row = spreadsheet.createRow((short) 3);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Thông tin sách");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("CMND");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Họ và tên");
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Ngày sinh");
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Giới tính");
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Ngày mượn");
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Ngày trả");

            Statement statement1 = conn.createStatement();
			final String sql1 = "SELECT count(*) FROM DOCGIA";
			ResultSet soluong= statement1.executeQuery(sql1);
            
            Statement statement = conn.createStatement();
			final String sql = "SELECT * FROM DOCGIA";
			ResultSet rs= statement.executeQuery(sql);
			
			int j=0;
				if (soluong.next()) {
					int n=soluong.getInt(1);
					while (rs.next()) {
						row = spreadsheet.createRow((short) 4 + j);
		                row.setHeight((short) 400);
		                
		                row.createCell(0).setCellValue(j + 1);
		   
		                row.createCell(1).setCellValue(rs.getString("tensach"));
		                row.createCell(2).setCellValue(rs.getString("CMND"));
		                row.createCell(3).setCellValue(rs.getString("HOVATEN"));
		                row.createCell(4).setCellValue(rs.getString("NGAYSINH"));
		                row.createCell(5).setCellValue(rs.getBoolean("GIOITINH"));
		                row.createCell(6).setCellValue(rs.getString("NGAYMUON"));
		                row.createCell(7).setCellValue(rs.getString("NGAYTRA"));
		                if (j<n) {
		                	j++;
		                }
		                else {
		                	break;
		                }
					}
					
				}
				
            FileOutputStream out = new FileOutputStream(new File("D:/docgia.xlsx"));
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

