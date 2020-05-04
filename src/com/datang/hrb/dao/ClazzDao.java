package com.datang.hrb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.datang.hrb.vo.Clazz;
import com.datang.hrb.vo.Student;


public class ClazzDao {

	public List<Clazz> getClazzList() {

		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = null;
		//System.out.println("nulllllllllllll");
		List<Clazz> clazzList = new ArrayList<Clazz>();

		try {
			ps = conn.prepareStatement("select * from Class");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Clazz clazz = new Clazz();
				clazz.setCno(rs.getString(1));
				clazz.setLname(rs.getString(2));
				clazz.setSchool(rs.getString(3));
				clazz.setProfessional(rs.getString(4));
				clazz.setNum(rs.getInt(5));
				clazzList.add(clazz);
			}
		} catch (SQLException e) {
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
		
		return clazzList;
	}

	public boolean add(Clazz clazz) {
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("insert into Class(cno,lname,school,professional,num) values (?,?,?,?,?)");
			ps.setString(1, clazz.getCno());
			ps.setString(2, clazz.getLname());
			ps.setString(3, clazz.getSchool());
			ps.setString(4, clazz.getProfessional());
			ps.setInt(5, 30);

			if (ps.executeUpdate() == 1) {
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
					e.printStackTrace();
				}
			}
		}

	}

	public List<Clazz> getClazzListByProfessional(String str) {

		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = null;

		List<Clazz> clazzList = new ArrayList<Clazz>();
		try {
			ps = conn.prepareStatement("SELECT * FROM Class WHERE professional LIKE '%" + str + "%';");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Clazz clazz = new Clazz();
				clazz.setCno(rs.getString(1));
				clazz.setLname(rs.getString(2));
				clazz.setSchool(rs.getString(3));
				clazz.setProfessional(rs.getString(4));
				clazzList.add(clazz);
			}
		} catch (SQLException e) {
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

		return clazzList;
	}

	public List<Clazz> getClazzListBySchool(String schoolname) {

		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = null;

		List<Clazz> clazzList = new ArrayList<Clazz>();
		try {
			ps = conn.prepareStatement("SELECT * FROM Class WHERE school LIKE '%" + schoolname + "%';");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Clazz clazz = new Clazz();
				clazz.setCno(rs.getString(1));
				clazz.setLname(rs.getString(2));
				clazz.setSchool(rs.getString(3));
				clazz.setProfessional(rs.getString(4));
				clazzList.add(clazz);
			}
		} catch (SQLException e) {
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
		return clazzList;
	}
	public int saveCla(Clazz clazz) {
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps=null;
		int result = 0;
		try {
			ps = conn.prepareStatement("update class set cno=?,lname=?,school=?,professional=?,num=? where cno=?");
			ps.setString(1, clazz.getCno());
			ps.setString(2, clazz.getLname());
			ps.setString(3, clazz.getSchool());
			ps.setString(4, clazz.getProfessional());
			ps.setInt(5, clazz.getNum());
			ps.setString(6, clazz.getOrigincno());
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

}
