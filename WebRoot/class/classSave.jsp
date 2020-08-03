<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
	function checkForm(){
		var gradeId=$("#gradeId").val();
		var className=$("#className").val();
		if(gradeId==null || gradeId==""){
			alert("请选择所在年级！");
			return false;
		}
		if(className==null || className==""){
			alert("班级名称不能为空！");
			return false;
		}
		return true;
	}
</script>
<div class="data_list">
	<div class="data_content">
		<form action="class!save" method="post" onsubmit="return checkForm()">
			<table width="40%" align="center">
				<tr>
					<td><label>班级名称：</label></td>
					<td><input type="text" id="className" name="c.className" value="${c.className }" /></td>
				</tr>
				<tr>
					<td><label>所在年级：</label></td>
					<td>
						<select id="gradeId" name="c.gradeId">
							<option value="">请选择数据字典类别</option>
							<c:forEach var="grade" items="${gradeList }">
								<option value="${grade.gradeId }" ${grade.gradeId==c.gradeId?'selected':'' }>${grade.gradeName }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				
				<tr>
					<td valign="top"><label>班级描述：</label></td>
					<td>
						<textarea rows="5" cols="10" id="classDesc" name="c.classDesc">${c.classDesc }</textarea>
					</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" id="classId" name="classId" value="${c.classId }"/>
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