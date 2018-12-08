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

import entity.BorrowingInfo;

/**
 * This class is controller that controll view borrow windows
 * @author Cai Việt Hoàng
 *
 */
public class ViewborrowController
{
	BorrowingInfo bi = new BorrowingInfo();
	public String requestgetbookBorrowed()
	{
		String get = bi.getbookBorrowed();
		return get;
	}
	
	public String selectBook()
	{
		String get = bi.getselectBook();
		return get;
	}
	
	public String getbookQuantity()
	{
		String get = bi.bookQuantity();
		return get;
	}
}
