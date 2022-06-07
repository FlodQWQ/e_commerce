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

        body{
            background-image: linear-gradient(135deg, #FAB2FF 10%, #1904E5 100%);
        }

        h1 {
            color: #FFFFFF;
            font-size: 5vmax;
            line-height: 1;
            font-weight: 900;
            margin-top: 40px;
            margin-bottom: 20px;
        }

        .center {
            background: rgba(89,97,249,0.2);
            padding: 30px;
            overflow: hidden;
            box-sizing: border-box;
            width: 600px;
            height: 550px;
            margin: 100px auto;
            text-align: center;
            box-shadow: 0 0 20px 6px #090b6f85;
        }
        a {
            color: #fff;
            font-size: x-large;
            padding: 10px 25px;
            border-radius: 50px;
            display: inline-block;
            border: 0;
            outline: 0;
            box-shadow: 0px 4px 20px 0px #49c628a6;
            background-image: linear-gradient(135deg, #70F570 10%, #49C628 100%);
            text-decoration: none;
            width: 200px;
            text-align: center;
            line-height: 50px;
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
        <a href="commodity_management">商品管理</a>
        <br>
        <br>
        <br>
        <a href="seller_management">商家管理</a>
        <br>
        <br>
        <br>
        <a href="../e_commerce_war_exploded/">注销</a>
    </div>
</body>
</html>
