package model;

import java.io.Serializable;

public class DangNhap implements Serializable {
	private String userNamee;
	private String passWordd;
	public String getUserNamee() {
		return userNamee;
	}
	public void setUserNamee(String userNamee) {
		this.userNamee = userNamee;
	}
	public String getPassWordd() {
		return passWordd;
	}
	public void setPassWordd(String passWordd) {
		this.passWordd = passWordd;
	}
}
