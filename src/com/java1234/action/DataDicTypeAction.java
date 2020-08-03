package com.java1234.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;

import com.java1234.dao.DataDicDao;
import com.java1234.dao.DataDicTypeDao;
import com.java1234.model.DataDicType;
import com.java1234.util.DbUtil;

import com.java1234.util.ResponseUtil;
import com.java1234.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class DataDicTypeAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DbUtil dbUtil=new DbUtil();
	private DataDicTypeDao dataDicTypeDao=new DataDicTypeDao();
	private DataDicDao dataDicDao=new DataDicDao();
	private List<DataDicType> dataDicTypeList=new ArrayList<DataDicType>();//新建一个集合，之后会返回到页面中
	
	private String mainPage; //main.jsp中缺少的主页

	private String ddTypeId;
	private DataDicType dataDicType;
	//页面需要显示的东西加get set方法
	public List<DataDicType> getDataDicTypeList() {
		return dataDicTypeList;
	}
	public void setDataDicTypeList(List<DataDicType> dataDicTypeList) {
		this.dataDicTypeList = dataDicTypeList;
	}
	public String getMainPage() {
		return mainPage;
	}
	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public String getDdTypeId() {
		return ddTypeId;
	}
	public void setDdTypeId(String ddTypeId) {
		this.ddTypeId = ddTypeId;
	}
	public DataDicType getDataDicType() {
		return dataDicType;
	}
	public void setDataDicType(DataDicType dataDicType) {
		this.dataDicType = dataDicType;
	}
	public String list(){//页面查询方法
		Connection con=null;
		try{
			con=dbUtil.getCon();
			dataDicTypeList=dataDicTypeDao.dataDicTypeList(con);
		//	navCode=NavUtil.getNavgation("系统管理", "数据字典类别维护");
			mainPage="dataDicType/dataDicTypeList.jsp";
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	public String preSave(){
		
		mainPage="dataDicType/dataDicTypeSave.jsp";
		return SUCCESS;
	}
	
	
	public String save(){
		Connection con=null;
		try{
			con=dbUtil.getCon();
			if(StringUtil.isNotEmpty(ddTypeId)){//修改
				dataDicType.setDdTypeId(Integer.parseInt(ddTypeId));//单独修改id
				dataDicTypeDao.dataDicTypeUpdate(con, dataDicType);
			}else{
				dataDicTypeDao.dataDicTypeAdd(con, dataDicType);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "typeSave";//到structs2进行配置
	}
	
	public String delete(){
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject resultJson=new JSONObject();
			boolean exist=dataDicDao.existDataDicByTypeId(con, ddTypeId);
			if(exist){
				resultJson.put("error", "数据字典类别下面有数据，不能删除！");
			}else{
				dataDicTypeDao.dataDicTypeDelete(con, ddTypeId);
				resultJson.put("success", true);
			}
			ResponseUtil.write(resultJson, ServletActionContext.getResponse());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}
