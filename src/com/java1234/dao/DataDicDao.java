package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.java1234.model.DataDic;
import com.java1234.model.PageBean;
import com.java1234.util.StringUtil;

public class DataDicDao {

	public boolean existDataDicByTypeId(Connection con,String ddTypeId)throws Exception{
		String sql="select * from t_dataDic where ddTypeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,ddTypeId);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return true;
		}else{
			return false;
		}
	}//检测数据字典下是否有值，有不能删
	
	public List<DataDic> dataDicList(Connection con,DataDic s_dataDic,PageBean pageBean)throws Exception{
		List<DataDic> dataDicList=new ArrayList<DataDic>();
		StringBuffer sb=new StringBuffer("select * from t_dataDic t1,t_dataDicType t2 where t1.ddTypeId=t2.ddTypeId");
		if(StringUtil.isNotEmpty(s_dataDic.getDdTypeName())){
			sb.append(" and t2.ddTypeName like '%"+s_dataDic.getDdTypeName()+"%'");//模糊查询
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}//分页
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			DataDic dataDic=new DataDic();
			dataDic.setDdId(rs.getInt("ddId"));
			dataDic.setDdTypeId(rs.getInt("ddTypeId"));
			dataDic.setDdTypeName(rs.getString("ddTypeName"));
			dataDic.setDdValue(rs.getString("ddValue"));
			dataDic.setDdDesc(rs.getString("ddDesc"));
			dataDicList.add(dataDic);//添加到数组
		}
		return dataDicList;
	}
	//查询总记录数
	public int dataDicCount(Connection con,DataDic s_dataDic)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_dataDic t1,t_dataDicType t2 where t1.ddTypeId=t2.ddTypeId");//查询总记录数
		if(StringUtil.isNotEmpty(s_dataDic.getDdTypeName())){
			sb.append(" and t2.ddTypeName like '%"+s_dataDic.getDdTypeName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	
	public int dataDicDelete(Connection con,String Id)throws Exception{
		String sql="delete from t_dataDic where ddId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,Id);
		return pstmt.executeUpdate();
	}
	
	public int dataDicAdd(Connection con,DataDic dataDic)throws Exception{
		String sql="insert into t_dataDic values(null,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, dataDic.getDdTypeId());
		pstmt.setString(2, dataDic.getDdValue());
		pstmt.setString(3, dataDic.getDdDesc());
		return pstmt.executeUpdate();
	}
	
	public int dataDicUpdate(Connection con,DataDic dataDic)throws Exception{
		String sql="update t_dataDic set ddTypeId=?,ddValue=?,ddDesc=? where ddId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, dataDic.getDdTypeId());
		pstmt.setString(2, dataDic.getDdValue());
		pstmt.setString(3, dataDic.getDdDesc());
		pstmt.setInt(4, dataDic.getDdId());
		return pstmt.executeUpdate();
	}
	
	public DataDic getDataDicById(Connection con,String ddId)throws Exception{
		String sql="select * from t_dataDic t1,t_dataDicType t2 where t1.ddTypeId=t2.ddTypeId and t1.ddId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, ddId);
		ResultSet rs=pstmt.executeQuery();
		DataDic dataDic=new DataDic();
		if(rs.next()){
			dataDic.setDdId(rs.getInt("ddId"));
			dataDic.setDdTypeId(rs.getInt("ddTypeId"));
			dataDic.setDdTypeName(rs.getString("ddTypeName"));
			dataDic.setDdValue(rs.getString("ddValue"));
			dataDic.setDdDesc(rs.getString("ddDesc"));
		}
		return dataDic;
	}
}
