package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.NguoiDoc;
import model.NhomNguoiDoc;

public class NhomNguoiDocService extends MySqlService {
	public int luuNhomMoi(NhomNguoiDoc nhom) {
		try {
			String sql="insert into nhomnguoidoc values(?,?)";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, nhom.getMaNhom());
			preStatement.setString(2,nhom.getTenNhom());
			return preStatement.executeUpdate();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return -1;
	}
	public ArrayList<NhomNguoiDoc>layToanBoNhom(){
		ArrayList<NhomNguoiDoc>ds=new ArrayList<NhomNguoiDoc>();
		try {
			String sql="select * from nhomnguoidoc";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			ResultSet result=preStatement.executeQuery();
			while(result.next()) {
				NhomNguoiDoc nhom=new NhomNguoiDoc();
				nhom.setMaNhom(result.getString(1));
				nhom.setTenNhom(result.getString(2));
				ds.add(nhom);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return ds;
		
	}
	public int xoaNhomNguoiDoc(NhomNguoiDoc nnd) {
		try {
			String sql="delete from nhomnguoidoc where manhom=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, nnd.getMaNhom());
			
			return preStatement.executeUpdate();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return -1;
	}
}
