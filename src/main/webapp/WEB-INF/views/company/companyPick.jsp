<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
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
			<th>
				登记日期
			</th>
			<th>
				更新日期
			</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${companys.content}" var="cmp">
			<tr>
				<td>
					${cmp.chineseName}
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
				<td>
					${cmp.registerDate}
				</td>
				<td>
					${cmp.updateDate}
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<tags:pagination page="${companys}" paginationSize="5" />

