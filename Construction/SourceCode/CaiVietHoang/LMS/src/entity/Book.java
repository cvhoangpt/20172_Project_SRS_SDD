/*
. Full Name: Cai Việt Hoàng
. Date: 24 thg 4, 2018
. Project name: LMS
. Teacher's name: Sc.D Nguyễn Thị Thu Trang
. Class: VUWIT15
. Someone help me: 
. Description: 
*/
package entity;

import java.sql.Connection;

import dbInfo.dbInclude;
import static boundary.LoginScreen.USERNAME;
import static boundary.RegborrowScreen.BOOKID;
/**
 * This class to connect to database
 * @author Cai Việt Hoàng
 *
 */
public class Book
{
	Connection connection = null;
	public void getConnection()
	{
		connection = dbInclude.dbConnector();
	}
	
	public String getbookCatalog()
	{
		final String query = "SELECT * FROM BookCatalog";
		return query;
	}
	
	public String getbookInfo()
	{
		final String query = "SELECT * FROM BookCatalog where BookID=?";
		return query;
	}
		
	public String updateTable()
	{
		final String query = "SELECT BookID, Title, Type, Author, Publisher, ISBN FROM bookBorrowed WHERE Username ='"+USERNAME+"'";
		return query;
	}
	
	public String findbidDuplicate()
	{
		final String query = "SELECT * FROM bookBorrowed WHERE bookid=? AND USERNAME='"+USERNAME+"'";
		return query;
	}
	
	public String getselectBook()
	{
		final String query = "SELECT BookID, Title, Type, Author, Publisher, ISBN FROM bookCatalog WHERE BookID='"+BOOKID+"'";
		return query;
	}
}