package com.feicuiedu.bean;

import java.util.Date;

import com.feicuiedu.util.IdUtil;

public class Reader {
	
	private String id;
	private String name;
	private String password;
	private String sex;
	private double balance;
	private String tel;
	private String email;
	private Date createDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Reader(String name, String password, String sex, double balance, String tel, String email, Date createDate) {
		super();
		this.id = IdUtil.getUUID();
		this.name = name;
		this.password = password;
		this.sex = sex;
		this.balance = balance;
		this.tel = tel;
		this.email = email;
		this.createDate = createDate;
	}
	public Reader() {
		super();
		this.id = IdUtil.getUUID();
	}
	@Override
	public String toString() {
		return "Reader [id=" + id + ", name=" + name + ", password=" + password + ", sex=" + sex + ", balance="
				+ balance + ", tel=" + tel + ", email=" + email + ", createDate=" + createDate + "]";
	}
	
}
