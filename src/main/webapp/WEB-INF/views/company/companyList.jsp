<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
	<head>
		<title>客户公司信息管理</title>
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
		
		
	<div class="page-header">
    	<h2>客户公司信息管理</h2>
    </div>
	<div class="row">
		<div class="span4 offset0">
			<form class="form-search" action="#">
				<label>名称：</label> <input type="text" name="search_LIKE_chineseName" class="input-medium" value="${param.search_LIKE_chineseName}"> 
				<button type="submit" class="btn" id="search_btn">Search</button>
		    </form>
	    </div>
	    <tags:sort/>
	</div>
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>
						中文名称
					</th>
					<th>
						中文地址
					</th>
					<th>
						英文名称
					</th>
					<th>
						英文地址
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${companys.content}" var="cmp">
					<tr>
						<td>
							<a href="${ctx}/company/view/${cmp.id}">${cmp.chineseName}</a>
						</td>
						<td>
							${cmp.chineseAddress}
						</td>
						<td>
							${cmp.englishName}
						</td>
						<td>
							${cmp.englishAddress}
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<tags:pagination page="${companys}" paginationSize="10" />

	</body>
</html>
