<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
	function classDelete(classId){
		if(confirm("确定要删除这条班级信息吗?")){
			$.post("class!delete",{classId:classId},
					function(result){
						var result=eval('('+result+')');
						if(result.error){
							alert(result.error);
						}else{
							alert("删除成功！");
							window.location.href="${pageContext.request.contextPath}/class!list";
						}
					}
			);
		}
	}
</script>
<div class="data_list">
	<div class="search_content">
		<button class="btn btn-mini btn-primary" type="button" style="float: right;margin-bottom: 5px;" onclick="javascript:window.location='class!preSave'">添加班级</button>
	</div>
	<div class="data_content">
		<table class="table table-bordered table-hover">
			<tr>
				<th>序号</th>
				<th>班级名称</th>
				<th>所在年级</th>
				<th>班级备注</th>
				<th>操作</th>
			</tr>
			<c:forEach var="c" items="${classList }" varStatus="status">
				<tr>
					<td>${status.index+1 }</td>
					<td>${c.className }</td>
					<td>${c.gradeName }</td>
					<td>${c.classDesc }</td>
					<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='class!preSave?classId=${c.classId}'">修改</button>&nbsp;&nbsp;<button class="btn btn-mini btn-danger" type="button" onclick="classDelete(${c.classId})">删除</button></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>