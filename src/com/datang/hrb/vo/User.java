package com.datang.hrb.vo;

import java.util.Date;

public class User {
private String username;
private String password;
private int age;
private Date ts;
private String pho;
private String newpassword;
public String getNewpassword() {
	return newpassword;
}
public void setNewpassword(String newpassword) {
	this.newpassword = newpassword;
}
public String getPho() {
	return pho;
}
public void setPho(String pho) {
	this.pho = pho;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public Date getTs() {
	return ts;
}
public void setTs(Date ts) {
	this.ts = ts;
}

}
