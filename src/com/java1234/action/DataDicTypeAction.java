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
	private List<DataDicType> dataDicTypeList=new ArrayList<DataDicType>();//�½�һ�����ϣ�֮��᷵�ص�ҳ����
	
	private String mainPage; //main.jsp��ȱ�ٵ���ҳ

	private String ddTypeId;
	private DataDicType dataDicType;
	//ҳ����Ҫ��ʾ�Ķ�����get set����
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
	public String list(){//ҳ���ѯ����
		Connection con=null;
		try{
			con=dbUtil.getCon();
			dataDicTypeList=dataDicTypeDao.dataDicTypeList(con);
		//	navCode=NavUtil.getNavgation("ϵͳ����", "�����ֵ����ά��");
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
			if(StringUtil.isNotEmpty(ddTypeId)){//�޸�
				dataDicType.setDdTypeId(Integer.parseInt(ddTypeId));//�����޸�id
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
		return "typeSave";//��structs2��������
	}
	
	public String delete(){
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject resultJson=new JSONObject();
			boolean exist=dataDicDao.existDataDicByTypeId(con, ddTypeId);
			if(exist){
				resultJson.put("error", "�����ֵ�������������ݣ�����ɾ����");
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
