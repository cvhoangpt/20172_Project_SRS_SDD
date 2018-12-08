package entity;

public class Book {
	private long 	idbook;
	private	String 	bookname;
	private	long 	soluong;
	public Book(long iD, String name, long soLuong) {
		super();
		idbook = iD;
		bookname = name;
		soluong = soLuong;
	}
	public long getidbook() {
		return idbook;
	}
	public void setidbook(long iD) {
		idbook = iD;
	}
	public String getbookname() {
		return bookname;
	}
	public void setbookname(String name) {
		bookname = name;
	}
	public long getsoluong() {
		return soluong;
	}
	public void setsoluong(long soLuong) {
		soluong = soluong;
	}
	
}
