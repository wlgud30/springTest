<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
jQuery.fn.serializeObject = function() { 
    var obj = null; 
    try { 
        if(this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) { 
            var arr = this.serializeArray(); 
            if(arr){ obj = {}; 
            jQuery.each(arr, function() { 
                obj[this.name] = this.value; }); 
            } 
        } 
    }catch(e) { 
        alert(e.message); 
    }finally {} 
    return obj; 
  }

	function Join(){
		const serializedValues2 = $('#joinForm').serializeObject()
		$.ajax({
			type : 'post',
			data : JSON.stringify(serializedValues2),
			url : "/User/Join.ji",
			dataType : "json",
			contentType : "application/json; charset=UTF-8",
			success : function(data){
				if(data.cnt==1){
					alert("회원가입 성공");
				}else{
					alert("실패");
				}
				
			},
	error : function(error){
		console.log(error);
	}
	});
	}
</script>
<body>
	<form id="joinForm">
		<div>
			ID : <input type="text" name="u_id" id="u_id">
		</div>
		<div>
			PW : <input type="password" name="u_pw" id="u_pw">
		</div>
		<div>
			EMAIL : <input type="text" name="u_email" id="u_email">
		</div>
		<div>
			NAME : <input type="text" name="u_nm" id="u_nm">
		</div>
		<div>
			BIRTH : <input type="text" name="u_birth" id="u_birth">
		</div>
		<div>
			SEX : <input type="text" name="u_sex" id="u_sex">
		</div>
	</form>
	<button onclick="Join()">회원가입</button>
</body>
</html>