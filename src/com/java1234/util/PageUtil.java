package com.java1234.util;

public class PageUtil {


	//targetUrlĿ���ַ
	public static String genPagation(String targetUrl,int totalNum,int currentPage,int pageSize){
		int totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;//����ҳ��
		StringBuffer pageCode=new StringBuffer();//ƴװ��ҳ�����Ч��
		pageCode.append("<li><a href='"+targetUrl+"?page=1'>��ҳ</a></li>");
		if(currentPage==1){
			pageCode.append("<li class='disabled'><a href='#'>��һҳ</a></li>");			//��һҳ������
		}else{
			pageCode.append("<li><a href='"+targetUrl+"?page="+(currentPage-1)+"'>��һҳ</a></li>");		//��һҳ��ַ	
		}
		for(int i=currentPage-2;i<=currentPage+2;i++){//�м�ҳ
			if(i<1||i>totalPage){
				continue;
			}
			if(i==currentPage){//��ǰҳ
				pageCode.append("<li class='active'><a href='#'>"+i+"</a></li>");		
			}else{//���ֺ�ҳ��Ӧ
				pageCode.append("<li><a href='"+targetUrl+"?page="+i+"'>"+i+"</a></li>");	
			}
		}
		if(currentPage==totalPage){
			pageCode.append("<li class='disabled'><a href='#'>��һҳ</a></li>");		//��һҳ������	
		}else{
			pageCode.append("<li><a href='"+targetUrl+"?page="+(currentPage+1)+"'>��һҳ</a></li>");		//��һҳ��ַ
		}
		pageCode.append("<li><a href='"+targetUrl+"?page="+totalPage+"'>βҳ</a></li>");
		return pageCode.toString();
	}
}
