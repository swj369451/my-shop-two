<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>我的商城</title>
    <jsp:include page="../includes/header.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<table id="dataTable" class="table table-hover">
    <tbody>
        <tr>
            <th>用户名:</th>
            <th>${tbUser.username}</th>
        </tr>
        <tr>
            <th>手机:</th>
            <th>${tbUser.phone}</th>
        </tr>
        <tr>
            <th>邮箱:</th>
            <th>${tbUser.email}</th>
        </tr>
        <tr>
            <th>创建日期:</th>
            <th><fmt:formatDate value="${tbUser.created}" pattern="yyyy-MM-dd HH:mm:ss"/> </th>
        </tr>
        <tr>
            <th>创建日期:</th>
            <th><fmt:formatDate value="${tbUser.updated}" pattern="yyyy-MM-dd HH:mm:ss"/> </th>
        </tr>
    </tbody>
</table>
<!-- ./wrapper -->
<jsp:include page="../includes/footer.jsp"/>
<script>
</script>
</body>
</html>
