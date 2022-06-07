<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<head>
    <meta charset="UTF-8"/>
    <title>商家管理界面</title>
    <script language="JavaScript" type="application/javascript">
        function confirm_del(seller_no) {
            if (confirm("确定要删除该条记录吗？")) {
                location.href = "sellerdel?seller_no=" + seller_no;
            }
        }
    </script>
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
<h1 align="center">商家管理界面</h1>
<div class="s_tab" id="s_tab">
    <form action="sellersearch" method="post">
        <input type="text" name="seller_search">
        &nbsp;&nbsp;
        <input type="submit" value="搜索">
    </form>
    <div class="s_tab_inner">
        <a href="../e_commerce_war_exploded/selleradd">新增商家</a>
        <a href="../e_commerce_war_exploded/select.jsp">回到上一级</a>
    </div>
</div>
<hr/>
<br/>
<div align="center">
    <table border="1">
        <tr>
            <th>序号</th>
            <th>商家名称</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${list}" var="seller" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${seller.seller_name}</td>
                <td>
                    <input type="button" value="删除" onclick="confirm_del(${seller.seller_no})" />
                    <input type="button" value="修改"
                           onclick="location.href='sellerupdate?seller_no=${seller.seller_no}'">
                    <input type="button" value="管理商品"
                           onclick="location.href='seller_commodities?seller_no=${seller.seller_no}'">
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>

</html>