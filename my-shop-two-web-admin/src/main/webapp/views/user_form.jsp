<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--springMVC表单标签库--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>我的商城</title>
    <jsp:include page="../includes/header.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/nav.jsp"/>
    <jsp:include page="../includes/menu.jsp"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                用户管理
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">${tbUser.id==null?"新增":"编辑"}用户</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <!-- Horizontal Form -->
            <div class="box box-info">
                <div class="box-header with-border">
                    <h3 class="box-title">${tbUser.id==null?"新增":"编辑"}用户</h3>
                </div>
                <!-- /.box-header -->
                <c:if test="${result != null}">
                    <div class="alert alert-${result.status==200?'success':'warning'} alert-dismissible">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                        <h4><i class="icon fa fa-warning"></i>${result.message}</h4>
                    </div>
                </c:if>
                <!-- form start -->
                <%--springMVC表单标签库--%>
                <form:form id="inputForm" cssClass="form-horizontal" action="/user/save" method="post" modelAttribute="tbUser">
                    <form:hidden path="id"/>
                    <div class="box-body">
                        <div class="form-group">
                            <label for="username" class="col-sm-2 control-label">用户名</label>

                            <div class="col-sm-10">
                                <%-- path="username" 等同于id,name,value都是等于usernmae--%>
                                <form:input cssClass="form-control required" path="username" placeholder="请输入用户名"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">密码</label>

                            <div class="col-sm-10">
                                <form:input cssClass="form-control required" path="password" placeholder="请输入密码"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="phone" class="col-sm-2 control-label">手机</label>

                            <div class="col-sm-10">
                                <form:input cssClass="form-control required mobile" path="phone" placeholder="请输入手机号"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label">邮箱</label>

                            <div class="col-sm-10">
                                <form:input cssClass="form-control required email" path="email" placeholder="请输入邮箱"/>
                            </div>
                        </div>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer">
                        <button type="button" class="btn btn-default" onclick="history.go(-1)">返回</button>
                        <button type="submit" class="btn btn-info pull-right">提交</button>
                    </div>
                    <!-- /.box-footer -->
                </form:form>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <jsp:include page="../includes/copyright.jsp"/>
</div>
<!-- ./wrapper -->
<jsp:include page="../includes/footer.jsp"/>
<script>
</script>
</body>
</html>
