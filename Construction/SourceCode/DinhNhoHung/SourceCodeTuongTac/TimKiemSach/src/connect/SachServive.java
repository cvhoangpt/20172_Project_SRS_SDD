package connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.NhaXuatBan;
import model.Sach;

public class SachServive extends MySqlService {
	public ArrayList<Sach>timSachTheoNhaXuatBan(String manxb){
		ArrayList<Sach>dsSach=new ArrayList<Sach>();
		try {
			String sql="select * from sach where manhaxuatban = ?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, manxb);
			ResultSet result=preStatement.executeQuery();
			while(result.next()) {
				Sach s=new Sach();
				s.setMaSach(result.getString(1));
				s.setTenSach(result.getString(2));
				s.setMaNhaXuatBan(result.getString(3));
				s.setSoTrang(result.getInt(4));
				dsSach.add(s);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return dsSach;
	}
	public ArrayList<Sach>timSachTheoSach(String masach) {
		ArrayList<Sach>dsSachS=new ArrayList<Sach>();
		try {
			String sql="select * from sach where masach = ?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, masach);
			ResultSet result=preStatement.executeQuery();
			while(result.next()) {
				Sach s=new Sach();
				s.setMaSach(result.getString(1));
				s.setTenSach(result.getString(2));
				s.setMaNhaXuatBan(result.getString(3));
				s.setSoTrang(result.getInt(4));
				dsSachS.add(s);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return dsSachS;
	}
}
