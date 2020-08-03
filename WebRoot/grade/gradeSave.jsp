<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
	function checkForm(){
		var gradeName=$("#gradeName").val();
		if(gradeName==null || gradeName==""){
			alert("年级不能为空！");
			return false;
		}
		return true;
	}
</script>
<div class="data_list">
	<div class="data_content">
		<form action="grade!save" method="post" onsubmit="return checkForm()">
			<table width="40%" align="center">
				<tr>
					<td><label>年级名称：</label></td>
					<td><input type="text" id="gradeName" name="grade.gradeName" value="${grade.gradeName }" /></td>
				</tr>
				<tr>
					<td valign="top"><label>年级描述：</label></td>
					<td>
						<textarea rows="5" cols="10" id="gradeDesc" name="grade.gradeDesc">${grade.gradeDesc }</textarea>
					</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" id="gradeId" name="gradeId" value="${grade.gradeId }"/>
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