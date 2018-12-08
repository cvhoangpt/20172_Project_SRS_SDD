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

import static boundary.LoginScreen.USERNAME;
import static boundary.ViewborrowedScreen.BOOKID;
public class BorrowingInfo
{
	public String getbookBorrowed()
	{
		final String query = "SELECT BookID, Title, Type, Author, Publisher, ISBN FROM bookBorrowed WHERE Username ='"+USERNAME+"'";
		return query;
	}
	
	public String findborrowMax()
	{
		final String query = "SELECT * FROM bookBorrowed WHERE username='"+USERNAME+"'";
		return query;
	}
	public String updatebookBorrowed()
	{
		final String query = "INSERT INTO bookBorrowed(Username, BookID, Title, Type, Author, Publisher, ISBN) VALUES (?,?,?,?,?,?,?)";
		return query;
	}
	
	public String updatecancelBorrow()
	{
		final String query = "DELETE FROM bookBorrowed WHERE username='"+USERNAME+"' AND bookid='"+BOOKID+"'";
		return query;
	}
	
	public String getselectBook()
	{
		final String query = "SELECT BookID FROM bookBorrowed WHERE username='"+USERNAME+"' AND bookid ='"+BOOKID+"'";
		return query;
	}
	
	public String bookQuantity()
	{
		final String query = "SELECT COUNT(*) FROM bookBorrowed WHERE username='"+USERNAME+"'";
		return query;
	}
}
