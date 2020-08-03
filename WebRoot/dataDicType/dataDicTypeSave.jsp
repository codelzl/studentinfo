<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
	function checkForm(){
		var ddTypeName=$("#ddTypeName").val();
		if(ddTypeName==null || ddTypeName==""){
			alert("数据字典类别不能为空！");
			return false;
		}
		return true;
	}
</script>
<div class="data_list">
	<div class="data_content">
		<form action="dataDicType!save" method="post" onsubmit="return checkForm()">
			<table width="40%" align="center">
				<tr>
					<td><label>数据字典类别名称：</label></td>
					<td><input type="text" id="ddTypeName" name="dataDicType.ddTypeName" value="${dataDicType.ddTypeName }" /></td>
				</tr>
				<tr>
					<td valign="top"><label>数据字典类别描述：</label></td>
					<td>
						<textarea rows="5" cols="10" id="ddTypeDesc" name="dataDicType.ddTypeDesc">${dataDicType.ddTypeDesc }</textarea>
					</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" id="ddTypeId" name="ddTypeId" value="${dataDicType.ddTypeId }"/>
						<input type="submit" class="btn  btn-primary" value="保存"/>
					</td> 
					<td>
						<input type="button" class="btn  btn-primary" value="返回" onclick="javascript:history.back() "/>&nbsp;&nbsp;<font id="error" color="red">${error }</font>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>