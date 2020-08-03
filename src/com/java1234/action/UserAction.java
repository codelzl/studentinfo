package com.java1234.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.java1234.dao.UserDao;
import com.java1234.model.User;
import com.java1234.util.DbUtil;

import com.java1234.util.ResponseUtil;
import com.java1234.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	
	private User user;
	private String error;

	
	private int userId;
	private String password;
	
	private String mainPage; 

	
	private String imageCode;
	
	
	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}
	
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	


	
	private DbUtil dbUtil=new DbUtil();
	private UserDao userDao=new UserDao();

	public String login(){
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(user.getUserName())||StringUtil.isEmpty(user.getPassword())){
			error="用户名或密码为空！";
			return ERROR;//进入STRUCTS2配置
		}
	
		
		if(StringUtil.isEmpty(imageCode)){
			error="验证码为空！";
			return ERROR;
		}
		if(!imageCode.equals(session.getAttribute("sRand"))){
			error="验证码错误！";
			return ERROR;
		}
		
		
		Connection con=null;
		try{
			con=dbUtil.getCon();
			User currentUser=userDao.login(con, user);
			if(currentUser==null){
				error="用户名或密码错误！";
				return ERROR;
			}else{
				session.setAttribute("currentUser", currentUser);
				return SUCCESS;
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
		return SUCCESS;
	}
	

	
	public String save(){
		User user=new User();
		user.setUserId(userId);
		user.setPassword(password);
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int updateNums=userDao.modifyPassword(con, user);
			JSONObject resultJson=new JSONObject();
			if(updateNums>0){
				resultJson.put("success", true);
			}else{
				resultJson.put("errorMsg", "修改密码失败");
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
	
	public String logOut(){
		HttpSession session=request.getSession();
		session.removeAttribute("currentUser");
		return "logOut";
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

}
