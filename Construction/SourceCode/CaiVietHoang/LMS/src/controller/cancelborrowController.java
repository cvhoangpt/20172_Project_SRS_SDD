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
 * This class is controller that controll cancel borrow
 * @author Cai Việt Hoàng
 *
 */
public class CancelborrowController
{
	public String requestcancelBorrow()
	{
		BorrowingInfo bi = new BorrowingInfo();
		String get = bi.updatecancelBorrow();
		return get;
	}
}
