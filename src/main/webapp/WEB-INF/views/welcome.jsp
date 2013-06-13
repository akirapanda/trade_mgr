<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
	<head>
		<title>欢迎页</title>
		<script>
	$(document).ready(function() {

	});
</script>
	</head>

	<body>
		<div class="hero-unit">
			<h1>
				Welcome
			</h1>
			<p>
				龟速开发ing ↓↓↓↓ 进度↓↓↓↓
			</p>
			<div class="progress progress-striped active">
				<div class="bar" style="width: 20%;"></div>
			</div>
		</div>

		<div class="well">
			<h3>
				已经完成的模块
			</h3>
			<ul>
				<li>
					客戶公司信息的维护模块
				</li>
			</ul>
			<h3>
				可用功能
			</h3>
			<ul>
				<li>
					合同管理
				</li>
				<li>
					LC管理及相应货物信息（不完全）
				</li>
				<li>
					账务信息管理（整合中）
				</li>
			</ul>

			<h3>
				Q1 13' 预计完成功能
			</h3>
			<ul>
				<li>
					LC管理及相应货物信息
				</li>
				<li>
					账务信息管理
				</li>
				<li>
					Order Status Report
				</li>
				<li>
					原有财务系统对接，对账
				</li>
				<li>
					银行对账单自动化解析
				</li>
			</ul>
		</div>
	</body>
</html>
