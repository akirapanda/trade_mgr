<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>\
<html>
	<head>
		<script>
		$(document).ready(function() {
			$("#ser_btn").click(function(){
				$("input").attr("disabled","false");
				});
		});
		</script>
	</head>
	<body>
		<div class="moda" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					×
				</button>
				<h3 id="myModalLabel">
					Modal header
				</h3>
			</div>
			<div class="modal-body">
				<pre>testing</pre>
				<button type="button" class="btn" id="ser_btn">
					aa
				</button>
			</div>
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">
					Close
				</button>
				<button class="btn btn-primary" id="save_btn">
					Save changes
				</button>
			</div>
		</div>
	</body>
</html>