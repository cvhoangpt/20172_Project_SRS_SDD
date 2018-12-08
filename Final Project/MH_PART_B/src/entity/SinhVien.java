package entity;

public class SinhVien {
	private String 	tensinhvien;
	private int 	idsinhvien;
	public SinhVien(String tensinhvien, int idsinhvien) {
		super();
		this.tensinhvien = tensinhvien;
		this.idsinhvien = idsinhvien;
	}
	public String getTensinhvien() {
		return tensinhvien;
	}
	public void setTensinhvien(String tensinhvien) {
		this.tensinhvien = tensinhvien;
	}
	public int getIdsinhvien() {
		return idsinhvien;
	}
	public void setIdsinhvien(int idsinhvien) {
		this.idsinhvien = idsinhvien;
	}
}