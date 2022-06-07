<%--
  Created by IntelliJ IDEA.
  User: 13427
  Date: 2022/6/2
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>商家商品管理界面</title>
    <link type="text/css" href="./commonStyle.css" rel="stylesheet"/>
    <script language="JavaScript" type="application/javascript">
        var str = window.location.href;
        var index = window.location.href.indexOf('=');
        var seller_no = str.substring(index+1,str.length);
        function confirm_del(commodity_no) {
            if (confirm("确定要删除吗？")) {
                location.href = "sellercommoditydel?commodity_no=" + commodity_no +"&seller_no=" + seller_no;
            }
        }
        function update(commodity_no){
                window.location = "sellercommodityupdate?commodity_no=" + commodity_no + "&seller_no=" + seller_no;
        }

        function go_parent(){
            window.location="../e_commerce_war_exploded/seller_management";
        }
    </script>

</head>
<body>
<h1 align="center">商家管理界面</h1>
<div class="s_tab" id="s_tab">
    <form action="sellersearch" method="post">
        <input type="text" name="seller_search">
        &nbsp;&nbsp;
        <input type="submit" class="buttonSearch" value="搜索">
    </form>
    <div class="s_tab_inner">
        <a href="#" onclick="go_parent()">回到上一级</a>
        <a href="../e_commerce_war_exploded/"> 注销 </a>
    </div>
</div>
<hr/>
<br/>
<div align="center">
    <table class="styled-table">
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
                    <input type="button" value="删除" class="button" onclick="confirm_del(${commodity.commodity_no})" />
                    <input type="button" value="修改" class="button" onclick="update(${commodity.commodity_no})">
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
