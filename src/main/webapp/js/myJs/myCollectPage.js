//页面加载后自动执行
$(function () {
    // getCollect();
    var left = $(".left-menu");
    left.height($(window).height());
    $(".top").width($(window).width()-200);
    // $(".right-data").width($(window).width() - 200);

    getCollect();
});
var itemlist;
var num = 9;
// 获取收藏列表
function getCollect() {
	var itemid = $("#itemid").text();
	$.ajax({
        url:"/item/getCollect",
        type:"get",
        success:function(data){//data为后台返回的json字符串内容为已提交订单(类ExcData.java)的列表，通过JSON.parse解析
        	itemlist=JSON.parse(data);
            showItem();
        },
        erro:function () {
            alert("服务器炸了");
        }
    });
}
// 显示
function showItem(){
	
	var box = $(".itembox");
	var load = $(".loadbox");//显示加载按钮的容器
	box.empty();
	load.empty();
	for (var j =0; j < num; j++) {
		if (itemlist[j] == null) {
			box.append("<div class='col-sm-12' style='color:white;text-align: center;'>已经没有啦》_《~</div>");
			return;
		}
		var e = "<div class='' style='color:black;width: 220px;padding: 10px;float: left;border: 0;min-width: 210px;margin-left: 10px;'>"
					+"<div class='thumbnail' style='margin-bottom: 5px;'>"
					+"<a href='/item?itemid="+itemlist[j].id+"'>"
						+"<img style='width:200px;height: auto;' src='"+itemlist[j].imgpath[0]+"'>"
					+"</a></div>"
					+"<div class='caption' style='background-image: url(\"/images/background/bottom.jpg\");color: white;text-align: center;border-radius: 4px;'>"
					+"<a style='color: white;text-decoration: none;' href='/item?itemid="+itemlist[j].id+"'>"+ itemlist[j].name + "</a>"
					+"<br /><a href='javascript:cancelCollect(\""+itemlist[j].id+"\")'>取消收藏</a>"
					+ "</div>" +"</div>";
		box.append(e);
	}
	if (num < itemlist.length) {
		load.append("<a href='javascript:onload()'><div class='load' style='color=white;text-align: center;'>加载更多...</div></a>");
	}
}
// 加载更多
function onload() {
	num += 6;
	showItem();
}

function cancelCollect(itemId) {
	$.ajax({
		url:"/item/cancelCollect",
		type:"get",
		dataType:"text",
		data:{
			"itemid":itemId
		},
		success:function (data) {
			var r=JSON.parse(data);
			if (r.state == 1){
			    alert("取消成功");
			    getCollect();
            }else if (r.state == 0){
			    alert("操作失败");
			    alert(r.result);
            }
        },
		error:function (data) {
			
        }
	})
}
