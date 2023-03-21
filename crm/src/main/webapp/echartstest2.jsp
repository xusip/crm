<%--
  Created by IntelliJ IDEA.
  User: xsp
  Date: 2023/3/13
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<base href="<%=basePath%>">
<head>
    <title>演示图表生成插件echarts</title>
</head>
<%--jquery--%>
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<%--echarts--%>
<script type="text/javascript" src="jquery/echars/echarts.min.js"></script>
<script type="text/javascript">
    $(function () {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '交易统计图表',
                subtext: '交易表中各个阶段的数量'
            },
            tooltip: {
                trigger: 'item',
                formatter: '{a} <br/>{b} : {c}'
            },
            toolbox: {
                feature: {
                    dataView: { readOnly: false },
                    restore: {},
                    saveAsImage: {}
                }
            },
            series: [
                {
                    name: 'Expected',
                    type: 'funnel',
                    left: '10%',
                    width: '80%',
                    label: {
                        formatter: '{b}Expected'
                    },
                    labelLine: {
                        show: true
                    },
                    itemStyle: {
                        opacity: 0.7
                    },
                    emphasis: {
                        label: {
                            position: 'inside',
                            formatter: '{b}Expected: {c}'
                        }
                    },
                    data: [
                        { value: 60, name: 'Visit' },
                        { value: 40, name: 'Inquiry' },
                        { value: 20, name: 'Order' },
                        { value: 80, name: 'Click' },
                        { value: 100, name: 'Show' }
                    ]
                },


            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    })
</script>
<body>
    <div id="main" style="width: 600px;height:400px;"></div>
</body>
</html>
