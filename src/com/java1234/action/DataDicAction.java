package com.java1234.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.java1234.dao.DataDicDao;
import com.java1234.dao.DataDicTypeDao;
import com.java1234.model.DataDic;
import com.java1234.model.DataDicType;
import com.java1234.model.PageBean;
import com.java1234.util.DbUtil;
//import com.java1234.util.NavUtil;
import com.java1234.util.PageUtil;

import com.java1234.util.ResponseUtil;
import com.java1234.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class DataDicAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private DbUtil dbUtil=new DbUtil();
	private DataDicDao dataDicDao=new DataDicDao();
	private DataDicTypeDao dataDicTypeDao=new DataDicTypeDao();
	private List<DataDic> dataDicList=new ArrayList<DataDic>();
	private List<DataDicType> dataDicTypeList=new ArrayList<DataDicType>();
	private String mainPage; 
	private String navCode;
	
	private String s_ddTypeName;//��ѯ���Ӧ
	
	private String page;//��ǰҳ
	private int total;//�ܼ�¼��
	private String pageCode;//��ҳ�Ĵ���
	
	private String ddId;
	
	private DataDic dataDic;
	
	
	
	public List<DataDicType> getDataDicTypeList() {
		return dataDicTypeList;
	}

	public void setDataDicTypeList(List<DataDicType> dataDicTypeList) {
		this.dataDicTypeList = dataDicTypeList;
	}

	public DataDic getDataDic() {
		return dataDic;
	}

	public void setDataDic(DataDic dataDic) {
		this.dataDic = dataDic;
	}

	public String getDdId() {
		return ddId;
	}

	public void setDdId(String ddId) {
		this.ddId = ddId;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public List<DataDic> getDataDicList() {
		return dataDicList;
	}

	public void setDataDicList(List<DataDic> dataDicList) {
		this.dataDicList = dataDicList;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public String getNavCode() {
		return navCode;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}

	public String getS_ddTypeName() {
		return s_ddTypeName;
	}

	public void setS_ddTypeName(String s_ddTypeName) {
		this.s_ddTypeName = s_ddTypeName;
	}

	public String list(){
		if(StringUtil.isEmpty(page)){
			page="1";
		}//��ǰҳΪ�գ���һҳ
		Connection con=null;
		try{
			HttpSession session=request.getSession();//��ֹ��ת��ҳ�Ժ�û�д���ȥ���ݣ���SESSION
			DataDic s_dataDic=new DataDic();
			if(s_ddTypeName!=null){
				s_dataDic.setDdTypeName(s_ddTypeName);
				session.setAttribute("s_ddTypeName", s_ddTypeName);
			}else{
				Object o=session.getAttribute("s_ddTypeName");
				if(o!=null){
					s_ddTypeName=(String)session.getAttribute("s_ddTypeName");
					s_dataDic.setDdTypeName(s_ddTypeName);
				}
			}
			PageBean pageBean=new PageBean(Integer.parseInt(page),8);//8ÿҳ8����¼
			con=dbUtil.getCon();
			dataDicList=dataDicDao.dataDicList(con,s_dataDic,pageBean);
			total=dataDicDao.dataDicCount(con, s_dataDic);
			pageCode=PageUtil.genPagation(request.getContextPath()+"/dataDic!list", total, Integer.parseInt(page), 8);
			//("ϵͳ����", "�����ֵ�ά��");
			mainPage="dataDic/dataDicList.jsp";
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

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}
	
	public String delete(){
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject resultJson=new JSONObject();
			dataDicDao.dataDicDelete(con, ddId);
			resultJson.put("success", true);
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
	
	public String preSave(){
		
		mainPage="dataDic/dataDicSave.jsp";
		return SUCCESS;
	}
	
	public String save(){
		Connection con=null;
		try{
			con=dbUtil.getCon();
			if(StringUtil.isNotEmpty(ddId)){
				dataDic.setDdId(Integer.parseInt(ddId));
				dataDicDao.dataDicUpdate(con, dataDic);
			}else{
				dataDicDao.dataDicAdd(con, dataDic);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.getCon();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "save";
	}
}
