package com.datang.hrb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.datang.hrb.vo.Student;

//澧炲埅鏀规煡
public class StudentDao {
	// 澧炲姞璁板綍
	public boolean addStu(Student student) {
		// TODO Auto-generated method stub
		if (student.getSno() != null && student.getName() != null && student.getPho() != null) {
			Connection conn = ConnectionUtil.getConnection();// 鑾峰彇鏁版嵁搴撹繛鎺�
			PreparedStatement ps = null;// jdbc棰勫鐞嗗璞�

			try {
				ps = conn.prepareStatement("insert into student(sno,sname,ssex,bname,professional,email,pho) values(?,?,?,?,?,?,?)");

				ps.setString(1, student.getSno());
				ps.setString(2, student.getName());
				ps.setString(3, student.getSex());
				ps.setString(4, student.getBname());
				ps.setString(5, student.getProfessional());
				ps.setString(6, student.getEmail());
				ps.setString(7, student.getPho());
				//ps.setDate(8, (java.sql.Date) student.getSbirh());

				int i = ps.executeUpdate();
				if (i == 1) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
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
		}
		return false;
	}

	// 鏌ヨ鎵�鏈夋暟鎹�
	public List<Student> getStudentList() {

		Connection conn = ConnectionUtil.getConnection();// 鑾峰彇鏁版嵁搴撹繛鎺�
		PreparedStatement ps = null;// jdbc棰勫鐞嗗璞�

		List<Student> studentList = new ArrayList<Student>();

		try {
			ps = conn.prepareStatement("select * from student");
			ResultSet rs = ps.executeQuery();
			// 瀹氫箟娉涘瀷瀛樺偍鏁版嵁骞跺瓨鍌ㄥ埌userList

			while (rs.next()) {
				Student student = new Student();
				student.setSno(rs.getString(1));
				student.setName(rs.getString(2));
				student.setSex(rs.getString(3));
				student.setBname(rs.getString(4));
				student.setProfessional(rs.getString(5));
				student.setEmail(rs.getString(6));
				student.setPho(rs.getString(7));
				//student.setSbirh(rs.getDate(8));
				studentList.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return studentList;
	}
	public int saveStu(Student user) {
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps=null;
		int result = 0;
		try {
			System.out.println("user.getOriginCode()==="+user.getOriginCode());
			ps = conn.prepareStatement("update student set sno=?,sname=?,ssex=?,bname=?,Professional=?,email=?,pho=? where sno=?");
			ps.setString(1, user.getSno());
			ps.setString(2, user.getName());
			ps.setString(3, user.getSex());
			ps.setString(4, user.getBname());
			ps.setString(5, user.getProfessional());
			ps.setString(6, user.getEmail());
			ps.setString(7, user.getPho());
			//ps.setDate(8,(Date) user.getSbirh());
			ps.setString(8, user.getOriginCode());
			result = ps.executeUpdate();
		
		} catch (SQLException e) {
			//System.out.println("000000");
			e.printStackTrace();
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		return result;
	}

	// 鎸夊鍚嶆煡瑭�
	public List<Student> getStudentListByName(Student stu) {
		// TODO Auto-generated method stub
		Connection conn = ConnectionUtil.getConnection();// 鑾峰彇鏁版嵁搴撹繛鎺�
		PreparedStatement ps = null;// jdbc棰勫鐞嗗璞�
		// 瀹氫箟娉涘瀷瀛樺偍鏁版嵁骞跺瓨鍌ㄥ埌studentList
		List<Student> studentList = new ArrayList<Student>();

		try {
			ps = conn.prepareStatement("SELECT * FROM student WHERE sname LIKE '%" + stu.getName() + "%';");
//			System.out.println("寮傚父2");
//			ps.setString(1, stu.getSname());
//			System.out.println(stu.getSname());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Student student = new Student();
				student.setSno(rs.getString(1));
				student.setName(rs.getString(2));
				student.setSex(rs.getString(3));
				student.setBname(rs.getString(4));
				student.setProfessional(rs.getString(5));
				student.setEmail(rs.getString(6));
				student.setPho(rs.getString(7));
				//student.setSbirh(rs.getDate(8));
				studentList.add(student);
			}
			//System.out.println("studentList" + studentList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// System.out.println("寮傚父");
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
		return studentList;
	}
}
