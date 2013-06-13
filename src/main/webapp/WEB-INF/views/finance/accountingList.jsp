<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
	<head>
		<title>账务信息</title>
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
		<div class="row">
			<div class="span4 offset7">
				<form class="form-search" action="#">
					<label>
						名称：
					</label>
					<input type="text" name="search_LIKE_title" class="input-medium"
						value="${param.search_LIKE_title}">
					<button type="submit" class="btn" id="search_btn">
						Search
					</button>
				</form>
			</div>
			<tags:sort />
		</div>

		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>
						记账参考号
					</th>
					<th>
						科目
					</th>
					<th>
						起息日
					</th>
					<th>
						内部参考号
					</th>
					<th>
						银行参考号
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${accountings.content}" var="account">
					<tr>
						<td>
							<a href="${ctx}/lc/application/update/${lc.id}">${account.no}</a>
						</td>
						<td>
							${account.cateId}
						</td>
						<td>
							${account.valueDate}
						</td>
						<td>
							${account.ourRef}
						</td>
						<td>
							${account.bankRef}
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<tags:pagination page="${accountings}" paginationSize="5" />


		<div>
			<a class="btn" href="${ctx}/lc/application/create">创建信用证</a>
		</div>
	</body>
</html>
