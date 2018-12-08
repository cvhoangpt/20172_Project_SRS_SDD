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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbInfo.dbInclude;
import entity.AccInfo;

/**
 * This class show the method in register account if user don't have account.
 * @author Cai Việt Hoàng
 *
 */
public class RegaccController
{
	Connection connection = null;
	AccInfo ai = new AccInfo();
	public String requestinsertInfo()
	{
		String get = ai.insertInfo();
		return get;
	}
	
	public String requestfindInfo()
	{
		
		String get = ai.findInfo();
		return get;
	}
	
	/**
	 * This funtion check duplicate of username
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public boolean checkusernameDuplicate(String username) throws SQLException 
	{
		connection = dbInclude.dbConnector();
		
		PreparedStatement pst = connection.prepareStatement(ai.findunDuplicate());
		pst.setString(1, username);
		
		ResultSet rs = null;
		rs = pst.executeQuery();
		if(rs.next()) return true;
		else return false;
	}
	/**
	 * This funtion check duplicate of studentid
	 * @param studentid
	 * @return
	 * @throws SQLException
	 */
	public boolean checkstudentidDuplicate(String studentid) throws SQLException 
	{
		PreparedStatement pst = connection.prepareStatement(ai.findsidDuplicate());
		
		pst.setString(1, studentid);
		ResultSet rs = null;
		rs = pst.executeQuery();
		if(rs.next()) return true;
		else return false;
	}
}
