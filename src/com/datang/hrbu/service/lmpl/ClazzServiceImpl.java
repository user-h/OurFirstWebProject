package com.datang.hrbu.service.lmpl;

import com.datang.hrb.dao.ClazzDao;
import com.datang.hrb.vo.Clazz;
import com.datang.hrbu.service.ClazzService;

public class ClazzServiceImpl implements ClazzService{

	@Override
	public String addClass(Clazz clazz) {
		// TODO Auto-generated method stub
		ClazzDao clazzDao = new ClazzDao();
		if(clazzDao.add(clazz)==true) {
			return "add_success";
		}else {
			return "add_fail";
		}
	}

	@Override
	public String getClazzListByProfessional(String str) {
		// TODO Auto-generated method stub
		ClazzDao clazzdao = new ClazzDao();
		if(clazzdao.getClazzListByProfessional(str).size()>0) {
			return "select_success";
		}else {
			return "select_fail";
		}	
	}

	@Override
	public String getClazzListBySchoolName(String schoolname) {
		// TODO Auto-generated method stub
		ClazzDao clazzdao = new ClazzDao();
		if(clazzdao.getClazzListBySchool(schoolname).size()>0) {
			return "select_success";
		}else {
			return "select_fail";
		}	
	}



}
