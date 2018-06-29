var pages;
var s;
var f_date = false;
var f_price = false;

$(function () {
    s = new select();
    var info = getUrlParam("s");
    var type = getUrlParam("c");
    var nav = $("#alls");
    $("#inputNumber").val(info);
    if (type != null) {
        nav.append("<li><a href='/selectPage?c=" + type + "'>" + type + "</a></li>");
    }
    if (info != null) {
        nav.append("<li><a href='/selectPage?s=" + info + "'>" + info + "</a></li>");
    }
    s.startLine = 0 * 40;
    s.endLine = 40;
    initSelect();
    getItemCount(s);
    var sort_by = $("#sort_by");
    $("#s_default").bind('click', function () {
        s.order = null;
        s.isDesc = null;
        getItemCount(s);
        sort_by.empty();
        sort_by.append('<li class="active">排序规则</li>');
    });
    $("#s_date").bind('click', function () {
        sort_by.empty();
        sort_by.append('<li class="active">排序规则</li>');
        sort_by.append('<li class="active">发布时间</li>');
        s.order = "time";
        f_date = !f_date;
        if (f_date) {
            s.isDesc = "DESC";
            sort_by.append('<li class="active">降序</li>')
        }
        else {
            s.isDesc = null;
            sort_by.append('<li class="active">升序</li>')
        }
        getItemCount(s);


    });
    $("#s_price").bind('click', function () {
        sort_by.empty();
        sort_by.append('<li class="active">排序规则</li>');
        sort_by.append('<li class="active">参考价</li>');
        s.order = "price";
        f_price = !f_price;
        if (f_price) {
            s.isDesc = null;
            sort_by.append('<li class="active">升序</li>')
        }
        else {
            s.isDesc = "DESC";
            sort_by.append('<li class="active">降序</li>')
        }
        getItemCount(s);

        sort_by.append('<li class="active"></li>')
    });
    $("#s_hot").bind('click', function () {
        // s.order="price";
        // s.isDesc="DESC";
        // getItemCount(s);
    });

    $("#i_clean").bind('click', function () {
        $("#startTime").val("");
        $("#endTime").val("");
        $("#highPrice").val("");
        $("#lowPrice").val("");
    });
    $("#i_filter").bind('click', function () {
        pages = null;
        var sT = $("#startTime").val();
        var eT = $("#endTime").val();
        var hP = $("#highPrice").val();
        var lP = $("#lowPrice").val();
        s.startTime = "1999-01-01";
        s.endTime = "2050-12-30";
        s.lowPrice = "-1";
        s.highPrice = "9999999";
        if (sT != "")
            s.startTime = sT;
        if (eT != "")
            s.endTime = eT;
        if (hP != "")
            s.highPrice = hP;
        if (lP != "")
            s.lowPrice = lP;
        getItemCount(s);

    })
});

function initSelect() {
    var p=$.cookie("province");
    var c=$.cookie("city");
    var d=$.cookie("district");
    var  address=p+"-"+c;

    var info = getUrlParam("s");
    var type = getUrlParam("c");

    if (type != null) {
        s.classification = type;
    }
    if (info != null) {
        s.detail = info;
        s.name = info;
    }
    s.address=address;
}

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

var page = function (count) {
    $('#item_pages').pagination({
        pageCount: count,
        mode: 'fixed',
        jump: true,
        coping: true,
        homePage: '首页',
        endPage: '末页',
        prevContent: '上页',
        nextContent: '下页',
        callback: function (api) {
            window.location.href = "#top";
            var p = api.getCurrent() - 1;
            initSelect();
            s.startLine = p * 40;
            s.endLine = 40;

            getItemCount(s)
        }
    });
};

function getItemCount(data) {
    var jdata = JSON.stringify(data);
    $.ajax({
        url: '/item/getItemList',
        type: 'post',
        dataType: 'text',
        contentType: 'application/json;charset=utf-8',
        data: jdata,
        success: function (data) {
            var itemm = JSON.parse(data);
            var count = itemm.count;
            if (pages == null)
                pages = new page(count / 40 + 1);
            showItem(itemm.list, itemm.classList);
        },
        error: function () {

        }
    })
}

function showItem(items, clist) {
    var box = $(".item_list");
    box.empty();
    var length = items.length;
    if (length <= 0) {
        box.append("<div style='width:100%;height: 35px;line-height: 35px;text-align: center;' >没有查询到匹配数据</div>");
    }
    for (var i = 0; i < length; i++) {
        var li = ' <a href="item?itemid=' + items[i].id + '"><div class="item_li"><img src="' + items[i].imgpath[0] + '" alt="">\n' +
            '            <div class="item-mon"><span>￥</span><span>' + items[i].price + '</span></div>\n' +
            '            <div class="item-name"><span></span><span>' + items[i].name + '</span></div>\n' +
            '            <div class="item-name"><span class="icon-time"></span><span>' + items[i].time + '</span></div>\n' +
            '        </div></a>';
        box.append(li);
    }

    var c = $("#i_class");
    c.empty();
    c.append(' <li class="active">可选分类</li>');
    var info = getUrlParam("s");
    length = clist.length;
    for (var j = 0; j < length; j++) {
        if (info != null)
            var li = '<li><a href="/selectPage?s=' + info + '&c=' + clist[j] + '">' + clist[j] + '</a></li>';
        else
            var li = '<li><a href="/selectPage?c=' + clist[j] + '">' + clist[j] + '</a></li>';
        c.append(li);
    }

}

var select = function () {
    var id;
    var startTime;
    var endTime;
    var name;
    var detail;
    var lowPrice;
    var highPrice;
    var uid;
    var classificati;
    var order;
    var isDesc;
    var startLine;
    var endLine;
    var address;
};