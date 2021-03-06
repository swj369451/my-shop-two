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
                <li><a href="#"><i class="fa fa-dashboard"></i> 内容管理</a></li>
                <li class="active">内容列表</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <!-- search -->
            <div class="box box-info box-search" style="display: none;">
                <div class="box-header with-border">
                    <h3 class="box-title">高级搜索</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <div class="row form-horizontal" style="margin-top: 20px">
                        <div class="col-xs-3">
                            <div class="form-group">
                                <label for="title" class="col-sm-2 control-label">标题</label>

                                <div class="col-sm-10">
                                    <input id="title" class="form-control" type="text" name="title" placeholder="请输入标题">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-3">
                            <div class="form-group">
                                <label for="subTitle" class="col-sm-2 control-label">子标题</label>

                                <div class="col-sm-10">
                                    <input id="subTitle" class="form-control" type="text" name="subTitle"
                                           placeholder="请输入子标题">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-3">
                            <div class="form-group">
                                <label for="titleDesc" class="col-sm-2 control-label">标题描述</label>

                                <div class="col-sm-10">
                                    <input id="titleDesc" class="form-control" type="text" name="titleDesc"
                                           placeholder="请输入标题描述">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-10">
                                <button onclick="search()" class="btn btn-info pull-right">查询</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">内容列表</h3>
                        </div>
                        <div class="box-body">
                            <a href="/content/form" type="button" class="btn btn-sm btn-default"><i
                                    class="fa fa-search"></i>新增</a>&nbsp;&nbsp;&nbsp;
                            <button type="button" onclick="app.deleteMulti('/content/delete')"
                                    class="btn btn-sm btn-default"><i class="fa fa-trash"></i>批量删除
                            </button>&nbsp;&nbsp;&nbsp;
                            <a href="#" type="button" class="btn btn-sm btn-default"><i
                                    class="fa fa-download"></i>导入</a>&nbsp;&nbsp;&nbsp;
                            <a href="#" type="button" class="btn btn-sm btn-default"><i class="fa fa-upload"></i>导出</a>&nbsp;&nbsp;&nbsp;
                            <a href="#" type="button" class="btn btn-sm btn-primary"
                               onclick="$('.box-search').css('display')=='none'?$('.box-search').show('fast'):$('.box-search').hide('fast')">
                                <i class="fa fa-upload"></i>查询</a>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <c:if test="${result != null}">
                                <div class="alert alert-${result.status==200?'success':'warning'} alert-dismissible">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×
                                    </button>
                                    <h4><i class="icon fa fa-warning"></i>${result.message}</h4>
                                </div>
                            </c:if>
                            <table id="dataTable" class="table table-hover">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" class="minimal icheck_master"></th>
                                    <th>id</th>
                                    <th>所属分类</th>
                                    <th>标题</th>
                                    <th>副标题</th>
                                    <th>详情</th>
                                    <th>链接</th>
                                    <th>图片1</th>
                                    <th>图片2</th>
                                    <th>更新时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <jsp:include page="../includes/copyright.jsp"/>
</div>
<!-- 自定义模态框 -->
<sys:modal title="标题啊"/>
<!-- /.modal -->
<!-- ./wrapper -->
<jsp:include page="../includes/footer.jsp"/>
<script>

    var columns = [
        {
            "data": function (row, type, val, meta) {
                return '<th><input id="' + row.id + '" type="checkbox" class="minimal"></th>';
            }
        },
        {"data": "id"},
        {"data": "tbContentCategory.name"},
        {"data": "title"},
        {"data": "subTitle"},
        {"data": "titleDesc"},
        {
            "data": function (row, type, val, meta) {
                if(row.url == null){
                    return '';
                }
                var url = ' <a href="' + row.url + '" target="_blank">查看</a>';
                return url;
            }
        },
        {
            "data": function (row, type, val, meta) {
                if(row.pic == null){
                    return '';
                }
                var url = ' <a href="' + row.pic + '" target="_blank">查看</a>';
                return url;
            }
        },
        {
            "data": function (row, type, val, meta) {
                if(row.pic1 == null){
                    return '';
                }
                var url = ' <a href="' + row.pic1 + '" target="_blank">查看</a>';
                return url;
            }
        },
        {
            "data": function (row, type, val, meta) {
                return DateTime.format(row.updated, "yyyy-MM-dd HH:mm:ss");
            }
        },
        {
            "data": function (row, type, val, meta) {
                var detailUrl = "/content/detail?contentId=" + row.id;
                var deleteUrl = "/content/delete";
                var mes = "警告：你确定要删除";
                return '<button onclick="app.showDetail(\'' + detailUrl + '\')" type="button" class="btn btn-sm btn-default"><i class="fa fa-search"></i>查看</button>&nbsp;&nbsp;&nbsp;' +
                    '<a href="/content/form?contentId=' + row.id + '" type="button" class="btn btn-sm btn-primary"><i class="fa fa-edit"></i>编辑</a>&nbsp;&nbsp;&nbsp;' +
                    '<button onclick="app.deleteSingle(\''+deleteUrl+'\','+row.id+',\'警告：你确认要删除此内容\')" type="button" class="btn btn-sm btn-danger"><i class="fa fa-remove"></i>删除</button>';
            }
        },
    ];
    var dataTables = app.initDataTables("/content/page", columns);
    function search() {
        var param = {
            "title": $('#title').val(),
            "subTitle": $('#subTitle').val(),
            "titleDesc": $('#titleDesc').val()
        };
        dataTables.settings()[0].ajax.data = param;
        dataTables.ajax.reload();
    }
</script>
</body>
</html>
