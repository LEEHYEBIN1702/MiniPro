<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈</title>
<style>
.center {
   text-align: center;
}

.pagination {
  display: inline-block;
}

.pagination a {
  color: black;
  float: left;
  padding: 8px 16px;
  text-decoration: none;
  transition: background-color .3s;
}

.pagination a.active {
  background-color: #4CAF50;
  color: white;
}

.pagination a:hover:not(.active) {background-color: #ddd;}
</style>
<script>
function goPage(page) {
	location.href = '${pageContext.request.contextPath}/paging.do?pageNo='+page;
}
</script>
</head>
<jsp:include page="../common/menu.jsp" />

<body>
<div align="center">
<div>${vo }</div> 
<c:forEach var="vo" items="${list }">
<p>id:${vo.employeeId } / ${vo.firstName } / ${vo.lastName } / ${vo.salary }</p>
</c:forEach>
<jsp:include page="../common/paging.jsp"></jsp:include>
<h1>여기는 처음으로 접속했을 때 보여주는 페이지입니다.</h1>
</div>
</body>
</html>