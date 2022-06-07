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
    <meta charset="UTF-8">
    <title>Login Page in HTML with CSS Code Example</title>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">


    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link rel="stylesheet" href="./style.css">

</head>

<body>
<!-- partial:index.partial.html -->
<script>
    function login() {
        document.getElementById("form").submit();
    }
</script>
<div class="box-form">
    <div class="left">
        <div class="overlay">
            <h1>E-Commerce System</h1>
            <p>an Extraordinary system which let you Control all your business</p>
        </div>
    </div>


    <div class="right">
        <h5>Login</h5>
        <p>Don't have an account? <a href="useradd">Creat Your Account</a> it takes less than a minute</p>
        <div class="inputs">
            <form action="login" id="form">
                <input type="text" placeholder="username" name="username">
                <br>
                <input type="password" placeholder="password" name="password">
            </form>
        </div>

        <br><br>

        <div class="remember-me--forget-password">
            <!-- Angular -->
            <label>
                <input type="checkbox" name="item" checked />
                <span class="text-checkbox">Remember me</span>
            </label>
            <p>forget password?</p>
        </div>

        <br>
        <button onclick="login()">Login</button>
    </div>

</div>
<!-- partial -->

</body>

<script>
    var errori = '<%=request.getParameter("error")%>';
    //alert(errori);
    if (errori == '102') {
        alert("账号不存在!");
    }
    else if (errori == '101') {
        alert("密码错误！")
    }
</script>

</html>