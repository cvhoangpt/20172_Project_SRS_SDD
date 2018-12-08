
package entity;

public class DanhSachMuon {
	private int idmuon;
	private String bookname;
	private long soluong;
	public DanhSachMuon(int idmuon, String bookname, long soluong) {
		super();
		idmuon = idmuon;
		this.bookname = bookname;
		soluong = soluong;
	}
	public int getidmuon() {
		return idmuon;
	}
	public void setidmuon(int idmuon) {
		idmuon = idmuon;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public long getsoluong() {
		return soluong;
	}
	public void setsoluong(long soluong) {
		soluong = soluong;
	}
	

}
