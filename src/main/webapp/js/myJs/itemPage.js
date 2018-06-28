/**
 * 
 */

$(function () {
    getItemDetails();
    getOwner();
    $("#div1").click(function() {
        var div1 = document.getElementById("div1");
        var div2 = document.getElementById("div2");
        div1.style.background = "rgb(225, 218, 68)";
        div1.style.color = "white";
        div2.style.background = "white";
        div2.style.color = "black";
        document.getElementById("div3").style.display = "block";
        document.getElementById("div4").style.display = "none";



    });
    $("#div2").click(function() {
        var div1 = document.getElementById("div1");
        var div2 = document.getElementById("div2");
        div2.style.background = "rgb(225, 218, 68)";
        div2.style.color = "white";
        div1.style.background = "white";
        div1.style.color = "black";
        document.getElementById("div4").style.display = "block";
        document.getElementById("div3").style.display = "none";
        getMessage();
    });
    isCollect();

})

var message = null;
var num = 10;
function getUrlParam(key) {
    // 获取参数
    var url = window.location.search;
    // 正则筛选地址栏
    var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
    // 匹配目标参数
    var result = url.substr(1).match(reg);
    //返回参数值
    return result ? decodeURIComponent(result[2]) : null;
}

function swap() {
    var id=getUrlParam("itemid");
    window.location.href="/item/swap?itemid="+id;
}

function getItemDetails() {
	var itemId=getUrlParam("itemid");
	$.ajax({
        url:"/item/details",
        type:"get",
        dataType:"text",
        data:{
            "id":itemId
        },
        success:function (data) {
            var item=JSON.parse(data);
            $("#i-browse").append("浏览量<br>"+item.browser);
            $("#i-date").append("发布时间<br>"+item.time);
            $(".img-bg").append("<img src='"+item.imgpath[0]+"'>");
            var len=item.imgpath.length;
            for (var i=0;i<len;i++){
                $(".img-li").append("<img src='"+item.imgpath[i]+"'>");
            }
            $("#i-title").append(item.name);
            $("#i-price").append(item.price+".00");
            $("#i-expect").append("期望物品："+item.expect);
            $("#div3").append(item.detail);
            $("#itemid").append(item.id);
        }
    })
}

function getOwner() {
    var itemId=getUrlParam("itemid");
    $.ajax({
        url:"/item/owner",
        type:"get",
        dateType:"text",
        data:{
            "id":itemId
        },
        success:function (data) {
            var owner=data;
            var user=owner.user;
            var items=owner.list;
            $("#u-nickname").append("拥有者<br><a href='/otherUser?uid="+user.username+"'>"+user.nickname+"</a>");
            $("#i-address").append("所属区域："+user.address);

            var len=items.length;
            for(var i=0;i<len;i++){
                $("#items").append(" <div class=\"u-item\">\n" +
                    "                        <div class=\"u-item-img\">\n" +
                    "                            <a href=\"/item?itemid="+items[i].id+"\"><img alt=\"\"\n" +
                    "                                                                    src=\""+items[i].imgpath[0]+"\"></a>\n" +
                    "                        </div>\n" +
                    "                        <div class=\"u-item-name\">"+items[i].name+"</div>\n" +
                    "                        <div class=\"u-item-price\">\n" +
                    "                            参考价：<span>"+items[i].price+".00</span>\n" +
                    "                        </div>\n" +
                    "                    </div>")
            }
            $(".img-li img").click(function(e) {
                $(".img-bg img").attr('src', this.src);
            });

        }
    })
}



// 获取评论列表
function getMessage() {
	var itemid = $("#itemid").text();
	
	$.ajax({
		url : "/getComment",
		type : "post",
		dataType : "text",
		data : {
			"itemid" : itemid
		},
		success : function(data) {
			message = JSON.parse(data);
			showMessage();
		}
	});
}
// 显示评论
function showMessage() {
	$("#comment_num").empty();
	$("#comment_num").append("留言("+message.length+")");
	
	var box = $(".messagebox");
	box.empty();
	var load = $(".loadbox");
	load.empty();
	for (var j =0; j < num; j++) {
		if (message[j] == null) {
			box.append("<div class='tips'>已无更多评论</div>");
			return;
		}
		var e = "<span>" + "<div class='m_u_id'>" + message[j].u_id + "</div>"
				+ "<div class='m_date'>" + message[j].date + "</div>"
				+ "<div class='m_info'>" + message[j].info + "</div>"
				+ "</span>";
		box.append(e);
	}
	if (num < message.length) {
		load
				.append("<a href='javascript:onload()'><div class='load'>加载更多...</div></a>");
	}
}
// 加载更多
function onload() {
	num += 10;
	showMessage();
}
// 发送留言
function sendMessage(uid) {
	var message;
	var itemid = $("#itemid").text();
	message = $("#i-message").val();
	if (message == "") {
		message = $("#i-message").val("你什么都没填诶");
		
	}
	
	else{
		$.ajax({
			url : "/comment",
			type : "post",
			dataType : "text",
			data : {
				'g_id' : itemid,
				'info' : message,
				'u_id' : uid
			},
			success : function(data) {
				if (data == "success") {
					$("#i-message").val("")
				}
			},
			erro : function() {
			}
		});
	}
}

// 是否已收藏
function isCollect() {
	var itemid = getUrlParam("itemid");
	var collect = $("#i_collect");
	$
			.ajax({
				url : "/isCollect",
				type : "get",
				dataType : "text",
				data : {
					"itemid" : itemid
				},
				success : function(data) {
					if (data == "exist") {
						collect
								.append("已收藏<br><span class='icon-heart-empty' id='collect' style='color:red;' onclick='cancelCollect()'></span></div>");
					} else if (data = "no") {
						collect
								.append("收藏<br><span class='icon-heart-empty' id='collect' onclick='collect()'></span></div>");
					}
				},
				erro : function() {

				}
			});
}

// 收藏
function collect() {
	var itemid = $("#itemid").text();
	var collect = $("#i_collect");
	$
			.ajax({
				url : "/item/collect",
				type : "post",
				dataType : "text",
				data : {
					"itemid" : itemid
				},
				success : function(data) {
					var result=JSON.parse(data);
					if (result.state =="1") {
						alert("收藏成功");
						collect.empty();
						collect
								.append("收藏<br><span class='' id='collect' style='color:red;' onclick='cancelCollect()'>"+result.result+"</span>");
					} else {
						alert("操作失败");
					}
				},
				error : function() {
					alert("服务器异常");
				}
			});
}
// 取消收藏
function cancelCollect() {
	alert("取消");
	var itemid = $("#itemid").text();
	var collect = $("#i_collect");
	$
			.ajax({
				url : "/item/cancelCollect",
				type : "post",
				dataType : "text",
				data : {
					"itemid" : itemid
				},
				success : function(data) {
                    var result=JSON.parse(data);
                    if (result.state =="1") {
                        alert("已取消");
                        collect.empty();
                        collect
                            .append("已收藏<br><span class='' id='collect'  onclick='cancelCollect()'>"+result.result+"</span>");
                    } else {
                        alert("操作失败");
                    }
				},
				error : function() {

				}
			});
}

