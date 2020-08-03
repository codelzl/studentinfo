<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="data_list">
	<div class="data_content">
		<table width="100%" align="center">
			<tr>
				<td><label>学号：</label></td>
				<td><input type="text" id="stuNo" value="${student.stuNo }" readonly="readonly"/></td>
				<td>&nbsp;</td>
				<td><label>姓名：</label></td>
				<td><input type="text" id="stuName" value="${student.stuName }" readonly="readonly"/></td>
				</tr>
			<tr>
				<td><label>性别：</label></td>
				<td><input type="text" id="stuSex" value="${student.stuSex }" readonly="readonly"/></td>
				<td>&nbsp;</td>
				<td><label>出生日期：</label></td>
				<td><input type="text" id="stuBirthday" value="<fmt:formatDate value="${student.stuBirthday }" type="date" pattern="yyyy-MM-dd"/> " readonly="readonly"/></td>
			</tr>
			<tr>
				<td><label>名族：</label></td>
				<td><input type="text" id="stuNation" value="${student.stuNation }" readonly="readonly"/></td>
				<td>&nbsp;</td>
				<td><label>政治面貌：</label></td>
				<td><input type="text" id="stuZzmm" value="${student.stuZzmm }" readonly="readonly"/></td>
			</tr>
			<tr>
				<td><label>入学时间：</label></td>
				<td><input type="text" id="stuRxsj" value="<fmt:formatDate value="${student.stuRxsj }" type="date" pattern="yyyy-MM-dd"/>" readonly="readonly"/></td>
				<td>&nbsp;</td>
				<td><label>班级：</label></td>
				<td><input type="text" id="className" value="${student.className }" readonly="readonly"/></td>
			</tr>
			<tr>
				<td><label>备注：</label></td>
				<td colspan="4">
					<textarea rows="5" style="width: 550px;" readonly="readonly" id="stuDesc">${student.stuDesc }</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="right">
					<input type="button" class="btn  btn-primary" value="返回" onclick="javascript:history.back() "/>
				</td>
			</tr>
		</table>
	</div>
</div>