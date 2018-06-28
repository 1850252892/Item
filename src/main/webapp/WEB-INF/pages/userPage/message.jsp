<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>消息通知</title>
    <link type="text/css" rel="stylesheet" href="/css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="/css/bootstrap-table.css">
    <link type="text/css" rel="stylesheet" href="/css/bootstrap-table.css">
    <link type="text/css" rel="stylesheet" href="/css/font-awesome.css">
    <link type="text/css" rel="stylesheet" href="/css/myItem.css">

    <script type="text/javascript" src="/js/jquery-3.1.1.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap-table.js"></script>
    <script type="text/javascript" src="/js/bootstrap-table-zh-CN.js"></script>
    <link href="css/message.css" rel="stylesheet">
    <style>

    </style>
</head>

<body>
<div class="left-menu">
    <a href="/mainPage" style="display: block;"><H2>HLeB</H2></a><br>
    <%--<div class="line_1" style="width: 100%;height: 1px;background: #3e4659;"></div>--%>
    <%--<div class="line_2" style="height: 1px;width: 100%;background-color: #50596e;"></div>--%>
    <div class="menu-param"><a href="/admin/userMainPage">个人信息</a></div>
    <div class="menu-param"><a href="/admin/myItem">我的货架</a></div>
    <div class="menu-param"><a href="/admin/businessPage">交易记录</a></div>
    <div class="menu-param"><a href="/item/addItemPage">发布商品</a></div>
    <div class="menu-param"><a href="/admin/myCollectPage">我的收藏</a></div>
    <div class="menu-param"><a href="/admin/pending">待处理的</a></div>
    <div class="menu-param"><a href="/admin/mySubmitPage">我提交的</a></div>
    <div class="menu-param"><a href="/admin/message" style="background: rgba(248,244,255,0.3);">消息通知</a></div>
</div>
<div class="right-data">
    <table class="message_box" id="message_box">
    </table>
</div>

</body>

<script type="text/javascript">
    var users = $('#message_box').bootstrapTable({
        url: "/getMessage", //请求的后台连接，需要请求到json数据
        method: 'get', //请求方式
        striped: true, //是否显示行间隔色
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true, //是否显示分页（*）
        sortable: true, //是否启用排序
        sortOrder: "asc", //排序方式
        queryParams: function (param) {
            return {"state": null};
        },
        sidePagination: "client", //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1, //初始化加载第一页，默认第一页
        pageSize: 10, //每页的记录行数（*）
        pageList: [10, 25, 50, 100], //可供选择的每页的行数（*）
        showExport: true, //是否显示导出
        exportDataType: "basic", //basic', 'all', 'selected'.
        columns: [{
            field: 'type',
            title: '消息类型',
            formatter: 'type',
            width: '20%',
        }, {
            field: 'date',
            title: '时间',
            width: '25%',
        }, {
            field: 'message',
            title: '消息内容',
            editable: true,
            width: '35%',
        }, {
            field: "state",
            title: '消息状态',
            formatter: "state",
            width: '10%',
        }, {
            field: "path",
            title: '跳转',
            formatter: "path",
            width: '10%',
        }, {
            field: 'do',
            title: '操作',
            formatter: "options",
            events: "events",
            width: '200px',
        }],
        /* 修改后点勾那个按钮触发的方法 */
    });

    function type(value, row, index) {
        if (value == 0) return "交换信息";
        if (value == 1)  return "用户评论";
        if (value == 2)  return "其它";

    }

    function options(value, row, index) {
        return [
            '<button type="button" class="delete btn btn-default  btn-sm" style=" width:100%;">删除</button>',]
            .join('');
    }

    function state(value, row, index) {
        if (row.state == 0)
            return "未读";
        else if (row.state == 1)
            return "已读"
    }

    function path(value, row, index) {
        return "<a href='" + value + "'>前往</a>"
    }

    var events = window.operateEvents = {
        'click .delete': function (e, value, row, index) {
            if (!confirm("确认删除消息？"))
                return false;

            $.ajax({
                type: "get", //ajax post方式请求
                url: "/deleteMsg",
                dataType: "text",//因为处理方法返回的类型 text
                data: {//请求的参数
                    "messageId": row.id,
                },
                success: function (data) {
                    var r = JSON.parse(data);
                    if (r.state == 1) {
                        alert("删除成功");
                        users.bootstrapTable("refresh", {
                            url: "/getMessage",
                            queryParams: function (param) {
                                return {"state": 0};
                            }
                        })
                    } else if (r.state == 0) {
                        alert("操作失败");
                    }
                },
                error: function (msg) {
                    alert("服务器GG了...");
                }
            });
        }
    };
</script>
</html>