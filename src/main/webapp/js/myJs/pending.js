//页面加载后自动执行
$(function() {
	var table=new itemTable();
	table.Init();
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
                	"type": "request"};
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
                    title: '对方商品',
                    formatter:showImga,
                    editable: true,
                    events: "read",

                },  {
                    field: "img_b",
                    formatter:showImgb,
                    title: '己方商品',

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
        '<button type="button" class="agree btn btn-default  btn-sm" style=" width:auto;height: 35px;">同意交换</button>'+'<br >'+
        '<button type="button" class="refuse btn btn-default  btn-sm" style=" width:auto;height: 35px;margin-top: 20px">拒绝交换</button>'
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
    'click .agree' : function(e, value, row, index) {
        if (!confirm("确认交换商品？"))
            return false;

        $.ajax({
            type : "get", //ajax post方式请求
            url : "/item/isExchange",
            dataType : "text",//因为处理方法返回的类型 text
            data : {//请求的参数
                "eId" : row.excId,
				"state" : "1"
            },
            success : function(data) {
                var result=JSON.parse(data);
                if (result.state =="1") {
                    alert("交换成功");
                    oTableInit.bootstrapTable("refresh", {
                        url:"/item/getExchange",
                        queryParams: function (param) {
                            return {
                                "type": "request"};
                        }
                    })
                }
                else if (result.state == "0"){
                    alert("操作失败:"+result.result);
                    oTableInit.bootstrapTable("refresh", {
                        url:"/item/getExchange",
                        queryParams: function (param) {
                            return {
                                "type": "request"};
                        }
                    })
                }

            },
            error : function(msg) {
            	alert(msg.state);
                alert("服务器GG了...");
            }
        });
    },

    'click .refuse' : function(e, value, row, index) {
        if (!confirm("确认拒绝交换？"))
            return false;

        $.ajax({
            type : "get", //ajax post方式请求
            url : "/item/isExchange",
            dataType : "text",//因为处理方法返回的类型 text
            data : {//请求的参数
                "eId" : row.excId,
                "state" : "-1"
            },
            success : function(data) {
                var result=JSON.parse(data);
                if (result.state =="1") {
                    alert("已拒绝该请求");
                    oTableInit.bootstrapTable("refresh", {
                        url:"/item/getExchange",
                        queryParams: function (param) {
                            return {
                                "type": "request"};
                        }
                    })
                }
                else if (result.state == "0"){
                    alert("操作失败:"+result.result);
                    oTableInit.bootstrapTable("refresh", {
                        url:"/item/getExchange",
                        queryParams: function (param) {
                            return {
                                "type": "request"};
                        }
                    })
                }
            },
            error : function(msg) {
                alert(msg);
                alert("服务器GG了...");
            }
        });
    }
};




















var subMit;







// 获取以提交的商品数据列表
function getData() {
	$.ajax({
		url : "/item/getExchange",
		type : "get",
		dataType : "text",
		data:{
			"type":"request"
		},
		success : function(data) {// data为后台返回的json字符串内容为已提交订单(类ExcData.java)的列表，通过JSON.parse解析
			subMit = JSON.parse(data);
			showData();
		},
		erro : function() {
			alert("服务器炸了");
		}
	});
}

var num = 7;

// 将json数据添加到网页
function showData() {
	var json = {};
	var box = $(".all-data");// 用以显示数据的容器
	var load = $(".loadbox");// 显示加载按钮的容器
	box.empty();
	load.empty();

	for (var j = 0; j < num; j++) {
		if (subMit[j] == null) {// 当列表遍历完后显示提示信息
			box
					.append("<div class='tips' style='color:white;text-align: center;'>已无更多信息</div>");
			return;
		}
		if (j % 2 == 0) {
			var e = "<div class='order' style='background-color: #d8f3f5;height:120px;float:left;width:100%;margin-bottom:20px;'>"
					+ "<div class='col-sm-6 col-md-4'><img style='width:100px;height:90px;' src=/"
					+ subMit[j].img_a
					+ ">"
					+ "<div class='gname'>别人的<a href='/item/itemid="
					+ subMit[j].gid_a
					+ "'>"
					+ subMit[j].gname_a
					+ "</a></div>"
					+ "</div>"
					+ "<div class='col-sm-6 col-md-4'><img style='width:100px;height:90px;' src=/"
					+ subMit[j].img_b
					+ ">"
					+ "<div class='gname'>我的<a href='/item/itemid="
					+ subMit[j].gid_b
					+ "'>"
					+ subMit[j].gname_b
					+ "</a></div>"
					+ "</div>"
					+ "<div class='eid' id='eid'>单号："
					+ subMit[j].excId
					+ "</div>"
					+ "<div class='eDate'>提交时间："
					+ subMit[j].excDate
					+ "</div>"
					+ "<div class='eInfo'>备注信息："
					+ subMit[j].info + "</div>"
					+"<button onclick=accede('"+subMit[j].excId+"')>同意</button>"
					+"<button onclick=refuse('"+subMit[j].excId+"')>拒绝</button>";
			" </div>"
		} else {
			var e = "<div class='order' style='background-color: #fdfde8;height:120px;float:left;width:100%;margin-bottom:20px;'>"
					+ "<div class='col-sm-6 col-md-4'><img style='width:100px;height:90px;' src=/"
					+ subMit[j].img_a
					+ ">"
					+ "<div class='gname'>别人的<a href='/item/itemid="
					+ subMit[j].gid_a
					+ "'>"
					+subMit[j].gname_a
					+ "</a></div>"
					+ "</div>"
					+ "<div class='col-sm-6 col-md-4'><img style='width:100px;height:90px;' src=/"
					+ subMit[j].img_b
					+ ">"
					+ "<div class='gname'>我的<a href='/item/itemid="
					+ subMit[j].gid_b
					+ "'>"
					+ subMit[j].gname_b
					+ "</a></div>"
					+ "</div>"
					+ "<div class='eid' id='eid'>单号："
					+ subMit[j].excId
					+ "</div>"
					+ "<div class='eDate'>提交时间："
					+ subMit[j].excDate
					+ "</div>"
					+ "<div class='eInfo'>备注信息："
					+ subMit[j].info + "</div>"
					+"<button onclick=accede('"+subMit[j].excId+"')>同意</button>"
					+"<button onclick=refuse('"+subMit[j].excId+"')>拒绝</button>";
			" </div>"
		}

		box.append(e);
	}
	if (num < subMit.length) {
		load
				.append("<a href='javascript:onload()'><div class='load' style='color=white;text-align: center;'>加载更多...</div></a>");
	}
}

function refuse(excId){
	$.ajax({
		url:"/item/isExchange",
		type:"get",
		dataType:"text",
		data:{
			"id":excId,
			"info":"fail"
		},
		success:function(data){
			if(data=="success")
				getData();
			else alert(data);
		},
		erro:function(){
			alert("请求服务器失败");
		}
		
	});
	}

function accede(excId){
	$.ajax({
		url:"/item/isExchange",
		type:"get",
		dataType:"text",
		data:{
			"id":excId,
			"info":"success"
		},
		success:function(data){
			if(data=="success")
				getData();
			else alert(data);
		},
		erro:function(){
			alert("请求服务器失败");
		}
		
	});
	}

// 加载更多数据
function onload() {
	num += 5;
	showData();
}
