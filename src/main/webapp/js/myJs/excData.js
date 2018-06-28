/**
 * 
 */
$(function() {
	getData();
	
});
function GetUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}
function getData() {
	var excId=GetUrlParam("excid");
	$.ajax({
		url : "/item/getExcData",
		type : "post",
		dataType : "text",
		data:{
			"excId":excId
		},
		success : function(data) {
			var excData = JSON.parse(data);
			loadData(excData);
		},
		erro : function() {

		}
	});
}

function refuse(){
	var excId=GetUrlParam("excid");
	$.ajax({
		url:"/item/isExchange",
		type:"get",
		dataType:"text",
		data:{
			"eId":excId,
			"state" : "1"
		},
		success:function(data){
            var result=JSON.parse(data);
            if (result.state =="1") {
                alert("已拒绝该请求");
                window.location.href="/mainPage";
            }
            else if (result.state =="0"){
                alert("操作失败: "+result.result);
			}

		},
		error:function(){
			alert("请求服务器失败");
		}
		
	});
	}

function accede(){
	var excId=GetUrlParam("excid");
	$.ajax({
		url:"/item/isExchange",
		type:"get",
		dataType:"text",
		data:{
			"eId":excId,
            "state" : "1"
		},
		success:function(data){
            var result=JSON.parse(data);
            if (result.state =="1") {
                alert("交换成功");
                window.location.href="/admin/businessPage";
            } else if (result.state =="0"){
                alert("操作失败: "+result.result);
            }
		},
		error:function(){
			alert("请求服务器失败");
		}
		
	});
	}

function loadData(excData) {
	$(".excId").append(excData.excId);
	$(".excDate").append(excData.excDate);
	
	$("#glogo_A").append(
			"<img width='150px' height='150px' src=" + excData.img_a + ">");
	$("#gname_A").append("<a href='/item/itemid="+excData.gid_a+"'>"+excData.gname_a+"</a>");
	
	$("#glogo_B").append(
			"<img width='150px' height='150px' src=" + excData.img_b + ">");
	$("#gname_B").append("<a href='/item/itemid="+excData.gid_b+"'>"+excData.gname_b+"</a>");
	
	$(".excInfo").append(excData.info);
}