<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>LMS</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/main.css"
  rel="stylesheet" type="text/css">
<script
  src="${pageContext.servletContext.contextPath}/assets/jquery/jquery-1.9.0.js"
  type="text/javascript"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
  <div id="container">
    <c:import url="/WEB-INF/views/include/header.jsp" />
    <div id="content">
      <div id="board">
        <form id="search_form"
          action="${pageContext.request.contextPath }" method="get">
          <input type="text" id="kwd" name="kwd" value="${kwd}">
          <input type="submit" value="찾기">
        </form>
        <table class="tbl-ex">
          <tr>
            <th>번호</th>
            <th>타이틀</th>
            <th>카테고리</th>
            <th>&nbsp;</th>
          </tr>
          <c:forEach items="${itemList}" var="item" varStatus="status">
            <tr>
              <td>${(totalCount - (5*page)) - status.index }</td>
              <td>${item.title}</td>
              <td>${item.category}</td>
              <td>
                <c:choose>
                  <c:when test="${item.status == 'LENDABLE'}">
                    <a href="${pageContext.servletContext.contextPath }/rent?itemNo=${item.no}" class="btn">대여</a> 
                  </c:when>
                  <c:when test="${item.status == 'LENT'}">
                    <a href="${pageContext.servletContext.contextPath }/reserve?itemNo=${item.no}" class="btn">예약</a>
                  </c:when>
                </c:choose>
              </td>
            </tr>
          </c:forEach>
        </table>
        <div class="pager">
          <ul>
            <c:if test="${page gt 0 }">
              <c:url value="/main" var="prevPage">
                <c:if test="${not empty kwd or not kwd eq '' }">
                  <c:param name="kwd" value="${kwd}" />
                </c:if>
                <c:param name="page" value="${page-1}" />
              </c:url>
              <li><a href="${prevPage}">◀</a></li>
            </c:if>
<%--             <c:forEach begin="0" end="${totalPage - 1}" var="no"> --%>
            <fmt:parseNumber var="mok" integerOnly="true" value="${page/5}"/>
            <c:set var="endPage" value="${(totalPage-1) < (mok*5 + 4) ? (totalPage-1) : (mok*5 + 4)}"/>
            <c:forEach begin="${mok*5}" end="${endPage}" var="no">
              <c:choose>
                <c:when test="${page == no}">
                  <li class="selected">${no+1}</li>
                </c:when>
                <c:otherwise>
                  <c:url value="/main" var="pageNo">
                    <c:if test="${not empty kwd or not kwd eq ''}">
                      <c:param name="kwd" value="${kwd}" />
                    </c:if>
                    <c:param name="page" value="${no}" />
                  </c:url>
                  <li><a href="${pageNo}">${no+1}</a></li>
                </c:otherwise>
              </c:choose>
            </c:forEach>
            <c:if test="${page lt totalPage-1 }">
              <c:url value="/main" var="nextPage">
                <c:if test="${not empty kwd or not kwd eq ''}">
                  <c:param name="kwd" value="${kwd}" />
                </c:if>
                <c:param name="page" value="${page+1}" />
              </c:url>
              <li><a href="${nextPage}">▶</a></li>
            </c:if>
          </ul>
        </div>
        <div class="bottom"></div>
      </div>
    </div>
    <c:import url="/WEB-INF/views/include/footer.jsp" />
  </div>
</body>
</html>