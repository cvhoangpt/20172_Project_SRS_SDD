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

public class AccInfo
{
	public String getvalidloginInfo()
	{
		final String query = "SELECT * FROM loginInfo WHERE username=? AND password=?";
		return query;
	}
	
	public String insertInfo()
	{
		final String query = "INSERT INTO loginInfo (StudentID, Username, Password, FullName, Gender, Email, Contact, Institute, Course, NumofRemaining) values (?,?,?,?,?,?,?,?,?,?)";
		return query;
	}
	
	public String findInfo()
	{
		final String query = "SELECT * FROM loginInfo";
		return query;
	}
	
	public String findunDuplicate()
	{
		final String query = "SELECT * FROM loginInfo WHERE username=?";
		return query;
	}
	
	public String findsidDuplicate()
	{
		final String query = "SELECT * FROM loginInfo WHERE studentid=?";
		return query;
	}
}
