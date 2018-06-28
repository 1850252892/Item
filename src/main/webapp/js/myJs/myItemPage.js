//页面加载后自动执行
$(function () {
    // getCollect();
    var left = $(".left-menu");
    left.height($(window).height());
    $(".top").width($(window).width()-200);
    $(".right-data").width($(window).width() - 200);
    $(".right-data").height($(window).height());
    var itable = new itemTable();

});

var oTableInit;
var itemTable = function () {
    var uid = $("#u_id").text();
    oTableInit =
        $('#itembox').bootstrapTable({
            url: "/item/getFreeItem", //请求的后台连接，需要请求到json数据
            method: 'get', //请求方式
            striped: false, //是否显示行间隔色
            cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true, //是否显示分页（*）
            sortable: true, //是否启用排序
            sortOrder: "asc", //排序方式
            queryParams: function (param) {
                return {"uid": uid};
            },
            sidePagination: "client", //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1, //初始化加载第一页，默认第一页
            pageSize: 4, //每页的记录行数（*）
            pageList: [4], //可供选择的每页的行数（*）
            showExport: true, //是否显示导出
            exportDataType: "basic", //basic', 'all', 'selected'.
            columns: [
                {
                    field: 'imgpath',
                    title: '',
                    formatter: 'showImg',
                    editable: true,

                },
                {
                    field: 'id',
                    title: '商品id',
                    formatter: "toItem",

                }, {
                    field: 'name',
                    title: '商品名',
                    editable: true,
                    events: "read",

                },  {
                    field: "price",
                    title: '参考价',

                },{
                    field: "classification",
                    title: '分类',

                }, {

                    field: "time",
                    title: "发布时间",
                },
                {
                    field: 'do',
                    title: '操作',
                    formatter: "options",
                    events : "events",

                }],
            /* 修改后点勾那个按钮触发的方法 */
        });

    return oTableInit;
}


function options(value, row, index) {
    return [
        '<button type="button" class="delete btn btn-default  btn-sm" style=" width:100%;">下架</button>',]
        .join('');
}

function status(value, row, index) {
    if (row.statu == 0)
        return "未读";
    else if (row.statu == 1)
        return "已读"
}

function toItem(value, row, index) {
    return "<a href='/item?itemid=" + row.id + "'>" + value + "</a>"
}

function showImg(value, row, index) {
    return "<img style='height:100px' src='" + row.imgpath[0] + "'>";
}

var events = window.operateEvents = {
    'click .delete' : function(e, value, row, index) {
        if (!confirm("确认下架商品？"))
            return false;

        $.ajax({
            type : "get", //ajax post方式请求
            url : "/item/deleteItem",
            dataType : "text",//因为处理方法返回的类型 text
            data : {//请求的参数
                "itemId" : row.id,
            },
            success : function(data) {
                if (data =="DELETE_SUCCESS") {
                    alert("删除成功");
                    var uid = $("#u_id").text();
                    oTableInit.bootstrapTable("refresh", {
                        url:"/item/getFreeItem",
                        queryParams: function (param) {
                            return {"uid": uid};
                        }
                    })
                }
                else
                    alert(data);
            },
            error : function(msg) {
                alert("服务器GG了...");
            }
        });
    }
};
// var read = window.operateEvents = {
//
//     'click a' : function(e, value, row, index) {
//         $.ajax({
//             type : "get", //ajax post方式请求
//             url : "/changeMessage",
//             dataType:"text",
//             data:{
//                 "excId":value
//             },
//             success : function(data) {
//                 alert("cheng");
//             },
//             error : function() {
//
//             }
//         });
//     }
// }