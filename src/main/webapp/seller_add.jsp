<%--
  Created by IntelliJ IDEA.
  User: 13427
  Date: 2022/6/1
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加新商家</title>
</head>
<body>
    <br/>
    <h3 align="center"><u>添加新商家信息</u></h3>
    <br/>
    <div align="center">
        <form action="selleradd" method="post">
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
