<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function() {
		$("input[type=password]").keypress(function(e) {
			if (e.keyCode == 13) {
				login();
			}
		});
	})
	function login() {
		$.ajax({
			type : 'post',
			data : JSON.stringify({"u_id" : $("#u_id").val(),"u_pw" : $("#u_pw2").val()}),
			url : "/User/Login.ji",
			dataType : "json",
			contentType : "application/json; charset=UTF-8",
			 beforeSend : function(xhr)
             {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
                 xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
             },
			success : function(data) {
				if (data.cnt == 1) {
					$("#u_pw").val(data.pw)
					$("#frm").submit();
				} else {
					alert(data.msg);
				}

			},
			error : function(error) {
				console.log(error);
			}
		});
	}
</script>

<body>
	<c:url value="/login" var="loginUrl" />
	<form:form name = 'f' id="frm" action="${loginUrl}" method="post">
		<div>
			ID : <input type="text" name="u_id" id="u_id">
		</div>
		<div>
			PW : <input type="password" name="u_pw2" id="u_pw2">
			<input type="hidden" name="u_pw" id = "u_pw">
		</div>
	</form:form>
	<sec:authorize access="isAnonymous()">
		<button onclick="login()">로그인</button>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<form:form action="${pageContext.request.contextPath}/logout" method="POST">
			<input type="submit" value="로그아웃" />
		</form:form>
	</sec:authorize>
	<sec:authorize access="isAnonymous()">
		<button onclick="location.href='/User/JoinPage.ji'">회원가입</button>
	</sec:authorize>
</body>
</html>