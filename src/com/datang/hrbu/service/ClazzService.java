package com.datang.hrbu.service;

import com.datang.hrb.vo.Clazz;

public interface ClazzService {
	public String addClass(Clazz clazz) ;
	
	public String getClazzListByProfessional(String str);
	
	public String getClazzListBySchoolName(String schoolname);
}
