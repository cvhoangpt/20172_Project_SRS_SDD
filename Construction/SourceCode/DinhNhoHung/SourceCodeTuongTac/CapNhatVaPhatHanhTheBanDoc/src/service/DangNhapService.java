package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.DangNhap;

public class DangNhapService extends MySqlService {
	public DangNhap login(String user, String pass) {
		DangNhap account=null;
		try {
			String sql="select * from dangnhap where usernamee=? and passwordd=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, user);
			preStatement.setString(2, pass);
			ResultSet result=preStatement.executeQuery();
			if(result.next()) {
				account=new DangNhap();
				account.setUserNamee(result.getString(1));
				account.setPassWordd(result.getString(2));
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return account;
	}
}
