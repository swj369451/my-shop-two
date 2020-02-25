var app = function () {
    var checkAllBtn;
    var checkBox ;
    var checkedIdArray;

    /**
     * 初始化ICheck
     */
    var handlerInitICheck = function () {
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });
        //选中主要的checkbox和被控制的checkbox
        checkAllBtn = $('input[type="checkbox"].minimal.icheck_master');
        checkBox = $('input[type="checkbox"].minimal');
    };

    /**
     * 全选按钮点击事件
     */
    var handlerCheckBoxAll = function () {
        checkAllBtn.on("ifClicked",function (e) {
            if(e.target.checked){
                //    返回true表示未选中
                checkBox.iCheck("uncheck");
            }else{
                //    选中状态
                checkBox.iCheck("check");
            }
        });
    };

    /**
     * 多选删除操作
     * @param url
     */
    var handlerDeleteMulti = function (url) {
        //初始化数组
        checkedIdArray = new Array();
        //判断是否选中并添加id到数组中
        checkBox.each(function () {
            var _id = $(this).attr('id');
            if(_id != null && _id != 'undefine' && $(this).is(":checked")){
                checkedIdArray.push(_id)
            }
        });
        //判断是否有选中
        if(checkedIdArray.length === 0){
            //未选中任何一项
            $("#modal-message").html("请至少选择一项");
            $('#modal-save').bind('click',function () {
                $("#modal-default").modal('hide');
            });
        }else{
            //已有选中
            $("#modal-message").html("您确定要删除吗");
            $('#modal-save').bind('click',function () {
                setTimeout(function () {
                    //删除的ajax请求
                    $.ajax({
                        "url":url,
                        "type":"POST",
                        "async":false,
                        "data":{"userIds":checkedIdArray.toString()},
                        "dataType":"JSON",
                        "success":function (data) {
                            //在返回的数据中进行判断是否成功，并解除之前的模态框绑定
                            $('#modal-save').unbind('click');
                            $("#modal-message").html(data.message);
                            if(data.status === 200){
                                //    成功执行
                                // window.location.reload();
                                $('#modal-save').bind('click',function () {
                                    $("#modal-default").modal('hide');
                                    window.location.reload();
                                });
                            }else{
                                //    执行错误
                                $('#modal-save').bind('click',function () {
                                    $("#modal-default").modal('hide');
                                });

                            }
                            $("#modal-default").modal('show');
                        }
                    });
                },500);
                // $("#modal-default").modal('hide');
            });
        }
        //显示模态框
        $("#modal-default").modal('show');
    };

    /**
     * 初始化DataTables
     * @param url 查询所发起的地址
     * @param columns 列的数据
     * @returns {*|jQuery}
     */
    var handlerInitDateTables = function (url,columns) {
        var dataTables = $('#dataTable').DataTable({
            "lengthChange": false,
            "info": true,
            "ordering": false,
            "searching": false,
            "processing": true,
            "serverSide": true,
            "ajax": {
                "url": url,
                "data": {
                    args1: "username"
                }
            },
            "columns":columns,
            //国际化
            language: {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(有 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            //回调函数，每当翻一页的时候就执行icheck的样式更新
            "drawCallback": function( settings ) {
                handlerInitICheck();
                handlerCheckBoxAll();
            }
        });
        return dataTables;
    };

    /**
     * 显示详细信息
     * 向模态框中填充jsp的数据
     * @param url 请求路径
     */
    var handlerShowDetail = function (url) {
        $.ajax({
            url: url,
            type: 'get',
            dataType: 'html',
            success: function (data) {
                $('#modal-body').html(data);
                $('#modal-detail').modal('show');
            }
        });
    };

    var handlerDelete = function (url,id) {
        // setTimeout(function () {
            $.ajax({
                "url": url,
                "type": "POST",
                "data": {"userIds": id},
                success: function (data) {
                    if(data.status === 200){
                        window.location.reload();
                    }
                }
            });
        // },500);
    };

    return{
        init:function () {
            handlerInitICheck();
            handlerCheckBoxAll();
        },
        deleteMulti:function (url) {
            handlerDeleteMulti(url);
        },
        initDataTables:function (url,columns) {
            return handlerInitDateTables(url,columns);
        },
        showDetail:function (url) {
          handlerShowDetail(url);
        },
        deleteById:function (url,id) {
            handlerDelete(url,id);
        }
    }
}();

//导入的时候就生效
$(document).ready(function () {
    app.init();
});