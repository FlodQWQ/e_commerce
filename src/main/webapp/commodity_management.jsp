<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<head>
    <meta charset="UTF-8"/>
    <title>商品管理界面</title>
    <script language="JavaScript" type="application/javascript">
        function confirm_del(commodity_no) {
            if (confirm("确定要删除该条记录吗？")) {
                location.href = "commoditydel?commodity_no=" + commodity_no;
            }
        }
    </script>
    <style>
        #s_tab {
            float: none;
            zoom: 1;
        }

        .s_tab {
            line-height: 18px;
            margin: 0 auto;
            text-align: center;
        }
    </style>

</head>

<body>
<h1 align="center">商品管理界面</h1>
<div class="s_tab" id="s_tab">
    <form action="commoditysearch" method="post">
        <input type="text" name="commodity_search">
        &nbsp;&nbsp;
        <input type="submit" value="搜索">
    </form>
    <div class="s_tab_inner">
        <a href="../e_commerce_war_exploded/commodityadd">新增商品</a>
        <a href="../e_commerce_war_exploded/select.jsp">回到上一级</a>
    </div>
</div>
<hr/>
<br/>
<!-- <h4 align="center"><a href="empadd">添加新商品</a></h4> -->
<div align="center">
    <table border="1">
        <tr>
            <th>序号</th>
            <th>商品名称</th>
            <th>商品价格</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${list}" var="commodity" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${commodity.commodity_name}</td>
                <td>${commodity.commodity_price}</td>
                <td>
                    <input type="button" value="删除" onclick="confirm_del(${commodity.commodity_no})" />
                    <input type="button" value="修改"
                           onclick="location.href='commodityupdate?commodity_no=${commodity.commodity_no}'">
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>

</html>