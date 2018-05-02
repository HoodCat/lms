<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="rent-result">
				<p class="success">
                    <c:choose>
                        <c:when test="${reserveResult}">
                                                        정상적으로 예약이 되었습니다.
                        </c:when>
                        <c:otherwise>
                                                        예약이 불가능합니다.
                        </c:otherwise>
                    </c:choose>
					<br><br>
					<a href="${pageContext.servletContext.contextPath }">목록으로 돌아가기</a>
				</p>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>