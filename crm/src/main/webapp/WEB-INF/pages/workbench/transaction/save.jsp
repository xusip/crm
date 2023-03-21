<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<head>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">
	<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

	<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
	<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="jquery/bs_typeahead/bootstrap3-typeahead.min.js"></script>
</head>
	<script type="text/javascript">
		$(function () {
			$("#openActivity").click(function () {
				$("#tbodyActivity").html("");
				$("#searchActivity").val("");
				$("#findMarketActivity").modal("show");
			})

			$("#openContacts").click(function () {
				$("#tbodyContacts").html("");
				$("#searchContacts").val("");
				$("#findContacts").modal("show");
			})

			$("#searchActivity").keyup(function () {
				var activityName = $("#searchActivity").val();

				$.ajax({
					url:"workbench/transaction/searchActivity.do",
					dataType:'json',
					type:'post',
					data:{
						name:activityName
					},
					success:function (data) {
						var htmlStr="";
						if(data.code=="1"){
							$.each(data.retData,function (index,obj) {

								htmlStr+="<tr>" +
										"<td><input type=\"radio\" id='"+obj.id+"' value='"+obj.id+"' '/></td>" +
										"<td id='name_"+obj.id+"' >"+obj.name+"</td>" +
										"<td>"+obj.startDate+"</td>" +
										"<td>"+obj.endDate+"</td>" +
										"<td>"+obj.owner+"</td>" +
										"</tr>"
							});
							$("#tbodyActivity").html(htmlStr);


						}
						else {
							alert(data.message)
						}
					}
				})
			});

			$("#tbodyActivity").on("click","input[type='radio']",function () {
				$("#findMarketActivity").modal("hide");
				//alert(this.value)
				var actName=$("#name_"+this.value).text()
				$("#create-activitySrc").val(actName);
				$("#activityId").val(this.value)
			})

			$("#searchContacts").keyup(function () {
				var contactsName=$("#searchContacts").val();
				$.ajax({
					url:"workbench/transaction/searchContacts.do",
					dataType:"json",
					type:"post",
					data:{
						fullname:contactsName
					},
					success:function (data) {
						if(data.code=="1"){
							var htmlStr="";
							$.each(data.retData,function (index,obj) {
								htmlStr+="<tr>" +
										"<td><input type=\"radio\" name=\"contacts\" value=\""+obj.id+"\"/></td>" +
										"<td id='fullname_"+obj.id+"'>"+obj.fullname+"</td>" +
										"<td>"+obj.email+"</td>" +
										"<td>"+obj.mphone+"</td>" +
										"</tr>"
							})
							$("#tbodyContacts").html(htmlStr);
						}else {
							alert(data.message);
						}
					}
				})
			});

			$("#tbodyContacts").on("click","input[type='radio']",function () {
				$("#findContacts").modal("hide");
				var contactsId=this.value
				var contactsName=$("#fullname_"+this.value).text();
				$("#create-contactsName").val(contactsName)
				$("#contactsId").val(this.value)
			})




			$(".mydate").datetimepicker({
				language:"zh-CN",
				format:"yyyy-mm-dd",
				minView:"month",
				initialDate:new Date(),
				autoclose:true,
				todayBtn:true,
				clearBtn:true,
				pickerPosition:"top-right"
			})

			$(".mydate2").datetimepicker({
				language:"zh-CN",
				format:"yyyy-mm-dd",
				minView:"month",
				initialDate:new Date(),
				autoclose:true,
				todayBtn:true,
				clearBtn:true,
				pickerPosition:"bottom-right"
			});

			$("#create-transactionStage").change(function () {
                //var stageValue=$(this).find("option:selected").text();
				var stageValue=$("#create-transactionStage option:selected").text()
				if(stageValue==""){
					$("#create-possibility").val("");
					return;
				}
				else{
					$.ajax({
						url:'workbench/transaction/getPossibilityByStage.do',
						data:{
							stageValue:stageValue
						},
						dataType:"json",
						type:"post",
						success:function (data) {
							$("#create-possibility").val(data+"%");
						}
					})
				}

            });


			$("#create-accountName").typeahead({
				source:function (jquery,process) {
					$.ajax({
						url:"workbench/transaction/queryCustomerNameByName.do",
						data:{
							customerName: jquery
						},
						dataType:'json',
						type:"post",
						success:function (data) {
							process(data)
							$("#create-accountName").val(data);
						}
					})
				}
			});

			$("#saveTran").click(function () {

				var ownerName=$.trim($("#create-transactionOwner").val());
				var money=$.trim($("#create-amountOfMoney").val());
				var transactionName=$.trim($("#create-transactionName").val());
				var expectedClosingDate=$("#create-expectedClosingDate").val();
				var customerName=$.trim($("#create-accountName").val());
				var stage=$("#create-transactionStage").val();
				var type=$("#create-transactionType").val();
				var source=$("#create-clueSource").val();
				var activityId=$("#activityId").val();
				var contactsId=$("#contactsId").val();
				var description=$.trim($("#create-describe").val());
				var contactSummary=$.trim($("#create-contactSummary").val());
				var nextContactTime=$.trim($("#create-nextContactTime").val());
				//alert("activityId="+activityId+"contactsId="+contactsId+"stage="+stage);

				var isMoney=/^(([1-9]\d*)|0)$/;
				if(!isMoney.test(money)){
					alert("金额应为非负整数！！！");
					return;
				}
				if(transactionName==""){
					alert("名称不能为空！！！")
					return;
				}
				if(expectedClosingDate==""){
					alert("预计成交日期不能为空！！！")
					return;
				}
				if(customerName==""){
					alert("客户名称不能为空！！！")
					return;
				}
				if(stage==""){
					alert("阶段不能为空！！！")
					return;
				}

				$.ajax({
					url:"workbench/transaction/saveCreateTran.do",
					type:"post",
					dataType:"json",
					data:{
						owner:ownerName,
						money:money,
						name:transactionName,
						expectedDate:expectedClosingDate,
						customerName:customerName,
						stage:stage,
						type:type,
						source:source,
						activityId:activityId,
						contactsId:contactsId,
						description:description,
						contactSummary:contactSummary,
						nextContactTime:nextContactTime
					},
					success:function (data) {
						if(data.code=="1"){
							window.location.href="workbench/transaction/index.do"
						}else {
							alert(data.message);
						}
					}
				})

			})

			$("#cancel").click(function () {
				var ownerName=$("#create-transactionOwner").val("");
				var money=$("#create-amountOfMoney").val("");
				var transactionName=$("#create-transactionName").val("");
				var expectedClosingDate=$("#create-expectedClosingDate").val("");
				var stage=$("#create-transactionStage").val("");
				var type=$("#create-transactionType").val("");
				var source=$("#create-clueSource").val("");
				var activityId=$("#activityId").val("");
				var contactsId=$("#contactsId").val("");
				var description=$("#create-describe").val("");
				var contactSummary=$("#create-contactSummary").val("");
				var nextContactTime=$("#create-nextContactTime").val("");
				window.location.href="workbench/transaction/index.do"
			})
		});
	</script>
<body>

	<!-- 查找市场活动 -->	
	<div class="modal fade" id="findMarketActivity" role="dialog">
		<div class="modal-dialog" role="document" style="width: 80%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">查找市场活动</h4>
				</div>
				<div class="modal-body">
					<div class="btn-group" style="position: relative; top: 18%; left: 8px;">
						<form class="form-inline" role="form">
						  <div class="form-group has-feedback">
						    <input id="searchActivity" type="text" class="form-control" style="width: 300px;" placeholder="请输入市场活动名称，支持模糊查询">
						    <span class="glyphicon glyphicon-search form-control-feedback"></span>
						  </div>
						</form>
					</div>
					<table id="activityTable3" class="table table-hover" style="width: 900px; position: relative;top: 10px;">
						<thead>
							<tr style="color: #B3B3B3;">
								<td></td>
								<td>名称</td>
								<td>开始日期</td>
								<td>结束日期</td>
								<td>所有者</td>
							</tr>
						</thead>
						<tbody id="tbodyActivity">
						<tbody>
							<%--<tr>
								<td><input type="radio" name="activity"/></td>
								<td>发传单</td>
								<td>2020-10-10</td>
								<td>2020-10-20</td>
								<td>zhangsan</td>
							</tr>
							<tr>
								<td><input type="radio" name="activity"/></td>
								<td>发传单</td>
								<td>2020-10-10</td>
								<td>2020-10-20</td>
								<td>zhangsan</td>
							</tr>--%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<!-- 查找联系人 -->	
	<div class="modal fade" id="findContacts" role="dialog">
		<div class="modal-dialog" role="document" style="width: 80%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">查找联系人</h4>
				</div>
				<div class="modal-body">
					<div class="btn-group" style="position: relative; top: 18%; left: 8px;">
						<form class="form-inline" role="form">
						  <div class="form-group has-feedback">
						    <input id="searchContacts" type="text" class="form-control" style="width: 300px;" placeholder="请输入联系人名称，支持模糊查询">
						    <span class="glyphicon glyphicon-search form-control-feedback"></span>
						  </div>
						</form>
					</div>
					<table id="activityTable" class="table table-hover" style="width: 900px; position: relative;top: 10px;">
						<thead>
							<tr style="color: #B3B3B3;">
								<td></td>
								<td>名称</td>
								<td>邮箱</td>
								<td>手机</td>
							</tr>
						</thead>
						<tbody id="tbodyContacts">
							<%--<tr>
								<td><input type="radio" name="activity"/></td>
								<td>李四</td>
								<td>lisi@bjpowernode.com</td>
								<td>12345678901</td>
							</tr>
							<tr>
								<td><input type="radio" name="activity"/></td>
								<td>李四</td>
								<td>lisi@bjpowernode.com</td>
								<td>12345678901</td>
							</tr>--%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	
	<div style="position:  relative; left: 30px;">
		<h3>创建交易</h3>
	  	<div style="position: relative; top: -40px; left: 70%;">
			<button type="button" class="btn btn-primary" id="saveTran">保存</button>
			<button type="button" class="btn btn-default" id="cancel">取消</button>
		</div>
		<hr style="position: relative; top: -40px;">
	</div>
	<form class="form-horizontal" role="form" style="position: relative; top: -30px;">
		<div class="form-group">
			<label for="create-transactionOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<select class="form-control" id="create-transactionOwner">
					<c:forEach items="${userList}" var="user">
						<option value="${user.id}">${user.name}</option>
					</c:forEach>
				</select>
			</div>
			<label for="create-amountOfMoney" class="col-sm-2 control-label">金额</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="create-amountOfMoney">
			</div>
		</div>
		
		<div class="form-group">
			<label for="create-transactionName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="create-transactionName">
			</div>
			<label for="create-expectedClosingDate" class="col-sm-2 control-label">预计成交日期<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control mydate2" id="create-expectedClosingDate" readonly>
			</div>
		</div>
		
		<div class="form-group">
			<label for="create-accountName" class="col-sm-2 control-label">客户名称<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="create-accountName" placeholder="支持自动补全，输入客户不存在则新建">
			</div>
			<label for="create-transactionStage" class="col-sm-2 control-label">阶段<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
			  <select class="form-control" id="create-transactionStage">
			  	<option></option>
				  <c:forEach items="${stageList}" var="stage">
					  <option value="${stage.id}">${stage.value}</option>
				  </c:forEach>
			  	<%--<option>资质审查</option>
			  	<option>需求分析</option>
			  	<option>价值建议</option>
			  	<option>确定决策者</option>
			  	<option>提案/报价</option>
			  	<option>谈判/复审</option>
			  	<option>成交</option>
			  	<option>丢失的线索</option>
			  	<option>因竞争丢失关闭</option>--%>
			  </select>
			</div>
		</div>
		
		<div class="form-group">
			<label for="create-transactionType" class="col-sm-2 control-label">类型</label>
			<div class="col-sm-10" style="width: 300px;">
				<select class="form-control" id="create-transactionType">
					<option></option>
					<c:forEach items="${transactionTypeList}" var="transactionType">
						<option value="${transactionType.id}">${transactionType.value}</option>
					</c:forEach>
					<%-- <option>已有业务</option>
                     <option>新业务</option>--%>
				</select>
			</div>
			<label for="create-possibility" class="col-sm-2 control-label">可能性</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="create-possibility" readonly>
			</div>
		</div>
		
		<div class="form-group">
			<label for="create-clueSource" class="col-sm-2 control-label">来源</label>
			<div class="col-sm-10" style="width: 300px;">
				<select class="form-control" id="create-clueSource">
				  <option></option>
					<c:forEach items="${sourceList}" var="source">
						<option value="${source.id}">${source.value}</option>
					</c:forEach>
				<%--  <option>广告</option>
				  <option>推销电话</option>
				  <option>员工介绍</option>
				  <option>外部介绍</option>
				  <option>在线商场</option>
				  <option>合作伙伴</option>
				  <option>公开媒介</option>
				  <option>销售邮件</option>
				  <option>合作伙伴研讨会</option>
				  <option>内部研讨会</option>
				  <option>交易会</option>
				  <option>web下载</option>
				  <option>web调研</option>
				  <option>聊天</option>--%>
				</select>
			</div>
			<label for="create-activitySrc" class="col-sm-2 control-label">市场活动源&nbsp;&nbsp;<a href="javascript:void(0);" data-toggle="modal" <%--data-target="#findMarketActivity"--%> id="openActivity"><span class="glyphicon glyphicon-search"></span></a></label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="create-activitySrc" readonly>
				<input type="hidden" id="activityId">
			</div>
		</div>
		
		<div class="form-group">
			<label for="create-contactsName" class="col-sm-2 control-label">联系人名称&nbsp;&nbsp;<a href="javascript:void(0);" data-toggle="modal"<%-- data-target="#findContacts"--%> id="openContacts"><span class="glyphicon glyphicon-search"></span></a></label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="create-contactsName" readonly>
				<input type="hidden" id="contactsId">
			</div>
		</div>
		
		<div class="form-group">
			<label for="create-describe" class="col-sm-2 control-label">描述</label>
			<div class="col-sm-10" style="width: 70%;">
				<textarea class="form-control" rows="3" id="create-describe"></textarea>
			</div>
		</div>
		
		<div class="form-group">
			<label for="create-contactSummary" class="col-sm-2 control-label">联系纪要</label>
			<div class="col-sm-10" style="width: 70%;">
				<textarea class="form-control" rows="3" id="create-contactSummary"></textarea>
			</div>
		</div>
		
		<div class="form-group">
			<label for="create-nextContactTime" class="col-sm-2 control-label">下次联系时间</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control mydate" id="create-nextContactTime" readonly>
			</div>
		</div>
		
	</form>
</body>
</html>