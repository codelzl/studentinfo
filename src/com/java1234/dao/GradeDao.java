package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.java1234.model.Grade;

public class GradeDao {

	public List<Grade> gradeList(Connection con)throws Exception{
		List<Grade> gradeList=new ArrayList<Grade>();
		String sql="select * from t_grade";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			Grade grade=new Grade();
			grade.setGradeId(rs.getInt("gradeId"));
			grade.setGradeName(rs.getString("gradeName"));
			grade.setGradeDesc(rs.getString("gradeDesc"));
			gradeList.add(grade);
		}
		return gradeList;
	}
	
	
	public int gradeAdd(Connection con,Grade grade)throws Exception{
		String sql="insert into t_grade values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, grade.getGradeName());
		pstmt.setString(2, grade.getGradeDesc());
		return pstmt.executeUpdate();
	}
	
	public Grade getGradeById(Connection con,String gradeId)throws Exception{
		String sql="select * from t_grade where gradeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, gradeId);
		ResultSet rs=pstmt.executeQuery();
		Grade grade=new Grade();
		if(rs.next()){
			grade.setGradeId(rs.getInt("gradeId"));
			grade.setGradeName(rs.getString("gradeName"));
			grade.setGradeDesc(rs.getString("gradeDesc"));
		}
		return grade;
	}
	
	public int gradeUpdate(Connection con,Grade grade)throws Exception{
		String sql="update t_grade set gradeName=?,gradeDesc=? where gradeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, grade.getGradeName());
		pstmt.setString(2, grade.getGradeDesc());
		pstmt.setInt(3, grade.getGradeId());
		return pstmt.executeUpdate();
	}
	
	public int gradeDelete(Connection con,String gradeId)throws Exception{
		String sql="delete from t_grade where gradeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,gradeId);
		return pstmt.executeUpdate();
	}
}
