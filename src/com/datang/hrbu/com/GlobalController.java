package com.datang.hrbu.com;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.datang.hrb.dao.ClazzDao;
import com.datang.hrb.dao.ConnectionUtil;
import com.datang.hrb.dao.StudentDao;
import com.datang.hrb.dao.UserDao;
import com.datang.hrb.util.ImgCodeUtil;
import com.datang.hrb.util.MD5Util;
import com.datang.hrb.vo.*;
import com.datang.hrbu.service.AddStuService;
import com.datang.hrbu.service.LoginService;
import com.datang.hrbu.service.Reset_passwordService;
import com.datang.hrbu.service.lmpl.AddStuServiceImpl;
import com.datang.hrbu.service.lmpl.ClazzServiceImpl;
import com.datang.hrbu.service.lmpl.LoginServicelmpl;
import com.datang.hrbu.service.lmpl.Reset_passwordServicelmpl;

public class GlobalController extends HttpServlet {
	// private Map<String, String> userMap = new HashMap<String, String>();
	String code1 = null;
	private Object clazzService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// System.out.println("1");
		String uri = req.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1, uri.indexOf("."));
		// resp.sendRedirect("ok.jsp");
		if (action.equals("getImgCode")) {
			// 调用工具类生成的验证码和验证码图片
			Map<String, Object> codeMap = ImgCodeUtil.generateCodeAndPic();
			// code-->1234
			// codePic-->picbyte

			// 将四位数字的验证码保存到Session中。
			HttpSession session = req.getSession();
			session.setAttribute("code", codeMap.get("code").toString());
			code1 = codeMap.get("code").toString();
			// 禁止图像缓存。
			resp.setHeader("Pragma", "no-cache");
			resp.setHeader("Cache-Control", "no-cache");
			resp.setDateHeader("Expires", -1);

			resp.setContentType("image/jpeg");

			// 将图像输出到Servlet输出流中。
			ServletOutputStream sos;
			try {
				sos = resp.getOutputStream();
				ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
				sos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			doPost(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String pho = req.getParameter("pho");
		String newpassword = req.getParameter("newpassword");
		String cno = req.getParameter("cno");
		String sno = req.getParameter("sno");
		String uri = req.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1, uri.indexOf("."));

		HttpSession session = req.getSession();
		//System.out.println(action);
		if (action.equals("register")) {
			if(!username.equals("") && !password.equals("") && !pho.equals("")) {
				Connection conn = ConnectionUtil.getConnection();
				PreparedStatement ps = null;
				try {
					ps = conn.prepareStatement("insert into user(username,password,pho) values(?,?,?)");
					ps.setString(1, username);
					ps.setString(2, MD5Util.getMD5(password));
					ps.setString(3, pho);
					int i = ps.executeUpdate();
					if (i == 1) {
						resp.sendRedirect("login.jsp");
					} else {
						
						session.setAttribute("message", "注册输入错误");
						resp.sendRedirect("error.jsp");
					}
				} catch (SQLException e) {
					session.setAttribute("message", "注册异常错误");
					resp.sendRedirect("error.jsp");
					e.printStackTrace();
				} finally {
					if (ps != null) {
						try {
							ps.close();
						} catch (SQLException e) {

							e.printStackTrace();
						}
					}
				}
			}else {
				session.setAttribute("message", "输入不能为空");
				resp.sendRedirect("error.jsp");
			}
			
		} else if (action.equals("selbyname")) {
			System.out.println(action);
			// 用User对用户名密码封装
			Student student = new Student();// sno,sname,ssex,bname,professional,email,pho,sbirh
			student.setName(req.getParameter("name"));
			// System.out.println("name=" + req.getParameter("name") + " +" +
			// student.getSname());

			StudentDao studentDao = new StudentDao();
			List<Student> userList = studentDao.getStudentListByName(student);

			if (userList.size() > 0) {
				session.setAttribute("userList", userList);
				resp.sendRedirect("student_list.jsp");
			} else {
				// System.out.println("查出等于0？？");
				session.setAttribute("message", "您搜索的用户不存在");
				resp.sendRedirect("error.jsp");
			}

		} else if (action.equals("add_stu")) {

			// 用User对用户名密码封装
			Student student = new Student();// sno,sname,ssex,bname,professional,email,pho,sbirh
			student.setSno(req.getParameter("sno"));
			student.setName(req.getParameter("sname"));
			student.setSex(req.getParameter("ssex"));
			student.setBname(req.getParameter("bname"));
			student.setProfessional(req.getParameter("professional"));
			student.setEmail(req.getParameter("email"));
			student.setPho(req.getParameter("pho"));
			/*
			 * SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss "); try {
			 * student.setSbirh(sdf.parse(req.getParameter("sbirh").trim())); } catch
			 * (ParseException e) { // TODO Auto-generated catch block e.printStackTrace();
			 * }
			 */

			AddStuServiceImpl addStuService = new AddStuServiceImpl();
			if (addStuService.AddStu(student) == "add_success") {
				session.setAttribute("username", username);

				StudentDao studentDao = new StudentDao();
				List<Student> userList = studentDao.getStudentList();
				session.setAttribute("userList", userList);
				resp.sendRedirect("student_list.jsp");
			} else {
				session.setAttribute("message", "输入错误");
				resp.sendRedirect("error.jsp");
			}

		} else if (action.equals("login")) {
			User user = new User();
			user.setUsername(username);
			user.setPassword(MD5Util.getMD5(password));
			LoginService loginService = new LoginServicelmpl();

			if (loginService.login(user) == "student_list.jsp") {
				// System.out.println(code1);
				// System.out.println(req.getParameter("code"));
				if (code1.equalsIgnoreCase(req.getParameter("code"))) {
					session.setAttribute("username", username);
					List<Student> userList = loginService.getUserList();
					session.setAttribute("userList", userList);

					resp.sendRedirect("student_list.jsp");
				} else {
					session.setAttribute("message", "验证码错误");
					resp.sendRedirect("error.jsp");
				}
			} else if (loginService.login(user) == "username_error") {
				session.setAttribute("message", "没有该用户");
				resp.sendRedirect("error.jsp");
			} else {
				session.setAttribute("message", "密码错误");
				resp.sendRedirect("error.jsp");
			}

		} else if (action.equals("delete")) {

			Connection conn = ConnectionUtil.getConnection();
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement("delete from student where sno =? ");

				ps.setString(1, sno);

				int i = ps.executeUpdate();

				if (i == 1) {
					LoginService loginService = new LoginServicelmpl();
					List<Student> userList = loginService.getUserList();
					session.setAttribute("username", username);
					session.setAttribute("userList", userList);
					resp.sendRedirect("student_list.jsp");
				} else {
					session.setAttribute("message", "删除失败");
					resp.sendRedirect("error.jsp");
				}
			} catch (SQLException e) {
				session.setAttribute("message", "删除异常");
				resp.sendRedirect("error.jsp");
				e.printStackTrace();
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else if (action.equals("delete1")) {

			Connection conn = ConnectionUtil.getConnection();
			PreparedStatement ps = null;

			try {
				ps = conn.prepareStatement("delete from class where cno =? ");

				ps.setString(1, cno);

				int i = ps.executeUpdate();

				if (i == 1) {
					// ClazzServiceImpl clazzService = new ClazzServiceImpl();
					ClazzDao clazzDao = new ClazzDao();
					List<Clazz> clazzList = clazzDao.getClazzList();
					session.setAttribute("username", username);
					session.setAttribute("clazzList", clazzList);
					resp.sendRedirect("class_list.jsp");
				} else {

				}
			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else if (action.equals("select")) {

			Connection conn = ConnectionUtil.getConnection();
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement("select * from student where sno like '%" + sno + "%' ");
				/*
				 * ps.setString(1, sno);
				 */
				ResultSet rs = ps.executeQuery();

				List<Student> userList = new ArrayList<Student>();
				while (rs.next()) {
					Student user = new Student();
					user.setSno(rs.getString(1));
					user.setName(rs.getString(2));
					user.setSex(rs.getString(3));
					user.setBname(rs.getString(4));
					user.setProfessional(rs.getString(5));
					user.setEmail(rs.getString(6));
					user.setPho(rs.getString(7));
					// user.setSbirh(rs.getDate(8));
					userList.add(user);
				}

				if (userList.size() > 0) {
					session.setAttribute("username", username);
					session.setAttribute("userList", userList);
					resp.sendRedirect("student_list.jsp");
				} else {
					session.setAttribute("message", "输入错误或查无此人");
					resp.sendRedirect("error.jsp");
				}
			} catch (SQLException e) {
				session.setAttribute("message", "异常");
				resp.sendRedirect("error.jsp");
				e.printStackTrace();
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else if (action.equals("update")) {

			Connection conn = ConnectionUtil.getConnection();
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement("select * from student where sno=? ");
				ps.setString(1, sno);

				ResultSet rs = ps.executeQuery();

				Student user = null;
				while (rs.next()) {
					user = new Student();
					user.setSno(rs.getString(1));
					user.setName(rs.getString(2));
					user.setSex(rs.getString(3));
					user.setBname(rs.getString(4));
					user.setProfessional(rs.getString(5));
					user.setEmail(rs.getString(6));
					user.setPho(rs.getString(7));
					// user.setSbirh(rs.getDate(8));

				}
				session.setAttribute("username", username);
				session.setAttribute("user", user);
				resp.sendRedirect("update.jsp");

			} catch (SQLException e) {
			
			session.setAttribute("message", "输入错误");
				resp.sendRedirect("error.jsp");
				e.printStackTrace();
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else if (action.equals("update1")) {

			Connection conn = ConnectionUtil.getConnection();
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement("select * from class where cno=? ");
				ps.setString(1, cno);

				ResultSet rs = ps.executeQuery();

				Clazz clazz = null;
				while (rs.next()) {
					clazz = new Clazz();
					clazz.setCno(rs.getString(1));
					clazz.setLname(rs.getString(2));
					clazz.setSchool(rs.getString(3));
					clazz.setProfessional(rs.getString(4));
					clazz.setNum(rs.getInt(5));

				}
				session.setAttribute("username", username);
				session.setAttribute("clazz", clazz);
				resp.sendRedirect("update1.jsp");

			} catch (SQLException e) {

				session.setAttribute("message", "输入错误");
				resp.sendRedirect("error.jsp");
				e.printStackTrace();
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else if (action.equals("saveStu")) {
			Student user = new Student();
			user.setOriginCode(req.getParameter("originSno"));
			user.setSno(req.getParameter("sno"));
			user.setName(req.getParameter("name"));
			user.setSex(req.getParameter("sex"));
			user.setBname(req.getParameter("bname"));
			user.setProfessional(req.getParameter("professional"));
			user.setEmail(req.getParameter("email"));
			user.setPho(req.getParameter("pho"));
			// user.setSbirh(DateFormat);//req.getParameter("sbirh")
			AddStuService studentService = new AddStuServiceImpl();
			int i = studentService.saveStu(user);
			System.out.println(i);
			if (i == 1) {
				LoginService loginService = new LoginServicelmpl();
				session.setAttribute("username", username);
				List<Student> userList = loginService.getUserList();
				session.setAttribute("userList", userList);
				resp.sendRedirect("student_list.jsp");

			} else {
				session.setAttribute("message", "输入错误");
				resp.sendRedirect("error.jsp");
			}
		} else if (action.equals("saveCla")) {

			Clazz clazz = new Clazz();

			clazz.setOrigincno(req.getParameter("origincno"));
			clazz.setCno(req.getParameter("cno"));
			clazz.setLname(req.getParameter("lname"));
			clazz.setSchool(req.getParameter("school"));
			clazz.setProfessional(req.getParameter("professional"));
			clazz.setNum(Integer.parseInt("0" + req.getParameter("num")));
			if (clazz.getCno() != "" && clazz.getLname() != "" && clazz.getProfessional() != ""
					&& clazz.getSchool() != "" && !req.getParameter("num").equals("")) {
				// user.setSbirh(DateFormat);//req.getParameter("sbirh")
				AddStuService studentService = new AddStuServiceImpl();
				int i = studentService.saveCla(clazz);
				System.out.println(i);
				if (i == 1) {

					ClazzDao clazzDao = new ClazzDao();
					session.setAttribute("username", username);
					List<Clazz> clazzList = clazzDao.getClazzList();
					session.setAttribute("clazzList", clazzList);
					resp.sendRedirect("class_list.jsp");

				} else {
					session.setAttribute("message", "输入错误");
					resp.sendRedirect("error.jsp");
				}
			} else {
				session.setAttribute("message", "输入不能为空");
				resp.sendRedirect("error.jsp");
			}
		} else if (action.equals("reset_password")) {
			// 根据手机号查询用户
			// 重置密码
			Connection conn = ConnectionUtil.getConnection();
			User user = new User();
			PreparedStatement ps = null;
			user.setUsername(username);
			user.setPho(pho);
			user.setNewpassword(MD5Util.getMD5(newpassword));
			Reset_passwordService reset_passwordService = new Reset_passwordServicelmpl();
			UserDao userDao = new UserDao();
			password = userDao.getUsersByUsername(user.getUsername());
			if (reset_passwordService.reset_password(user) == "reset_password.jsp") {
				try {
					ps = conn.prepareStatement("update user set password=? where pho=?");
					ps.setString(1, user.getNewpassword());
					ps.setString(2, pho);
					ps.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				session.setAttribute("username", username);
				resp.sendRedirect("reset_password.jsp");

			} else if (reset_passwordService.reset_password(user) == "username_error") {
				session.setAttribute("message", "用户不存在");
				resp.sendRedirect("error.jsp");
			} else {
				session.setAttribute("message", "电话输入错误");
				resp.sendRedirect("error.jsp");
			}
		} else if (action.equals("class_list")) {

			ClazzDao clazzDao = new ClazzDao();
			session.setAttribute("username", username);
			List<Clazz> clazzList = clazzDao.getClazzList();
			session.setAttribute("clazzList", clazzList);
			resp.sendRedirect("class_list.jsp");

		} else if (action.equals("addclazz")) {// addclazz
			
			Clazz clazz = new Clazz();
			clazz.setCno(req.getParameter("cno"));
			clazz.setLname(req.getParameter("lname"));
			clazz.setSchool(req.getParameter("school"));
			clazz.setProfessional(req.getParameter("professional"));
			ClazzServiceImpl clazzService = new ClazzServiceImpl();
			if (clazz.getCno() != "" && clazz.getLname() != "" && clazz.getProfessional() != ""
					&& clazz.getSchool() != "") {
				if (clazzService.addClass(clazz).equals("add_success")) {
					ClazzDao clazzDao = new ClazzDao();
					session.setAttribute("username", username);

					List<Clazz> clazzList = clazzDao.getClazzList();
					session.setAttribute("clazzList", clazzList);
					System.out.println(clazzList);
					resp.sendRedirect("class_list.jsp");
				} else {
					session.setAttribute("message", "输入错误");
					resp.sendRedirect("error.jsp");
				}
			} else {
				session.setAttribute("message", "输入不能为空");
				resp.sendRedirect("error.jsp");
			}

		} else if (action.equals("selbypro")) {
			String prof = req.getParameter("prof");
			ClazzServiceImpl clazzService = new ClazzServiceImpl();
			if (clazzService.getClazzListByProfessional(prof).equals("select_success")) {
				ClazzDao clazzdao = new ClazzDao();
				List<Clazz> clazzList = clazzdao.getClazzListByProfessional(prof);
				session.setAttribute("username", username);
				session.setAttribute("clazzList", clazzList);
				resp.sendRedirect("class_list.jsp");
			} else {
				// String message = "沒有搜索找到(按专业)";
				session.setAttribute("message", "输入错误");
				resp.sendRedirect("error.jsp");
			}

		} else if (action.equals("selbyschool")) {
			String schooln = req.getParameter("schooln");
			ClazzServiceImpl clazzService = new ClazzServiceImpl();
			if (clazzService.getClazzListBySchoolName(schooln).equals("select_success")) {
				ClazzDao clazzdao = new ClazzDao();
				List<Clazz> clazzList = clazzdao.getClazzListBySchool(schooln);
				session.setAttribute("username", username);
				session.setAttribute("clazzList", clazzList);
				resp.sendRedirect("class_list.jsp");
			} else {
				// String message = "沒有搜索找到(按学校)";
				session.setAttribute("message", "输入错误");
				resp.sendRedirect("error.jsp");
			}

		} else {

		}

	}
}
