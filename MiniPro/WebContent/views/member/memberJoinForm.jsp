<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입폼</title>
<script type="text/javascript">
	function formCheck() {

		if (frm.mPassword.value != frm.mPassword1.value) {
			alert("패스워드가 일치하지 않습니다.")
			frm.mPassword1.value = null;
			frm.mPassword1.focus();
			return false;
		}
		
		if (frm.mName.value == "") {
			alert("이름은 반드시 입력해야 합니다.")
			frm.mName.focus();
			return false;
		}

		if (frm.mPassword.value == "") {
			alert("패스워드는 반드시 입력해야 합니다..")
			frm.mPassword.focus();
			return false;
		}
		
		return true;
	}
	
	function idCheck(str) {
		var url = "idCheck.do?mid=" + str;
		if(str == "") {
			alter("아이디를 입력하세요");
			frm.mId.focus();
		}else {
		window.open(url, "아이디 중복체크","width=600, height=200, top=100, left=100");
		}
	}
</script>
</head>
<jsp:include page="../common/menu.jsp" />
<body>
	<div align="center">
		<div>
			<h1>회원가입</h1>
		</div>
		<div>
			<form id="frm" name="frm" onsubmit="return formCheck()" action="memberJoin.do" method="post">
				<div>
					<table border="1">
						<tr>
							<th width="150">아 이 디</th>
							<td width="400"><input type="text" id="mId" name="mId" size="30" required="required"> &nbsp;&nbsp;
								<button type="button" onclick="idCheck(mId.value)">중복체크</button></td>
						</tr>
						<tr>
							<th width="150">패스워드</th>
							<td width="400"><input type="password" id="mPassword" name="mPassword" size="30" required="required"></td>
						</tr>
						<tr>
							<th width="150">패스워드 확인</th>
							<td width="400"><input type="password" id="mPassword1" name="mPassword1" size="30" required="required"></td>
						</tr>
						<tr>
							<th width="150">이름</th>
							<td width="400"><input type="text" id="mName" name="mName" size="30" required="required"></td>
						</tr>
					</table>
				</div>
				<br /> <input type="submit" value="회원가입">&nbsp;&nbsp;&nbsp;
				<input type="reset" value="취소">
			</form>
		</div>
	</div>
</body>
</html>