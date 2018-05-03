<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>lms</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
<script src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script>
<script type="text/javascript">
$(function() {
	var emailChecked = false;
	
	$("#email").keypress(function(event){
		if(emailChecked) {
            $("#check-image").css("display","none");
            $("#check-button").css("display","initial");
            emailChecked = false;
        }
        return;
	});
	
    $("#check-button").click(function() {
        var email = $("#email").val();
        if (email == "") {
        	alert("이메일을 입력해주세요");
            return;
        }

        $.ajax({
            url : "${pageContext.servletContext.contextPath}/api/user/checkemail",
            type : "post",
            data : {"email": email},
            dataType:"json",
            success : function(response) {
                if(response.result != "success") {
                    console.log(response.message);
                    return;
                }
                
                if(response.data == "exist") {
                    alert("이미 사용 중인 이메일 입니다.");
                    $("#email").val("").focus();
                    return;
                }
                
                $("#check-image").css("display","initial");
                $("#check-button").css("display","none");
                emailChecked = true;
                return;
            },
            error : function(xhr, status, e) {
                console.error(status + "+" + e);
            }
        });
    });
    
    $("#join-form").submit(function(event){
    	if(emailChecked == false) {
    		event.preventDefault();
    		alert("이메일을 체크하세요.")
    		return;
    	}
    	
    	if($("#agree-prov").is(":checked") == false) {
    		event.preventDefault();
    		alert("약관에 동의해주세요.")
    		return;
    	}
    });
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="user">
				<form:form
					modelAttribute="user"
					id="join-form"
					name="joinForm"
					method="post"
					action="${pageContext.servletContext.contextPath }/user/join">
					
					<label class="block-label" for="name"><spring:message code="name" text="이름"/></label>
					<input id="name" name="name" type="text" value="${user.name }">
                    <p style="padding:0; font-weight:bold; text-align:left; color:#F00;">
                        <form:errors path="name"/>
                    </p>

					<label class="block-label" for="email">이메일</label>
					<form:input id="email" path="email"/>
					<img id="check-image" src="${pageContext.servletContext.contextPath }/assets/images/email-check.png" style="display:none"/>
					<input id="check-button" type="button" value="중복체크" style="display:;">
					<p style="padding:0; font-weight:bold; text-align:left; color:#F00;">
                        <form:errors path="email"/>
                    </p>
					
					<label class="block-label">패스워드</label>
					<form:password path="password" />
                    <spring:hasBindErrors name="user">
                       <c:if test="${errors.hasFieldErrors('password') }">
                         <p style="padding:0; text-align:left; color:red;">
                            <strong>
                                <spring:message 
                                  code="${errors.getFieldError( 'password' ).codes[0] }" 
                                  text="${errors.getFieldError( 'password' ).defaultMessage }"/>
                            </strong>
                         </p>
                       </c:if>
                    </spring:hasBindErrors>
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="FEMALE" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="MALE">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form:form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>