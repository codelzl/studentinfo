package com.java1234.action;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.java1234.dao.ClassDao;
import com.java1234.dao.DataDicDao;
import com.java1234.dao.GradeDao;
import com.java1234.dao.StudentDao;
import com.java1234.model.Class;
import com.java1234.model.DataDic;
import com.java1234.model.Grade;
import com.java1234.model.PageBean;
import com.java1234.model.Student;
import com.java1234.util.DateUtil;
import com.java1234.util.DbUtil;

import com.java1234.util.PageUtil;

import com.java1234.util.ResponseUtil;
import com.java1234.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class StudentAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;//默认序列
	
	private HttpServletRequest request;
	private DbUtil dbUtil=new DbUtil();
	private StudentDao studentDao=new StudentDao();
	private DataDicDao dataDicDao=new DataDicDao();
	private GradeDao gradeDao=new GradeDao();
	private ClassDao classDao=new ClassDao();
	
	private List<Student> studentList=new ArrayList<Student>();
	
	private String mainPage; 

	
	private List<DataDic> s_sexDataDicList;//对应数据字典s开头设定为查询需要的
	private List<DataDic> s_nationDataDicList;
	
	private List<DataDic> sexDataDicList;
	private List<DataDic> nationDataDicList;
	private List<DataDic> zzmmDataDicList;
	private List<Class> classList;
	
	private List<Grade> s_gradeList;
	private List<Class> s_classList;
	
	private Student s_student;
	
	private String page;
	private int total;
	private String pageCode;
	
	private Student student;
	private String studentId;
	
	private File stuPic;
	private String stuPicFileName;
	
	
	
	public File getStuPic() {
		return stuPic;
	}
	public void setStuPic(File stuPic) {
		this.stuPic = stuPic;
	}
	public String getStuPicFileName() {
		return stuPicFileName;
	}
	public void setStuPicFileName(String stuPicFileName) {
		this.stuPicFileName = stuPicFileName;
	}
	public List<DataDic> getZzmmDataDicList() {
		return zzmmDataDicList;
	}
	public void setZzmmDataDicList(List<DataDic> zzmmDataDicList) {
		this.zzmmDataDicList = zzmmDataDicList;
	}
	public List<DataDic> getSexDataDicList() {
		return sexDataDicList;
	}
	public void setSexDataDicList(List<DataDic> sexDataDicList) {
		this.sexDataDicList = sexDataDicList;
	}
	public List<DataDic> getNationDataDicList() {
		return nationDataDicList;
	}
	public void setNationDataDicList(List<DataDic> nationDataDicList) {
		this.nationDataDicList = nationDataDicList;
	}
	public List<Class> getClassList() {
		return classList;
	}
	public void setClassList(List<Class> classList) {
		this.classList = classList;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public Student getS_student() {
		return s_student;
	}
	public void setS_student(Student s_student) {
		this.s_student = s_student;
	}
	public List<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	public String getMainPage() {
		return mainPage;
	}
	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	
	
	
	public List<DataDic> getS_sexDataDicList() {
		return s_sexDataDicList;
	}
	public void setS_sexDataDicList(List<DataDic> s_sexDataDicList) {
		this.s_sexDataDicList = s_sexDataDicList;
	}
	public List<DataDic> getS_nationDataDicList() {
		return s_nationDataDicList;
	}
	public void setS_nationDataDicList(List<DataDic> s_nationDataDicList) {
		this.s_nationDataDicList = s_nationDataDicList;
	}
	public List<Grade> getS_gradeList() {
		return s_gradeList;
	}
	public void setS_gradeList(List<Grade> s_gradeList) {
		this.s_gradeList = s_gradeList;
	}
	public List<Class> getS_classList() {
		return s_classList;
	}
	public void setS_classList(List<Class> s_classList) {
		this.s_classList = s_classList;
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
	
	public String list(){
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){//当前空，默认1
			page="1";
		}
		if(s_student!=null){//点击查询以后跳转到的该页
			session.setAttribute("s_student", s_student);
		}else{
			Object o=session.getAttribute("s_student");
			if(o!=null){//点击分页时候
				s_student=(Student)o;
			}else{//默认的首次
				s_student=new Student();
			}
		}
		Connection con=null;
		try{
			con=dbUtil.getCon();
			PageBean pageBean=new PageBean(Integer.parseInt(page),8);
			studentList=studentDao.studentList(con,s_student,pageBean);
			
		//	navCode=NavUtil.getNavgation("学生信息管理", "学生维护");
			total=studentDao.studentCount(con, s_student);
			pageCode=PageUtil.genPagation(request.getContextPath()+"/student!list", total, Integer.parseInt(page),8);
			
			DataDic s_dataDic=new DataDic();
			s_dataDic.setDdTypeName("性别");//查询性别从数据字典
			s_sexDataDicList=dataDicDao.dataDicList(con, s_dataDic, null);//null无需分页
			
			s_dataDic.setDdTypeName("名族");
			s_nationDataDicList=dataDicDao.dataDicList(con, s_dataDic, null);
			
			s_gradeList=gradeDao.gradeList(con);
			
			if(s_student!=null && s_student.getGradeId()!=-1){//不为空
				Class s_class=new Class();
				s_class.setGradeId(s_student.getGradeId());
				s_classList=classDao.classList(con, s_class);
			}
			
			mainPage="student/studentList.jsp";
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
	
	public String show(){
		Connection con=null;
		try{
			con=dbUtil.getCon();
			student=studentDao.getStudentById(con, studentId);
		//	navCode=NavUtil.getNavgation("学生信息管理", "学生详细信息");
			mainPage="student/studentShow.jsp";
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
	
	public String preSave(){//提前添加处理
		Connection con=null;
		try{
			con=dbUtil.getCon();
			if(StringUtil.isNotEmpty(studentId)){
				student=studentDao.getStudentById(con, studentId);
			}
			DataDic s_dataDic=new DataDic();
			s_dataDic.setDdTypeName("性别");//下拉框LIST
			sexDataDicList=dataDicDao.dataDicList(con, s_dataDic, null);
			
			s_dataDic.setDdTypeName("名族");
			nationDataDicList=dataDicDao.dataDicList(con, s_dataDic, null);
			
			s_dataDic.setDdTypeName("政治面貌");
			zzmmDataDicList=dataDicDao.dataDicList(con, s_dataDic, null);
			
			classList=classDao.classList(con, null);//查询所有班级
			
			mainPage="student/studentSave.jsp";
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
	
	public String save(){
		Connection con=null;
		try{
			con=dbUtil.getCon();
			
			if(StringUtil.isNotEmpty(studentId)){
				student.setStudentId(studentId);
				studentDao.studentUpdate(con, student);
			}else{
				studentDao.studentAdd(con, student);
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
		return "save";
	}
	
	public String delete(){
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject resultJson=new JSONObject();
			studentDao.studentDelete(con, studentId);
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
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

}
