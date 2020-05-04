package com.datang.hrbu.service.lmpl;

import java.util.List;

import com.datang.hrb.dao.ClazzDao;
import com.datang.hrb.dao.StudentDao;
import com.datang.hrbu.service.AddStuService;
import com.datang.hrb.vo.Clazz;
import com.datang.hrb.vo.Student;

public class AddStuServiceImpl implements AddStuService {

	@Override
	public String AddStu(Student student) {
		// TODO Auto-generated method stub
		StudentDao studentDao = new StudentDao();
		if (studentDao.addStu(student) == true) {
			return "add_success";
		} else {
			return "add_fail";
		}
	}


	@Override
	public int saveStu(Student user) {
		
		StudentDao studentDao = new StudentDao();
		return studentDao.saveStu(user);
	}


	@Override
	public int saveCla(Clazz clazz) {
		ClazzDao clazzDao = new ClazzDao();
		return clazzDao.saveCla(clazz);
	}

}
