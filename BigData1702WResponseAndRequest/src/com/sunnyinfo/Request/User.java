package com.sunnyinfo.Request;

public class User {
	private String user;
	private String pwd;
	private String sex;
	private String[] hobby;
	private String area;
	public User() {

	}
	public User(String user, String pwd, String sex, String[] hobby, String area) {
		super();
		this.user = user;
		this.pwd = pwd;
		this.sex = sex;
		this.hobby = hobby;
		this.area = area;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String[] getHobby() {
		return hobby;
	}
	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
}
