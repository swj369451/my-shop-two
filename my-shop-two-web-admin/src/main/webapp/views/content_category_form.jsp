<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--springMVC表单标签库--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--自定义标签--%>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 内容管理</title>
    <jsp:include page="../includes/header.jsp"/>
    <%--ztree--%>
    <link rel="stylesheet" href="/static/assets/plugins/jquery-ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
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
                内容管理
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">${tbContent.id==null?"新增":"编辑"}分类</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <!-- Horizontal Form -->
            <div class="box box-info">
                <div class="box-header with-border">
                    <h3 class="box-title">${tbContent.id==null?"新增":"编辑"}分类</h3>
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
                <form:form id="inputForm" cssClass="form-horizontal" action="/content/category/save" method="post"
                           modelAttribute="tbContentCategory">
                    <form:hidden path="id"/>
                    <div class="box-body">
                        <div class="form-group">
                            <label for="categoryName" class="col-sm-2 control-label">父级</label>

                            <div class="col-sm-10">
                                    <%-- path="username" 等同于id,name,value都是等于usernmae--%>
                                    <%--<form:hidden path="parent.id"/>--%>
                                <input type="text" id="parentId" name="parent.id" value="${tbContentCategory.parent.id}">
                                <input type="text" class="form-control required" placeholder="请输入父级"
                                       id="categoryName" readonly="readonly"
                                       data-toggle="modal" data-target="#modal-default" value="${tbContentCategory.parent.name}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">分类名称</label>

                            <div class="col-sm-10">
                                <form:input cssClass="form-control required" path="name" placeholder="请输入分类名称"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="sortOrder" class="col-sm-2 control-label">分类排序</label>

                            <div class="col-sm-10">
                                <form:input cssClass="form-control required digits" path="sortOrder"
                                            placeholder="请输入分类排序"/>
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
<!-- 自定义模态框 -->
<sys:modal title="标题啊" message="<ul id='myTree' class='ztree'></ul>"/>
<!-- ./wrapper -->
<jsp:include page="../includes/footer.jsp"/>
<%--./ztree--%>
<script type="text/javascript" src="/static/assets/plugins/jquery-ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script>
    $(function () {
        app.initZtree("/content/category/treeDate", ['id'], function (nodes) {
            var node = nodes[0];
            $('#parentId').val(node.id);
            $('#categoryName').val(node.name);
            $('#modal-default').modal('hide');
        })
    })
</script>
</body>
</html>
