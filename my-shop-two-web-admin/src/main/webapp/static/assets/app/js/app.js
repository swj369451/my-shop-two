var app = function () {
    //bugsm var _icheck 在handleInitICheck中已经初始化了可是getCheckBox调用的时候显示为空所以不得不在定义的时候就初始化他
    var checkAllBtn;
    var checkBox ;
    var checkedIdArray;
    /**
     * 私有方法，初始化ICheck
     */
    var handleInitICheck = function () {
        //input[type="checkbox"].minimal  选择所有的input标签，类型是checkbox，并且类中有minimal
        //iCheck for checkbox and radio inputs
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });
        //选中主要的checkbox和被控制的checkbox
        checkAllBtn = $('input[type="checkbox"].minimal.icheck_master');
        checkBox = $('input[type="checkbox"].minimal');
    };

    //全选判断
    var handleCheckBoxAll = function () {
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

    //多选删除
    var handleDeleteMulti = function (url) {
        //初始化数组
        checkedIdArray = new Array();
        //判断是否选中并添加id到数组中
        checkBox.each(function () {
            var _id = $(this).attr('id');
            if(_id != null && _id != 'undefine' && $(this).is(":checked")){
                checkedIdArray.push(_id)
            }
        });
        if(checkedIdArray.length === 0){
            $("#modal-message").html("请至少选择一项");
            $('#modal-save').bind('click',function () {
                $("#modal-default").modal('hide');
            });
        }else{
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
                            console.log(data);
                            if(data.status === 200){
                                //    成功执行
                                window.location.reload();
                            }else{
                                //    执行错误
                                $("#modal-message").html(data.message);
                                $('#modal-save').unbind('click');
                                $('#modal-save').bind('click',function () {
                                    $("#modal-default").modal('hide');
                                });
                                $("#modal-default").modal('show');
                            }
                        }
                    });
                },500);
                $("#modal-default").modal('hide');
            });
        }
        //显示模态框
        $("#modal-default").modal('show');
    };

    //DataTables
    var handleInitDateTables = function (url,columns) {
        $('#dataTable').DataTable({
            "lengthChange": false,
            "info": true,
            "ordering": false,
            "searching": false,
            "processing": true,
            "serverSide": true,
            "ajax": {
                "url": url,

            },
            "columns":columns,
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
            "drawCallback": function( settings ) {
                handleInitICheck();
                handleCheckBoxAll();
            }
        })
    };

    //查看详情
    var handleShowDetail = function (url) {
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

    return{
        init:function () {
            handleInitICheck();
            handleCheckBoxAll();
        },
        getCheckBox:function () {
            return checkBox;
        },
        deleteMulti:function (url) {
            handleDeleteMulti(url);
        },
        initDataTables:function (url,columns) {
            handleInitDateTables(url,columns);
        },
        showDetail:function (url) {
          handleShowDetail(url);
        }
    }
}();

//导入的时候就生效
$(document).ready(function () {
    app.init();
});