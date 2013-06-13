<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>信用证管理</title>
</head>

<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<div class="row">
		<div class="span4 offset7">
			<form class="form-search" action="#">
				<label>名称：</label> <input type="text" name="search_LIKE_title" class="input-medium" value="${param.search_LIKE_title}"> 
				<button type="submit" class="btn" id="search_btn">Search</button>
		    </form>
	    </div>
	    <tags:sort/>
	</div>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			<th>信用证号</th><th>关联合同号</th>
			<th>申请人名称</th><th>受益人名称</th>
			<th>动作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${letters}" var="lc">
			<tr>
				<td><a href="${ctx}/lc/application/update/${lc.id}">${lc.lcNo}</a></td>
				<td>${lc.contractOrderNo}</td> 
				<td>${lc.customerName}</td>
				<td>${lc.beneName}</td>
				<td><a href="${ctx}/lc/application/download/${lc.id}" target=_blank>下载</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	

	<div><a class="btn" href="${ctx}/lc/application/create">创建信用证</a></div>
</body>
</html>
