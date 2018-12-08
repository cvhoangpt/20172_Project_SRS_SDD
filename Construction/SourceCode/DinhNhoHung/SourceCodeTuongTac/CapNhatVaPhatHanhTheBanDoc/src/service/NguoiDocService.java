package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.NguoiDoc;
import model.NhomNguoiDoc;

public class NguoiDocService extends MySqlService {
	public int luuNguoiMoi(NguoiDoc nguoi) {
		try {
			String sql="insert into nguoidoc values(?,?,?,?)";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1,nguoi.getMa() );
			preStatement.setString(2,nguoi.getTen());
			preStatement.setString(3,nguoi.getMaNhom());
			preStatement.setString(4,nguoi.getDiaChi());
			return preStatement.executeUpdate();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return -1;
	}
	public int xoaNguoiDoc(NguoiDoc nd) {
		try {
			String sql="delete from nguoidoc where ma=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, nd.getMa());
			
			return preStatement.executeUpdate();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return -1;
	}
	public ArrayList<NguoiDoc>layToanBoNguoiDocTheoMa(String manhom){
		ArrayList<NguoiDoc>ds=new ArrayList<NguoiDoc>();
		try {
			String sql="select * from nguoidoc where manhom=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, manhom);
			ResultSet result=preStatement.executeQuery();
			while (result.next()) {
				NguoiDoc nd= new NguoiDoc();
				nd.setMa(result.getString(1));
				nd.setTen(result.getString(2));
				nd.setMaNhom(result.getString(3));
				nd.setDiaChi(result.getString(4));
				ds.add(nd);
				
				
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return ds;
	}
}
