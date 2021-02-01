<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 실패</title>
</head>
<jsp:include page="../common/menu.jsp" />
<body>
<div align="center">
<h3>${param.mId } 님 로그인에 실패했습니다.</h3> <!-- 디비에서 값을 못 가져오기 때문에 로그인실패는 vo.mId가 아니라 param.mId로 적어야 함-->
</div>
</body>
</html>