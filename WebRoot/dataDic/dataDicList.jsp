<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
	function dataDicDelete(ddId){
		if(confirm("确定要删除这条数据字典吗?")){
			$.post("dataDic!delete",{ddId:ddId},
					function(result){
						var result=eval('('+result+')');
						if(result.error){
							alert(result.error);
						}else{
							alert("删除成功！");
							window.location.href="${pageContext.request.contextPath}/dataDic!list";
						}
					}
			);
		}
	}
</script>
<div class="data_list">
	<div class="search_content">
		<form action="${pageContext.request.contextPath}/dataDic!list" method="post">
			数据字典类别名称：<input type="text" id="s_ddTypeName" name="s_ddTypeName" value="${s_ddTypeName }"/>
			&nbsp;&nbsp;<button class="btn btn-primary" type="submit" style="margin-top: -8px;">查询</button>
		</form>
		<button class="btn btn-mini btn-primary" type="button" style="float: right;margin-bottom: 5px;" onclick="javascript:window.location='dataDic!preSave'">添加数据字典</button>
	</div>
	<div class="data_content">
		<table class="table table-bordered table-hover">
			<tr>
				<th>序号</th>
				<th>数据字典类别名称</th>
				<th>数据字典值</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
			<c:forEach var="dataDic" items="${dataDicList }" varStatus="status">
				<tr>
					<td>${status.index+1 }</td>
					<td>${dataDic.ddTypeName }</td>
					<td>${dataDic.ddValue }</td>
					<td>${dataDic.ddDesc }</td>
					<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='dataDic!preSave?ddId=${dataDic.ddId}'">修改</button>&nbsp;&nbsp;<button class="btn btn-mini btn-danger" type="button" onclick="dataDicDelete(${dataDic.ddId})">删除</button></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="pagination pagination-centered">
  		<ul>
  			${pageCode }
  		</ul>
	</div>
</div>