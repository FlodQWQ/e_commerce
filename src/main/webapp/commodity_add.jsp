<%--
  Created by IntelliJ IDEA.
  User: 13427
  Date: 2022/6/1
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<head>
    <title>添加新商品</title>
    <style>
        .table {
            width: 600px;
            margin: 0 auto;
            text-align: center;
        }
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
<script>
    var available = [];
    function del(obj) {
        updateArray(obj.id);
        let Id = "available";
        let direction = "left";
        if (obj.parentNode.parentNode.parentNode.parentNode.id === "available") {
            Id = "occupied";
            direction = "right";
        }
        const table = document.getElementById(Id);
        const rows = table.rows.length;
        const newTr = table.insertRow(rows);
        const newTd0 = newTr.insertCell(0);
        newTd0.class = "td";
        newTd0.align = "center";
        newTd0.width = "50%";
        newTd0.style = "float: " + direction + ";margin: 0px;padding: 0px;";
        newTd0.innerHTML = '<a href="#" onclick="del(this)" id="' +obj.id+'"'+'>' + obj.innerHTML + '</a>';
        obj.parentNode.parentNode.remove();
    }
    function updateArray(val){
        const index = available.indexOf(val);
        if(index>-1){
            available.splice(index,1);
        }
        else{
            available.push(val);
        }
        update();
    }

    function update(){
        document.getElementById("sellers").value = available.toString();
    }
</script>
<br>
<h2 align="center"><u>添加新商品信息</u></h2>
<div class="s_tab" id="s_tab">
    <div class="s_tab_inner">
        <a href="../e_commerce_war_exploded/commodity_management">回到上一级</a>
    </div>
</div>
<hr/>
<br />
<div align="center">
    <form action="commodityadd" method="post">
        <table border="1">
            <input id="sellers" type="hidden" name="commodity_sellers" value="" />
            <tr>
                <th>商品名称:</th>
                <td><input type="text" name="commodity_name" /> </td>
            </tr>
            <tr>
                <th>商品价格:</th>
                <td><input type="number" step="0.01" name="commodity_price" /> </td>
            </tr>
            <tr>
                <td colspan="2" align="right"><input type="submit"/> </td>
            </tr>
        </table>
    </form>
</div>
<div class="table">
    <h4>设置可用商家</h4>

    <table>
        <tr>
            <td>
                <table width="300px" align="center" id="available">
                    <c:forEach items="${sellers}" var="seller" varStatus="status">
                    <tr>
                        <td width="50%" align="center" style="float: left;margin: 0px;padding: 0px;">
                            <a href="#" onclick="del(this)" id="${seller.seller_no}">${seller.seller_name}</a>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
            </td>
            <td>
                <table width="300px" align="center" id="occupied">
                </table>
            </td>
        </tr>

    </table>
</div>
</body>

</html>
