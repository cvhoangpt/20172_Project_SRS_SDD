/*
. Full Name: Cai Việt Hoàng
. Date: 11 thg 4, 2018
. Project name: LMS
. Teacher's name: Sc.D Nguyễn Thị Thu Trang
. Class: VUWIT15
. Someone help me: 
. Description: 
*/
package dbInfo;

import java.io.File;
import java.sql.*;
import javax.swing.*;

/**
 * This class allow system get data in database
 * 
 * @author Cai Việt Hoàng
 *
 */
public class dbInclude
{
	/**
	 * This funtion connect to database in directory
	 * 
	 * @param url
	 *            is String that text include database directory in hard disk
	 * @param conn
	 *            is object in Connection
	 * @return conn
	 */
	public static Connection dbConnector()
	{
		final String url = "jdbc:sqlite:E://HUST-BKHN/Subjects/Phan tich yeu cau phan mem, thiet ke xay dung phan mem/SourceTree/Construction/SourceCode/CaiVietHoang/LMS/DataBase/DataBase.db"; // Directory to database
		//final String url = "jdbc:sqlite:DataBase"+File.separator+"DataBase.db"; // Directory to database
		//private static String dbPath = "jdbc:sqlite:db"+File.separator+"CodeExample.sqlite";
		Connection conn = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(url);
			// JOptionPane.showMessageDialog(null, "Connection Successful");
			return conn;
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
