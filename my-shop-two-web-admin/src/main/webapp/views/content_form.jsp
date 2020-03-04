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
    <!-- ztree -->
    <link rel="stylesheet" href="/static/assets/plugins/jquery-ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <!-- dropzone -->
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/dropzone.css" type="text/css">
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/min/basic.min.css" type="text/css">
    <!-- wangEditor -->
    <link rel="stylesheet" href="/static/assets/plugins/wangEditor/wangEditor.min.css" type="text/css">


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
                <li class="active">${tbContent.id==null?"新增":"编辑"}内容</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <!-- Horizontal Form -->
            <div class="box box-info">
                <div class="box-header with-border">
                    <h3 class="box-title">${tbContent.id==null?"新增":"编辑"}用户</h3>
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
                <form:form id="inputForm" cssClass="form-horizontal" action="/content/save" method="post"
                           modelAttribute="tbContent">
                    <form:hidden path="id"/>
                    <div class="box-body">
                        <div class="form-group">
                            <label for="categoryId" class="col-sm-2 control-label">父级类目</label>

                            <div class="col-sm-10">
                                    <%-- path="username" 等同于id,name,value都是等于usernmae--%>
                                <form:hidden path="categoryId"/>
                                <input type="text" class="form-control required" placeholder="请输入父级"
                                       id="categoryName" readonly="readonly"
                                       data-toggle="modal" data-target="#modal-default">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="title" class="col-sm-2 control-label">标题</label>

                            <div class="col-sm-10">
                                <form:input cssClass="form-control required" path="title" placeholder="请输入标题"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="subTitle" class="col-sm-2 control-label">子标题</label>

                            <div class="col-sm-10">
                                <form:input cssClass="form-control required" path="subTitle" placeholder="请输入子标题"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="titleDesc" class="col-sm-2 control-label">标题描述</label>

                            <div class="col-sm-10">
                                <form:input cssClass="form-control required" path="titleDesc" placeholder="请输入标题描述"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="url" class="col-sm-2 control-label">链接</label>

                            <div class="col-sm-10">
                                <form:input cssClass="form-control required" path="url" placeholder="请输入链接"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pic" class="col-sm-2 control-label">图片1</label>

                            <div class="col-sm-10">
                                <form:input cssClass="form-control required" path="pic" placeholder="请输入图片1"/>
                                <div id="dropz" class="dropzone">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pic2" class="col-sm-2 control-label">图片2</label>

                            <div class="col-sm-10">
                                <form:input cssClass="form-control required" path="pic2" placeholder="请输入图片1"/>
                                <div id="dropz2" class="dropzone">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="content" class="col-sm-2 control-label">详情</label>

                            <div class="col-sm-10">
                                <form:hidden cssClass="form-control required" path="content" placeholder="请输入详情"/>
                                <div id="editor">${tbContent.content}</div>
                            </div>
                        </div>

                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer">
                        <button type="button" class="btn btn-default" onclick="history.go(-1)">返回</button>
                        <button id="submit" type="submit" class="btn btn-info pull-right">提交</button>
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
<!--ztree-->
<script type="text/javascript" src="/static/assets/plugins/jquery-ztree/js/jquery.ztree.core-3.5.min.js"></script>
<!-- dropzone -->
<script type="text/javascript" src="/static/assets/plugins/dropzone/min/dropzone.min.js"></script>
<!-- wangEditor -->
<script type="text/javascript" src="/static/assets/plugins/wangEditor/wangEditor.min.js"></script>

<script>
    $(function () {
        app.initZtree("/content/category/treeDate", ['id'], function (nodes) {
            var node = nodes[0];
            $('#categoryId').val(node.id);
            $('#categoryName').val(node.name);
            $('#modal-default').modal('hide');
        });


    });
    //上传文件插件初始化
    app.initDropzone({
        id: "dropz",
        url: "/upload",
        paramName: "dropFile",
        init: function () {
            this.on("success", function (file, data) {
                // 上传成功触发的事件
                $("#pic").val(data.fileName);
            });
        }
    });
    //初始化Dropzone
    app.initDropzone({
        id: "dropz2",
        url: "/upload",
        paramName: "dropFile",
        init: function () {
            this.on("success", function (file, data) {
                // 上传成功触发的事件
                $("#pic2").val(data.fileName);
            });
        }
    });
    //wangEditor
    var E = window.wangEditor;
    var editor = new E('#editor');
    // 或者 var editor = new E( document.getElementById('editor') )
    // 配置图片上传
    editor.customConfig.uploadImgServer = '/upload';
    editor.customConfig.uploadFileName = 'editFile';
    editor.create();

    //配置富文本到input框
    var content = $('#content');
    $("#submit").bind("click", function () {
        content.val(editor.txt.html());
    });

</script>
</body>
</html>
