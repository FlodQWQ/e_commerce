<%--
  Created by IntelliJ IDEA.
  User: 13427
  Date: 2022/6/7
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
    <link type="text/css" href="./commonStyle.css" rel="stylesheet"/>
</head>
<body>
<h2 align="center">注册用户</h2>
<div class="s_tab" id="s_tab">
    <div class="s_tab_inner">
        <a href="../e_commerce_war_exploded/">回到上一级</a>
    </div>
</div>
<hr/>
<div align="center">
    <form action="useradd" method="post" id="form">
        <table class="styled-table">
            <tr>
                <th>用户名:</th>
                <td><input type="text" id="username" name="username"/> </td>
            </tr>
            <tr>
                <th>密码:</th>
                <td><input type="text" id="password" name="password"/> </td>
            </tr>
            <tr>
                <td colspan="2" align="right"><input type="button" onclick="submit()" class="button" value="提交" /></td>
            </tr>
        </table>
    </form>
</div>
</body>
<script>
    function submit(){
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        alert(password)
        if((username==null || username === "") && (password==null || password === "")){
            alert("请输入账号和密码！");
        }
        else{
            document.getElementById("form").submit();
            alert("注册成功！")
        }
    }
</script>
<script>
    var errori = '<%=request.getParameter("error")%>';
    if (errori === '102') {
        alert("请输入账号密码！");
    }
    else if (errori === '101'){
        alert("账号已存在！")
    }
</script>
</html>
