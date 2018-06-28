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
	var itemId = GetUrlParam("itemid");
	$.ajax({
		url : "/item/getIAD",
		type : "post",
		dataType : "text",
		data : {
			"itemId" : itemId
		},
		success : function(data) {
			var item ;
			item= JSON.parse(data);
			showData(item);
		},
		erro : function() {

		}

	});
}
function showData(item) {
	$("#i-name").append(item.item.name);
	$("#i-id").append(item.item.id);
	$("#i-date").append(item.item.date);
	$("#i-type").append(item.item.classification);
	$("#i-expect").append(item.item.expect);
	$("#i-price").append(item.item.price);
	var imgArray=item.item.img.split("*");
	var length = imgArray.length;
	for (var i = 0; i < length; i++) {
		$("#i-img").append("<img style='width:150px;height:150px;'src='" + imgArray[i] + "'>");
	}

	$("#i-info").append(item.item.detail);
	$("#i-collect").append(item.collect);
	$("#i-rq").append(item.rq);
	$("#i-comment").append(item.comment);
}