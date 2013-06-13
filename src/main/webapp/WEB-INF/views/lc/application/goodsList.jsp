<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
	<head>
		<title>货物信息</title>
		<script>
		$(document).ready(function() {
			$("#inputForm").validate();
			});
		</script>
	</head>

	<body>
		<c:if test="${not empty message}">
			<div id="message" class="alert alert-success">
				<button data-dismiss="alert" class="close">
					×
				</button>
				${message}
			</div>
		</c:if>

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
							<a href="${ctx}/lc/application/update/${lc.id}">信用证信息</a>
						</li>
						<li>
							<a href="#">变更历史</a>
						</li>
					</ul>
				</div>

				<div class="span10">
					<c:if test="${action !='update'}">
						<form id="inputForm" action="${ctx}/lc/goods/create" method="post"
							class="form-horizontal">
					</c:if>
					<a id="create_goods" href="#" class="btn btn-primary"
						data-toggle="collapse" data-target="#form">隐藏/显示表单</a>

					<div id="form" class="collapse in">
						<c:if test="${action =='update'}">
							<form id="inputForm" action="${ctx}/lc/goods/update/${good.id}"
								method="post" class="form-horizontal">
						</c:if>

						<input type="hidden" name="id" value="${good.id}" />
						<input type="hidden" name="lcId" value="${lc.id}" />

						<fieldset>
							<legend>
								<small>货物信息</small>
							</legend>
							<div class="control-group">
								<label for="contract_order_no" class="control-label">
									信用证号：
								</label>
								<div class="controls">
									<span class="input-large uneditable-input">${lc.lcNo}</span>
								</div>
							</div>
							<div class="control-group">
								<label for="lc_issue_date" class="control-label">
									厂商:
								</label>
								<div class="controls">
									<input type="text" id="band" name="band" value="${good.band}"
										class="input-large required" />
								</div>
							</div>
							<div class="control-group">
								<label for="lc_issue_date" class="control-label">
									商品名:
								</label>
								<div class="controls">
									<input type="text" id="goods_name" name="goodsName"
										value="${good.goodsName}" class="input-large required" />
								</div>
							</div>
							<div class="control-group">
								<label for="lc_issue_date" class="control-label">
									单价:
								</label>
								<div class="controls">
									<input type="text" id="goods_name" name="tradeTerm"
										value="${good.goodsName}" class="input-small required"
										placeholder="价格术语" />
									<input type="text" id="goods_name" name="ccy"
										value="${good.ccy}" class="input-small required"
										placeholder="币种" />
									<input type="text" id="goods_name" name="price"
										value="${good.price}" class="input-small required"
										placeholder="价格" />
									<input type="text" id="goods_name" name="unit"
										value="${good.unit}" class="input-small required"
										placeholder="单位" />
								</div>
							</div>
							<div class="control-group">
								<label for="lc_issue_date" class="control-label">
									重量:
								</label>
								<div class="controls">
									<input type="text" id="weight" name="weight"
										value="${good.weight}" class="input-small required"
										placeholder="重量" />
									<input type="text" id="weight_unit" name="weightUnit"
										value="${good.weightUnit}" class="input-small required"
										placeholder="单位" />
								</div>
							</div>
							<div class="control-group">
								<label for="lc_issue_date" class="control-label">
									结算方式:
								</label>
								<div class="controls">
									<input type="text" id="pymt_type" name="pymtType"
										value="${good.pymtType}" class="input-small required"
										placeholder="ex:DP or TT" />
									<input type="text" id="pymt_time" name="pymtTime"
										value="${good.pymtTime}" class="input-large required"
										placeholder="ex:90DAYS AFTER B/L DATE" />
								</div>
							</div>
							<div class="form-actions">
								<c:if test="${action !='update'}">
									<input id="submit_btn" class="btn btn-primary" type="submit"
										value="新增提交" />
									&nbsp;
									<input id="cancel_btn" class="btn" type="button" value="返回"
										onclick="history.back()" />
								</c:if>
								<c:if test="${action =='update'}">
									<input id="update_submit_btn" class="btn btn-primary"
										type="submit" value="编辑提交" />
									&nbsp;
									<input id="cancel_btn" class="btn" type="button" value="返回"
										onclick="history.back()" />
								</c:if>
							</div>
						</fieldset>
						</form>
					</div>


					<div>
						<table id="contentTable"
							class="table table-striped table-bordered table-condensed">
							<thead>
								<tr>
									<th>
										厂商
									</th>
									<th>
										商品名
									</th>
									<th>
										单价
									</th>
									<th>
										重量(数量)
									</th>
									<th>
										结算方式
									</th>
									<th>
										操作
									</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${lc.goods}" var="goods">
									<tr>
										<td>
											${goods.band}
										</td>
										<td>
											${goods.goodsName}
										</td>
										<td>
											${goods.tradeTerm} ${goods.ccy}
											<fmt:formatNumber type="number" maxFractionDigits="3"
												value="${goods.price}" />
											/${goods.unit}
										</td>
										<td>
											<fmt:formatNumber type="number" maxFractionDigits="3"
												value="${goods.weight}"></fmt:formatNumber>
											${goods.weightUnit}
										</td>
										<td>
											${goods.pymtType} ${goods.pymtTime}
										</td>
										<td>
											<a id="update_goods"
												href="${ctx}/lc/goods/update/${goods.id}"
												class="btn btn-primary">编辑</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>

			</div>

		</div>
	</body>
</html>
