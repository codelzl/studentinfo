<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
	function checkForm(){
		var ddTypeId=$("#ddTypeId").val();
		var ddValue=$("#ddValue").val();
		if(ddTypeId==null || ddTypeId==""){
			$("#error").html("请选择数据字典类别！");
			return false;
		}
		if(ddValue==null || ddValue==""){
			$("#error").html("数据字典值不能为空！");
			return false;
		}
		return true;
	}
</script>
<div class="data_list">
	<div class="data_content">
		<form action="dataDic!save" method="post" onsubmit="return checkForm()">
			<table width="40%" align="center">
				<tr>
					<td><label>数据字典类别：</label></td>
					<td>
						<select id="ddTypeId" name="dataDic.ddTypeId">
							<option value="">请选择数据字典类别</option>
							<c:forEach var="dataDicType" items="${dataDicTypeList }">
								<option value="${dataDicType.ddTypeId }" ${dataDicType.ddTypeId==dataDic.ddTypeId?'selected':'' }>${dataDicType.ddTypeName }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td><label>数据字典值：</label></td>
					<td><input type="text" id="ddValue" name="dataDic.ddValue" value="${dataDic.ddValue }" /></td>
				</tr>
				<tr>
					<td valign="top"><label>数据字典描述：</label></td>
					<td>
						<textarea rows="5" cols="10" id="ddDesc" name="dataDic.ddDesc">${dataDic.ddDesc }</textarea>
					</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" id="ddId" name="ddId" value="${dataDic.ddId }"/>
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