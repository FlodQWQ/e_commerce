<%--
  Created by IntelliJ IDEA.
  User: 13427
  Date: 2022/5/31
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>选择界面</title>
    <style>
        .center {
            width: 600px;
            height: 600px;
            margin: 0 auto;
            text-align: center;
            background-color: #fff;
        }
        a {
            text-decoration: none;
            width: 200px;
            height: 100px;
            background-color: red;
            display: inline-block;
            color: white;
            text-align: center;
            line-height: 100px;
            font-size: x-large;
        }

        a:hover {
            background-color: orange;
        }
    </style>
</head>
<body>
    <div class="center">
        <h1>功能选择</h1>
        <br>
        <br>
        <a href="commodity_management">商品管理</a>
        <br>
        <br>
        <br>
        <a href="seller_management">商家管理</a>
    </div>
</body>
</html>
