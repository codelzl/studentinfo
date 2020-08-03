<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function studentDelete(studentId){
		if(confirm("确定要删除这条学生信息吗?")){
			$.post("student!delete",{studentId:studentId},
					function(result){
						var result=eval('('+result+')');
						if(result.error){
							alert(result.error);
						}else{
							alert("删除成功！");
							window.location.href="${pageContext.request.contextPath}/student!list";
						}
					}
			);
		}
	}

	function loadClassInfo(){//状态发生改变进行调用
		var s_gradeId=$("#s_gradeId").val();//获取选中的年级ID
		$("#s_classId option[value!='-1']").remove();//选中年级以后不等-1的都进行班级ID重置
		if(s_gradeId==-1){
			$("#s_classId").val("-1");//保留选择那栏
			return;
		}
		$.post(
		"class!getClassByGradeId",
		{s_gradeId:s_gradeId},//传值
			function(result){//传回数据
				$("#s_classId").val("-1");
				var result=eval("("+result+")");//转为JS对象
				$.each(result,function(i,item){//遍历js对象
					$("<option value='"+item.classId+"'>"+item.className+"</option>").appendTo($("#s_classId"));//添加到ID值#s_classId，给SELECT添加项
				});
			}		
		);
	}
	
	function resetValue(){
		$("#s_stuNo").val("");
		$("#s_stuName").val("");
		$("#s_stuSex").val("-1");
		$("#s_stuNation").val("-1");
		$("#s_gradeId").val("-1");
		$("#s_classId").val("-1");
		$("#s_b_stuBirthday").val("");
		$("#s_e_stuBirthday").val("");
		$("#s_b_stuRxsj").val("");
		$("#s_e_stuRxsj").val("");
	}
</script>
<div class="data_list">
	<div class="search_content">
		<form action="${pageContext.request.contextPath}/student!list" method="post">
			<table align="center">
				<tr>
					<td><lable>学号：</lable></td>
					<td><input type="text" id="s_stuNo" name="s_student.stuNo" value="${s_student.stuNo }"/></td>
					<td width="30px;">&nbsp;</td>
					<td><lable>姓名：</lable></td>
					<td><input type="text" id="s_stuName" name="s_student.stuName" value="${s_student.stuName }"/></td>
				</tr>
				<tr>
					<td><lable>性别：</lable></td>
					<td>  <!--select下拉框-->
						<select id="s_stuSex" name="s_student.stuSex">
							<option value="-1">请选择性别...</option><!--默认值.c标签之后进行便利，studentaction获取s_sexDataDicList-->
							<c:forEach var="sexData" items="${s_sexDataDicList }">
								<option value="${sexData.ddValue }" ${s_student.stuSex==sexData.ddValue?'selected':'' }>${sexData.ddValue }</option><!-- 查询回调值相等则选中；显示的值 -->>
							</c:forEach>
						</select>
					</td>
					<td>&nbsp;</td>
					<td><lable>民族：</lable></td>
					<td>
						<select id="s_stuNation" name="s_student.stuNation">
							<option value="-1">请选择民族...</option>
							<c:forEach var="nationData" items="${s_nationDataDicList }">
								<option value="${nationData.ddValue }" ${s_student.stuNation==nationData.ddValue?'selected':'' }>${nationData.ddValue }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td><lable>所在年级：</lable></td>
					<td>
						<select id="s_gradeId" name="s_student.gradeId" onchange="loadClassInfo()">
							<option value="-1">请选择年级...</option>
							<c:forEach var="grade" items="${s_gradeList }">
								<option value="${grade.gradeId }" ${s_student.gradeId==grade.gradeId?'selected':'' }>${grade.gradeName }</option>
							</c:forEach>
						</select>
					</td>
					<td>&nbsp;</td>
					<td><lable>所在班级：</lable></td>
					<td>
						<select id="s_classId" name="s_student.classId">
							<option value="-1">请选择班级...</option>
							<c:forEach var="c" items="${s_classList }">
								<option value="${c.classId }" ${s_student.classId==c.classId?'selected':'' }>${c.className }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr> 
					<td><lable>出生日期：</lable></td>
					<td colspan="4">            
					       <!--开头引入时间控件，占四列--my97时间控件main.jsp引入，这里直接调用CLASS,onclik事件-->
						<input type="text" id="s_b_stuBirthday" name="s_student.s_b_stuBirthday" value="${s_student.s_b_stuBirthday }" class="Wdate" onClick="WdatePicker()"/>&nbsp;&nbsp;到&nbsp;&nbsp;
						<input type="text" id="s_e_stuBirthday" name="s_student.s_e_stuBirthday" value="${s_student.s_e_stuBirthday }" class="Wdate" onClick="WdatePicker()"/>
					</td>
				</tr>
				<tr>
					<td><lable>入学时间：</lable></td>
					<td colspan="4">
						<input type="text" id="s_b_stuRxsj" name="s_student.s_b_stuRxsj" value="${s_student.s_b_stuRxsj }" class="Wdate" onClick="WdatePicker()"/>&nbsp;&nbsp;到&nbsp;&nbsp;
						<input type="text" id="s_e_stuRxsj" name="s_student.s_e_stuRxsj" value="${s_student.s_e_stuRxsj }" class="Wdate" onClick="WdatePicker()"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<button class="btn btn-primary" type="submit" >查询</button>
					</td>
					<td>&nbsp;</td>
					<td colspan="2">
						<button class="btn btn-primary" type="button" onclick="resetValue()">重置</button>
					</td>
				</tr>
			</table>
		</form>
		<button class="btn btn-mini btn-primary" type="button" style="float: right;margin-bottom: 5px;" onclick="javascript:window.location='student!preSave'">添加学生</button>
	</div>
	<div class="data_content">
		<table class="table table-bordered table-hover">
			<tr>
				<th>序号</th>
				<th>学号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>出生日期</th>
				<th>入学时间</th>
				<th>所在班级</th>
				<th>所在年级</th>
				<th>操作</th>
			</tr>
			<c:forEach var="student" items="${studentList }" varStatus="status">
				<tr>
				<!--写对应的属性-->
					<td>${status.index+1 }</td>
					<td>${student.stuNo }</td>
					<td>${student.stuName }</td>
					<td>${student.stuSex }</td>
					<td><fmt:formatDate value="${student.stuBirthday }" type="date" pattern="yyyy-MM-dd"/></td><!--日期格式的转化-->
					<td><fmt:formatDate value="${student.stuRxsj }" type="date" pattern="yyyy-MM-dd"/></td>
					<td>${student.className }</td>
					<td>${student.gradeName }</td>
					<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='student!show?studentId=${student.studentId}'">查看详细信息</button>&nbsp;&nbsp;<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='student!preSave?studentId=${student.studentId}'">修改</button>&nbsp;&nbsp;<button class="btn btn-mini btn-danger" type="button" onclick="studentDelete('${student.studentId}')">删除</button></td>
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