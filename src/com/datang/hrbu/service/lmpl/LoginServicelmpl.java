package com.datang.hrbu.service.lmpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.datang.hrb.dao.ConnectionUtil;
import com.datang.hrb.dao.UserDao;
import com.datang.hrb.vo.Student;
import com.datang.hrb.vo.User;
import com.datang.hrbu.service.LoginService;


public class LoginServicelmpl   implements LoginService{

	@Override
	public String login(User user) {
		UserDao userDao =new UserDao();
		if(userDao.getUsersByUsename(user.getUsername())==null) {
			return "username_error";
		}else {
			String password=userDao.getUsersByUsername(user.getUsername());
			//System.out.println(userDao.getUsersByUsername(user.getUsername()));
			if(password.equals(user.getPassword())) {
				return "student_list.jsp";
			}else {
				return "error.jsp";
			}
		}
	}

	@Override
	public List<Student> getUserList() {
		// TODO Auto-generated method stub
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = null;
		List<Student> userList = new ArrayList<Student>();
		try {
			ps = conn.prepareStatement("select * from student");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Student user = new Student();
				user.setSno(rs.getString(1));
				user.setName(rs.getString(2));
				user.setSex(rs.getString(3));
				user.setBname(rs.getString(4));
				user.setProfessional(rs.getString(5));
				
				user.setEmail(rs.getString(6));
				user.setPho(rs.getString(7));
				//user.setSbirh(rs.getDate(8));
				userList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("666");
		}

		return userList;
	}



}
