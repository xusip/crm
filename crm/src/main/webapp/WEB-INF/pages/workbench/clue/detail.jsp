<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<head>
	<base href="<%=basePath%>">
<meta charset="UTF-8">

<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>

<script type="text/javascript">

	//默认情况下取消和保存按钮是隐藏的
	var cancelAndSaveBtnDefault = true;
	
	$(function(){
		$("#remark").focus(function(){
			if(cancelAndSaveBtnDefault){
				//设置remarkDiv的高度为130px
				$("#remarkDiv").css("height","130px");
				//显示
				$("#cancelAndSaveBtn").show("2000");
				cancelAndSaveBtnDefault = false;
			}
		});
		
		$("#cancelBtn").click(function(){
			//显示
			$("#cancelAndSaveBtn").hide();
			//设置remarkDiv的高度为130px
			$("#remarkDiv").css("height","90px");
			cancelAndSaveBtnDefault = true;
		});
		
		/*$(".remarkDiv").mouseover(function(){
			$(this).children("div").children("div").show();
		});*/
		$("#remarkDivList").on("mouseover",".remarkDiv",function () {
			$(this).children("div").children("div").show();

		});

		/*$(".remarkDiv").mouseout(function(){
			$(this).children("div").children("div").hide();
		});*/
		$("#remarkDivList").on("mouseout",".remarkDiv",function () {
			$(this).children("div").children("div").hide();
		});
		
		/*$(".myHref").mouseover(function(){
			$(this).children("span").css("color","red");
		});*/
		$("#remarkDivList").on("mouseover",".myHref",function () {
			$(this).children("span").css("color","red");
		});
		
		/*$(".myHref").mouseout(function(){
			$(this).children("span").css("color","#E6E6E6");
		});*/
		$("#remarkDivList").on("mouseout",".myHref",function () {
			$(this).children("span").css("color","#E6E6E6");
		});

		$("#saveClueRemarkBtn").click(function () {
			var noteContent=$.trim($("#remark").val());
			var clueId=$("#clueId").val();
			$.ajax({
				url:'workbench/clue/saveClueRemark.do',
				data:{
					noteContent:noteContent,
					clueId:clueId
				},
				type:'post',
				dataType:'json',
				success:function (data) {
					if (data.code=="1"){
						$("#remark").val("");
						var htmlStr="<div class=\"remarkDiv\" id=div_\""+data.retData.id+"\" style=\"height: 60px;\">\n" +
								"<input value=\""+data.retData.id+"\" hidden>"+
								"\t\t\t\t<img title=\"${sessionScope.sessionUser.name}\" src=\"image/user-thumbnail.png\" style=\"width: 30px; height:30px;\">\n" +
								"\t\t\t\t<div style=\"position: relative; top: -40px; left: 40px;\" >\n" +
								"\t\t\t\t\t<h5 id=\"h5_"+data.retData.id+"\">"+noteContent+"</h5>\n" +
								"\t\t\t\t\t<font color=\"gray\">线索</font> <font color=\"gray\">-</font> <b>${clue.fullname}${clue.appellation}-${clue.company}</b> <small id=\"small_"+data.retData.id+"\" style=\"color: gray;\">" +data.retData.createTime+" 由${sessionScope.sessionUser.name}创建</small>\n" +
								"\t\t\t\t\t<div style=\"position: relative; left: 500px; top: -30px; height: 30px; width: 100px; display: none;\">\n" +
								"\t\t\t\t\t\t<a class=\"myHref\" name=\"editA\" remarkId=\""+data.retData.id+"\" href=\"javascript:void(0);\"><span class=\"glyphicon glyphicon-edit\" style=\"font-size: 20px; color: #E6E6E6;\"></span></a>\n" +
								"\t\t\t\t\t\t<a class=\"myHref\" name=\"deleteA\" remarkId=\""+data.retData.id+"\" href=\"javascript:void(0);\"><span class=\"glyphicon glyphicon-remove\" style=\"font-size: 20px; color: #E6E6E6;\"></span></a>\n" +
								"\t\t\t\t\t</div>\n" +
								"\t\t\t\t</div>\n" +
								"\t\t\t</div>"
						$("#remarkDiv").before(htmlStr);
					}
					else{
						alert(data.message);
					}
				}
			})
		});

		$("#remarkDivList").on("click","a[name='editA']",function () {
			var id=$(this).attr('remarkId');
			var noteContent=$("#h5_"+id).text();
			//alert("id="+id+",noteContent"+noteContent);
			$("#remarkId").val(id);
			$("#edit-noteContent").val(noteContent);
			$("#editRemarkModal").modal("show");
		});

		$("#updateRemarkBtn").click(function () {
			var noteContent=$.trim($("#edit-noteContent").val());
			var id=$("#remarkId").val();
			$.ajax({
				url:'workbench/clue/updateClueRemarkById.do',
				data:{
					noteContent:noteContent,
					id:id
				},
				type:'post',
				dataType:'json',
				success:function (data) {
					if (data.code=="1"){
						$("#small_"+data.retData.id).text(data.retData.editTime+" 由${sessionScope.sessionUser.name}修改");
						$("#h5_"+id).text(noteContent);
						$("#editRemarkModal").modal("hide");
					}
					else{
						alert(data.message);
						$("#editRemarkModal").modal("show");
					}
				}
			})
		});

		//删除线索备注
		$("#remarkDivList").on("click","a[name=deleteA]",function () {
		 	if(window.confirm("确认删除？")){
				var id=$(this).attr("remarkId");
				$.ajax({
					url:'workbench/clue/deleteClueRemarkById',
					data:{
						id:id
					},
					type:'post',
					dataType:'json',
					success:function (data) {
						if(data.code=="1"){
							$("#div_"+id).remove();
						}else{
							alert(data.message);
						}
					}
				})
			}
		})

		$("#bundActivityBtn").click(function () {
			$("#bundModal").modal("show");
		})

        $("#activityName").keyup(function () {
            $("#searchActivity").click();
        });

		$("#searchActivity").click(function () {

			var clueId=$("#clueId").val();
			var name=$("#activityName").val();
			$.ajax({
				url:'workbench/clue/selectActivityListByName.do',
				data:{
					name:name,
					clueId:clueId
				},
				type:'post',
				dataType:'json',
				success:function (data) {
					if(data.code=="1"){
						var htmlStr="";
						$.each(data.retData,function (index,obj) {
							htmlStr+="<tr>\n" +
									"\t\t\t\t\t\t\t\t<td><input value=\""+obj.id+"\" type=\"checkbox\"/></td>\n" +
									"\t\t\t\t\t\t\t\t<td>"+obj.name+"</td>\n" +
									"\t\t\t\t\t\t\t\t<td>"+obj.startDate+"</td>\n" +
									"\t\t\t\t\t\t\t\t<td>"+obj.endDate+"</td>\n" +
									"\t\t\t\t\t\t\t\t<td>"+obj.owner+"</td>\n" +
									"\t\t\t\t\t\t\t</tr>"
						})
						$("#activityTbody").html(htmlStr);
					}else {
						alert(data.message);
					}
				}
			})
		})

		$("#checkAll").click(function () {
			$("#activityTbody input[type='checkbox']").prop("checked",this.checked);
		})
		$("#activityTbody").on("click","input[type='checkbox']",function () {
			var sizeAll=$("#activityTbody input[type='checkbox']").size();
			var size=$("#activityTbody input[type='checkbox']:checked").size();
			if(size!=sizeAll){
				$("#checkAll").prop("checked",false);
			}else {
				$("#checkAll").prop("checked",true);
			}
		})



		$("#saveRelation").click(function () {
			var id=$("#activityTbody input[type='checkbox']:checked");
			if(id.size()==0){
				alert("请至少选择一个市场活动进行关联！！");
				return;
			}
			var clueId=$("#clueId").val();
			var ids="";
			$.each(id,function () {
				ids+="activityId="+this.value+"&"
			});
            ids=ids+"clueId="+clueId;
			//ids=ids.substr(0,ids.length-1);

			$.ajax({
				url:'workbench/clue/saveClueActivityRelationByList.do',
				data:ids,
				type:'post',
				dataType:'json',
				success:function (data) {
					if(data.code=="1"){
						var htmlStr="";
						$.each(data.retData,function (index,obj) {
							htmlStr+="<tr id=\"tr_"+obj.id+"\">\n" +
									"\t\t\t\t\t\t\t\t<td>"+obj.name+"</td>\n" +
									"\t\t\t\t\t\t\t\t<td>"+obj.startDate+"</td>\n" +
									"\t\t\t\t\t\t\t\t<td>"+obj.endDate+"</td>\n" +
									"\t\t\t\t\t\t\t\t<td>"+obj.owner+"</td>\n" +
									"\t\t\t\t\t\t\t\t<td><a href=\"javascript:void(0);\"  name=\"removeCAR\"   style=\"text-decoration: none;\" value=\""+obj.id+"\"><span class=\"glyphicon glyphicon-remove\"></span>解除关联</a></td>\n" +
									"\t\t\t\t\t\t\t</tr>";
						})
						$("#tbodyActivity").append(htmlStr);
                        $("#activityTbody").html("");
                        $("#bundModal").modal("hide");
					}else{
						alert(data.message);
                        $("#bundModal").modal("show");
					}
				}
			})
		});

		$("#notSaveRelation").click(function () {
            $("#activityTbody").html("");
            $("#bundModal").modal("hide");
        })

		//删除关联的市场活动
        $("#tbodyActivity").on("click","a[name='removeCAR']",function () {
            var activityId=$(this).attr("value");
            var clueId="${clue.id}";
            //alert(clueId);
			if(window.confirm("确认删除？")){
				$.ajax({
					url:'workbench/clue/deleteClueActivityRelationByClueIdActId.do',
					data:{
						activityId:activityId,
						clueId:clueId
					},
					type:'post',
					dataType:'json',
					success:function (data) {
						if (data.code=="1"){
							$("#tr_"+activityId).remove();
						}else {
							alert(data.message);
						}
					}
				})
			}

        })

		//线索转换
		$("#convertClueBtn").click(function () {
			window.location.href='workbench/clue/convert.do?id=${clue.id}';
		})
	});
	
</script>

</head>
<body>

	<!-- 关联市场活动的模态窗口 -->
	<div class="modal fade" id="bundModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 80%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">关联市场活动</h4>
				</div>
				<div class="modal-body">
					<div class="btn-group" style="position: relative; top: 18%; left: 8px;">
						<%--<form class="form-inline" role="form">--%>
						  <div class="form-group has-feedback">
						    <input type="text" class="form-control" style="width: 300px;" placeholder="请输入市场活动名称，支持模糊查询" id="activityName">
						    <a id="searchActivity" style="text-decoration: none;"><span class="glyphicon glyphicon-search form-control-feedback"></span></a>
						  </div>
						<%--</form>--%>
					</div>
					<table id="activityTable" class="table table-hover" style="width: 900px; position: relative;top: 10px;">
						<thead>
							<tr style="color: #B3B3B3;">
								<td><input type="checkbox" id="checkAll"/></td>
								<td>名称</td>
								<td>开始日期</td>
								<td>结束日期</td>
								<td>所有者</td>
								<td></td>
							</tr>
						</thead>
						<tbody id="activityTbody">
							<%--<tr>
								<td><input type="checkbox"/></td>
								<td>发传单</td>
								<td>2020-10-10</td>
								<td>2020-10-20</td>
								<td>zhangsan</td>
							</tr>
							<tr>
								<td><input type="checkbox"/></td>
								<td>发传单</td>
								<td>2020-10-10</td>
								<td>2020-10-20</td>
								<td>zhangsan</td>
							</tr>--%>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" id="notSaveRelation" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" id="saveRelation" class="btn btn-primary" data-dismiss="modal">关联</button>
				</div>
			</div>
		</div>
	</div>


	<!-- 返回按钮 -->
	<div style="position: relative; top: 35px; left: 10px;">
		<a href="javascript:void(0);" onclick="window.history.back();"><span class="glyphicon glyphicon-arrow-left" style="font-size: 20px; color: #DDDDDD"></span></a>
	</div>


	<!-- 修改线索活动备注的模态窗口 -->
	<div class="modal fade" id="editRemarkModal" role="dialog">
		<%-- 备注的id --%>
		<input type="hidden" id="remarkId">
		<div class="modal-dialog" role="document" style="width: 40%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改备注</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form">
						<input type="hidden" id="edit-id">
						<div class="form-group">
							<label for="edit-noteContent" class="col-sm-2 control-label">内容</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-noteContent"></textarea>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="updateRemarkBtn">更新</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 大标题 -->
	<div style="position: relative; left: 40px; top: -30px;">
		<div class="page-header">
			<h3>${clue.fullname }${clue.appellation} <small>动力节点</small></h3>
		</div>
		<div style="position: relative; height: 50px; width: 500px;  top: -72px; left: 700px;">
			<button type="button" class="btn btn-default" id="convertClueBtn"><span class="glyphicon glyphicon-retweet"></span> 转换</button>
			
		</div>
	</div>
	
	<br/>
	<br/>
	<br/>

	<!-- 详细信息 -->
	<div style="position: relative; top: -70px;">
		<div style="position: relative; left: 40px; height: 30px;">
			<input id="clueId" value="${clue.id}" hidden>
			<div style="width: 300px; color: gray;">名称</div>
			<div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${clue.fullname }${clue.appellation}</b></div>
			<div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">所有者</div>
			<div style="width: 300px;position: relative; left: 650px; top: -60px;"><b>${clue.owner}</b></div>
			<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
			<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 10px;">
			<div style="width: 300px; color: gray;">公司</div>
			<div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${clue.company}</b></div>
			<div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">职位</div>
			<div style="width: 300px;position: relative; left: 650px; top: -60px;"><b>${clue.job}</b></div>
			<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
			<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 20px;">
			<div style="width: 300px; color: gray;">邮箱</div>
			<div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${clue.email}</b></div>
			<div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">公司座机</div>
			<div style="width: 300px;position: relative; left: 650px; top: -60px;"><b>${clue.phone}</b></div>
			<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
			<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 30px;">
			<div style="width: 300px; color: gray;">公司网站</div>
			<div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${clue.website}</b></div>
			<div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">手机</div>
			<div style="width: 300px;position: relative; left: 650px; top: -60px;"><b>${clue.mphone}</b></div>
			<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
			<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 40px;">
			<div style="width: 300px; color: gray;">线索状态</div>
			<div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${clue.state}</b></div>
			<div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">线索来源</div>
			<div style="width: 300px;position: relative; left: 650px; top: -60px;"><b>${clue.source}</b></div>
			<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
			<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 50px;">
			<div style="width: 300px; color: gray;">创建者</div>
			<div style="width: 500px;position: relative; left: 200px; top: -20px;"><b>${clue.createBy}&nbsp;</b><small style="font-size: 10px; color: gray;">${clue.createTime}</small></div>
			<div style="height: 1px; width: 550px; background: #D5D5D5; position: relative; top: -20px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 60px;">
			<div style="width: 300px; color: gray;">修改者</div>
			<div style="width: 500px;position: relative; left: 200px; top: -20px;"><b>${clue.editBy}</b><small style="font-size: 10px; color: gray;">${clue.editTime}</small></div>
			<div style="height: 1px; width: 550px; background: #D5D5D5; position: relative; top: -20px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 70px;">
			<div style="width: 300px; color: gray;">描述</div>
			<div style="width: 630px;position: relative; left: 200px; top: -20px;">
				<b>
					${clue.description}
				</b>
			</div>
			<div style="height: 1px; width: 850px; background: #D5D5D5; position: relative; top: -20px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 80px;">
			<div style="width: 300px; color: gray;">联系纪要</div>
			<div style="width: 630px;position: relative; left: 200px; top: -20px;">
				<b>
					${clue.contactSummary}
				</b>
			</div>
			<div style="height: 1px; width: 850px; background: #D5D5D5; position: relative; top: -20px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 90px;">
			<div style="width: 300px; color: gray;">下次联系时间</div>
			<div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${clue.nextContactTime}</b></div>
			<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -20px; "></div>
		</div>
        <div style="position: relative; left: 40px; height: 30px; top: 100px;">
            <div style="width: 300px; color: gray;">详细地址</div>
            <div style="width: 630px;position: relative; left: 200px; top: -20px;">
                <b>
					${clue.address}
                </b>
            </div>
            <div style="height: 1px; width: 850px; background: #D5D5D5; position: relative; top: -20px;"></div>
        </div>
	</div>
	
	<!-- 备注 -->
	<div id="remarkDivList" style="position: relative; top: 40px; left: 40px;">
		<div class="page-header">
			<h4>备注</h4>
		</div>

		<c:forEach items="${clueRemarkList}" var="clueRemark">
			<div class="remarkDiv" id="div_${clueRemark.id}" style="height: 60px;">
				<input value="${clueRemark.id}" hidden>
				<img title="${clueRemark.editFlag==0?clueRemark.createBy:clueRemark.editBy}" src="image/user-thumbnail.png" style="width: 30px; height:30px;">
				<div style="position: relative; top: -40px; left: 40px;" >
					<h5 id="h5_${clueRemark.id}">${clueRemark.noteContent}</h5>
					<font color="gray">线索</font> <font color="gray">-</font> <b>${clue.fullname}${clue.appellation}-${clue.company}</b> <small id="small_${clueRemark.id}" style="color: gray;"> ${clueRemark.editFlag==0?clueRemark.createTime:clueRemark.editTime} 由${clueRemark.editFlag==0?clueRemark.createBy:clueRemark.editBy}${clueRemark.editFlag==0?'创建':'修改'}</small>
					<div style="position: relative; left: 500px; top: -30px; height: 30px; width: 100px; display: none;">
						<a class="myHref" name="editA" remarkId="${clueRemark.id}" href="javascript:void(0);"><span class="glyphicon glyphicon-edit" style="font-size: 20px; color: #E6E6E6;"></span></a>
						<a class="myHref" name="deleteA" remarkId="${clueRemark.id}" href="javascript:void(0);"><span class="glyphicon glyphicon-remove" style="font-size: 20px; color: #E6E6E6;"></span></a>
					</div>
				</div>
			</div>
		</c:forEach>
		<%--<!-- 备注1 -->
		<div class="remarkDiv" style="height: 60px;">
			<img title="zhangsan" src="image/user-thumbnail.png" style="width: 30px; height:30px;">
			<div style="position: relative; top: -40px; left: 40px;" >
				<h5>哎呦！</h5>
				<font color="gray">线索</font> <font color="gray">-</font> <b>李四先生-动力节点</b> <small style="color: gray;"> 2017-01-22 10:10:10 由zhangsan</small>
				<div style="position: relative; left: 500px; top: -30px; height: 30px; width: 100px; display: none;">
					<a class="myHref" href="javascript:void(0);"><span class="glyphicon glyphicon-edit" style="font-size: 20px; color: #E6E6E6;"></span></a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a class="myHref" href="javascript:void(0);"><span class="glyphicon glyphicon-remove" style="font-size: 20px; color: #E6E6E6;"></span></a>
				</div>
			</div>
		</div>
		
		<!-- 备注2 -->
		<div class="remarkDiv" style="height: 60px;">
			<img title="zhangsan" src="image/user-thumbnail.png" style="width: 30px; height:30px;">
			<div style="position: relative; top: -40px; left: 40px;" >
				<h5>呵呵！</h5>
				<font color="gray">线索</font> <font color="gray">-</font> <b>李四先生-动力节点</b> <small style="color: gray;"> 2017-01-22 10:20:10 由zhangsan</small>
				<div style="position: relative; left: 500px; top: -30px; height: 30px; width: 100px; display: none;">
					<a class="myHref" href="javascript:void(0);"><span class="glyphicon glyphicon-edit" style="font-size: 20px; color: #E6E6E6;"></span></a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a class="myHref" href="javascript:void(0);"><span class="glyphicon glyphicon-remove" style="font-size: 20px; color: #E6E6E6;"></span></a>
				</div>
			</div>
		</div>--%>
		
		<div id="remarkDiv" style="background-color: #E6E6E6; width: 870px; height: 90px;">
			<form role="form" style="position: relative;top: 10px; left: 10px;">
				<textarea id="remark" class="form-control" style="width: 850px; resize : none;" rows="2"  placeholder="添加备注..."></textarea>
				<p id="cancelAndSaveBtn" style="position: relative;left: 737px; top: 10px; display: none;">
					<button id="cancelBtn" type="button" class="btn btn-default">取消</button>
					<button type="button" class="btn btn-primary" id="saveClueRemarkBtn">保存</button>
				</p>
			</form>
		</div>
	</div>
	
	<!-- 市场活动 -->
	<div>
		<div style="position: relative; top: 60px; left: 40px;">
			<div class="page-header">
				<h4>市场活动</h4>
			</div>
			<div style="position: relative;top: 0px;">
				<table class="table table-hover" style="width: 900px;">
					<thead>
						<tr style="color: #B3B3B3;">
							<td>名称</td>
							<td>开始日期</td>
							<td>结束日期</td>
							<td>所有者</td>
							<td></td>
						</tr>
					</thead>
					<tbody id="tbodyActivity">
						<c:forEach items="${activityList}" var="activity">
							<tr id="tr_${activity.id}">
								<td>${activity.name}</td>
								<td>${activity.startDate}</td>
								<td>${activity.endDate}</td>
								<td>${activity.owner}</td>
								<td><a href="javascript:void(0);" name="removeCAR"  style="text-decoration: none;" value="${activity.id}"><span class="glyphicon glyphicon-remove"></span>解除关联</a></td>
							</tr>
						</c:forEach>
						<%--<tr>
							<td>发传单</td>
							<td>2020-10-10</td>
							<td>2020-10-20</td>
							<td>zhangsan</td>
							<td><a href="javascript:void(0);"  style="text-decoration: none;"><span class="glyphicon glyphicon-remove"></span>解除关联</a></td>
						</tr>
						<tr>
							<td>发传单</td>
							<td>2020-10-10</td>
							<td>2020-10-20</td>
							<td>zhangsan</td>
							<td><a href="javascript:void(0);"  style="text-decoration: none;"><span class="glyphicon glyphicon-remove"></span>解除关联</a></td>
						</tr>--%>
					</tbody>
				</table>
			</div>
			
			<div>
				<a data-toggle="modal" id="bundActivityBtn" <%--data-target="#bundModal"--%> style="text-decoration: none;"><span class="glyphicon glyphicon-plus"></span>关联市场活动</a>
			</div>
		</div>
	</div>
	
	
	<div style="height: 200px;"></div>
</body>
</html>