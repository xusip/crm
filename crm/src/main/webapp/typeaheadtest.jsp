<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<base href="<%=basePath%>">
<head>
    <title>演示自动补全插件</title>
</head>


<%--jquery--%>
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<%--bootstarap--%>
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>

<%--typeahead--%>
<script type="text/javascript" src="jquery/bs_typeahead/bootstrap3-typeahead.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#customerName").typeahead({
            //source:["京东商城","百度科技","高德地图","字节跳动","动力节点","淘宝商城"]
            source:function (jquery,process) {
                $.ajax({
                    url:'workbench/transaction/queryAllCustomerName.do',
                    data:{
                        customerName:jquery
                    },
                    dataType:"json",
                    type:"post",
                    success:function (data) {
                        process(data)
                    }
                })
            }
        })
    })
</script>
<body>
    <input type="text" id="customerName">
</body>
</html>
