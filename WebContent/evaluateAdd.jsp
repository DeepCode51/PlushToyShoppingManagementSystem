<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户评价</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/store.css">
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script charset="utf-8" src="admin/editor/kindeditor.js"></script>
<script language="javascript" type="text/javascript"> 
	
</script>
<style type="text/css">
 body,td,div
 {
   font-size:12px;
 }
</style>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div id="middle">
	 <div id="product_menu">
	 	 <jsp:include page="leftMenu.jsp"></jsp:include>
	 </div>
	 <!--  购物车 -->
	<div id="product_info">
			<div class="title">个人中心  &gt;&gt;  用户评价</div>
			<div style="margin-top:5px">
				 <form id="info" name="info" action="page_addEvaluate.action" method="post" style="width:100%;height:100%">    
				 <input type="hidden" name="orders_no" id="orders_no" value="${orders.orders_no}"/>
				 <input type="hidden" name="user_id" id="user_id" value="${userFront.user_id}"/>
				 <input type="hidden" name="nick_name" id="nick_name" value="${userFront.user_name}"/>
				 <table class="ptable" style="width:95%;margin-bottom:5px;">
				    <tr>
			          <td width="15%" align="right" style="padding-right:5px">订单编号：</td>
			          <td width="*">
			           ${orders.orders_no}
			          </td>
			        </tr>
			        <tr>
			          <td width="15%" align="right" style="padding-right:5px">评价额度：</td>
			          <td width="*">
			          <select id="evaluate_level" name="evaluate_level" class="selectstyle" style="width:200px;">
				      	<option value="1">1分----失望</option>
				      	<option value="2">2分----不满</option>
				      	<option value="3">3分----一般</option>
				      	<option value="4">4分----满意</option>
				      	<option value="5" selected>5分----惊喜</option>
				      </select>
			          </td>
			        </tr>
			        <tr>
			          <td align="right" style="padding-right:5px">评价内容：</td>
			          <td class="KEEdit">
			          	<textarea style="width:450px;height:150px" name="evaluate_content" id="noticeContent"></textarea> 
			          </td>
			        </tr> 
			        <tr class="">
			          <td align="center" height="30" colspan="2">
			            <input type="button" id="addBtn" Class="btnstyle" value="发 表" />
			          </td>
			        </tr>
			     </table>
			     </form>
			</div>
		</div>
	 <!--  购物车 -->
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
	$(document).ready(function(){
		var num=/^\d+(\.\d+)?$/;
		var num2=/^\d+$/;
		$("#addBtn").bind('click',function(){
			if($("#noticeContent").val()==''){
				alert('评价内容不能为空');
				return;
			}
			var aQuery = $("#info").serialize();  
			$.post('page_addEvaluate.action',aQuery,
				function(responseObj) {
						if(responseObj.success) {	
							 alert('评价成功！');
							 window.location.href="page_listMyOrderss.action";
						}else  if(responseObj.err.msg){
							 alert('评价失败：'+responseObj.err.msg);
						}else{
							 alert('评价失败：服务器异常！');
						}	
			},'json');
		 });
		
	});	 
</script>
</body>
</html>