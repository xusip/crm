<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <head>
    <title>演示文件下载</title>
        <script type="text/javascript" language="JavaScript">
            $(function () {
                console.log("1")
                $("#fileDownloadBtn").click(function () {
                    alert("aa");
                    console.log("2")
                    window.location.href="workbench/activity/fileDownload.do";
                })
            })
        </script>
</head>
<body>
    <input type="button" value="下载" id="fileDownloadBtn"/>
</body>
</html>
