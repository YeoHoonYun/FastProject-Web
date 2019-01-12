<%--
  Created by IntelliJ IDEA.
  User: cjswo
  Date: 2019-01-07
  Time: 오후 3:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글수정 페이지</title>
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
    <form method="post" action="/board/update/${board.num}">
        <div class="form-group">
            <label>제목</label>
            <input type="title" class="form-control" name="title" placeholder="제목" value="${board.title}">
        </div>
        <div class="form-group">
            <label>내용</label>
            <textarea class="form-control" name="content" rows="3"  placeholder="내용">${board.content}</textarea>
        </div>
        <div class="form-group">
            <label class="sr-only">ID</label>
            <input type="id" class="form-control" name="id" placeholder="ID" value="${board.id}">
        </div>
        <button type="submit" class="btn btn-default">수정</button>
    </form>
</div>
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script type="text/javascript" src="http:code.jquery.com/jquery-1.8.3.min.js"></script>
</body>
</html>
