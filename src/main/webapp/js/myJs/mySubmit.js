//页面加载后自动执行
$(function() {
    var table=new itemTable();
    // table.Init();
});
var oTableInit;
var itemTable = function () {
    oTableInit =
        $('#tb_pend').bootstrapTable({
            url: "/item/getExchange", //请求的后台连接，需要请求到json数据
            method: 'get', //请求方式
            striped: false, //是否显示行间隔色
            cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true, //是否显示分页（*）
            sortable: true, //是否启用排序
            sortOrder: "asc", //排序方式
            queryParams: function (params) {
                return {
                    "type": "submit"};
            },
            sidePagination: "client", //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1, //初始化加载第一页，默认第一页
            pageSize: 4, //每页的记录行数（*）
            pageList: [4], //可供选择的每页的行数（*）
            showExport: true, //是否显示导出
            exportDataType: "basic", //basic', 'all', 'selected'.
            columns: [
                {
                    field: 'img_a',
                    title: '己方商品',
                    formatter:showImga,
                    editable: true,
                    events: "read",

                },  {
                    field: "img_b",
                    formatter:showImgb,
                    title: '对方商品',

                },{
                    field: "info",
                    title: '备注信息',

                }, {

                    field: "excDate",
                    title: "发起时间",
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
    return[
        '<button type="button" class="cancel btn btn-default  btn-sm" style=" width:auto;height: 35px;">取消交换</button>'+'<br >'
    ].join('');
}

function status(value, row, index) {
    if (row.statu == 0)
        return "未读";
    else if (row.statu == 1)
        return "已读"
}

function toItem(value, row, index) {
    return "<a href='/item/itemid=" + row.id + "'>" + value + "</a>"
}

function showImga(value, row, index) {
    return "<img style='width:100px;height:100px' src='" + value + "'><br >"+row.gname_a;
}
function showImgb(value, row, index) {
    return "<img style='width:100px;height:100px' src='" + value + "'><br >"+row.gname_b;
}

var events = window.operateEvents = {
    'click .cancel' : function(e, value, row, index) {
        if (!confirm("确认取消交换？"))
            return false;

        $.ajax({
            type : "get", //ajax post方式请求
            url : "/item/cancelExc",
            dataType : "text",//因为处理方法返回的类型 text
            data : {//请求的参数
                "eId" : row.excId
            },
            success : function(data) {
                var result=JSON.parse(data);
                if (result.state =="1") {
                    alert("取消成功");
                    oTableInit.bootstrapTable("refresh", {
                        url:"/item/getExchange",
                        queryParams: function (param) {
                            return {
                                "type": "submit"};
                        }
                    })
                }
                else
                    alert("操作失败");
            },
            error : function(msg) {
                alert(msg.state);
                alert("服务器GG了...");
            }
        });
    }
};

