<%--
  Created by IntelliJ IDEA.
  User: 13427
  Date: 2022/6/1
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>商家商品修改</title>
    <link type="text/css" href="./commonStyle.css" rel="stylesheet"/>
</head>
<body>
<script>
    var name = '${commodity.commodity_name}';
    var price = parseFloat(${commodity.commodity_price});
    var str = window.location.href;
    const index = window.location.href.indexOf("seller_no=");
    var seller_no = str.substring(index+10,str.length);
    seller_no.replace("#","");
    function go_parent(){
        window.location="../e_commerce_war_exploded/seller_commodities?seller_no=" + seller_no;
    }

</script>
<h2 align="center">商家商品信息修改</h2>
<div class="s_tab" id="s_tab">
    <div class="s_tab_inner">
        <a href="#" onclick="go_parent()">回到上一级</a>
        <a href="../e_commerce_war_exploded/"> 注销 </a>
    </div>
</div>
<hr/>
<br/>
<div align="center">
    <form action="sellercommodityupdate" method="post">
        <input type="hidden" name="commodity_no" value="${commodity.commodity_no}"/>
        <input id="seller_no" type="hidden" name="seller_no" value="" />
        <table class="styled-table">
            <tr>
                <th>商品名称:</th>
                <td><input id="name" type="text" name="commodity_name"/> </td>
            </tr>
            <tr>
                <th>商品价格:</th>
                <td><input id="price" type="number" step="0.01" name="commodity_price"/> </td>
            </tr>
            <tr>
                <td colspan="2" align="right"><input type="submit"/> </td>
            </tr>
        </table>
    </form>
</div>
</body>
<script>
    document.getElementById("name").value = name;
    document.getElementById("price").value = price;
    document.getElementById("seller_no").value = seller_no;
</script>
</html>
