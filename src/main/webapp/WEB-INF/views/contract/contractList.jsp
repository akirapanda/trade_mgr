<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
	<head>
		<title>合同管理</title>
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
			<div class="span4 offset6">
				<form class="form-search" action="#">
					<label>
						名称：
					</label>
					<input type="text" name="search_LIKE_orderNo" class="input-medium"
						value="${param.search_LIKE_orderNo}">
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
						合同号
					</th>
					<th>
						(预期)签约日期
					</th>
					<th>
						(实际)签约日期
					</th>
					<th>
						买方名称
					</th>	
					<th>
						卖方名称
					</th>
					<th>
						合同状态
					</th>	
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${contracts.content}" var="contract">
					<tr>
						<td>
							<a href="${ctx}/contract/view/${contract.id}">${contract.orderNo}</a>
						</td>
						<td>
							<fmt:formatDate value="${contract.contractDate}" pattern="yyyy-MM-dd"/>
						</td>
						<td>
							<fmt:formatDate value="${contract.actContractDate}" pattern="yyyy-MM-dd"/>
						</td>
						<td>
							${contract.buyName}
						</td>
						<td>
							${contract.sellName}
						</td>
						<td>
					
							<fmt:setBundle basename="message" var="messages"/> 
     						   <fmt:bundle basename="message">  
     					       <fmt:message key="contract.status.${contract.status}"/> 
      					  </fmt:bundle>  
							
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<tags:pagination page="${contracts}" paginationSize="5" />
	</body>
</html>
