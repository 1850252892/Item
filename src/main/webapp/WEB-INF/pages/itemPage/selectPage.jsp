<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <title>搜索</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="css/bootstrap-table.css">
    <link type="text/css" rel="stylesheet" href="css/font-awesome.css">
    <link rel="stylesheet" href="css/selectPage.css">
    <link type="text/css" rel="stylesheet" href="css/font-awesome.css">
    <link type="text/css" rel="stylesheet" href="css/pagination.css">

    <script src="/js/jquery-3.1.1.js"></script>
    <script src="/js/jquery.pagination.js"></script>
    <script src="/js/laydate.js"></script>
    <script src="/js/myJs/selectPage.js"></script>

    <script>
        lay('#version').html('-v' + laydate.v);
        //常规用法
        laydate.render({
            elem: '#startTime' //指定元素
        });
        laydate.render({
            elem: '#endTime' //指定元素
        });
    </script>
    <style>
        .demo-input {
            padding-left: 10px;
            height: 38px;
            min-width: 100px;
            line-height: 38px;
            border: 1px solid #e6e6e6;
            background-color: #fff;
            border-radius: 2px;
        }

    </style>

</head>
<body>
<jsp:include page="../head.jsp"></jsp:include>

<div class="item_box">
    <div class="select_nav">
        <ol class="n_li breadcrumb" id="alls">
            <li><a href="/selectPage">全部</a></li>
        </ol>
        <div class="line_s">
            <ol class="n_li breadcrumb" id="i_class">
                <li class="active">可选分类</li>
            </ol>
        </div>
        <div class="line_s">
            <ol class="n_li breadcrumb">
                <li class="active">筛选条件</li>
            </ol>

            <input  class="n_li demo-input" id="lowPrice" placeholder="￥最低价">
            <input class="n_li demo-input" id="highPrice" placeholder="￥最高价">
            <input type="text" class="n_li demo-input" placeholder="开始时间" id="startTime">
            <input type="text" class="n_li demo-input" placeholder="结束时间" id="endTime">
            <button class="n_li btn " type="button" id="i_clean">清空</button>
            <button class="n_li btn " type="button" id="i_filter">筛选</button>


        </div>

        <div class="line_s" id="sort">
            <ol class="n_li breadcrumb" id="sort_by">
                <li class="active">排序规则</li>
            </ol>
            <button type="button" class="btn btn-default" id="s_default">默认</button>
            <button type="button" class="btn btn-default" id="s_date">发布时间</button>
            <button type="button" class="btn btn-default" id="s_price">参考价格</button>
            <%--<button type="button" class="btn btn-default" id="s_hot">热度</button>--%>

        </div>
    </div>
    <div class="item_list">
        等待数据加载中...
    </div>
    <a href="#top" style="display: block;width: auto;height: auto;overflow: auto;">
        <div id="item_pages" class="m-style"></div>
    </a>
</div>


</body>
</html>