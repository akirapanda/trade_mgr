<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
	<head>
		<title>信用证申请书</title>

		<script>
		var search=jQuery.ajax({  
	        type:"get",  
	        url:"${ctx}/manage/company/list",  
	        dataType:"json",  
	        success: function aa(data) {  
		        console.log(data);
	        	return data;
	        }
		});

		
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#task_title").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
			$("#myModal").hide();

			$("#myModal .btn").click(function(){
				$("#myModal").hide();
			});
			$("#myModal .close").click(function(){
				$("#myModal").hide();
			});
			

			$(function() {
			    $('#datetimepicker4').datetimepicker({
			      pickTime: false
			    });
			  });
			
			$('#customer_address').focus(function (){
				jQuery.ajax({  
			        type:"get",  
			        url:"${ctx}/manage/company/viewByName/"+$("#customer_name").attr('value'),  
			        dataType:"json",  
			        success: function aa(root) {  
				        console.log(root);
			        	$('#customer_address').val(root.data.englishAddress);
			        	}
				});
				});
			$("#customer_name").typeahead({
				source:function (query, process) {
				jQuery.ajax({  
			        type:"get",  
			        url:"${ctx}/manage/company/list",  
			        dataType:"json",  
			        success: function aa(root) {  
						var companys=[];
				        if (root.data) {
				          $(root.data).each(function (index, val) {
					     	name=val.englishName;
					     	if(name){
				            companys.push(name);
					     	}
					      });
				        console.log(companys);
			        	return process(companys);
			        	}
			        }
				});
				}
			});
		});


	</script>
	</head>

	<body>
		<c:if test="${not empty message}">
			<div id="message" class="alert alert-error">
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
							<a href="${ctx}/lc/application/goods/${lc.id}">货物及其他信息</a>
						</li>
						<li>
							<a href="#">变更历史</a>
						</li>
					</ul>
				</div>

				<div class="span10">

					<form id="inputForm" action="${ctx}/lc/application/${action}"
						method="post" class="form-horizontal">
						<input type="hidden" name="id" value="${lc.id}" />
						<fieldset>
							<legend>
								<small>信用证申请书</small>
							</legend>
							<div class="control-group">
								<label for="contract_order_no" class="control-label">
									关联合同号：
								</label>
								<div class="controls">
									<input type="text" id="contract_order_no"
										name="contractOrderNo" value="${lc.contractOrderNo}"
										class="input-large" />
								</div>
							</div>
							<div class="control-group">
								<label for="lc_issue_date" class="control-label">
									信用证号码：
								</label>
								<div class="controls">
									<input type="text" id="lc_no" name="lcNo" value="${lc.lcNo}"
										class="input-large required" />
								</div>
							</div>
							<div class="control-group">
								<label for="lc_establish_type" class="control-label">
									信用证类型：
								</label>
								<div class="controls">
									<select name="lcType" id="lc_type">
										<option value="1"
											<c:if test="${lc.lcType==1}">selected="selected"</c:if>>
											主证
										</option>
										<option value="2"
											<c:if test="${lc.lcType==2}">selected="selected"</c:if>>
											从证
										</option>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label for="lc_issue_date" class="control-label">
									日期:
								</label>
								<div class="controls">
									<div id="datetimepicker4" class="input-append">
										<input data-format="yyyy-MM-dd" name="issueDate"
											value='<fmt:formatDate value="${lc.issueDate}" pattern="yyyy-MM-dd"/>'
											type="text"></input>
										<span class="add-on"> <i data-time-icon="icon-time"
											data-date-icon="icon-calendar"> </i> </span> ${lc.issueDate}
									</div>
								</div>
							</div>
							<div class="control-group">
								<label for="lc_establish_type" class="control-label">
									请贵行用：
								</label>
								<div class="controls">
									<select name="establishType" id="establish_type">
										<option value="1"
											<c:if test="${lc.establishType==1}">selected="selected"</c:if>>
											详电
										</option>
										<option value="2"
											<c:if test="${lc.establishType==2}">selected="selected"</c:if>>
											航空函/速递及简电
										</option>
										<option value="3"
											<c:if test="${lc.establishType==3}">selected="selected"</c:if>>
											航空函/速递
										</option>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label for="task_title" class="control-label">
									客户名称:
								</label>
								<div class="controls">
									<input type="text" data-provide="typeahead" id="customer_name"
										name="customerName" value="${lc.customerName}"
										class="input-large required" />
								</div>
							</div>
							<div class="control-group">
								<label for="description" class="control-label">
									客户地址:
								</label>
								<div class="controls">
									<input type="text" id="customer_address" name="customerAddress"
										class="input-large" value="${lc.customerAddress}">
								</div>
							</div>

							<div class="control-group">
								<label for="task_title" class="control-label">
									受益人名称:
								</label>
								<div class="controls">
									<input type="text" data-provide="typeahead" id="customer_name"
										name="beneName" value="${lc.beneName}"
										class="input-large required" />
								</div>
							</div>
							<div class="control-group">
								<label for="description" class="control-label">
									受益人地址:
								</label>
								<div class="controls">
									<input type="text" id="customer_address" name="beneAddress"
										class="input-large" value="${lc.beneAddress}">
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
			</div>
		</div>
	</body>
</html>


