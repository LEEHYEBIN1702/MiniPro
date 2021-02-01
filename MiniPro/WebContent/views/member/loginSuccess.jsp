<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공</title>
</head>
<jsp:include page="../common/menu.jsp" />
<body>
<div align="center">
<h3>${vo.mName } 님 로그인에 성공했습니다.</h3>
<h3>${vo.mAuth } 님 권한을 갖습니다.</h3>
</div>
</body>
</html>