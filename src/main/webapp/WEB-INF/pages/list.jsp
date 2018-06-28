<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"
	content="initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<!-- <link rel="stylesheet" href="css/style.css"> -->

<style type="text/css">
.piclist a {
	padding: 0;
	margin: 0;
	text-decoration: none;
}

img {
	border-style: none;
}

ul {
	list-style: none;
}

.piclist {
	background-color: rgb(245, 245, 245);
	width: 100%;
	height: 800px;
	background-color: #fff;
}

.listul {
	width: 1250px;
	margin: 0 auto;
	padding-left: 40px;
	overflow: hidden;
	height: 800px;
	background-color: rgb(245, 245, 245);
}

.listul li {
	float: left;
	width: 220px;
	margin: 10px 20px 0 0;
	padding: 5px;
	height: 210px;
	position: relative;
	background-color: white;
}

.listul li .name {
	position: absolute;
	bottom: 40px;
	width: 210px;
	text-align: center;
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000,
		endColorstr=#99000000);
	color: #333;
	font-size: 15px;
	padding: 3px 0;
}

.listul li span {
	position: absolute;
	bottom: 10px;
	width: 210px;
	text-align: center;
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000,
		endColorstr=#99000000);
	color: #FF0036;
	font-size: 15px;
	padding: 3px 0;
}

.fydiv {
	width: 320px;
	text-align: center;
	height: 30px;
	margin: 0 auto;
	margin-top: 30px;
}

.fenye {
	text-align: center;
	height: 30px;
	margin: 0 auto;
}

.fenye li {
	float: left;
	border: 1px solid #6CBFB7;
	height: 26px;
	line-height: 26px;
	margin-right: 10px;
}

.fenye li:hover {
	background-color: #f1f1f1;
}

.fenye li a {
	text-decoration: none;
	color: #6CBFB7;
	display: block;
}

.prev {
	width: 66px;
}

.next {
	width: 66px;
}

.numb {
	width: 26px;
}

.choose {
	border: 1px solid #357973 !important;
}

.choose a {
	color: #357973 !important;
}
</style>
</head>
<body>
	<div class="piclist">
		<ul class="listul">

		</ul>
		<div class="fydiv">
			<ul class="fenye">

			</ul>
		</div>
	</div>
	<script type="text/javascript">
		var jsonData = null;
		var length;
		window.onload = getData;
		function getData() {
			$.ajax({
				url : "./getItemList",
				type : 'get',
				success : function(data) {
					jsonData = JSON.parse(data); 
					length = jsonData.length;
					getPage(1);
				},
				erro : function() {
					alert("erro");
				}
			});
		}

		function getPage(pn) {
			var dataCount = length;//×ÜÊý¾ÝÌõÊý
			var pageSize = 25;//Ã¿Ò³ÏÔÊ¾ÌõÊý
			var pageCount = Math.ceil(dataCount / pageSize);//×ÜÒ³Êý
			if (pn == 0 || pn > pageCount) {
				return;
			}
			var ul = $(".listul");
			ul.empty();
			//console.log(pageCount+"..."+pn)
			paintPage(pageCount, pn); //»æÖÆÒ³Âë
			var startPage = pageSize * (pn - 1);

			if (pageCount == 1) { // µ±Ö»ÓÐÒ»Ò³Ê± 
				for (var j = 0; j < dataCount; j++) {
					var e = "<li><a href='itemPage/itemid="+jsonData[j].id+"><img width='210px' height='120px' src="+jsonData[j].imgpath[0]+"></a><p><span>¸ú×ÅÎÒÒ»ÆðÒ¡°Ú"
							+ j + "</span></p></li>";
					ul.append(e);
				}
			} else { // µ±³¬¹ýÒ»Ò³Ê± 
				var e = "";
				var endPage = pn < pageCount ? pageSize * pn : dataCount;
				for (var j = startPage; j < endPage; j++) {
					var url = "itemPage/itemid=" + jsonData[j].id;
					var e = "<li>"
							+ "<a href="+url+"><img width='210px' height='120px' src="+jsonData[j].imgpath[0]+"></a>"
							+ "<p class='name'>" + jsonData[j].name
							+ "</p></br>" + "<p><span>￥" + jsonData[j].price
							+ "</span></p></li>";
					ul.append(e);
				}
			}
		}

		//绘制页码
		function paintPage(number, currNum) //number 总页数,currNum 当前页  
		{
			var pageUl = $(".fenye");
			pageUl.empty();
			var ulDetail = "";

			if (number == 1) {
				ulDetail = "<li class=\"prev\"><a href=\"javascript:void(0)\">上一页</a></li>"
						+ "<li class=\"numb choose\"><a href=\"javascript:getPage(1)\">1</a></li>"
						+ "<li class=\"next\"><a href=\"javascript:void(0)\">下一页</a></li>";
			} else if (number == 2) {
				ulDetail = "<li class=\"prev\"><a href=\"javascript:getPage(1)\">上一页</a></li>"
						+ "<li class=\"numb"
						+ choosele(currNum, 1)
						+ "\"><a href=\"javascript:getPage(1)\">1</a></li>"
						+ "<li class=\"numb"
						+ choosele(currNum, 2)
						+ "\"><a href=\"javascript:getPage(2)\">2</a></li>"
						+ "<li class=\"next\"><a href=\"javascript:getPage(2)\">下一页</a></li>";
			} else if (number == 3) {
				ulDetail = "<li class=\"prev\"><a href=\"javascript:getPage("
						+ parseInt(currNum - 1) + ")\">上一页</a></li>"
						+ "<li class=\"numb" + choosele(currNum, 1)
						+ "\"><a href=\"javascript:getPage(1)\">1</a></li>"
						+ "<li class=\"numb" + choosele(currNum, 2)
						+ "\"><a href=\"javascript:getPage(2)\">2</a></li>"
						+ "<li class=\"numb" + choosele(currNum, 3)
						+ "\"><a href=\"javascript:getPage(3)\">3</a></li>"
						+ "<li class=\"next\"><a href=\"javascript:getPage("
						+ parseInt(currNum + 1) + ")\">下一页</a></li>";
			} else if (number == currNum && currNum > 3) {
				ulDetail = "<li class=\"prev\"><a href=\"javascript:getPage("
						+ parseInt(currNum - 1)
						+ ")\">上一页</a></li>"
						+ "<li class=\"numb\"><a href=\"javascript:getPage("
						+ parseInt(currNum - 2)
						+ ")\">"
						+ parseInt(currNum - 2)
						+ "</a></li>"
						+ "<li class=\"numb\"><a href=\"javascript:getPage("
						+ parseInt(currNum - 1)
						+ ")\">"
						+ parseInt(currNum - 1)
						+ "</a></li>"
						+ "<li class=\"numb choose\"><a href=\"javascript:getPage("
						+ currNum + ")\">" + currNum + "</a></li>"
						+ "<li class=\"next\"><a href=\"javascript:getPage("
						+ currNum + ")\">下一页</a></li>";
			} else if (currNum == 1 && number > 3) {
				ulDetail = "<li class=\"prev\"><a href=\"javascript:void(0)\">上一页</a></li>"
						+ "<li class=\"numb choose\"><a href=\"javascript:void(0)\">1</a></li>"
						+ "<li class=\"numb\"><a href=\"javascript:getPage(2)\">2</a></li>"
						+ "<li class=\"numb\"><a href=\"javascript:getPage(3)\">3</a></li>"
						+ "<li class=\"next\"><a href=\"javascript:getPage(2)\">下一页</a></li>";
			} else {
				ulDetail = "<li class=\"prev\"><a href=\"javascript:getPage("
						+ parseInt(currNum - 1)
						+ ")\">上一页</a></li>"
						+ "<li class=\"numb\"><a href=\"javascript:getPage("
						+ parseInt(currNum - 1)
						+ ")\">"
						+ parseInt(currNum - 1)
						+ "</a></li>"
						+ "<li class=\"numb choose\"><a href=\"javascript:getPage("
						+ currNum + ")\">" + currNum + "</a></li>"
						+ "<li class=\"numb\"><a href=\"javascript:getPage("
						+ parseInt(currNum + 1) + ")\">"
						+ parseInt(currNum + 1) + "</a></li>"
						+ "<li class=\"next\"><a href=\"javascript:getPage("
						+ parseInt(currNum + 1) + ")\">下一页</a></li>";
			}

			$(".fenye").append(ulDetail);

		}

		function choosele(num, cur) {
			if (num == cur) {
				return " choose";
			} else {
				return "";
			}
		}
	</script>
</body>
</html>