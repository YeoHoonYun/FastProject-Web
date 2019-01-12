<%--
  Created by IntelliJ IDEA.
  User: cjswo
  Date: 2019-01-09
  Time: 오후 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글목록 페이지</title>
    <!-- 합쳐지고 최소화된 최신 CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h1 class="text-center"><a href="/">테스트 페이지</a></h1>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">글번호</th>
            <th scope="col">제목</th>
            <th scope="col">ID</th>
            <th scope="col">등록일</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.list}" var="board">
            <tr>
                <th scope="row">${board.num}</th>
                <td><a href="/board/detail/${board.num}">${board.title}</a></td>
                <td>${board.id}</td>
                <td>${board.localDateTime}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <hr/>
    <a class="btn btn-default" href="write">글쓰기</a>
    <%--<div class="text-center"></div>--%>
    <%--<ul class="pagination">--%>
        <%--<li><a href="#">1</a></li>--%>
        <%--<li><a href="#">2</a></li>--%>
        <%--<li><a href="#">3</a></li>--%>
        <%--<li><a href="#">4</a></li>--%>
        <%--<li><a href="#">5</a></li>--%>
    <%--</ul>--%>
</div>
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script type="text/javascript" src="http:code.jquery.com/jquery-1.8.3.min.js"></script>
</body>
</html>