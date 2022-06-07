<%--
  Created by IntelliJ IDEA.
  User: yukunpeng
  Date: 2022/5/22
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h3 align="center">Login System</h3>
    <hr/>
    <div align="center">
        <form action="login">
            <table border="1">
                <tr>
                    <th>User Name:</th>
                    <td><input type="text" name="username"/> </td>
                </tr>
                <tr>
                    <th>Password:</th>
                    <td><input type="password" name="password"/> </td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Login"/> </td>
                </tr>
            </table>
        </form>
    </div>
    <script>
        var errori ='<%=request.getParameter("error")%>';
        //alert(errori);
        if(errori == '102'){
            alert("账号不存在!");
        }
        else if(errori == '101'){
            alert("密码错误！")
        }
    </script>
</body>
</html>
