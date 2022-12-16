<%@page import="java.util.Vector"%>
<%@page import="dto.ManagerDTO"%>
<%@page import="dao.ManagerDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Utopia</title>
</head>
<body>
<%
	Vector<ManagerDTO> arr = (Vector<ManagerDTO>)session.getAttribute("data");
%>

<fieldset>
	<legend>매니저 추가</legend>
	<form action="manage-manager-add.do" method="post" >
		<input type="hidden" name="mode" value="add" />
		<input type="text" name="id" placeholder="아이디" required/>
		<input type="text" name="password" placeholder="비밀번호" required/>
		<input type="submit" value="추가" />
	</form>
	<form action="manage-manager-delete.do" method="post" >
		<input type="hidden" name="mode" value="del" />
		<div>
			<%
				for(ManagerDTO manager: arr) {
			%>
				<div>
					<input type="checkbox" name="id" value="<%= manager.getManagerId() %>" ><%= manager.getManagerId() %>
					아이디 : <%= manager.getManagerId() %>
					비밀번호 : <%= manager.getManagerPw() %>
				</div>	
			<%
				}
			%>
		</div>
		<input type="submit" value="삭제" />
	</form>
</fieldset>
	
	
</body>
</html>