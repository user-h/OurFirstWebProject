package com.datang.hrbu.service;

import java.util.List;

import com.datang.hrb.vo.Clazz;
import com.datang.hrb.vo.Student;

public interface AddStuService {
	public String AddStu(Student student);

	public int saveStu(Student student);

	public int saveCla(Clazz clazz);

}