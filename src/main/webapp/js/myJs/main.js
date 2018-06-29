$(function () {
   // getAddress(returnCitySN["cip"]);
    setHotImg();
    getClasss();
    getHot();
    getNew();
    getPhone();
    getComputer();
    getBook();
    getClothing();
    getSport();
    getGame();
    getOther();


});
var p=$.cookie("province");
var c=$.cookie("city");
var d=$.cookie("district");
var  address=p+"-"+c;
function setHotImg() {
    var s = new select();
    s.order = "b.browser";
    s.startLine = 0;
    s.endLine = 3;
    s.status = "FREE";
    s.isDesc = "DESC";
    var box = $("#lunbo");
    $.ajax({
        url: "/item/getItemList",
        type: "post",
        contentType: 'application/json;charset=utf-8',
        dataType: "text",
        data: JSON.stringify(s),
        success: function (data) {
            var items = JSON.parse(data);
            items = items.list;
            var len = items.length;
            if (len > 0)
                box.empty();
            for (var i = 0; i < len; i++) {
                if (i == 0)
                {
                    box.append("<div class='item active'" +
                        "style='height: 500px; width: 800px; margin: 0; padding: 0;'>" +
                        "<a href='/item?itemid=" + items[i].id + "'><img src='" + items[i].imgpath[0] + "'" +
                        "alt='...'" +
                        "style='height: 500px; width: 800px;'></a>\n" +
                        "</div>");
                    $(".hot-right").append(
                        "<a href='/item?itemid="+items[i].id+"'><img src='"+items[i].imgpath[0]+"'width='150px'></a>" +
                        "<a href='/item?itemid="+items[i].id+"' style='padding: 0px;'>"+items[i].name+"</a>");
                }
                else if (i<3)
                {
                    box.append("<div class='item'" +
                        "style='height: 500px; width: 800px; margin: 0; padding: 0;'>" +
                        "<a href='/item?itemid=" + items[i].id + "'><img src='" + items[i].imgpath[0] + "'" +
                        "alt='...'" +
                        "style='height:500px; width: 100%;'></a>\n" +
                        "</div>");
                    $(".hot-right").append(
                        "<a href='/item?itemid="+items[i].id+"'><img src='"+items[i].imgpath[0]+"'width='150px'></a>" +
                        "<a href='/item?itemid="+items[i].id+"' style='padding: 0px;'>"+items[i].name+"</a>");
                }
                else{

                }

            }
        }
    })
}

function getClasss() {
    $.ajax({
        url: "/item/class_list",
        type: "get",
        dataType: "text",
        success: function (data) {
            var list = JSON.parse(data);
            var list_box = $("#class_ul");
            var len = list.length;
            list_box.empty();
            for (var i = 0; i < len; i++) {
                var li = '<li class="sortlist"><a href="/selectPage?s=&c=' + list[i] + '">' + list[i] + '</a></li>';
                list_box.append(li);
            }
        },
        error: function () {

        }

    })
}

var select = function () {
    var classification;
    var order;
    var isDesc;
    var status;
    var startLine;
    var endLine;
    var address;
};

function getHot() {
    var s = new select();
    s.order = "b.browser";
    s.startLine = 0;
    s.endLine = 8;
    s.status = "FREE";
    s.isDesc = "DESC";
    s.address=address;
    var box = $("#hot");
    getItems(s, box);

}

function getNew() {
    var s = new select();
    s.order = "a.time";
    s.startLine = 0;
    s.endLine = 8;
    s.status = "FREE";
    s.isDesc = "DESC";
    s.address=address;
    var box = $("#new");
    getItems(s, box);
}

function getPhone() {
    var s = new select();
    s.order = "a.time";
    s.startLine = 0;
    s.endLine = 8;
    s.address=address;
    s.status = "FREE";
    s.isDesc = "DESC";
    s.classification = "手机";
    var box = $("#phone");
    getItems(s, box);
}

function getComputer() {
    var s = new select();
    s.order = "a.time";
    s.startLine = 0;
    s.endLine = 8;
    s.status = "FREE";
    s.address=address;
    s.isDesc = "DESC";
    s.classification = "电脑";
    var box = $("#computer");
    getItems(s, box);
}

function getBook() {
    var s = new select();
    s.order = "a.time";
    s.startLine = 0;
    s.endLine = 8;
    s.status = "FREE";
    s.isDesc = "DESC";
    s.address=address;
    s.classification = "书本";
    var box = $("#book");
    getItems(s, box);
}

function getClothing() {
    var s = new select();
    s.order = "a.time";
    s.startLine = 0;
    s.endLine = 8;
    s.status = "FREE";
    s.isDesc = "DESC";
    s.address=address;
    s.classification = "服装";
    var box = $("#clothing");
    getItems(s, box);
}

function getSport() {
    var s = new select();
    s.order = "a.time";
    s.startLine = 0;
    s.endLine = 8;
    s.status = "FREE";
    s.isDesc = "DESC";
    s.address=address;
    s.classification = "运动";
    var box = $("#sport");
    getItems(s, box);
}

function getGame() {
    var s = new select();
    s.order = "a.time";
    s.startLine = 0;
    s.endLine = 8;
    s.status = "FREE";
    s.isDesc = "DESC";
    s.address=address;
    s.classification = "游戏";
    var box = $("#game");
    getItems(s, box);
}

function getOther() {
    var s = new select();
    s.order = "a.time";
    s.startLine = 0;
    s.endLine = 8;
    s.status = "FREE";
    s.isDesc = "DESC";
    s.address=address;
    s.classification = "其它";
    var box = $("#other");
    getItems(s, box);
}

function getItems(select, box) {
    $.ajax({
        url: "/item/getItemList",
        type: "post",
        contentType: 'application/json;charset=utf-8',
        dataType: "text",
        data: JSON.stringify(select),
        success: function (data) {
            var items = JSON.parse(data);
            items = items.list;
            box.empty();
            var length = items.length;
            for (var i = 0; i < length; i++) {
                box.append(" <div class=\"item\">\n" +
                    "                    <a href=\"item?itemid=" + items[i].id + "\">\n" +
                    "                        <div class=\"item-img\">\n" +
                    "                            <img alt=\"\" src=\"" + items[i].imgpath[0] + "\">\n" +
                    "                        </div>\n" +
                    "                    </a>\n" +
                    "                    <div class=\"span\" style=\"color: #ff1b46;font-size: 18px;\">￥" + items[i].price + "</div>\n" +
                    "                    <a href=\"item?itemid=" + items[i].id + "\">\n" +
                    "                        <div class=\"span\">" + items[i].name + "</div>\n" +
                    "                    </a>\n" +
                    "                    <div class=\"span\"><b class=\"icon-time\"></b>" + items[i].time + "</div>\n" +
                    "                </div>")
            }
        },
        error: function () {

        }
    })
}