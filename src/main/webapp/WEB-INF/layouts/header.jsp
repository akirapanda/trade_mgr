<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div id="header">
	<div id="title">
		<h1>
			Trade Demo
			<small>--Trade应用演示</small>

			<shiro:user>
				<div class="btn-group pull-right">
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
						<i class="icon-user"></i> <shiro:principal property="name" /> <span
						class="caret"></span> </a>

					<ul class="dropdown-menu">
						<shiro:hasRole name="admin">
							<li>
								<a href="${ctx}/admin/user">Admin Users</a>
							</li>
							<li class="divider"></li>
						</shiro:hasRole>
						<li>
							<a href="${ctx}/profile">Edit Profile</a>
						</li>
						<li>
							<a href="${ctx}/logout">Logout</a>
						</li>
					</ul>
				</div>
			</shiro:user>
		</h1>
	</div>
	<shiro:user>
		<div class="navbar navbar-fixed-bottom">
			<div class="navbar-inner">
				<div class="container">
					<a class="btn btn-navbar" data-toggle="collapse"
						data-target=".nav-collapse"> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> </a>
					<div class="nav-collapse">
						<ul class="nav">
							<li>
								<a href="${ctx}"><i class="icon-home"></i>首页</a>
							</li>
							<li class="dropdown">
								<a class="dropdown-toggle" data-toggle="dropdown" href="#">
									<i class="icon-file"></i>合同<span class="caret"></span> </a>
								<ul class="dropdown-menu">
									<li>
										<a href="${ctx}/contract">查询合同</a>
									</li>
									<li>
										<a href="${ctx}/contract/create">新增合同</a>
									</li>
								</ul>
							</li>
							<li class="dropdown">
								<a class="dropdown-toggle" data-toggle="dropdown" href="#">
									<i class="icon-file"></i>客户<span class="caret"></span> </a>
								<ul class="dropdown-menu">
									<li>
										<a href="${ctx}/company">查询客户信息</a>
									</li>
									<li>
										<a href="${ctx}/company/create">新增客户信息</a>
									</li>
								</ul>
							</li>
							<li class="dropdown">
								<a class="dropdown-toggle" data-toggle="dropdown" href="#">
									单证 <span class="caret"></span> </a>
								<ul class="dropdown-menu">
									<li>
										<a href="${ctx}/lc/application">信用证管理</a>
									</li>
									<li>
										<a href="${ctx}/manage/company">客户公司信息管理</a>
									</li>
								</ul>
							</li>
							<li class="dropdown">
								<a class="dropdown-toggle" data-toggle="dropdown" href="#">
									账务 <span class="caret"></span> </a>
								<ul class="dropdown-menu">
									<li>
										<a href="${ctx}/finance/">账务信息管理</a>
									</li>
								</ul>
							</li>
							<li class="dropdown">
								<a class="dropdown-toggle" data-toggle="dropdown" href="#">
									报表 <span class="caret"></span> </a>
								<ul class="dropdown-menu">
									<li>
										<a href="${ctx}/lc/application">Order Status Report</a>
									</li>
								</ul>
							</li>
							<li class="dropdown">
								<a class="dropdown-toggle" data-toggle="dropdown" href="#">
									系统 <span class="caret"></span> </a>
								<ul class="dropdown-menu">
									<li>
										<a href="#">未开发</a>
									</li>
								</ul>
							</li>
						</ul>
						<form class="navbar-search pull-right" action="">
							<input type="text" class="search-query span2" placeholder="搜索">
						</form>
						<ul class="nav pull-right">
							<li>
								<a href="#">链接</a>
							</li>
							<li class="divider-vertical"></li>
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">下拉
									<b class="caret"></b> </a>
								<ul class="dropdown-menu">
									<li>
										<a href="#">动作</a>
									</li>
									<li>
										<a href="#">另一个动作</a>
									</li>
									<li>
										<a href="#">其他动作</a>
									</li>
									<li class="divider"></li>
									<li class="nav-header">
										导航头
									</li>
									<li>
										<a href="#">被间隔的链接</a>
									</li>
									<li>
										<a href="#">另一个链接</a>
									</li>
								</ul>
							</li>
						</ul>
					</div>
					<!-- /.nav-collapse -->
				</div>
			</div>
			<!-- /navbar-inner -->
		</div>
		<!-- /navbar -->
	</shiro:user>
</div>