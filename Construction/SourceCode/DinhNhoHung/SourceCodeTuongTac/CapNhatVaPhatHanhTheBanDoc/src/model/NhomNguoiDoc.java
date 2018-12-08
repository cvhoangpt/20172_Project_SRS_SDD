package model;

public class NhomNguoiDoc {
	private String maNhom;
	private String tenNhom;
	public String getMaNhom() {
		return maNhom;
	}
	public void setMaNhom(String maNhom) {
		this.maNhom = maNhom;
	}
	public String getTenNhom() {
		return tenNhom;
	}
	public void setTenNhom(String tenNhom) {
		this.tenNhom = tenNhom;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.tenNhom;
	}
}
