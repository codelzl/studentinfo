package com.java1234.util;

public class PageUtil {


	//targetUrl目标地址
	public static String genPagation(String targetUrl,int totalNum,int currentPage,int pageSize){
		int totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;//求总页数
		StringBuffer pageCode=new StringBuffer();//拼装分页下面的效果
		pageCode.append("<li><a href='"+targetUrl+"?page=1'>首页</a></li>");
		if(currentPage==1){
			pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");			//上一页不可用
		}else{
			pageCode.append("<li><a href='"+targetUrl+"?page="+(currentPage-1)+"'>上一页</a></li>");		//上一页地址	
		}
		for(int i=currentPage-2;i<=currentPage+2;i++){//中间页
			if(i<1||i>totalPage){
				continue;
			}
			if(i==currentPage){//当前页
				pageCode.append("<li class='active'><a href='#'>"+i+"</a></li>");		
			}else{//数字和页对应
				pageCode.append("<li><a href='"+targetUrl+"?page="+i+"'>"+i+"</a></li>");	
			}
		}
		if(currentPage==totalPage){
			pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");		//下一页不可用	
		}else{
			pageCode.append("<li><a href='"+targetUrl+"?page="+(currentPage+1)+"'>下一页</a></li>");		//下一页地址
		}
		pageCode.append("<li><a href='"+targetUrl+"?page="+totalPage+"'>尾页</a></li>");
		return pageCode.toString();
	}
}
