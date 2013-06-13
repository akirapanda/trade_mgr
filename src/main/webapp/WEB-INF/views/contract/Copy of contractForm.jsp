<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html>
	<head>
		<title>合同信息</title>

		<script>
		$(document).ready(function() {
			/*判断当前的Action如果是View 全部UI*/
			if('${action}'=='view'){
				$("input").attr("disabled",true);
				$("textarea").attr("disabled",true);
				$("select").attr("disabled",true);
				console.log("11");
				}
			else{
				$("input").attr("disabled",false);
				$("textarea").attr("disabled",false);
				$("select").attr("disabled",false);
				
				//聚焦第一个输入框
				$("#task_title").focus();
				//为inputForm注册validate函数
				$("#inputForm").validate();
			    $('#datetimepicker1').datetimepicker({
				      pickTime: false
				    });
			    $('#datetimepicker2').datetimepicker({
				      pickTime: false
				    });
				}

		});
	</script>
	</head>

	<body>
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
							<a href="${ctx}/contract/update/${contract.id}"><i
								class="icon-edit"></i>编辑</a>
						</li>
						<li>
							<a href="#"><i class="icon-time"></i>变更历史</a>
						</li>
						<li>
							<a href="${ctx}/contract/"><i class="icon-repeat"></i>返回列表</a>
						</li>
					</ul>
				</div>

				<div class="span10">
					<c:if test="${not empty errorMessage}">
						<div id="message" class="alert alert-error">
							<button data-dismiss="alert" class="close">
								×
							</button>
							${errorMessage}
						</div>
					</c:if>
					<c:if test="${not empty message}">
						<div id="message" class="alert alert-success">
							<button data-dismiss="alert" class="close">
								×
							</button>
							${message}
						</div>
					</c:if>

					<a href="${ctx}/manage/company/pickup" data-target="#xoo"
						role="button" class="btn" data-toggle="modal"> Launch demo
						modal</a>
			
          <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" class="modal hide fade" id="xoo" style="display: none;">
            <div class='modal-body'>
            </div>
          </div>

					<form id="inputForm" action="${ctx}/contract/${action}"
						method="post" class="form-horizontal">
						<input type="hidden" name="id" value="${contract.id}" />
						<fieldset>
							<legend>
								<small>合同信息</small>
							</legend>
							<div class="control-group">
								<label for="task_title" class="control-label">
									合同编号:
								</label>
								<div class="controls">
									<input type="text" id="task_title" name="orderNo"
										value="${contract.orderNo}" class="input-large required"
										minlength="3" />
								</div>
							</div>

							<div class="control-group">
								<label for="lc_issue_date" class="control-label">
									（预期）签约日期:
								</label>
								<div class="controls">
									<div id="datetimepicker1" class="input-append">
										<input data-format="yyyy-MM-dd" name="contractDate"
											value='<fmt:formatDate value="${contract.contractDate}" pattern="yyyy-MM-dd"/>'
											type="text"></input>
										<span class="add-on"> <i data-time-icon="icon-time"
											data-date-icon="icon-calendar"> </i> </span>
									</div>
								</div>
							</div>
							<div class="control-group">
								<label for="lc_issue_date" class="control-label">
									（实际）签约日期:
								</label>
								<div class="controls">
									<div id="datetimepicker2" class="input-append">
										<input data-format="yyyy-MM-dd" name="actContractDate"
											value='<fmt:formatDate value="${contract.actContractDate}" pattern="yyyy-MM-dd"/>'
											type="text"></input>
										<span class="add-on"> <i data-time-icon="icon-time"
											data-date-icon="icon-calendar"> </i> </span>
									</div>
								</div>
							</div>

							<div class="control-group">
								<label for="description" class="control-label">
									买方名称:
								</label>
								<div class="controls">
									<textarea id="buy_name" name="buyName" class="input-large">${contract.buyName}</textarea>
								</div>
							</div>

							<div class="control-group">
								<label for="description" class="control-label">
									卖方名称:
								</label>
								<div class="controls">
									<textarea id="sell_name" name="sellName" class="input-large">${contract.sellName}</textarea>
								</div>
							</div>
							<div class="control-group">
								<label for="lc_establish_type" class="control-label">
									合同状态:
								</label>
								<div class="controls">
									<select name="establishType" id="establish_type">
										<fmt:setBundle basename="message" var="messages" />
										<fmt:bundle basename="message">
											<option value="0"
												<c:if test="${contract.status==0}">selected="selected"</c:if>>
												<fmt:message key="contract.status.0" />
											</option>
											<option value="1"
												<c:if test="${contract.status==1}">selected="selected"</c:if>>
												<fmt:message key="contract.status.1" />
											</option>
											<option value="2"
												<c:if test="${contract.status==2}">selected="selected"</c:if>>
												<fmt:message key="contract.status.2" />
											</option>
										</fmt:bundle>
									</select>
								</div>
							</div>
							<c:if test="${action!='view'}">
								<div class="form-actions">
									<input id="submit_btn" class="btn btn-primary" type="submit"
										value="提交" />
									&nbsp;
									<input id="cancel_btn" class="btn" type="button" value="返回"
										onclick="history.back()" />
								</div>
							</c:if>
							<c:if test="${action=='view'}">
								<div>
									<table id="contentTable"
										class="table table-striped table-bordered table-condensed">
										<thead>
											<tr>
												<th>
													信用证号
												</th>
												<th>
													关联合同号
												</th>
												<th>
													申请人名称
												</th>
												<th>
													受益人名称
												</th>
												<th>
													动作
												</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${letters}" var="lc">
												<tr>
													<td>
														<a href="${ctx}/lc/application/update/${lc.id}">${lc.lcNo}</a>
													</td>
													<td>
														${lc.contractOrderNo}
													</td>
													<td>
														${lc.customerName}
													</td>
													<td>
														${lc.beneName}
													</td>
													<td>
														<a href="${ctx}/lc/application/download/${lc.id}"
															target=_blank>下载</a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</c:if>

						</fieldset>
					</form>
				</div>
	</body>
</html>
