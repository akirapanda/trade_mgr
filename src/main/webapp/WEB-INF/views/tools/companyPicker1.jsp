<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<a href="${ctx}/manage/company/pickup1" data-target="${ctx}/manage/company/pickup1" role="button" class="btn" data-toggle="modal">
    Launch demo modal</a>

<div class="modal hide" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">Modal header1</h3>
      </div>
      <div class="modal-body">
123
      </div>
      <div class="modal-footer">
        <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
        <button class="btn btn-primary" id="save_btn">Save changes</button>
      </div>
    </div>
</html>