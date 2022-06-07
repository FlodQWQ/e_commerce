<%--
  Created by IntelliJ IDEA.
  User: 13427
  Date: 2022/6/2
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>修改商家信息</title>
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
    <br/>
    <h2 align="center"><u>修改新商家信息</u></h2>
    <div class="s_tab" id="s_tab">
        <div class="s_tab_inner">
            <a href="../e_commerce_war_exploded/seller_management">回到上一级</a>
        </div>
    </div>
    <hr/>
    <br/>
    <div align="center">
        <form action="sellerupdate" method="post">
            <input type="hidden" name="seller_no" value="${seller.seller_no}"/>
            <input type="hidden" name="seller_commodities" value="${seller.seller_commodities}"/>
            <table border="1">
                <tr>
                    <th>商家名称:</th>
                    <td><input type="text" name="seller_name"/> </td>
                </tr>
                <tr>
                    <td colspan="2" align="right"><input type="submit"/> </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
