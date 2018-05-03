<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>lms</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
  href="${pageContext.request.contextPath }/assets/css/admin/rent.css"
  rel="stylesheet" type="text/css">
</head>
<body>
  <div id="container">
    <c:import url="/WEB-INF/views/admin/include/header.jsp" />
    <div id="wrapper">
      <div id="content">
        <table class="tbl-ex">
          <tr>
            <th>번호</th>
            <th>타이틀</th>
            <th>카테고리</th>
            <th>대여일</th>
            <th>반납일</th>
          </tr>
          <c:forEach items="${itemList}" var="item" varStatus="status">
            <tr>
              <td>${(totalCount - (5*page)) - status.index }</td>
              <td>${item.title}</td>
              <td>${item.category}</td>
              <td>${item.rentDate}</td>
              <td>${item.returnDate}</td>
            </tr>
          </c:forEach>
        </table>
        <div class="pager">
          <ul>
            <c:if test="${page gt 0 }">
              <li><a
                href="${pageContext.servletContext.contextPath }/admin/rent?page=${page-1}">◀</a></li>
            </c:if>
            <c:if test="${totalPage gt 0 }">
              <c:forEach begin="0" end="${totalPage - 1}" var="no">
                <c:choose>
                  <c:when test="${page == no}">
                    <li class="selected">${no+1}</li>
                  </c:when>
                  <c:otherwise>
                    <li><a
                      href="${pageContext.servletContext.contextPath}/admin/rent?page=${no}">${no+1}</a></li>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
            </c:if>
            <c:if test="${page lt totalPage-1 }">
              <li><a
                href="${pageContext.servletContext.contextPath}/admin/rent?page=${page+1}">▶</a></li>
            </c:if>
          </ul>
        </div>
      </div>
      <c:import url="/WEB-INF/views/admin/include/navigation.jsp" />
    </div>
  </div>
</body>
</html>