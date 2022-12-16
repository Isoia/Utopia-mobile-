<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/Utopia/css/index.css">
</head>
<body>
	<form class="mt-5 text-center" action="manage-login.do" method="post">
		<fieldset>
			<legend>매니저 로그인</legend>
			<div><input type="text" name="id" placeholder="아이디" /></div>
			<div><input type="password" name="pw" placeholder="패스워드" /></div>
			<div><input type="submit" value="로그인" /></div>
		</fieldset>
	</form>
</body>
</html>