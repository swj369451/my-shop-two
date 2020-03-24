var app = function () {
    var checkAllBtn;
    var checkBox;
    var checkedIdArray;

    var defaultDropzoneConfig = {
        url: "", // 文件提交地址
        method: "post",  // 也可用put
        paramName: "file", // 默认为file
        maxFiles: 1,// 一次性上传的文件数量上限
        maxFilesize: 2, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        //previewsContainer:"#preview", // 上传图片的预览窗口
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传" + this.maxFiles + "个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是" + this.acceptedFiles,
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消",
    };

    /**
     * 初始化ICheck
     */
    var handlerInitICheck = function () {
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass: 'iradio_minimal-blue'
        });
        //选中主要的checkbox和被控制的checkbox
        checkAllBtn = $('input[type="checkbox"].minimal.icheck_master');
        checkBox = $('input[type="checkbox"].minimal');
    };

    /**
     * 全选按钮点击事件
     */
    var handlerCheckBoxAll = function () {
        checkAllBtn.on("ifClicked", function (e) {
            if (e.target.checked) {
                //    返回true表示未选中
                checkBox.iCheck("uncheck");
            } else {
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
            if (_id != null && _id != 'undefine' && $(this).is(":checked")) {
                checkedIdArray.push(_id)
            }
        });
        //判断是否有选中
        if (checkedIdArray.length === 0) {
            //未选中任何一项
            $("#modal-message").html("请至少选择一项");
            $('#modal-save').bind('click', function () {
                $("#modal-default").modal('hide');
            });
        } else {
            //已有选中
            $("#modal-message").html("您确定要删除吗");
            $('#modal-save').bind('click', function () {
                setTimeout(function () {
                    //删除的ajax请求
                    $.ajax({
                        "url": url,
                        "type": "POST",
                        "async": false,
                        "data": {"ids": checkedIdArray.toString()},
                        "dataType": "JSON",
                        "success": function (data) {
                            //在返回的数据中进行判断是否成功，并解除之前的模态框绑定
                            $('#modal-save').unbind('click');
                            $("#modal-message").html(data.message);
                            if (data.status === 200) {
                                //    成功执行
                                // window.location.reload();
                                $('#modal-save').bind('click', function () {
                                    $("#modal-default").modal('hide');
                                    window.location.reload();
                                });
                            } else {
                                //    执行错误
                                $('#modal-save').bind('click', function () {
                                    $("#modal-default").modal('hide');
                                });

                            }
                            $("#modal-default").modal('show');
                        }
                    });
                }, 500);
                $("#modal-default").modal('hide');
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
    var handlerInitDateTables = function (url, columns) {
        var dataTables = $('#dataTable').DataTable({
            "lengthChange": false,
            "info": true,
            "ordering": false,
            "searching": false,
            "processing": true,
            "serverSide": true,
            "ajax": {
                "url": url,
                // "data": {
                //     args1: "username"
                // }
            },
            "columns": columns,
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
            "drawCallback": function (settings) {
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

    /**
     * 根据id删除
     * @param url 请求路径
     */
    var handlerDeleteData = function (url) {
        $("#modal-default").modal("hide");
        //判断是否有id
        if (checkedIdArray.length > 0) {
            // 发起异步删除请求
            setTimeout(function () {
                $.ajax({
                    url: url,
                    type: 'post',
                    data: {'ids': checkedIdArray.toString()},
                    dataType: 'JSON',
                    success: function (data) {
                        //解除确认按钮的绑定
                        $("#modal-save").unbind("click");
                        if (data.status === 200) {
                            //删除成功
                            $("#modal-save").bind("click", function () {
                                window.location.reload();
                            })
                        } else {
                            //删除失败
                            $("#modal-save").bind("click", function () {
                                $("#modal-default").modal("hide");
                            })
                        }
                        //设置处理信息
                        $("#modal-message").html(data.message);
                        $("#modal-default").modal("show");

                    }
                })
            }, 500)
        }
    };

    /**
     * 初始化ztree
     * 必须加载一个模态框
     * 后台通过父类目id查询所有子类id
     * @param url 后台地址
     * @param autoParam 向后端传的数据，数组方式
     * @param callback 得到选中的类目时执行的回调函数
     */
    var handlerInitZtree = function (url, autoParam, callback) {
        var setting = {
            view: {
                //关闭多选
                selectedMulti: false
            },
            async: {
                enable: true,
                url: url,
                //当点击一个类目的时候自动传向后端的参数
                autoParam: autoParam,
            }
        };
        //初始化
        $.fn.zTree.init($("#myTree"), setting);
        //设置模态框
        $("#modal-save").bind("click", function () {
            var myTree = $.fn.zTree.getZTreeObj("myTree");
            var nodes = myTree.getSelectedNodes();
            if (nodes.length == 0) {
                // 未选择
                alert("请先选择一个节点");
            } else {
                // 已选择
                callback(nodes);
            }

        })
    };

    /**
     * 初始化dropzone
     */
    var handlerInitDropzone = function (opts) {
        //关闭dropzone的自动发现
        Dropzone.autoDiscover = false;
        //让opts继承defaultDropzoneConfig的属性,extend()的第一个形参为true时候进行深拷贝，否则浅拷贝
        $.extend(defaultDropzoneConfig, opts);
        var myDropzone = new Dropzone("#" + defaultDropzoneConfig.id, defaultDropzoneConfig);
    };
    var handlerDeleteSingle = function (url, id, mes) {
        //设置为可选参数，当为空的时候设置为null
        if (!mes) mes = null;

        //将id放入数组中和批量删除通用
        checkedIdArray = new Array();
        checkedIdArray.push(id);

        $("#modal-message").html(mes == null ? "你确定要删除吗？" : mes);
        $("#modal-default").modal("show");
        $("#modal-save").bind("click", function () {
            handlerDeleteData(url)
        });

    };
    return {
        init: function () {
            handlerInitICheck();
        },
        deleteMulti: function (url) {
            handlerDeleteMulti(url);
        },
        initDataTables: function (url, columns) {
            return handlerInitDateTables(url, columns);
        },
        showDetail: function (url) {
            handlerShowDetail(url);
        },
        initZtree: function (url, autoParam, callback) {
            handlerInitZtree(url, autoParam, callback);
        },
        initDropzone: function (opts) {
            handlerInitDropzone(opts);
        },
        deleteSingle: function (url, id, mes) {
            handlerDeleteSingle(url, id, mes);
        }
    }
}();

//导入的时候就生效
$(document).ready(function () {
    app.init();
});