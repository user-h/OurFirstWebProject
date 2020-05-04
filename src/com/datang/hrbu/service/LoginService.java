package com.datang.hrbu.service;

import java.util.List;

import com.datang.hrb.vo.*;

public interface LoginService {
	public String login(User user);

	public List<Student> getUserList();
	
}
