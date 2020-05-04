package com.datang.hrbu.service.lmpl;

import com.datang.hrb.dao.UserDao;
import com.datang.hrb.vo.User;
import com.datang.hrbu.service.Reset_passwordService;

public class Reset_passwordServicelmpl implements Reset_passwordService {

	@Override
	public String reset_password(User user) {

		UserDao userDao = new UserDao();
		if(userDao.getUsersByUsename(user.getUsername())==null) {
			return "username_error";
		}else {
			String pho  = userDao.getUsersByUsername1(user.getUsername());
			if (pho.equals(user.getPho())) {
				return "reset_password.jsp";
			} else {
				return "ok.jsp";
			}
		}

	}

}
