/*
. Full Name: Cai Việt Hoàng
. Date: 22 thg 4, 2018
. Project name: LMS
. Teacher's name: Sc.D Nguyễn Thị Thu Trang
. Class: VUWIT15
. Someone help me: 
. Description: 
*/
package controller;

import entity.AccInfo;

/**
 * This class show the method 
 * @author Cai Việt Hoàng
 *
 */
public class LoginController
{
	AccInfo li = new AccInfo();
	/**
	 * This funtion connect to database and use SQL query
	 * @param query is String that one code processing in SQL
	 * @return query
	 */
	public String requestgetvalidloginInfo()
	{
		/*
		final String query = "select * from loginInfo where username=? and password=?";
		return query;
		*/
		String get = li.getvalidloginInfo();
		return get;
	}

}
