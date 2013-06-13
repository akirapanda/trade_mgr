<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
	<head>
		<title>公司信息</title>

		<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#task_title").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();


			if('${action}'=='view'){
				$("input").attr("disabled",true);
				$("textarea").attr("disabled",true);
			}else{

			}

			$("#delete_href").click(function(){
				 $( "#dialog-confirm" ).dialog("open");
				});

			 $( "#dialog-confirm" ).dialog({
					 resizable: false,
					 height: 300,
					 width: 350,
					 modal: true,
					 autoOpen: false,
					 buttons: {
					 "Confirm": function() {
				     location.href="${ctx}/company/delete/${cmp.id}";
					 $( this ).dialog( "close" );
				 },
				 Cancel: function() {
					 $( this ).dialog( "close" );
				 }
				 }
				 });
		});
	</script>
	</head>

	<body>
		<!-- confirmation dialog -->
		<div id="dialog-confirm" title="是否确认删除该记录?">
			<p>
				客户[${cmp.chineseName}]的信息将被删除，请再次确认！
			</p>
		</div>


		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span2">
					<ul class="nav nav-pills nav-stacked">
						<li class="nav-header">
							动作
						</li>
						<li>
							<a href="#"><i class="icon-star-empty"></i>关注</a>
						</li>
						<li>
							<a href="${ctx}/company/update/${cmp.id}"><i
								class="icon-edit"></i>编辑</a>
						</li>
						<li>
							<a  id="delete_href" ><i
								class="icon-trash"></i>删除</a>
						</li>

						<li>
							<a href="#"><i class="icon-time"></i>变更历史</a>
						</li>
						<li>
							<a href="${ctx}/company/"><i class="icon-repeat"></i>返回列表</a>
						</li>
					</ul>
				</div>

				<div class="span10">
					<form id="inputForm" action="${ctx}/company/${action}"
						method="post" class="form-horizontal">
						<input type="hidden" name="id" value="${cmp.id}" />
						<fieldset>
							<legend>
								<small>公司信息</small>
							</legend>
							<div class="control-group">
								<label for="task_title" class="control-label">
									中文名称:
								</label>
								<div class="controls">
									<input type="text" id="chineseName" name="chineseName"
										value="${cmp.chineseName}" class="input-large required"
										minlength="3" />
								</div>
							</div>
							<div class="control-group">
								<label for="description" class="control-label">
									中文地址:
								</label>
								<div class="controls">
									<textarea id="chineseAddress" name="chineseAddress"
										class="input-large">${cmp.chineseAddress}</textarea>
								</div>
							</div>
							<div class="control-group">
								<label for="description" class="control-label">
									最后更新时间:
								</label>
								<div class="controls">
									<span class="input-large uneditable-input">${cmp.updateDate}</span>
								</div>
							</div>
							<div class="control-group">
								<label for="description" class="control-label">
									第一次登记时间:
								</label>
								<div class="controls">
									<span class="input-large uneditable-input">${cmp.registerDate}</span>
								</div>
							</div>
							<div class="form-actions">
								<input id="submit_btn" class="btn btn-primary" type="submit"
									value="提交" />
								&nbsp;
								<input id="cancel_btn" class="btn" type="button" value="返回"
									onclick="history.back()" />
							</div>

						</fieldset>
					</form>
				</div>
	</body>
</html>
