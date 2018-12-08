/*
. Full Name: Cai Việt Hoàng
. Date: 4 thg 4, 2018
. Project name: LMS
. Teacher's name: Sc.D Nguyễn Thị Thu Trang
. Class: VUWIT15
. Someone help me: 
. Description: 
*/
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbInfo.dbInclude;
import entity.Book;
import entity.BorrowingInfo;
/**
 * This class is register to borrow controller
 * @author Cai Việt Hoàng
 *
 */
public class RegborrowController
{
	Connection connection = null;
	BorrowingInfo bi = new BorrowingInfo();
	Book b = new Book();
	public String requestgetbookBorrowed()
	{
		String get = bi.getbookBorrowed();
		return get;
	}
	public String requestgetbookCatalog()
	{
		String get = b.getbookCatalog();
		return get;
	}
	
	public String requestgetbookInfo()
	{
		String get = b.getbookInfo();
		return get;
	}
	
	public String requestbookBorrowed()
	{
		String get = bi.updatebookBorrowed();
		return get;
	}
	
	public String requestupdateTable()
	{
		String get = b.updateTable();
		return get;
	}
	
	public boolean checkbookselectDuplicate(String bookid) throws SQLException 
	{
		connection = dbInclude.dbConnector();
		
		PreparedStatement pst = connection.prepareStatement(b.findbidDuplicate());
		pst.setString(1, bookid);
		
		ResultSet rs = null;
		rs = pst.executeQuery();
		if(rs.next()) return true;
		else return false;
	}
	
	public String requestfindborrowMax() 
	{
		String get = bi.findborrowMax();
		return get;
	}
	
	public String selectBook()
	{
		String get = b.getselectBook();
		return get;
	}
}
